package com.zxpublic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("file")  
@Controller  
public class FileController {
//    /**  
//     * �ļ��ϴ�����  
//     * @param file  
//     * @return  
//     * @throws IOException   
//     */  
//    @RequestMapping(value="/upload",method=RequestMethod.POST)  
//    @ResponseBody  
//    public String upload(MultipartFile file,HttpServletRequest request) throws IOException{
//    	String result = "";
//    	//��ȡ�ļ���
//        String fileName = file.getOriginalFilename();
//        if(!StringUtils.isNullOrEmpty(fileName)){
//        	String strs[] = fileName.split(".");
//        	String suffStr = strs[1];
//        	//У���ļ���׺
//        	if(!StringUtils.isNullOrEmpty(suffStr) && (suffStr.toLowerCase().equals("jpg") || 
//        			suffStr.toLowerCase().equals("png") || 
//        			suffStr.toLowerCase().equals("jpeg")|| 
//        			suffStr.toLowerCase().equals("bmp")|| 
//        			suffStr.toLowerCase().equals("jif")
//        			)){
//        		//��ʽ��ʱ��
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                //�����ļ�Ŀ¼
//                String pathUrl = "D:\\zxpublic\\upload\\"+sdf.format(new Date());
//                
//                File logosavedir = new File(pathUrl);
//                //���Ŀ¼�����ھʹ���
//                if (!logosavedir.exists()) {
//                	logosavedir.mkdirs();
//            	}
//                //MultipartFile�Դ��Ľ�������  
//                file.transferTo(logosavedir);  
//                result = "ok!";
//        	}else{
//        		result = "���ϴ�ָ�����ļ���ʽ";
//        	}
//        }else{
//    		result = "�ļ���Ϊ��";
//        }
//        return result;  
//    }  
//      
//    /**  
//     * �ļ����ع���  
//     * @param request  
//     * @param response  
//     * @throws Exception  
//     */  
//    @RequestMapping("/down")  
//    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
//        //ģ���ļ���myfile.txtΪ��Ҫ���ص��ļ�  
//        //String fileName = request.getSession().getServletContext().getRealPath("upload")+"/myfile.txt";  
//        String fileName = "D:\\zxpublic\\upload\\2017-08-10\\���ϸ�.jpg";
//        //��ȡ������  
//        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
//        //���������������صĻ�  
//        String filename = "���ϸ�.jpg";  
//        //ת�룬����ļ�����������  
//        filename = URLEncoder.encode(filename,"UTF-8");  
//        //�����ļ�����ͷ  
//        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
//        //1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����    
//        response.setContentType("multipart/form-data");   
//        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
//        int len = 0;  
//        while((len = bis.read()) != -1){
//            out.write(len);  
//            out.flush();  
//        }  
//        out.close();  
//    }  
}  