package com.zxpublic.servlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.util.UriUtils;

import com.mysql.jdbc.StringUtils;
import com.zxpublic.service.AttachmentService;
import com.zxpublic.vo.Attachment;

/**
 * 附件下载
 * @author penggl
 *
 */
@SuppressWarnings("serial")
public class AttaDownloadServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(AttaDownloadServlet.class);
	
    private AttachmentService attachmentService;  
    
    @Override   
    public void init(ServletConfig servletConfig) throws ServletException {   
        super.init(servletConfig);   
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());   
  
        attachmentService=(AttachmentService) webApplicationContext.getBean("attachmentServiceImpl");   
    }  
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){
		ServletOutputStream out = null;
		try{
			String refId=req.getParameter("attachId");
			//LongStringPair pair = Attachment.getRefrencePair(refId);
			if(refId != null){
				req.setCharacterEncoding("UTF-8");
				resp.setCharacterEncoding("UTF-8");
				Attachment attach=attachmentService.get(Long.parseLong(refId));
				if (StringUtils.isNullOrEmpty(attach.getAttachment())){
					// 非法请求，返回请求的文件没有找到
					resp.sendError(404, "抱歉! 您请求的文件没有找到!");
					return;
				}
				String sourcePath= attach.getAttachment();
				String ext=attach.getAttachtype();
				if(ext.toLowerCase().endsWith("zip")){
					resp.setContentType("application/x-zip-compressed");
				}else if(ext.toLowerCase().endsWith("rar")){
					resp.setContentType("application/octet-stream");
				}else if(ext.toLowerCase().endsWith("doc")){
					resp.setContentType("application/msword");
				}else if(ext.toLowerCase().endsWith("xls") || ext.toLowerCase().endsWith("csv")){
					resp.setContentType("application/ms-excel ");
				}else if (ext.toLowerCase().endsWith("pdf")){
					resp.setContentType("application/pdf");
				}else{
					resp.setContentType("application/x-msdownload");
				}
				String fileName = attach.getAttachname();
				String fileNameEnc = UriUtils.encodeQueryParam(fileName, "utf-8");
				String ua = req.getHeader("user-agent");
				if (ua == null){
					ua = "msie";
				} else {
					ua = ua.toLowerCase();
				}
				// 判断浏览器类型，并设置文档名称
				if (ua.indexOf("msie") >= 0){
					resp.addHeader("Content-Disposition","attachment;filename=\"" + fileNameEnc + "\"");
				} else if (ua.indexOf("firefox") >= 0 || ua.indexOf("opera") >= 0 || ua.indexOf("chrome") >= 0){
					resp.addHeader("Content-Disposition","attachment;filename*=UTF-8''" + fileNameEnc);
				} else if (ua.indexOf("safari") >= 0){
//					fileNameEnc = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
					resp.addHeader("Content-Disposition","attachment;filename=" + fileName);
				} else if (ua.indexOf("applewebkit") >= 0){
//					fileNameEnc = MimeUtility.encodeText(fileName, "UTF-8", "B");
					resp.addHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
				} else {
					resp.addHeader("Content-Disposition","attachment;filename=\"" + fileNameEnc + "\"");
				}
	    		FileInputStream fileIn =new FileInputStream(sourcePath);
	            out = resp.getOutputStream();
	            byte[] buff = new byte[1024];
	            int leng = fileIn.read(buff);
	            while(leng>0){
            		out.write(buff,0,leng);
        			leng = fileIn.read(buff);
	            }
			}
		}catch(Exception ex){
			logger.error(""+ex.getMessage());
		}finally{
			if(out!=null){
				try {
					out.flush();
					out.close();
				} catch (Exception ex) {
					logger.error(""+ex.getMessage());
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		doGet(req, resp);
	}
}
