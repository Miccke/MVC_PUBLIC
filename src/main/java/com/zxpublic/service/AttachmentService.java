package com.zxpublic.service;

import com.zxpublic.vo.Attachment;

public interface AttachmentService {
	
	/**
	 * 插入附件信息
	 * @param atta
	 * @return
	 */
	public int insertAttachment(Attachment atta);
	
	/**
	 * 依据ID，获取Attachment对象
	 * @param attaId
	 * @return
	 */
	public Attachment get(Long attachId);
	
}
