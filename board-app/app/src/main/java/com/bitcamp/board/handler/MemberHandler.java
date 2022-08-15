/*
 * 게시글 메뉴 처리 클래스
 */
package com.bitcamp.board.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.bitcamp.board.dao.MemberList;
import com.bitcamp.board.domain.Member;
import com.bitcamp.util.Prompt;

public class MemberHandler {

  private String title; // 게시판의 제목

  // 게시글 목록을 관리할 객체 준비
  private MemberList memberList = new MemberList();

  public MemberHandler() {
    this.title = "회원";
  }

  public MemberHandler(String title) {
    this.title = title;
  }

  public void execute() {
    while (true) {
      System.out.printf("%s:\n", this.title);
      System.out.println("  1: 목록");
      System.out.println("  2: 상세보기");
      System.out.println("  3: 등록");
      System.out.println("  4: 삭제");
      System.out.println("  5: 변경");
      System.out.println();
      try {
        int menuNo = Prompt.inputInt("메뉴를 선택하세요[1..5](0: 이전) ");
        displayHeadline();

        // 다른 인스턴스 메서드를 호출할 때 this에 보관된 인스턴스 주소를 사용한다. 
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
      } catch (Exception e) {
        System.out.printf("예외 발생! : %s\n", e.getMessage());
      }
    } // 게시판 while
  }

  private static void displayHeadline() {
    System.out.println("=========================================");
  }

  private static void displayBlankLine() {
    System.out.println(); // 메뉴를 처리한 후 빈 줄 출력
  }

  private void onList() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    System.out.printf("[%s 목록]\n", this.title);
    System.out.println("번호 이름 이메일 등록일");

    // boardList 인스턴스에 들어 있는 데이터 목록을 가져온다.
    Object[] list = this.memberList.toArray();

    for (Object item : list) {
      Member member = (Member) item;
      Date date = new Date(member.createdDate);
      String dateStr = formatter.format(date); 
      System.out.printf("%d\t%s\t%s\t%s\n",
          member.no, member.name, member.email, dateStr);
    }

  }

  private void onDetail() throws Exception {
    System.out.printf("[%s 상세보기]\n", this.title);

    String email = Prompt.inputString("조회할 회원 이메일? ");

    // 해당 번호의 게시글이 몇 번 배열에 들어 있는지 알아내기
    Member member = this.memberList.get(email);

    // 사용자가 입력한 번호에 해당하는 게시글을 못 찾았다면
    if (member == null) {
      System.out.println("해당 이메일의 회원이 없습니다!");
      return;
    }

    System.out.printf("이름: %s\n", member.name);
    System.out.printf("이메일: %s\n", member.email);
    Date date = new Date(member.createdDate);
    System.out.printf("등록일: %tY-%1$tm-%1$td %1$tH:%1$tM\n", date);

  }

  private void onInput() {
    System.out.printf("[%s 등록]\n", this.title);

    Member member = new Member();

    member.name = Prompt.inputString("이름? ");
    member.email = Prompt.inputString("이메일? ");
    member.password = Prompt.inputString("암호? ");
    member.createdDate = System.currentTimeMillis();

    this.memberList.add(member);

    System.out.println("회원을 등록했습니다.");
  }

  private void onDelete() throws Exception {
    System.out.printf("[%s 삭제]\n", this.title);

    String email = Prompt.inputString("삭제할 회원 이메일? ");

    if (memberList.remove(email)) {
      System.out.println("삭제하였습니다.");
    } else {
      System.out.println("해당 이메일의 회원이 없습니다!");
    }
  }

  private void onUpdate() throws Exception {
    System.out.printf("[%s 변경]\n", this.title);

    String eamil = Prompt.inputString("변경할 회원 이메일? ");

    Member member = this.memberList.get(eamil);

    if (member == null) {
      System.out.println("해당 이메일의 회원이 없습니다!");
      return;
    }

    String newName = Prompt.inputString("이름?(" + member.name + ") ");
    String newEmail = Prompt.inputString(String.format("이메일?(%s) ", member.email));

    String input = Prompt.inputString("변경하시겠습니까?(y/n) ");
    if (input.equals("y")) {
      member.name = newName;
      member.email = newEmail;
      System.out.println("변경했습니다.");
    } else {
      System.out.println("변경 취소했습니다.");
    }
  }
}




