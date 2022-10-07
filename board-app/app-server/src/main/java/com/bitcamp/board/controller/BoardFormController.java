package com.bitcamp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.servlet.Controller;

public class BoardFormController implements Controller {

  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    return "/board/form.jsp";
  }
}






