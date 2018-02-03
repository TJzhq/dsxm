package com.atguigu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.T_MALL_PRODUCT;

public interface SpuMapper {

	void insert(T_MALL_PRODUCT spu);

	List<T_MALL_PRODUCT> selectAll( @Param("pp_id")int pp_id,@Param("flbh2") int flbh2);
	
}
