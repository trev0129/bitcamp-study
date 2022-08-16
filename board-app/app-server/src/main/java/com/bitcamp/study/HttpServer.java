package com.bitcamp.study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

  public static void main(String[] args) throws Exception {
    try (ServerSocket ss = new ServerSocket(80);) {
      System.out.println("서버 시작!!");

      while (true) {
        try (
            Socket socket = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            ) {
          System.out.println("클라이언트가 연결됨!");

          System.out.println("-----------------------------------");
          String line;

          // 클라이언트가 보낸 데이터를 읽는다.
          while ((line = in.readLine()) != null) {
            if (line.length() == 0) { // 클라이언트가 빈줄을 보내면, 읽기를 끝낸다.
              break;
            }
            System.out.println(line);
          }

          // 클라이언트에게 응답한다.
          out.println("Http/1.1 200 OK"); //    상태  
          out.println("Content-Type: text/html;charset=UTF-8"); // 
          out.println();
          out.println("<html>");
          out.println("<head>");
          out.println("<title>Hello!</title>");
          out.println("</head>");
          out.println("<boby>");
          out.println("<h1>안녕하세요</h1>");
          out.println("<h2>반갑습니다</h2>");
          out.println("<h3>이렇게 하는 거구나</h3>");
          out.println("<h3>난 언제 그럴싸한 사이트 만드나</h3>");
          out.println("<h2>이제 그만 가볼게요</h2>");
          out.println("<h3>빠이</h3>");
          out.println("</boby>");
          out.println("</html>");
        }
      }
    }
  }
}





