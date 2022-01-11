package com.example.practice_react.controller;

import com.example.practice_react.dto.ResponseDTO;
import com.example.practice_react.dto.TodoDTO;
import com.example.practice_react.model.TodoEntity;
import com.example.practice_react.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    //TodoController가 초기화 될 때 스프링은 알아서 TodoService를 초기화 또는 검색해서 TodoController에 주입해준다.
    @Autowired
    private TodoService todoService;

    @GetMapping("/test_prac")
    public ResponseEntity<?> testTodo_prac() {
        List<String> testList;
        testList = todoService.testService_prac();

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(testList).build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String str = todoService.testService();
        List<String> list = new ArrayList<>();
        list.add(str);

        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping            //ResponseEntity 는 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스이다.
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
            //임시 아이디
            String temp = "temp-user";

            //★★★사용자로부터 요청이 들어올 때 해당 DTO를 Entity로 변환한다.
            TodoEntity entity = TodoDTO.toEntity(dto);

            //id를 null로 초기화한다.      //유저가 아닌 오브젝트 id이다.
            entity.setId(null);

            //임시 사용자 아이디를 넣어준다.
            entity.setUserId(temp);

            //★★★서비스를 이용해 Todo Entity를 생성한다.
            List<TodoEntity> entities = todoService.create(entity);

            //자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
            //map은 Stream에서 뽑아져 나오는 데이터에 변경을 가해준다.
            //아래는 TodoEntity로 된 데이터들을 TodoDTO::new 로 DTO객체로 바꿔서 .collect에서 toList 즉, List컬렉션에 모아둔다.
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            //변환된 TodoDTO 리스트를 이용해 이제 최종적으로 ResponseDTO를 초기화 하고 클라이언트에게 리턴해준다.
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String temp = "temp-user";

        //서비스에서 retrieve 메서드를 이용해  Todo 리스트를 가져온다
        List<TodoEntity> entities = todoService.retrieve(temp);

        //TodoEntity를 TodoDTO로 변환해야지!
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        //변환된 TodoDTO 의 dtos를 이용해 responseDTO를 초기화 한다.
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        //이제 반환해준다.
        return ResponseEntity.ok().body(response);


    }

    @PutMapping
    public ResponseEntity<?> updateTodoList(@RequestBody TodoDTO dto){

        //클라이언트가 업데이트를 하기위해 요청을 보내온다.
        String temp = "temp-user";

        //dto를 TodoEntity로 변환한다.
        TodoEntity entity = TodoDTO.toEntity(dto);

        //변환 후에 setUserid 설정
        entity.setUserId(temp);

        //서비스를 이용해 entity를 업데이트 한다
        List<TodoEntity> entities = todoService.update(entity);

        //업데이트 된 것을 TodoDTO로 변환한다.
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        //변환된 TodoDTO 리스트를 이용해서 responseDTO를 초기화 한다.
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        //ResponseDTO를 클라이언트에게 리턴한다.
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodoList(@RequestBody TodoDTO dto){
        try {
            //클라이언트에서 삭제에 관한 요청이 오면,
            String temp = "temp-user";

            //dto 를 Entity로 변환
            TodoEntity entity = TodoDTO.toEntity(dto);

            //임시 사용자 아이디를 entity에 넣어줌.
            entity.setUserId(temp);

            //해당 entity를 삭제 후에 retrieve로 조회된 데이터를 받은것을 리스트 entities에 넣어준다.
            List<TodoEntity> entities = todoService.delete(entity);

            //이걸 다시 DTO로 변환
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            //ResponseDTO 초기화
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            //클라이언트에게 삭제 후 조회된 리스트를 리턴.
            return ResponseEntity.ok().body(response);


        }catch(Exception e){
            //예외가 있는 경우 에러메시지를 리턴한다.
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }

    }
}
