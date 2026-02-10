package questBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import questBoard.dto.QuestBoardDTO;
import questBoard.mapper.QuestBoardMapper;

@Service
public class QuestBoardServiceImpl implements QuestBoardService{
	@Autowired
	QuestBoardMapper questboardMapper;

	@Override
	public void insertQuestBoard(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServiceImpl insertQuestBoard(*@*) 메서드 확인");
		questboardMapper.insertQuestBoard(qdto);
	}

	@Override
	public List<QuestBoardDTO> getAllQuestBoard() {
		System.out.println("QuestBoardServiceImpl getAllQuestBoard(*@*) 메서드 확인");
		return questboardMapper.getAllQuestBoard();
	}

	@Override
	public QuestBoardDTO getOneQuestBoard(int num) {
		System.out.println("QuestBoardServiceImpl getOneQuestBoard(*@*) 메서드 확인");
		return questboardMapper.getOneQuestBoard(num);
	}

	@Override
	public void reWriteQuestBoard(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServiceImpl reWriteQuestBoard(*@*) 메서드 확인");
		questboardMapper.reWriteQuestBoard(qdto);
	}

	@Override
	public void readCountUpdate(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServiceImpl readCountUpdate(*@*) 메서드 확인");
		questboardMapper.readCountUpdate(qdto);
	}

	@Override
	public void updateOneBoard(QuestBoardDTO qdto) {
		System.out.println("QuestBoardServiceImpl updateOneBoard(*@*) 메서드 확인");
		questboardMapper.updateOneBoard(qdto);
	}

	@Override
	public int getAllcount() {
		System.out.println("QuestBoardServiceImpl getAllcount(*@*) 메서드 확인");
		return 0;
	}

	@Override
	public List<QuestBoardDTO> getPagelist(int startRow, int pageSize) {
		System.out.println("QuestBoardServiceImpl getPagelist(*@*) 메서드 확인");
		return null;
	}
	
	
}
