package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.DishInfo;

/** 
 * ���ܸ�Ҫ��DishInfoService�ӿ��� 
 * @author Administrator
 *
 */
public interface DishInfoService {  
    /**
     * ���̼����в�Ʒ
     * @author Miccke  DateTime 2017��9��5�� ����10:41:17
     * @param store_id
     * @return
     */
	public List<DishInfo> selectByStoreId(Integer storeId,Integer dishtypeId);
	/**
	 * �鵥����Ʒ��Ϣ
	 * @author Miccke  DateTime 2017��9��5�� ����10:41:34
	 * @param dishId
	 * @return
	 */
    public DishInfo selectByPrimaryKey(Integer dishId);
    
    /**
     * ��ȡ���еĲ�Ʒ
     * @author Miccke  DateTime 2017��8��24�� ����6:10:56
     * @return
     */
	public List<DishInfo> getAll(DishInfo dishInfo);

	/**
	 * �Բ�Ʒ���м���
	 * @author Miccke  DateTime 2017��8��24�� ����6:24:00
	 * @return
	 */
	public int countDish(DishInfo dishInfo);
	
	/**
	 * ������Ʒ
	 * @author Miccke  DateTime 2017��8��25�� ����9:44:08
	 * @param dishInfo
	 * @return
	 */
	public int insertDish(DishInfo dishInfo);
	
	/**
	 * ɾ����Ʒ��д��״̬0��
	 * @author Miccke  DateTime 2017��8��25�� ����2:58:45
	 * @param dishId
	 * @return
	 */
	public int deleteDish(Integer dishId);
	
}  