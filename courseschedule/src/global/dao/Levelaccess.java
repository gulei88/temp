package global.dao;

import global.model.Department;
import global.model.Level;
import global.model.Major;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 层次管理类
 * 
 * @author zzt
 * 
 */
public class Levelaccess {
	private static ResultSet rs;

	/**
	 * @author czy
	 * @param l_id
	 *            :层次编号
	 * @return “Level”类的数组列表
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<Level> getLevels(int l_id) {
		// 生成查找“Level”表的select查询语句
		String sql = "SELECT * FROM level";
		// 如果传入的科室编号不为空，则SQL语句添加查找条件为根据科室编号查找“Level”视图数据
		if (!(l_id == 0)) {
			sql += " WHERE l_id='" + l_id + "'";
		}
		// 初始化“Major”类的数组列表对象
		ArrayList<Level> levellist = new ArrayList<Level>();
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
				l_id = rs.getInt("l_id");
				String l_name = rs.getString("l_name");
				// 根据结果集的数据生成“Level”类对象
				Level m = new Level(l_id, l_name);
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

	public static ArrayList<Level> getLevels(String m_id) {
		// TODO Auto-generated method stub
		return null;
	}}

