package com.example.practice_react.service;

import com.example.practice_react.model.TodoEntity;
import com.example.practice_react.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j      //Slf4j를 사용하려면 구현부를 연결해줘야 하는데 스프링이 기본적으로 해줌. 스프링이 기본적으로 사용하는 로그 라이브러리는 Logback이다.
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


    //Service의 Create 부분.  repository에 저장하고 컨트롤러로 리턴한다.
    public List<TodoEntity> create(final TodoEntity entity){
        validate(entity);
        repository.save(entity);
        log.info("entity id : {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId){
        log.info("Todo List를 보기 위한 Service : {}", userId);
        return repository.findByUserId(userId);
    }

    //update를 위한 Service
    public List<TodoEntity> update(final TodoEntity entity){
        //업데이트 하기 전, 유효한지부터 확인한다
        validate(entity);

        //유효하면 넘겨받은 entity의 id를 이용해  TodoEntity를 가져온다. 존재하지 않으면 업뎃 불가.
        final Optional<TodoEntity> original = repository.findById(entity.getId());


        //ifPresent 메소드로 값이 존재하면 덮어씌운다.
        original.ifPresent(woojin ->{
            woojin.setTitle(entity.getTitle());
            woojin.setDone(entity.isDone());
            //데이터베이스에 새 값을 저장한다.
            repository.save(woojin);
        });

        return retrieve(entity.getUserId());

    }



    //entity 객체에 대한 유효성 검사. validate
    private void validate(final TodoEntity entity){
        if(entity==null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("entity cannot be null");
        }

        if(entity.getUserId()==null){
            log.warn("Unknown User");
            throw new RuntimeException("Unknown User");
        }

        log.info("validate success");

    }
}
