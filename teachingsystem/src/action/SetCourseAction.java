package action;
/**
 * 设置课程action
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.DBPool;

@SuppressWarnings("serial")
public class SetCourseAction extends ActionSupport{
	private String course;

	@Override
	public String execute() throws Exception {
		Connection conn = DBPool.getConnection();
		String teacher_id =(String) ServletActionContext.getContext().getSession().get("id");
		PreparedStatement p =conn.prepareStatement("select name,id from course where id in(select course_id from teach where teacher_id=?)");
		p.setInt(1, Integer.parseInt(teacher_id));
		ResultSet result= p.executeQuery();
		while(result.next()){
			if(result.getString("name").equals(course)){
				ServletActionContext.getContext().getSession().put("course_id", result.getInt("id"));
				ServletActionContext.getContext().getSession().put("course_name",course);
				break;
			}
		}
		DBPool.resultSetClose(result);
		DBPool.preparedStatementClose(p);
		DBPool.connectionClose(conn);
		return SUCCESS;
	}
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
}
