package com.vaadin.peter.foundation;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.peter.foundation.datagrid.definition.AbstractGridDefinition;

@Component
@PrototypeScope
public class CustomerTestGridDefinition extends AbstractGridDefinition<Customer, Void> {

  @PostConstruct
  protected void initialize() {
    addColumnDefinition(Customer::getFirstName).withTranslationKey("firstName").withHiddenByDefault(true);
    addColumnDefinition(Customer::getLastName).withTranslationKey("lastName").withHiddenByDefault(true);
  }
}
