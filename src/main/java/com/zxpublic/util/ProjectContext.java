package com.zxpublic.util;


import javax.servlet.ServletContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;


/**
 * <p>�ṩWebӦ�ó��������ķ���
 * ������Spring��������־��Session������</p>
 */
public class ProjectContext implements ApplicationContextAware,ServletContextAware{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static ProjectContext instance = null;
//	private static ProjectContext projectContext = null;
	private ApplicationContext applicationContext = null;
	private ServletContext servletContext = null;
//	private static PropertyPlaceholderConfigurer propConfigurer = null;

	private static ProjectContext getInstance() {
		return ProjectContext.instance;
	}

	private static void setInstance(ProjectContext inst) {
		ProjectContext.instance = inst;
	}
	
	public void initialize() {

		ProjectContext.setInstance(this);

		logger.info(this.getClass().getSimpleName() + "ʵ���ѳ�ʼ��");
	}

	public void terminate() {

		servletContext = null;
		servletContext = null;

	}


	/**
	 * <p>ȡ�õ�ǰӦ�ó��������Ķ���</p>
	 * @return ��ǰ�����Ķ���
	 */
	public static ProjectContext getContext() {
		return getInstance();
	}

	/**
	 * ȡ��bean����
	 * @return ��ǰApplicationContext����
	 */
	public static ApplicationContext getApplicationContext() {
		return getInstance().applicationContext;
	}


	/**
	 * <p>��������ȡ��spring bean����</p>
	 * @param bean id
	 * @return �ҵ��򷵻ض��󣬷��򷵻�null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		ApplicationContext appCtx = ProjectContext.getApplicationContext();
		if (appCtx == null)
			return null;

		return (T)appCtx.getBean(name);
	}

	/**
	 * <p>������ȡ��spring bean����</p>
	 * @param bean id
	 * @return �ҵ��򷵻ض��󣬷��򷵻�null
	 */
	public static <T> T getBean(Class<T> nameCls){
		ApplicationContext appCtx = ProjectContext.getApplicationContext();
		if (appCtx == null)
			return null;
		
		return appCtx.getBean(nameCls);
	}

	/**
	 * <p>����ָ�����Ƶ���־����</p>
	 * @param ��־�������ֿռ�
	 * @return ��־����
	 */
	public Logger getLogger(String name) {
		Logger logger = LoggerFactory.getLogger(name);
		return logger;
	}

	/**
	 * ������־����
	 * @return ��־����
	 */
	public Logger getLogger() {
		return LoggerFactory.getLogger(ProjectContext.class);
	}
	


	

	public static String getAppAbsolutePath(){
		return ProjectContext.getInstance().servletContext.getRealPath("/");
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.applicationContext = context;
	}


	public void setServletContext(ServletContext sc) {
		this.servletContext = sc;
	}


}
