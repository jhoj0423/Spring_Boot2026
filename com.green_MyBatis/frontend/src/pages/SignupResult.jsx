import { useNavigate, useSearchParams } from "react-router-dom";

export default function SignupResult(){
    // 현재 URL ~~?result=값, 값을 읽기 위해 사용하는 훅
    // 예 : '/member/signup_result?result=success'
    // 위 주소에서 result=success 부분을 꺼내사용하는 도구

    const [searchParams] = useSearchParams();
    const navigate= useNavigate();
    // "success" 문자열만 꺼내서 result 변수에 담는다
    const result = searchParams.get("result");

    return(
        <div style={{textAlign:'center'}}>
            {result==='success' && <h2>회원가입성공</h2>}
            {result==='duplicate' && <h2>이미존재하는 아이디입니다</h2>}
            {result==='fail' && <h2>회원가입실패</h2>}
            {result==='error' && <h2>서버오류</h2>}

            <button onClick={() => navigate('/memmber/login')} className="Btn">
                로그인 페이지로 이동
            </button>
        </div>
    )
}