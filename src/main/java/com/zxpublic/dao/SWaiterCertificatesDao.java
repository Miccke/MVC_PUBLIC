package com.zxpublic.dao;

import com.zxpublic.vo.SWaiterCertificates;


public interface SWaiterCertificatesDao {

	/**
	 * 
	 * @Description: TODO(����waiterId��ȡ֤��) 
	 * @return    �趨�ļ� 
	 * @return SWaiterCertificates    �������� 
	 * @throws
	 */
	public SWaiterCertificates getCertificatesByWaiterId(String waiterNo);
}
