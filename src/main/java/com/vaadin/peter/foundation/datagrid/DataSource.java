package com.vaadin.peter.foundation.datagrid;

import java.util.stream.Stream;

import com.vaadin.data.provider.Query;

public interface DataSource<ITEM, FILTER> {

  int getSize(Query<ITEM, FILTER> query);

  Stream<ITEM> getData(Query<ITEM, FILTER> query);
}
