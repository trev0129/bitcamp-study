package com.bitcamp.transaction;

import java.sql.Connection;
import com.bitcamp.sql.DataSource;

public class TransactionManager {
  DataSource ds;

  public TransactionManager(DataSource ds) {
    this.ds = ds;
  }

  public TransactionStatus getTransaction() throws Exception {
    Connection con = ds.getConnection();

    con.setAutoCommit(false);

    return new TransactionStatus(con);
  }

  public void commit(TransactionStatus status) {
    try {
      status.get().commit();
    } catch (Exception e) {
      // 예외 무시 
    } finally {
      try {
        status.get().setAutoCommit(true);
      } catch (Exception e) {
        //예외 무시 
      }
    }
  }

  public void rollback(TransactionStatus status) {
    try {
      status.get().rollback();
    } catch (Exception e) {
      // 예외 무시 
    } finally {
      try {
        status.get().setAutoCommit(true);
      } catch (Exception e) {
        //예외 무시 
      }
    }
  }

}
