package com.green.carproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.carproduct.mapper.CarProductMapper;

@Service
public class CarProductService {
	@Autowired
	CarProductMapper  carProductmapper;
	
	public List<CarProductDTO> getAllCarProduct(){
		return carProductmapper.getAllCarProduct();
	}
	
	public void insertCarProduct(CarProductDTO dto) {
		carProductmapper.insertCarProduct(dto);
	}
}
