package global.dao;

import global.model.Classinformation;
import global.model.Department;
import global.model.Teachingplangrade;
import global.model.Teachingtask;
import global.model.View_teachingplan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import zzt.view.teachingplangrade;

/**
 * 教学计划与专业、年级对应关系的数据管理类
 * 
 * @author czy
 * 
 */
public class Teachingplangradeaccess {
	/**
	 * 根据条件取得Teachingplangrade表中数据
	 * 
	 * @author czy
	 * @param condition
	 *            ：查询条件
	 * @return 满足条件的Teachingplangrade类的数组列表
	 */
	public static ArrayList<Teachingplangrade> getTeachingplangrade(
			String condition) {
		// 生成查找“Teachingplangrade”视图的select查询语句
		String sql = "select tg_grade,max(tg_grade) from teachingplangrade";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!condition.equals(""))
			sql += " WHERE tp_id='" + condition +"'group by tg_grade" + "";
		// 初始化“Teachingplangrade”类的数组列表对象
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
				String tp_id = null;
				String m_id = null;
				String tg_grade = rs.getString("tg_grade");
				// 根据结果集的数据生成“Teachingplangrade”类对象
				Teachingplangrade tg = new Teachingplangrade(tp_id, m_id,
						tg_grade);
				// 将“Teachingplangrade”类对象添加到“Teachingplangrade”类的数组列表对象中
				Teachingplangradelist.add(tg);
			}
			// 返回“Teachingplangrade”类的数组列表对象
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
//		System.out.println(Teachingplangradelist);
		return Teachingplangradelist;
	}
	
	
	
	public static ArrayList<Teachingplangrade> getTeachingplangrade_two(
			String condition) {
		// 生成查找“Teachingplangrade”视图的select查询语句
		String sql = "select tg_grade,max(tg_grade) from teachingplangrade";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!condition.equals(""))
			sql += " WHERE tp_id='" + condition +"'group by tg_grade" + "";
		// 初始化“Teachingplangrade”类的数组列表对象
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
				String tp_id = rs.getString("tp_id");
				String m_id = rs.getString("m_id");
				String tg_grade = rs.getString("tg_grade");
				// 根据结果集的数据生成“Teachingplangrade”类对象
				Teachingplangrade tg = new Teachingplangrade(tp_id, m_id,
						tg_grade);
				// 将“Teachingplangrade”类对象添加到“Teachingplangrade”类的数组列表对象中
				Teachingplangradelist.add(tg);
			}
			// 返回“Teachingplangrade”类的数组列表对象
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
//		System.out.println(Teachingplangradelist);
		return Teachingplangradelist;
	}
	public static ArrayList<Teachingplangrade> getTeachingplangradetwo(
			String condition) {
		// 生成查找“Teachingplangrade”视图的select查询语句
		String sql = "select * from teachingplangrade";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!condition.equals(""))
			sql += " WHERE m_id=" + condition;
		// 初始化“Teachingplangrade”类的数组列表对象
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
				String tp_id = rs.getString("tp_id");
				String m_id = rs.getString("m_id");
				String tg_grade = rs.getString("tg_grade");
				// 根据结果集的数据生成“Teachingplangrade”类对象
				Teachingplangrade tg = new Teachingplangrade(tp_id, m_id,
						tg_grade);
				// 将“Teachingplangrade”类对象添加到“Teachingplangrade”类的数组列表对象中
				Teachingplangradelist.add(tg);
			}
			// 返回“Teachingplangrade”类的数组列表对象
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
//		System.out.println(Teachingplangradelist);
		return Teachingplangradelist;
	}
	
	
	
	
	
	
	
	
	
	public static int insert(Connection con, Teachingplangrade tap) throws SQLException{
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 执行sql语句，返回记录集
		sql = "INSERT INTO teachingplangrade (tp_id,m_id,tg_grade) VALUES ('"+ tap.getTp_id()+ "', '"+ tap.getM_id()+ "', '"+ tap.getTg_grade()
				+ "')";
		// 声明对象执行SQL语句，返回教学任务id
		return st.executeUpdate(sql);
	}
	
	public static int Delete(Connection con, String tg_grade)
			throws SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		//如果d_id（部门编号）为空的话，则返回0
		if (tg_grade.equals(""))
			return 0;
		////要执行的SQL语句是根据条件删除d_id(部门编号)
		String sql = "DELETE FROM teachingplangrade WHERE tg_grade in"+(tg_grade)+"";
		//生成数据库声明对
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		return st.executeUpdate(sql);
	}
	
	
	
	
//	public static int insert(Connection con, Classinformation cf)
//			throws ClassNotFoundException, SQLException {
//		// 如果数据库的连接为空或已关闭，则返回空
//		if (con == null || con.isClosed())
//			return  -1;
//		String sql;
//		// 生成数据库声明对象
//		Statement st = con.createStatement();
//		// 生成sql语句用于插入部门信息
//	    sql = "INSERT INTO classinformation (cla_id, m_id,cla_name,cla_grade,l_id,cla_number) VALUES ('"+cf.getCla_id()+"', '"+cf.getM_id()+"','"+cf.getCla_name()+"','"+cf.getCla_grade()+"','"+cf.getL_id()+"','"+cf.getCla_number()+"')";
//		// 声明对象执行SQL语句，返回插入记录数
//		return st.executeUpdate(sql);
//	}
//	
	
	
	
	
	public static ArrayList<View_teachingplan> getTeachingplansss(String condition) {
		// 生成查找“Department”表的select查询语句
		String sql = "SELECT * FROM view_teachingplan";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Department”视图数据
		if (!condition.equals(""))
			sql += " WHERE m_id=" + condition;
		// 初始化“Department”类的数组列表对象
		ArrayList<View_teachingplan> teachingplanlist = new ArrayList<View_teachingplan>();
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
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				String o_id = rs.getString("o_id");
				String o_name = rs.getString("o_name");
				String m_id = rs.getString("m_id");   
				String m_name = rs.getString("m_name");
				String tp_id = rs.getString("tp_id");
				String tp_name = rs.getString("tp_name");
				String tp_mark = rs.getString("tp_mark");
				// 根据结果集的数据生成“Department”类对象
				View_teachingplan vt = new View_teachingplan(d_id, d_name, o_id, o_name, m_id, m_name, tp_id, tp_name, tp_mark);
				// 将“Department”类对象添加到“Department”类的数组列表对象中
				teachingplanlist.add(vt);
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
		return teachingplanlist;
}
}
