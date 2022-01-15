import React from 'react';
import Todo from './Todo';
import AddTodo from './AddTodo';
import { call} from './service/ApiService';
import './App.css';
import {Paper, List, Container} from "@material-ui/core";

class App extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            //item: {id: 0, title:"Hello Woojin 1",done :true},
            items: [ ],
        };
    }
    //DOM 트리를 구성하는 과정을 Mount, 마운팅 과정에서 constructor와 render를 부르고 마운팅을 마친 후에,
    //부르는 함수가 하나 더 있는데, componentDidMount이다. 이 함수에서 백엔드 API콜 부분을 구현하게 된다.
    componentDidMount(){
        call("/todo","GET",null).then((response)=>
            this.setState({items : response.data})
        );
    }


    //(+) 버튼을 클릭시 리스트에 추가되는 Add 함수
    add = (item) =>{
        call("/todo","POST",item).then((response)=>
            this.setState({items: response.data})
        );
    }

    //Update함수
    update = (item) => {
        call("/todo","PUT",item).then((response)=>
        this.setState({items: response.data}));
    }

    //Delete 함수
    delete = (item) =>{
        /*const thisItems = this.state.items;
        console.log("업데이트 이전 아이템들 :",this.state.items);
        const newItems=thisItems.filter(e => e.id !==item.id);
        this.setState({items:newItems}, ()=>{
            console.log("업데이트 된 아이템들: ",this.state.items);
        });*/
        call("/todo","DELETE",item).then((response)=>
        this.setState({items: response.data}));
    }


    //Rendering 부분
    render() {
        var todoItems = this.state.items.length > 0 && (
            <Paper style={{margin: 16 } } >
                <List>
                    {this.state.items.map((item, idx)=>(
                        <Todo item={item} key={item.id} delete={this.delete} update={this.update}/>
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
