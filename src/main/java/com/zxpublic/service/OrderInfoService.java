package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.OrderInfo;
/**
 * ������Ϣ�ӿ���
 * @author CW5128
 *
 */
public interface OrderInfoService {  
	
	public int insertOrder(OrderInfo of);
	
	/**
	 * ��ѯ��������
	 * @param order
	 * @return
	 */
	public List<OrderInfo> queryOrder(OrderInfo order);
	/**
	 * ��ѯ��������
	 * @param order
	 * @return
	 */
	public int queryOrderCount(OrderInfo order);
	/**
	 * ��ѯ����
	 * @param order
	 * @return
	 */
	public OrderInfo getOrder(OrderInfo order);
	/**
	 * ���¶���״̬
	 * @param orader
	 * @return
	 */
	public int updateOrder(OrderInfo orader);
	/**
	 * ��ҳ��������
	 * @author Miccke  DateTime 2017��9��13�� ����7:30:57
	 * @param orderInfo
	 * @return
	 */
	public List<OrderInfo> selectByState(OrderInfo orderInfo);
	/**
	 * ƽ̨��ѯ�Ƶ궩������
	 * @param order
	 * @return
	 */
	public List<OrderInfo> hotelQueryOrder(OrderInfo order);

	/**
	 * ��ȡ����Ķ���������������
	 * @param serve
	 * @return
	 */
	public OrderInfo getAllOrderNum(OrderInfo serve);
	/**
	 * ��ȡ�쳣����������������������
	 * @param serve
	 * @return
	 */
	public OrderInfo getAbnormalOrderNum(OrderInfo info);
	/**
	 * ���������ַ�������ȡ�����ڵĶ���������
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(OrderInfo info);
	
	public List<OrderInfo> selectByOrderNum(String orderNum);
	/**
	 * �������Ѿ��ͳ��ĵ���û���ֶ������Ķ���
	 */
	public List<OrderInfo> selectByStateAndSendTime();
	
    public int updateByOrderNum(OrderInfo of);
    /**
     * 
     * @return
     */
    public List<OrderInfo> selectByState3(String openid);
    /**
     * 
     * @Description: TODO(�鵱ǰ���Ķ������) 
     * @return    �趨�ļ� 
     * @return String    �������� 
     * @throws
     */
    public String selectMaxNum();
}                                                    