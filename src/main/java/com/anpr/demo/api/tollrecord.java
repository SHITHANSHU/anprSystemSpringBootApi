package com.anpr.demo.api;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tollrecord")

public class tollrecord {
	@Id
	@Column(name="code")
    private String code;
	@Column(name="type1")
    private int type1;
	@Column(name="type2")
    private int type2;
	@Column(name="type3")
    private int type3;
	@Column(name="type4")
    private int type4;
	@Column(name="type5")
    private int type5;
	@Column(name="type6")
    private int type6;
   
    public tollrecord() {
		
	}
    public tollrecord(String code, int type1, int type2, int type3, int type4, int type5, int type6) {
        this.code = code;
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.type4 = type4;
        this.type5 = type5;
        this.type6 = type6;
    }

    public int getType6() {
        return type6;
    }

    public void setType6(int type6) {
        this.type6 = type6;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public int getType3() {
        return type3;
    }

    public void setType3(int type3) {
        this.type3 = type3;
    }

    public int getType4() {
        return type4;
    }

    public void setType4(int type4) {
        this.type4 = type4;
    }

    public int getType5() {
        return type5;
    }

    public void setType5(int type5) {
        this.type5 = type5;
    }
}

