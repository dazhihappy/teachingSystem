package action;
/**
 * 添加预习action
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import utils.Utils;

import bean.Preview;

import com.opensymphony.xwork2.ActionSupport;

import dao.AddPreviewDao;

@SuppressWarnings("serial")
public class AddPreviewAction extends ActionSupport{
	private String context;
	private int chapter;
	private String title;
	
	public void addPreview1(){
		int course_id=(Integer)ServletActionContext.getContext().getSession().get("course_id");
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");		
		try {
			Preview preview = new Preview();
			preview.setChapter(chapter);			
			preview.setContext(Utils.DoGetString(context));			 
			preview.setCourse_id(course_id);
			preview.setTitle(Utils.DoGetString(title));
			boolean b=AddPreviewDao.addPreview(preview);			
			if(b){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				response.getWriter().print("wrong");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
 		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
