package com.example.practice_react.controller;

import com.example.practice_react.dto.ResponseDTO;
import com.example.practice_react.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController     // @Controller + @ResponseBody 로 이루어져있는 어노테이션이다.
//@Controller는 @Component로 스프링이 이 클래스의 오브젝트를 알아서 생성하고 다른 오브젝트들과의 의존성을 연결한다는 뜻.
//@ResponseBody는 이 클래스의 메서드가 리턴하는 것은 웹 서비스의 ResponseBody라는 뜻.
@RequestMapping("test")     //리소스
public class TestController {

    @GetMapping
    public String testController(){
        return "Hello WORLD";
    }

    @GetMapping("/{id}")
    public String testControllerWithPath(@PathVariable(required = false)String id){
        return "kim woo jin"+id;
    }


    //RequestBody로 보내오는 JSON을  TestRequestBodyDTO 오브젝트로 변환해서 가져오라는 뜻.
    //클라이언트는 요청 바디를 JSON 형태의 문자열로 넘겨준다.
    @GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO){
        return "hello woojin id "+testRequestBodyDTO.getId() + "message :"+testRequestBodyDTO.getMessage();
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody(){
        List<String> list= new ArrayList<>();
        list.add("Hello World ! I'm ResponseDTO");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }


    //ResponseEntity는 HTTP응답의 Body뿐 아니라 다른 매개변수들, status, header를 조작하고 싶을 때 사용한다.
    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testContrllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("Hello World ! I'm ResponseEntity. And you got 400!");

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();

        return ResponseEntity.badRequest().body(response);
    }
}
