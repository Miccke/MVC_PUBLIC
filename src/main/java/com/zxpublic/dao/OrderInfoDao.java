package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.OrderInfo;

/**
 * OrderIndoMapper.xml ӳ����
 * @author CW5128
 *
 */
public interface OrderInfoDao {  
	public int insertOrder(OrderInfo of);

	/**
	 * ��ҳ��������
	 * @author Miccke  DateTime 2017��9��13�� ����7:33:00
	 * @param orderInfo
	 * @return
	 */
	public List<OrderInfo> selectByState(OrderInfo orderInfo);
	
	public List<OrderInfo> selectByState2();
	/**
	 * ��ҳ��ѯ�����б���
	 * @param order
	 * @return
	 */
	public List<OrderInfo> queryOrderList(OrderInfo order);
	public List<OrderInfo> selectByOrderNum(String orderNum);
	/**
	 * ��ҳ��ѯ�����б�������
	 * @param order
	 * @return
	 */
	public int queryOrderListCount(OrderInfo order);
    public int updateByOrderNum(OrderInfo of);
	/**
	 * �������Ѿ��ͳ��ĵ���û���ֶ������Ķ���
	 */
	public List<OrderInfo> selectByStateAndSendTime();
	
	/**
	 * ���¶�������
	 * @param orader
	 * @return
	 */
	public int updateOrder(OrderInfo order);
	
	/**
	 * ��ѯ������ϸ
	 * @param order
	 * @return
	 */
	public OrderInfo getOrder(OrderInfo order);
	
	/**
	 * ƽ̨��ѯ�Ƶ궩������
	 * @param order
	 * @return
	 */
	//public List<OrderInfo> hotelQueryOrder(OrderInfo order);

	/**
	 * ��ȡ����Ķ���������������
	 * @param serve
	 * @return
	 */
	public OrderInfo getAllOrderNum(OrderInfo info);
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