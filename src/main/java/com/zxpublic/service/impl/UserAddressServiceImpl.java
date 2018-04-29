package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.UserAddressDao;
import com.zxpublic.service.UserAddressService;
import com.zxpublic.vo.UserAddress;

/** 
 * 功能概要：UserAddressService实现类 
 * @author Administrator
 *
 */
@Service  
public class UserAddressServiceImpl implements UserAddressService{

	@Autowired
	private UserAddressDao userAddressDao;
	/**
	 * 新增用户地址
	 * @author Miccke  DateTime 2017年8月29日 下午7:32:02
	 * @param userAddress
	 * @return
	 */
	public int insert(UserAddress userAddress) {
		return userAddressDao.insert(userAddress);
	}
	/**
	 * 用户提交订单时的地址
	 * @author Miccke  DateTime 2017年9月1日 上午9:29:16
	 * @param openId
	 * @return
	 */
	public UserAddress getUserAddress(UserAddress userAddress) {
		return userAddressDao.getUserAddress(userAddress);
	}
	/**
	 * 查询用户所有地址
	 * @author Miccke  DateTime 2017年8月31日 下午3:45:34
	 * @param openId
	 * @return
	 */
	public List<UserAddress> listAll(String openId){
		return userAddressDao.listAll(openId);
		
	}
	/**
	 * 更新用户所选地址
	 * @author Miccke  DateTime 2017年9月1日 上午9:02:10
	 * @param userAddress
	 * @return
	 */
	public int update(UserAddress userAddress){
		return userAddressDao.update(userAddress);
	}
}  