package com.example.practice_react.controller;

import com.example.practice_react.dto.ResponseDTO;
import com.example.practice_react.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("todo")
public class TodoController {

    //TodoController가 초기화 될 때 스프링은 알아서 TodoService를 초기화 또는 검색해서 TodoController에 주입해준다.
    @Autowired
    private TodoService todoService;

    @GetMapping("/test_prac")
    public ResponseEntity<?> testTodo_prac(){
        List<String> testList;
        testList = todoService.testService_prac();

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(testList).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testTodo(){
        String str = todoService.testService();
        List<String> list = new ArrayList<>();
        list.add(str);

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }
}
