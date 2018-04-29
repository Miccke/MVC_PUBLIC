package com.zxpublic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("file")  
@Controller  
public class FileController {
//    /**  
//     * 文件上传功能  
//     * @param file  
//     * @return  
//     * @throws IOException   
//     */  
//    @RequestMapping(value="/upload",method=RequestMethod.POST)  
//    @ResponseBody  
//    public String upload(MultipartFile file,HttpServletRequest request) throws IOException{
//    	String result = "";
//    	//获取文件名
//        String fileName = file.getOriginalFilename();
//        if(!StringUtils.isNullOrEmpty(fileName)){
//        	String strs[] = fileName.split(".");
//        	String suffStr = strs[1];
//        	//校验文件后缀
//        	if(!StringUtils.isNullOrEmpty(suffStr) && (suffStr.toLowerCase().equals("jpg") || 
//        			suffStr.toLowerCase().equals("png") || 
//        			suffStr.toLowerCase().equals("jpeg")|| 
//        			suffStr.toLowerCase().equals("bmp")|| 
//        			suffStr.toLowerCase().equals("jif")
//        			)){
//        		//格式化时间
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                //定义文件目录
//                String pathUrl = "D:\\zxpublic\\upload\\"+sdf.format(new Date());
//                
//                File logosavedir = new File(pathUrl);
//                //如果目录不存在就创建
//                if (!logosavedir.exists()) {
//                	logosavedir.mkdirs();
//            	}
//                //MultipartFile自带的解析方法  
//                file.transferTo(logosavedir);  
//                result = "ok!";
//        	}else{
//        		result = "请上传指定的文件格式";
//        	}
//        }else{
//    		result = "文件名为空";
//        }
//        return result;  
//    }  
//      
//    /**  
//     * 文件下载功能  
//     * @param request  
//     * @param response  
//     * @throws Exception  
//     */  
//    @RequestMapping("/down")  
//    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
//        //模拟文件，myfile.txt为需要下载的文件  
//        //String fileName = request.getSession().getServletContext().getRealPath("upload")+"/myfile.txt";  
//        String fileName = "D:\\zxpublic\\upload\\2017-08-10\\不合格.jpg";
//        //获取输入流  
//        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
//        //假如以中文名下载的话  
//        String filename = "不合格.jpg";  
//        //转码，免得文件名中文乱码  
//        filename = URLEncoder.encode(filename,"UTF-8");  
//        //设置文件下载头  
//        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
//        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
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