package com.bitcamp.handler;

import com.bitcamp.board.App;
import com.bitcamp.util.Prompt;

public abstract class AbstractHandler implements Handler {

  String[] menus;

  public AbstractHandler(String[] menus) {
    this.menus = menus;
  }

  protected void printMenus() {
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("  %d: %s\n", i + 1, menus[i]);
    }
  }

  private static void printHeadline() {
    System.out.println("=========================================");
  }

  private static void printBlankLine() {
    System.out.println(); // 메뉴를 처리한 후 빈 줄 출력
  }

  protected static void printTitle() {
    System.out.printf("%s:\n", App.breadcrumbMenu);
  }

  @Override
  public void execute() {
    while (true) {
      printTitle();
      printMenus();
      printBlankLine();

      try {
        int menuNo = Prompt.inputInt("메뉴를 선택하세요[1..5](0: 이전) ");

        if (menuNo < 0 || menuNo > menus.length) {
          System.out.println("메뉴 번호가 옳지 않습니다!");
          continue; // while 문의 조건 검사로 보낸다.

        } else if (menuNo == 0) {
          return; // 메인 메뉴로 돌아간다.
        }

        App.breadcrumbMenu.push(menus[menuNo - 1]);

        printHeadline();

        printTitle();

        service(menuNo);

        printBlankLine();

        App.breadcrumbMenu.pop();

      } catch (Exception ex) {
        System.out.printf("예외 발생: %s\n", ex.getMessage());
      }
    } // while
  }

  public abstract void service(int menuNo);

}
