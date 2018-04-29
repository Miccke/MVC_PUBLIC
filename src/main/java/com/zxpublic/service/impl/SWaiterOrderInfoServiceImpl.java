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
 * @Description: TODO(���񶩵�ʵ����) 
 * @author CW5128 Miccke 
 * @date 2017��8��14�� ����10:11:32 
 * @version V1.0
 */
@Service  
public class SWaiterOrderInfoServiceImpl implements SWaiterOrderInfoService{

	@Autowired
	private SWaiterOrderInfoDao sWaiterOrderInfoDao;

	/**
	 * ���ɷ��񶩵�
	 * @param swInfo
	 * @return
	 */
	public int insert(SWaiterOrderInfo swInfo) {
		return sWaiterOrderInfoDao.insert(swInfo);
	}
	/**
	 * �����������ȡ��ͬ���ͷ���Ķ�������
	 * @param serve
	 * @return
	 */
	public List<SWaiterOrderInfo> getIntervalNum(SWaiterOrderInfo order){
		return sWaiterOrderInfoDao.getIntervalNum(order);
	}
	/**
	 * ȡ��ͬ���ͷ���Ķ�������
	 * @param serve
	 * @return
	 */
	public List<SWaiterOrderInfo> getAllOrderNum(SWaiterOrderInfo order){
		return sWaiterOrderInfoDao.getAllOrderNum(order);
	}
	/**
	 * ��ȡ�쳣����������������������
	 * @param serve
	 * @return
	 */
	public SWaiterOrderInfo getAbnormalOrderNum(SWaiterOrderInfo order){
		return sWaiterOrderInfoDao.getAbnormalOrderNum(order);
	}
	/**
	 * ���������ַ�������ȡ�����ڵĶ���������
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(String dateStr){
		return sWaiterOrderInfoDao.getDayNum(dateStr);
	}

	/**
	 * ��ѯ��������
	 * @param serve
	 * @return
	 */
	public List<SWaiterOrderInfo> queryOrderList(SWaiterOrderInfo info){
		return sWaiterOrderInfoDao.queryOrderList(info);
	}
	/**
	 * ��ѯ����������
	 * @param serve
	 * @return
	 */
	public int queryOrderCount(SWaiterOrderInfo info){
		return sWaiterOrderInfoDao.queryOrderCount(info);
	}

	/**
	 * ��δ��ɵķ��񶩵�
	 * @param openid
	 * @return
	 */
	public List<SWaiterOrderInfo> selectByStateNoEnd(String openid) {
		return sWaiterOrderInfoDao.selectByStateNoEnd(openid);
	}
	/**
	 * ����ɵķ��񶩵�
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
	 * ���¶���״̬
	 * @param SWaiterOrderInfo
	 * @return
	 */
	public int updateServe(SWaiterOrderInfo info){
		return sWaiterOrderInfoDao.updateServe(info);
	}
}  