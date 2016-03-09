package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteQuestionDao {
	public static void main(String[] args) throws SQLException {
		deleteChoice(4);
	}
	
	//删除填空题
	public static boolean deleteBlank(int id) throws SQLException{
		Connection conn= DBPool.getConnection();
		PreparedStatement p = conn.prepareStatement("delete from blank where id=?");
		p.setInt(1, id);
		if(p.executeUpdate()>0){
			DBPool.preparedStatementClose(p);
			DBPool.connectionClose(conn);
			return true;
		}else{
			DBPool.preparedStatementClose(p);
			DBPool.connectionClose(conn);
			return false;
		}
	}
	
	//删除选择题
	public static boolean deleteChoice(int id) throws SQLException{
		Connection conn= Conn.getConnection();
		PreparedStatement p = conn.prepareStatement("delete from choice where id=?");
		p.setInt(1, id);
		if(p.executeUpdate()>0){
			return true;
		}else{
			return false;
		}
	}
}
