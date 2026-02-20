package com.green.carproduct.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.carproduct.CarProductDTO;

@Mapper
public interface CarProductMapper {
	public List<CarProductDTO> getAllCarProduct();
	
	// insert 문 => List<CarProductDTO> 사용 못함
	public void insertCarProduct(CarProductDTO dto);
}
