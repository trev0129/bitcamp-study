package com.bitcamp.board.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// @Transaction 애노테이션을 사용하여 트랙잭션을 제어할 수 있게 기능을 활성화시킨다. 
@EnableTransactionManagement

@PropertySource("classpath:com/bitcamp/board/config/jdbc.properties")
public class DatabaseConfig {

  public DatabaseConfig() {
    System.out.println("DatabaseConfig() 생성자 호출됨!");
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource ds) {
    System.out.println("tx ");
    return new DataSourceTransactionManager(ds);
  }

  // ${jdbc.url} ==> .properties 파일에 서 jdbc.url 이름으로 저장된 값을 가져온다.
  // @Value("...") ==> 애노테이션에 지정된 문자
  @Bean
  public DataSource dataSource(
      @Value("${jdbc.driverClassName}") String driverClassName,
      @Value("${jdbc.url}") String url,
      @Value("${jdbc.username}") String username,
      @Value("${jdbc.password}") String password) {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(driverClassName);
    ds.setUrl(url);
    ds.setUsername(username);
    ds.setPassword(password);
    return ds;
  }

}
