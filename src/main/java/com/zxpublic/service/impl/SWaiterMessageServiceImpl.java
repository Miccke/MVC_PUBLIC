package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.SWaiterMessageDao;
import com.zxpublic.service.SWaiterMessageService;
import com.zxpublic.vo.SWaiterMessage;

/**
 * ������Ա��Ϣ  ʵ����
 * @author penggl
 * @date   2017��8��12��10:56:58
 */
@Service  
public class SWaiterMessageServiceImpl implements SWaiterMessageService{


	@Autowired  
	private SWaiterMessageDao msgDao;
	 
	/**
	 * ���������Ա��Ϣ
	 * @param msg
	 * @return
	 */
	public int insertWaiter(SWaiterMessage msg){
		return msgDao.insertWaiter(msg);
	}
	/**
	 * ͨ��ID��ȡ������Ա��Ϣ
	 * @param id
	 * @return
	 */
	public SWaiterMessage get(Long id){
		return msgDao.get(id);
	}
	
	/**
	 * ����������ȡ������Ա��Ϣ����
	 * @param msg
	 * @return
	 */
	public List<SWaiterMessage> getMsgList(SWaiterMessage msg){
		return msgDao.getMsgList(msg);
	}

	/**
	 * ���·�����Ա��Ϣ
	 * @param msg
	 * @return
	 */
	public int updateWaiter(SWaiterMessage msg){
		return msgDao.updateWaiter(msg);
	}

	/**
	 * ��ȡ��¼�����ܺ�
	 * @param msg
	 * @return
	 */
	public int getMsgListCount(SWaiterMessage msg){
		return msgDao.getMsgListCount(msg);
	}

	
	/**
	 * ͨ��IDɾ��������Ա��Ϣ����ɾ����
	 * @param msg
	 * @return
	 */
	public int deleteMsgById(SWaiterMessage msg){
		return msgDao.deleteMsgById(msg);
	}
	
	@Autowired
	private SWaiterMessageDao sWaiterMessageDao;

	/**
	 * ����type������Ӧ��waiterlist
	 * @param waiterType
	 * @return
	 */
	public List<SWaiterMessage> getSWaiterByType(Integer waiterType) {
		return sWaiterMessageDao.getSWaiterByType(waiterType);
	}
	
	/**
	 * ��waiterNo������Ӧ��waiter��Ϣ
	 * @param waiterNo
	 * @return
	 */
	public SWaiterMessage getSWaiterByNo1(String waiterNo) {
		return sWaiterMessageDao.getSWaiterByNo1(waiterNo);
	}
	/**
	 * ��waiterNo������Ӧ��waiter��Ϣ
	 * @param waiterNo
	 * @return
	 */
	public SWaiterMessage getSWaiterByNo2(String waiterNo) {
		return sWaiterMessageDao.getSWaiterByNo2(waiterNo);
	}

	/**
	 * @Description: TODO(������Ӧ��waiterlist) 
	 * @param sw
	 * @return    �趨�ļ� 
	 * @return List<SWaiterMessage>    �������� 
	 * @throws
	 */
	public List<SWaiterMessage> getSWaiterByType1(SWaiterMessage sw) {
		return sWaiterMessageDao.getSWaiterByType1(sw);
	}
	/**
	 * ģ����ѯ
	 * @author Miccke  DateTime 2017��9��7�� ����4:24:10
	 * @param inputValue
	 * @return
	 */
	public List<SWaiterMessage> getLikeList(SWaiterMessage sw){
		return sWaiterMessageDao.getLikeList(sw);
		
	}
}  