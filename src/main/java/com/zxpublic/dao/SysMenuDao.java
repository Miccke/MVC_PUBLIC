package com.zxpublic.dao;

import java.util.List;

import com.zxpublic.vo.SysMenu;

public interface SysMenuDao {

	/**
	 * ����userID��ѯ��Ӧ�Ĳ˵�����
	 * @param sysMunu
	 * @return
	 */
	public List<SysMenu> getMunuByUserId(String uid);
}
