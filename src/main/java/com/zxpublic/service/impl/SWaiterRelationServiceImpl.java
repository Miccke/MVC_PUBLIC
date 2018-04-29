package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.SWaiterRelationDao;
import com.zxpublic.service.SWaiterRelationService;
import com.zxpublic.vo.SWaiterRelation;

/**
 * ������Ա��ϵ  ʵ����
 * @author penggl
 * @date   2017��8��12��10:57:14
 */
@Service  
public class SWaiterRelationServiceImpl implements SWaiterRelationService{

	@Autowired
	private SWaiterRelationDao sWaiterRelationService;
	/**
	 * �����ϵ����
	 * @param sw
	 * @return
	 */
	public int insert(SWaiterRelation sw){
		return sWaiterRelationService.insert(sw);
	}
	/**
	 * ɾ����ϵ����
	 * @param sw
	 * @return
	 */
	public int deleteByPrimaryKey(Long id){
		return sWaiterRelationService.deleteByPrimaryKey(id);
	}
	/**
	 * ����������ɾ��
	 * @param sw
	 * @return
	 */
	public int deleteByWaiter(SWaiterRelation sw){
		return sWaiterRelationService.deleteByWaiter(sw);
	}

	/**
	 * ��ѯ����ID�͸������Ƽ���
	 * @param sw
	 * @return
	 */
	public List<SWaiterRelation> getListBySwaiter(SWaiterRelation sw){
		return sWaiterRelationService.getListBySwaiter(sw);
	}
}