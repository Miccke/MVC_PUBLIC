package com.zxpublic.vo;

public class Page {
	//当前页码
	private Integer currentPage;
	//每页大小
	private Integer pageSize;
	//
	private Integer pageNo;
	//总页数
	private Integer total;
	
	private Integer startIndex;
	
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
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getStartIndex() {
		if(startIndex == null){
			this.startIndex = (this.currentPage - 1) * this.pageSize;
		}
		if(this.startIndex < 0 ){
			this.startIndex = 0;
		}
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

}
