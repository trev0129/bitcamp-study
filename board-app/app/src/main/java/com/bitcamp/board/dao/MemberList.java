package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Member;
import com.bitcamp.util.ObjectList;

// 회원 목록을 관리하는 역할
//
public class MemberList extends ObjectList {

  // 수퍼클래스 get() 메서드를 호출했을 때 예외가 발생하면
  // 서브클래스의 get(0 메서드에서 처리할 상황이 아니다.
  //서브클래스 get()을 호출한 쪽에 보고하는 것이 낫다.
  // 이럴 경우 try ~ catch ~ 를 쓰지 말고 메서드 선언부에 발생되는 에외를 표시하
  public Member get(String email) throws Throwable {
    for (int i = 0; i < size(); i++) {
      Member member = (Member) get(i);
      if (member.email.equals(email)) {
        return member;
      }
    }
    return null;
  }

  public boolean remove(String email) throws Throwable {
    for (int i = 0; i < size(); i++) {
      Member member = (Member) get(i);
      if (member.email.equals(email)) {
        return remove(i);
      }
    }
    return false;
  }
}














