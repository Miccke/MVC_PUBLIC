package com.zxpublic.vo;


/**
 * SysMenu ϵͳ�˵���
 * @author penggl
 *
 */
public class SysMenu {

	//ID
	private Integer sid;
	//��ID
	private Integer pid;
	//�˵���Ŀ����
	private String stitle;
	//�˵���ַ
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
