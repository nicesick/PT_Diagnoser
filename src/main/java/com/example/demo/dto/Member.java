package com.example.demo.dto;

/**
 * @author 이혜연
 *
 */
@javax.persistence.Entity
public class Member {
	@org.springframework.data.annotation.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
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
