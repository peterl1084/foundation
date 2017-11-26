package com.vaadin.peter.foundation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.vaadin.peter.foundation.datagrid.EnableDataGrid;

@Configuration
@ComponentScan
public class VaadinFoundationAutoConfiguration {

  private static Logger logger = LoggerFactory.getLogger(VaadinFoundationAutoConfiguration.class);

  @Configuration
  @EnableDataGrid
  static class EnableDataGridConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
      logger.debug("{} initialized", getClass().getName());
    }
  }
}
