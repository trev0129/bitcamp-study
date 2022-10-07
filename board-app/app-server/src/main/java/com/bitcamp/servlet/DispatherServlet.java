package com.bitcamp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//역할 : 
// - 페이지 컨트롤러 앞 쪽에서 클라이언트 요청을 받는 일을 한다.
// - 클라이언트가 요청한 경로에 따라 적절한 페이지 컨트롤러를 실행한다.
// - 페이지 컨트롤러의 공통 기능을 수행한다. 
//@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // 최대 10MB까지 업로드 허용 
//@WebServlet(value = "/service/*")
public class DispatherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      // "/service" 다음에 오는 경로, 즉 *에 해당하는 경로를 리턴한다. 
      String pathInfo = req.getPathInfo();

      Controller controller = (Controller) req.getServletContext().getAttribute(pathInfo);
      if (controller == null) {
        throw new Exception("페이지 컨트롤러가 없습니다.");
      }

      resp.setContentType("text/html;charset=UTF-8");
      String viewName = controller.execute(req, resp);

      if (viewName.startsWith("redirect:")) { 
        resp.sendRedirect(viewName.substring(9)); 

      } else {
        req.getRequestDispatcher(viewName).include(req, resp);
      }

    } catch (Exception e) { 
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, resp);
    }


  }
}
