package com.vaadin.peter.foundation.datagrid;

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
import com.vaadin.peter.foundation.FormatterTestFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { DataGridServiceBean.class, CustomerTestDataSource.class, DataGridConfiguration.class,
    CustomerTestGridDefinition.class, DataGridTestBean.class, FormatterTestFactory.class })
public class DataGridTests {

  @Autowired
  private DataGridTestBean dataGridTestBean;

  @Test
  public void testDataGridInstantiation_DataGridInstantiatedFromResolvableType() {
    DataGrid<Customer, Void> dataGrid = dataGridTestBean.getDataGrid();
    Assert.assertNotNull(dataGrid);

    Assert.assertEquals(Customer.class, dataGrid.getItemType());
    Assert.assertEquals(Void.class, dataGrid.getFilterType());

    Assert.assertEquals(2, dataGrid.getNumberOfColumns());
    Assert.assertEquals(3, dataGrid.getNumberOfRows());
  }
}
