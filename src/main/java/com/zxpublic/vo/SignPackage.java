package com.zxpublic.vo;

public class SignPackage {
	/**
	 * 时间戳
	 */
	private String timestamp;
	/**
	 * 随机字符串
	 */
	private String nonceStr;
	/**
	 * 签名字符串
	 */
	private String signature;
	/**
	 * 参与签名的url
	 */
	private String url;
	/**
	 * js票据
	 */
	private JsapiTicket jsapiTicket;
	/**
	 * 网页授权access_token
	 */
	private String access_token;
	/**
	 * 用户刷新access_token 
	 */
	private String refresh_token;
	/**
	 * 公众号id
	 */
	private String appid;
	/**
	 * 用户openid
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
