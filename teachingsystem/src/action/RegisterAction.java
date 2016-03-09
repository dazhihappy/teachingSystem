package action;
/**
 * 注册action
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import utils.Utils;

import com.opensymphony.xwork2.ActionSupport;

import dao.DBPool;

@SuppressWarnings("serial")
public class RegisterAction extends ActionSupport{
	private String name;
	private String password;
	private int id;
	
	public void register1() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");		
		Connection conn= DBPool.getConnection();
		PreparedStatement p = null;
		try {
			p =conn.prepareStatement("insert teacher values(?,?,?)");
			p.setInt(1, id);
			p.setString(2, name);
			p.setString(3, Utils.DoGetString(password));
			if(p.executeUpdate()>0){			
				response.getWriter().print("true");
			}
		} catch (SQLException e) {
			response.getWriter().print("用户已存在");
		}finally{ 		
			DBPool.preparedStatementClose(p);
			DBPool.connectionClose(conn);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
