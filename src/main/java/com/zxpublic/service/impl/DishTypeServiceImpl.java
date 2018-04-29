package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.DishTypeDao;
import com.zxpublic.service.DishTypeService;
import com.zxpublic.vo.DishType;

/** 
 * ���ܸ�Ҫ��DishTypeServiceʵ���� 
 * @author penggl
 *
 */
@Service  
public class DishTypeServiceImpl implements DishTypeService{  
    @Autowired  
    private DishTypeDao dishTypeDao;

	public List<DishType> selectByStoreId(Integer storeId) {
		return dishTypeDao.selectByStoreId(storeId);
	}

	/**
	 * ��ȡ���в�Ʒ����
	 * @author Miccke  DateTime 2017��8��23�� ����5:05:01
	 * @return
	 */
	public List<DishType> getAll() {
		// TODO Auto-generated method stub
		return dishTypeDao.getAll();
	}
 
	/**
	 * ������Ʒ����
	 * @author Miccke  DateTime 2017��8��24�� ����1:52:10
	 * @param dishType
	 * @return
	 */
	public int insertDishType(DishType dishType){
		return dishTypeDao.insertDishType(dishType);
	}
	/**
	 * ɾ����Ʒ���ͣ�д��״̬0��
	 * @author Miccke  DateTime 2017��8��25�� ����2:58:45
	 * @param dishtypeId
	 * @return
	 */
	public int deleteDishType(Integer dishtypeId){
		return dishTypeDao.deleteDishType(dishtypeId);
	}
	/**
	 * ��ҳ��ȡ��Ʒ����
	 * @author Miccke  DateTime 2017��9��15�� ����4:38:50
	 * @param dishType
	 * @return
	 */
	public List<DishType> getAllByPage(DishType dishType){
		return dishTypeDao.getAllByPage(dishType);
	}

	/**
	 * ͳ�Ʋ�Ʒ��������
	 * @author Miccke  DateTime 2017��9��15�� ����4:50:10
	 * @return
	 */
	public int countDishType(){
		return dishTypeDao.countDishType();
	}
}  