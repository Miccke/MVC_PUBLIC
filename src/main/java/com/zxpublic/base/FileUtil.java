package com.zxpublic.base;

import static jodd.core.JoddCore.ioBufferSize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
    
	private static Log logger=LogFactory.getLog(FileUtil.class);
	
	public static String generateFilename(String originalFilename){
		
		SimpleDateFormat dirSdf=new SimpleDateFormat("yyyyMM");
		String filePre=dirSdf.format(new Date());
		
        String fileExt="";
        int lastIndex=originalFilename.lastIndexOf('.');
        //取得文件的扩展名
        if(lastIndex!=-1){
        	fileExt=originalFilename.substring(lastIndex);
        }

        //String filename=filePre+"/"+UUIDGenerator.getUUID()+fileExt;
        String filename=UUIDGenerator.getUUID()+fileExt;
        
        return filename;
	}
	
	/**
	 * 把数据写至文件中
	 * @param filePath
	 * @param data
	 */
	public static void writeFile(String filePath,String data){
		FileOutputStream fos = null;
		OutputStreamWriter writer=null;
		if (!makeParentDir(filePath)){
			logger.error("建立文件所在目录失败:" + filePath);
		}
		try {
			fos = new FileOutputStream(new File(filePath));
			writer=new OutputStreamWriter(fos, "UTF-8");			
			writer.write(data);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			try {
				if(writer!=null){
					writer.close();
				}
				if (fos != null){
					fos.close();
				}
			} catch (Exception e) {
			}
		}		
	}
	
	/**
	 * 读取文件内容
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath){
		 StringBuffer buffer = new StringBuffer();
		// 读出这个文件的内容
		try{
			File file = new File(filePath);
		    FileInputStream fis = null;
		    BufferedReader breader=null;
		    try {
		      fis = new FileInputStream(file);
		      InputStreamReader isReader=new InputStreamReader(fis,"UTF-8");
		      breader=new BufferedReader(isReader);
		      String line;
		      while((line=breader.readLine())!=null) {
		        buffer.append(line);
		        buffer.append("\r\n");
		      }
		      breader.close();
		      isReader.close();
		      fis.close();
		      
		    } catch (FileNotFoundException e) {
		      logger.error(e.getMessage());
		    } catch (IOException e) {
		    	logger.error(e.getMessage());
		    }
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return buffer.toString();
	}

	public static boolean makeParentDir(String path){
		File file = new File(path);
		return makeDir(file.getParent());
	}
	public static boolean makeDir(String path){
		File file = new File(path);
		if (!file.exists()){
			String parentPath = file.getParent();
			if (parentPath == null || !makeDir(parentPath)){
				return false;
			}
			if (!file.mkdir()){
				return false;
			}
		}
		
		return true;
	}
	
	public static String buildPath(String base, String attach){
		String path;
		
		if (base.endsWith("/") || base.endsWith("\\")){
			if (attach.startsWith("/") || attach.startsWith("\\")){
				path = base + attach.substring(1);
			} else {
				path = base + attach;
			}
		} else {
			if (attach.startsWith("/") || attach.startsWith("\\")){
				path = base + attach;
			} else {
				path = base + "/" + attach;
			}
		}
		
		return path;
	}
	
	public static int copy(Reader input, Writer output) throws IOException {
		char[] buffer = new char[ioBufferSize];
		int count = 0;
		int read;
		while ((read = input.read(buffer, 0, ioBufferSize)) >= 0) {
			output.write(buffer, 0, read);
			count += read;
		}
		output.flush();
		return count;
	}
	
	public static String fileSizeStr(long size){
	    DecimalFormat df=new DecimalFormat("0.00");
		if(size>1024*1024){
			 double ss=size/(1024*1024.0);
		 	 return df.format(ss)+" M";
		}else if(size>1024){
			double ss=size/1024.0;
			return df.format(ss)+" KB";
		}else{
			return size+" B";
		}
    }
}
