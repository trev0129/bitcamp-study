package com.bitcamp.board.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// 스프링 IoC 컨테이너의 설정
//1) DB 커넥션 객체 관리자 준비 : DataSource
//2) 트랜잭션 관리자 준비 : TransactionManager
//3) 어떤 패키지에 있는 객체를 자동으로 생성할 것인지 지정한다.
@ComponentScan(value="com.bitcamp.board")
// com.bitcamp.board 패키지 및 그 하위 패키지에 소속된 클래스 중에서
// @Component, @Contoller, @Service, @Repository 등의 애노테이션이 붙은 클래스를 찾아
// 객체를 생성한다.
public class AppConfig {

  public AppConfig() {
    System.out.println("AppConfig() 생성자 호출됨!");
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource ds) {
    // Spring IoC 컨테이너는 이 메서드를 호출하기 전에 
    // 먼저 이 메서드가 원하는 파라미터값인 DataSource를 먼저 생성한다.
    // => createDataSource() 메서드를 먼저 호출한다.
    System.out.println("transactionManager() 호출됨!");

    return new DataSourceTransactionManager(ds);
  }

  @Bean
  public DataSource dataSource() {
    System.out.println("dataSource() 호출됨!");

    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName("org.mariadb.jdbc.Driver");
    ds.setUrl("jdbc:mariadb://localhost:3306/studydb");
    ds.setUsername("study");
    ds.setPassword("1111");
    return ds;
  }

  // multipart/form-data 형식으로 보내온 요청 데이터를 
  // 도메인 객체로 받아주는 도우미 객체를 등록한다. 
  // 이 객체가 등록된 경우 multipart/form-data를 도메인 객체로 받을 수 있다.
  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  // Spring WebMVC의 기본 ViewResolver를 교체한다.
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();  
    viewResolver.setViewClass(JstlView.class); // 주어진 URL을 처리할 객체 => JSP를 실행시켜주는 객체
    viewResolver.setPrefix("/WEB-INF/jsp/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;

  }

}
