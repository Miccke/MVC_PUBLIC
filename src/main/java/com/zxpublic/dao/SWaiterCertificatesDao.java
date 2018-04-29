package com.zxpublic.dao;

import com.zxpublic.vo.SWaiterCertificates;


public interface SWaiterCertificatesDao {

	/**
	 * 
	 * @Description: TODO(根据waiterId获取证书) 
	 * @return    设定文件 
	 * @return SWaiterCertificates    返回类型 
	 * @throws
	 */
	public SWaiterCertificates getCertificatesByWaiterId(String waiterNo);
}
