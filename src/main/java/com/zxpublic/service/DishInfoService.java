package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.DishInfo;

/** 
 * 功能概要：DishInfoService接口类 
 * @author Administrator
 *
 */
public interface DishInfoService {  
    /**
     * 查商家所有菜品
     * @author Miccke  DateTime 2017年9月5日 上午10:41:17
     * @param store_id
     * @return
     */
	public List<DishInfo> selectByStoreId(Integer storeId,Integer dishtypeId);
	/**
	 * 查单个菜品信息
	 * @author Miccke  DateTime 2017年9月5日 上午10:41:34
	 * @param dishId
	 * @return
	 */
    public DishInfo selectByPrimaryKey(Integer dishId);
    
    /**
     * 获取所有的菜品
     * @author Miccke  DateTime 2017年8月24日 下午6:10:56
     * @return
     */
	public List<DishInfo> getAll(DishInfo dishInfo);

	/**
	 * 对菜品进行计数
	 * @author Miccke  DateTime 2017年8月24日 下午6:24:00
	 * @return
	 */
	public int countDish(DishInfo dishInfo);
	
	/**
	 * 新增菜品
	 * @author Miccke  DateTime 2017年8月25日 上午9:44:08
	 * @param dishInfo
	 * @return
	 */
	public int insertDish(DishInfo dishInfo);
	
	/**
	 * 删除菜品（写入状态0）
	 * @author Miccke  DateTime 2017年8月25日 下午2:58:45
	 * @param dishId
	 * @return
	 */
	public int deleteDish(Integer dishId);
	
}  