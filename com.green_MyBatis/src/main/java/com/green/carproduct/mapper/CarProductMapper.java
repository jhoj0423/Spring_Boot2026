package com.green.carproduct.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.carproduct.CarProductDTO;

@Mapper
public interface CarProductMapper {
	public List<CarProductDTO> getAllCarProduct();
}
