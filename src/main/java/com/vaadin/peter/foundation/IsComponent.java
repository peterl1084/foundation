package com.vaadin.peter.foundation;

import com.vaadin.ui.Component;

/**
 * {@link IsComponent} is marker interface for any such type that internally is
 * Vaadin component but not exposing traditional {@link Component} API through
 * the main public API.
 * 
 * @author Peter / Vaadin
 *
 * @param <C>
 *          type of the component this type represents.
 */
public interface IsComponent<C extends Component> {

  /**
   * @return this as Vaadin {@link Component}
   */
  @SuppressWarnings("unchecked")
  default C asComponent() {
    return (C) this;
  }
}
