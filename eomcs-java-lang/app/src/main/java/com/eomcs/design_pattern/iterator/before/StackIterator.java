package com.eomcs.design_pattern.iterator.before;



public class StackIterator<E> implements Iterator<E> {

  Stack<E> list;

  public StackIterator(Stack<E> list) {
    this.list = list;
  }

  @Override
  public boolean hasNext() {
    return !list.empty();
  }

  @Override
  public E next() {
    return list.pop();
  }
}
