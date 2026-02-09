package reviewboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import reviewboard.dto.ReviewBoardDTO;

@Mapper
public interface ReviewBoardMapper {
	// 리뷰 추가
	public void insertReview(ReviewBoardDTO rdto);
	// 리뷰 전체 선택
	//public List<ReviewBoardDTO> getAllreview();
	// 한개의 리뷰 선택
	public ReviewBoardDTO getOnereview(int num);
	// 선택리뷰 조회수 ↑
	public void updateReadReview(int num);
	// 선택한 리뷰 내용 수정
	public int updateReview(ReviewBoardDTO rdto);
	// 선택한 리뷰 삭제
	public int deleteReview(int num);
	// 전체 별점의 평균
	public double starAvg(ReviewBoardDTO rdto);
	
	// 전체 게시글의 개수를 구하는 매소드
	public int getAllcount();
	
	// 전체 게시글의 시작(startRow), 몇개의 행 (pageSize)만큼 보는 메소드
	public List<ReviewBoardDTO> getPagelist(@Param("startRow")int startRow,@Param("pageSize")int pageSize);
		
}
