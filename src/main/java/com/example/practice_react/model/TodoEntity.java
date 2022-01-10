package com.example.practice_react.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity         //Entity클래스는 그 자체로 테이블을 정의해야된다.
//@Table(name ="Todo")
public class TodoEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid")  //커스텀으로 문자열 형태의 uuid를 사용하기 위해 커스텀 Generator를 만듦.
    private String id;          //이 오브젝트의 아이디


    private String userId;      //이 오브젝트를 생성한 사용자의 아이디
    private String title;       /* 타이틀 ex) 운동하기*/
    private boolean done;       //true -todo를 완료한 경우(checked)
}
