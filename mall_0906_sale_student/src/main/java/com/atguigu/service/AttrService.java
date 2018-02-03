package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.Obj_T_MALL_ATTR;

public interface AttrService {

	void save(List<Obj_T_MALL_ATTR> list, int flbh2);

	List<Obj_T_MALL_ATTR> select_attrall(int flbh2);

}
