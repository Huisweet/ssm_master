package com.oracle.web.service;

import com.oracle.web.bean.Admin;

public interface AdminService {

	 
	void save(Admin admin);
	
	 
	Admin login(Admin admin);

 
	Admin queryone(Admin admin);

}
