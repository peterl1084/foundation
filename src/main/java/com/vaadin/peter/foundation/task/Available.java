package com.vaadin.peter.foundation.task;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import com.vaadin.server.SerializableSupplier;

public class Available implements Serializable {

  private final boolean available;
  private SerializableSupplier<String> unavailabilityReason;

  private Available(boolean available) {
    this.available = available;
  }

  public boolean isAvailable() {
    return available;
  }

  public static Available available() {
    return new Available(true);
  }

  public static Available unavailable() {
    return new Available(false);
  }

  public static Available when(boolean condition) {
    return new Available(condition);
  }

  public static Available whenPresent(Object object) {
    return when(object != null);
  }

  public static Available whenPresentAndNotEmpty(Collection<? extends Object> collection) {
    return when(collection != null && !collection.isEmpty());
  }

  public Available orElseUnavailable(SerializableSupplier<String> unavailabilityReason) {
    if (isAvailable()) {
      return this;
    }

    this.unavailabilityReason = unavailabilityReason;
    return this;
  }

  public Optional<String> getUnavailabilityReason() {
    if (unavailabilityReason == null) {
      return Optional.empty();
    }

    return Optional.ofNullable(unavailabilityReason.get());
  }
}
