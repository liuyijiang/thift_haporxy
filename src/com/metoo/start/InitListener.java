package com.metoo.start;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		 System.out.println("================>[ServletContextListener]自动加载启动开始.");  
	        // 读取Spring容器中的Bean[此时Bean已加载,可以使用]  
//	        WebApplicationContext wac = WebContextUtil.getContext();  
//	        if (wac != null) {  
//	            ShopService shopService = wac.getBean("shopService", ShopService.class);  
//	            System.out.println("================>shopService[" + shopService + "]");  
//	            List<Map<String, Object>> shopList = shopService.findAllShop();  
//	            System.out.println("================>" + shopList);  
//	        }  
	        System.out.println("================>[ServletContextListener]自动加载启动结束.");
		
	}

}
