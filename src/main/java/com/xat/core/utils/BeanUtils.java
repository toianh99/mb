package com.xat.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanUtils implements ApplicationContextAware {
   private static ApplicationContext context;

   public static ApplicationContext getApplicationContext() {
      return context;
   }

   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      context = applicationContext;
   }
}
