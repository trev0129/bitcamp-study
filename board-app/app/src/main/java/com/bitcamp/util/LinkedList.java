package com.bitcamp.util;


public class LinkedList {

  int size;
  Node head;
  Node tail;

  public void append(Object value) {
    Node node = new Node(value);

    size++;

    if (tail == null) {
      head = tail = node;
      return;
    }

    tail.next = node;
    node.prev = tail;

    tail = node;

  }

  public Object retrieve(int index) {
    if (index < 0 | index >= size) {
      throw new ListException("인덱스의 범위를 초과했습니다.");
    }
    Node cursor = head;
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }

  public Object delete(int index) {
    if (index < 0 | index >= size) {
      throw new ListException("인덱스의 범위를 초과했습니다.");
    }

    size--;

    Object deleted;

    if (head == tail) {
      deleted = tail.value;
      tail.value = null;
      head = tail = null;
      return deleted;
    }

    Node cursor = head;

    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }

    deleted = cursor.value;

    if (cursor.next != null) {
      cursor.next.prev = cursor.prev;
    } else {
      tail = cursor.prev;
      tail.next = null;
    }
    if (cursor.prev != null) {
      cursor.prev.next = cursor.next;
    } else {
      head = cursor.next;
      head.prev = null;
    }

    cursor.value = null;
    cursor.prev = null;
    cursor.next = null;

    return deleted;
  }

  public int length() {
    return size;
  }

  public Object[] getArray() {
    Object[] arr = new Object[size];
    Node cursor = head;
    for (int i = 0; i < arr.length; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  class Node {
    Object value;
    Node prev;
    Node next;
    public Node(Object value) {
      this.value = value;
    }

  }
}
