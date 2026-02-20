import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../contexts/AuthContext';
import { useContext } from 'react';
import './Member.css'

export default function MyInfo() {
  const navigate = useNavigate();
  // 회원탈퇴시 -> 로그인 상태에서 벗어나야 하므로
  const {user,logout} = useContext(AuthContext);

  // 내정보 조회하는 컨포넌트

  const [member,setMember] = useState(null);

  useEffect(()=>{
    axios.get('/api/member/myinfo')
    .then((res)=>{
      console.log("받아온 데이터" , res.data)
      if(!res.data){
        // 현재 내정보 데이터가 없다
        alert("로그인이 필요합니다")
        navigate("/member/login")
      }else{
        setMember(res.data)
      }
    })
    .catch((error)=>{
      console.log("데이터 로딩 에러",error)
    })
  },[])

  // 회원삭제 핸들러
  const handleDelete=()=>{
    if(!window.confirm("정말 삭제하겠습니까?")){
      return;
    }
    axios.delete('/api/member/delete')
    .then((res)=>{
      if(res.data === 1){
        alert("회원이 탈퇴 되었습니다");
        logout();
        navigate("/");
      }else{
        alert("삭제실패")
      }
    })
    .catch((error)=>console.log(error))
  }

  if(!member){
    return<div>로딩중...</div>
  }

  return (
    <section>
      <div id="section_wrap">
        <div className="word">
          <h2>개인 회원 상세 정보</h2>
        </div>

        <div className="content">
          <table border="1">
            <tbody>
              <tr>
                <th>아이디</th>
                <td>{member.id}</td>
              </tr>
              <tr>
                <th>이메일</th>
                <td>{member.mail}</td>
              </tr>
              <tr>
                <th>전화</th>
                <td>{member.phone}</td>
              </tr>
              <tr>
                <th>등록일</th>
                <td>{member.reg_date}</td>
              </tr>
            </tbody>
          </table>

          {/* 버튼 영역 */}
          <div className="btn-area" style={{ marginTop: '20px' }}>
            <button className="btn" onClick={() => navigate('/member/modify')}>
              회원수정
            </button>

            <button className="btn btn-danger" onClick={handleDelete}>
              회원삭제
            </button>

            <button className="btn" onClick={() => navigate('/')}>
              홈으로
            </button>

          </div>
        </div>
      </div>
    </section>
  );
}