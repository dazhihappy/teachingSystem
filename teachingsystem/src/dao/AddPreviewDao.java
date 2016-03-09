package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.Preview;

public class AddPreviewDao {
	public static void main(String[] args) throws Exception {
		Preview preview = new Preview();
		preview.setChapter(3);
		preview.setContext("请预习第一章内容");
		preview.setCourse_id(1);
		preview.setTitle("第一章预习");
		addPreview(preview);
	}
	
	public static boolean addPreview(Preview preview) throws SQLException{
		Connection conn = DBPool.getConnection();
		PreparedStatement p =conn.prepareStatement("insert into preview values(?,?,?,?)");
		p.setString(1, preview.getTitle());
		p.setString(2, preview.getContext());
		p.setInt(3, preview.getCourse_id());
		p.setInt(4, preview.getChapter());
		if(p.executeUpdate()>0){
			DBPool.preparedStatementClose(p);
			DBPool.connectionClose(conn);
			return true;
		}else{
			DBPool.preparedStatementClose(p);
			DBPool.connectionClose(conn);
			return false;
		}
	}
}
