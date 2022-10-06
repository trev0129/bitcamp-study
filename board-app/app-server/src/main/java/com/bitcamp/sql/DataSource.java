package com.bitcamp.sql;

import java.sql.Connection;
import java.sql.DriverManager;

// 스레드 전용 DB 커넥션을 제공하는 일 
public class DataSource {

  String jdbcUrl;
  String username;
  String password;

  // 스레드 전용 DB보관소 객체
  ThreadLocal<Connection> conStore = new ThreadLocal<>();

  public DataSource( String driver, String jdbcUrl, String username, String password) throws Exception {
    //  JDBC Driver 클래스 로딩하기 
    Class.forName(driver);

    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws Exception {
    Thread currThread = Thread.currentThread();
    System.out.printf("%s=> getConnection() 호출\n", currThread.getName());
    Connection con = conStore.get();
    if (con == null) {
      con = DriverManager.getConnection(jdbcUrl, username, password);
      conStore.set(con);
      System.out.printf("%s=> Connection 객체 생성\n", currThread.getName());
    }
    return con;
  }

}
