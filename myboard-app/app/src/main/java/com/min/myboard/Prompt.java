package com.min.myboard;

public class Prompt {
  static java.util.Scanner keyboardInput = new java.util.Scanner(System.in);

  static int inputInt() {
    String str = keyboardInput.nextLine();
    return Integer.parseInt(str);
  }
  static int inputInt(String s) {
    System.out.println(s);
    String str = keyboardInput.nextLine();
    return Integer.parseInt(str);
  }
  static String inputString() {
    return keyboardInput.nextLine();
  }
  static String inputString(String s) {
    System.out.println(s);
    return keyboardInput.nextLine();
  }
  static void close() {
    keyboardInput.close();
  }
}
