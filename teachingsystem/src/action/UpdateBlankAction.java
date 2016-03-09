package action;
/**
 * 修改填空题action
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import utils.Utils;
import bean.Blank;

import com.opensymphony.xwork2.ActionSupport;

import dao.UpdateQuestionDao;

@SuppressWarnings("serial")
public class UpdateBlankAction extends ActionSupport{	
	private int id;
	private String title;
	private String answer;
	private String explain;
	private int type;
	private int chapter;
	
	public void updateBlank1() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		int course_id=(Integer)ServletActionContext.getContext().getSession().get("course_id");		
		try {
			Blank blank = new Blank();
			blank.setAnswer(Utils.DoGetString(answer));
			blank.setChapter(chapter);
			blank.setCourse_id(course_id);
			blank.setExplain(Utils.DoGetString(explain));
			blank.setId(id);
			blank.setQuestion(Utils.DoGetString(title));
			blank.setType(type);
			boolean b =UpdateQuestionDao.updateBlank(blank);
			if(b){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
		}catch (SQLException e) {
			response.getWriter().print("false");
		}		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
}
