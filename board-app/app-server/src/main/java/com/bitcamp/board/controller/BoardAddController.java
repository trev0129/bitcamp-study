package com.bitcamp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.dao.BoardDao;
import com.bitcamp.board.domain.Board;

@WebServlet("/board/add")
public class BoardAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;

  @Override
  public void init() {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      Board board = new Board();
      board.title = request.getParameter("title");
      board.content = request.getParameter("content");
      board.memberNo = Integer.parseInt(request.getParameter("writerNo"));

      if (boardDao.insert(board) == 0) {
        throw new Exception("게시글 등록 실패");
      } 
      //      // Refresh:
      //         - 응답 헤더 또는 HTML문서에 삽입할 수 있다.
      //      // - 응답 프로토콜
      //      //      HTTP/1.1 200
      //      //      Content-Type: text/html;charset=UTF-8
      //      //      Content-Length: 233
      //      //      Date: Mon, 26 Sep 2022 05:24:28 GMT
      //      //      Keep-Alive: timeout=20
      //      //      Connection: keep-alive
      //      //      
      //      //      <!DOCTYPE html>
      //      //      <html>
      //      //      <head>
      //      //      <meta charset="UTF-8">
      //      //      <title>bitcamp</title>
      //      //      <meta http-equiv='Refresh' content='1; url=list'>    <== HTML에 refresh 삽입
      //      //      </head>
      //      //      <body>
      //      //      <h1>게시글 입력</h1>
      //      //        <p>게시글을 등록했습니다.</p>
      //      //      </body>
      //      //      </html>
      //      response.setHeader("Refresh", "1;url=list");                  <== 응답헤더에 삽입
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/board/add.jsp").include(request, response);

      // Redirect :
      // - 클라이언트에게 콘텐트를 보내지 않는다.

      //      // (콘텐트 없음)
      //      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("exception", e);
      request.getRequestDispatcher("/error.jsp").forward(request, response); 
    }
  }
}
