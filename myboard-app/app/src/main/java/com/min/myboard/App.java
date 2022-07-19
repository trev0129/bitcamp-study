package com.min.myboard;

public class App {

  public static void main(String[] args) {
    System.out.println("[게시글 어플리케이션!]");
    System.out.println("환영합니다!");
    System.out.println();

    java.util.Scanner keyboardInput = new java.util.Scanner(System.in);

    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");

    int no= 0;
    String title = "";
    String content = "";
    String writer = "";
    String password = "";
    int viewCount = 0;
    long createDate = 0;

    int count = 1;

    while (true) {

      System.out.println("메뉴를 선택하세요.");
      System.out.println("------------------");
      System.out.println("1 게시글 목록");
      System.out.println("2 게시글 상세보기");
      System.out.println("3 게시글 작성");
      System.out.println("4 게시글 삭제");
      System.out.println("5 게시글 수정");
      System.out.println();
      System.out.println("메뉴를 선택하세요.[1..5][종료: 0]");
      System.out.println("------------------");
      String str = keyboardInput.nextLine();
      int menuNo = Integer.parseInt(str);

      if (menuNo == 0) {
        break;
      } else if (menuNo == 1) {
        System.out.println("[게시글 목록]");
        System.out.println("번호 제목 작성자 조회수 작성일");
        System.out.printf("%d %s %s %d %s", 1, "aaaa", "aaa", 1, "2022-07-18");
      } else if (menuNo == 2) {
        java.util.Date date = new java.util.Date(createDate);
        String dateStr = formatter.format(date);
        System.out.println("[게시글 상세보기]");
        System.out.println("게시글 번호? ");
        System.out.printf("번호: %d\n", no);
        System.out.printf("제목: %s\n", title);
        System.out.printf("내용: %s\n", content);
        System.out.printf("조회수: %d\n", viewCount);
        System.out.printf("작성자: %s\n", writer);

        System.out.printf("등록일: %s\n", dateStr);

      } else if(menuNo == 3) {
        System.out.println("[게시글 작성]");
        System.out.println("제목? ");
        title = keyboardInput.nextLine();
        System.out.println("내용? ");
        content = keyboardInput.nextLine();
        System.out.println("작성자? ");
        writer = keyboardInput.nextLine();
        System.out.println("비밀번호? ");
        password = keyboardInput.nextLine();
        no = count;
        viewCount = 0;
        createDate = System.currentTimeMillis();
        count++;
      } else {
        System.out.println("잘못 입력하셨습니다.");
      }




    } //while 종료
    System.out.println("안녕히 가세요!");
  }
}
