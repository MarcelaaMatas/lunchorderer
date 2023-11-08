package com.myproject.lunchordererapplication.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@ConfigurationProperties("spring.mail")
public class SpringMailProperties {

    private String host;
    private int port;
    private String username;
    private String password;
}
