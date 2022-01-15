import React from 'react';
import Todo from './Todo';
import AddTodo from './AddTodo';
import './App.css';
import {Paper, List, Container} from "@material-ui/core";

class App extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            //item: {id: 0, title:"Hello Woojin 1",done :true},
            items: [
                {id: "0", title: "Hello woojin 1",done: true},
                {id: "1", title: "Hello woojin 2",done: false},
            ],
        };
    }
    //(+) 버튼을 클릭시 리스트에 추가되는 Add 함수
    add = (item) =>{
        const thisItems = this.state.items;
        item.id = "ID ---" +thisItems.length; //Key를 위한 id 추가
        item.done=false;        //done 초기화
        thisItems.push(item);       //리스트에 아이템 추가
        this.setState({items:thisItems});       //업데이트는 반드시 this.setState로 해야함.
        console.log("add의 items :",this.state.items);
    }


    //Rendering 부분
    render() {
        var todoItems = this.state.items.length > 0 && (
            <Paper style={{margin: 16 } } >
                <List>
                    {this.state.items.map((item, idx)=>(
                        <Todo item={item} key={item.id}/>
                    ))}
                </List>

            </Paper>
        );

        return (
            <div className={"App"}>
                <Container maxWidth={"md"}>
                    <AddTodo add={this.add}/>
                    <div className={"TodoList"}>{todoItems}</div>
                </Container>
            </div>

    );
    }

}

export default App;
