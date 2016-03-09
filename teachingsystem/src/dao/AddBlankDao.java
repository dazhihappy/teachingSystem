package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.Blank;

public class AddBlankDao {
	public static void main(String[] args) throws SQLException {
		Blank blank = new Blank();
		blank.setAnswer("答案");
		blank.setChapter(1);
		blank.setCourse_id(2);
		blank.setExplain("解释");
		blank.setQuestion("问题");
		blank.setType(1);
		addBlank(blank);
	}
	
	public static boolean addBlank(Blank blank) throws SQLException{
		Connection conn = DBPool.getConnection();
		
		PreparedStatement p =conn.prepareStatement("insert into blank(question,answer,explain,type,course_id,chapter) values(?,?,?,?,?,?)");
		p.setString(1, blank.getQuestion());
		p.setString(2, blank.getAnswer());
		p.setString(3, blank.getExplain());
		p.setInt(4, blank.getType());
		p.setInt(5, blank.getCourse_id());
		p.setInt(6, blank.getChapter());
		if(p.executeUpdate()>0){ //executeUpdate返回受影响的行数
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
