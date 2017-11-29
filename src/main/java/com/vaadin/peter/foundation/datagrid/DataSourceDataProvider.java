package com.vaadin.peter.foundation.datagrid;

import java.util.Objects;
import java.util.stream.Stream;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.ui.Grid;

/**
 * {@link DataSourceDataProvider} is used by {@link DataGrid} to connect with
 * the backing {@link DataSource} by means of Vaadin {@link Grid} component.
 * 
 * @author Peter / Vaadin
 *
 * @param <ITEM>
 * @param <FILTER>
 */
public class DataSourceDataProvider<ITEM, FILTER> extends AbstractBackEndDataProvider<ITEM, FILTER> {

  private DataSource<ITEM, FILTER> dataSource;
  private FILTER filter;

  public DataSourceDataProvider(DataSource<ITEM, FILTER> dataSource) {
    this.dataSource = Objects.requireNonNull(dataSource);
  }

  /**
   * Sets the filter for filtering the data in the backend or {@link DataSource}
   * side of the service.
   * 
   * @param filter
   */
  public void setFilter(FILTER filter) {
    this.filter = filter;
    refreshAll();
  }

  protected FILTER getFilter() {
    return filter;
  }

  @Override
  public boolean isInMemory() {
    return dataSource.isInMemory();
  }

  @Override
  protected Stream<ITEM> fetchFromBackEnd(Query<ITEM, FILTER> query) {
    Stream<ITEM> dataStream = dataSource.getData(mixinFilter(query));
    if (isInMemory() && query.getInMemorySorting() != null) {
      dataStream = dataStream.sorted(query.getInMemorySorting());
    }
    return dataStream;
  }

  @Override
  protected int sizeInBackEnd(Query<ITEM, FILTER> query) {
    return dataSource.getSize(mixinFilter(query));
  }

  /**
   * Mixes in the provided current filter with inbound data query.
   * 
   * @param baseQuery
   * @return {@link Query} with current filter mixed in.
   */
  protected Query<ITEM, FILTER> mixinFilter(Query<ITEM, FILTER> baseQuery) {
    return new Query<>(baseQuery.getOffset(), baseQuery.getLimit(), baseQuery.getSortOrders(),
        baseQuery.getInMemorySorting(), getFilter());
  }
}
