package com.zxpublic.vo;

import java.util.List;


/** 
 *  Userӳ���� 
 * @author Administrator
 *
 */
public class User extends Page{  
    private Integer uid;
    //�˺�
    private String loginName;
    //����
    private String passWord;
    //�ǳ�
    private String nickName;
    //����
    private String uAge;
    //�Ա�  1:�У�2��Ů��0������
    private String uSex;
    //����
    private String uEMail;
    //�û����� ��1��ƽ̨��Ա��2���̼ң�3����ͨ�û���
    private Integer uType;
    //�Ƿ�����Ϣ  1���У�0����
    private Integer havaMsg;
    
    //ͷ��ID
    private String imageUrl;
    
    private List<SysMenu> menus;
    //�ƶ��绰
    private String mobilphone;
    //����
    private String telephone;
	//��ַ
    private String address;
    //����
    private String longitudex;
    //γ��
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
				uSex = "��";
			}else if(uSex.equals("2")){
				uSex = "Ů";
			}else{
				uSex = "����";
			}
		}else{
			uSex = "����";
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