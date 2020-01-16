package com.fuceng.util;

import java.io.Serializable;

public class Result implements Serializable{

	private boolean flag;
	private String message;
	private Object date;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getDate() {
		return date;
	}
	public void setDate(Object date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Result [flag=" + flag + ", message=" + message + ", date=" + date + "]";
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Result(boolean flag, String message, Object date) {
		super();
		this.flag = flag;
		this.message = message;
		this.date = date;
	}
	public Result(boolean flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}
	
	
}
