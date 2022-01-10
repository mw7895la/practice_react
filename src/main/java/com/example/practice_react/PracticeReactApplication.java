package com.example.practice_react;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//이 어노테이션이 @ComponentScan을 포함하고 있다.
//이 어노테이션이 build.gradle에 h2를 디페던시로 설정하면 스프링이 알아서 App을 H2데이터베이스에 연결해준다.
@SpringBootApplication
public class PracticeReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeReactApplication.class, args);
    }

}
