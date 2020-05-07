package com.anpr.demo.api;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="numberplate")
public class numberplate {
	@Id
	@Column(name="id")
    String id;

	@Column(name="license")
    String license;

	@Column(name="mobile")
    int mobile;
	
	@Column(name="name")
    String name;

	@Column(name="toll")
    int toll;

	@Column(name="type")
    int type;


    public static final numberplate blank=new numberplate();
    
    public numberplate()
    {

    }
    public numberplate(String id,String license,int mobile, String name, int toll,int type)
    {
        System.out.println("new number plate created");
        this.id=id;
        this.license=license;
        this.mobile=mobile;
        this.name=name;
        this.toll=toll;
        this.type=type;
    }
    public numberplate(String id,String license,int mobile, String name, int toll)
    {
        System.out.println("new number plate created");
        this.id=id;
        this.license=license;
        this.mobile=mobile;
        this.name=name;
        this.toll=toll;
        this.type=1;
    }

    public int getMobile() {
        return mobile;
    }

    public int getToll() {
        return toll;
    }

    public String getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLicense() {
        return license;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToll(int toll) {
        this.toll = toll;
    }
}

