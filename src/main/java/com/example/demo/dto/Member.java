package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 이혜연
 *
 */
@javax.persistence.Entity
public class Member {
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
