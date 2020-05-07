package com.anpr.demo.api;


import java.math.BigInteger;
import java.security.MessageDigest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class login {
	@Id
	@Column(name="username")
    String uname;
	@Column(name="password")
    String password;
	@Column(name="type")
    int type;

	public login()
	{
		
	}
	
    public login(String uname,String password,int type)
    {
        this.uname=uname;
        this.password=password;
        this.type=type;
        
        System.out.println(uname+" "+password+" "+type);
    }

    public static String getMd5(String input)
    {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");


            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void setuplogin(String uname,String password,int type)
    {
        this.uname=uname;
        this.password=getMd5(password);
        this.type=type;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
    	System.out.println(uname);
        return uname;
    }

    public void setUname(String uname) {
    	System.out.println(uname);
        this.uname = uname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
