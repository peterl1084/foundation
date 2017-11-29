package com.vaadin.peter.foundation.datagrid.definition;

import java.util.Objects;

import com.vaadin.data.ValueProvider;

public class GridColumnImpl<ITEM, PROPERTY> implements GridColumn<ITEM, PROPERTY> {

  private ValueProvider<ITEM, PROPERTY> propertyValueProvider;

  private String translationKey;
  private boolean hiddenByDefault;

  private boolean sortable;

  void setPropertyValueProvider(ValueProvider<ITEM, PROPERTY> propertyValueProvider) {
    this.propertyValueProvider = Objects.requireNonNull(propertyValueProvider);
  }

  public ValueProvider<ITEM, PROPERTY> getPropertyValueProvider() {
    return propertyValueProvider;
  }

  public GridColumnImpl<ITEM, PROPERTY> withTranslationKey(String translationKey) {
    this.translationKey = translationKey;
    return this;
  }

  public String getTranslationKey() {
    return translationKey;
  }

  public GridColumnImpl<ITEM, PROPERTY> withHiddenByDefault(boolean hiddenByDefault) {
    this.hiddenByDefault = hiddenByDefault;
    return this;
  }

  public boolean isHiddenByDefault() {
    return hiddenByDefault;
  }

  public void asSortable() {
    sortable = true;
  }

  public boolean isSortable() {
    return sortable;
  }
}
