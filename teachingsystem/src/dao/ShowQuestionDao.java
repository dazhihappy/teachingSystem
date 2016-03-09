package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ShowBlank;
import bean.ShowChoice;

public class ShowQuestionDao {
	
	//获取填空题
	public static ArrayList<ShowBlank> showBlanks(int course_id,int type, int chapter ) throws SQLException{
		ArrayList<ShowBlank> list = new ArrayList<ShowBlank>();
		Connection conn = DBPool.getConnection();
		PreparedStatement p =  conn.prepareStatement("select id,question,answer,explain from blank where course_id=? and type =? and chapter=?");
		p.setInt(1, course_id);
		p.setInt(2, type);
		p.setInt(3, chapter);
		ResultSet result = p.executeQuery();
		while(result.next()){
			ShowBlank blank = new ShowBlank();
			blank.setId(result.getInt("id"));
			blank.setQuestion(result.getString("question"));
			blank.setAnswer(result.getString("answer"));
			blank.setExplain(result.getString("explain"));
			blank.setTag(0);// 0--填空
			list.add(blank);
		}
		DBPool.resultSetClose(result);
		DBPool.preparedStatementClose(p);
		DBPool.connectionClose(conn);
		return list;
	}
	
	//获取选择题
	public static ArrayList<ShowChoice> showChoices(int course_id,int type, int chapter ) throws SQLException{
		ArrayList<ShowChoice> list = new ArrayList<ShowChoice>();
		Connection conn = DBPool.getConnection();
		PreparedStatement p =  conn.prepareStatement("select id,question,chooseA,chooseB,chooseC,chooseD,answer,explain from choice where course_id=? and type =? and chapter=?");
		p.setInt(1, course_id);
		p.setInt(2, type);
		p.setInt(3, chapter);
		ResultSet result = p.executeQuery();
		while(result.next()){
			ShowChoice choice = new ShowChoice();
			choice.setId(result.getInt("id"));
			choice.setQuestion(result.getString("question"));
			choice.setAnswer(result.getString("answer"));
			choice.setChooseA(result.getString("chooseA"));
			choice.setChooseB(result.getString("chooseB"));
			choice.setChooseC(result.getString("chooseC"));
			choice.setChooseD(result.getString("chooseD"));
			choice.setExplain(result.getString("explain"));
			choice.setTag(1);//1--选择
			list.add(choice);
		}
		DBPool.resultSetClose(result);
		DBPool.preparedStatementClose(p);
		DBPool.connectionClose(conn);
		return list;
	}
}
