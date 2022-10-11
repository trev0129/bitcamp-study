package com.bitcamp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.bitcamp.board.domain.Member;
import com.bitcamp.board.service.BoardService;

@Controller
public class BoardDeleteController {

  BoardService boardService;

  public BoardDeleteController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping("/board/delete")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));

    Member loginMember = (Member) request.getSession().getAttribute("loginMember");
    if (boardService.get(no).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }

    if (!boardService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다!");
    }

    return "redirect:list";

  }
}






