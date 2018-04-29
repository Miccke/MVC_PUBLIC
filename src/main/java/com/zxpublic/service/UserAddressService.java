package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.UserAddress;

/**
 * �ջ��˵�ַ�ӿ���
 * Class Name: UserAddressService.java
 * Modifications:   
 * @author Miccke  DateTime 2017��8��29�� ����7:29:47    
 * @version 1.0
 */
public interface UserAddressService {
	/**
	 * �����ջ��˵�ַ
	 * @author Miccke  DateTime 2017��8��29�� ����7:30:19
	 * @param record
	 * @return
	 */
	public int insert(UserAddress userAddress);
	/**
	 * ��ѯĬ�ϵ�ַ
	 * @author Miccke  DateTime 2017��8��29�� ����7:32:02
	 * @param userAddress
	 * @return
	 */
	public UserAddress getUserAddress(UserAddress userAddress);
	/**
	 * ��ѯ�û����е�ַ
	 * @author Miccke  DateTime 2017��8��31�� ����3:45:34
	 * @param openId
	 * @return
	 */
	public List<UserAddress> listAll(String openId);
	/**
	 * �����û���ѡ��ַ
	 * @author Miccke  DateTime 2017��9��1�� ����9:02:10
	 * @param userAddress
	 * @return
	 */
	public int update(UserAddress userAddress);
}