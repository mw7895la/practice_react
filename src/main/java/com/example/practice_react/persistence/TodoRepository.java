package com.example.practice_react.persistence;

import com.example.practice_react.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository         //@Component 어노테이션의 특별 케이스 이것도 스프링이 관리한다.
public interface TodoRepository extends JpaRepository<TodoEntity,String> {
    //첫번째 매개변수인 T는 테이블에 매핑될 엔티티 클래스
    //두번째 매개변수는 기본키의 타입
    List<TodoEntity> findByUserId(String userId);
    //SELECT * FROM TodoRepository WHERE userid = `{userid}` 와 같은 쿼리를 작성실행.

}
