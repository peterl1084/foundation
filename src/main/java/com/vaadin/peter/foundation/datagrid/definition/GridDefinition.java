package com.vaadin.peter.foundation.datagrid.definition;

import java.util.List;

import org.springframework.core.ResolvableType;

import com.vaadin.peter.foundation.datagrid.DataGrid;
import com.vaadin.peter.foundation.datagrid.DataSource;

/**
 * {@link GridDefinition} is the bean that is used for declaratively configuring
 * the {@link DataGrid} component.
 * 
 * @author Peter
 *
 * @param <ITEM>
 *          type of the item (Grid's row)
 * @param <FILTER>
 *          type of the filter
 */
public interface GridDefinition<ITEM, FILTER> {

  static final int ITEM_INDEX = 0;
  static final int FILTER_INDEX = 1;

  /**
   * @return List of columns in definition order.
   */
  List<GridColumn<ITEM, ?>> getColumns();

  /**
   * @return the associated {@link DataSource} based on item and filter types.
   */
  DataSource<ITEM, FILTER> getAssociatedDataSource();

  /**
   * @return the type of the item (row) that this {@link GridDefinition} uses.
   */
  @SuppressWarnings("unchecked")
  default Class<ITEM> getItemType() {
    return (Class<ITEM>) ResolvableType.forClass(GridDefinition.class, getClass()).getGeneric(ITEM_INDEX).resolve();
  }

  /**
   * @return the type of the filter criteria object this {@link GridDefinition}
   *         uses.
   */
  @SuppressWarnings("unchecked")
  default Class<FILTER> getFilterType() {
    return (Class<FILTER>) ResolvableType.forClass(GridDefinition.class, getClass()).getGeneric(FILTER_INDEX).resolve();
  }
}
