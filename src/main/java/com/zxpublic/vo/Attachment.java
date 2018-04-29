package com.zxpublic.vo;

import java.util.Date;

public class Attachment {

	private Long attachId;
	//系统配置基本存放路径,如果没有指定，则使用系统默认存放基本路径
	private String baseDir;
	//附件名称
	private String attachname;
	//附件大小
	private Long filesize;
	//附件存放路径
	private String attachment;
	/**
	 * 附件类型
	 */
	private String attachtype;
	/**
	 * 上传人名称
	 */
	private String operName;
	//上传人ID
	private String operId;
	//备注
	private String memo;
	//上传时间
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

	//状态   默认为0  表示有效
	private Integer status;
}
