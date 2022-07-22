package com.min.quiz.ex02;

// 출처: codefights.com
//
// 숫자 배열에서 각 이웃하는 숫자간의 차가 가장 큰 것을 알아내기
// 예)
// [2, 4, 1, 0] => 3
// 
// [시간 복잡도]
// - ?
public class Test01 {

  public static void main(String[] args) {
    System.out.println(maxDiff(new int[]{2, 4, 1, 0}) == 3);
    System.out.println(maxDiff(new int[]{3, 1, 4, 3, 8, 7}) == 5);
  }

  static int maxDiff(int[] values) {
    int answer = 0;
    for (int i = 0; i < values.length - 1; i++) {
      int abs = Math.abs(values[i] - values[i + 1]);
      answer = abs > answer ? abs : answer;
    }
    return answer;
  }
}
