package com.bitcamp.board.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitcamp.board.domain.Member;

@WebFilter("*")
public class LoginCheckFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    // 요청 URL을 통해 로그인 여부를 검사할 지 결정한다
    // 요청 URL은 HTTP 프로토콜과 관련된 값이다.
    // ServletRequest 타입은 HTTP프로토콜과 관련된 기능을 다루는 메서드가 없다.
    // 필터의 파라미너로 넘어오는 객체는 원래 HttpServletRequest 객체이다.
    // 세션처럼 HTTP 프로토콜과 관련된 기능을 쓰고 싶다면,
    // 원래 타입으로 형변환 한 다음에사용하라!
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    // 응답 기능에 대해서도 HTTP관련 메서드를 사용하고 싶다면

    HttpServletResponse httpResponse = (HttpServletResponse) response;

    // 요청 URLdptj 서블릿 경로만 추출한다.
    // 예) 요청 URL :  http://loclahost:8888/app/board/add?title=aaa&cont=bbb
    //      서블릿 경로 : /board/add/ <== 웹 애플케이션 경로는 다.
    String servletPath = httpRequest.getServletPath();
    //    System.out.println(servletPath);

    if (servletPath.endsWith("add") ||
        servletPath.endsWith("update") ||
        servletPath.endsWith("delete")) {
      Member loginMember = (Member) httpRequest.getSession().getAttribute("loginMember");
      if (loginMember == null) { // 로그인하지 않았다면
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/auth/form.jsp");
        return;
      }
    }




    // 다음 필터를 실행한다.
    // 다음 실행할 필터가 없다면 원래 목적지인 서블릿이 실행될 것이다.
    chain.doFilter(request, response);

  }
}
