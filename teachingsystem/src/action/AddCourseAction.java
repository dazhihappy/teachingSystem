package action;
/**
 * 添加课程action
 */
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import dao.AddCourseDao;

@SuppressWarnings("serial")
public class AddCourseAction extends ActionSupport{
	private String course_name;
	private int chapter;
	private int course_id;
	
	public void addCourse1() throws IOException  {
		HttpServletResponse response =ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String teacher_id =(String ) ServletActionContext.getContext().getSession().get("id");
		boolean b = false;
		try {
			b = AddCourseDao.addCourse(Integer.parseInt(teacher_id), course_id, Utils.DoGetString(course_name), chapter);
			if(b){			
				response.getWriter().print("true");			 
			}else{			
				response.getWriter().print("false");			
			}
		} catch (IOException e){
			response.getWriter().print("false");
		} catch (NumberFormatException e) {
			response.getWriter().print("false");
		} catch (Exception e) {
			response.getWriter().print("false");
		}
	}
	
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
}
