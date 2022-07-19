package com.min.myboard;

public class App {

  public static void main(String[] args) {
    System.out.println("[게시글 어플리케이션!]");
    System.out.println("환영합니다!");

    java.util.Scanner keyboardInput = new java.util.Scanner(System.in);

    int no = 0;
    String title = "";
    String content = "";
    String writer = "";
    String password = "";
    int viewCount = 0;
    long createDate = 0;

    int boardCount = 0;

    while (true) {
      System.out.println("메뉴");
      System.out.println("1: 게시글 목록");
      System.out.println("2: 게시글 상세보기");
      System.out.println("3: 게시글 작성");
      System.out.println();
      System.out.println("번호를 입력하세요.[1..3][종료: 0]");

      String str = keyboardInput.nextLine();
      int menuNo = Integer.parseInt(str);

      if (menuNo == 0) {
        break;
      } else if (menuNo == 1) {
        System.out.println("[게시글 목록]");
        System.out.println("번호 제목 조회수 작성자 등록일");
        System.out.printf("%d\t%s\t%d\t%s\t%s\n",
            no, title, viewCount, writer, "2022-07-08");

      } else if (menuNo == 2) {
        System.out.println("[게시글 상세보기]");
        System.out.println("게시글 번호? ");
        String input = keyboardInput.nextLine();
        int boardinput = Integer.parseInt(input);

        System.out.printf("번호: %d\n", no);
        System.out.printf("제목: %s\n", title);
        System.out.printf("내용: %s\n", content);
        System.out.printf("조회수: %d\n", viewCount);
        System.out.printf("작성자: %s\n", writer);

        java.util.Date date = new java.util.Date(createDate);

        System.out.printf("등록일: 등록일: %tY-%1$tm-%1$td %1$tH:%1$tM\\n", date);  

      } else if (menuNo == 3) {
        System.out.println("[게시글 등록]");
        System.out.println("제목? ");
        title = keyboardInput.nextLine();
        System.out.println("내용? ");
        content = keyboardInput.nextLine();
        System.out.println("작성자? ");
        writer = keyboardInput.nextLine();
        System.out.println("비밀번호? ");
        password = keyboardInput.nextLine();

        no = 1;
        viewCount = 0;
        boardCount++;

        createDate = System.currentTimeMillis();
      }
    } //    while 종료
    System.out.println("안녕히 가세요!");
    System.out.println();
  }
}
