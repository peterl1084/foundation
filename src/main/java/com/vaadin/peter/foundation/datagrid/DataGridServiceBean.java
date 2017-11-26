package com.vaadin.peter.foundation.datagrid;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;

import com.vaadin.peter.foundation.datagrid.definition.GridDefinition;

@Service
public class DataGridServiceBean implements DataGridService {

  @Autowired
  private ApplicationContext appContext;

  @Override
  @SuppressWarnings("unchecked")
  public <ITEM, FILTER> Optional<DataSource<ITEM, FILTER>> findDataSourceFor(Class<ITEM> itemType,
      Class<FILTER> filterType) {

    ResolvableType dataSourceResolvable = ResolvableType.forClassWithGenerics(DataSource.class,
        Objects.requireNonNull(itemType), Objects.requireNonNull(filterType));

    List<String> beanNames = Arrays.asList(appContext.getBeanNamesForType(dataSourceResolvable));
    if (beanNames.isEmpty()) {
      return Optional.empty();
    }

    if (beanNames.size() > 1) {
      throw new IllegalStateException(
          "More than one " + DataSource.class.getSimpleName() + " available for types: " + itemType.getSimpleName()
              + "," + filterType.getName() + ": " + beanNames.stream().collect(Collectors.joining(",")));
    }

    return Optional.of(appContext.getBean(beanNames.iterator().next(), DataSource.class));
  }

  @Override
  @SuppressWarnings("unchecked")
  public <ITEM, FILTER> Optional<GridDefinition<ITEM, FILTER>> findGridDefinition(Class<ITEM> itemType,
      Class<FILTER> filterType) {
    ResolvableType gridDefinitionResolvable = ResolvableType.forClassWithGenerics(GridDefinition.class,
        Objects.requireNonNull(itemType), Objects.requireNonNull(filterType));

    List<String> beanNames = Arrays.asList(appContext.getBeanNamesForType(gridDefinitionResolvable));
    if (beanNames.isEmpty()) {
      return Optional.empty();
    }

    if (beanNames.size() > 1) {
      throw new IllegalStateException(
          "More than one " + GridDefinition.class.getSimpleName() + " available for types: " + itemType.getSimpleName()
              + "," + filterType.getName() + ": " + beanNames.stream().collect(Collectors.joining(",")));
    }

    return Optional.of(appContext.getBean(beanNames.iterator().next(), GridDefinition.class));
  }
}
