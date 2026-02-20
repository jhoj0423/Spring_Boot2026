import { useState, useContext } from 'react';
import { AuthContext } from '../contexts/AuthContext';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Member.css';

export default function Login() {
 
    const {login} = useContext(AuthContext);

    const [id,setId] = useState('');
    const [pw,setPw] = useState('');

    // 이동할때 사용하는 커스텀 훅
    const navigator = useNavigate();

    const loginHandler=()=>{

        if(id === ''){
            alert("아이디를 입력하세요")
        }
        if(pw === ''){
            alert("비밀번호를 입력하세요")
        }

        axios.post('/api/member/login',{id:id,pw:pw}) // 
        .then((res)=>{
            console.log("받아온 데이터 : ",res.data)
            if(res.data){
                alert(`${res.data.id}님 환영합니다.`);
                navigator("/");
                login(res.data.id);
            }else{
                //
                alert("아이디 또는 비밀번호를 다시 확인해주세요");
            }
        })
        .catch((error)=>{
            console.log("데이터 로딩 에러",error)
        })
        
    }


  return (
    <div id="section_wrap">
      <div className="word">로그인</div>

      <table width="500" border="1">
        <tbody>
          <tr>
            <td>아이디</td>
            <td>
              <input type="text" name='id'  onChange={(e)=>setId(e.target.value)}/>
            </td>
          </tr>

          <tr>
            <td>비밀번호</td>
            <td>
              <input type="password" name='pw' onChange={(e)=>setPw(e.target.value)} />
            </td>
          </tr>

          <tr>
            <td colSpan="2" align="center">
              <button onClick={loginHandler}>로그인</button>
              <button type="reset">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}