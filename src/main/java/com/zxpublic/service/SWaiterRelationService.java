package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.SWaiterRelation;

/**
 * ������Ա��ϵ     �ӿ���
 * @author penggl
 * @date   2017��8��12��10:57:25
 */
public interface SWaiterRelationService {
	/**
	 * �����ϵ����
	 * @param sw
	 * @return
	 */
	public int insert(SWaiterRelation sw);
	/**
	 * ɾ����ϵ����
	 * @param sw
	 * @return
	 */
	public int deleteByPrimaryKey(Long id);
	/**
	 * ����������ɾ��
	 * @param sw
	 * @return
	 */
	public int deleteByWaiter(SWaiterRelation sw);
	
	/**
	 * ��ѯ����ID�͸������Ƽ���
	 * @param sw
	 * @return
	 */
	public List<SWaiterRelation> getListBySwaiter(SWaiterRelation sw);
}  