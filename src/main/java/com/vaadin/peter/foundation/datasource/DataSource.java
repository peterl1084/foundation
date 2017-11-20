package com.vaadin.peter.foundation.datasource;

import java.util.stream.Stream;

import com.vaadin.data.provider.Query;

public interface DataSource<DATA, FILTER> {

  long getSize(Query<DATA, FILTER> query);

  Stream<DATA> getData(Query<DATA, FILTER> query);
}
