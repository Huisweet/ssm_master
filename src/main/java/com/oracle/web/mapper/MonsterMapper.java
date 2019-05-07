package com.oracle.web.mapper;

import com.oracle.web.bean.Monster;
import com.oracle.web.bean.SubMonster;

import java.util.List;

public interface MonsterMapper {
    int deleteByPrimaryKey(Integer monsterId);

    int insert(SubMonster monster);

    SubMonster selectByPrimaryKey(Integer monsterId);

    List<SubMonster> selectAll();

    int updateByPrimaryKey(Monster record);

	List<SubMonster> showByPage(Integer index);

	int selectCount();

	List<SubMonster> selectAllByPageHelper();
}