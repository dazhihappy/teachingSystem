package action;
/**
 * 显示问题action
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import bean.ShowBlank;
import bean.ShowChoice;
import com.opensymphony.xwork2.ActionSupport;
import dao.ShowQuestionDao;

@SuppressWarnings("serial")
public class ShowQuestionAction extends ActionSupport{
	private int chapter;	//章节
	private int type;		//0-填空；1-选择
	private int type1;		//0-测试；1-复习
	
	public void showQue(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int course_id=(Integer)ServletActionContext.getContext().getSession().get("course_id");
		if(type==0){
			try {
			ArrayList<ShowBlank> list = ShowQuestionDao.showBlanks(course_id, type1, chapter);
			JSONObject jsonObject = new JSONObject();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONObject object = new JSONObject();
				object.put("id",list.get(i).getId());
				object.put("question", list.get(i).getQuestion());
				object.put("answer", list.get(i).getAnswer());
				object.put("explain", list.get(i).getExplain());
				object.put("tag", list.get(i).getTag()); //0--填空
				array.put(object);
			}
			jsonObject.accumulate("success", true);
			jsonObject.accumulate("gradeList", array);
			response.getWriter().print(jsonObject.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
			ArrayList<ShowChoice> list = ShowQuestionDao.showChoices(course_id, type1, chapter);
			JSONObject jsonObject = new JSONObject();
			JSONArray array = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONObject object = new JSONObject();
				object.put("id",list.get(i).getId());
				object.put("chooseA", list.get(i).getChooseA());
				object.put("chooseB", list.get(i).getChooseB());
				object.put("chooseC", list.get(i).getChooseC());
				object.put("chooseD", list.get(i).getChooseD());
				object.put("question", list.get(i).getQuestion());
				object.put("answer", list.get(i).getAnswer());
				object.put("explain", list.get(i).getExplain());
				object.put("tag", list.get(i).getTag()); //1--选择
				array.put(object);
			}
			jsonObject.accumulate("success", true);
			jsonObject.accumulate("gradeList", array);
			response.getWriter().print(jsonObject.toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
	public int getType1() {
		return type1;
	}
	public void setType1(int type1) {
		this.type1 = type1;
	}
}
