package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.OrderInfo;
/**
 * 订单信息接口类
 * @author CW5128
 *
 */
public interface OrderInfoService {  
	
	public int insertOrder(OrderInfo of);
	
	/**
	 * 查询订单集合
	 * @param order
	 * @return
	 */
	public List<OrderInfo> queryOrder(OrderInfo order);
	/**
	 * 查询订单数量
	 * @param order
	 * @return
	 */
	public int queryOrderCount(OrderInfo order);
	/**
	 * 查询订单
	 * @param order
	 * @return
	 */
	public OrderInfo getOrder(OrderInfo order);
	/**
	 * 更新订单状态
	 * @param orader
	 * @return
	 */
	public int updateOrder(OrderInfo orader);
	/**
	 * 分页订单数据
	 * @author Miccke  DateTime 2017年9月13日 下午7:30:57
	 * @param orderInfo
	 * @return
	 */
	public List<OrderInfo> selectByState(OrderInfo orderInfo);
	/**
	 * 平台查询酒店订单集合
	 * @param order
	 * @return
	 */
	public List<OrderInfo> hotelQueryOrder(OrderInfo order);

	/**
	 * 获取区间的订单数量和总数量
	 * @param serve
	 * @return
	 */
	public OrderInfo getAllOrderNum(OrderInfo serve);
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
	
	public List<OrderInfo> selectByOrderNum(String orderNum);
	/**
	 * 查所有已经送出的但还没有手动结束的订单
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
     * @Description: TODO(查当前最大的订单编号) 
     * @return    设定文件 
     * @return String    返回类型 
     * @throws
     */
    public String selectMaxNum();
}                                                    