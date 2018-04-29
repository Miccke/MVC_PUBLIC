package com.zxpublic.vo;

import java.util.List;

/**
 * ������Ա��Ϣ��
 * 
 * @author penggl
 * @date 2017��8��12��10:52:40
 *
 */
public class SWaiterMessage extends Page {
	// ID������
	private Long id;
	// ������Ա���
	private String waiterNo;

	// 1��ɩ��2����ʦ��3�㻤ʦ
	private Integer waiterType;
	// ������Ա����
	private String waiterName;
	// ������Աͷ��ID
	private String waiterImageurl;
	// ������Ա���֤
	private String waiterIdcard;
	// �۸� ��10000/26�죩
	private Long waiterPrice;
	// ����
	private Integer waiterAge;
	// ����_ʡ
	private String waiterProvince;
	// ����_��
	private String waiterCity;
	// ����������
	private Integer waiterBabycount;
	// ������aaa,bbb,ccc��
	private String waiterCharacteristics;
	// ƽ̨��֤��Ϣ
	private String waiterPlatformMsg;
	// ��ҵ����
	private String waiterExperience;
	// ����
	private String waiterConstellation;
	// 1 һ����ɩ 2 ������ɩ 3 ������ɩ 4 ������ɩ 5 ������ɩ
	private Integer waiterStarlevel;
	// 1 Сѧ 2���� 3 ���� 4 ��ר 5 ��ר 6 ����
	private Integer waiterEducation;
	// ��ע
	private String remark;
	// ɾ����ʶ ��1����ʶɾ����0������������
	private Integer deleteFlag;
	// ͷ��Id
	private String attachId;
	// ְ��
	private String jobTitle;

	// ��������
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