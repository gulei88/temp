package global.dao;

import global.model.View_course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Viewcourseaccess {
	public static ArrayList<View_course> getCourses(String cou_id) {
		// 生成查找“Major”表的select查询语句
		String sql = "SELECT * FROM View_course";
		// 如果传入的科室编号不为空，则SQL语句添加查找条件为根据科室编号查找“Major”视图数据
		if (!(cou_id == null || cou_id.equals(""))) {
			sql += " WHERE cou_id='" + cou_id + "'";
		}
		// 初始化“Major”类的数组列表对象
		ArrayList<View_course> courselist = new ArrayList<View_course>();
		// 取得数据库的连接
		Connection con=null;
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
				String tp_id = rs.getString("tp_id");
				String tp_name=rs.getString("tp_name");
				String tp_mark=rs.getString("tp_mark");
				String m_id=rs.getString("m_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String d_id = rs.getString("d_id");
				String d_name=rs.getString("d_name");
				// 根据结果集的数据生成“Major”类对象
				View_course cou = new View_course(tp_id, tp_name, tp_mark, m_id, cou_id, cou_category, cou_name, cou_credit, cou_theoryhour, cou_experimentalhours, cou_practicehour, cou_semester, cou_type, d_id, d_name);
				// 将“Major”类对象添加到“Major”类的数组列表对象中
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
