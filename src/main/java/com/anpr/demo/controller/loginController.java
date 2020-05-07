package com.anpr.demo.controller;

import java.util.Date;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anpr.demo.api.carrecord;
import com.anpr.demo.api.login;
import com.anpr.demo.api.numberplate;
import com.anpr.demo.api.session;
import com.anpr.demo.api.tolldata;
import com.anpr.demo.api.tollrecord;
import com.anpr.demo.repo.carrecordRep;
import com.anpr.demo.repo.loginRep;
import com.anpr.demo.repo.numberplateRep;
import com.anpr.demo.repo.sessionRep;
import com.anpr.demo.repo.tolldataRep;
import com.anpr.demo.repo.tollrecordRep;

@RestController
@RequestMapping("anpr")
public class loginController {

	@Autowired
	private loginRep loginrep;
	@Autowired
	private numberplateRep numrep;
	@Autowired
	private sessionRep sessionrep;
	@Autowired
	private tolldataRep tolldatarep;
	@Autowired
	private tollrecordRep tollrecordrep;
	@Autowired
	private carrecordRep carrecordrep; 
	
	@GetMapping("getalllogin")
	public List<login> getallLogin()
	{
		return this.loginrep.findAll();
	}
	
	@GetMapping("insertlogin/{id}/{pas}/{type}")
	public List<login> insertLohin(@PathVariable("id") String id,@PathVariable("pas") String pass,@PathVariable("type") int type)
	{
		login l=new login(id,pass,type);
		this.loginrep.save(l);
		return this.loginrep.findAll();
	}
	
	
	@GetMapping("getallnumberplate")
	public List<numberplate> getallNumberPlate()
	{
		return this.numrep.findAll();
	}
	
	@GetMapping("insertnumberplate/{id}/{lic}/{mobile}/{name}/{toll}/{type}")
	public List<numberplate> insertNumberPlate(@PathVariable("id") String id,@PathVariable("lic") String lic,@PathVariable("mobile") int mobile,@PathVariable("name") String name,@PathVariable("toll") int toll,@PathVariable("type") int type)
	{
		numberplate num=new numberplate(id, lic, mobile, name, toll,type);
		this.numrep.save(num);
		return this.numrep.findAll();
	}
	
	
	@GetMapping("getallcarrecord")
	public List<carrecord> getallCarRecord()
	{
		return this.carrecordrep.findAll();
	}
	
	@GetMapping("insertcarrecord/{num}/{tollam}/{tollco}")
	public List<carrecord> insertCarRecord(@PathVariable("num") String num,@PathVariable("tollam") int tollam,@PathVariable("tollco") String tollco)
	{
		carrecord c=new carrecord(num, tollam, new java.util.Date(), tollco)
		return this.carrecordrep.findAll();
	}
	
	
	@GetMapping("getallsession")
	public List<session> getallSesssion()
	{
		return this.sessionrep.findAll();
	}
	
	@GetMapping("insertsession/{id}/{type}")
	public List<session> insertSesssion(@PathVariable("id") String id,@PathVariable("type") int type)
	{
		session s=new session(id,type);
		this.sessionrep.save(s);
		return this.sessionrep.findAll();
	}	
	
	@GetMapping("getalltolldata")
	public List<tolldata> getallTollData()
	{
		return this.tolldatarep.findAll();
	}
	
	@GetMapping("inserttolldata/{id}/{excep}")
	public List<tolldata> insertTollData(@PathVariable("id") String id,@PathVariable("excep") String excep)
	{
		tolldata t=new tolldata(id,excep);
		this.tolldatarep.save(t);
		return this.tolldatarep.findAll();
	}
	
	@GetMapping("getalltollrecord")
	public List<tollrecord> getallTollRecord()
	{
		return this.tollrecordrep.findAll();
	}
	
	@GetMapping("inserttollrecord/{id}/{t1}/{t2}/{t3}/{t4}/{t5}/{t6}")
	public List<tollrecord> insertTollRecord(@PathVariable("id") String id,@PathVariable("t1") int t1,@PathVariable("t2") int t2,@PathVariable("t3") int t3,@PathVariable("t4") int t4,@PathVariable("t5") int t5,@PathVariable("t6") int t6)
	{
		tollrecord t=new tollrecord(id, t1, t2, t3, t4, t5, t6);
		this.tollrecordrep.save(t);
		return this.tollrecordrep.findAll();
	}
	
	
	
}
