package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.DishInfoDao;
import com.zxpublic.service.DishInfoService;
import com.zxpublic.vo.DishInfo;

/** 
 * ���ܸ�Ҫ��DishTypeServiceʵ���� 
 * @author Administrator
 *
 */
@Service  
public class DishInfoServiceImpl implements DishInfoService{  
	
    @Autowired  
    private DishInfoDao dishInfoDao;

    public List<DishInfo> selectByStoreId(Integer storeId,Integer dishtypeId) {
		return dishInfoDao.selectByStoreId(storeId,dishtypeId);
	}

	public DishInfo selectByPrimaryKey(Integer dishId) {
		// TODO Auto-generated method stub
		return dishInfoDao.selectByPrimaryKey(dishId);
	}
	  /**
     * ��ȡ���еĲ�Ʒ
     * @author Miccke  DateTime 2017��8��24�� ����6:10:56
     * @return
     */
	public List<DishInfo> getAll(DishInfo dishInfo){
		return dishInfoDao.getAll(dishInfo);	
	}
	/**
	 * �Բ�Ʒ���м���
	 * @author Miccke  DateTime 2017��8��24�� ����6:24:00
	 * @return
	 */
	public int countDish(DishInfo dishInfo){
		return dishInfoDao.countDish(dishInfo);
	}

	/**
	 * ������Ʒ
	 * @author Miccke  DateTime 2017��8��25�� ����9:44:08
	 * @param dishInfo
	 * @return
	 */
	public int insertDish(DishInfo dishInfo){
		return dishInfoDao.insertDish(dishInfo);		
	}
	/**
	 * ɾ����Ʒ��д��״̬0��
	 * @author Miccke  DateTime 2017��8��25�� ����2:58:45
	 * @param dishId
	 * @return
	 */
	public int deleteDish(Integer dishId){
		return dishInfoDao.deleteDish(dishId);
		
	}
}  