package com.user.user.User;

import java.util.List;
import javax.transaction.Transactional;
import com.user.user.User.Exception.EmailAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/")
    public List<User> allUser() {
        return this.repository.findAll();
    }

    @Transactional
    @PostMapping(path = "/user")
    public User register(@Validated @RequestBody User user) {
        User u = repository.findByEmail(user.getemail());
        if (u != null) {
            throw new EmailAlreadyExistException("Email Already Exist");

        } else {

            BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            String s = b.encode(user.getpassword());
            user.setpassword(s);
            this.repository.save(user);
            return user;
        }

    }
}
