package com.min.quiz.ex01;

// [문제] 
// 두 위치의 비트 값을 맞교환 하라! 
// 예) 값:   0b00101100_01110001
//     2번째(2^2 자리) 비트와 13번째(2^13 자리) 비트s
//     결과: 0b00001100_01110101
//  
// [훈련 목표]
// - 관계 연산자 및 비트 연산자, 비트 이동 연산자 활용
// - 반복문 활용
// - 메서드 파라미터 및 리턴 값 다루기
//
public class Test03x {

  public static void main(String[] args) {
    int r = swapBits(0b00101100_01110001, 2, 13);
    System.out.println(r == 0b00001100_01110101); // true

    r = swapBits(0b01010111_01100011, 6, 10);
    System.out.println(r == 0b01010111_01100011); // true
  }

  static int swapBits(int value, int i, int j) {
    if (((value >>> i) & 1) != ((value >>> j) & 1)) {
      int bitMask = (1 << i) | (1 << j);
      value ^= bitMask;
    }
    return value;


    /* 내 답변
    int result, iBit, jBit, iPosition, jPosition;

    //i, j 값을 그대로 알아내고 위치를 바꿈, 그리고 나머지는 0으도 함
    iBit = ((value >>> i) & 1) << j;
    jBit = ((value >>> j) & 1) << i; 

    //i,j 자리 무조건 1로 만들고 나머지는 0으로 함 
    iPosition = 1 << i;
    jPosition = 1 << j; 

    // ~연산자 활용 해서 그 자리만 0이되게 함
    result = value & ~iPosition; 
    result = value & ~jPosition; 

    // 빈자리를 바꾼 값으로  각각 채워줌 
    result |= iBit;
    result |= jBit;


    return result;
     */
    /*
    static int swapBits(int value, int i, int j) {
      int set1 = (value >> i) & ((1 << 2) - 1);
      int set2 = (value >> j) & ((1 << 2) - 1);
      int xor = (set1 ^ set2);

      xor = (xor << i) | (xor << j);

      int result = value ^ xor;

      return result;
     */
  }
}
