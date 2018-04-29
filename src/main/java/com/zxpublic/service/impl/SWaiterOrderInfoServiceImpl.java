package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.SWaiterOrderInfoDao;
import com.zxpublic.service.SWaiterOrderInfoService;
import com.zxpublic.vo.SWaiterOrderInfo;

/**
 * 
 * @ClassName: SWaiterOrderInfoServiceImpl 
 * @Description: TODO(服务订单实现类) 
 * @author CW5128 Miccke 
 * @date 2017年8月14日 上午10:11:32 
 * @version V1.0
 */
@Service  
public class SWaiterOrderInfoServiceImpl implements SWaiterOrderInfoService{

	@Autowired
	private SWaiterOrderInfoDao sWaiterOrderInfoDao;

	/**
	 * 生成服务订单
	 * @param swInfo
	 * @return
	 */
	public int insert(SWaiterOrderInfo swInfo) {
		return sWaiterOrderInfoDao.insert(swInfo);
	}
	/**
	 * 按日期区间获取不同类型服务的订单数量
	 * @param serve
	 * @return
	 */
	public List<SWaiterOrderInfo> getIntervalNum(SWaiterOrderInfo order){
		return sWaiterOrderInfoDao.getIntervalNum(order);
	}
	/**
	 * 取不同类型服务的订单数量
	 * @param serve
	 * @return
	 */
	public List<SWaiterOrderInfo> getAllOrderNum(SWaiterOrderInfo order){
		return sWaiterOrderInfoDao.getAllOrderNum(order);
	}
	/**
	 * 获取异常订单的区间数量和总数量
	 * @param serve
	 * @return
	 */
	public SWaiterOrderInfo getAbnormalOrderNum(SWaiterOrderInfo order){
		return sWaiterOrderInfoDao.getAbnormalOrderNum(order);
	}
	/**
	 * 传入日期字符串，获取该日期的订单总数量
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(String dateStr){
		return sWaiterOrderInfoDao.getDayNum(dateStr);
	}

	/**
	 * 查询订单集合
	 * @param serve
	 * @return
	 */
	public List<SWaiterOrderInfo> queryOrderList(SWaiterOrderInfo info){
		return sWaiterOrderInfoDao.queryOrderList(info);
	}
	/**
	 * 查询订单总数量
	 * @param serve
	 * @return
	 */
	public int queryOrderCount(SWaiterOrderInfo info){
		return sWaiterOrderInfoDao.queryOrderCount(info);
	}

	/**
	 * 查未完成的服务订单
	 * @param openid
	 * @return
	 */
	public List<SWaiterOrderInfo> selectByStateNoEnd(String openid) {
		return sWaiterOrderInfoDao.selectByStateNoEnd(openid);
	}
	/**
	 * 查完成的服务订单
	 * @param openid
	 * @return
	 */
	public List<SWaiterOrderInfo> selectByState(String openId) {
		// TODO Auto-generated method stub
		return sWaiterOrderInfoDao.selectByState(openId);
	}
	public String selectMaxNum() {
		return sWaiterOrderInfoDao.selectMaxNum();
	}

	/**
	 * 更新订单状态
	 * @param SWaiterOrderInfo
	 * @return
	 */
	public int updateServe(SWaiterOrderInfo info){
		return sWaiterOrderInfoDao.updateServe(info);
	}
}  