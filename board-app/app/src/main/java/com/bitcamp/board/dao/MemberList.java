package com.bitcamp.board.dao;

import com.bitcamp.board.domain.Member;
import com.bitcamp.util.ObjectList;

// 게시글 목록을 관리하는 역할
//
public class MemberList extends ObjectList{

  // 게시글 번호에 해당하는 Board 인스턴스를 찾아 리턴한다.
  public Member get(String email) throws Exception {
    for (int i = 0; i < this.size(); i++) {
      Member member = (Member) super.get(i);
      if (member.email.equals(email)) {
        return member;
      }
    }
    return null;
  }

  public boolean remove(String email) throws Exception {
    for (int i = 0; i < this.size(); i++) {
      Member member = (Member) super.get(i);
      if (member.email.equals(email)) {
        return remove(i);
      }
    }
    return false;
  }

}














