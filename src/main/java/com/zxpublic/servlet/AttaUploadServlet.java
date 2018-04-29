package com.zxpublic.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zxpublic.base.FileUtil;
import com.zxpublic.service.AttachmentService;
import com.zxpublic.vo.Attachment;
import com.zxpublic.vo.User;



/**
 * @version 1.0
 * @see
 */
//@WebServlet("/UploadServlet")
public class AttaUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    //private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    //private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
    
    private AttachmentService attachmentService;  
    
    @Override   
    public void init(ServletConfig servletConfig) throws ServletException {   
        super.init(servletConfig);   
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());   
  
        attachmentService=(AttachmentService) webApplicationContext.getBean("attachmentServiceImpl");   
    }  
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String pathUrl = "D:\\zxpublic\\upload\\"+sdf.format(new Date());
		String type = req.getParameter("type");
//		if(type.equals("headImg")){//头像上传
		if(type == null){//头像上传
			//重定义文件目录
	        pathUrl = "D:\\zxpublic\\upload\\headImg\\";
		}
		StringBuffer result = new StringBuffer();
		result.append("[");
		//格式化时间
        //定义文件目录
		
        HttpSession session = req.getSession(false); 
        User user = (User)session.getAttribute("user");
        
		// 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        // 如果目录不存在则创建
        File uploadDir = new File(pathUrl);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        if(user == null){
        	//登录后操作
            req.setAttribute("message","登录后进行操作!");
        }else{
        	try {
        		List<FileItem> formItems = upload.parseRequest(req);
        		if (formItems != null && formItems.size() > 0) {
        			boolean flag = false;
        			// 迭代表单数据
        			for (FileItem item : formItems) {
        				// 处理不在表单中的字段
        				if (!item.isFormField()) {
        					String fileName = new File(item.getName()).getName();
        					String newFileName = FileUtil.generateFilename(fileName);
        					String filePath = pathUrl + File.separator + newFileName;
        					File storeFile = new File(filePath);
        					
        					// 在控制台输出文件的上传路径
        					System.out.println(filePath);
        					// 保存文件到硬盘
        					item.write(storeFile);
        					Long fileSize = item.getSize();
        					Attachment atta = new Attachment();
        					atta.setFilesize(fileSize);
        					atta.setAttachment(filePath);
        					atta.setAttachname(fileName);
        					String fileNames[] = fileName.split("\\.");
        					atta.setAttachtype(fileNames[1]);
        					atta.setCreatetime(new Date());
        					atta.setOperName(user.getLoginName());
        					atta.setOperId(user.getUid().toString());
        					//插入附件信息
        					attachmentService.insertAttachment(atta);
        					//map.put(atta.getAttachId()+"", fileName);
        					//第一次进来flag为false，此时前面不拼接逗号","，后面每次进来在前面拼接一个逗号","
        					if(flag){
        						result.append(",{\"name\":\""+fileName+"\",\"id\":"+atta.getAttachId()+"}");
        					}else{
        						result.append("{\"name\":\""+fileName+"\",\"id\":"+atta.getAttachId()+"}");
        					}
        					req.setAttribute("message","文件上传成功!");
        					flag = true;
        				}
        			}
        		}
        		result.append("]");
        		//Gson gson = new Gson();
    			PrintWriter out = resp.getWriter();
    			out.write(result.toString());
    			out.flush();
    			out.close();
        	} catch (FileUploadException e) {
        		e.printStackTrace();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}