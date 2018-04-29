package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.SysMenu;

public interface SysMenuDao {

	/**
	 * 依据userID查询对应的菜单集合
	 * @param sysMunu
	 * @return
	 */
	public List<SysMenu> getMunuByUserId(String uid);
}
