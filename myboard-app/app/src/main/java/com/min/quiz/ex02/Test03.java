package com.min.quiz.ex02;

// 출처: codefights.com
//
// 숫자 배열이 있다.
// 나누어 0이 떨어지는 수가 몇 쌍이 있는지 구하라!
//
// [시간 복잡도]
// - ?
//
public class Test03 {

  public static void main(String[] args) {
    int[] values = {2, 4, 8};
    System.out.println(divisorsPairs(values) == 3);

  }

  public static int divisorsPairs(int[] sequence) {
    int result = 0;
    // 이 메서드를 완성하시오!
    for (int i = 0; i < sequence.length; i++) {
      for (int j = i + 1; j < sequence.length; j++) {
        int iVlaue = sequence[i]; int jValue = sequence[j];
        if (iVlaue % jValue == 0 || jValue % iVlaue == 0) {
          result++;
        }
      }
    }
    return result;
  }
}
