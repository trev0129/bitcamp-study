package com.bitcamp.board.domain;

import java.io.Serializable;

public class Board implements Serializable {
  private static final long serialVersionUID = 1L;
  // 인스턴스를 생성할 때 준비되는 메모리를 선언
  public int no;
  public String title;
  public String content;
  public String writer;
  public String password;
  public int viewCount;
  public long createdDate;
  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
        + ", password=" + password + ", viewCount=" + viewCount + ", createdDate=" + new java.sql.Date(createdDate)
        + "]";
  }

  public static Board create(String csv) {
    String[] values = csv.split(",");

    Board board = new Board();
    board.no = Integer.parseInt(values[0]);
    board.title = values[1];
    board.content = values[2];
    board.writer = values[3];
    board.password = values[4];
    board.viewCount = Integer.parseInt(values[5]);
    board.createdDate = Long.parseLong(values[6]);
    return board;
  }

  public String toCsv() {
    return String.format("%d,%s,%s,%s,%s,%d,%d\n", 
        this.no,
        this.title,
        this.content,
        this.writer,
        this.password,
        this.viewCount,
        this.createdDate);
  }

}
