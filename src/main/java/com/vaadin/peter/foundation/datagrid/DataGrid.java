package com.vaadin.peter.foundation.datagrid;

import com.vaadin.peter.foundation.IsComponent;
import com.vaadin.ui.Component;

public interface DataGrid<ITEM, FILTER> extends IsComponent<Component> {

  void setFilter(FILTER filter);

  void refresh();

  Class<ITEM> getItemType();

  Class<FILTER> getFilterType();

  int getNumberOfColumns();

  int getNumberOfRows();
}
