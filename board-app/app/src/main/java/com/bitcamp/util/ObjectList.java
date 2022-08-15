package com.bitcamp.util;

public class ObjectList {

  private static final int DEFAULT_CAPACITY = 3;

  private int size;
  private Object[] elementData;

  public ObjectList() {
    this.elementData = new Object[DEFAULT_CAPACITY];
  }

  public ObjectList(int initCapacity) {
    this.elementData = new Object[initCapacity];
  }

  public Object[] toArray() {
    Object[] arr = new Object[this.size];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = this.elementData[i];
    }
    return arr;
  }

  public  Object get(int index) throws Exception{
    if (index < 0 || index >= size) {
      throw new ListException("인덱스가 무효함!");
    }
    return elementData[index];
  }

  // Board 인스턴스를 배열에 저장한다.
  public void add(Object e) {
    if (this.size == this.elementData.length) {
      grow();
    }
    this.elementData[this.size++] = e;
  }

  public boolean remove(int index) throws Exception {
    if (index < 0 || index >= size) {
      throw new ListException("인덱스가 무효함!");
    }

    // 삭제할 항목의 다음 항목을 앞으로 당긴다.
    for (int i = index + 1; i < this.size; i++) {
      this.elementData[i - 1] = this.elementData[i];
    }

    // 게시글 개수를 한 개 줄인 후 
    // 맨 뒤의 있던 항목의 주소를 0으로 설정한다.
    this.elementData[--this.size] = null;

    return true;
  }

  public int size() {
    return size;
  }

  private void grow() {
    int newSize = this.elementData.length + (this.elementData.length >> 1);
    Object[] newArray = new Object[newSize];
    for (int i = 0; i < this.elementData.length; i++) {
      newArray[i] = this.elementData[i];
    }
    this.elementData = newArray;
  }

}
