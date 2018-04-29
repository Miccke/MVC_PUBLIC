package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.SWaiterRelationDao;
import com.zxpublic.service.SWaiterRelationService;
import com.zxpublic.vo.SWaiterRelation;

/**
 * 服务人员关系  实现类
 * @author penggl
 * @date   2017年8月12日10:57:14
 */
@Service  
public class SWaiterRelationServiceImpl implements SWaiterRelationService{

	@Autowired
	private SWaiterRelationDao sWaiterRelationService;
	/**
	 * 插入关系数据
	 * @param sw
	 * @return
	 */
	public int insert(SWaiterRelation sw){
		return sWaiterRelationService.insert(sw);
	}
	/**
	 * 删除关系数据
	 * @param sw
	 * @return
	 */
	public int deleteByPrimaryKey(Long id){
		return sWaiterRelationService.deleteByPrimaryKey(id);
	}
	/**
	 * 多条件过滤删除
	 * @param sw
	 * @return
	 */
	public int deleteByWaiter(SWaiterRelation sw){
		return sWaiterRelationService.deleteByWaiter(sw);
	}

	/**
	 * 查询附件ID和附件名称集合
	 * @param sw
	 * @return
	 */
	public List<SWaiterRelation> getListBySwaiter(SWaiterRelation sw){
		return sWaiterRelationService.getListBySwaiter(sw);
	}
}