package com.bitcamp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.bitcamp.board.domain.Board;
import com.bitcamp.board.service.BoardService;

@Controller
public class BoardDetailController {

  BoardService boardService;

  public BoardDetailController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/board/detail")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int boardNo = Integer.parseInt(request.getParameter("no"));

    Board board = boardService.get(boardNo);

    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    request.setAttribute("board", board);

    return "/board/detail.jsp";

  }
}





