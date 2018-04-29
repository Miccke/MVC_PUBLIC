package com.zxpublic.dao;

import com.zxpublic.vo.Attachment;

public interface AttachmentDao {

	/**
	 * 插入附件信息
	 * @param atta
	 * @return
	 */
	public int insertAttachment(Attachment atta);
	/**
	 * 依据ID获取attachment对象信息
	 * @param attaId
	 * @return
	 */
	public Attachment get(Long attachId);
	
}
