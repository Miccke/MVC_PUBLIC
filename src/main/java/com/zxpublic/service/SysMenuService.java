package com.zxpublic.service;

import java.util.List;

import com.zxpublic.vo.SysMenu;

public interface SysMenuService {

	public List<SysMenu> getMunuByUserId(String uid);
}
