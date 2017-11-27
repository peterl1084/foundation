package com.vaadin.peter.foundation.datagrid;

import com.vaadin.peter.foundation.IsComponent;
import com.vaadin.peter.foundation.IsRefreshable;
import com.vaadin.peter.foundation.datagrid.definition.GridDefinition;
import com.vaadin.peter.foundation.toolbar.ToolBar;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid.SelectionMode;

/**
 * {@link DataGrid} is table like component for visualizing tabular data. It's
 * defined with Item and Filter types and provides means for external
 * configuration through {@link GridDefinition} object.
 * 
 * @author Peter / Vaadin
 *
 * @param <ITEM>
 *          type of the item (row) in this table
 * @param <FILTER>
 *          type of the filter object used for filtering the data this table
 *          displays.
 */
public interface DataGrid<ITEM, FILTER> extends IsComponent<Component>, IsRefreshable {

  /**
   * @return the ITEM type associated with this {@link DataGrid}.
   */
  Class<ITEM> getItemType();

  /**
   * @return the FILTER type associated with this {@link DataGrid}.
   */
  Class<FILTER> getFilterType();

  /**
   * Sets the filter object for filtering the data within this {@link DataGrid}.
   * Setting the filter trigger full visible data reload.
   * 
   * @param filter
   */
  void setFilter(FILTER filter);

  /**
   * @return current number of visible columns in this {@link DataGrid}.
   */
  int getNumberOfColumns();

  /**
   * @return current number of total rows in this {@link DataGrid}. Not all rows
   *         are necessarily loaded.
   */
  int getNumberOfRows();

  /**
   * Enables {@link ToolBar} for this {@link DataGrid} passes a reference to it.
   * If invoked multiple times the same {@link ToolBar} instance is returned.
   * 
   * @return
   */
  ToolBar<ITEM> withToolBar();

  /**
   * Sets the selection mode (none, single, multi) for this {@link DataGrid}.
   * 
   * @param selectionMode
   */
  void setSelectionMode(SelectionMode selectionMode);
}
