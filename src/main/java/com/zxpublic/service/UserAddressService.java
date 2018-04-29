package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.UserAddress;

/**
 * 收货人地址接口类
 * Class Name: UserAddressService.java
 * Modifications:   
 * @author Miccke  DateTime 2017年8月29日 下午7:29:47    
 * @version 1.0
 */
public interface UserAddressService {
	/**
	 * 新增收货人地址
	 * @author Miccke  DateTime 2017年8月29日 下午7:30:19
	 * @param record
	 * @return
	 */
	public int insert(UserAddress userAddress);
	/**
	 * 查询默认地址
	 * @author Miccke  DateTime 2017年8月29日 下午7:32:02
	 * @param userAddress
	 * @return
	 */
	public UserAddress getUserAddress(UserAddress userAddress);
	/**
	 * 查询用户所有地址
	 * @author Miccke  DateTime 2017年8月31日 下午3:45:34
	 * @param openId
	 * @return
	 */
	public List<UserAddress> listAll(String openId);
	/**
	 * 更新用户所选地址
	 * @author Miccke  DateTime 2017年9月1日 上午9:02:10
	 * @param userAddress
	 * @return
	 */
	public int update(UserAddress userAddress);
}