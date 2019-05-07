package com.oracle.web.mapper;

import com.oracle.web.bean.Admin;
 

public interface AdminMapper {
    
    int insert(Admin record);

	Admin login(Admin admin);

	 Admin queryone(Admin admin);
    
}