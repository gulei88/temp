package global.dao;

import global.model.view_courselaboratory;
import global.model.view_courselaboratory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Courselaboratoryaccess {
	/**
	 * @author czy
	 * @param condition
	 *            :查询条件
	 * @return “view_courselaboratory”类的数组列表
	 */
	public static ArrayList<view_courselaboratory> getCourselaboratory(
			String condition) {
		// 生成查找“view_courselaboratory”表的select查询语句
		String sql = "SELECT * FROM view_courselaboratory";
		// 如果传入的条件不为空，则SQL语句添加查找条件为根据条件查找“view_courselaboratory”视图数据
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// 初始化“view_courselaboratory”类的数组列表对象
		ArrayList<view_courselaboratory> view_courselaboratorylist = new ArrayList<view_courselaboratory>();
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
				int ul_id = rs.getInt("ul_id");
				String grade = rs.getString("grade");
				String Experimentalenvironment = rs
						.getString("Experimentalenvironment");
				String cou_id = rs.getString("cou_id");
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
				int cr_id = rs.getInt("cr_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				int cr_seating = rs.getInt("cr_seating");
				int b_id = rs.getInt("b_id");
				int sl_id = rs.getInt("sl_id");
				String sl_name = rs.getString("sl_name");
				int sl_seating = rs.getInt("sl_seating");

				// 根据结果集的数据生成“view_courselaboratory”类对象
				view_courselaboratory dm = new view_courselaboratory(ul_id,
						grade, Experimentalenvironment, cou_id, tp_id,
						cou_category, cou_name, cou_credit, cou_theoryhour,
						cou_experimentalhours, cou_practicehour, cou_semester,
						cou_type, d_id, cr_id, cr_name, ct_id, cr_seating,
						b_id, sl_id, sl_name, sl_seating);
				// 将“view_courselaboratory”类对象添加到“view_courselaboratory”类的数组列表对象中
				view_courselaboratorylist.add(dm);
			}
			// 返回“view_courselaboratory”类的数组列表对象
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
		return view_courselaboratorylist;
	}

}
