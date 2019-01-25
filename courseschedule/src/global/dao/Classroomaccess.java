package global.dao;

import global.model.Classroom;
import global.model.Department;
import global.model.Level;
import global.model.View_classroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Classroomaccess {
	/**
	 * @author czy
	 * @param o_id
	 *            :科室编号
	 * @return “View_classroom”类的数组列表
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<View_classroom> getView_classroom(String condition) {
		// 生成查找“View_classroom”表的select查询语句
		String sql = "SELECT * FROM view_classroom";
		// 如果传入的条件不为空，则SQL语句添加查找条件为根据条件查找“View_classroom”视图数据
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// 初始化“View_classroom”类的数组列表对象
		ArrayList<View_classroom> View_classroomlist = new ArrayList<View_classroom>();
		// 取得数据库的连接
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Databaseconnection.getconnection();
			// 如果数据库的连接为空，则返回空
			if (con == null)
				return null;
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);
			// 如果结果集有数据
			while (rs.next()) {
				// 取出结果集对应字段数据
				int b_id = rs.getInt("b_id");
				String b_name = rs.getString("b_name");
				String b_alias = rs.getString("b_alias");
				String b_address = rs.getString("b_address");
				int cr_id = rs.getInt("cr_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				String ct_name = rs.getString("ct_name");
				int seating = rs.getInt("seating");
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				String sl_name = rs.getString("sl_name");
				// 根据结果集的数据生成“View_classroom”类对象
				View_classroom m = new View_classroom(d_id, d_name, cr_id,
						cr_name, sl_name, seating, ct_id, ct_name, b_id,
						b_name, b_alias, b_address);
				// 将“View_classroom”类对象添加到“View_classroom”类的数组列表对象中
				View_classroomlist.add(m);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 返回“View_teacher”类的数组列表对象
		return View_classroomlist;
	}
	
	public static ArrayList<Department> getDepartment(String d_id) {
		// 生成查找“Department”表的select查询语句
		String sql = "SELECT * FROM department";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Department”视图数据
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// 初始化“Department”类的数组列表对象
		ArrayList<Department> departmentlist = new ArrayList<Department>();
		// 取得数据库的连接
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Databaseconnection.getconnection();
			// 如果数据库的连接为空，则返回空
			if (con == null)
				return null;
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);
			// 如果结果集有数据
			while (rs.next()) {
				// 取出结果集对应字段数据
				d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				// 根据结果集的数据生成“Department”类对象
				Department dm = new Department(d_id, d_name);
				// 将“Department”类对象添加到“Department”类的数组列表对象中
				departmentlist.add(dm);
			}
			// 返回“Department”类的数组列表对象
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return departmentlist;
	}
	
	
	
	
	public static ArrayList<Classroom> getClassroom(int cr_id) {
		// 生成查找“Level”表的select查询语句
		String sql = "SELECT * FROM classroom";
		// 如果传入的科室编号不为空，则SQL语句添加查找条件为根据科室编号查找“Level”视图数据
		if (!(cr_id == 0)) {
			sql += " WHERE cr_id='" + cr_id + "'";
		}
		// 初始化“Major”类的数组列表对象
		ArrayList<Classroom> levellist = new ArrayList<Classroom>();
		// 取得数据库的连接
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Databaseconnection.getconnection();
			// 如果数据库的连接为空，则返回空
			if (con == null)
				return null;
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);
			// 如果结果集有数据
			while (rs.next()) {
				// 取出结果集对应字段数据
				cr_id = rs.getInt("cr_id");
				String d_id = rs.getString("d_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				int cr_seating = rs.getInt("cr_seating");
				int b_id = rs.getInt("b_id");
				// 根据结果集的数据生成“Level”类对象
				Classroom m = new Classroom(cr_id, d_id,cr_name,ct_id,cr_seating,b_id);
				// 将“Level”类对象添加到“Level”类的数组列表对象中
				levellist.add(m);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 返回“levellist”类的数组列表对象
		return levellist;
	}
}
