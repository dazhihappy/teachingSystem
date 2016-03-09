package action;
/**
 * 添加填空题action
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import utils.Utils;
import bean.Blank;
import com.opensymphony.xwork2.ActionSupport;

import dao.AddBlankDao;

@SuppressWarnings("serial")
public class AddBlankAction extends ActionSupport{
	private int chapter;
	private int type;
	private String title;
	private String answer;
	private String explain;
	
	public void addBlank1() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		int course_id=(Integer)ServletActionContext.getContext().getSession().get("course_id");		
		Blank blank = new Blank();
		blank.setCourse_id(course_id);
		blank.setChapter(chapter);
		blank.setType(type);
		blank.setQuestion(Utils.DoGetString(title));
		blank.setAnswer(Utils.DoGetString(answer));
		blank.setExplain(Utils.DoGetString(explain));
		try {
			boolean b = AddBlankDao.addBlank(blank);
			if(b){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
