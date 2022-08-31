package com.min.test;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

  static final int ROCK = 0;
  static final int PAPER = 1;
  static final int SCISSORS = 2;

  public static void main(String[] args) {
    printMainMenu();
    printMenu();
    try (Scanner sc = new Scanner(System.in)) {
      while (true) {
        String select = sc.nextLine();
        if (select.equals("나가기")) {
          System.out.println("안녕히 가세요!");
          break;
        }
        matchResult(select);
        printBlank();
        printMenu();
      }
    } 
  } // main

  static void printMainMenu() {
    System.out.println("[도전! 가위바위보!]");
    System.out.println("저희 '도전! 가위바위보' 게임에 오신 걸 환영합니다.");
  }

  static void printMenu() {
    System.out.println("가위, 바위 보 중 하나를 입력하세요![종료:나가기]");
  }

  static void printBlank() {
    System.out.println();
  }

  static String randomResult(int ran) {
    if (ran < 1) {
      return "바위";
    } else if (ran > 1) {
      return "가위";
    } else {
      return "보"; 
    }
  }

  static void matchResult(String select) {
    switch (select) {
      case "가위":   
        System.out.printf("당신 : 가위\n");
        match(SCISSORS);
        break;
      case "바위":
        System.out.printf("당신 : 바위\n");
        match(ROCK);
        break;
      case "보":
        System.out.printf("당신 : 보\n");
        match(PAPER);
        break;
      default: 
        System.out.println("잘못입력하였습니다.");
        break;
    }
  }

  static void match(int choice) {
    int ran = new Random().nextInt(3);
    String result = randomResult(ran);

    if (choice > ran) {
      if (choice == 2 && ran == 0) {
        System.out.println("컴퓨터 : " + result);
        System.out.println("컴퓨터가 이겼습니다!");
      } else {
        System.out.println("컴퓨터 : " + result);
        System.out.println("당신이 이겼습니다!");
      }
    } else if (choice < ran) {
      if (choice == 0 && ran == 2) {
        System.out.println("컴퓨터 : " + result);
        System.out.println("당신이 이겼습니다!");
      } else {
        System.out.println("컴퓨터 : " + result);
        System.out.println("컴퓨터가 이겼습니다!");
      }
    } else if (choice == ran) {
      System.out.println("컴퓨터 : " + result);
      System.out.println("비겼습니다!");
    }
  }
}
