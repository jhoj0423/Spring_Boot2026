package com.green.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	
	
	@Autowired
	private DataSource datasource;
	
	//쿼리문 작성시 keypoint
	// 1. 한사람, 하나에 관련된 자료를 insert, select 할때는 
	//   => DTO객체에 담아 사용한다. 고로 데이터 타입은 DTO
	// 2. 전체목록, 전체회원... 여러개의 해당하는 자료는 
	//   => List<E> list = new ArrayList<E> 업캐스팅
	// 3. 필드명하나 select 할때 => 데이터타입 : String, int, boolean등을 사용한다.
	// 4. 메소드 작성시 void는 return사용안함
	// 5. 메소드 작성시 데이터 타입이 존재하면 반환값 retrun 필요함
	// 6. try catch 사용

	
	// 하나의 게시글 자성하여 추가하는 쿼리문
	public void insertBoard(BoardDTO bdto) {
		System.out.println("2+insertBoard() 호출");
		String sql = "INSERT INTO board(writer,subject,writerPw,content) values(?,?,?,?)";
		
		
		try (	
				//datasource 주입한 데이터 베이스 원본의 자료들을 연결하세요
				Connection conn = datasource.getConnection();
				//conn => url,username,userPassword
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//
				){
			// 제일 먼저 할일 => ? 대응
			pstmt.setString(1, bdto.getWriter());
			pstmt.setString(2, bdto.getSubject());
			pstmt.setString(3, bdto.getWriterPw());
			pstmt.setString(4, bdto.getContent());
			// select문은 반드시 ResultSet 캑체에 담아야 한다.
			pstmt.executeUpdate();
			System.out.println(pstmt+"pstmt 확인용");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 전체 게시글 목록을 출력하는 쿼리문
	public List<BoardDTO> getAllboard(){
		// List<> 인스턴스롸 한다.
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		//sql
		String sql = "SELECT * FROM board ORDER BY num DESC";
		
		try (
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			// select문은 ResultSet에 담는다
			ResultSet rs = pstmt.executeQuery();
			//rs.next() 다음행이 존재하면 true 아니면 false
			// rs.next() 무조건 빈 DTO생성됨을 주의하자
			while(rs.next()) {
				
				BoardDTO bdto = new BoardDTO();
				bdto.setNum(rs.getInt("num"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setWriterPw(rs.getString("writerPw"));
				bdto.setReg_date(rs.getString("reg_date"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setContent(rs.getString("content"));
				boardlist.add(bdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardlist;
	}
	
	

	// 하나의 개시글 상세 정보 보기
	// 여기는 readcount 누적하여 조회수를 증가하는 메소드도 함께 작성한다.
	public BoardDTO getOneBoard(int num) {
		BoardDTO bdto = new BoardDTO();
		//readcount 클릭할때 마다 1씩 누적하는 sql문 작성
		String sql = "UPDATE board SET readcount= readcount+1 WHERE num=?" ;
		String sql2 = "SELECT * FROM board WHERE num=?";
		try (   // 조회수 증가 sql try~catch()구문 하나의 게시글 정보 가져오는 sql2 try~catch()구문
				Connection conn = datasource.getConnection();
				){
			
			try (   
					PreparedStatement pstmt = conn.prepareStatement(sql);
					){
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
			} 
			try (   
					PreparedStatement pstmt = conn.prepareStatement(sql2);
					){
				pstmt.setInt(1, num);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()){
					bdto.setNum(rs.getInt("num"));
					bdto.setWriter(rs.getString("writer"));
					bdto.setSubject(rs.getString("subject"));
					bdto.setWriterPw(rs.getString("writerPw"));
					bdto.setReg_date(rs.getString("reg_date"));
					bdto.setReadcount(rs.getInt("readcount"));
					bdto.setContent(rs.getString("content"));
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bdto;
	}
	
	//하나의 게시글을 수정하는 메소드
	public int updateBoard(BoardDTO bdto) {
		System.out.println("updateBoard() 메소드 확인용");
		
		int result = 0; // 수정되면 1, 아니면 0
		// 수정할때 반드시, 번호와 비밀번호가 일ㅊ치해야만 수정하는 쿼리
		String sql ="UPDATE board SET subject=?, content=? WHERE num=? AND writerPw=?";
		
		try (	
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setString(1, bdto.getSubject());
			pstmt.setString(2, bdto.getContent());
			pstmt.setInt(3, bdto.getNum());
			pstmt.setString(4, bdto.getWriterPw());
			result = pstmt.executeUpdate();
			System.out.println(pstmt+"pstmt(!!!!!!!!!) 확인용");
			System.out.println(result+"result(!!!) 확인용");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
