package com.zxpublic.vo;

import java.util.Date;

public class ServeInfo extends Page {
	private Integer serveId;
	// 1��ɩ��2����ʦ��3�㻤ʦ
	private Integer servetype;
	//�û�����
	private String personalneed;
	//�㻤����
	private String escortperson;
	//��������
	private String lactationdemand;
	//�������
	private String selfcare;
	//�����
	private String agebracket;
	//����ʱ��
	private Date dutytime;
	//�ύʱ��
	private Date duedate;
	//����
	private String username;
	//�ֻ���
	private String phonenum;
	//����ʱ��
	private Date subtime;
	//st
	//��ע
	private String remark;
	//����״̬   0����δȷ�ϣ�1����ȷ�ϣ�2������Ա���ţ�3�������,4�ܾ�����
	private Integer servestate;

	//��ʼʱ�䣨����ʱ�䷶Χ��ѯ��
	private Date startTime;
	//����ʱ�䣨����ʱ�䷶Χ��ѯ��
	private Date endTime;
	
	//��������
	private Integer IntervalNumber;
	//��������
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