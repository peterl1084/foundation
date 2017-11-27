package com.vaadin.peter.foundation.task;

/**
 * {@link Task} is a top level interface for anything that can be executed in
 * specific context as well as which availability can be checked with
 * {@link Available}.
 * 
 * @author Peter / Vaadin
 *
 * @param <ITEM>
 */
public interface Task<ITEM> {

  /**
   * Executes this task for given item context.
   * 
   * @param item
   */
  void execute(ITEM item);

  /**
   * Checks if this {@link Task} is available for given item context provided.
   * 
   * @param item
   * @return {@link Available} according to evaluation.
   */
  Available checkAvailability(ITEM item);

  /**
   * Simple short hand test for asserting availability for given item context.
   * 
   * @param item
   * @return true if available, false otherwise.
   */
  default boolean isAvailable(ITEM item) {
    return checkAvailability(item).isAvailable();
  }
}
