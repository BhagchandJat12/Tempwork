package com.user.user.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.transaction.Transactional;
import com.user.user.User.Exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    EmailSender emailSender;

    @GetMapping("/")
    public List<User> allUser() {
        return this.repository.findAll();
    }

    @Transactional
    @PostMapping(path = "/user")
    public ResponseEntity<Object> register(@Validated @RequestBody User user) {
        User u = repository.findByEmail(user.getemail());
        if (u != null) {
            throw new MyException("Email Already Exist");

        } else {

            BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            String s = b.encode(user.getpassword());
            user.setpassword(s);
            emailSender.mailSender(user.getemail(), "Otp for verification is <234455>", "Email Verification");
            this.repository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);

        }

    }

    @Transactional
    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody User user) throws IOException {
        User us = repository.findByEmail(user.getemail());

        if (us != null) {
            BCryptPasswordEncoder bEncoder = new BCryptPasswordEncoder();
            boolean valid = bEncoder.matches(user.getpassword(), us.getpassword());

            if (valid) {

                try (
                        ObjectOutputStream osw = new ObjectOutputStream(
                                new FileOutputStream("file.txt"))) {

                    osw.writeObject(user);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return new ResponseEntity<>("Login Successfully", HttpStatus.ACCEPTED);
            } else {
                throw new MyException("Enter valid Password");
            }

        } else {
            throw new MyException("Enter valid User or Password");
        }

    }

    @GetMapping("/file")
    public User serious() throws ClassNotFoundException {
        User u = null;
        try (
                ObjectInputStream os = new ObjectInputStream(
                        new FileInputStream("file.txt"))) {
            u = (User) os.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw e;
        }
        return u;
    }
}
