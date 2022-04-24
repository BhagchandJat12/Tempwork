package com.user.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void mailSender(String toEmail, String body, String subject) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("bhagchandjat258@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        try {
            mailSender.send(message);
            System.out.println("Email Sent");
        } catch (MailException e) {
            System.err.println(e.getMessage());

        }

    }

}
