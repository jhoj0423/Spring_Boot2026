/* 회원가입 유효성 검사 규칙 */

function signupForm(){
	console.log("회원가입 폼")
	/* DOM으로 form을 연결*/
	
	let form = document.signup_form;
	if(form.id.value === ''){
		alert("새로운 id 입력");
		/* 커서를 id로 지정*/
		form.id.focus();
	}else if(form.pw.value === ''){
		alert("새로운 pw 입력");
		form.pw.focus();
	}else if(form.pwchk.value === ''){
		alert("새로운 pwchk 입력");
		form.pwchk.focus();
	}else if(form.mail.value === ''){
		alert("새로운 email 입력");
		form.mail.focus();
	}else if(form.phone.value === ''){
		alert("새로운 전화번호 입력");
		form.phone.focus();
	}else{
		form.submit();
	}
}

// 회원이 로그인 상태면 글쓰기 가능, 아니면 '로그인 후 사용 가능 메시지 출력'

let write = document.getElementById("writeBtn");
write.addEventListener("click",function(){
	const isLogin = this.dataset.login;
	
	if(isLogin === "true"){
		/* 로그인 된상태 => 글쓰기 => /board/write */
		location.href = "/board/write";
	}else{
		/* 로그인 안된상태 => 글쓰기 안됨 => alert창띄우기 */
		/* 로그인 폼으로 이동. => /member/login */
		alert("로그인 후 사용가능");
		location.href="/member/login";
	}
})