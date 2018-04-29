package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.DishTypeDao;
import com.zxpublic.service.DishTypeService;
import com.zxpublic.vo.DishType;

/** 
 * 功能概要：DishTypeService实现类 
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
	 * 获取所有菜品类型
	 * @author Miccke  DateTime 2017年8月23日 下午5:05:01
	 * @return
	 */
	public List<DishType> getAll() {
		// TODO Auto-generated method stub
		return dishTypeDao.getAll();
	}
 
	/**
	 * 新增菜品种类
	 * @author Miccke  DateTime 2017年8月24日 下午1:52:10
	 * @param dishType
	 * @return
	 */
	public int insertDishType(DishType dishType){
		return dishTypeDao.insertDishType(dishType);
	}
	/**
	 * 删除菜品类型（写入状态0）
	 * @author Miccke  DateTime 2017年8月25日 下午2:58:45
	 * @param dishtypeId
	 * @return
	 */
	public int deleteDishType(Integer dishtypeId){
		return dishTypeDao.deleteDishType(dishtypeId);
	}
	/**
	 * 分页获取菜品类型
	 * @author Miccke  DateTime 2017年9月15日 下午4:38:50
	 * @param dishType
	 * @return
	 */
	public List<DishType> getAllByPage(DishType dishType){
		return dishTypeDao.getAllByPage(dishType);
	}

	/**
	 * 统计菜品类型条数
	 * @author Miccke  DateTime 2017年9月15日 下午4:50:10
	 * @return
	 */
	public int countDishType(){
		return dishTypeDao.countDishType();
	}
}  