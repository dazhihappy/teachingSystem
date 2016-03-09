package action;
/**
 * 编辑习题action
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.struts2.ServletActionContext;

import bean.Blank;
import bean.Choice;

import com.opensymphony.xwork2.ActionSupport;

import dao.DBPool;

@SuppressWarnings("serial")
public class EditQuestionAction extends ActionSupport{
	private int id;
	private int tag;//0-填空；1-选择
	
	@Override
	public String execute() throws Exception {
		if(tag==0){
			Connection conn = DBPool.getConnection();
			PreparedStatement p =conn.prepareStatement("select * from blank where id =?");
			p.setInt(1, id);
			ResultSet result = p.executeQuery();
			while(result.next()){
				Blank blank = new Blank();
				blank.setId(result.getInt("id"));
				blank.setQuestion(result.getString("question"));
				blank.setAnswer(result.getString("answer"));
				blank.setChapter(result.getInt("chapter"));
				blank.setCourse_id(result.getInt("course_id"));
				blank.setExplain(result.getString("explain"));
				blank.setType(result.getInt("type"));
				ServletActionContext.getContext().put("blank", blank);
			}
			DBPool.resultSetClose(result);
			DBPool.preparedStatementClose(p);
			DBPool.connectionClose(conn);
			return "blank";
		}else{
			Connection conn = DBPool.getConnection();
			PreparedStatement p =conn.prepareStatement("select * from choice where id =?");
			p.setInt(1, id);
			ResultSet result = p.executeQuery();
			while(result.next()){
				Choice choice = new Choice();
				choice.setId(result.getInt("id"));
				choice.setQuestion(result.getString("question"));
				choice.setAnswer(result.getString("answer"));
				choice.setChapter(result.getInt("chapter"));
				choice.setCourse_id(result.getInt("course_id"));
				choice.setExplain(result.getString("explain"));
				choice.setType(result.getInt("type"));
				choice.setChooseA(result.getString("chooseA"));
				choice.setChooseB(result.getString("chooseB"));
				choice.setChooseC(result.getString("chooseC"));
				choice.setChooseD(result.getString("chooseD"));
				ServletActionContext.getContext().put("choice", choice);
			}
			DBPool.resultSetClose(result);
			DBPool.preparedStatementClose(p);
			DBPool.connectionClose(conn);
			return "choice";
		}		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
}
