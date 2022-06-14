package com.kyk.controller;

import com.kyk.listener.SpringContainerManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import javax.servlet.http.HttpServlet;

public abstract class AbstractBeanFactoryServlet extends HttpServlet implements BeanFactoryServlet {


    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        var spring = getServletContext().getAttribute(SpringContainerManager.SPRING_CONTEXT);
        if (spring != null && spring instanceof BeanFactory factory) {
            return factory.getBean(name, requiredType);
        }
        return null;
    }
}
