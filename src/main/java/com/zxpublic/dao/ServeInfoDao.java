package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.ServeInfo;

public interface ServeInfoDao {  
 
	public int insertServe(ServeInfo si);

	/**
	 * ��ѯ��������
	 * @param serve
	 * @return
	 */
	public List<ServeInfo> queryOrderList(ServeInfo serve);
	/**
	 * ��ѯ����������
	 * @param serve
	 * @return
	 */
	public int queryOrderCount(ServeInfo serve);
	
	/**
	 * ���¶���״̬
	 * @param serve
	 * @return
	 */
	public int updateServe(ServeInfo serve);
	
	/**
	 * �����������ȡ��ͬ���ͷ���Ķ�������
	 * @param serve
	 * @return
	 */
	public List<ServeInfo> getIntervalNum(ServeInfo serve);
	/**
	 * ȡ��ͬ���ͷ���Ķ�������
	 * @param serve
	 * @return
	 */
	public List<ServeInfo> getAllOrderNum(ServeInfo serve);
	/**
	 * ��ȡ�쳣����������������������
	 * @param serve
	 * @return
	 */
	public ServeInfo getAbnormalOrderNum(ServeInfo serve);
	/**
	 * ���������ַ�������ȡ�����ڵĶ���������
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(String dateStr);
	public List<ServeInfo> selectByState(String openid);
	/**
	 * ��δ��ɵķ��񶩵�
	 */
	public List<ServeInfo> selectByStateNoEnd(String openid);
}