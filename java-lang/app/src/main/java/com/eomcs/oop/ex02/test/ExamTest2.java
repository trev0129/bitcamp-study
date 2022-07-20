package com.eomcs.oop.ex02.test;

//# 관련된 기능(메서드)을 묶어 분류하기
//1) 분류 전
//2) 메서드를 클래스로 묶어 분류하기
//3) 클래스 변수 도입
//4) 클래스 변수의 한계 확인
//5) 인스턴스 변수 도입
//6) 인스턴스 메서드 활용
//7) 패키지 멤버 클래스로 분리
//8) 클래스를 역할에 따라 패키지로 분류하기
//
public class ExamTest2 {

  static class Calculater {
    static int plus(int a, int b) {
      return a + b;
    }

    static int minus(int a, int b) {
      return a - b;
    }

    static int multiple(int a, int b) {
      return a * b;
    }

    static int divide(int a, int b) {
      return a / b;
    }
  }

  public static void main(String[] args) {

    int result = 0;

    result = Calculater.plus(2, 3);
    result = Calculater.minus(result, 1);
    result = Calculater.multiple(result, 7);
    result = Calculater.divide(result, 3);

    System.out.printf("result = %d\n", result);
  }


}
