package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.OrderInfo;

/**
 * OrderIndoMapper.xml 映射类
 * @author CW5128
 *
 */
public interface OrderInfoDao {  
	public int insertOrder(OrderInfo of);

	/**
	 * 分页订单数据
	 * @author Miccke  DateTime 2017年9月13日 下午7:33:00
	 * @param orderInfo
	 * @return
	 */
	public List<OrderInfo> selectByState(OrderInfo orderInfo);
	
	public List<OrderInfo> selectByState2();
	/**
	 * 分页查询订单列表集合
	 * @param order
	 * @return
	 */
	public List<OrderInfo> queryOrderList(OrderInfo order);
	public List<OrderInfo> selectByOrderNum(String orderNum);
	/**
	 * 分页查询订单列表总数量
	 * @param order
	 * @return
	 */
	public int queryOrderListCount(OrderInfo order);
    public int updateByOrderNum(OrderInfo of);
	/**
	 * 查所有已经送出的但还没有手动结束的订单
	 */
	public List<OrderInfo> selectByStateAndSendTime();
	
	/**
	 * 更新订单数据
	 * @param orader
	 * @return
	 */
	public int updateOrder(OrderInfo order);
	
	/**
	 * 查询订单明细
	 * @param order
	 * @return
	 */
	public OrderInfo getOrder(OrderInfo order);
	
	/**
	 * 平台查询酒店订单集合
	 * @param order
	 * @return
	 */
	//public List<OrderInfo> hotelQueryOrder(OrderInfo order);

	/**
	 * 获取区间的订单数量和总数量
	 * @param serve
	 * @return
	 */
	public OrderInfo getAllOrderNum(OrderInfo info);
	/**
	 * 获取异常订单的区间数量和总数量
	 * @param serve
	 * @return
	 */
	public OrderInfo getAbnormalOrderNum(OrderInfo info);
	/**
	 * 传入日期字符串，获取该日期的订单总数量
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(OrderInfo info);
	
	public List<OrderInfo> selectByState3(String openid);
	 /**
     * 
     * @Description: TODO(查当前最大的订单编号) 
     * @return    设定文件 
     * @return String    返回类型 
     * @throws
     */
    public String selectMaxNum();
}                                                  