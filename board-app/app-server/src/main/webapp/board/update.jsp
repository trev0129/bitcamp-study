<%@page import="com.bitcamp.board.domain.Board"%>
<%@page import="com.bitcamp.board.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bitcamp</title>
<meta http-equiv='Refresh' content='1; url=list'>
</head>
<body>
<h1>게시글 변경-JSP</h1>
<%
Board board = new Board();
board.no = Integer.parseInt(request.getParameter("no"));
board.title = request.getParameter("title");
board.content = request.getParameter("content");

try {
  if (boardDao.update(board) == 0) {
%>
    <p>해당 번호의 게시글이 없습니다.</p>
<%
  } else {
%>
    <p>해당 게시글을 변경했습니다.</p>
<%
  }
} catch (Exception e) {
%>
  <p>실행 중 서버 오류!</p>
<%
}
%>
</body>
</html>
<%!
BoardDao boardDao;

public void jspInit() {
  boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
}
%>