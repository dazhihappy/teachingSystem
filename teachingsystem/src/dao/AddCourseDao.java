package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddCourseDao {
	public static void main(String[] args) throws Exception {
		addCourse(1,3,"计算机",8);
	}
	
	public static boolean addCourse(int teacher_id,int course_id ,String course_name, int chapter) throws Exception{
		Connection conn = DBPool.getConnection();
		PreparedStatement p =conn.prepareStatement("insert into course values (?,?,?)");
		p.setInt(1, course_id);
		p.setString(2, course_name);
		p.setInt(3, chapter);
		PreparedStatement p1 = conn.prepareStatement("insert into teach values(?,?)");
		p1.setInt(1, teacher_id);
		p1.setInt(2, course_id);
		if(p.executeUpdate()>0&&p1.executeUpdate()>0){
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
