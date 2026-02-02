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
}
