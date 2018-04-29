package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.User;

public interface UserDao {  
    /** 
     *  
     */  
    public User selectUserById(Integer userId);  
    
	public List<User> listAll();
	
	public int addRecord(User p);
	
	public int saveOrUpdate(User p);
	
	public User getDetail(String id);
	
	public User login(User user);
	/**
	 * 分页查询用户列表集合
	 * @param pageUser
	 * @return
	 */
	public List<User> queryUserList(User user);
	/**
	 * 分页查询用户列表总数量
	 * @param loginName
	 * @return
	 */
	public int queryUserListCount(User user);
	public User selectByUid(Integer uid);
	

	/**
	 * 新增
	 * @return
	 */
	public List<User> selectByUtype1();

	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public int updatepwd(User user);

	/**
	 * 通过ID获取人员信息
	 * @param id
	 * @return
	 */
	public User get(Long id);

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