package com.vaadin.peter.foundation.task;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import com.vaadin.server.SerializableSupplier;

/**
 * {@link Available} is general purpose mechanism for asserting availability of
 * something. The class provides various helper methods to easily provide
 * Availability check based on certain rules.
 * 
 * @author Peter / Vaadin
 */
public class Available implements Serializable {
  private final boolean available;
  private SerializableSupplier<String> reasonSupplier;

  private Available(boolean available) {
    this.available = available;
  }

  /**
   * @return current availability
   */
  public boolean isAvailable() {
    return available;
  }

  /**
   * Adds unavailability reason supplier if this {@link Available} is currently
   * unavailable.
   * 
   * @param reasonSupplier
   * @return {@link Available} this
   */
  public Available orElseUnavailable(SerializableSupplier<String> reasonSupplier) {
    if (isAvailable()) {
      return this;
    }

    this.reasonSupplier = reasonSupplier;
    return this;
  }

  /**
   * @return Optional of unavailability reason or empty optional if this
   *         {@link Available} is available or if provided unavailability reason
   *         supplier does not provide reason.
   */
  public Optional<String> getUnavailabilityReason() {
    return Optional.ofNullable(reasonSupplier).map(SerializableSupplier::get);
  }

  /**
   * @return {@link Available} as true.
   */
  public static Available available() {
    return new Available(true);
  }

  /**
   * @return {@link Available} as false.
   */
  public static Available unavailable() {
    return new Available(false);
  }

  /**
   * @param condition
   * @return {@link Available} based on provided condition.
   */
  public static Available when(boolean condition) {
    return new Available(condition);
  }

  /**
   * @param object
   * @return {@link Available} based on presence of provided object. (false for
   *         null).
   */
  public static Available whenPresent(Object object) {
    return when(object != null);
  }

  /**
   * @param collection
   * @return {@link Available} true or false based on presence and non-emptiness
   *         of provided collection.
   */
  public static Available whenPresentAndNotEmpty(Collection<? extends Object> collection) {
    return when(collection != null && !collection.isEmpty());
  }
}
