package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.SWaiterMessage;


public interface SWaiterMessageDao {

	/**
	 * ���������Ա��Ϣ
	 * @param msg
	 * @return
	 */
	public int insertWaiter(SWaiterMessage msg);
	/**
	 * ͨ��ID��ȡ������Ա��Ϣ
	 * @param id
	 * @return
	 */
	public SWaiterMessage get(Long id);
	
	/**
	 * ����������ȡ������Ա��Ϣ����
	 * @param msg
	 * @return
	 */
	public List<SWaiterMessage> getMsgList(SWaiterMessage msg);
	
	/**
	 * ���·�����Ա��Ϣ
	 * @param msg
	 * @return
	 */
	public int updateWaiter(SWaiterMessage msg);
	
	/**
	 * ��ȡ��¼�����ܺ�
	 * @param msg
	 * @return
	 */
	public int getMsgListCount(SWaiterMessage msg);
	
	/**
	 * ͨ��IDɾ��������Ա��Ϣ����ɾ����
	 * @param msg
	 * @return
	 */
	public int deleteMsgById(SWaiterMessage msg);
	/**
	 * ����type������Ӧ��waiterlist
	 * @param waiterType
	 * @return
	 */
	public List<SWaiterMessage> getSWaiterByType(Integer waiterType);
	/**
	 * 
	 * @Description: TODO(����waiterNo������Ӧ��waiter��Ϣ) 
	 * @param waiterNo
	 * @return    �趨�ļ� 
	 * @return SWaiterMessage    �������� 
	 * @throws
	 */
	public SWaiterMessage getSWaiterByNo1(String waiterNo);
	/**
	 * 
	 * @Description: TODO(����waiterNo������Ӧ��waiter��Ϣ) 
	 * @param waiterNo
	 * @return    �趨�ļ� 
	 * @return SWaiterMessage    �������� 
	 * @throws
	 */
	public SWaiterMessage getSWaiterByNo2(String waiterNo);
	
	/**
	 * 
	 * @Description: TODO(������Ӧ��waiterlist) 
	 * @param sw
	 * @return    �趨�ļ� 
	 * @return List<SWaiterMessage>    �������� 
	 * @throws
	 */
	public List<SWaiterMessage> getSWaiterByType1(SWaiterMessage sw);
	/**
	 * ģ����ѯ
	 * @author Miccke  DateTime 2017��9��7�� ����4:24:10
	 * @param inputValue
	 * @return
	 */
	public List<SWaiterMessage> getLikeList(SWaiterMessage sw);
}
