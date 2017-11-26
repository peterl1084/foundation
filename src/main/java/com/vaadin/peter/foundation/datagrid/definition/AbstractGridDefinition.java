package com.vaadin.peter.foundation.datagrid.definition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.ValueProvider;
import com.vaadin.peter.foundation.datagrid.DataGridService;
import com.vaadin.peter.foundation.datagrid.DataSource;

/**
 * {@link AbstractGridDefinition} is the abstract base class for all
 * {@link GridDefinition}s.
 * 
 * @author Peter
 *
 * @param <ITEM>
 * @param <FILTER>
 */
public abstract class AbstractGridDefinition<ITEM, FILTER> implements GridDefinition<ITEM, FILTER> {

  private List<GridColumn<ITEM, ?>> columns;

  @Autowired
  private DataGridService dataGridService;

  public AbstractGridDefinition() {
    columns = new ArrayList<>();
  }

  protected <PROPERTY> GridColumnImpl<ITEM, PROPERTY> addColumnDefinition(
      ValueProvider<ITEM, PROPERTY> propertyValueProvider) {
    GridColumnImpl<ITEM, PROPERTY> gridColumn = new GridColumnImpl<>();
    gridColumn.setPropertyValueProvider(propertyValueProvider);
    columns.add(gridColumn);
    return gridColumn;
  }

  @Override
  public DataSource<ITEM, FILTER> getAssociatedDataSource() {
    return dataGridService.findDataSourceFor(getItemType(), getFilterType()).orElseThrow(
        () -> new IllegalStateException("Could not associate " + DataSource.class.getSimpleName() + " with " + this));
  }

  @Override
  public List<GridColumn<ITEM, ?>> getColumns() {
    return Collections.unmodifiableList(columns);
  }
}
