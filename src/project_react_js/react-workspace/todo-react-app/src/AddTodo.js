import React from "react";
import {TextField, Paper, Button, Grid} from "@material-ui/core";

class AddTodo extends React.Component{
    constructor(props) {
        super(props);
        this.state={item: {title:""}};      //사용자의 입력을 저장할 오브젝트
        this.add=props.add;
    }

    //추가할 Item을 입력하는 onInputChange 함수 작성
    onInputChange = (e) => {
        const thisItem = this.state.item;
        thisItem.title= e.target.value;     //e.target은 이벤트가 발생 했을 때 즉, 여기서는 title 추가할 것을 입력한 행위
        //e.target.value는 입력된 title이다.
        this.setState({item: thisItem});
        console.log(thisItem);
    }

    //App.js에서 add()함수를 props로 넘겨받아 onButtonClick에 사용.
    onButtonClick=() => {
        this.add(this.state.item);  //add 함수 사용
        this.setState({item: {title:""}});
    }

    //렌더링의 onKeyPress의 경우 키보드키를 누르자 마자 여기가 수행되는데 엔터일 경우만 +버튼 클릭과 같은 효과.
    enterKeyEventHandler =(e) =>{
        if(e.key==='Enter'){
            this.onButtonClick();
        }else{
        console.log("Do You Prees Enter Key ?");
    }
}

    render(){
        return(
            <Paper style={{margin:16,padding:16}}>
                <Grid container>
                    <Grid xs={10} md={10} item style={{paddingRight:16}}>
                        <TextField placeholder={"추가할 아이템을 등록하세요"}
                                   fullWidth
                                   onChange={this.onInputChange}  //onChange는 이벤트가 발생할 때 마다 실행된다 즉,입력할때마다.
                                   value={this.state.item.title}
                                   onKeyPress={this.enterKeyEventHandler}
                        />
                    </Grid>
                    <Grid xs={2} md={2} item>
                        <Button fullWidth color={"primary"}
                                variant={"outlined"}
                                onClick={this.onButtonClick}
                        >
                            (+)
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        );
    }
}

export default AddTodo;