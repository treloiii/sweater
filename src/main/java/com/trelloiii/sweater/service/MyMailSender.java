package com.trelloiii.sweater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MyMailSender {
    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailTo,String subject,String message){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        sender.send(simpleMailMessage);
    }
}
