package com.bitcamp.board.config;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
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
//extends AbstractAnnotationConfigDispatcherServletInitializer 
{

  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  protected String getServletName() {
    return "admin";
  }

  protected Class<?>[] getServletConfigClasses() {
    // java config 클래스 정보를 배열에 담아서 리턴한다. 
    return new Class<?>[] {AdminWebConfig.class};
  }

  protected String[] getServletMappings() {
    return new String[] {"/admin/*"};
  }

  protected Filter[] getServletFilters() {
    return new Filter[] {
        new CharacterEncodingFilter("UTF-8"),
        new LoginCheckFilter(),
        new AdminCheckFilter()
    };
  }

}
