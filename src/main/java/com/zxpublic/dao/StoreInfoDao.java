package com.zxpublic.dao;

import java.util.List;




import com.zxpublic.vo.StoreInfo;

public interface StoreInfoDao {  
 
	public List<StoreInfo> listAll();
	public StoreInfo selectByStoreId(Integer storeId);

	public StoreInfo selectByUid(Integer uid);
	
	/**
	 * �����̼ң��û���
	 * @author Miccke  DateTime 2017��8��23�� ����2:14:22
	 * @param storeInfo
	 * @return
	 */
	public int insertStore(StoreInfo storeInfo);
	/**
	 * ɾ���̼ң��û���
	 * @author Miccke  DateTime 2017��8��25�� ����4:32:26
	 * @param storeId
	 * @return
	 */
	public int deleteStore(Integer storeId);
}