package br.com.judev.version2025ju.controllers;

import br.com.judev.version2025ju.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
public class GreetingsController {

    private static final String template = "Hello , %s !";
    private static  final AtomicLong counter = new AtomicLong();


    //http://localhost:8080/greeting?name=Junior
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World")  String name){
      //  return new String (String.format(template, name));
        return new Greeting(counter.incrementAndGet() ,String.format(template, name));

    }
}
