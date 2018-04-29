package com.zxpublic.vo;

public class Page {
	//��ǰҳ��
	private Integer currentPage;
	//ÿҳ��С
	private Integer pageSize;
	//
	private Integer pageNo;
	//��ҳ��
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
