package com.zxpublic.vo;

public class JsapiTicket {
	/**
	 * ��Чʱ��
	 */
	private int expiresIn;
	/**
	 * js����Ʊ��
	 */
	private String ticket;
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getTicket() {
		return null==ticket?"":ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
}
