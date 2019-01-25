package global.dao;

import global.model.Sublaboratory;
import global.model.View_classroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Sublaboratoryaccess {
	/**
	 * @author czy
	 * @param condition
	 *            :查询条件
	 * @return “Sublaboratory”类的数组列表
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<Sublaboratory> getSublaboratory(String condition) {
		// 生成查找“Sublaboratory”表的select查询语句
		String sql = "SELECT * FROM sublaboratory";
		// 如果传入的条件不为空，则SQL语句添加查找条件为根据条件查找“View_classroom”视图数据
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// 初始化“View_classroom”类的数组列表对象
		ArrayList<Sublaboratory> sllist = new ArrayList<Sublaboratory>();
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
				int sl_id= rs.getInt("sl_id");
				int cr_id= rs.getInt("cr_id");
				String sl_name = rs.getString("sl_name");
				int sl_seating= rs.getInt("sl_seating");
				// 根据结果集的数据生成“View_classroom”类对象
				Sublaboratory s = new Sublaboratory(sl_id, cr_id, sl_name, sl_seating);
				// 将“View_classroom”类对象添加到“View_classroom”类的数组列表对象中
				sllist.add(s);
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
		return sllist;
	}
}
