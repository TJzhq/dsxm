package com.atguigu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.mapper.SpuImageMapper;
import com.atguigu.mapper.SpuMapper;
@Service
public class SpuServiceImp implements SpuService {
	@Autowired
	private SpuMapper spuMapper;
	@Autowired
	private SpuImageMapper spuImageMapper;
	@Override
	public void save(T_MALL_PRODUCT spu, List<String> list_image) {
		spu.setShp_tp(list_image.get(0));
		spuMapper.insert(spu);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list_image", list_image);
		map.put("spu_id", spu.getId());
		
		spuImageMapper.insert(map);
	}
	@Override
	public List<T_MALL_PRODUCT> selectallspu(int pp_id ,int flbh2) {
		return spuMapper.selectAll(pp_id,flbh2);
	}

	
	

}
