package com.zxpublic.vo;

public class WeiXinSingInfo {
	private String openid;//	�û���Ψһ��ʶ
	private String nickname;//	�û��ǳ�
	private int sex;//	�û����Ա�ֵΪ1ʱ�����ԣ�ֵΪ2ʱ��Ů�ԣ�ֵΪ0ʱ��δ֪
	private String province;//	�û�����������д��ʡ��
	private String city;//	��ͨ�û�����������д�ĳ���
	private String country;//	���ң����й�ΪCN
	private String headimgurl;//	�û�ͷ�����һ����ֵ����������ͷ���С����0��46��64��96��132��ֵ��ѡ��0����640*640������ͷ�񣩣��û�û��ͷ��ʱ����Ϊ�ա����û�����ͷ��ԭ��ͷ��URL��ʧЧ��
	private String privilege;//	�û���Ȩ��Ϣ��json ���飬��΢���ֿ��û�Ϊ��chinaunicom��
	private String language;
	
	/*{
	    "openid": "ort3e1XdNZ9tkdtup92pgUW0m3D8",
	    "nickname": "Miccke",
	    "sex": 1,
	    "language": "zh_CN",
	    "city": "����",
	    "province": "����",
	    "country": "�й�",
	    "headimgurl": "http://wx.qlogo.cn/mmopen/7zCZdnCEqwEjQm7tCzrzz6dadSbficrQg8SYMloGVMRlbXrV6MgbVn05km60IXribicJtGkXYLabmxQ1iaQOLWgEtH0wBHn9B5uy/0",
	    "privilege": []
	}*/
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	@Override
	public String toString() {
		return "WeiXinSingInfo [openid=" + openid + ", nickname=" + nickname
				+ ", sex=" + sex + ", province=" + province + ", city=" + city
				+ ", country=" + country + ", headimgurl=" + headimgurl
				+ ", privilege=" + privilege + ", language=" + language + "]";
	}

	
}
