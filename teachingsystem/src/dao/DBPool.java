package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBPool {
	private static ComboPooledDataSource cpds;
	static{
		cpds = new ComboPooledDataSource();
	}
	
	public static Connection getConnection()
	{
		Connection conn = null;
	    try {
	       conn = cpds.getConnection();
	     }
	     catch (SQLException e) {
	       e.printStackTrace();
	     }
	     return conn;
	 }
	
	public static void resultSetClose(ResultSet resultSet){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void preparedStatementClose(PreparedStatement statement){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void connectionClose(Connection connection){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		String sql ="select * from student";
		Connection conn = null;
		conn = DBPool.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet set = statement.executeQuery();
		while(set.next()){
			System.out.println(set.getString("name"));
		}
		DBPool.resultSetClose(set);
		DBPool.preparedStatementClose(statement);
		DBPool.connectionClose(conn);
	}
}
