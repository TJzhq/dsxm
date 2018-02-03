package com.atguigu.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.Obj_T_MALL_ATTR;

public interface AttrMapper {

	void insertAttr(Map<String, Object> map);

	void insertValue(Map<String, Object> map2);

	List<Obj_T_MALL_ATTR> select_attrall(int flbh2);

}
