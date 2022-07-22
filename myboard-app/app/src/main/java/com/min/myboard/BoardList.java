package com.min.myboard;

public class BoardList {
  int boardCount; // 저장된 게시글의 개수
  Board[] boards;
  int no = 0;
  static final int SIZE = 3;

  // Board 인스턴스의 주소를 저장할 레퍼런스 배열을 만든다.

  BoardList() {
    this.boards = new Board[SIZE];
  }
  BoardList(int i) {
    this.boards = new Board[i];
  }

  Board[] toArray() {
    Board[] arr = new Board[this.boardCount];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = this.boards[i];
    }
    return arr;
  }
  Board get(int boardNo) {
    for (int i = 0; i < this.boardCount; i++) {
      if (this.boards[i].no == boardNo) {
        return this.boards[i];
      }
    }
    return null;
  }
  void add(Board board) {
    if (this.boardCount == this.boards.length) {
      int newSize = this.boards.length + (this.boards.length >> 1);
      Board[] newArray = new Board[newSize];
      for (int i = 0; i < this.boards.length; i++) {
        newArray[i] = this.boards[i];
      }
      this.boards = newArray;
    }
    board.no = nextNo();
    this.boards[this.boardCount++] = board;
  }
  int nextNo() {
    return ++no;
  }
}
