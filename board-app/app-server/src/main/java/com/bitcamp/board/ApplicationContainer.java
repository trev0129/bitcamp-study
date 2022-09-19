package com.bitcamp.board;

import static org.reflections.scanners.Scanners.TypesAnnotated;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import com.bitcamp.board.handler.ErrorHandler;
import com.bitcamp.servlet.Servlet;
import com.bitcamp.servlet.WebServlet;
import com.bitcamp.servlet.annotation.Repository;


// 1) 기본 웹 서버 만들기
// 2) 한글 콘텐트를 출력하기
// 3) HTML 콘텐트를 출력하기
// 4) 메인 화면을 출력하는 요청처리 객체를 분리하기
// 5) 요청 자원의 경로를 구분하여 처리하기
// 6) 게시글 요청 처리하기
// 7) URL 디코딩 처리
// 8) 회원 요청 처리하기
//
public class ApplicationContainer {
  //  객체(DAO, 서블릿)를 보관할 맵을준비
  Map<String,Object> objMap = new HashMap<>();
  Reflections reflections;
  ErrorHandler errorHandler = new ErrorHandler();

  public ApplicationContainer(String packegeName) throws Exception {

    reflections = new Reflections(packegeName);

    Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost:3306/studydb","study","1111");

    Set<Class<?>> classes = reflections.get(TypesAnnotated.with(Repository.class).asClass());
    for (Class<?> clazz : classes) {
      String objName = clazz.getAnnotation(Repository.class).value();
      Constructor<?> constructor = clazz.getConstructor(Connection.class);
      objMap.put(objName, constructor.newInstance(con));
    }

    Set<Class<?>> servlets = reflections.get(TypesAnnotated.with(WebServlet.class).asClass());
    for (Class<?> servlet : servlets) {
      String servletPath = servlet.getAnnotation(WebServlet.class).value();

      Constructor<?> constructor = servlet.getConstructors()[0];
      Parameter[] params = constructor.getParameters();

      if (params.length == 0) {
        objMap.put(servletPath, constructor.newInstance());

      } else { // 파라미터가 있다면 해당 파라미터 타입과 일치하는 객체를 찾아 생성자 호출한다.
        Object argument = findObject(objMap, params[0].getType());
        if (argument != null) {
          objMap.put(servletPath, constructor.newInstance(argument));
        }
      }
    }

  }

  public void execute(String path, String query, PrintWriter out) throws Exception {

    // 쿼리 스트링을 분석하여 파라미터 값을 맵에 저장한다.
    Map<String,String> paramMap = new HashMap<>();
    if (query != null && query.length() > 0) { // 예) no=1&title=aaaa&content=bbb
      String[] entries = query.split("&");
      for (String entry : entries) { // 예) no=1
        String[] kv = entry.split("=");
        // 웹브라우저가 보낸 파라미터 값은 저장하기 전에 URL 디코딩 한다.
        paramMap.put(kv[0], URLDecoder.decode(kv[1], "UTF-8"));
      }
    }
    //    System.out.println(paramMap);

    // 경로에 해당하는 
    Servlet servlet = (Servlet) objMap.get(path);
    if (servlet != null) {
      servlet.service(paramMap, out);
    } else {
      errorHandler.service(paramMap, out);
    }
  }
  private static Object findObject(Map<String, Object> objMap, Class<?> type) {
    // 맵에 들어있는 객체를 모두 꺼낸다
    Collection<Object> values = objMap.values();
    for (Object value : values) {
      if (type.isInstance(value)) {
        return value;
      }
    }
    return null; 
  }

}
