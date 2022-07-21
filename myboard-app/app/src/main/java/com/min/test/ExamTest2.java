package com.min.test;

import com.min.test.util.Calculater;

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
  public static void main(String[] args) {

    Calculater c1 = new Calculater();
    Calculater c2 = new Calculater();


    c1.plus(3);
    c1.plus(2);
    c1.minus(1);
    c1.multiple(7);
    c1.divide(3);

    System.out.printf("result = %d\n", c1.result);

    c2.plus(3);
    c2.plus(1);
    c2.minus(2);
    c2.multiple(7);
    c2.divide(3);

    System.out.printf("result = %d\n", c2.result);
  }

}
