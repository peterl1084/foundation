package com.vaadin.peter.foundation.datagrid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * Enables the {@link DataGridConfiguration} for current application context.
 * 
 * @author Peter / Vaadin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DataGridConfiguration.class)
public @interface EnableDataGrid {

}
