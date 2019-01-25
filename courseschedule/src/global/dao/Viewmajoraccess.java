package global.dao;

import global.model.Major;
import global.model.View_major;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Viewmajoraccess {
	public static ArrayList<View_major> getMajor(String o_id) {
		// 生成查找“Major”表的select查询语句
		String sql = "SELECT * FROM View_major";
		// 如果传入的科室编号不为空，则SQL语句添加查找条件为根据科室编号查找“Major”视图数据
		if (!(o_id == null || o_id.equals(""))) {
			sql += " WHERE o_id='" + o_id + "'";
		}
		// 初始化“Major”类的数组列表对象
		ArrayList<View_major> majorlist = new ArrayList<View_major>();
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
				String d_id=rs.getString("d_id");
				String d_name=rs.getString("d_name");
				o_id = rs.getString("o_id");
				String o_name=rs.getString("o_name");
				String m_id = rs.getString("m_id");
				String m_name = rs.getString("m_name");
				// 根据结果集的数据生成“Major”类对象
				View_major m = new View_major(d_id, d_name, o_id, o_name, m_id, m_name);
				// 将“Major”类对象添加到“Major”类的数组列表对象中
				majorlist.add(m);
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
		return majorlist;
	}
	
}
