package com.zxpublic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxpublic.dao.AttachmentDao;
import com.zxpublic.service.AttachmentService;
import com.zxpublic.vo.Attachment;


@Service
public class AttachmentServiceImpl implements AttachmentService{
	@Autowired  
	private AttachmentDao attachmentDao;
	
	public int insertAttachment(Attachment atta) {
		return attachmentDao.insertAttachment(atta);
	}
	
	public Attachment get(Long attachId){
		return attachmentDao.get(attachId);
	}
	
}
