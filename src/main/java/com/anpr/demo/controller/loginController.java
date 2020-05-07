package com.anpr.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anpr.demo.api.login;
import com.anpr.demo.repo.loginRep;

@RestController
@RequestMapping("anpr")
public class loginController {

	@Autowired
	private loginRep loginrep;
	
	@GetMapping("getalllogin")
	public List<login> getallLogin()
	{
		return this.loginrep.findAll();
	}
	
	
}
