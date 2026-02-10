package replyBoard.service;

import java.util.List;

import replyBoard.dto.ReplyBoardDTO;

public interface ReplyBoardService {
	// 게시글 잣성하여 추가하는 메소드
	public void insertReplyBoard(ReplyBoardDTO rdto);
	
	// 게시글 전체 목록 섬색 메소드
	public List<ReplyBoardDTO> getAllReplyBoard();
	
	//하나의 게시글을 리턴 받는 메소드
	public ReplyBoardDTO getOneBoard(int num);
	
	// 답글 작성하여 추가하는 메소드
	public void reWriteInsert(ReplyBoardDTO rdto);
	
	// 답글 작성시 부모글의 re_level 보다 큰 값들을 모두 1씩 증가시키는 메소드
	// ref : 1, re_step :1, re_level : 1 => 원글
	// 원글의 답글을 달 경우 => ref:1, re_step:2,re_level:2
	public void reSqUpdate(ReplyBoardDTO rdto);
	
	// 답글 추가시 reSqUpdate() 메소드가 먼저 실행되도록 묶음으로 만든 메소드
	// reWriteInsert + reSqUpdate 합쳐서 실행하는 메소드
	// 이유는 답글은 추가되기 이전에 기조의ref, re_step,re_level의 값이 변경
	// 되는 부분이 필요하므로 반드시 reSqUpdate()먼저, reWriteInsert()실행한다.
	public void replyProcess(ReplyBoardDTO rdto);
}
