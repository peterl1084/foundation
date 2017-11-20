package com.vaadin.peter.foundation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.data.provider.Query;
import com.vaadin.peter.foundation.datagrid.DataSource;

@Component
@PrototypeScope
public class CustomerTestDataSource implements DataSource<Customer, Void> {

  private List<Customer> testCustomers;

  public CustomerTestDataSource() {
    testCustomers = new ArrayList<>();

    testCustomers.add(Customer.of("A", "1"));
    testCustomers.add(Customer.of("B", "2"));
    testCustomers.add(Customer.of("C", "3"));
  }

  @Override
  public int getSize(Query<Customer, Void> query) {
    return testCustomers.size();
  }

  @Override
  public Stream<Customer> getData(Query<Customer, Void> query) {
    return testCustomers.stream().skip(query.getOffset()).limit(query.getLimit());
  }
}
