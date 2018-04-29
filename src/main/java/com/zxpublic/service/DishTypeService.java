package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.DishType;

/** 
 * 功能概要：StoreInfoService接口类 
 * @author Administrator
 *
 */
public interface DishTypeService {  

	public List<DishType> selectByStoreId(Integer storeId);
	
	/**
	 * 获取所有菜品类型
	 * @author Miccke  DateTime 2017年8月23日 下午5:05:01
	 * @return
	 */
	public List<DishType> getAll();
	
	/**
	 * 新增菜品种类
	 * @author Miccke  DateTime 2017年8月24日 下午1:52:10
	 * @param dishType
	 * @return
	 */
	public int insertDishType(DishType dishType);

	/**
	 * 删除菜品类型（写入状态0）
	 * @author Miccke  DateTime 2017年8月25日 下午2:58:45
	 * @param dishtypeId
	 * @return
	 */
	public int deleteDishType(Integer dishtypeId);
	
	/**
	 * 分页获取菜品类型
	 * @author Miccke  DateTime 2017年9月15日 下午4:38:50
	 * @param dishType
	 * @return
	 */
	public List<DishType> getAllByPage(DishType dishType);
	
	/**
	 * 统计菜品类型条数
	 * @author Miccke  DateTime 2017年9月15日 下午4:50:10
	 * @return
	 */
	public int countDishType();
	
}  