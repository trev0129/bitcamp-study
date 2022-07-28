package com.bitcamp.util;

public class ObjectList {
  private static final int DEFAULT_SIZE = 3;

  private int size; 
  private Object[] elementData; 

  public ObjectList() {
    this.elementData = new Object[DEFAULT_SIZE];
  }

  public ObjectList(int initCapacity) {
    this.elementData = new Object[initCapacity];
  }

  public void add(Object e) {
    if (this.size == this.elementData.length) {
      grow();
    }
    this.elementData[this.size++] = e;
  }

  public Object[] toArray() {
    Object[] arr = new Object[this.size];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = this.elementData[i];
    }
    return arr;
  }

  public Object get(int index) throws Throwable {
    if (index < 0 || index >= size) {
      //인덱스가 무효하면 예외를 발시킨다.
      //예외정보를 객체에 담아서 호출한 쪽으로 던진다.
      //예외정보는 던질 수 있는 객체(java.lang.Throwable)에 담아야 한다.
      //단, 메서드 선언부에 어떤 예외를 던지는 지 표시해야 한다.
      throw new Throwable("인덱스가 무효함.");
    }
    return elementData[index];
  }

  public boolean remove(int index) throws Throwable{
    if (index < 0 || index >= size) {
      // 인덱스가 무효할 때 false를 리턴하는 대신
      // 에외정보와 상황을 보고한다.
      throw new Throwable("인덱스가 무효합니다.");
    }
    for (int i = index + 1; i < this.size; i++) {
      this.elementData[i - 1] = this.elementData[i];
    }
    this.elementData[--this.size] = null;

    return true;
  }

  private void grow() {
    int newSize = this.elementData.length + (this.elementData.length >> 1);
    Object[] newArray = new Object[newSize];
    for (int i = 0; i < this.elementData.length; i++) {
      newArray[i] = this.elementData[i];
    }
    this.elementData = newArray;
  }

  public int size() {
    return size;
  }

}
