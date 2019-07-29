package com.promart.client;

import com.github.fabiomaffioletti.firebase.EnableFirebaseRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Application Class.
 *
 * @author Christian Arias [chri.arias@gmail.com]
 */
@SpringBootApplication(exclude = {ThymeleafAutoConfiguration.class})
@EnableFirebaseRepositories
@ComponentScan(basePackages = "com.promart")
public class Application extends SpringBootServletInitializer {

    /**
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
