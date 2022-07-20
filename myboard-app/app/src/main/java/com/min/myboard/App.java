package com.min.myboard;

public class App {




  public static void main(String[] args) {

    welcome();

    loop : while (true) {
      displayMenu();
      int menuNo = Prompt.inputInt("번호를 입력하세요.[1..5][종료: 0]");
      displayLine();

      switch(menuNo) {
        case 0: break loop;
        case 1: BoardHandler.processList(); break;
        case 2: BoardHandler.processDetail(); break;
        case 3: BoardHandler.processInput(); break;
        case 4: BoardHandler.processDelite(); break;
        case 5: BoardHandler.processUpdate(); break;
        default: System.out.println("메뉴 번호가 옳지 않습니다.");
      } // switch

      displayBlankLine();
    } // while

    Prompt.close();
    System.out.println("안녕히 가세요!");
  }
  static void welcome() {
    System.out.println("[게시글 어플리케이션!]");
    System.out.println();
    System.out.println("환영합니다!");
    System.out.println();

  }
  static void displayMenu() {
    System.out.println("메뉴");
    System.out.println("1: 게시글 목록");
    System.out.println("2: 게시글 상세보기");
    System.out.println("3: 게시글 작성");
    System.out.println("4: 게시글 삭제");
    System.out.println("5: 게시글 변경");
    System.out.println();
  }
  static void displayLine() {
    System.out.println("---------------------------------");
  }
  static int promptMenu() {
    return Prompt.inputInt();
  }
  static void displayBlankLine() {
    System.out.println();
  }
}
