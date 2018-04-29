package com.zxpublic.vo;

import java.util.Date;

public class SWaiterOrderInfo extends Page{
    private Long id;
    //服务类型 1月嫂，2催乳师，3陪护师
    private Integer waiterType;
    //用户姓名
    private String userName;
    //用户年龄
    private Integer userSex;
    //用户手机号
    private String userPhone;
    //用户微信对于公众号唯一标识ID
    private String openid;
    //选定的服务人员的Id 
    private String waiterno;
    //服务订单开始时间
    private Date startTime;
    //服务订单完成时间
    private Date finishTime;
    //服务订单状态
    private Integer serveState;
    //备注
    private String remark;
	//开始时间（用于时间范围查询）
	private Date sTime;
	//结束时间（用于时间范围查询）
	private Date eTime;
	//区间数量
	private Integer IntervalNumber;
	//区间数量
	private Integer allNumber;
    //订单编号
    private String orderNum;
    //到岗时间、预产期
    private Date dutyTime;
    //服务人员信息
    private SWaiterMessage sWaiterMessage;
    
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWaiterType() {
        return waiterType;
    }

    public void setWaiterType(Integer waiterType) {
        this.waiterType = waiterType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getWaiterno() {
        return waiterno;
    }

    public void setWaiterno(String waiterno) {
        this.waiterno = waiterno == null ? null : waiterno.trim();
    }
    
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getServeState() {
        return serveState;
    }

    public void setServeState(Integer serveState) {
        this.serveState = serveState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Date getsTime() {
		return sTime;
	}

	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public Integer getIntervalNumber() {
		return IntervalNumber;
	}

	public void setIntervalNumber(Integer intervalNumber) {
		IntervalNumber = intervalNumber;
	}

	public Integer getAllNumber() {
		return allNumber;
	}

	public void setAllNumber(Integer allNumber) {
		this.allNumber = allNumber;
	}

    public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
    public Date getDutyTime() {
        return dutyTime;
    }

    public void setDutyTime(Date dutyTime) {
        this.dutyTime = dutyTime;
    }
    
    public SWaiterMessage getsWaiterMessage() {
		return sWaiterMessage;
	}

	public void setsWaiterMessage(SWaiterMessage sWaiterMessage) {
		this.sWaiterMessage = sWaiterMessage;
	}

	@Override
	public String toString() {
		return "SWaiterOrderInfo [id=" + id + ", waiterType=" + waiterType
				+ ", userName=" + userName + ", userSex=" + userSex
				+ ", userPhone=" + userPhone + ", openid=" + openid
				+ ", waiterno=" + waiterno + ", startTime=" + startTime
				+ ", finishTime=" + finishTime + ", serveState=" + serveState
				+ ", remark=" + remark + ", sTime=" + sTime + ", eTime="
				+ eTime + ", IntervalNumber=" + IntervalNumber + ", allNumber="
				+ allNumber + ", orderNum=" + orderNum + ", dutyTime="
				+ dutyTime + ", sWaiterMessage=" + sWaiterMessage + "]";
	}
	
}