package com.zxpublic.vo;

public class SWaiterChatting {
    private Long id;

    private String userName;

    private Long userPhone;

    private String chattingContent;

    private String openId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public String getChattingContent() {
        return chattingContent;
    }

    public void setChattingContent(String chattingContent) {
        this.chattingContent = chattingContent == null ? null : chattingContent.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }
}
