package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.DishTypeDao;
import com.zxpublic.dao.SWaiterChattingDao;
import com.zxpublic.service.DishTypeService;
import com.zxpublic.service.SWaiterChattingService;
import com.zxpublic.vo.DishType;
import com.zxpublic.vo.SWaiterChatting;

/** 
 * 咨询实现类
 * Class Name: SWaiterChattingServiceImpl.java
 * Modifications:   
 * @author Miccke  DateTime 2017年9月7日 下午7:36:46    
 * @version 1.0
 */
@Service  
public class SWaiterChattingServiceImpl implements SWaiterChattingService{  
    @Autowired  
    private SWaiterChattingDao sWaiterChattingdao;

    /**
	 * 记录咨询信息
	 * @author Miccke  DateTime 2017年9月7日 下午7:39:01
	 * @param sWaiterChatting
	 * @return
	 */
	public int insert(SWaiterChatting sWaiterChatting) {
		// TODO Auto-generated method stub
		return sWaiterChattingdao.insert(sWaiterChatting);
	}

}  