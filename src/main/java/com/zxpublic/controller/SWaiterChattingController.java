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
 * 咨询信息控制器
 * Class Name: SWaiterChattingController.java
 * Modifications:   
 * @author Miccke  DateTime 2017年9月7日 下午7:41:45    
 * @version 1.0
 */
@Controller  
@RequestMapping("/chatting")  
public class SWaiterChattingController {

	Log logger= LogFactory.getLog(SWaiterChattingController.class);
	
	@Autowired  
    private SWaiterChattingService sWaiterChattingService; 
	/**
	 * 记录咨询记录
	 * @author Miccke  DateTime 2017年9月7日 下午7:55:47
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
			url.append(UrlUtil.getURLEncoderString("客户姓名为："+userName+",联系方式:"+userPhone+"咨询内容为："+chattingContent)+",请尽快处理，谢谢");
			url.append(UrlUtil.getURLEncoderString("【信伊健康服务】"));
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
