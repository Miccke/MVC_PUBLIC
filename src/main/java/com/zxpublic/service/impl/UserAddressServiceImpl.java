package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.UserAddressDao;
import com.zxpublic.service.UserAddressService;
import com.zxpublic.vo.UserAddress;

/** 
 * ���ܸ�Ҫ��UserAddressServiceʵ���� 
 * @author Administrator
 *
 */
@Service  
public class UserAddressServiceImpl implements UserAddressService{

	@Autowired
	private UserAddressDao userAddressDao;
	/**
	 * �����û���ַ
	 * @author Miccke  DateTime 2017��8��29�� ����7:32:02
	 * @param userAddress
	 * @return
	 */
	public int insert(UserAddress userAddress) {
		return userAddressDao.insert(userAddress);
	}
	/**
	 * �û��ύ����ʱ�ĵ�ַ
	 * @author Miccke  DateTime 2017��9��1�� ����9:29:16
	 * @param openId
	 * @return
	 */
	public UserAddress getUserAddress(UserAddress userAddress) {
		return userAddressDao.getUserAddress(userAddress);
	}
	/**
	 * ��ѯ�û����е�ַ
	 * @author Miccke  DateTime 2017��8��31�� ����3:45:34
	 * @param openId
	 * @return
	 */
	public List<UserAddress> listAll(String openId){
		return userAddressDao.listAll(openId);
		
	}
	/**
	 * �����û���ѡ��ַ
	 * @author Miccke  DateTime 2017��9��1�� ����9:02:10
	 * @param userAddress
	 * @return
	 */
	public int update(UserAddress userAddress){
		return userAddressDao.update(userAddress);
	}
}  