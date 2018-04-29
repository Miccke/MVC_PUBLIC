package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.SWaiterRelation;


public interface SWaiterRelationDao {
	
	/**
	 * 插入关系数据
	 * @param sw
	 * @return
	 */
	public int insert(SWaiterRelation sw);

	/**
	 * 通过ID删除数据
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
