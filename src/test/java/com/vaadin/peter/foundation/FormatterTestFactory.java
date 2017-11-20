package com.vaadin.peter.foundation;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vaadin.peter.foundation.formatter.Formatter;

@Configuration
public class FormatterTestFactory {

  @Bean
  protected Formatter provideFormatter() {
    return Mockito.mock(Formatter.class);
  }
}
