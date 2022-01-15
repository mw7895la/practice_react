import React from 'react';
import {ListItem, ListItemText, InputBase, Checkbox,ListItemSecondaryAction,IconButton} from "@material-ui/core";
import DeleteOutlined from "@material-ui/icons/DeleteOutlined";


class Todo extends React.Component{
    /*백엔드에서 받아온 임의의 Todo 리스트를 출력할 수 있어야함.*/
    /*현 Todo 컴포넌트에 title을 매개변수로 넘겨줘야 함. title과 더불어 필요한 것들을 생성자를 통해 넘겨야한다.*/
    constructor(props){
        super(props);
        this.state={ item: props.item, readOnly:true };
        this.delete = props.delete;
        //this.handleClick = this.handleClick().bind(this);
        //Add readonly state variable
        this.update=props.update;

    }


    deleteEventHandler=()=>{
        this.delete(this.state.item);
    }

    offReadOnlyMode =() => {
        console.log(" Event ! ",this.state.readOnly);
        this.setState({readOnly: false}, () =>{
            console.log("ReadOnly ? ",this.state.readOnly);
        });
    }

    //enter 키를 누르면 readonly가 true가 되면서 깜빡임 없앰.
    enterKeyEventHandler=(e)=>{
        if(e.key ==='Enter'){
            this.setState({readOnly: true});
            this.update(this.state.item);
        }
    }

    editEventHandler=(e) => {
        //한개씩 수정하는거니까.
        const thisItem = this.state.item;
        thisItem.title=e.target.value;      //InputBase에서 value가 item.title로 되어있음.
        this.setState({item: thisItem});
        this.update(this.state.item);
    }

    checkboxEventHandler=(e)=>{
        //이것도 마찬가지 한개씩 수정하는 것.
        const thisItem = this.state.item;
        thisItem.done=!thisItem.done;
        this.setState({item: thisItem});
    }

    render(){
        const item = this.state.item;
        return (        //{ }로 감싼것은 자바스크립트로 된 변수를 JSX에서 사용하기 위함.
            <ListItem>
                <Checkbox checked={item.done} onChange={this.checkboxEventHandler} />
                <ListItemText>

                        <InputBase//onClick시 readOnly가 false로 바뀌면 InputBase가 수정할 수 있는 상태로 변경해준다.
                            inputProps={{"aria-label": "Always visible", readOnly: this.state.readOnly,}}
                            onClick={this.offReadOnlyMode}
                            onKeyPress={this.enterKeyEventHandler}
                            onChange={this.editEventHandler}
                            type={"text"}
                            id={item.id}
                            name={item.id}
                            value={item.title}
                            multiline={true}
                            fullWidth={true}
                            />
                </ListItemText>

                <ListItemSecondaryAction>
                    <IconButton
                        aria-label={"삭제할 Todo"}
                        onClick={this.deleteEventHandler}>
                        <DeleteOutlined />
                    </IconButton>
                </ListItemSecondaryAction>
                </ListItem>
            );//aria-label은 단지 설명을 위한 텍스트일뿐 !!
    }
}

export default Todo;