package com.zxpublic.service;

import com.zxpublic.vo.Attachment;

public interface AttachmentService {
	
	/**
	 * ���븽����Ϣ
	 * @param atta
	 * @return
	 */
	public int insertAttachment(Attachment atta);
	
	/**
	 * ����ID����ȡAttachment����
	 * @param attaId
	 * @return
	 */
	public Attachment get(Long attachId);
	
}
