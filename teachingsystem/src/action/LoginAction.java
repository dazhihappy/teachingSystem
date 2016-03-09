package action;
/**
 * 登录action
 */
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.LoginDao;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	private String id;
	private String password;
	
	public void loginCheck() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		if(id.matches("[0-9]+")){
			String name =LoginDao.login(Integer.parseInt(id), password);
			if(name!=null&&!name.equals("")){
				ServletActionContext.getContext().getSession().put("id", id);
				ServletActionContext.getContext().getSession().put("name", name);
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
		}else{
			response.getWriter().print("false");
		}
	}		
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
