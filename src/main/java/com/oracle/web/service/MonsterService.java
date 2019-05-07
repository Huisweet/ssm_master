package com.oracle.web.service;

import java.util.List;

import com.oracle.web.bean.Monster;
import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.SubMonster;
 
 

public interface MonsterService {

	List<SubMonster> list();

	int save(SubMonster monster);
 
	void delete(Monster monster);

	SubMonster queryOneMonster(Integer monsterId);

	void update(SubMonster monster);

	PageBean<SubMonster> showByPage(Integer pageNow, int pageSize);

	PageBean<SubMonster> selectAllByPageHelper(Integer pageNow);

 
	 

	 
	
}
