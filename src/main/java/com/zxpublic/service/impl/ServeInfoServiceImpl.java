package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.ServeInfoDao;
import com.zxpublic.service.ServeInfoService;
import com.zxpublic.vo.ServeInfo;

/** 
 * ���ܸ�Ҫ��ServeInfoServiceʵ���� 
 * @author Administrator
 *
 */
@Service  
public class ServeInfoServiceImpl implements ServeInfoService{  
	
    @Autowired  
    private ServeInfoDao serveInfoDao;

	public int insertServe(ServeInfo si) {
		// TODO Auto-generated method stub
		return serveInfoDao.insertServe(si);
	}  
	public int queryOrderCount(ServeInfo serve){
		return serveInfoDao.queryOrderCount(serve);
	}
	public List<ServeInfo> queryOrderList(ServeInfo serve) {
		return serveInfoDao.queryOrderList(serve);
	}
	public int updateServe(ServeInfo serve){
		return serveInfoDao.updateServe(serve);
	}
	/**
	 * �����������ȡ��ͬ���ͷ���Ķ�������
	 * @param serve
	 * @return
	 */
	public List<ServeInfo> getIntervalNum(ServeInfo serve){
		return serveInfoDao.getIntervalNum(serve);
	}
	/**
	 * ȡ��ͬ���ͷ���Ķ�������
	 * @param serve
	 * @return
	 */
	public List<ServeInfo> getAllOrderNum(ServeInfo serve){
		return serveInfoDao.getAllOrderNum(serve);
	}
	/**
	 * ��ȡ�쳣����������������������
	 * @param serve
	 * @return
	 */
	public ServeInfo getAbnormalOrderNum(ServeInfo serve){
		return serveInfoDao.getAbnormalOrderNum(serve);
	}

	/**
	 * ���������ַ�������ȡ�����ڵĶ���������
	 * @param dateStr
	 * @return
	 */
	public int getDayNum(String dateStr){
		return serveInfoDao.getDayNum(dateStr);
	}
	
	public List<ServeInfo> selectByState(String openId) {
		return serveInfoDao.selectByState(openId);
	}
	public List<ServeInfo> selectByStateNoEnd(String openId) {
		// TODO Auto-generated method stub
		return serveInfoDao.selectByStateNoEnd(openId);
	}  
  
}  