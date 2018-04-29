package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.UserAddress;

public interface UserAddressDao {
	/**
	 * �����ջ��˵�ַ
	 * @author Miccke  DateTime 2017��8��29�� ����7:30:19
	 * @param record
	 * @return
	 */
	public int insert(UserAddress userAddress);
	/**
	 * �û��ύ����ʱ�ĵ�ַ
	 * @author Miccke  DateTime 2017��9��1�� ����9:30:07
	 * @param openId
	 * @param addId
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