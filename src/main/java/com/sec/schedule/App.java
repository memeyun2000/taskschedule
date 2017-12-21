package com.sec.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@ImportResource(locations = { "classpath:applicationcontext-task.xml" })
@EnableScheduling
public class App{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
