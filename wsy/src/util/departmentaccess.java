package util;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.department;

public class departmentaccess {

	public static ArrayList<department> getDepartments(String d_id) {
		String sql="SELECT * FROM department";
		if (d_id!=null&&!d_id.equals("")) {
			sql+=" where d_id= '"+d_id+" '";
		}
		ResultSet resultSet=null;
		Statement statement=null;
		Connection connection=null;
		ArrayList<department> list=new ArrayList<department>();
		try {
			connection=jdutil.getCon();
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while (resultSet.next()) {
				String did=resultSet.getString("d_id");
				String dname=resultSet.getString("d_name");
				department department1=new department(did, dname);
				list.add(department1);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return list;	
	}
	
	public static int insert(String d_id,String d_name) {
		int re=0;
		String sql="INSERT INTO department (d_id,d_name) VALUES ('"+d_id+"','"+d_name+"')";
		Connection connection=null;
		try {
			connection=jdutil.getCon();
			if (connection==null&&connection.isClosed()) {
				return -1;
			}
			Statement statement=connection.createStatement();
			re=statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
	public static int delect (String d_id) throws SQLException {
		String sql="DELETE FROM department WHERE d_id='"+d_id+"'";
		int re=0;
		Connection connection=null;
		Statement statement=null;
		try {
			connection=jdutil.getCon();
			if (connection==null&&connection.isClosed()) {
				return 0;
			}
			statement=connection.createStatement();
			re=statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return re;	
	}
	public static int update (String d_id,String d_name) {
		int re=0;
		String sql="UPDATE department SET d_name='"+d_name+"' WHERE d_id='"+d_id+"'";
		Connection connection=null;
		try {
			Statement statement=null;
			connection=jdutil.getCon();
			statement=connection.createStatement();
			re=statement.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;
	}
}
