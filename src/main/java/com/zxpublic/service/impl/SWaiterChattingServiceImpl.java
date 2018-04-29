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
 * ��ѯʵ����
 * Class Name: SWaiterChattingServiceImpl.java
 * Modifications:   
 * @author Miccke  DateTime 2017��9��7�� ����7:36:46    
 * @version 1.0
 */
@Service  
public class SWaiterChattingServiceImpl implements SWaiterChattingService{  
    @Autowired  
    private SWaiterChattingDao sWaiterChattingdao;

    /**
	 * ��¼��ѯ��Ϣ
	 * @author Miccke  DateTime 2017��9��7�� ����7:39:01
	 * @param sWaiterChatting
	 * @return
	 */
	public int insert(SWaiterChatting sWaiterChatting) {
		// TODO Auto-generated method stub
		return sWaiterChattingdao.insert(sWaiterChatting);
	}

}  