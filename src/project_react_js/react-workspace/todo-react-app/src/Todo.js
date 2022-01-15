import React from 'react';
import {ListItem, ListItemText, InputBase, Checkbox,ListItemSecondaryAction,IconButton} from "@material-ui/core";
import DeleteOutlined from "@material-ui/icons/DeleteOutlined";


class Todo extends React.Component{
    /*백엔드에서 받아온 임의의 Todo 리스트를 출력할 수 있어야함.*/
    /*현 Todo 컴포넌트에 title을 매개변수로 넘겨줘야 함. title과 더불어 필요한 것들을 생성자를 통해 넘겨야한다.*/
    constructor(props){
        super(props);
        this.state={
            item: props.item

        };
        //this.handleClick = this.handleClick().bind(this);
    }



    render(){
        const item = this.state.item;
        return (        //{ }로 감싼것은 자바스크립트로 된 변수를 JSX에서 사용하기 위함.
            <ListItem>
                <Checkbox checked={item.done} disableRipple/>
                <ListItemText>

                        <InputBase
                            inputProps={{"aria-label": "Always visible"}}
                            type={"text"}
                            id={item.id}
                            name={item.id}
                            value={item.title}
                            multiline={true}
                            fullWidth={true}
                            />
                </ListItemText>

                <ListItemSecondaryAction>
                    <IconButton aria-label={"삭제할 Todo"}>
                        <DeleteOutlined />
                    </IconButton>
                </ListItemSecondaryAction>
                </ListItem>
            );//aria-label은 단지 설명을 위한 텍스트일뿐 !!
    }
}

export default Todo;