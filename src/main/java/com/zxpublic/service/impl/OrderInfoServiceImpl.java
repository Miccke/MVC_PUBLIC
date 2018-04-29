package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.OrderInfoDao;
import com.zxpublic.service.OrderInfoService;
import com.zxpublic.vo.OrderInfo;

/**
 * ��������OrderInfoService �ӿ�ʵ����
 * @author CW5128
 *
 */
@Service  
public class OrderInfoServiceImpl implements OrderInfoService{
	 @Autowired  
	private OrderInfoDao orderInfoDao;
	 
	public int insertOrder(OrderInfo of) {
		return orderInfoDao.insertOrder(of);
	}  

	public List<OrderInfo> queryOrder(OrderInfo order){
		List<OrderInfo> list = orderInfoDao.queryOrderList(order);
		return list;
	}
	public int queryOrderCount(OrderInfo order){
		return orderInfoDao.queryOrderListCount(order);
	}
	public int updateOrder(OrderInfo orader){
		return orderInfoDao.updateOrder(orader);
	}
	
	/**
	 * ��ҳ��������
	 * @author Miccke  DateTime 2017��9��13�� ����7:32:21
	 * @param out
	 * @param orderInfo
	 */
	public List<OrderInfo> selectByState(OrderInfo orderInfo) {
		List<OrderInfo> oilist = orderInfoDao.selectByState(orderInfo);
		return oilist ;
	}

	public OrderInfo getOrder(OrderInfo order) {
		return orderInfoDao.getOrder(order);
	}  
	
	public List<OrderInfo> hotelQueryOrder(OrderInfo order){
		return null;//orderInfoDao.hotelQueryOrder(order);
	}

	/**
	 * ��ȡ����Ķ���������������
	 * @param serve
	 * @return
	 */
	public OrderInfo getAllOrderNum(OrderInfo info){
		return orderInfoDao.getAllOrderNum(info);
	}
	/**
	 * ��ȡ�쳣����������������������
	 * @param serve
	 * @return
	 */
	public OrderInfo getAbnormalOrderNum(OrderInfo info){
		return orderInfoDao.getAbnormalOrderNum(info);
	}
	/**
	 * ���������ַ�������ȡ�����ڵĶ���������
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(OrderInfo order){
		return orderInfoDao.getDayNum(order);
	}

	public List<OrderInfo> selectByOrderNum(String orderNum) {
		return orderInfoDao.selectByOrderNum(orderNum);
	}

	public int updateByOrderNum(OrderInfo of) {
		return orderInfoDao.updateByOrderNum(of);
	}

	/**
	 * �������Ѿ��ͳ��ĵ���û���ֶ������Ķ���
	 */
	public List<OrderInfo> selectByStateAndSendTime() {
		return orderInfoDao.selectByStateAndSendTime();
	}

	public List<OrderInfo> selectByState3(String openid) {
		return orderInfoDao.selectByState3(openid);
	}
	public String selectMaxNum() {
		return orderInfoDao.selectMaxNum();
	}

}  