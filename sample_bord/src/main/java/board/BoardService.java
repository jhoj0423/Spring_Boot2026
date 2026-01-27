package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDao;
	
	
	// 게시글 추가 메서드
	public int writeConfirm(BoardDTO bdto) {
		System.out.println("writeConfirm() 메서드 확인용");
		return boardDao.insertBoard(bdto);
	}

	// 회원 전체 목록 출력 메소드
	public List<BoardDTO> selectBoardAll() {
		System.out.println("selectBoardAll() 메소드 확인용");
		return boardDao.selectBoardsAll();
	}
}
