package com.bitcamp.study;

import java.io.FileOutputStream;
import com.bitcamp.board.domain.Board;
import com.bitcamp.util.DataOutputStream;

public class Test01_out {

  public static void main(String[] args) throws Exception {
    FileOutputStream out = new FileOutputStream("test.data");
    DataOutputStream out2 = new DataOutputStream(out);

    Board board = new Board();
    board.no = 100;
    board.title = "제목입니다.";
    board.content = "내용입니다";
    board.writer = "hong@test.com";
    board.password = "1111";
    board.viewCount = 11;
    board.createdDate = System.currentTimeMillis();

    byte b = 120;
    short s = 32000;
    int i = 1123456789;
    long l = 1234567812345678l;
    float f = 12.375f;
    double d = 12.375;
    boolean bool = true;
    String str1 = "ABCabc012가각간";
    String str2 = "홍길동";

    out2.writeByte(b);
    out2.writeShort(s);
    out2.writeInt(i);
    out2.writeLong(l);
    out2.writeFloat(f);
    out2.writeDouble(d);
    out2.writeBoolean(bool);
    out2.writeUTF(str1);
    out2.writeUTF(str2);

    out2.writeInt(board.no);
    out2.writeUTF(board.title);
    out2.writeUTF(board.content);
    out2.writeUTF(board.writer);
    out2.writeUTF(board.password);
    out2.writeInt(board.viewCount);
    out2.writeLong(board.createdDate);


    out2.close();
    out.close();


    System.out.println("실행 완료!");



  }

}
