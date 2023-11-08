package com.myproject.lunchordererapplication.infrastructure;

import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@RequiredArgsConstructor
@Configuration
public class EmailServiceConfig {

    private final SpringMailProperties springMailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(springMailProperties.getHost());
        mailSender.setPort(springMailProperties.getPort());

        mailSender.setUsername(springMailProperties.getUsername());
        mailSender.setPassword(springMailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
