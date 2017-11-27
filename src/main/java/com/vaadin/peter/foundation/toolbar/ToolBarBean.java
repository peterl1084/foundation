package com.vaadin.peter.foundation.toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.vaadin.spring.annotation.PrototypeScope;

import com.vaadin.peter.foundation.task.MultiItemTask;
import com.vaadin.peter.foundation.task.Task;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;

@SpringComponent
@PrototypeScope
public class ToolBarBean<ITEM> extends CssLayout implements ToolBar<ITEM> {

  private List<ToolBarButton> tasks;
  private ItemProvider<Set<ITEM>> itemProvider;
  private boolean refreshRequested;

  private Set<ITEM> currentItems;

  public ToolBarBean() {
    tasks = new ArrayList<>();
  }

  @Override
  public ToolBarButton addTask(Task<ITEM> task) {
    SingleItemToolBarButton button = new SingleItemToolBarButton(task);
    tasks.add(button);
    addComponent(button);
    refresh();
    return button;
  }

  @Override
  public ToolBarButton addTask(MultiItemTask<ITEM> task) {
    MultiItemToolBarButton button = new MultiItemToolBarButton(task);
    tasks.add(button);
    addComponent(button);
    refresh();
    return button;
  }

  @Override
  public ToolBar<ITEM> withItemProvider(ItemProvider<Set<ITEM>> itemProvider) {
    this.itemProvider = itemProvider;
    return this;
  }

  @Override
  public void refresh() {
    refreshRequested = true;
    markAsDirty();
  }

  @Override
  public void beforeClientResponse(boolean initial) {
    if (refreshRequested) {
      try {
        doRefresh();
      } finally {
        refreshRequested = false;
      }
    }

    super.beforeClientResponse(initial);
  }

  protected void doRefresh() {
    currentItems = getItemProvider().map(provider -> provider.get()).orElse(Collections.emptySet());
    tasks.forEach(ToolBarButton::refresh);
  }

  protected Optional<ItemProvider<Set<ITEM>>> getItemProvider() {
    return Optional.ofNullable(itemProvider);
  }

  private class SingleItemToolBarButton extends Button implements ToolBarButton {
    private final Task<ITEM> task;

    public SingleItemToolBarButton(Task<ITEM> task) {
      this.task = Objects.requireNonNull(task);
      addClickListener(this::onClick);
    }

    protected void onClick(ClickEvent event) {
      if (currentItems.isEmpty()) {
        return;
      }

      ITEM firstSelectedItem = currentItems.iterator().next();
      if (task.isAvailable(firstSelectedItem)) {
        task.execute(firstSelectedItem);
      }
    }

    @Override
    public void refresh() {
      if (currentItems.size() == 1) {
        ITEM itemToCheck = currentItems.iterator().next();
        boolean available = task.isAvailable(itemToCheck);
        setEnabled(available);
      } else {
        setEnabled(false);
      }
    }
  }

  private class MultiItemToolBarButton extends Button implements ToolBarButton {
    private final MultiItemTask<ITEM> task;

    public MultiItemToolBarButton(MultiItemTask<ITEM> task) {
      this.task = Objects.requireNonNull(task);
      addClickListener(this::onClick);
    }

    protected void onClick(ClickEvent event) {
      if (currentItems.isEmpty()) {
        return;
      }

      if (task.isAvailable(currentItems)) {
        task.execute(currentItems);
      }
    }

    @Override
    public void refresh() {
      setEnabled(task.isAvailable(currentItems));
    }
  }
}
