package com.qualtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.qualtech.beans.User;
import com.qualtech.dao.UserDaointerface;

@Controller
public class MappingController {
	@Autowired
	private UserDaointerface daoref;
	
	@RequestMapping(value="/")
	public String indexpageWithModel(Model model)
	{
		model.addAttribute("User",new User());		
		return "index";
		
	}
	
	@RequestMapping(value="/dashboard" , method = RequestMethod.GET)
	public String dashboard()
	{
		System.out.println("in dashboard");
		return "dashboard";
		
	}
	@RequestMapping(value="/data" , method = RequestMethod.GET)
	public String data()
	{
		System.out.println("Json Grid");
		return "data";
		
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User usr,Model model) {
		String flag=null;
		System.out.println("reaching in login post method ");
		User user=daoref.getUser(usr.getUserEmail(), usr.getUserPassword());
		if(user!=null)
		flag="login_success";
		else
		flag="login_fail";
		model.addAttribute("flag",flag);
		return "status";
		
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(User usr,Model model) {
		String flag=null;
		System.out.println("reaching in register post method ");
		long uid=daoref.addUser(usr);
		if(uid>0 && Long.toString(uid)!=null){
			flag="register_success";
		}else{
			flag="register_fail";
		}
		model.addAttribute("flag",flag);
		return "status";
		
	}
	
	
}
