package com.fuceng.util;

public class PageUtils {

	private Integer cpage;//当前页
	private Integer pageSize=5;//每页最大展示条数
	private Integer count;
	private Integer totalPage;//总页数(末页)
	private Integer prevPage;//上一页
	private Integer nextPage;//下一页
	private Integer startIndex;//起始下标
	public PageUtils(Integer cpage,Integer pageSize, Integer count) {
		if(pageSize!=null) {
			this.pageSize = pageSize;
		}
		this.count = count;
		colTotalPage();
		if(cpage==null || cpage<1){
			cpage = 1;
		}else if(cpage>this.totalPage){
			cpage = this.totalPage;
		}
		this.cpage = cpage;
		colStartIndex();
		colPrevPage();
		colNextPage();
	}
	private void colNextPage() {//计算下一页
		this.nextPage = cpage>=totalPage?totalPage:(cpage+1);
	}
	private void colPrevPage() {//计算上一页
		this.prevPage = cpage<=1?1:(cpage-1);
	}
	private void colStartIndex() {//计算起始下标
		this.startIndex = (cpage-1)*pageSize;
	}
	private void colTotalPage() {//计算总页数
		this.totalPage = count/pageSize + (count%pageSize==0?0:1);
	}
	public Integer getCpage() {
		return cpage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public Integer getCount() {
		return count;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public Integer getPrevPage() {
		return prevPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	
}