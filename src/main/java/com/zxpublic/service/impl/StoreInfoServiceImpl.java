package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.StoreInfoDao;
import com.zxpublic.service.StoreInfoService;
import com.zxpublic.vo.StoreInfo;

/** 
 * ���ܸ�Ҫ��StoreInfoServiceʵ���� 
 * @author Administrator
 *
 */
@Service  
public class StoreInfoServiceImpl implements StoreInfoService{  
	
    @Autowired  
    private StoreInfoDao storeInfoDao;

	public List<StoreInfo> listAll() {
		return storeInfoDao.listAll();
	}  

	public StoreInfo selectByStoreId(Integer storeId) {
		return storeInfoDao.selectByStoreId(storeId);
	}  
	public StoreInfo selectByUid(Integer uid){
		return storeInfoDao.selectByUid(uid);
	}
	/**
	 * �����̼ң��û���
	 * @author Miccke  DateTime 2017��8��23�� ����2:14:22
	 * @param storeInfo
	 * @return
	 */
	public int insertStore(StoreInfo storeInfo) {
		return storeInfoDao.insertStore(storeInfo);
	}
	/**
	 * ɾ���̼ң��û���
	 * @author Miccke  DateTime 2017��8��25�� ����4:32:26
	 * @param storeId
	 * @return
	 */
	public int deleteStore(Integer storeId){
		return storeInfoDao.deleteStore(storeId);
	}
}  