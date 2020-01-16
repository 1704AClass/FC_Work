package com.fuceng.util;

import java.io.Serializable;

public class QueryPageBean implements Serializable{

	private Integer currentPage;
	private Integer pageSize;
	private String queryString;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	@Override
	public String toString() {
		return "QueryPageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", queryString=" + queryString
				+ "]";
	}
	public QueryPageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QueryPageBean(Integer currentPage, Integer pageSize, String queryString) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.queryString = queryString;
	}
	
	
}
