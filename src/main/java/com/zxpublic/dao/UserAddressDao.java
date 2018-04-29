package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.UserAddress;

public interface UserAddressDao {
	/**
	 * 新增收货人地址
	 * @author Miccke  DateTime 2017年8月29日 下午7:30:19
	 * @param record
	 * @return
	 */
	public int insert(UserAddress userAddress);
	/**
	 * 用户提交订单时的地址
	 * @author Miccke  DateTime 2017年9月1日 上午9:30:07
	 * @param openId
	 * @param addId
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