package org.eclipse.triquetrum.processing.model.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.eclipse.triquetrum.processing.model.Attribute;
import org.eclipse.triquetrum.processing.model.AttributeHolder;
import org.eclipse.triquetrum.processing.model.ResultBlock;
import org.eclipse.triquetrum.processing.model.ResultItem;
import org.eclipse.triquetrum.processing.model.Task;

public class ResultBlockImpl implements ResultBlock {
  private static final long serialVersionUID = 1932134372473000309L;

  private Long id;
  private Date creationTS;
  private Task task;
  private String type;
  private Map<String, ResultItem<? extends Serializable>> items = new ConcurrentHashMap<>();
  private AttributeHolder attributeHolder = new AttributeHolderImpl();

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public Date getCreationTS() {
    return creationTS;
  }

  @Override
  public Attribute<? extends Serializable> getAttribute(String name) {
    return attributeHolder.getAttribute(name);
  }

  @Override
  public Attribute<? extends Serializable> putAttribute(Attribute<? extends Serializable> attribute) {
    return attributeHolder.putAttribute(attribute);
  }

  @Override
  public Set<String> getAttributeNames() {
    return attributeHolder.getAttributeNames();
  }

  @Override
  public Set<Attribute<? extends Serializable>> getAttributes() {
    return attributeHolder.getAttributes();
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public ResultItem<? extends Serializable> putItem(ResultItem<? extends Serializable> item) {
    return items.put(item.getName(), item);
  }

  @Override
  public Stream<ResultItem<? extends Serializable>> getAllItems() {
    return items.values().stream();
  }

  @Override
  public ResultItem<? extends Serializable> getItemForName(String name) {
    return items.get(name);
  }

  @Override
  public Task getTask() {
    return task;
  }

  @Override
  public String toString() {
    return "ResultBlockImpl [id=" + id + ", creationTS=" + creationTS + ", type=" + type + "]";
  }
}
