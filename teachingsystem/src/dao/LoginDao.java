package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {	
	
	public static String login(int id,String password) throws Exception{
		Connection conn = DBPool.getConnection();
		PreparedStatement p = conn.prepareStatement("select * from teacher where id =? and password =?");
		p.setInt(1, id);
		p.setString(2, password);
		ResultSet result =p.executeQuery();
		if(result.next()){
			String name = result.getString("name");
			DBPool.resultSetClose(result);
			DBPool.preparedStatementClose(p);
			DBPool.connectionClose(conn);
			return name;
		}else{
			DBPool.resultSetClose(result);
			DBPool.preparedStatementClose(p);
			DBPool.connectionClose(conn);
			return null;
		}
	}
	
		
}


