package com.zxpublic.util;


import javax.servlet.ServletContext;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;


/**
 * <p>提供Web应用程序上下文服务
 * 包括：Spring工厂，日志，Session工厂等</p>
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

		logger.info(this.getClass().getSimpleName() + "实例已初始化");
	}

	public void terminate() {

		servletContext = null;
		servletContext = null;

	}


	/**
	 * <p>取得当前应用程序上下文对象</p>
	 * @return 当前上下文对象
	 */
	public static ProjectContext getContext() {
		return getInstance();
	}

	/**
	 * 取得bean工厂
	 * @return 当前ApplicationContext对象
	 */
	public static ApplicationContext getApplicationContext() {
		return getInstance().applicationContext;
	}


	/**
	 * <p>根据名称取得spring bean对象</p>
	 * @param bean id
	 * @return 找到则返回对象，否则返回null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		ApplicationContext appCtx = ProjectContext.getApplicationContext();
		if (appCtx == null)
			return null;

		return (T)appCtx.getBean(name);
	}

	/**
	 * <p>根据类取得spring bean对象</p>
	 * @param bean id
	 * @return 找到则返回对象，否则返回null
	 */
	public static <T> T getBean(Class<T> nameCls){
		ApplicationContext appCtx = ProjectContext.getApplicationContext();
		if (appCtx == null)
			return null;
		
		return appCtx.getBean(nameCls);
	}

	/**
	 * <p>返回指定名称的日志对象</p>
	 * @param 日志对象名字空间
	 * @return 日志对象
	 */
	public Logger getLogger(String name) {
		Logger logger = LoggerFactory.getLogger(name);
		return logger;
	}

	/**
	 * 返回日志对象
	 * @return 日志对象
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
