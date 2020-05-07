package com.anpr.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("insert/{id}/{pas}/{type}")
	public List<login> insertLohin(@PathVariable("id") String id,@PathVariable("pas") String pass,@PathVariable("type") int type)
	{
		login l=new login(id,pass,type);
		this.loginrep.save(l);
		return this.loginrep.findAll();
	}
	
}
