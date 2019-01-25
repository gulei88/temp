package global.dao;

import global.model.Teachingplan;
import global.model.Teachingplangrade;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Teaplanaccess {
	public static ArrayList<Teachingplan> getteachingplan(String m_id) {
		// 生成查找“Major”表的select查询语句
		String sql = "SELECT * FROM teachingplan";
		// 如果传入的科室编号不为空，则SQL语句添加查找条件为根据科室编号查找“Major”视图数据
		if (!(m_id == null || m_id.equals(""))) {
			sql += " WHERE m_id='" + m_id + "'";
		}
		// 初始化“Major”类的数组列表对象
		ArrayList<Teachingplan> Teachingplanlist = new ArrayList<Teachingplan>();
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
			    String  tp_id = rs.getString("tp_id");
				String tp_name = rs.getString("tp_name");
				 m_id = rs.getString("m_id");
				String tp_mark = rs.getString("tp_mark");
				// 根据结果集的数据生成“Major”类对象
				Teachingplan m = new Teachingplan(tp_id,tp_name,m_id, tp_mark);
				// 将“Major”类对象添加到“Major”类的数组列表对象中
				Teachingplanlist.add(m);
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
		// 返回“majorlist”类的数组列表对象
		return Teachingplanlist;
	}
	
	
	
	
	
	
	
	
	
	public static ArrayList<Teachingplangrade> getTeachingplangrade(String tp_id) {
		// 生成查找“Major”表的select查询语句
		String sql = "SELECT * FROM teachingplangrade";
		// 如果传入的科室编号不为空，则SQL语句添加查找条件为根据科室编号查找“Major”视图数据
		if (!(tp_id == null || tp_id.equals(""))) {
			sql += " WHERE tp_id='" + tp_id + "'";
		}
		// 初始化“Major”类的数组列表对象
		ArrayList<Teachingplangrade> Teachingplangradelist = new ArrayList<Teachingplangrade>();
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
			    tp_id = rs.getString("tp_id");
				String m_id = rs.getString("m_id");
				String tg_grade = rs.getString("tg_grade");
				// 根据结果集的数据生成“Major”类对象
				Teachingplangrade  tg = new  Teachingplangrade(tp_id,m_id,tg_grade);
				// 将“Major”类对象添加到“Major”类的数组列表对象中
				Teachingplangradelist.add(tg);
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
		// 返回“majorlist”类的数组列表对象
		return Teachingplangradelist;
	}
}
