package com.green.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.board.BoardDTO;

@Mapper
public interface BoardMapper {
	
	//게시판 추가 
	public int insertBoard(BoardDTO bdto);
	
	//모든 게시판 선택
	public List<BoardDTO> selectBoardsAll();
	
	//한개 게시판 선택
	public BoardDTO oneSelectBoard(int id);
	
	//게시판 수정
	public int updateBoard(BoardDTO bdto);
	
	//게시판 삭제
	public int deleteBorad(int id);
	
	// 전체 게시글의 개수를 구하는 매소드
	public int getAllcount();
	
	// 전체 게시글의 시작(startRow), 몇개의 행 (pageSize)만큼 보는 메소드
	public List<BoardDTO> getPagelist(@Param("startRow")int startRow,@Param("pageSize")int pageSize);
}
