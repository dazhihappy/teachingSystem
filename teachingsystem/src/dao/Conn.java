package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	public static Connection getConnection(){
		  String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ssdutedu";  //连接服务器和数据库test
		  String userName = "sa";  //默认用户名
		  String userPwd = "123456";  //密码
		  Connection conn = null;
		  try {
		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		   conn = DriverManager.getConnection(dbURL, userName, userPwd);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return conn;
	}
}
