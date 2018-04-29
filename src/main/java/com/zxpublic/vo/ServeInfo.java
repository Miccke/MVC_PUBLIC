package com.zxpublic.vo;

import java.util.Date;

public class ServeInfo extends Page {
	private Integer serveId;
	// 1月嫂，2催乳师，3陪护师
	private Integer servetype;
	//用户需求
	private String personalneed;
	//陪护对象
	private String escortperson;
	//催乳需求
	private String lactationdemand;
	//自理情况
	private String selfcare;
	//年龄段
	private String agebracket;
	//到岗时间
	private Date dutytime;
	//提交时间
	private Date duedate;
	//姓名
	private String username;
	//手机号
	private String phonenum;
	//结束时间
	private Date subtime;
	//st
	//备注
	private String remark;
	//订单状态   0订单未确认，1订单确认，2服务人员上门，3订单完成,4拒绝订单
	private Integer servestate;

	//开始时间（用于时间范围查询）
	private Date startTime;
	//结束时间（用于时间范围查询）
	private Date endTime;
	
	//区间数量
	private Integer IntervalNumber;
	//区间数量
	private Integer allNumber;
	
	private String openid;
	
	public Integer getServeId() {
		return serveId;
	}

	public void setServeId(Integer serveId) {
		this.serveId = serveId;
	}

	public Integer getServetype() {
		return servetype;
	}

	public void setServetype(Integer servetype) {
		this.servetype = servetype;
	}

	public String getPersonalneed() {
		return personalneed;
	}

	public void setPersonalneed(String personalneed) {
		this.personalneed = personalneed == null ? null : personalneed.trim();
	}

	public String getEscortperson() {
		return escortperson;
	}

	public void setEscortperson(String escortperson) {
		this.escortperson = escortperson == null ? null : escortperson.trim();
	}

	public String getLactationdemand() {
		return lactationdemand;
	}

	public void setLactationdemand(String lactationdemand) {
		this.lactationdemand = lactationdemand == null ? null : lactationdemand
				.trim();
	}

	public String getSelfcare() {
		return selfcare;
	}

	public void setSelfcare(String selfcare) {
		this.selfcare = selfcare == null ? null : selfcare.trim();
	}

	public String getAgebracket() {
		return agebracket;
	}

	public void setAgebracket(String agebracket) {
		this.agebracket = agebracket == null ? null : agebracket.trim();
	}

	public Date getDutytime() {
		return dutytime;
	}

	public void setDutytime(Date dutytime) {
		this.dutytime = dutytime;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum == null ? null : phonenum.trim();
	}

	public Date getSubtime() {
		return subtime;
	}

	public void setSubtime(Date subtime) {
		this.subtime = subtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getServestate() {
		return servestate;
	}

	public void setServestate(Integer servestate) {
		this.servestate = servestate;
	}

	@Override
	public String toString() {
		return "ServeInfo [serveId=" + serveId + ", servetype=" + servetype
				+ ", personalneed=" + personalneed + ", escortperson="
				+ escortperson + ", lactationdemand=" + lactationdemand
				+ ", selfcare=" + selfcare + ", agebracket=" + agebracket
				+ ", dutytime=" + dutytime + ", duedate=" + duedate
				+ ", username=" + username + ", phonenum=" + phonenum
				+ ", subtime=" + subtime + ", remark=" + remark
				+ ", servestate=" + servestate + "]";
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getIntervalNumber() {
		return IntervalNumber;
	}

	public void setIntervalNumber(Integer intervalNumber) {
		IntervalNumber = intervalNumber;
	}

	public Integer getAllNumber() {
		return allNumber;
	}

	public void setAllNumber(Integer allNumber) {
		this.allNumber = allNumber;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}