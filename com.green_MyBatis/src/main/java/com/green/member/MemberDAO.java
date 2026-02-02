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
	
	
	// ============== 2026년 1월 27일 추가 쿼리 작성 부분 ==================
	// 개인 한 사람의 정보를 검색하는 메소드
	public MemberDTO oneSelectMember(String id) {
		// log찍기 
		System.out.println("memberDAO oneSelectMember()메소드 확인");
		// 반환받은 MemberDTO 객체를 생성한다.
		MemberDTO mdto = new MemberDTO();
		//sql구문을 작성
		String  sql = "SELECT * FROM user_member WHERE id=?";
		//예외 처리하는 트라이(자동 close를 위해 Connection설정)~ 캐치
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			//실행 구문 , ?대응을 먼저 작성한다.
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			// 한사람의 정보를 물을때도 if문으로 반드시 물어봐야한다.
			// rs.next() 없이 값을 꺼내오면 -> 항상 DTO임을 주의하자!
			if(rs.next()) {
				mdto.setNo(rs.getInt("no"));
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setMail(rs.getString("mail"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setReg_date(rs.getString("reg_date"));
				mdto.setMod_date(rs.getString("mod_date"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mdto;
	}
	
	
	// 개인 한사람의 정보를 수정하는 쿼리 
	// 단 정보수정 할때 password 일치하는지확인
	
	public int updateMember(MemberDTO mdto) {
		System.out.println("memberDAO updateMember()메소드 확인");
		int result = 0;
		String sql ="UPDATE user_member SET mail=? , phone =? WHERE id =?";
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, mdto.getMail());
			pstmt.setString(2, mdto.getPhone());
			pstmt.setString(3, mdto.getId());
			result = pstmt.executeUpdate();
			System.out.println("update result="+result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// 개인 한사람의 패스워드 리턴
	public String getPass(String id) {
		System.out.println("memberDAO getPass()메소드 확인");
		String pass="";
		String sql="SELECT pw FROM user_member WHERE id=?";
		try(
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pass=rs.getString(1); // 패스워드 값에 저장된 mapping 인덱스
			}
			System.out.println("get pass result = "+pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pass;
	}
	
	
	
	// 한사람 개인의 정보를 삭제하는 메소드 작성
	public int deleteMember(String id) {
		int result = 0;
		String sql ="DELETE FROM user_member WHERE id=?";
		
		try (
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, id);
			//쿼리문 실행 할때 executeUpdate -> 딜리트,인섵,업데이트
			//select문 실행 할때는 executeQuery() 사용한다.
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
//	public MemberDTO setOneSelectMember(String id) {
//		System.out.println("memberDAO setOneSelectMember()메소드 확인");
//		MemberDTO mdto = new MemberDTO();
//		String sql ="UPDATE user_member SET mail=? , phone =? WHERE id =?";
//		try(
//				Connection conn = datasource.getConnection();
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				){
//			pstmt.setString(1, );
//			pstmt.setString(2, );
//			pstmt.setString(3, id);
//			ResultSet rs = pstmt.executeQuery();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return mdto;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
