package com.zxpublic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.SysMenuDao;
import com.zxpublic.service.SysMenuService;
import com.zxpublic.vo.SysMenu;


@Service
public class SysMenuServiceImpl implements SysMenuService{

    @Autowired  
    private SysMenuDao menuDao; 
    
	public List<SysMenu> getMunuByUserId(String uid) {
		return menuDao.getMunuByUserId(uid);
	}

}
