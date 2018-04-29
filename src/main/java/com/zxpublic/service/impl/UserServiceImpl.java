package com.zxpublic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.UserDao;
import com.zxpublic.service.UserService;
import com.zxpublic.vo.Page;
import com.zxpublic.vo.User;

/** 
 * ���ܸ�Ҫ��UserServiceʵ���� 
 * @author Administrator
 *
 */
@Service  
public class UserServiceImpl implements UserService{  
	
    @Autowired  
    private UserDao userDao;  
  
    public User selectUserById(Integer userId) {  
        return userDao.selectUserById(userId);  
          
    }  
  
	public List<User> listAll() {
		return userDao.listAll();
	}

	public User login(User user){
		return userDao.login(user);
	}

	public List<User> queryUser(Page page,Map<String, Object> map){
		return null;
	}
	public List<User> queryUser(User user){
		List<User> list = userDao.queryUserList(user);
		return list;
	}
	public int queryUserCount(User user){
		return userDao.queryUserListCount(user);
	}
	public User selectByUid(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.selectByUid(uid);
	}
	public List<User> selectByUtype1() {
		// TODO Auto-generated method stub
		return userDao.selectByUtype1();
	}

	/**
	 * �޸��û�����
	 * @param user
	 * @return
	 */
	public int updatepwd(User user){
		return userDao.updatepwd(user);
	};

	/**
	 * ͨ��ID��ȡ��Ա��Ϣ
	 * @param id
	 * @return
	 */
	public User get(Long id){
		return userDao.get(id);
	}
	/**
	 * ���¸�����Ϣ
	 * @param user
	 * @return
	 */
	public int updateMsg(User user){
		return userDao.updateMsg(user);
	}

	/**
	 * �����̼ң��Ñ���
	 * @author Miccke  DateTime 2017��8��23�� ����10:36:01
	 * @param user
	 * @return
	 */
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}
}  