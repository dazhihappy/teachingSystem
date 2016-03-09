package action;
/**
 * url错误处理action
 */
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ErrorAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
