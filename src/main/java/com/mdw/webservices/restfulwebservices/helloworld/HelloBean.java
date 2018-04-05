package com.mdw.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class HelloBean {

    String message;

    public HelloBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloBean{" +
                "message='" + message + '\'' +
                '}';
    }

    @RestController
    public static class HelloWorldController {


        @GetMapping(path="/hello")
        public String helloWorld(){
            String str = "Hello World";
            return str;
        }

        @GetMapping(path="/hello-bean")
        public HelloBean helloWorldBean(){
            HelloBean hello = new HelloBean("hello from bean");
            return hello;
        }

    }
}
