package com.anpr.demo.api;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tolldata")

public class tolldata {
	@Id
	@Column(name="id")
	String id;
	@Column(name="code")
    public String code;
	@Column(name="exception")
    public String exception;
    
    public tolldata()
    {
    	
    }

    public tolldata(String code, String exception) {
    	this.id=UUID.randomUUID().toString().substring(0,23);
        this.code = code;
        this.exception = exception;
    }

    public String getId()
    {
    	return this.id;
    }
    
    public void setId(String id)
    {
    	this.id=id;
    }
    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
