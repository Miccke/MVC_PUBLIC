package com.zxpublic.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zxpublic.service.SWaiterChattingService;
import com.zxpublic.util.ShortMessageUtil;
import com.zxpublic.util.UrlUtil;
import com.zxpublic.vo.SWaiterChatting;

/**
 * ��ѯ��Ϣ������
 * Class Name: SWaiterChattingController.java
 * Modifications:   
 * @author Miccke  DateTime 2017��9��7�� ����7:41:45    
 * @version 1.0
 */
@Controller  
@RequestMapping("/chatting")  
public class SWaiterChattingController {

	Log logger= LogFactory.getLog(SWaiterChattingController.class);
	
	@Autowired  
    private SWaiterChattingService sWaiterChattingService; 
	/**
	 * ��¼��ѯ��¼
	 * @author Miccke  DateTime 2017��9��7�� ����7:55:47
	 * @param request
	 * @param out
	 * @param sw
	 */
    @RequestMapping(value="/insert")
    public void insert(HttpServletRequest request,PrintWriter out,SWaiterChatting sw){
    	try {
			String userName = request.getParameter("userName");
			String userPhone = request.getParameter("userPhone");
			String chattingContent = request.getParameter("chattingContent");
			String openId = request.getParameter("openId");
			
			sw.setChattingContent(chattingContent);
			sw.setOpenId(openId);
			sw.setUserName(userName);
			sw.setUserPhone(Long.parseLong(userPhone));    	
			
			sWaiterChattingService.insert(sw);
			String mobilphone = "13554563506";
			
			StringBuffer url = new StringBuffer();
			url.append("http://green.58yhkj.com/sms.aspx?action=send&userid=393&account=cdyhtz34&password=cdyhtz34888&mobile=");
			url.append(mobilphone);
			url.append("&content=");
			url.append(UrlUtil.getURLEncoderString("�ͻ�����Ϊ��"+userName+",��ϵ��ʽ:"+userPhone+"��ѯ����Ϊ��"+chattingContent)+",�뾡�촦��лл");
			url.append(UrlUtil.getURLEncoderString("��������������"));
			url.append("&sendTime=&extno=");		
			ShortMessageUtil.httpRequest(url.toString());
			
			out.print(1);
			out.flush();
			out.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.print(0);
			out.flush();
			out.close();
		}
		
    }
      
 
}
