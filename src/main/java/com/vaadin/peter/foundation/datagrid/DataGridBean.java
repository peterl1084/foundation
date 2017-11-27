package com.vaadin.peter.foundation.datagrid;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.vaadin.data.provider.CallbackDataProvider.CountCallback;
import com.vaadin.data.provider.CallbackDataProvider.FetchCallback;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.peter.foundation.datagrid.definition.GridDefinition;
import com.vaadin.peter.foundation.formatter.Formatter;
import com.vaadin.peter.foundation.toolbar.ToolBar;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;

class DataGridBean<ITEM, FILTER> extends Composite implements DataGrid<ITEM, FILTER>, ApplicationContextAware {

  private Class<ITEM> itemType;
  private Class<FILTER> filterType;

  @Autowired
  private Formatter formatter;

  private Grid<ITEM> grid;
  private ConfigurableFilterDataProvider<ITEM, Void, FILTER> dataProvider;
  private ApplicationContext applicationContext;
  private ToolBar<ITEM> toolBar;

  private VerticalLayout layout;

  public DataGridBean(Class<ITEM> itemType, Class<FILTER> filterType) {
    layout = new VerticalLayout();
    layout.setSizeFull();
    layout.setMargin(false);
    layout.setSpacing(false);

    this.itemType = Objects.requireNonNull(itemType);
    this.filterType = Objects.requireNonNull(filterType);

    grid = new Grid<>();
    grid.setSizeFull();

    layout.addComponent(grid);
    layout.setExpandRatio(grid, 1);
    setCompositionRoot(layout);
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
    return (int) grid.getColumns().stream().filter(column -> !column.isHidden()).count();
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
    dataProvider.refreshAll();
    getToolBar().ifPresent(ToolBar::refresh);
  }

  @Override
  public Class<ITEM> getItemType() {
    return itemType;
  }

  @Override
  public Class<FILTER> getFilterType() {
    return filterType;
  }

  @Override
  @SuppressWarnings("unchecked")
  public ToolBar<ITEM> withToolBar() {
    return getToolBar().orElseGet(() -> {
      toolBar = applicationContext.getBean(ToolBar.class);
      toolBar.withItemProvider(() -> grid.getSelectedItems());
      grid.addSelectionListener(selectionEvent -> {
        toolBar.refresh();
      });
      layout.addComponent(toolBar.asComponent());
      return toolBar;
    });
  }

  protected Optional<ToolBar<ITEM>> getToolBar() {
    return Optional.ofNullable(toolBar);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  public void setSelectionMode(SelectionMode selectionMode) {
    grid.setSelectionMode(Objects.requireNonNull(selectionMode));
  }
}
