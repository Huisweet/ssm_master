package com.oracle.web.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.web.bean.Monster;
import com.oracle.web.bean.PageBean;
import com.oracle.web.bean.School;
import com.oracle.web.bean.SubMonster;
import com.oracle.web.service.MonsterService;
import com.oracle.web.service.SchoolService;

@Controller
@Scope(value = "prototype")
public class MonsterHandler {

	@Autowired
	private MonsterService monsterService;

	@Autowired
	private SchoolService schoolService;

	private Integer pageNow;

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	@RequestMapping(value = "/monsters", method = RequestMethod.GET)
	public String monsters(HttpServletRequest request) {

		List<SubMonster> list = monsterService.list();
		System.out.println(list);
		request.setAttribute("mlist", list);

		return "list";

	}

	@RequestMapping(value = "/addUI", method = RequestMethod.GET)
	public String addUI(HttpServletRequest request) {

		List<School> list = schoolService.list();

		request.setAttribute("slist", list);

		return "add";

	}

	@RequestMapping(value = "/monster", method = RequestMethod.POST)
	public String add(SubMonster monster) {// 真正的add

		monsterService.save(monster);

		return "redirect:/monsters/1";// 重定向到list

	}

	@RequestMapping(value = "/monster_delete/{monsterId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("monsterId") Integer id) {
		Monster m = new Monster();

		m.setMonsterId(id);

		monsterService.delete(m);

		return "redirect:/monsters/1";

	}

	@RequestMapping(value = "/monster/{monsterId}", method = RequestMethod.GET)

	public String updateUI(@PathVariable("monsterId") Integer id, HttpSession session) {

		Monster monster = monsterService.queryOneMonster(id);

		session.setAttribute("m", monster);

		List<School> list = schoolService.list();

		session.setAttribute("slist", list);

		return "redirect:/update.jsp";

	}

	@RequestMapping(value = "/monster", method = RequestMethod.PUT)

	public String update(SubMonster monster) {

		monsterService.update(monster);

		return "redirect:/monsters/1";

	}

	/*@RequestMapping(value = "/monsters/{pageNow}", method = RequestMethod.GET)
	public String showByPage(@PathVariable(value="pageNow")Integer pageNow,HttpServletRequest request) {// 分页

		int pageSize=4;
		
		if(pageNow==null){
			
			pageNow=1;
			
		}
		
		PageBean<SubMonster> pb=this.monsterService.showByPage(pageNow, pageSize);
		
		request.setAttribute("pb", pb);
		
		return "list";
		
		
	}*/
	
	@RequestMapping(value = "/monsters/{pageNow}", method = RequestMethod.GET)
	public String showByPage(@PathVariable(value="pageNow")Integer pageNow,HttpServletRequest request) {// 分页

		 
		
		if(pageNow==null){
			
			pageNow=1;
			
		}
		 
		PageBean<SubMonster> pb=this.monsterService.selectAllByPageHelper(pageNow);
		
		request.setAttribute("pb", pb);
		
		return "list";
		
		
	}

}
