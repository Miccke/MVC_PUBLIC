package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.DishInfoDao;
import com.zxpublic.service.DishInfoService;
import com.zxpublic.vo.DishInfo;

/** 
 * 功能概要：DishTypeService实现类 
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
     * 获取所有的菜品
     * @author Miccke  DateTime 2017年8月24日 下午6:10:56
     * @return
     */
	public List<DishInfo> getAll(DishInfo dishInfo){
		return dishInfoDao.getAll(dishInfo);	
	}
	/**
	 * 对菜品进行计数
	 * @author Miccke  DateTime 2017年8月24日 下午6:24:00
	 * @return
	 */
	public int countDish(DishInfo dishInfo){
		return dishInfoDao.countDish(dishInfo);
	}

	/**
	 * 新增菜品
	 * @author Miccke  DateTime 2017年8月25日 上午9:44:08
	 * @param dishInfo
	 * @return
	 */
	public int insertDish(DishInfo dishInfo){
		return dishInfoDao.insertDish(dishInfo);		
	}
	/**
	 * 删除菜品（写入状态0）
	 * @author Miccke  DateTime 2017年8月25日 下午2:58:45
	 * @param dishId
	 * @return
	 */
	public int deleteDish(Integer dishId){
		return dishInfoDao.deleteDish(dishId);
		
	}
}  