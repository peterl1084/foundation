package com.vaadin.peter.foundation.datagrid;

import com.vaadin.peter.foundation.IsComponent;
import com.vaadin.peter.foundation.IsRefreshable;
import com.vaadin.peter.foundation.toolbar.ToolBar;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid.SelectionMode;

public interface DataGrid<ITEM, FILTER> extends IsComponent<Component>, IsRefreshable {

  void setFilter(FILTER filter);

  Class<ITEM> getItemType();

  Class<FILTER> getFilterType();

  int getNumberOfColumns();

  int getNumberOfRows();

  ToolBar<ITEM> withToolBar();

  void setSelectionMode(SelectionMode selectionMode);
}
