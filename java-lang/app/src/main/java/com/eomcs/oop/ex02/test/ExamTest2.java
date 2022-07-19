package com.eomcs.oop.ex02.test;

import com.eomcs.oop.ex02.test.domain.Score2;
public class ExamTest2 {

  public static void main(String[] args) {

    Score2 s1 = new Score2("홍길동", 100, 90, 85);
    printScore(s1);

    Score2 s2 = new Score2("임꺽정", 90, 80, 75);
    printScore(s2);

    Score2 s3 = new Score2("유관순", 80, 70, 65);
    printScore(s3);

  }
  static void printScore(Score2 s) {
    System.out.printf("%s: %d, %d, %d, %d, %.1f\n", 
        s.name, s.kor, s.eng, s.math, s.sum, s.aver);
  }
}
