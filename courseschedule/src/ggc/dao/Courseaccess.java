package ggc.dao;

import global.dao.Databaseconnection;
import global.model.Course;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Courseaccess {
	public static ArrayList<Course> getcourse(String cou_id) {
		// 生成查找“Office”表的select查询语句
		String sql = "SELECT * FROM course";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Office”视图数据
		if (!(cou_id == null || cou_id.equals(""))) {
			sql += " WHERE cou_id='" + cou_id + "'";
		}
		// 初始化“Office”类的数组列表对象
		ArrayList<Course> courselist = new ArrayList<Course>();
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
				cou_id=rs.getString("cou_id");
				String tp_id = rs.getString("tp_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String d_id = rs.getString("d_id");
				// 根据结果集的数据生成“Office”类对象
				Course cou = new Course(cou_id, tp_id, cou_category, cou_name, cou_credit, cou_theoryhour, cou_experimentalhours, cou_practicehour, cou_semester, cou_type, d_id);
				// 将“Office”类对象添加到“Office”类的数组列表对象中
				courselist.add(cou);
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
		return courselist;
	
	}
}
