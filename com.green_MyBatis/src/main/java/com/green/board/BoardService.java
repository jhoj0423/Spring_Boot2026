package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.mapper.BoardMapper;

@Service
public class BoardService {
	
//	@Autowired
//	BoardDAO boardDao;
	
	@Autowired
	BoardMapper boardMapper;
	
	//하나의 게시글이 추가되는 메소드를 boardDAO에서 접근하여 사용
	public void addBoard(BoardDTO bdto) {
		System.out.println("addBoard메소드 확인용");
		boardMapper.insertBoard(bdto);
	}
	
	public List<BoardDTO> allboard(){
		System.out.println("allboard메소드 확인용");
		return boardMapper.getAllboard();
	}
	
	public BoardDTO oneboard(int num){
		System.out.println("allboard메소드 확인용");
		boardMapper.updateReadBoard(num);
		return boardMapper.getOneBoard(num);
	}
	
	//하나의 게시글을 수정하는 메소드
	public boolean modify(BoardDTO bdto) {
		System.out.println("modify() 메소드 확인용");
		int result =  boardMapper.updateBoard(bdto);
		
		if(result>0) {
			System.out.println("게시글 수정 완료");
			return true;
		}else {
			System.out.println("게시글 수정 실패");
			return false;
			
		}
	}
	
	// =============== 2026-01-29 수정부분 =======================
	public boolean removeBoard(int num, String writerPw) {
		System.out.println("removeBoard(**^^^) 메소드 확인용");
		// DAO에서 받아오는 deleteBoard()는 삭제 = 1 , 아니면 0
		int result = boardMapper.deleteBoard(num, writerPw);
		
		if(result>0) {
			System.out.println("게시글 삭제 성공");
			return true;
		}else {
			System.out.println("게시글 삭제 실패");
			return false;
		}
		
	}
	
	//검색하기위한 메소드
	public List<BoardDTO> SearchBoard(String searchType, String searchKeyword) {
		System.out.println("SearchBoard(ㅠ^ㅠ) 메소드 확인용");
		System.out.println("SearchBoard(ㅠ^ㅠ)searchType 확인용"+searchType);
		System.out.println("SearchBoard(ㅠ^ㅠ)searchKeyword 확인용"+searchKeyword);
		
		return  boardMapper.getSearchBoard(searchType, searchKeyword);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
