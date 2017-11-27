package com.vaadin.peter.foundation.task;

import java.util.Set;

/**
 * {@link MultiItemTask} is {@link Task} with extended ability to execute and
 * check availability based on multiple items simultaneously.
 * 
 * @author Peter / Vaadin
 *
 * @param <ITEM>
 */
public interface MultiItemTask<ITEM> extends Task<Set<ITEM>> {

  @Override
  void execute(Set<ITEM> items);

  @Override
  Available checkAvailability(Set<ITEM> items);
}
