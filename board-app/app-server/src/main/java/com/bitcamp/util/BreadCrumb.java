package com.bitcamp.util;

import java.util.Stack;

public class BreadCrumb {

  public Stack<String> menuStack = new Stack<>();

  // 스레드마다 별도로 breadcrumb 객체를 따로 보관할 수 있도록 보관소를 준비한다.
  static ThreadLocal<BreadCrumb> localManager = new ThreadLocal<>();

  public static BreadCrumb getBreadcrumbOfCurrentThread() {
    //  스레드 로컬 관리자를 통해 현재 스레드 보관소에 저장되어 있는 
    // breadcrumb 객체를 달라고 한다.
    return localManager.get();
  }

  public BreadCrumb() {
    // 스레드 로컬 관리자에게 현재 스레드 전용 보소에
    // breadcrumb 객체를 보관해 달라고 요청한다.
    localManager.set(this);
  }

  public void put(String menu) {
    menuStack.push(menu);
  }

  public void pickUp() {
    menuStack.pop();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (String title : menuStack) {
      if (!builder.isEmpty()) {
        builder.append(" > ");
      }
      builder.append(title);
    }
    return builder.toString();
  }



}
