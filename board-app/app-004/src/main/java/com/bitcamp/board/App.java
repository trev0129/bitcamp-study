/*
 * 게시판 관리 애플리케이션
 * 비트캠프-20220704
 */
package com.bitcamp.board;

public class App {
  public static void main(String[] args) {

    System.out.println("[게시판 애플리케이션]");
    System.out.println();
    System.out.println("환영합니다!");
    System.out.println();

    java.util.Scanner keyboardInput = new java.util.Scanner(System.in);

    final int SIZE = 3;
    int[] no = new int[SIZE];
    String[] title = new String[SIZE];
    String[] content = new String[SIZE];
    String[] writer = new String[SIZE];
    String[] password = new String[SIZE];
    int[] viewCount = new int[SIZE];
    long[] createdDate = new long[SIZE];

    int boardCount = 0;

    while (true) {
      System.out.println("메뉴:");
      System.out.println("  1: 게시글 목록");
      System.out.println("  2: 게시글 상세보기");
      System.out.println("  3: 게시글 등록");
      System.out.println();
      System.out.print("메뉴를 선택하세요[1..3](0: 종료) ");
      
      int menuNo = keyboardInput.nextInt();
      keyboardInput.nextLine(); // 입력한 숫자 뒤에 남아 있는 줄바꿈 코드 제거

      if (menuNo == 0) {
        break;
      } else if (menuNo == 1) {
        System.out.println("[게시글 목록]");
        System.out.println("번호 제목 조회수 작성자 등록일");

      
        System.out.printf("%d\t%s\t%d\t%s\t%s\n",
            4, "제목입니다4", 45, "유관순", "2022-07-08");
      } else if (menuNo == 2) {
        System.out.println("[게시글 상세보기]");

        System.out.println("게시글 번호를 입력하세요.");

        String input = keyboardInput.nextLine();
        int boardNo = Integer.parseInt(input);

        int boardIndex = -1;
        for (int i = 0; i < boardCount; i++) {
          if (no[i] == boardNo) {
            boardIndex = i;
            break;
          }
        }

        if (boardIndex == -1) {
          System.out.println("없는 번호입니다.");
          continue;
        }

        System.out.printf("번호: %d\n", no[boardIndex]);
        System.out.printf("제목: %s\n", title[boardIndex]);
        System.out.printf("내용: %s\n", content[boardIndex]);
        System.out.printf("조회수: %d\n", viewCount[boardIndex]);
        System.out.printf("작성자: %s\n", writer[boardIndex]);

        // Date 도구함의 도구를 쓸 수 있도록 데이터를 준비시킨다.
        // new Date(밀리초)
        //   => 지정한 밀리초를 가지고 날짜 관련 도구를 사용할 수 있도록 설정한다.
        // Date date
        //   => createdDate 밀리초를 가지고 설정한 날짜 정보
        java.util.Date date = new java.util.Date(createdDate[boardIndex]);
        
        // Date 도구함을 통해 설정한 날짜 정보를 가지고 printf()를 실행한다.
        // %Y : date에 설정된 날짜 정보에서 년도만 추출한다.
        System.out.printf("등록일: %tY-%1$tm-%1$td %1$tH:%1$tM\n", date);

      } else if (menuNo == 3) {
        System.out.println("[게시글 등록]");

        if (boardCount == SIZE) {
          System.out.println("더이상 글 못씀");
        }
        
        System.out.print("제목? ");
        title[boardCount] = keyboardInput.nextLine();

        System.out.print("내용? ");
        content[boardCount] = keyboardInput.nextLine();

        System.out.print("작성자? "); 
        writer[boardCount] = keyboardInput.nextLine();

        System.out.print("암호? ");
        password[boardCount] = keyboardInput.nextLine();

        no[boardCount] = boardCount == 0 ? 1 : no[boardCount - 1] + 1;
        /*
         * if (no[boardCount] == 0) {
         *  no[boardCount] = 1;
         * } else {
         *  no[boardCount - 1] + 1;
         * }
         */
        viewCount[boardCount] = 0;
        createdDate[boardCount] = System.currentTimeMillis();

        boardCount++;

      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }

      System.out.println(); // 메뉴를 처리한 후 빈 줄 출력
    } // while

    System.out.println("안녕히 가세요!");
    keyboardInput.close();
  }
}
