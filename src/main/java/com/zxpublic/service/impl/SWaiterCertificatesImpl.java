package com.zxpublic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.SWaiterCertificatesDao;
import com.zxpublic.service.SWaiterCertificatesService;
import com.zxpublic.vo.SWaiterCertificates;

/**
 * 证件类型  实现类
 * @author penggl
 * @date   2017年8月12日10:56:33
 */
@Service  
public class SWaiterCertificatesImpl implements SWaiterCertificatesService{
	
	@Autowired
	private SWaiterCertificatesDao sWaiterCertificatesDao;
	
	/**
	 * 根据根据waiterId获取证书
	 * @param 
	 * @return
	 */
	public SWaiterCertificates getCertificatesByWaiterId(String waiterNo) {
		return sWaiterCertificatesDao.getCertificatesByWaiterId(waiterNo);
	}
}  