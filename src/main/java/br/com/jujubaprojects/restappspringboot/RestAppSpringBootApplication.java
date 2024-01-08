package br.com.jujubaprojects.restappspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "br.com.jujubaprojects.restappspringboot.Model.person")
@ComponentScan(basePackages = "br.com.jujubaprojects.restappspringboot")

public class RestAppSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestAppSpringBootApplication.class, args);
    }
}
