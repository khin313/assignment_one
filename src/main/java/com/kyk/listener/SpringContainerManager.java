package com.kyk.listener;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SpringContainerManager implements ServletContextListener {

    public static final String SPRING_CONTEXT = "spring.context";

    private GenericXmlApplicationContext springContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Init");
        springContext = new GenericXmlApplicationContext("classpath:application.xml");
        sce.getServletContext().setAttribute(SPRING_CONTEXT, springContext);
        System.out.println("done");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Des");

        if (springContext != null) {
            springContext.close();
        }

    }
}
