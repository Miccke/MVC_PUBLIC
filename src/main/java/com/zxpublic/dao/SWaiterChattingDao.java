package com.zxpublic.dao;

import com.zxpublic.vo.SWaiterChatting;

/**
 * 咨询信息映射类
 * Class Name: SWaiterChattingDao.java
 * Modifications:   
 * @author Miccke  DateTime 2017年9月7日 下午7:38:39    
 * @version 1.0
 */
public interface SWaiterChattingDao {
	/**
	 * 记录咨询信息
	 * @author Miccke  DateTime 2017年9月7日 下午7:39:01
	 * @param sWaiterChatting
	 * @return
	 */
	public int insert(SWaiterChatting sWaiterChatting);
}
