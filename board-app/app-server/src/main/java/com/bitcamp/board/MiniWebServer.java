package com.bitcamp.board;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.dao.MariaDBBoardDao;
import com.bitcamp.board.dao.MariaDBMemberDao;
import com.bitcamp.board.dao.MemberDao;
import com.bitcamp.board.handler.BoardHandler;
import com.bitcamp.board.handler.ErrorHandler;
import com.bitcamp.board.handler.WelcomHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class MiniWebServer {

  public static void main(String[] args) throws Exception {
    // DAO가 사용할 커넥션 객체 준비
    Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");

    // DAO 객체를 준비한다.
    BoardDao boardDao = new MariaDBBoardDao(con);
    MemberDao memberDao = new MariaDBMemberDao(con);


    WelcomHandler welcomHandler = new WelcomHandler();
    ErrorHandler errorHandler = new ErrorHandler();
    BoardHandler boardHandler = new BoardHandler(boardDao);

    // 클라이언트 요청을 처리하는 객체 
    class MyHttpHandler implements HttpHandler {
      @Override
      public void handle(HttpExchange exchange) throws IOException {
        // 클라이언트 요청이 들어 왔을 때마다 호출된다.
        System.out.println("클라이언트가요청함!  ");

        URI requestUri = exchange.getRequestURI();


        String path = requestUri.getPath();
        String query = requestUri.getQuery();

        byte[] bytes = null;

        try (StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter)) {

          Map<String,String> paramMap = new HashMap<>();
          if (query != null && query.length() > 0) {
            String[] entries = query.split("&");
            for (String entry : entries) {
              String[] kv = entry.split("=");
              paramMap.put(kv[0], kv[1]);
            }
          }

          System.out.println(paramMap);

          if (path.equals("/")) {
            welcomHandler.service(paramMap, printWriter);
          } else if (path.equals("/board/list")) {
            boardHandler.list(paramMap, printWriter);
          } else if (path.equals("/board/detail")) {
            boardHandler.detail(paramMap, printWriter);
          } else if (path.equals("/board/update")) {
            boardHandler.update(paramMap, printWriter);
          } else if (path.equals("/board/delete")) {
            boardHandler.delete(paramMap, printWriter);
          } else {
            errorHandler.error(paramMap, printWriter);
          }
          bytes =stringWriter.toString().getBytes("UTF-8");
        } catch (Exception e) {
          bytes = "요청 처리 중 오류 발생!".getBytes("UTF-8");
          e.printStackTrace();
        }

        // 보내는 콘텐트의 MIME타입이 무엇인지 응답헤더에 추가한다.
        Headers responseHeader = exchange.getResponseHeaders();
        responseHeader.add("Content-Type", "text/html; charset=UTF-8");

        exchange.sendResponseHeaders(200, bytes.length);


        OutputStream out = exchange.getResponseBody();
        out.write(bytes);
        out.close();
      }
    }
    HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
    server.createContext("/", new MyHttpHandler());
    server.setExecutor(null); //    httpserver에 기본으로 설정되어 있는 Executor 사용
    //    Executor? 멀티 스레딩을 수행하는 객체
    server.start(); // httpserver를 시작시킨 후 즉시 리턴한다.
    System.out.println("서버 시작!");
    // main() 메서드 호출이 끝났다 하더라도 내부에서 생성한 스레드(예 : HttpServer)가 종료되지 않으면
    // JVM도 종료되지 않는다.
  }
}
