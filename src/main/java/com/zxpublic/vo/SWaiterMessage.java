package com.zxpublic.vo;

import java.util.List;

/**
 * 服务人员信息类
 * 
 * @author penggl
 * @date 2017年8月12日10:52:40
 *
 */
public class SWaiterMessage extends Page {
	// ID，主键
	private Long id;
	// 服务人员编号
	private String waiterNo;

	// 1月嫂，2催乳师，3陪护师
	private Integer waiterType;
	// 服务人员姓名
	private String waiterName;
	// 服务人员头像ID
	private String waiterImageurl;
	// 服务人员身份证
	private String waiterIdcard;
	// 价格 （10000/26天）
	private Long waiterPrice;
	// 年龄
	private Integer waiterAge;
	// 籍贯_省
	private String waiterProvince;
	// 籍贯_市
	private String waiterCity;
	// 带宝宝总数
	private Integer waiterBabycount;
	// 特征（aaa,bbb,ccc）
	private String waiterCharacteristics;
	// 平台认证信息
	private String waiterPlatformMsg;
	// 从业经历
	private String waiterExperience;
	// 星座
	private String waiterConstellation;
	// 1 一星月嫂 2 二星月嫂 3 三星月嫂 4 四星月嫂 5 五星月嫂
	private Integer waiterStarlevel;
	// 1 小学 2初中 3 高中 4 大专 5 中专 6 本科
	private Integer waiterEducation;
	// 备注
	private String remark;
	// 删除标识 （1：标识删除，0：数据正常）
	private Integer deleteFlag;
	// 头像Id
	private String attachId;
	// 职称
	private String jobTitle;

	// 附件集合
	private List<SWaiterRelation> rlist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWaiterNo() {
		return waiterNo;
	}

	public void setWaiterNo(String waiterNo) {
		this.waiterNo = waiterNo == null ? null : waiterNo.trim();
	}

	public Integer getWaiterType() {

		return waiterType;
	}

	public void setWaiterType(Integer waiterType) {
		this.waiterType = waiterType;
	}

	public String getWaiterName() {
		return waiterName;
	}

	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName == null ? null : waiterName.trim();
	}

	public String getWaiterImageurl() {
		return waiterImageurl;
	}

	public void setWaiterImageurl(String waiterImageurl) {
		this.waiterImageurl = waiterImageurl == null ? null : waiterImageurl.trim();
	}

	public String getWaiterIdcard() {
		return waiterIdcard;
	}

	public void setWaiterIdcard(String waiterIdcard) {
		this.waiterIdcard = waiterIdcard == null ? null : waiterIdcard.trim();
	}

	public Long getWaiterPrice() {
		return waiterPrice;
	}

	public void setWaiterPrice(Long waiterPrice) {
		this.waiterPrice = waiterPrice;
	}

	public Integer getWaiterAge() {
		return waiterAge;
	}

	public void setWaiterAge(Integer waiterAge) {
		this.waiterAge = waiterAge;
	}

	public String getWaiterProvince() {
		return waiterProvince;
	}

	public void setWaiterProvince(String waiterProvince) {
		this.waiterProvince = waiterProvince == null ? null : waiterProvince
				.trim();
	}

	public String getWaiterCity() {
		return waiterCity;
	}

	public void setWaiterCity(String waiterCity) {
		this.waiterCity = waiterCity == null ? null : waiterCity.trim();
	}

	public Integer getWaiterBabycount() {
		return waiterBabycount;
	}

	public void setWaiterBabycount(Integer waiterBabycount) {
		this.waiterBabycount = waiterBabycount;
	}

	public String getWaiterCharacteristics() {
		return waiterCharacteristics;
	}

	public void setWaiterCharacteristics(String waiterCharacteristics) {
		this.waiterCharacteristics = waiterCharacteristics == null ? null
				: waiterCharacteristics.trim();
	}

	public String getWaiterPlatformMsg() {
		return waiterPlatformMsg;
	}

	public void setWaiterPlatformMsg(String waiterPlatformMsg) {
		this.waiterPlatformMsg = waiterPlatformMsg == null ? null
				: waiterPlatformMsg.trim();
	}

	public String getWaiterExperience() {
		return waiterExperience;
	}

	public void setWaiterExperience(String waiterExperience) {
		this.waiterExperience = waiterExperience == null ? null
				: waiterExperience.trim();
	}

	public String getWaiterConstellation() {
		return waiterConstellation;
	}

	public void setWaiterConstellation(String waiterConstellation) {
		this.waiterConstellation = waiterConstellation == null ? null
				: waiterConstellation.trim();
	}

	public Integer getWaiterStarlevel() {
		return waiterStarlevel;
	}

	public void setWaiterStarlevel(Integer waiterStarlevel) {
		this.waiterStarlevel = waiterStarlevel;
	}

	public Integer getWaiterEducation() {
		return waiterEducation;
	}

	public void setWaiterEducation(Integer waiterEducation) {
		this.waiterEducation = waiterEducation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag == null ? 0 : deleteFlag;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public List<SWaiterRelation> getRlist() {
		return rlist;
	}

	public void setRlist(List<SWaiterRelation> rlist) {
		this.rlist = rlist;
	}

	@Override
	public String toString() {
		return "SWaiterMessage [id=" + id + ", waiterNo=" + waiterNo
				+ ", waiterType=" + waiterType + ", waiterName=" + waiterName
				+ ", waiterImageurl=" + waiterImageurl + ", waiterIdcard="
				+ waiterIdcard + ", waiterPrice=" + waiterPrice
				+ ", waiterAge=" + waiterAge + ", waiterProvince="
				+ waiterProvince + ", waiterCity=" + waiterCity
				+ ", waiterBabycount=" + waiterBabycount
				+ ", waiterCharacteristics=" + waiterCharacteristics
				+ ", waiterPlatformMsg=" + waiterPlatformMsg
				+ ", waiterExperience=" + waiterExperience
				+ ", waiterConstellation=" + waiterConstellation
				+ ", waiterStarlevel=" + waiterStarlevel + ", waiterEducation="
				+ waiterEducation + ", remark=" + remark + ", deleteFlag="
				+ deleteFlag + ", attachId=" + attachId + ", jobTitle="
				+ jobTitle + ", rlist=" + rlist + "]";
	}
	
}