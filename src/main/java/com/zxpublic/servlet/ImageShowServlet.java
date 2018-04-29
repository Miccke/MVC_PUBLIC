package com.zxpublic.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zxpublic.base.FileUtil;
import com.zxpublic.service.AttachmentService;
import com.zxpublic.vo.Attachment;

/**
 * 通过ID，显示图片内容
 * @author penggl
 *
 */
public class ImageShowServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    private AttachmentService attachmentService;  
    
    @Override   
    public void init(ServletConfig servletConfig) throws ServletException {   
        super.init(servletConfig);   
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());   
  
        attachmentService=(AttachmentService) webApplicationContext.getBean("attachmentServiceImpl");   
    }  

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileId = req.getParameter("fileId");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		OutputStream output = resp.getOutputStream();
		String imagePath = "";
		if (StringUtils.isNotEmpty(fileId) && !fileId.equals("null") && !fileId.equals("(null)")) {
			Attachment fileAttach = attachmentService.get(Long.parseLong(fileId));
			if (fileAttach == null) {
				imagePath = FileUtil.buildPath(getServletContext().getRealPath("/images"), "no_pic.png");
			} else {
				imagePath = fileAttach.getAttachment();
				File file = new File(imagePath);
				if (!file.exists()) {
					imagePath = FileUtil.buildPath(getServletContext().getRealPath("/images"), "no_pic.png");
				}
			}
			try {
				FileInputStream fileIn = new FileInputStream(imagePath);
				BufferedOutputStream bos = new BufferedOutputStream(output);
				byte data[] = new byte[4096];
				int size = 0;
				size = fileIn.read(data);
				while (size != -1) {
					bos.write(data, 0, size);
					size = fileIn.read(data);
				}
				fileIn.close();
				bos.flush();
				bos.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		output.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	/** 
     * 获取服务的url基本地址 
     * @param request 
     * @return 
     */  
    public static String getServerPath(HttpServletRequest request){  
        String path = request.getContextPath();  
        String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";  
          
        return basePath;  
    }  
    /** 
     * 获取带目录的url地址 
     * @param request 
     * @param directory 
     * @return 
     */  
    public static String getServerPath(HttpServletRequest request,String directory){  
        String path = request.getContextPath();  
        String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";  
        return basePath+directory+"/";  
    }  
    /** 
     * 获取服务器的根路径 
     * @param request 
     * @param directory 
     * @return 
     */  
    public static String getServerContextPath(HttpServletRequest request){  
        String path = request.getContextPath();  
        return path;  
    }  
}
