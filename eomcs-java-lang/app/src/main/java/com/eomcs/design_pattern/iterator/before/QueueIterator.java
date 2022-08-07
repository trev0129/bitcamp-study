package com.eomcs.design_pattern.iterator.before;

public class QueueIterator<E> implements Iterator<E> {
  Queue<E> list;
  public QueueIterator(Queue<E> list) {
    this.list = list;
  }
  @Override
  public boolean hasNext() {
    return !list.empty();
  }
  @Override
  public E next() {
    return list.poll();
  }
}
