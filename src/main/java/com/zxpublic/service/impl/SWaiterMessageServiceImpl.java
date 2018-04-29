package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.SWaiterMessageDao;
import com.zxpublic.service.SWaiterMessageService;
import com.zxpublic.vo.SWaiterMessage;

/**
 * 服务人员信息  实现类
 * @author penggl
 * @date   2017年8月12日10:56:58
 */
@Service  
public class SWaiterMessageServiceImpl implements SWaiterMessageService{


	@Autowired  
	private SWaiterMessageDao msgDao;
	 
	/**
	 * 插入服务人员信息
	 * @param msg
	 * @return
	 */
	public int insertWaiter(SWaiterMessage msg){
		return msgDao.insertWaiter(msg);
	}
	/**
	 * 通过ID获取服务人员信息
	 * @param id
	 * @return
	 */
	public SWaiterMessage get(Long id){
		return msgDao.get(id);
	}
	
	/**
	 * 依据条件获取服务人员信息集合
	 * @param msg
	 * @return
	 */
	public List<SWaiterMessage> getMsgList(SWaiterMessage msg){
		return msgDao.getMsgList(msg);
	}

	/**
	 * 更新服务人员信息
	 * @param msg
	 * @return
	 */
	public int updateWaiter(SWaiterMessage msg){
		return msgDao.updateWaiter(msg);
	}

	/**
	 * 获取记录条数总和
	 * @param msg
	 * @return
	 */
	public int getMsgListCount(SWaiterMessage msg){
		return msgDao.getMsgListCount(msg);
	}

	
	/**
	 * 通过ID删除服务人员信息（软删除）
	 * @param msg
	 * @return
	 */
	public int deleteMsgById(SWaiterMessage msg){
		return msgDao.deleteMsgById(msg);
	}
	
	@Autowired
	private SWaiterMessageDao sWaiterMessageDao;

	/**
	 * 根据type返回相应的waiterlist
	 * @param waiterType
	 * @return
	 */
	public List<SWaiterMessage> getSWaiterByType(Integer waiterType) {
		return sWaiterMessageDao.getSWaiterByType(waiterType);
	}
	
	/**
	 * 据waiterNo返回相应的waiter信息
	 * @param waiterNo
	 * @return
	 */
	public SWaiterMessage getSWaiterByNo1(String waiterNo) {
		return sWaiterMessageDao.getSWaiterByNo1(waiterNo);
	}
	/**
	 * 据waiterNo返回相应的waiter信息
	 * @param waiterNo
	 * @return
	 */
	public SWaiterMessage getSWaiterByNo2(String waiterNo) {
		return sWaiterMessageDao.getSWaiterByNo2(waiterNo);
	}

	/**
	 * @Description: TODO(返回相应的waiterlist) 
	 * @param sw
	 * @return    设定文件 
	 * @return List<SWaiterMessage>    返回类型 
	 * @throws
	 */
	public List<SWaiterMessage> getSWaiterByType1(SWaiterMessage sw) {
		return sWaiterMessageDao.getSWaiterByType1(sw);
	}
	/**
	 * 模糊查询
	 * @author Miccke  DateTime 2017年9月7日 下午4:24:10
	 * @param inputValue
	 * @return
	 */
	public List<SWaiterMessage> getLikeList(SWaiterMessage sw){
		return sWaiterMessageDao.getLikeList(sw);
		
	}
}  