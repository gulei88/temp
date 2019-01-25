package global.dao;

import global.model.Office;
import global.model.View_office;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Viewofficeaccess {
	public static ArrayList<View_office> getoffice(String d_id) {
		// 生成查找“Office”表的select查询语句
		String sql = "SELECT * FROM view_office";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Office”视图数据
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// 初始化“Office”类的数组列表对象
		ArrayList<View_office> officelist = new ArrayList<View_office>();
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
				String o_id = rs.getString("o_id");
				String o_name = rs.getString("o_name");
				// 根据结果集的数据生成“Office”类对象
				View_office of = new View_office(d_id, d_name, o_id, o_name);
				// 将“Office”类对象添加到“Office”类的数组列表对象中
				officelist.add(of);
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
//		System.out.println(officelist);
		// 返回“View_teacher”类的数组列表对象
		return officelist;
	}
}
