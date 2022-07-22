package com.bitcamp.board.dao;

public class ObjectList {
  private static final int DEFAULT_SIZE = 3;

  protected int length;

  //서브클래스에 직접 접근할 수 있도록 접근 범위를넓힌다.
  protected Object[] list; //protected

  public ObjectList() {
    this.list = new ObjectList[DEFAULT_SIZE];
  }

  public ObjectList(int initCapacity) {
    this.list = new ObjectList[initCapacity];
  }

  // 목록에 저장된  
  public Object[] toArray() {
    Object[] arr = new Object[this.length];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = this.list[i];
    }
    return arr;
  }

  // 목록에서 지정된 인덱스의 값을 찾아 리턴한다.
  // ->목록에서 값을 꺼낼 때 인덱스에 기반해서 꺼낸다.
  // -> 번호가 아닌 이메일이나 아이디를 가지고 데이터를 꺼낼 수도 있기 문이다.
  public Object get(int index) {
    if (index < 0 || index >=this.length) {
      return null;
    }
    return list[index];
  }

  // Board 인스턴스를 배열에 저장한다.
  public void add(Object obj) {
    if (this.length == this.list.length) {
      grow();
    }
    this.list[this.length++] = obj;
  }
  private void grow() {
    int newSize = this.list.length + (this.list.length >> 1);
    Object[] newArray = new Object[newSize];
    for (int i = 0; i < this.list.length; i++) {
      newArray[i] = this.list[i];
    }
    this.list = newArray;
  }
  public  boolean remove(int index) {
    if (index < 0 || index >=this.length) {
      return false;
    }
    for (int i = index + 1; i < this.length; i++) {
      this.list[i - 1] = this.list[i];
    }
    this.list[--this.length] = null;
    return true;
  }
}
