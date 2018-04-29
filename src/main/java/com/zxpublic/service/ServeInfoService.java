package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.ServeInfo;

/** 
 * 功能概要：ServeInfoService接口类 
 * @author Administrator
 *
 */
public interface ServeInfoService {  
    
	public int insertServe(ServeInfo si);

	/**
	 * 查询订单集合
	 * @param serve
	 * @return
	 */
	public List<ServeInfo> queryOrderList(ServeInfo serve);
	/**
	 * 查询订单总数量
	 * @param serve
	 * @return
	 */
	public int queryOrderCount(ServeInfo serve);
	
	/**
	 * 更新订单状态
	 * @param serve
	 * @return
	 */
	public int updateServe(ServeInfo serve);
	
	/**
	 * 按日期区间获取不同类型服务的订单数量
	 * @param serve
	 * @return
	 */
	public List<ServeInfo> getIntervalNum(ServeInfo serve);
	/**
	 * 取不同类型服务的订单数量
	 * @param serve
	 * @return
	 */
	public List<ServeInfo> getAllOrderNum(ServeInfo serve);
	/**
	 * 获取异常订单的区间数量和总数量
	 * @param serve
	 * @return
	 */
	public ServeInfo getAbnormalOrderNum(ServeInfo serve);

	/**
	 * 传入日期字符串，获取该日期的订单总数量
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(String dateStr);
	public List<ServeInfo> selectByState(String openId);
	/**
	 * 查未完成的服务订单
	 */
	public List<ServeInfo> selectByStateNoEnd(String openId);
}  