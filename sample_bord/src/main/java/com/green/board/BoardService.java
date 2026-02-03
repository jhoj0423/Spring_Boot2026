package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.board.mapper.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
	//BoardDAO boardDao;
	BoardMapper boardMapper;
	
	
	// 게시글 추가 메서드
	public int writeConfirm(BoardDTO bdto) {
		System.out.println("writeConfirm() 메서드 확인용");
		return boardMapper.insertBoard(bdto);
	}

	// 회원 전체 목록 출력 메소드
	public List<BoardDTO> selectBoardAll() {
		System.out.println("selectBoardAll() 메소드 확인용");
		return boardMapper.selectBoardsAll();
	}

	public BoardDTO oneSelect(int id) {
		System.out.println("oneSelect() 메소드 확인용");
		return boardMapper.oneSelectBoard(id);
	}

	public boolean Rewriteboard(BoardDTO bdto) {
		System.out.println("Rewriteboard() 메소드 확인용");
		System.out.println("Rewriteboard(^^^##) 메소드에서 boardDao.updateBoard(bdto)"+boardMapper.updateBoard(bdto));
		
		return boardMapper.updateBoard(bdto)==1;
	}
	
	// ========== 2026-01-29 수정부분 =================
	
	public boolean removeBorad(int id) {
		System.out.println("Rewriteboard( • ᴗ - )메소드 확인용");
		
		int result = boardMapper.deleteBorad(id);
		
		if(result>0) {
			// 삭제 성공
			return true;
		}else {
			// 삭제 실패
			return false;
		}
		
	}
	
	public int getAllcount() {
		System.out.println("getAllcount( * ᴗ * )메소드 확인용");
		return boardMapper.getAllcount();
		
	}
	
	public List<BoardDTO> getPageList(int StartRow,int pageSize){
		System.out.println("getPageList( * ᴗ * )메소드 확인용");
		
		return boardMapper.getPagelist(StartRow,pageSize);
	
	}
	
}
