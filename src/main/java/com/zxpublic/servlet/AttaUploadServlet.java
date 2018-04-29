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
	
    // �ϴ�����
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
//		if(type.equals("headImg")){//ͷ���ϴ�
		if(type == null){//ͷ���ϴ�
			//�ض����ļ�Ŀ¼
	        pathUrl = "D:\\zxpublic\\upload\\headImg\\";
		}
		StringBuffer result = new StringBuffer();
		result.append("[");
		//��ʽ��ʱ��
        //�����ļ�Ŀ¼
		
        HttpSession session = req.getSession(false); 
        User user = (User)session.getAttribute("user");
        
		// �����ϴ�����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // ������ʱ�洢Ŀ¼
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        // ���Ŀ¼�������򴴽�
        File uploadDir = new File(pathUrl);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        if(user == null){
        	//��¼�����
            req.setAttribute("message","��¼����в���!");
        }else{
        	try {
        		List<FileItem> formItems = upload.parseRequest(req);
        		if (formItems != null && formItems.size() > 0) {
        			boolean flag = false;
        			// ����������
        			for (FileItem item : formItems) {
        				// �����ڱ��е��ֶ�
        				if (!item.isFormField()) {
        					String fileName = new File(item.getName()).getName();
        					String newFileName = FileUtil.generateFilename(fileName);
        					String filePath = pathUrl + File.separator + newFileName;
        					File storeFile = new File(filePath);
        					
        					// �ڿ���̨����ļ����ϴ�·��
        					System.out.println(filePath);
        					// �����ļ���Ӳ��
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
        					//���븽����Ϣ
        					attachmentService.insertAttachment(atta);
        					//map.put(atta.getAttachId()+"", fileName);
        					//��һ�ν���flagΪfalse����ʱǰ�治ƴ�Ӷ���","������ÿ�ν�����ǰ��ƴ��һ������","
        					if(flag){
        						result.append(",{\"name\":\""+fileName+"\",\"id\":"+atta.getAttachId()+"}");
        					}else{
        						result.append("{\"name\":\""+fileName+"\",\"id\":"+atta.getAttachId()+"}");
        					}
        					req.setAttribute("message","�ļ��ϴ��ɹ�!");
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