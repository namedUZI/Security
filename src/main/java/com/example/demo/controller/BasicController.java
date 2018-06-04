package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class BasicController {
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(Principal pr) {
		//当前用户
		String name = pr.getName();
		return "index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String denglu() {
		System.out.println("myloginGET");
		return "login";
	}
}
