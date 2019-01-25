package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.View_Teacher;

public class TeacherAccess {

	public static ArrayList<View_Teacher> getView_teacher (String t_idd) {//t_idd为教师编号
		
		String sql="select * from view_teacher";//查询语句
		if(!(t_idd.equals("")))
			sql+=" where t_id= "+t_idd;
		ArrayList<View_Teacher> teacher =new ArrayList<View_Teacher>();
		Connection con=null;
		ResultSet re=null;
		try {
			con=jdutil.getCon();//获取数据库连接
			if (con==null) {
				return null;
			}
			Statement statement=con.createStatement();//查询
			re=statement.executeQuery(sql);//执行查询语句
			while (re.next()) {
				String d_id = re.getString("d_id");
				String d_name = re.getString("d_name");
				String o_id = re.getString("o_id");
				String o_name = re.getString("o_name");
				String t_id = re.getString("t_id");
				String t_name = re.getString("t_name");
				String t_password = re.getString("t_password");
				String t_power = re.getString("t_power");
				String t_tel = re.getString("t_tel");
				View_Teacher vTeacher=new View_Teacher(d_id, d_name, o_id, o_name, t_id, t_name, t_password, t_power, t_tel);
				teacher.add(vTeacher);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				re.close();
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return teacher;	
	}
	
	public static int changepassword(Connection connection,View_Teacher teacher) throws SQLException {
		int re=0;
		Statement statement=null;
		try {
			statement=connection.createStatement();
			re=statement.executeUpdate("update teacher set t_password='"+teacher.getT_password()+"' where t_id='"+teacher.getT_id()+"'");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return re;	
	}
}
