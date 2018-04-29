package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.DishType;

/** 
 * ���ܸ�Ҫ��StoreInfoService�ӿ��� 
 * @author Administrator
 *
 */
public interface DishTypeService {  

	public List<DishType> selectByStoreId(Integer storeId);
	
	/**
	 * ��ȡ���в�Ʒ����
	 * @author Miccke  DateTime 2017��8��23�� ����5:05:01
	 * @return
	 */
	public List<DishType> getAll();
	
	/**
	 * ������Ʒ����
	 * @author Miccke  DateTime 2017��8��24�� ����1:52:10
	 * @param dishType
	 * @return
	 */
	public int insertDishType(DishType dishType);

	/**
	 * ɾ����Ʒ���ͣ�д��״̬0��
	 * @author Miccke  DateTime 2017��8��25�� ����2:58:45
	 * @param dishtypeId
	 * @return
	 */
	public int deleteDishType(Integer dishtypeId);
	
	/**
	 * ��ҳ��ȡ��Ʒ����
	 * @author Miccke  DateTime 2017��9��15�� ����4:38:50
	 * @param dishType
	 * @return
	 */
	public List<DishType> getAllByPage(DishType dishType);
	
	/**
	 * ͳ�Ʋ�Ʒ��������
	 * @author Miccke  DateTime 2017��9��15�� ����4:50:10
	 * @return
	 */
	public int countDishType();
	
}  