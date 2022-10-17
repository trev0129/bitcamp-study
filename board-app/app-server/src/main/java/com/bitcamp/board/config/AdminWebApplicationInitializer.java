package com.bitcamp.board.config;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.bitcamp.board.filter.AdminCheckFilter;
import com.bitcamp.board.filter.LoginCheckFilter;

// 서블릿 콘테이너 
// ===> SpringServletContainerInitializer.onStartup() 호출
//      ===> WebApplicationInitializer.onStartup() 호출
//          ===> AbstractContextLoaderInitializer 구현체의 onStartup() 호출 
//              ===> registerContextLoaderListner() 호출
//                  ===> createRootApplicationContext() 호출
//          ===> IoC 컨테이너, 프론트 컨트롤러, 필터 준비 
// 수퍼클래스 코드 분석할 것 
public class AdminWebApplicationInitializer 
extends AbstractAnnotationConfigDispatcherServletInitializer {

  // 수퍼클래스에서 ContextLoaderListner에서 사용할 Root IoC 컨테이너   
  // 컨테이너가 사용할 클래스 정보를 알려준다.
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }


  // 수퍼클래스에서 DispatcherServlet을 준비할 때 사용할 서블릿 이름을 리턴한다 . 
  @Override
  protected String getServletName() {
    return "admin";
  }

  // 수퍼클래스에 DispatcherServlet이 사용할 IoC 컨테이너를 만듬
  // 컨테이너를 만들 때 사용할 java config 클래스를 알려주면 된다. 
  @Override
  protected Class<?>[] getServletConfigClasses() {
    // java config 클래스 정보를 배열에 담아서 리턴한다. 
    return new Class<?>[] {AdminWebConfig.class};
  }

  // 수퍼클래스에 DispatcherServlet을 URL을 연결할 때 사용할 경로를 리턴한다.
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/admin/*"};
  }

  // 수퍼클래스에 필터를 등록할 때 사용할 정보를 리턴한다. 
  @Override
  protected Filter[] getServletFilters() {
    return new Filter[] {
        new CharacterEncodingFilter("UTF-8"),
        new LoginCheckFilter(),
        new AdminCheckFilter()
    };
  }

}
