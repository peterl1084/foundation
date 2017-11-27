package com.vaadin.peter.foundation;

/**
 * {@link IsRefreshable} is a marker interface for any such types that support
 * explicit refresh.
 * 
 * @author Peter / Vaadin
 */
public interface IsRefreshable {

  /**
   * Refreshes this object instance.
   */
  void refresh();
}
