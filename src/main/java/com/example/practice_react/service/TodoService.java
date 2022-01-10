package com.example.practice_react.service;

import com.example.practice_react.model.TodoEntity;
import com.example.practice_react.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<String> testService_prac(){
        List<String> list = new ArrayList<>();

        list.add("kimwoojin");
        list.add("woojinkim");
        list.add("jinwookim");

        return list;
    }

    public String testService(){
        //TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My First Item").build();

        //TodoEntity 저장
        repository.save(entity);

        //TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }
}
