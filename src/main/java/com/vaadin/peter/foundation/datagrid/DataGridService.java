package com.vaadin.peter.foundation.datagrid;

import java.util.Optional;

import com.vaadin.peter.foundation.datagrid.definition.GridDefinition;

public interface DataGridService {

  <ITEM, FILTER> Optional<GridDefinition<ITEM, FILTER>> findGridDefinition(Class<ITEM> itemType,
      Class<FILTER> filterType);

  /**
   * Looks up {@link DataSource} for given item and filter types.
   * 
   * @param itemType
   * @param filterType
   * @return Optional of {@link DataSource} matching provided item and filter type
   *         or empty optional if no such {@link DataSource} was available.
   */
  <ITEM, FILTER> Optional<DataSource<ITEM, FILTER>> findDataSourceFor(Class<ITEM> itemType, Class<FILTER> filterType);
}
