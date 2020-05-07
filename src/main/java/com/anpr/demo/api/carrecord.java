package com.anpr.demo.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class carrecord {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="numberplate")
	private String numberplate;
	@Column(name="tollamount")
    private int tollamount;
	@Column(name="tollcode")
    private String tollcode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    
    public carrecord()
    {
    	
    }

//    public carrecord(int tollamount,String date,String tollcode) throws ParseException
//    {
//        this.tollamount=tollamount;
//        this.tollcode=tollcode;
//        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
//        this.date=date1;
//        System.out.println("date "+ date);
//    }

    
    
    public carrecord(String numberplate, int tollamount, Date date, String tollcode)
    {
    	this.id=UUID.randomUUID().toString().substring(0,23);
    	this.numberplate=numberplate;
        this.tollamount = tollamount;
        this.date = date;
        this.tollcode = tollcode;
    }

    public carrecord(int tollamount,String date,String tollcode) throws ParseException
    {
        this.tollamount=tollamount;
        this.tollcode=tollcode;
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        this.date=date1;
        System.out.println("date "+ date);
    }

    public carrecord(int tollamount, String tollcode) {
        this.tollamount = tollamount;
        this.tollcode = tollcode;
    }

    public void setNumberplate(String num)
    {
    	this.numberplate=num;
    }
    
    public String getNumberplate() 
    {
    	return this.numberplate;
    }
    
    public void setId(String Id)
    {
    	this.id=Id;
    }
    
    public String getId()
    {
    	return this.id;
    }
    
    public int getTollamount() {
        return tollamount;
    }

    public void setTollamount(int tollamount) {
        this.tollamount = tollamount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTollcode() {
        return tollcode;
    }

    public void setTollcode(String tollcode) {
        this.tollcode = tollcode;
    }
}

