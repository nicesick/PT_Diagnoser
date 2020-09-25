package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 이혜연
 *
 */
public class Member {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private String id;
	private String pwd;
	private String eNum;
	private String email;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String geteNum() {
		return eNum;
	}

	public void seteNum(String eNum) {
		this.eNum = eNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
