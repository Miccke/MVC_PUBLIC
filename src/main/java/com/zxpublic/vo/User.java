package com.zxpublic.vo;

import java.util.List;


/** 
 *  User映射类 
 * @author Administrator
 *
 */
public class User extends Page{  
    private Integer uid;
    //账号
    private String loginName;
    //密码
    private String passWord;
    //昵称
    private String nickName;
    //年龄
    private String uAge;
    //性别  1:男；2：女；0：其他
    private String uSex;
    //邮箱
    private String uEMail;
    //用户类型 （1：平台人员，2：商家，3：普通用户）
    private Integer uType;
    //是否有消息  1：有；0：无
    private Integer havaMsg;
    
    //头像ID
    private String imageUrl;
    
    private List<SysMenu> menus;
    //移动电话
    private String mobilphone;
    //座机
    private String telephone;
	//地址
    private String address;
    //经度
    private String longitudex;
    //纬度
    private String latitudey;
    
    public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getuAge() {
		return uAge;
	}

	public void setuAge(String uAge) {
		this.uAge = uAge;
	}

	public String getuSex() {
		if(uSex != null && uSex != ""){
			if(uSex.equals("1")){
				uSex = "男";
			}else if(uSex.equals("2")){
				uSex = "女";
			}else{
				uSex = "其他";
			}
		}else{
			uSex = "其他";
		}
		return uSex;
	}

	public void setuSex(String uSex) {
		this.uSex = uSex;
	}

	public String getuEMail() {
		return uEMail;
	}

	public void setuEMail(String uEMail) {
		this.uEMail = uEMail;
	}

	@Override  
    public String toString() {  
        return "User [uid=" + uid + ", loginName=" + loginName  + " , nickName="+ nickName
                + ", passWord=" + passWord + ", uEMail=" + uEMail  
                + "]";  
    }

	public List<SysMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SysMenu> menus) {
		this.menus = menus;
	}

	public Integer getuType() {
		return uType;
	}

	public void setuType(Integer uType) {
		this.uType = uType;
	}

	public Integer getHavaMsg() {
		return havaMsg;
	}

	public void setHavaMsg(Integer havaMsg) {
		this.havaMsg = havaMsg;
	}  

    public String getMobilphone() {
		return mobilphone;
	}

	public void setMobilphone(String mobilphone) {
		this.mobilphone = mobilphone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitudex() {
		return longitudex;
	}

	public void setLongitudex(String longitudex) {
		this.longitudex = longitudex;
	}

	public String getLatitudey() {
		return latitudey;
	}

	public void setLatitudey(String latitudey) {
		this.latitudey = latitudey;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}  