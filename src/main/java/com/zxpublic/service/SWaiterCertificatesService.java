package com.zxpublic.service;

import com.zxpublic.vo.SWaiterCertificates;


/**
 * ֤������    �ӿ���
 * @author penggl
 * @date   2017��8��12��10:57:25
 */
public interface SWaiterCertificatesService {  

	/**
	 * 
	 * @Description: TODO(����waiterId��ȡ֤��) 
	 * @return    �趨�ļ� 
	 * @return SWaiterCertificates    �������� 
	 * @throws
	 */
	public SWaiterCertificates getCertificatesByWaiterId(String waiterNo);
	
}  