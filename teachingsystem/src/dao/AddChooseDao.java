package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import bean.Choice;

public class AddChooseDao {
	public static boolean addChoose(Choice choice) throws SQLException{
		Connection conn = DBPool.getConnection();
		PreparedStatement p =conn.prepareStatement("insert into choice(question,chooseA,chooseB,chooseC,chooseD,answer,explain,type,course_id,chapter) values(?,?,?,?,?,?,?,?,?,?)");
		p.setString(1, choice.getQuestion());
		p.setString(2, choice.getChooseA());
		p.setString(3, choice.getChooseB());
		p.setString(4, choice.getChooseC());
		p.setString(5, choice.getChooseD());
		p.setString(6, choice.getAnswer());
		p.setString(7, choice.getExplain());
		p.setInt(8, choice.getType());
		p.setInt(9, choice.getCourse_id());
		p.setInt(10, choice.getChapter());
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
}
