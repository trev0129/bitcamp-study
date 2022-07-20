/*
0 * 게시판 관리 애플리케이션
 * 비트캠프-20220704
 */
package com.bitcamp.board;

public class App {

  public static void main(String[] args) {
    welcome();
    loop :   
      while (true) {

        System.out.println("메뉴:");
        System.out.println("  1: 게시판");
        System.out.println("  2: 독서록");
        System.out.println("  3: 방명록");
        System.out.println("  4: 공지사항");
        int mainMenuNo = Prompt.inputInt("메뉴를 선택하세요. [1..4](0: 종료) ");

        switch (mainMenuNo) {
          case 0: break loop;
          case 1: //게시판
            BoardHandler.execute();
            break;
          case 2: break;
          case 3: break;
          case 4: break;
          default: System.out.println("메뉴에 있는 번호를 입력하세요. ");
        } // switch

      } //while문 종료 

    System.out.println("안녕히가세요!");
    Prompt.close();

  } //main

  static void welcome() {
    System.out.println("[게시판 애플리케이션]");
    System.out.println();
    System.out.println("환영합니다!");
    System.out.println();

  }
}
