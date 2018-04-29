package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.SWaiterOrderInfo;
import com.zxpublic.vo.ServeInfo;

/**
 * 
 * @ClassName: SWaiterOrderInfoService 
 * @Description: TODO(���񶩵��ӿ���) 
 * @author CW5128 Miccke 
 * @date 2017��8��14�� ����10:10:13 
 * @version V1.0
 */
public interface SWaiterOrderInfoService {  
	/**
	 * 
	 * @Description: TODO(���ɷ��񶩵�) 
	 * @param swInfo
	 * @return    �趨�ļ� 
	 * @return int    �������� 
	 * @throws
	 */
	public int insert(SWaiterOrderInfo swInfo);
	
	/**
	 * �����������ȡ��ͬ���ͷ���Ķ�������
	 * @param order
	 * @return
	 */
	public List<SWaiterOrderInfo> getIntervalNum(SWaiterOrderInfo order);
	/**
	 * ȡ��ͬ���ͷ���Ķ�������
	 * @param order
	 * @return
	 */
	public List<SWaiterOrderInfo> getAllOrderNum(SWaiterOrderInfo order);
	
	/**
	 * ��ȡ�쳣����������������������
	 * @param order
	 * @return
	 */
	public SWaiterOrderInfo getAbnormalOrderNum(SWaiterOrderInfo order);
	/**
	 * ���������ַ�������ȡ�����ڵĶ���������
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(String dateStr);
	

	/**
	 * ��ѯ��������
	 * @param serve
	 * @return
	 */
	public List<SWaiterOrderInfo> queryOrderList(SWaiterOrderInfo info);
	/**
	 * ��ѯ����������
	 * @param serve
	 * @return
	 */
	public int queryOrderCount(SWaiterOrderInfo info);

	/**
	 * 
	 * @Description: TODO(��δ��ɵķ��񶩵�) 
	 * @param openid
	 * @return    �趨�ļ� 
	 * @return List<ServeInfo>    �������� 
	 * @throws
	 */
	public List<SWaiterOrderInfo> selectByStateNoEnd(String openid);
	/**
	 * 
	 * @Description: TODO(����ɵķ��񶩵�) 
	 * @param openId
	 * @return    �趨�ļ� 
	 * @return List<SWaiterOrderInfo>    �������� 
	 * @throws
	 */
	public List<SWaiterOrderInfo> selectByState(String openId);
	/**
	 * 
	 * @Description: TODO(�鶩�������ı��) 
	 * @return    �趨�ļ� 
	 * @return SWaiterOrderInfo    �������� 
	 * @throws
	 */
	public String selectMaxNum();
	/**
	 * ���¶���״̬
	 * @param SWaiterOrderInfo
	 * @return
	 */
	public int updateServe(SWaiterOrderInfo info);

}  