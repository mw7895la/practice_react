import React from 'react';
import Todo from './Todo';
import './App.css';

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

    render() {
    /*return (
        <div className={"App"}>
            <Todo item={this.state.item}/>
           </div>
    );*/
        var todoItems = this.state.items.map((item, idx) => (
            <Todo item={item} key={item.id}/>
        ));
        return <div className={"App"}>{todoItems}</div>;
  }

}

export default App;
