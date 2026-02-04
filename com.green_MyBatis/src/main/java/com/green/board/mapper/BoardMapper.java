package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.board.BoardDTO;

@Mapper
public interface BoardMapper {
	public void insertBoard(BoardDTO bdto);
	
	public List<BoardDTO> getAllboard();
	
	public BoardDTO getOneBoard(int num);
	
	public void updateReadBoard(int num);
	
	public int updateBoard(BoardDTO bdto);
	
	//매개변수가 2개이상인 경우 @Param("변수") 데이터타입 필드명 이용해 작성한다.
	public int deleteBoard(@Param("num")int num, @Param("writerPw")String writerPw);
	
	public List<BoardDTO> getSearchBoard(@Param("searchType") String searchType,
			@Param("searchKeyword") String searchKeyword);
	
	// 전체 게시글의 개수를 구하는 매소드
	public int getAllcount();
	
	// 전체 게시글의 시작(startRow), 몇개의 행 (pageSize)만큼 보는 메소드
	public List<BoardDTO> getPagelist(@Param("startRow")int startRow,@Param("pageSize")int pageSize);
	
	//검색페이징에 필요한 메서드
	//searchType, searchKeyword에 해당하는 검색된 개수를 반환하는 메소드
	public int getSearchCount(@Param("searchType") String searchType,
			@Param("searchKeyword") String searchKeyword);
	
	// searchType, searchKeyword, startRow, pageSize
	// => limit startRow부터, pageSize개 만큼 한 화면에 보여질 행의 개수
	public List<BoardDTO> getSearchPageList(
			@Param("searchType") String searchType,
			@Param("searchKeyword") String searchKeyword,
			@Param("startRow") int startRow,
			@Param("pageSize") int pageSize
			);
	
	// ---- 로그인이 된 상태의 나만의 게시글을 mypage.html에 출력
	public List<BoardDTO> getMyBoardList(@Param("loginId") String loginId,
			@Param("startRow") int startRow,
			@Param("pageSize") int pageSize);
	
	
	// 로그인된 나만의 게시글의 갯수
	public int getMyBoardCount(String loginId);
	
}
