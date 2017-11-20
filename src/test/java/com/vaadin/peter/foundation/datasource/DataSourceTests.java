package com.vaadin.peter.foundation.datasource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.vaadin.peter.foundation.Customer;
import com.vaadin.peter.foundation.CustomerTestDataSource;
import com.vaadin.peter.foundation.CustomerTestGridDefinition;
import com.vaadin.peter.foundation.DataGridService;
import com.vaadin.peter.foundation.DataGridServiceBean;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { DataGridServiceBean.class, CustomerTestDataSource.class })
public class DataSourceTests {

  @Autowired
  private DataGridService dataSourceService;

  @Test
  public void testDataSourceTypeLookup_properItemAndFilterTypesFound() {
    CustomerTestGridDefinition customerGridDef = new CustomerTestGridDefinition();
    Assert.assertEquals(Customer.class, customerGridDef.getItemType());
    Assert.assertEquals(Void.class, customerGridDef.getFilterType());
  }

  @Test
  public void testDataSourceServiceDiscovery_customerDataSourceFoundByTypeInfo() {
    Assert.assertTrue(dataSourceService.findDataSourceFor(Customer.class, Void.class).isPresent());
  }
}
