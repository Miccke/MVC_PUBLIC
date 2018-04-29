package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.OrderInfoDao;
import com.zxpublic.service.OrderInfoService;
import com.zxpublic.vo.OrderInfo;

/**
 * 订单管理OrderInfoService 接口实现类
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
	 * 分页订单数据
	 * @author Miccke  DateTime 2017年9月13日 下午7:32:21
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
	 * 获取区间的订单数量和总数量
	 * @param serve
	 * @return
	 */
	public OrderInfo getAllOrderNum(OrderInfo info){
		return orderInfoDao.getAllOrderNum(info);
	}
	/**
	 * 获取异常订单的区间数量和总数量
	 * @param serve
	 * @return
	 */
	public OrderInfo getAbnormalOrderNum(OrderInfo info){
		return orderInfoDao.getAbnormalOrderNum(info);
	}
	/**
	 * 传入日期字符串，获取该日期的订单总数量
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
	 * 查所有已经送出的但还没有手动结束的订单
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