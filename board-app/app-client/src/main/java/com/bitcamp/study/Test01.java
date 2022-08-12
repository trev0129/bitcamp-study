package com.bitcamp.study;

import com.bitcamp.board.domain.Board;
import com.google.gson.Gson;

public class Test01 {
  public static void main(String[] args) {
    Board b= new Board();
    b.no = 100;
    b.title = "제목입니다";
    b.content = "내용입니다.";
    b.writer = "홍깅동";
    b.password = "1111";
    b.viewCount = 11;
    b.createdDate = System.currentTimeMillis();

    // object --> Json문자열
    Gson gson = new Gson();

    String json = gson.toJson(b);

    System.out.println(json);

    Board b2 = gson.fromJson(json, Board.class); // static 변

    System.out.println(b2);

    System.out.println(b == b2);

  }
}
