package action;
/**
 * 删除习题action
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.DeleteQuestionDao;

@SuppressWarnings("serial")
public class DelQuestionAction extends ActionSupport{
	private int id;
	private int tag;//0-填空；1-选择
	
	//删除问题
	public void delQuestion(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		if(tag==0){
			try {
				boolean b= DeleteQuestionDao.deleteBlank(id);
				if(b){
					response.getWriter().print("true");
				}else{
					response.getWriter().print("false");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				boolean b= DeleteQuestionDao.deleteChoice(id);
				if(b){
					response.getWriter().print("true");
				}else{
					response.getWriter().print("false");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
