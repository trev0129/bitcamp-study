/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardHandler {

  String title;

  // 게시글 목록을 관리할 객체 준
  BoardList boardList = new BoardList();

  public BoardHandler() {
    this.title = "게시판";
  }

  //제목을 입력받느생성
  BoardHandler(String title) {
    this.title = title;
  }

  void execute() {
    // App 클래스에서 메서드를 호출 할 떄 BoardHandler의 인스턴스 주소를 줄 것이다.
    // 그 주소는 this라는 내장 변수에 저장된다.
    while (true) {
      System.out.printf("%s:\n", this.title);
      System.out.println("  1: 목록");
      System.out.println("  2: 상세보기");
      System.out.println("  3: 등록");
      System.out.println("  4: 삭제");
      System.out.println("  5: 변경");
      System.out.println();

      int menuNo = Prompt.inputInt("메뉴를 선택하세요[1..5](0: 이전) ");
      displayHeadline();

      // 다른 인스턴스 메서드를 호출할 때 this에 보관된 인스턴스 주소를 사
      switch (menuNo) {
        case 0: return;
        case 1: this.onList(); break; 
        case 2: this.onDetail(); break;
        case 3: this.onInput(); break;
        case 4: this.onDelete(); break;
        case 5: this.onUpdate(); break;
        default: System.out.println("메뉴 번호가 옳지 않습니다!");
      }

      displayBlankLine();
    } // 게시판 while
  }

  static void displayHeadline() {
    System.out.println("=========================================");
  }

  static void displayBlankLine() {
    System.out.println(); // 메뉴를 처리한 후 빈 줄 출력
  }

  void onList() {
    // 날짜 정보에서 값을 추출하여 특정 포맷의 문자열로 만들어줄 도구를 준비
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    System.out.printf("[%s 목록]\n", this.title);
    System.out.println("번호 제목 조회수 작성자 등록일");

    // boardList 인스턴스에 들어있는 데이터 목록을 가져온다.
    Board[] list = this.boardList.toArray();

    for (Board board : list) {

      Date date = new Date(board.createdDate);

      // 날짜 정보 ==> "yyyy-MM-dd" 형식의 문자열
      String dateStr = formatter.format(date); 

      System.out.printf("%d\t%s\t%d\t%s\t%s\n",
          board.no, board.title, board.viewCount, board.writer, dateStr);
    }

  }

  void onDetail() {
    System.out.printf("[%s 상세보기]\n", this.title);

    int boardNo = Prompt.inputInt("조회할 게시글 번호? ");

    Board board = this.boardList.get(boardNo);

    if (board == null) {
      System.out.printf("해당 번호의 %s이 없습니다!\n", this.title);
      return;
    }

    System.out.printf("번호: %d\n", board.no);
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("조회수: %d\n", board.viewCount);
    System.out.printf("작성자: %s\n", board.writer);
    Date date = new Date(board.createdDate);
    System.out.printf("등록일: %tY-%1$tm-%1$td %1$tH:%1$tM\n", date);

  }

  void onInput() {
    System.out.printf("[%s 등록]\n", this.title);

    Board board = new Board();

    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer = Prompt.inputString("작성자? ");
    board.password = Prompt.inputString("암호? ");
    board.viewCount = 0;
    board.createdDate = System.currentTimeMillis();

    this.boardList.add(board);

    System.out.printf("%s을 등록했습니다.\n", this.title);
  }

  void onDelete() {
    System.out.printf("[%s 삭제]\n", this.title);

    int boardNo = Prompt.inputInt("삭제할 게시글 번호? ");

    if (this.boardList.remove(boardNo)) {
      System.out.println("삭제하였습니다.");
    } else {
      System.out.printf("해당 번호의 %s이 없습니다!\n", this.title);
    }

  }

  void onUpdate() {
    System.out.printf("[%s 변경]\n", this.title);

    int boardNo = Prompt.inputInt("변경할 게시글 번호? ");

    Board board = this.boardList.get(boardNo);

    if (board == null) {
      System.out.printf("해당 번호의 %s이 없습니다!\n", this.title);
      return;
    }

    String newTitle = Prompt.inputString("제목?(" + board.title + ") ");
    String newContent = Prompt.inputString(String.format("내용?(%s) ", board.content));

    String input = Prompt.inputString("변경하시겠습니까?(y/n) ");
    if (input.equals("y")) {
      board.title = newTitle;
      board.content = newContent;
      System.out.println("변경했습니다.");
    } else {
      System.out.println("변경 취소했습니다.");
    }
  }
}




