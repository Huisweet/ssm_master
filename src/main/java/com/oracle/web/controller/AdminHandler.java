package com.oracle.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.oracle.web.bean.Admin;
import com.oracle.web.service.AdminService;

@Controller
@Scope(value = "prototype")
public class AdminHandler {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String register(Admin admin, Errors errors) {// 真正的add

		if (errors.hasErrors()) {

			adminService.save(admin);

			return "register";

		}

		adminService.save(admin);

		return "login";

	}

	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Admin admin, HttpSession session) throws Exception {

		Admin admin1 = adminService.login(admin);

		if (admin1 == null) {

			return "redirect:/login";
		} else {

			if (!admin1.getPassword().equals(admin1.getPassword())) {

				return "redirect:/login";
			}
		}

		session.setAttribute("admin1", admin1);

		return "redirect:/index.jsp";
	}

	
	@RequestMapping(value="/queryone",method = RequestMethod.POST)
	public String queryone(Admin admin,HttpServletResponse response) throws IOException {
		
		Admin admin2=adminService.queryone(admin);
		 
		
		response.setContentType("text/html;charset=utf-8");
		
		if(admin2!=null){
			
            response.getWriter().write("{\"valid\":\"false\"}");
		
			}else{
				
				response.getWriter().write("{\"valid\":\"true\"}");//不存在
			}
		
		return null;
	}
	
	

 
}
