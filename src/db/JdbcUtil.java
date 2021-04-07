package db;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {
	
	public static Connection getConnection() { // STATIC 활용해서 바로 사용가능토록
		Connection con = null;
		
		try { // CONNECTION 객체 생성
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env"); 
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MYSQL"); // DB값 변경하면 다른 DB 사용 가능
			// DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/MySQLDB");
			con = ds.getConnection();
			con.setAutoCommit(false); // TRANSACTION 실행
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con) {
		
		try {
			if(con != null) con.close(); /*조건문이 null값을 걸러내서 오류를 덜 발생시킨다. */ 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		
		try {
			if(stmt != null)stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		
		try {
			if(rs != null)rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void commit(Connection con) {
		
		try {
			con.commit();
			System.out.println("commit success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		
		try {
			con.rollback();
			System.out.println("rollback success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
