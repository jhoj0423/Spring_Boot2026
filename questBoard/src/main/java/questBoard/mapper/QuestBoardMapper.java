package questBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import questBoard.dto.QuestBoardDTO;

@Mapper
public interface QuestBoardMapper {
	
	// 문의 추가
	public void insertQuestBoard(QuestBoardDTO qdto);
	
	// 문의 리스트 출력
	public List<QuestBoardDTO> getAllQuestBoard();
	
	// 한가지 문의 리스트 
	public QuestBoardDTO getOneQuestBoard(int num);
	
	// 답글 추가
	public void reWriteQuestBoard(QuestBoardDTO qdto);
	
	// 조회수 증가
	public void readCountUpdate(QuestBoardDTO qdto);
	
	// 문의글 수정
	public void updateOneBoard(QuestBoardDTO qdto);
	
	// 전체 게시글의 개수를 구하는 매소드
	public int getAllcount();
	
	// 전체 게시글의 시작(startRow), 몇개의 행 (pageSize)만큼 보는 메소드
	public List<QuestBoardDTO> getPagelist(@Param("startRow")int startRow,@Param("pageSize")int pageSize);
}
