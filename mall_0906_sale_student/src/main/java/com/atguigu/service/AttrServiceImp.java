package com.atguigu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Obj_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;
import com.atguigu.mapper.AttrMapper;
@Service
public class AttrServiceImp implements AttrService {
	@Autowired
	private AttrMapper attrMapper;
	@Override
	public void save(List<Obj_T_MALL_ATTR> list, int flbh2) {
		for (int i = 0; i < list.size(); i++) {
			Obj_T_MALL_ATTR attr = list.get(i);
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("flbh2", flbh2);
			map.put("attr",attr);
			attrMapper.insertAttr(map);
			
			Map<String,Object> map2 =new HashMap<String,Object>();
			map2.put("shxm_id", attr.getId());
			System.out.println("shxm_id" +attr.getId());
			map2.put("list", attr.getList_value());
			attrMapper.insertValue(map2);
		}
		
		
	}
	@Override
	public List<Obj_T_MALL_ATTR> select_attrall(int flbh2) {
		
		return attrMapper.select_attrall(flbh2);
	}

}
