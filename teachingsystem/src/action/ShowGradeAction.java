package action;
/**
 * 显示成绩action
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.GradeMessage;

import com.opensymphony.xwork2.ActionSupport;

import dao.ShowGradeDao;

@SuppressWarnings("serial")
public class ShowGradeAction extends ActionSupport {
	private int chapter;
	
	public void selectGrade(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int course_id=(Integer)ServletActionContext.getContext().getSession().get("course_id");		
		try {
			ArrayList<GradeMessage> list =ShowGradeDao.getGradeMessgae(course_id, chapter);	
			JSONObject jsonObject = new JSONObject();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONObject object = new JSONObject();
				object.put("name",list.get(i).getStudentName());
				object.put("id", list.get(i).getStudentId());
				object.put("grade", list.get(i).getStudentGrade());
				object.put("class", list.get(i).getStudentClass());
				object.put("score", list.get(i).getScore());
				array.put(object);
			}
			jsonObject.accumulate("success", true);
			jsonObject.accumulate("gradeList", array);
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}	
}
