package com.min.myboard;

public class BoardHandler {

  static int boardCount = 0;

  static final int SIZE = 3;

  static Board[] boards = new Board[SIZE];

  static void processList() {
    java.text.SimpleDateFormat formatter = 
        new java.text.SimpleDateFormat("yyyy-MM-dd");

    System.out.println("[게시글 목록]");
    System.out.println("번호 제목 조회수 작성자 등록일");

    for ( int i = 0; i < boardCount; i++) {
      java.util.Date date = new java.util.Date(boards[i].createDate);
      String dateStr = formatter.format(date);
      System.out.printf("%d\t%s\t%d\t%s\t%s\n",
          boards[i].no, boards[i].title, boards[i].viewCount, boards[i].writer, dateStr);
    }
  }
  static void processDetail() {
    System.out.println("[게시글 상세보기]");
    int boardNo = Prompt.inputInt("게시글 번호? ");

    int boardIndex = -1;
    for (int i = 0 ; i < boardCount; i++) {
      if (boards[i].no == boardNo) {
        boardIndex = i;
        break;
      }
    }
    if (boardIndex == -1) {
      System.out.println("잘못 입력하셨습니다.");
      return;

    }
    System.out.printf("번호: %d\n", boards[boardIndex].no);
    System.out.printf("제목: %s\n", boards[boardIndex].title);
    System.out.printf("내용: %s\n", boards[boardIndex].content);
    System.out.printf("조회수: %d\n", boards[boardIndex].viewCount);
    System.out.printf("작성자: %s\n", boards[boardIndex].writer);
    java.util.Date date = new java.util.Date(boards[boardIndex].createDate);
    System.out.printf("등록일:  %tY-%1$tm-%1$td %1$tH:%1$tM\n", date);  

  }
  static void processInput() {
    if (boardCount == SIZE) {
      System.out.println("더 이상 작성할 수 없습니다.");
      return;
    }
    System.out.println("[게시글 등록]");
    boards[boardCount].title = Prompt.inputString("제목? ");
    boards[boardCount].content = Prompt.inputString("내용? ");
    boards[boardCount].writer = Prompt.inputString("작성자? ");
    boards[boardCount].password = Prompt.inputString("비밀번호? ");

    boards[boardCount].no = boardCount == 0 ? 1 : boards[boardCount - 1].no + 1;
    boards[boardCount].viewCount = 0;

    boards[boardCount].createDate = System.currentTimeMillis();

    boardCount++;
  }
  static void processDelite
}
