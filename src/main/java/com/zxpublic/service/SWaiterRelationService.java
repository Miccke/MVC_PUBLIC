package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.SWaiterRelation;

/**
 * 服务人员关系     接口类
 * @author penggl
 * @date   2017年8月12日10:57:25
 */
public interface SWaiterRelationService {
	/**
	 * 插入关系数据
	 * @param sw
	 * @return
	 */
	public int insert(SWaiterRelation sw);
	/**
	 * 删除关系数据
	 * @param sw
	 * @return
	 */
	public int deleteByPrimaryKey(Long id);
	/**
	 * 多条件过滤删除
	 * @param sw
	 * @return
	 */
	public int deleteByWaiter(SWaiterRelation sw);
	
	/**
	 * 查询附件ID和附件名称集合
	 * @param sw
	 * @return
	 */
	public List<SWaiterRelation> getListBySwaiter(SWaiterRelation sw);
}  