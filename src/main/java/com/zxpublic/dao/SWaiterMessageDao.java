package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.SWaiterMessage;


public interface SWaiterMessageDao {

	/**
	 * 插入服务人员信息
	 * @param msg
	 * @return
	 */
	public int insertWaiter(SWaiterMessage msg);
	/**
	 * 通过ID获取服务人员信息
	 * @param id
	 * @return
	 */
	public SWaiterMessage get(Long id);
	
	/**
	 * 依据条件获取服务人员信息集合
	 * @param msg
	 * @return
	 */
	public List<SWaiterMessage> getMsgList(SWaiterMessage msg);
	
	/**
	 * 更新服务人员信息
	 * @param msg
	 * @return
	 */
	public int updateWaiter(SWaiterMessage msg);
	
	/**
	 * 获取记录条数总和
	 * @param msg
	 * @return
	 */
	public int getMsgListCount(SWaiterMessage msg);
	
	/**
	 * 通过ID删除服务人员信息（软删除）
	 * @param msg
	 * @return
	 */
	public int deleteMsgById(SWaiterMessage msg);
	/**
	 * 根据type返回相应的waiterlist
	 * @param waiterType
	 * @return
	 */
	public List<SWaiterMessage> getSWaiterByType(Integer waiterType);
	/**
	 * 
	 * @Description: TODO(根据waiterNo返回相应的waiter信息) 
	 * @param waiterNo
	 * @return    设定文件 
	 * @return SWaiterMessage    返回类型 
	 * @throws
	 */
	public SWaiterMessage getSWaiterByNo1(String waiterNo);
	/**
	 * 
	 * @Description: TODO(根据waiterNo返回相应的waiter信息) 
	 * @param waiterNo
	 * @return    设定文件 
	 * @return SWaiterMessage    返回类型 
	 * @throws
	 */
	public SWaiterMessage getSWaiterByNo2(String waiterNo);
	
	/**
	 * 
	 * @Description: TODO(返回相应的waiterlist) 
	 * @param sw
	 * @return    设定文件 
	 * @return List<SWaiterMessage>    返回类型 
	 * @throws
	 */
	public List<SWaiterMessage> getSWaiterByType1(SWaiterMessage sw);
	/**
	 * 模糊查询
	 * @author Miccke  DateTime 2017年9月7日 下午4:24:10
	 * @param inputValue
	 * @return
	 */
	public List<SWaiterMessage> getLikeList(SWaiterMessage sw);
}
