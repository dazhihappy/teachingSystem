package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.GradeMessage;

public class ShowGradeDao {
	public static ArrayList<GradeMessage> getGradeMessgae(int course_id ,int chapter) throws SQLException{
		ArrayList<GradeMessage> list = new ArrayList<GradeMessage>();
		Connection conn= DBPool.getConnection();
		PreparedStatement p =conn.prepareStatement("select id,name,grade,class,score from  student,grade  where course_id=? and chapter=? and grade.student_id= student.id order by  score desc");
		p.setInt(1,course_id);
		p.setInt(2, chapter);
		ResultSet result  =p.executeQuery();
		while(result.next()){
			GradeMessage gradeMessage = new GradeMessage();
			gradeMessage.setStudentName(result.getString("name"));
			gradeMessage.setStudentId(result.getInt("id"));
			gradeMessage.setStudentGrade(result.getString("grade"));
			gradeMessage.setStudentClass(result.getString("class"));
			gradeMessage.setScore(result.getInt("score"));
			list.add(gradeMessage);
		}
		DBPool.resultSetClose(result);
		DBPool.preparedStatementClose(p);
		DBPool.connectionClose(conn);
		return list;
	}
}
