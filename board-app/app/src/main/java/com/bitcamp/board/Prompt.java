/*
 * 키보드 입력을 받는 도구를 구비하고 있다.
 */
package com.bitcamp.board;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class Prompt {

  static java.util.Scanner keyboardInput = new java.util.Scanner(System.in);
  // 특정 날짜정보에서 값을 추출하여 특정 포맷의 문자열을 만들어줄 도구 준비
   
  static int inputInt() {
    String str = keyboardInput.nextLine();
    return Integer.parseInt(str);
  }

  static int inputInt(String title) {
    System.out.println(title);
    String str = keyboardInput.nextLine();
    return Integer.parseInt(str);
  }

  static String inputString() {
    return keyboardInput.nextLine();
  }

  static String inputString(String title) {
    System.out.println(title);
    return keyboardInput.nextLine();
  }

  static void close() {
    keyboardInput.close();
  }

}
