package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import bean.Blank;
import bean.Choice;

public class UpdateQuestionDao {
	
	public static void main(String[] args) throws SQLException {
		Choice choice = new Choice();
		choice.setId(19);
		choice.setQuestion("1");
		choice.setAnswer("1");
		choice.setType(1);
		choice.setChapter(1);
		choice.setCourse_id(1);
		choice.setExplain("1");
		choice.setChooseA("1");
		choice.setChooseB("1");
		choice.setChooseC("1");
		choice.setChooseD("1");
		updateChoice(choice);
	}
	
	//更新填空题
	public static boolean updateBlank(Blank blank) throws SQLException{
		Connection conn = DBPool.getConnection();
		String sql="update blank set question =? ,answer=?,explain=?,type=?,chapter=? where id =?";
		PreparedStatement p =conn.prepareStatement(sql);
		p.setString(1, blank.getQuestion());
		p.setString(2, blank.getAnswer());
		p.setString(3, blank.getExplain());
		p.setInt(4, blank.getType());
		p.setInt(5, blank.getChapter());
		p.setInt(6, blank.getId());
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
	
	//更新选择题
	public static boolean updateChoice(Choice choice) throws SQLException{
		Connection conn = Conn.getConnection();
		String sql="update choice set question =?,chooseA=? ,chooseB=?,chooseC=?,chooseD=?,answer=?,explain=?,type=?,chapter=? where id =?";
		PreparedStatement p =conn.prepareStatement(sql);
		p.setString(1, choice.getQuestion());
		p.setString(2, choice.getChooseA());
		p.setString(3, choice.getChooseB());
		p.setString(4, choice.getChooseC());
		p.setString(5, choice.getChooseD());
		p.setString(6, choice.getAnswer());
		p.setString(7, choice.getExplain());
		p.setInt(8, choice.getType());
		p.setInt(9, choice.getChapter());
		p.setInt(10, choice.getId());
		if(p.executeUpdate()>0){
			return true;
		}else{
			return false;
		}
	}
}
