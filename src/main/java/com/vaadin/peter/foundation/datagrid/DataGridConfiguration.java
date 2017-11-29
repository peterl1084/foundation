package com.vaadin.peter.foundation.datagrid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.peter.foundation.datagrid.definition.GridDefinition;

/**
 * {@link DataGridConfiguration} provides {@link DataGrid} as bean with
 * preconfigured {@link GridDefinition} based on the ITEM and FILTER type of the
 * injection point.
 * 
 * @author Peter / Vaadin
 */
@Configuration
public class DataGridConfiguration {
  private static final int ITEM_INDEX = 0;
  private static final int FILTER_INDEX = 1;

  @Autowired
  private DataGridService dataGridService;

  @Bean
  @PrototypeScope
  protected <ITEM, FILTER> DataGrid<ITEM, FILTER> provideDataGrid(DependencyDescriptor dependencyDescriptor) {
    Class<ITEM> itemType = resolveItemType(dependencyDescriptor);
    Class<FILTER> filterType = resolveFilterType(dependencyDescriptor);

    DataGridBean<ITEM, FILTER> dataGrid = new DataGridBean<>(itemType, filterType);

    Optional<GridDefinition<ITEM, FILTER>> gridDefinition = dataGridService.findGridDefinition(itemType, filterType);
    dataGrid.setGridDefinition(
        gridDefinition.orElseThrow(() -> new RuntimeException("Could not find " + GridDefinition.class.getSimpleName()
            + " for data grid with types: " + itemType.getCanonicalName() + ", " + filterType.getCanonicalName())));

    return dataGrid;
  }

  @SuppressWarnings("unchecked")
  private <FILTER> Class<FILTER> resolveFilterType(DependencyDescriptor dependencyDescriptor) {
    return (Class<FILTER>) dependencyDescriptor.getResolvableType().getGeneric(FILTER_INDEX).resolve();
  }

  @SuppressWarnings("unchecked")
  private <ITEM> Class<ITEM> resolveItemType(DependencyDescriptor dependencyDescriptor) {
    return (Class<ITEM>) dependencyDescriptor.getResolvableType().getGeneric(ITEM_INDEX).resolve();
  }
}
