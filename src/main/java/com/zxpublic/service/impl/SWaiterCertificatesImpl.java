package com.zxpublic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.SWaiterCertificatesDao;
import com.zxpublic.service.SWaiterCertificatesService;
import com.zxpublic.vo.SWaiterCertificates;

/**
 * ֤������  ʵ����
 * @author penggl
 * @date   2017��8��12��10:56:33
 */
@Service  
public class SWaiterCertificatesImpl implements SWaiterCertificatesService{
	
	@Autowired
	private SWaiterCertificatesDao sWaiterCertificatesDao;
	
	/**
	 * ���ݸ���waiterId��ȡ֤��
	 * @param 
	 * @return
	 */
	public SWaiterCertificates getCertificatesByWaiterId(String waiterNo) {
		return sWaiterCertificatesDao.getCertificatesByWaiterId(waiterNo);
	}
}  