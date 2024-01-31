package br.com.jujubaprojects.restappspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.jujubaprojects.restappspringboot")
public class RestAppSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestAppSpringBootApplication.class, args);
    }
}
