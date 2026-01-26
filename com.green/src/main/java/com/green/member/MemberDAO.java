package com.green.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	// MySQL Driver 설치 및 JDBC 환경설정 완료
	//외부에서 DataSource 를 DI 삽입한다.
	
	@Autowired
	private DataSource datasource;

	
	
	public int insertMember(MemberDTO mdto) {
		System.out.println("memberDAO insertMember()메소드 확인");
		
		// 실무에서 쿼리문 작성시 대문자로 작성함
		//NO<REG_DATE,MOD_DATE default 값이 존재하므로 추가하지 않아도 됨
		// 추가할 필드명이 정해져 있을경우 반드시 (1필드명,2필드명,...)필드명을 명시한다.
		// INSERT INTO USER_MEMBER(id,pw,mail,phone) VALUES(?,?,?,?)
		String sql = "INSERT INTO user_member(id,pw,mail,phone) VALUES(?,?,?,?)";
		int result =0;
		
		//DB는 네트워크를 통해 자료를 가져오므로 try ~ catch() 구문 이용한다.
		
		try(
				// connection 클래스를 이용해 dataSource를 getConnection()해야한다.
				// Connection은 연결하는 자원으로 사용하고 나면 반드시 반납을 해야 함, close()를 해야함
				// try(Connetction) => 자동 close()가됨
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			
			//?,?,?,?
			//input => 입력 => mdto에 가방에 담긴상태
			//mdto라는 가방에서 필요한 자원을 getId()꺼내온다
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPw());
			pstmt.setString(3, mdto.getMail());
			pstmt.setString(4, mdto.getPhone());
			
			//insert, delete, update구문은 실행명령 : executUpdate()이다.
			result = pstmt.executeUpdate();
			// executeUpdate => insert, delete, update구문은 실행하고 실행 결과를 int데이터 타입의 행의 개수로 반환한다는 의미
			// insert 1건 성공 => 반환값 :1
			// insert 0건 중복체크, => 반환값 :0
			// update 3건 수정 => 반환값 :3
			// delete 5건 수정 => 반환값 :5
			
			System.out.println("MemberController insertMember() result값: "+result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	// 회원가입한 유저 모두 출력하는 메소드 작성
	public List<MemberDTO> selectMemberAll() {
		System.out.println("memberDAO selectMemberAll()메소드 확인");
		String sql="SELECT * FROM user_member";
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt =conn.prepareStatement(sql);
				// select 구문은 executeQuery() 실행한 결과를 rs에 담는다
				ResultSet rs = pstmt.executeQuery();
				
				){
			//rs.next() 다음행의 값이 존재하면 true, 아니면 false를 반환한다.
			while(rs.next()) {
				MemberDTO mdto = new MemberDTO();
				mdto.setNo(rs.getInt("no"));
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setMail(rs.getString("mail"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setReg_date(rs.getString("reg_date"));
				mdto.setMod_date(rs.getString("mod_date"));
				
				// Arraylist에 추가한다.
				list.add(mdto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public boolean isMember(String id) {
		System.out.println("memberDAO isMember()메소드 확인");
		return false;
	}
}
