package com.bitcamp.study;

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int H = sc.nextInt();
    int M = sc.nextInt();
    int needTime = sc.nextInt();
    int TH = (needTime / 10) / 6;

    if ((M + needTime) > 60) {
      if (H + TH > 23) {
        System.out.print(((H + TH) - 24) + " " + ((60 * TH) - (M + needTime)));
      }
      System.out.print((H + TH) + " " + ((60 * TH) - (M + needTime))); 
    } else {
      System.out.print(H + TH + " " + (M + needTime));
    }
  }
}