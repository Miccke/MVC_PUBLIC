package com.zxpublic.vo;

/**
 * 服务人员关系类
 * @author penggl
 * @date   2017年8月12日10:52:06
 */
public class SWaiterRelation {
	
	//ID，主键
    private Long id;
    //服务人员ID
    private Long waiterId;
    //关系ID
    private Long relationId;
    //关系类型    [关系类型（1：证件【tb_certificates_t表id】，2：tb_attachment_t表id）]
    private Integer type;
    //关系ID对应的名称
    private String relationName;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Long waiterId) {
        this.waiterId = waiterId;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
}