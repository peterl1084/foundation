package com.vaadin.peter.foundation.formatter;

import org.springframework.stereotype.Component;

@Component
public class DummyFormatter implements Formatter {

  @Override
  public String formatToText(Object value) {
    if (value == null) {
      return null;
    }

    return value.toString();
  }
}
