package com.atguigu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.atguigu.bean.T_MALL_PRODUCT;

public interface SpuService {

	void save(T_MALL_PRODUCT spu, List<String> list_image);

	List<T_MALL_PRODUCT> selectallspu(int pp_id,int flbh2);

	

}
