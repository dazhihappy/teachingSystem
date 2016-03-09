package action;
/**
 * 注销action
 */
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		ServletActionContext.getContext().getSession().remove("course_id");
		ServletActionContext.getContext().getSession().remove("course_name");
		ServletActionContext.getContext().getSession().remove("name");
		ServletActionContext.getContext().getSession().remove("id");
		return SUCCESS;
	}
}
