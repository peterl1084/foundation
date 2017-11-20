package com.vaadin.peter.foundation.datagrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.peter.foundation.Customer;

@Component
@PrototypeScope
public class DataGridTestBean {

  @Autowired
  private DataGrid<Customer, Void> dataGrid;

  public DataGrid<Customer, Void> getDataGrid() {
    return dataGrid;
  }
}
