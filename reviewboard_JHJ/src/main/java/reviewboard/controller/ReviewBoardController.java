package reviewboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reviewboard.dto.PageHandler;
import reviewboard.dto.ReviewBoardDTO;
import reviewboard.service.ReviewBoardService;

@Controller
public class ReviewBoardController {
	
	@Autowired
	ReviewBoardService reviewboardservice;
	
	//리뷰 리스트 출력
	@GetMapping("/review/list")
	public String reviewList_form(Model model,ReviewBoardDTO rdto,
			//페이지 번호 1부터 시작이므로 초기값 1로 정의 
			@RequestParam(value="page",defaultValue="1") int page,
			// 한화면에 보여지는 페이지 수를 5로 초기화 한다.
			@RequestParam(value="pageSize",defaultValue="5") int pageSize
			) {
		System.out.println("reviewList_form(^^) 메소드 확인");
		
		int totalCnt = reviewboardservice.getAllcount();
		
		PageHandler ph = new PageHandler(totalCnt, page, pageSize);
		reviewboardservice.getPagelist(0, 0);
		
//		List<ReviewBoardDTO> list = reviewboardservice.getAllreview();
		List<ReviewBoardDTO> list = reviewboardservice.getPagelist(ph.getStartRow(), pageSize);
		double starAvg = reviewboardservice.starAvg(rdto);
		model.addAttribute("list",list);
		model.addAttribute("star",starAvg);
		model.addAttribute("ph",ph);
		return "/review/reviewList";
	}
	
	// 리뷰 작성페이지
	@GetMapping("/review/review_form")
	public String review_form() {
		System.out.println("review_form(^^) 메소드 확인");
		
		return "/review/review_form";
	}
	// 리뷰 상세보기 페이지
	@GetMapping("/review/info")
	public String review_info(Model model,@RequestParam("num") int num) {
		System.out.println("review_info(^^) 메소드 확인");
		ReviewBoardDTO oneReviewInfo = reviewboardservice.getOnereview(num);
		
		model.addAttribute("onereview",oneReviewInfo);
		return "/review/review_info";
	}
	
	// 리뷰 작성기능
	@PostMapping("/review/writePro")
	public String review_writePro(ReviewBoardDTO rdto) {
		System.out.println("review_writePro(^^) 메소드 확인");
		reviewboardservice.insertReview(rdto);
		
		return "redirect:/review/list";
	}
	// 리뷰 삭제 기능
	@GetMapping("/review/deletePro")
	public String review_deletePro(@RequestParam("num") int num) {
		System.out.println("review_deletePro(^^) 메소드 확인");
		boolean ischk = reviewboardservice.deleteReview(num);
		if(ischk) {
			return "redirect:/review/list";
		}else {
			return "redirect:/review/review_info?num"+num;
		}
		
		
	}
	
	// 리뷰 수정페이지
	@GetMapping("/review/update")
	public String review_update_form(Model model,@RequestParam("num") int num) {
		System.out.println("review_update_form(^^) 메소드 확인");
		
		ReviewBoardDTO oneReviewInfo = reviewboardservice.getOnereview(num);
		model.addAttribute("oneReview",oneReviewInfo);
		
		return "/review/review_update";
	}
	// 리뷰 수정기능
	@PostMapping("/review/updatePro")
	public String review_updatePro(Model model, ReviewBoardDTO rdto) {
		System.out.println("review_updatePro(^^) 메소드 확인");
		
		boolean chk = reviewboardservice.updateReview(rdto);
		if(chk) {
			return "redirect:/review/list";
		}else {
			return "redirect:/review/update?num"+rdto.getNum();
		}
	}
}
