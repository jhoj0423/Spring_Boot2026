package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDao;
	
	//하나의 게시글이 추가되는 메소드를 boardDAO에서 접근하여 사용
	public void addBoard(BoardDTO bdto) {
		System.out.println("addBoard메소드 확인용");
		boardDao.insertBoard(bdto);
	}
	
	public List<BoardDTO> allboard(){
		System.out.println("allboard메소드 확인용");
		return boardDao.getAllboard();
	}
	public BoardDTO oneboard(int num){
		System.out.println("allboard메소드 확인용");
		return boardDao.getOneBoard(num);
	}
	
	//하나의 게시글을 수정하는 메소드
	public boolean modify(BoardDTO bdto) {
		System.out.println("modify() 메소드 확인용");
		int result =  boardDao.updateBoard(bdto);
		
		if(result>0) {
			System.out.println("게시글 수정 완료");
			return true;
		}else {
			System.out.println("게시글 수정 실패");
			return false;
			
		}
	}
	
}
