package com.fuceng.Bean;

import java.io.Serializable;

public class User implements Serializable{

	private Integer id;
	private String name;
	private String address;
	private String date;
	private Integer age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + ", date=" + date + ", age=" + age + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String name, String address, String date, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.date = date;
		this.age = age;
	}
	
	
	
}
