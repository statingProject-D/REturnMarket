package co.REturnMarket.admin.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	public Connection conn;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://118.131.179.138:3306/ip20b_kc?serverTimezone=UTC";
	private String username = "ip20bkc";
	private String password = "ip20bkc";

	public DAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
