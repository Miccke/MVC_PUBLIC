package com.zxpublic.service;

import java.util.List;
import java.util.Map;

import com.zxpublic.vo.Page;
import com.zxpublic.vo.User;

/** 
 * ���ܸ�Ҫ��UserService�ӿ��� 
 * @author Administrator
 *
 */
public interface UserService {
User selectUserById(Integer userId);  
    
	public List<User> listAll();
	public User login(User user);
	public List<User> queryUser(Page page,Map<String, Object> map);
	public List<User> queryUser(User user);
	public int queryUserCount(User user);
	public User selectByUid(Integer uid);
	/**
	 * ͨ��ID��ȡ��Ա��Ϣ
	 * @param id
	 * @return
	 */
	public User get(Long id);
	
	public List<User> selectByUtype1();
	/**
	 * �޸��û�����
	 * @param user
	 * @return
	 */
	public int updatepwd(User user);
	
	/**
	 * ���¸�����Ϣ
	 * @param user
	 * @return
	 */
	public int updateMsg(User user);
	/**
	 * �����̼ң��Ñ���
	 * @author Miccke  DateTime 2017��8��23�� ����10:36:01
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
  
}  