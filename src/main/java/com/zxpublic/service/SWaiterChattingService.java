package com.zxpublic.service;

import com.zxpublic.vo.SWaiterChatting;

/**
 * 咨询信息接口类
 * Class Name: SWaiterChattingService.java
 * Modifications:   
 * @author Miccke  DateTime 2017年9月7日 下午7:37:15    
 * @version 1.0
 */
public interface SWaiterChattingService {
	/**
	 * 记录咨询信息
	 * @author Miccke  DateTime 2017年9月7日 下午7:39:01
	 * @param sWaiterChatting
	 * @return
	 */
	public int insert(SWaiterChatting sWaiterChatting);
}  