package com.zxpublic.service;

import java.util.List;
import java.util.Map;

import com.zxpublic.vo.Page;
import com.zxpublic.vo.User;

/** 
 * 功能概要：UserService接口类 
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
	 * 通过ID获取人员信息
	 * @param id
	 * @return
	 */
	public User get(Long id);
	
	public List<User> selectByUtype1();
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public int updatepwd(User user);
	
	/**
	 * 更新个人信息
	 * @param user
	 * @return
	 */
	public int updateMsg(User user);
	/**
	 * 新增商家（用戶）
	 * @author Miccke  DateTime 2017年8月23日 上午10:36:01
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
  
}  