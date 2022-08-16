package com.bitcamp.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HttpClient {
  public static void main(String[] args) throws Exception {
    try (Socket socket = new Socket("stores.auction.co.kr", 80);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      // http 프로토콜에 따라 ㅁ메인 웹 페이지를 요청
      out.println("GET /thisbike HTTP/1.1"); // 어떤 자원을요청하는지 
      out.println("Host: stores.auction.co.kr"); // 
      out.println(); // 빈줄(요청정보 끝)


      // 웹 서버의 응답을 출력한다.
      String line;
      while ((line = in.readLine()) != null) {
        System.out.println(line); 
      }

    }
  }
}
