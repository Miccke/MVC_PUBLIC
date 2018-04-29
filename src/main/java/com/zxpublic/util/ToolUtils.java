package com.zxpublic.util;



/**
 * 工具类
 * @author penggl
 * @time   2017-8-5 11:52:54
 */

public class ToolUtils {
	//地球半径// 单位千米  
    private static double EARTH_RADIUS = 6378.137;
    
    /** 
     * 角度弧度计算公式 rad:()
     *  
     * 360度=2π π=Math.PI 
     *  
     * x度 = x*π/360 弧度 
     *  
     * @param d 
     * @return 
     */  
    private static double getRadian(double degree) {  
        return degree * Math.PI / 180.0;  
    }  
  
    /** 
     * 根据经纬度计算两点之间的距离 GetDistance:()
     *  
     * @param lat1 
     *            1点的纬度 
     * @param lng1 
     *            1点的经度 
     * @param lat2 
     *            2点的纬度 
     * @param lng2 
     *            2点的经度 
     * @return 距离 单位 米 
     */  
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {  
        double radLat1 = getRadian(lat1);  
        double radLat2 = getRadian(lat2);  
        double a = radLat1 - radLat2;// 两点纬度差  
        double b = getRadian(lng1) - getRadian(lng2);// 两点的经度差  
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)  
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));  
        s = s * EARTH_RADIUS;  
        return s * 1000;  
    }
    
//    public static void main(String ar[]) {
//    	HttpMethod method = new GetMethod("https://api.weixin.qq.com/sns/userinfo?access_token=VsLU_qxoeumgBdvU0yOPfvjgKO05dnyFnVHuOcV3uo3xXSMXWbOe9w_qoymXZk4bedvqLFbkotOLy4oK29i2bQIfQBM3XoV9lS69utmBsnk&openid=ort3e1UMHzvua5FQrXctnXcX1JgI&lang=zh_CN");
//		HttpClient httpclient = new HttpClient();
//		
//		 try {
//			httpclient.executeMethod(method);
//			String result = new String(method.getResponseBody(), "utf-8");
//			//String result = method.getResponseBodyAsString();
//			System.out.println("getWeiXinUserInfo result = " + result);
//		} catch (HttpException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}      
//    } 
    
}
