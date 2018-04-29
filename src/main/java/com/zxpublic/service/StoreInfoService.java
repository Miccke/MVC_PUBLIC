package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.StoreInfo;

/** 
 * 功能概要：StoreInfoService接口类 
 * @author Administrator
 *
 */
public interface StoreInfoService {  
    
	public List<StoreInfo> listAll();

	public StoreInfo selectByStoreId(Integer storeId);
	public StoreInfo selectByUid(Integer uid);

	/**
	 * 新增商家（用户）
	 * @author Miccke  DateTime 2017年8月23日 下午2:14:22
	 * @param storeInfo
	 * @return
	 */
	public int insertStore(StoreInfo storeInfo);
	/**
	 * 删除商家（用户）
	 * @author Miccke  DateTime 2017年8月25日 下午4:32:26
	 * @param storeId
	 * @return
	 */
	public int deleteStore(Integer storeId);
}  