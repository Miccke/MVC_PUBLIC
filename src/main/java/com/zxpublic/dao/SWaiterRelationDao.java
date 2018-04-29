package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.SWaiterRelation;


public interface SWaiterRelationDao {
	
	/**
	 * �����ϵ����
	 * @param sw
	 * @return
	 */
	public int insert(SWaiterRelation sw);

	/**
	 * ͨ��IDɾ������
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
