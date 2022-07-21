package com.min.test.util;

public class Calculater {
  public int result = 0;

  public int plus(int b) {
    return this.result += b;
  }

  public int minus(int b) {
    return this.result -= b;
  }

  public int multiple(int b) {
    return this.result *= b;
  }

  public int divide(int b) {
    return this.result /= b;
  }

  public static void abs(int b) {
    b = b >= 0? b : b* -1;
  }
}
