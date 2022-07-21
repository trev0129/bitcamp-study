/*
 * 게시글 메뉴 처리 클래스
 */
package com.min.myboard;

import java.util.Date;

public class BoardHandler {

  // 모든 게시판이 공유하는 건 클래스 변수에 저장. 한번만 생성하기 때
  static final int DEFAULT_SIZE = 3;

  // 각 게시판이 별도 관리하는 데이터는 인스턴스 변수에 저장
  // 인스턴스 변수는 게시판 별로 생성할 수 있기 때

  int boardCount = 0; // 저장된 게시글의 개수


  // Board 인스턴스의 주소를 저장할 레퍼런스 배열을 만든다.
  Board[] boards = new Board[DEFAULT_SIZE];

  String title = "";

  // 클래스 샐설자가 정의되어 있지 않으면
  // 다음과 같이 파라미터가 없는 기본생성자를 컴파일러가 자동으로 추가함
  // -파라미터가 없다. 
  // -몸체도 비어있다.
  // -메서드 접근범위는 무조건 공개(public)이다.
  //
  // public BoardHandler() {
  // }

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
        case 1: this.processList(); break; 
        case 2: this.processDetail(); break;
        case 3: this.processInput(); break;
        case 4: this.processDelete(); break;
        case 5: this.processUpdate(); break;
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

  void processList() {
    // 날짜 정보에서 값을 추출하여 특정 포맷의 문자열로 만들어줄 도구를 준비
    java.text.SimpleDateFormat formatter = 
        new java.text.SimpleDateFormat("yyyy-MM-dd");

    System.out.printf("[%s 목록]\n", this.title);
    System.out.println("번호 제목 조회수 작성자 등록일");

    for (int i = 0; i < this.boardCount; i++) {
      Board board = this.boards[i];

      Date date = new Date(board.createdDate);

      // 날짜 정보 ==> "yyyy-MM-dd" 형식의 문자열
      String dateStr = formatter.format(date); 

      System.out.printf("%d\t%s\t%d\t%s\t%s\n",
          board.no, board.title, board.viewCount, board.writer, dateStr);
    }

  }

  void processDetail() {
    System.out.printf("[%s 상세보기]\n", this.title);

    int boardNo = Prompt.inputInt("조회할 게시글 번호? ");

    // 해당 번호의 게시글이 몇 번 배열에 들어 있는지 알아내기
    Board board = null;
    for (int i = 0; i < this.boardCount; i++) {
      if (this.boards[i].no == boardNo) {
        board = this.boards[i];
        break;
      }
    }

    // 사용자가 입력한 번호에 해당하는 게시글을 못 찾았다면
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

  void processInput() {
    System.out.printf("[%s 등록]\n", this.title);

    // 배열의 크기를 초과하면 배열 크기를 50% 증가시킨다.
    if (this.boardCount == this.boards.length) {
      // 새로 만들 배열의 크기를 계산한다.
      int newSize = this.boards.length + (this.boards.length >> 1);
      Board[] newArray = new Board[newSize];
      // 새 배열을 분지하고 기존값을 새 배열에 넣는다.
      for (int i = 0; i < this.boards.length; i++) {
        newArray[i] = this.boards[i];
      }
      // 기존배열 주소를 버리고 새 배열 소를 사용한다.
      this.boards = newArray;
    }

    Board board = new Board();

    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer = Prompt.inputString("작성자? ");
    board.password = Prompt.inputString("암호? ");

    board.no = this.boardCount == 0 ? 1 : this.boards[this.boardCount - 1].no + 1;
    board.viewCount = 0;
    board.createdDate = System.currentTimeMillis();

    // 새로 만든 인스턴스 주소를 레퍼런스 배열에 저장한다.
    this.boards[this.boardCount] = board;

    this.boardCount++;

    System.out.printf("%s을 등록했습니다.\n", this.title);
  }

  void processDelete() {
    System.out.printf("[%s 삭제]\n", this.title);

    int boardNo = Prompt.inputInt("삭제할 게시글 번호? ");

    // 해당 번호의 게시글이 몇 번 배열에 들어 있는지 알아내기
    int boardIndex = -1;
    for (int i = 0; i < this.boardCount; i++) {
      if (this.boards[i].no == boardNo) {
        boardIndex = i;
        break;
      }
    }

    // 사용자가 입력한 번호에 해당하는 게시글을 못 찾았다면
    if (boardIndex == -1) {
      System.out.printf("해당 번호의 %s이 없습니다!\n", this.title);
      return;
    }

    // 삭제할 게시글의 다음 항목을 앞으로 당긴다.
    for (int i = boardIndex + 1; i < boardCount; i++) {
      this.boards[i - 1] = this.boards[i];
    }

    // 게시글 개수를 1개 줄이고 맨 마지막 레퍼런스는 null 로 비운다.
    this.boards[--this.boardCount] = null;

    System.out.println("삭제하였습니다.");

  }

  void processUpdate() {
    System.out.printf("[%s 변경]\n", this.title);

    int boardNo = Prompt.inputInt("변경할 게시글 번호? ");

    // 해당 번호의 게시글이 몇 번 배열에 들어 있는지 알아내기
    Board board = null;
    for (int i = 0; i < this.boardCount; i++) {
      if (this.boards[i].no == boardNo) {
        board = this.boards[i];
        break;
      }
    }

    // 사용자가 입력한 번호에 해당하는 게시글을 못 찾았다면
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




