package com.zxpublic.vo;

public class SignPackage {
	/**
	 * ʱ���
	 */
	private String timestamp;
	/**
	 * ����ַ���
	 */
	private String nonceStr;
	/**
	 * ǩ���ַ���
	 */
	private String signature;
	/**
	 * ����ǩ����url
	 */
	private String url;
	/**
	 * jsƱ��
	 */
	private JsapiTicket jsapiTicket;
	/**
	 * ��ҳ��Ȩaccess_token
	 */
	private String access_token;
	/**
	 * �û�ˢ��access_token 
	 */
	private String refresh_token;
	/**
	 * ���ں�id
	 */
	private String appid;
	/**
	 * �û�openid
	 */
	private String openid;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public JsapiTicket getJsapiTicket() {
		return jsapiTicket;
	}
	public void setJsapiTicket(JsapiTicket jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
