package com.zxpublic.vo;

/**
 * ������Ա��ϵ��
 * @author penggl
 * @date   2017��8��12��10:52:06
 */
public class SWaiterRelation {
	
	//ID������
    private Long id;
    //������ԱID
    private Long waiterId;
    //��ϵID
    private Long relationId;
    //��ϵ����    [��ϵ���ͣ�1��֤����tb_certificates_t��id����2��tb_attachment_t��id��]
    private Integer type;
    //��ϵID��Ӧ������
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