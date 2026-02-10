package reviewboard.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reviewboard.dto.ReviewBoardDTO;
import reviewboard.mapper.ReviewBoardMapper;

@Service
public class ReviewBoardService {
	
	@Autowired
	ReviewBoardMapper reviewboardmapper;
	
	// 리뷰 추가
	public void insertReview(ReviewBoardDTO rdto) {
		System.out.println("insertReview(@ㅐ@) 메서드 확인용");
		reviewboardmapper.insertReview(rdto);
	};
	// 리뷰 전체 선택
//	public List<ReviewBoardDTO> getAllreview(){
//		System.out.println("getAllreview(@ㅐ@) 메서드 확인용");
//		return reviewboardmapper.getAllreview();
//	};
	// 한개의 리뷰 선택
	public ReviewBoardDTO getOnereview(int num){
		System.out.println("getOnereview(@ㅐ@) 메서드 확인용");
		//선택리뷰 조회수 ↑
		reviewboardmapper.updateReadReview(num);
		return reviewboardmapper.getOnereview(num);
	};
	// 선택한 리뷰 내용 수정
	public boolean updateReview(ReviewBoardDTO rdto) {
		System.out.println("updateReview(@ㅐ@) 메서드 확인용");
		int result = reviewboardmapper.updateReview(rdto);
		if(result>0) {
			return true;
		}else {
			return false;
		}
	};
	// 선택한 리뷰 삭제
	public boolean deleteReview(int num) {
		System.out.println("deleteReview(@ㅐ@) 메서드 확인용");
		int result = reviewboardmapper.deleteReview(num);
		
		if(result>0) {
			System.out.println("게시글 삭제 성공");
			return true;
		}else {
			System.out.println("게시글 삭제 실패");
			return false;
		}
	};
	// 전체 별점의 평균
	public double starAvg(ReviewBoardDTO rdto) {
		System.out.println("starAvg(@ㅐ@) 메서드 확인용");
		return reviewboardmapper.starAvg(rdto);
	};
	
	// 전체 게시글의 개수를 구하는 매소드
	public int getAllcount() {
		System.out.println("getAllcount(@ㅐ@) 메서드 확인용");
		return reviewboardmapper.getAllcount();
	};
	
	// 전체 게시글의 시작(startRow), 몇개의 행 (pageSize)만큼 보는 메소드
	public List<ReviewBoardDTO> getPagelist(int startRow,int pageSize){
		System.out.println("getPagelist(@ㅐ@) 메서드 확인용");
		return reviewboardmapper.getPagelist(startRow, pageSize);
	};
}
