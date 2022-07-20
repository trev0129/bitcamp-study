/*
 * 게시판 메뉴 처리 클래스
 */
package com.bitcamp.board;

public class BoardHandler {

  static int boardCount = 0; // 저장된 게시글의 개수    

  static final int SIZE = 3;
  // Board 인스턴스의 주소를 저장할 수 있는 배열을 만든다.
  static Board[] boards = new Board[SIZE];

  static void execute() {
    while (true) {
      System.out.println("게시판:");
      System.out.println("  1: 목록");
      System.out.println("  2: 상세보기");
      System.out.println("  3: 등록");
      System.out.println("  4: 삭제");
      System.out.println("  5: 변경");
      System.out.println();
      int menuNo = Prompt.inputInt("메뉴를 선택하세요. [1..5](0: 이전) ");
      displayHeadLine();

      switch (menuNo) {
        case 0: return;
        case 1: BoardHandler.processList(); break;
        case 2: BoardHandler.processDetail(); break;
        case 3: BoardHandler.processInput(); break;
        case 4: BoardHandler.processDelite(); break;
        case 5: BoardHandler.processUpdate(); break;
        default: System.out.println("메뉴에 있는 번호를 입력하세요. ");
      }  // 게시판 while
    }
  }

  static void displayHeadLine() {
    System.out.println("-------------------------------------");
  }
  static void displayBlankLine() {
    System.out.println(); // 메뉴 처리 후 빈줄 출력 
  }

  static void processList() {
    java.text.SimpleDateFormat formatter = 
        new java.text.SimpleDateFormat("yyyy-MM-dd");

    System.out.println("[게시글 목록]");
    System.out.println("번호 제목 조회수 작성자 등록일");

    for (int i = 0; i < boardCount; i++) {
      Board board = boards[i];
      java.util.Date date = new java.util.Date(board.createdDate);
      String dateStr = formatter.format(date);
      System.out.printf("%d\t%s\t%d\t%s\t%s\n", 
          board.no, board.title, board.viewCount, board.writer, dateStr);
    }

  }

  static void processDetail() {
    System.out.println("[게시글 상세보기]");

    int boardNo = Prompt.inputInt("조회할 게시글 번호? ");

    Board board = null;
    for (int i = 0; i < boardCount; i++) {
      if (boards[i].no == boardNo) {
        board = boards[i];
        break;
      }
    }

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }
    System.out.printf("번호: %d\n", board.no);
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("조회수: %d\n", board.viewCount);
    System.out.printf("작성자: %s\n", board.writer);
    java.util.Date date= new java.util.Date(board.createdDate);
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM\n", date);

  }

  static void processInput() {
    System.out.println("[게시글 등록]");

    if (boardCount == SIZE) {
      System.out.println();
      System.out.println("게시글을 더 이상 저장할 수 없습니다.");
      return;
    }
    Board board = new Board();
    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer = Prompt.inputString("작성자? ");
    board.password = Prompt.inputString("암호? ");

    board.no = boardCount == 0 ? 1 : boards[boardCount - 1] .no + 1; 

    board.viewCount = 0;
    board.createdDate = System.currentTimeMillis();

    // 새로만든 인스턴스 주소를 레퍼런스 배열에 저장한다.
    boards[boardCount] = board;

    boardCount++;
  }

  static void processDelite() {
    System.out.println("[게시글 삭제]");
    int boardNo = Prompt.inputInt("삭제할 게시글 번호? ");
    int boardIndex = -1;
    for (int i = 0; i < boardCount; i++) {
      if (boards[i].no == boardNo) {
        boardIndex = i;
        break;
      }
    }
    if (boardIndex == -1) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }
    for ( int i = boardIndex + 1; i < boardCount; i++) {
      boards[i - 1] = boards[i];
    } 
    boards[--boardCount] = null;
    System.out.println("삭제하였습니다.");
  }

  public static void processUpdate() {
    System.out.println("[게시글 변경]");
    int boardNo = Prompt.inputInt("변경할 게시글 번호? ");
    int boardIndex = -1;
    for (int i = 0; i < boardCount; i++) {
      if (boards[i].no == boardNo) {
        boardIndex = i;
        break;
      }
    }
    if (boardIndex == -1) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }
    Board board = new Board();
    Board updateBoard = boards[boardIndex];
    board.title = Prompt.inputString("제목? (" + updateBoard.title + ")");
    board.content = Prompt.inputString("내용? (" + updateBoard.content + ")");

    while (true) {
      char str = Prompt.inputChar("변경하시겠습니까? (y/n)");
      if (str == 'y') {
        updateBoard.title = board.title;
        updateBoard.content = board.content;
        System.out.println("변경하였습니다.");
        break;
      } else if (str == 'n') {
        System.out.println("취소하였습니다.");
        break;
      } else {
        System.out.println("잘못입력하였습니다.");
      }
    }
  }

}
