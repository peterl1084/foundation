package com.vaadin.peter.foundation.task;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

public class AvailabilityTests {

  @Test
  public void testSimpleAvailability() {
    Assert.assertTrue(Available.available().isAvailable());
    Assert.assertFalse(Available.unavailable().isAvailable());
  }

  @Test
  public void testUnavailabilityReason() {
    Available unavailable = Available.when(false).orElseUnavailable(() -> "Not available");

    Assert.assertTrue(unavailable.getUnavailabilityReason().isPresent());
    Assert.assertEquals("Not available", unavailable.getUnavailabilityReason().get());
  }

  @Test
  public void testAvailableNotHavingUnavailabilityReason() {
    Available available = Available.when(true).orElseUnavailable(() -> "Not available");
    Assert.assertFalse(available.getUnavailabilityReason().isPresent());
  }

  @Test
  public void testAvailableWhenPresent() {
    Assert.assertFalse(Available.whenPresent(null).isAvailable());
    Assert.assertTrue(Available.whenPresent(new Object()).isAvailable());
    Assert.assertEquals("Not present",
        Available.whenPresent(null).orElseUnavailable(() -> "Not present").getUnavailabilityReason().get());
  }

  @Test
  public void testAvailableWhenPresentAndNotEmpty() {
    Assert.assertFalse(Available.whenPresentAndNotEmpty(null).isAvailable());
    Assert.assertFalse(Available.whenPresentAndNotEmpty(Collections.emptyList()).isAvailable());
    Assert.assertTrue(Available.whenPresentAndNotEmpty(Arrays.asList(1, 2, 3)).isAvailable());
  }
}
