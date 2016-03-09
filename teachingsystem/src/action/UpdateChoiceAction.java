package action;
/**
 * 修改选择题action
 */
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import utils.Utils;
import bean.Choice;
import dao.UpdateQuestionDao;

public class UpdateChoiceAction {
	private int id;
	private String title;
	private String answer;
	private String explain;
	private int type;
	private int chapter;
	private String chooseA;
	private String chooseB;
	private String chooseC;
	private String chooseD;
	
	public void updateChoice1() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		int course_id=(Integer)ServletActionContext.getContext().getSession().get("course_id");				
		try {
			Choice choice = new Choice();
			choice.setAnswer(Utils.DoGetString(answer));
			choice.setChapter(chapter);
			choice.setChooseA(Utils.DoGetString(chooseA));
			choice.setChooseB(Utils.DoGetString(chooseB));
			choice.setChooseC(Utils.DoGetString(chooseC));
			choice.setChooseD(Utils.DoGetString(chooseD));
			choice.setCourse_id(course_id);
			choice.setExplain(Utils.DoGetString(explain));
			choice.setId(id);
			choice.setQuestion(Utils.DoGetString(title));
			choice.setType(type);
			boolean b =UpdateQuestionDao.updateChoice(choice);
			if(b){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
		} catch (SQLException e) {
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
	public String getChooseA() {
		return chooseA;
	}
	public void setChooseA(String chooseA) {
		this.chooseA = chooseA;
	}
	public String getChooseB() {
		return chooseB;
	}
	public void setChooseB(String chooseB) {
		this.chooseB = chooseB;
	}
	public String getChooseC() {
		return chooseC;
	}
	public void setChooseC(String chooseC) {
		this.chooseC = chooseC;
	}
	public String getChooseD() {
		return chooseD;
	}
	public void setChooseD(String chooseD) {
		this.chooseD = chooseD;
	}
}
