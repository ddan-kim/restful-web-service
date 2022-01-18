package com.study.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {


    // 어노테이션을 통한 의존성 주입
    @Autowired
    private MessageSource messageSource;

    // GET
    // /hello-world (endpoint)
    //
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
       return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
       return new HelloWorldBean("Hellow world");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
       return new HelloWorldBean(String.format("Hellow world, %s", name));
    }

    // 다국어처리
    @GetMapping(path = "/hellow-world-internationalized")
    public String hellowWorldInternationalized(
            @RequestHeader(name="Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null,  locale);
    }
}
