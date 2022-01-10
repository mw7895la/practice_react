package com.example.practice_react.dto;


import com.example.practice_react.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    private String id;
    private String title;
    private boolean done;
    //userId는 나중에 스프링시큐리티로 인증할것.


    //entity -> DTO 로 변환하는
    public TodoDTO(final TodoEntity entity){
        this.id = entity.getId();
        this.title=entity.getTitle();
        this.done=entity.isDone();
    }
}
