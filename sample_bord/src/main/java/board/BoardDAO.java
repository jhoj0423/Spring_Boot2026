package board;

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
	
	// 외부에서 DataSource 를 DI한다
	@Autowired
	private DataSource datasource;
	
	public int insertBoard(BoardDTO bdto) {
		System.out.println("insertBoard() 메서드 확인용");
		int result = 0;
		// 성공하면 result =1 , 실패하면 0
		String sql="INSERT INTO board(title,content,writer) VALUES(?,?,?)";
		
		
		try (
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
				){
			pstmt.setString(1, bdto.getTitle());
			pstmt.setString(2, bdto.getContent());
			pstmt.setString(3, bdto.getWriter());
			
			result = pstmt.executeUpdate();
			System.out.println("BoardController insertBoard() result값: "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<BoardDTO> selectBoardsAll() {
		System.out.println("selectBoardsAll() 메소드 확인용");
		String sql = "SELECT * FROM board";
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try (
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				){
			while(rs.next()) {
				BoardDTO bdto = new BoardDTO();
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(rs.getString("createdAt"));
				
				list.add(bdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("list DAO에서 확인"+list);
		return list;
	}

	public BoardDTO oneSelectBoard(int id) {
		System.out.println("oneSelectBoard() 메소드 확인용");
		BoardDTO bdto = new BoardDTO();
		String sql = "SELECT * FROM board WHERE id = ?";
		
		try (
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bdto.setId(rs.getInt("id"));
				bdto.setTitle(rs.getString("title"));
				bdto.setContent(rs.getString("content"));
				bdto.setWriter(rs.getString("writer"));
				bdto.setCreatedAt(rs.getString("createdAt"));
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bdto;
	}

	public int updateBoard(BoardDTO bdto) {
		System.out.println("updateBoard() 메소드 확인용");
		int result =0;
		String sql = "UPDATE board SET title=? , writer=? , content=? WHERE id =?";
		try (
				Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				){
			System.out.println(bdto.getTitle() + "update id확인용");
			System.out.println(bdto.getWriter() + "update writer확인용");
			System.out.println(bdto.getContent() + "update content확인용");
			pstmt.setString(1, bdto.getTitle());
			pstmt.setString(2, bdto.getWriter());
			pstmt.setString(3, bdto.getContent());
			pstmt.setInt(4, bdto.getId());
			
			result = pstmt.executeUpdate();
			System.out.println("update result"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
