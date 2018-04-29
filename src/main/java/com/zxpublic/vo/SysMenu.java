package com.zxpublic.vo;


/**
 * SysMenu 系统菜单类
 * @author penggl
 *
 */
public class SysMenu {

	//ID
	private Integer sid;
	//父ID
	private Integer pid;
	//菜单栏目名称
	private String stitle;
	//菜单地址
	private String surl;
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getStitle() {
		return stitle;
	}
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	public String getSurl() {
		return surl;
	}
	public void setSurl(String surl) {
		this.surl = surl;
	}
	
}
