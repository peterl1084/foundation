package com.vaadin.peter.foundation.datagrid.definition;

import com.vaadin.data.ValueProvider;

/**
 * {@link GridColumn} specifies individual column in {@link GridDefinition}.
 * 
 * @author Peter
 *
 * @param <ITEM>
 *          type of the item (Grid's row)
 * @param <PROPERTY>
 *          data type of this column.
 */
public interface GridColumn<ITEM, PROPERTY> {

  ValueProvider<ITEM, PROPERTY> getPropertyValueProvider();

  String getTranslationKey();

  boolean isHiddenByDefault();

}
