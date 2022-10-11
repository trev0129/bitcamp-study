package com.bitcamp.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.bitcamp.board.service.MemberService;

@Controller
public class MemberListController {

  MemberService memberService;

  public MemberListController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/member/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("members", memberService.list());
    return "/member/list.jsp";
  }
}






