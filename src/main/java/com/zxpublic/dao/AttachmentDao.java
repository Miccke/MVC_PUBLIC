package com.zxpublic.dao;

import com.zxpublic.vo.Attachment;

public interface AttachmentDao {

	/**
	 * ���븽����Ϣ
	 * @param atta
	 * @return
	 */
	public int insertAttachment(Attachment atta);
	/**
	 * ����ID��ȡattachment������Ϣ
	 * @param attaId
	 * @return
	 */
	public Attachment get(Long attachId);
	
}
