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
	 * ��ҳ��ѯ�û��б���
	 * @param pageUser
	 * @return
	 */
	public List<User> queryUserList(User user);
	/**
	 * ��ҳ��ѯ�û��б�������
	 * @param loginName
	 * @return
	 */
	public int queryUserListCount(User user);
	public User selectByUid(Integer uid);
	

	/**
	 * ����
	 * @return
	 */
	public List<User> selectByUtype1();

	/**
	 * �޸��û�����
	 * @param user
	 * @return
	 */
	public int updatepwd(User user);

	/**
	 * ͨ��ID��ȡ��Ա��Ϣ
	 * @param id
	 * @return
	 */
	public User get(Long id);

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