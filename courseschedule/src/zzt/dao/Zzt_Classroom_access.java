package zzt.dao;

import global.dao.Databaseconnection;
import global.model.Classroom;
import global.model.Office;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import zzt.model.Zzt_Teacher_login;
import zzt.view.Zzt_JIF_Classroom;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Zzt_Classroom_access {
	public static ArrayList<Classroom> getClassroom(String d_id) throws SQLException,
			ClassNotFoundException {
		// 生成查找“Office”表的select查询语句
		String sql = "SELECT * FROM classroom";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Office”视图数据
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// 初始化“Office”类的数组列表对象
		ArrayList<Classroom> Classroomlist = new ArrayList<Classroom>();
		// 取得数据库的连接
		java.sql.Connection con = Databaseconnection.getconnection();
		// 如果数据库的连接为空，则返回空
		if (con == null)
			return null;
		// 生成数据库声明对象
		Statement st = (Statement) con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		// 如果结果集有数据
		while (rs.next()) {
//			private int cr_id;
//			private String d_id;
//			private String cr_name;
//			private int ct_id;
//			private int cr_seating;
//			private int b_id;
			int cr_id=rs.getInt("cr_id");	
			String cr_name = rs.getString("cr_name");
			d_id=rs.getString("d_id");
			int ct_id=rs.getInt("ct_id");
			int cr_seating = rs.getInt("cr_seating");
			int b_id=rs.getInt("b_id");
			
//			String b_name = rs.getString("b_name");
//			String b_alias = rs.getString("b_alias");
//			String b_address = rs.getString("b_address");
//			int cr_seating = rs.getInt("cr_seating");
//			String d_name = rs.getString("d_name");
			// 根据结果集的数据生成“Office”类对象
			Classroom of = new	Classroom(cr_id, d_id, cr_name, ct_id, cr_seating, b_id);
//			 将“Office”类对象添加到“Office”类的数组列表对象中
			Classroomlist.add(of);
		}
		// 返回“View_teacher”类的数组列表对象
		return Classroomlist;
	}
}
