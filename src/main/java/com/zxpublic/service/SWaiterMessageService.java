package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.SWaiterMessage;

/**
 * ������Ա��Ϣ   �ӿ���
 * @author penggl
 * @date   2017��8��12��10:57:25
 */
public interface SWaiterMessageService {
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
	 * 
	* @Description: ����waiterType������Ӧ��waiterlist
	* @param @param waiterType
	* @return List<SWaiterMessage>    �������� 
	* @throws
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