package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.SWaiterOrderInfo;
import com.zxpublic.vo.ServeInfo;

/**
 * 
 * @ClassName: SWaiterOrderInfoService 
 * @Description: TODO(服务订单接口类) 
 * @author CW5128 Miccke 
 * @date 2017年8月14日 上午10:10:13 
 * @version V1.0
 */
public interface SWaiterOrderInfoService {  
	/**
	 * 
	 * @Description: TODO(生成服务订单) 
	 * @param swInfo
	 * @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	public int insert(SWaiterOrderInfo swInfo);
	
	/**
	 * 按日期区间获取不同类型服务的订单数量
	 * @param order
	 * @return
	 */
	public List<SWaiterOrderInfo> getIntervalNum(SWaiterOrderInfo order);
	/**
	 * 取不同类型服务的订单数量
	 * @param order
	 * @return
	 */
	public List<SWaiterOrderInfo> getAllOrderNum(SWaiterOrderInfo order);
	
	/**
	 * 获取异常订单的区间数量和总数量
	 * @param order
	 * @return
	 */
	public SWaiterOrderInfo getAbnormalOrderNum(SWaiterOrderInfo order);
	/**
	 * 传入日期字符串，获取该日期的订单总数量
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(String dateStr);
	

	/**
	 * 查询订单集合
	 * @param serve
	 * @return
	 */
	public List<SWaiterOrderInfo> queryOrderList(SWaiterOrderInfo info);
	/**
	 * 查询订单总数量
	 * @param serve
	 * @return
	 */
	public int queryOrderCount(SWaiterOrderInfo info);

	/**
	 * 
	 * @Description: TODO(查未完成的服务订单) 
	 * @param openid
	 * @return    设定文件 
	 * @return List<ServeInfo>    返回类型 
	 * @throws
	 */
	public List<SWaiterOrderInfo> selectByStateNoEnd(String openid);
	/**
	 * 
	 * @Description: TODO(查完成的服务订单) 
	 * @param openId
	 * @return    设定文件 
	 * @return List<SWaiterOrderInfo>    返回类型 
	 * @throws
	 */
	public List<SWaiterOrderInfo> selectByState(String openId);
	/**
	 * 
	 * @Description: TODO(查订单中最大的编号) 
	 * @return    设定文件 
	 * @return SWaiterOrderInfo    返回类型 
	 * @throws
	 */
	public String selectMaxNum();
	/**
	 * 更新订单状态
	 * @param SWaiterOrderInfo
	 * @return
	 */
	public int updateServe(SWaiterOrderInfo info);

}  