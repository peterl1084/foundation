package com.vaadin.peter.foundation.datagrid;

import java.util.Optional;

import com.vaadin.peter.foundation.datagrid.definition.GridDefinition;

/**
 * {@link DataGridService} provides general utility methods that need to be
 * executed in present application context. It allows finding
 * {@link GridDefinition}s and {@link DataSource}s compatible with specific ITEM
 * and FILTER types.
 * 
 * @author Peter / Vaadin
 */
public interface DataGridService {

  /**
   * Finds the {@link GridDefinition} bean for provided itemType and filterType
   * definitions.
   * 
   * @param itemType
   * @param filterType
   * @return Optional of {@link GridDefinition} given for provided item and filter
   *         or empty optional if no such {@link GridDefinition} was available.
   */
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
