package com.zxpublic.vo;

import java.util.Date;

public class Attachment {

	private Long attachId;
	//ϵͳ���û������·��,���û��ָ������ʹ��ϵͳĬ�ϴ�Ż���·��
	private String baseDir;
	//��������
	private String attachname;
	//������С
	private Long filesize;
	//�������·��
	private String attachment;
	/**
	 * ��������
	 */
	private String attachtype;
	/**
	 * �ϴ�������
	 */
	private String operName;
	//�ϴ���ID
	private String operId;
	//��ע
	private String memo;
	//�ϴ�ʱ��
	private Date createtime;
	
	public Long getAttachId() {
		return attachId;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String getAttachname() {
		return attachname;
	}

	public void setAttachname(String attachname) {
		this.attachname = attachname;
	}

	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachtype() {
		return attachtype;
	}

	public void setAttachtype(String attachtype) {
		this.attachtype = attachtype;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	//״̬   Ĭ��Ϊ0  ��ʾ��Ч
	private Integer status;
}
