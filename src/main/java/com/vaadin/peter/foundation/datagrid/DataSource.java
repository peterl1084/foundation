package com.vaadin.peter.foundation.datagrid;

import java.util.stream.Stream;

import com.vaadin.data.provider.Query;

/**
 * {@link DataSource} is general UI independent top level interface for
 * accessing the data from the backend system. With {@link DataSource} the
 * system can query the amount of data available, as well as Stream of data
 * limited to certain segment.
 * 
 * @author Peter / Vaadin
 *
 * @param <ITEM>
 * @param <FILTER>
 */
public interface DataSource<ITEM, FILTER> {

  /**
   * @param query
   * @return count of data with provided query available through this
   *         {@link DataSource}
   */
  int getSize(Query<ITEM, FILTER> query);

  /**
   * @param query
   * @return {@link Stream} of data provided for given query.
   */
  Stream<ITEM> getData(Query<ITEM, FILTER> query);

  /**
   * @return true or false depending if this {@link DataSource} keeps all
   *         available data in memory.
   */
  boolean isInMemory();
}
