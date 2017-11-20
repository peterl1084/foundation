package com.vaadin.peter.foundation.datagrid;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.provider.CallbackDataProvider.CountCallback;
import com.vaadin.data.provider.CallbackDataProvider.FetchCallback;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.peter.foundation.datagrid.definition.GridDefinition;
import com.vaadin.peter.foundation.formatter.Formatter;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;

class DataGridBean<ITEM, FILTER> extends Composite implements DataGrid<ITEM, FILTER> {

  private Class<ITEM> itemType;
  private Class<FILTER> filterType;

  @Autowired
  private Formatter formatter;

  private Grid<ITEM> grid;
  private ConfigurableFilterDataProvider<ITEM, Void, FILTER> dataProvider;

  public DataGridBean(Class<ITEM> itemType, Class<FILTER> filterType) {
    this.itemType = Objects.requireNonNull(itemType);
    this.filterType = Objects.requireNonNull(filterType);

    grid = new Grid<>();
    setCompositionRoot(grid);
  }

  protected void setGridDefinition(GridDefinition<ITEM, FILTER> gridDefinition) {
    configureColumns(Objects.requireNonNull(gridDefinition));
    configureDataProvider(gridDefinition);
  }

  protected void configureColumns(GridDefinition<ITEM, FILTER> gridDefinition) {
    grid.removeAllColumns();

    gridDefinition.getColumns().forEach(columnDefinition -> {
      grid.addColumn(item -> {
        return columnDefinition.getPropertyValueProvider().apply(item);
      }, value -> {
        return formatter.formatToText(value);
      });
    });
  }

  protected void configureDataProvider(GridDefinition<ITEM, FILTER> gridDefinition) {
    DataSource<ITEM, FILTER> dataSource = gridDefinition.getAssociatedDataSource();

    FetchCallback<ITEM, FILTER> fetch = query -> dataSource.getData(query);
    CountCallback<ITEM, FILTER> count = query -> dataSource.getSize(query);

    dataProvider = DataProvider.fromFilteringCallbacks(fetch, count).withConfigurableFilter();
    grid.setDataProvider(dataProvider);
  }

  public int getNumberOfColumns() {
    return grid.getColumns().size();
  }

  public int getNumberOfRows() {
    return grid.getDataCommunicator().getDataProviderSize();
  }

  @Override
  public void setFilter(FILTER filter) {
    dataProvider.setFilter(filter);
  }

  @Override
  public void refresh() {

  }

  @Override
  public Class<ITEM> getItemType() {
    return itemType;
  }

  @Override
  public Class<FILTER> getFilterType() {
    return filterType;
  }
}
