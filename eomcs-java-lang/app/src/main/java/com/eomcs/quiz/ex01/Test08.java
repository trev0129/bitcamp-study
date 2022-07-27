package com.eomcs.quiz.ex01;

// [문제] 
// 음이 아닌 두 정수의 곱셈을 수행하는 프로그램을 작성하라.
// 조건:
// => 2진수로 변환하여 비트 연산자만 이용하여 곱셈을 수행한다.
//
// [훈련 목표]
// - 비트 연산자를 이용한 2진수의 곱셈 수행 방법
//
// [시간 복잡도]
// - ?
//
public class Test08 {
  public static void main(String[] args) {
    System.out.println(multiply(5, 3) == 15);
    System.out.println(multiply(17, 13) == 221);
  }

  static int multiply(int a, int b) {  
    int result = 0;
    while (b != 0) {
      if ((b & 1) == 1) {
        result += a;
      }
      b = b >> 1;
      a = a << 1;
    }
    return result;
  }

  //    int result = 0;
  //    int cnt = 1;
  //    while(true)
  //    {
  //      if( (b >> cnt) < 1 )break;
  //      cnt++;
  //    }
  //    for(int i = 0 ; i < cnt; i++) {
  //      if((b >> i & 1) == 1)
  //      {
  //        result += a << i;
  //      }
  //    }
  //    return result;
  //  }
}


