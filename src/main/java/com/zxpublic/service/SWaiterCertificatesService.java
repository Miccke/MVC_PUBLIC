package com.zxpublic.service;

import com.zxpublic.vo.SWaiterCertificates;


/**
 * 证件类型    接口类
 * @author penggl
 * @date   2017年8月12日10:57:25
 */
public interface SWaiterCertificatesService {  

	/**
	 * 
	 * @Description: TODO(根据waiterId获取证书) 
	 * @return    设定文件 
	 * @return SWaiterCertificates    返回类型 
	 * @throws
	 */
	public SWaiterCertificates getCertificatesByWaiterId(String waiterNo);
	
}  