package com.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// @RestController는 @controller + @ResponseBody를 합친 어노테이션이다
// -> 컨트롤러 역활 + 데이터를 JSON으로 응답하여 사용
// @ResponseBody는 메소드가 변환하는 데이터를 HTML 뷰를 찾는 용도가
// 아니라, 데이터 그 자체(JSON)로 응답 받아 직접쓰겠다는 의미
// @Restcontroller 하나만 맨 위에 적어주면 모든 메서드들은
// @ResponseBody 붙이지 않아도 된다

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	CarProductService carProductservice; // carList 메서드
	
	// 자동차 리스트 JSON으로 변환하는 API
	@GetMapping("/cars")
	public List<CarProductDTO> getCarList(){
		System.out.println("ApiController getCarList(UvU) 메소드 확인");
		//DB에서 데이터를 가져와서 그대로 리턴(Spring가 자동으로 JSON배열로 변환)
		return carProductservice.getAllCarProduct();
	}
	
	
	
}
