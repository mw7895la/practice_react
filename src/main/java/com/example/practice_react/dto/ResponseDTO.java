package com.example.practice_react.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
    //TodoDTO 뿐 아니라 다른 모델의 DTO도 ResponseDTO를 이용해 리턴할 수 있도록 한다.
    private String error;
    private List<T> data;
}
