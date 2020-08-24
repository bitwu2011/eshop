package com.zhss.eshop.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SystemInitContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.printf("系统启动了。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.printf("系统开始销毁....");
    }
}
