package com.anpr.demo.api;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="session")
public class session {
	@Column(name="id")
    private  String id;
	@Id
	@Column(name="accessToken")
    private  String accessToken;
	@Column(name="type")
    private int type;
	public session()
	{
		
	}
    public session(String id,int type) {
        this.id = id;
        this.accessToken = UUID.randomUUID().toString().substring(0,23);
        this.type=type;
    }
    public session(String id,String accessToken,int type ) {
        this.id = id;
        this.accessToken = accessToken;
        this.type=type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
