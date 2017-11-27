package com.vaadin.peter.foundation.toolbar;

import java.util.Set;
import java.util.function.Supplier;

import com.vaadin.peter.foundation.IsComponent;
import com.vaadin.peter.foundation.IsRefreshable;
import com.vaadin.peter.foundation.i18n.Translations;
import com.vaadin.peter.foundation.task.MultiItemTask;
import com.vaadin.peter.foundation.task.Task;
import com.vaadin.server.FontIcon;
import com.vaadin.server.SerializableSupplier;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The {@link ToolBar} is component capable of providing execution controls for
 * various types of {@link Task}s. The {@link ToolBar} can be equipped with
 * regular {@link Task}s as well as {@link MultiItemTask}s that are executed for
 * multiple items simultaneously. The {@link ToolBar} will respect the
 * availability states of tasks associated and will allow user to visually
 * execute only such tasks which are really available for current selection of
 * items accessible via {@link ItemProvider}.
 * 
 * @author Peter / Vaadin
 *
 * @param <ITEM>
 */
public interface ToolBar<ITEM> extends IsComponent<Component>, IsRefreshable {

  /**
   * Adds the given {@link Task} to this {@link ToolBar} and creates a control
   * component to execute and further configure the {@link Task}.
   * 
   * @param task
   * @return {@link ToolBarButton} for launching the task.
   */
  ToolBarButton addTask(Task<ITEM> task);

  /**
   * Adds given {@link MultiItemTask} to this {@link ToolBar} and creates a
   * control component to execute and further configure the {@link MultiItemTask}.
   * 
   * @param task
   * @return {@link ToolBarButton} for launchng the task.
   */
  ToolBarButton addTask(MultiItemTask<ITEM> task);

  /**
   * Associates the given {@link ItemProvider} with this {@link ToolBar} so that
   * whenever this tool bar is refreshed, the current availability of added tasks
   * will be resolved from the item provided.
   * 
   * @param itemProvider
   * @return {@link ToolBar} this.
   */
  ToolBar<ITEM> withItemProvider(ItemProvider<Set<ITEM>> itemProvider);

  /**
   * {@link ItemProvider} is {@link Supplier} for providing the item the
   * {@link ToolBar} {@link Task}s target.
   * 
   * @param <ITEM>
   */
  @FunctionalInterface
  public interface ItemProvider<ITEM> extends SerializableSupplier<ITEM> {
  }

  /**
   * {@link ToolBarButton} is {@link ToolBar} sub interface for control buttons
   * representing added {@link Task}s.
   * 
   * @author Peter / Vaadin
   */
  public interface ToolBarButton extends IsComponent<Button>, IsRefreshable {

    /**
     * Configures this button with given translationKey.
     * 
     * @param translationKey
     * @return {@link ToolBarButton} for further configuration.
     */
    default ToolBarButton withTranslationKey(String translationKey) {
      asComponent().setCaption(Translations.t(translationKey));
      return this;
    }

    /**
     * Configures this button with given icon.
     * 
     * @param icon
     * @return {@link ToolBarButton} for further configuration.
     */
    default ToolBarButton withIcon(FontIcon icon) {
      asComponent().setIcon(icon);
      asComponent().removeStyleName(ValoTheme.BUTTON_ICON_ONLY);
      return this;
    }

    /**
     * Configures this button with given icon in a special way where the caption of
     * the button will be hidden and only the provided icon is shown.
     * 
     * @param icon
     * @return {@link ToolBarButton} for further configuration.
     */
    default ToolBarButton withIconOnly(FontIcon icon) {
      asComponent().setIcon(icon);
      asComponent().addStyleName(ValoTheme.BUTTON_ICON_ONLY);
      return this;
    }
  }
}
