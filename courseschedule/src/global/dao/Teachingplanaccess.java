package global.dao;

import global.model.Major;
import global.model.Teacher;
import global.model.Teachingplan;
import global.model.View_teachingplan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 教学计划信息管理类
 * 
 * @author zzt
 * 
 */

public class Teachingplanaccess {
	/**
	 * 根据部门id取得view_teachingplan表中数据
	 * 
	 * @author zzt
	 * @param m_id
	 *            :专业编号
	 * @return “view_teachingplan”类的数组列表
	 */
	public static ArrayList<View_teachingplan> getTeachingplan(
			String condition) {
		// 生成查找“Teachingplangrade”视图的select查询语句
		String sql = "SELECT * FROM view_teachingplan";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!condition.equals(""))
			sql += " WHERE m_id =" + condition;
		// 初始化“Teachingplangrade”类的数组列表对象
		ArrayList<View_teachingplan> Teachingplanlist = new ArrayList<View_teachingplan>();
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
				// 根据结果集的数据生成“Teachingplangrade”类对象
				View_teachingplan vt = new View_teachingplan(d_id, d_name,o_id,
						o_name, m_id, m_name, tp_id,tp_name, tp_mark);
				// 将“Teachingplangrade”类对象添加到“Teachingplangrade”类的数组列表对象中
				Teachingplanlist.add(vt);
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
		// 返回“Teachingplanlist”类的数组列表对象
		return Teachingplanlist;
	}
	
	public static List<Teachingplan> getteachingplan(String m_id) {
		// 生成查找“Major”表的select查询语句
		String sql = "SELECT * FROM teachingplan";
		// 如果传入的科室编号不为空，则SQL语句添加查找条件为根据科室编号查找“Major”视图数据
		if (!(m_id == null || m_id.equals(""))) {
			sql += " WHERE m_id='" + m_id + "'";
		}
		// 初始化“Major”类的数组列表对象
		List<Teachingplan> teachingplanlist = new ArrayList<Teachingplan>();
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
				String tp_name = rs.getString("tp_name");
				m_id = rs.getString("m_id");
				String tp_mark = rs.getString("tp_mark");
				// 根据结果集的数据生成“Major”类对象
				Teachingplan m = new Teachingplan(tp_id, tp_name, tp_name,tp_mark);
				// 将“Major”类对象添加到“Major”类的数组列表对象中
				teachingplanlist.add(m);
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
		return teachingplanlist;
	}
	
	
	
	
	
	/**
	 * 根据教学计划id查找数据表里是否和输入的数据冲突
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :教学计划编号
	 * @return rs.next()
	 */
	
	
	public static boolean findid(Connection conn,String tp_id) throws SQLException{
		//要执行的SQL语句是根据条件查找d_name（部门名称）
		String sql = "SELECT * FROM teachingplan where tp_id='"+tp_id+"'";
		//生成数据库声明对象
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		//返回遍历rs的结果集所有记录
		return rs.next();
}
	/**
	 * 根据教学计划名字查找数据表里是否和输入的数据冲突
	 * 
	 * @author zzt
	 * @param tp_name
	 *            :教学计划名称
	 * @return rs.next()
	 */
	
	
	public static boolean findname(Connection conn,String tp_name) throws SQLException{
		//要执行的SQL语句是根据条件查找d_name（部门名称）
		String sql = "SELECT * FROM teachingplan where tp_name='"+tp_name+"'";
		//生成数据库声明对象
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		//返回遍历rs的结果集所有记录
		return rs.next();
}
	
	/**
	 * 根据教学计划id删除数据表的数据
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :教学计划编号
	 * @return return st.executeUpdate(sql);
	 */
	public static int Delete(Connection con, String tp_id) throws SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 如果d_id（部门编号）为空的话，则返回0
		if (tp_id.equals(""))
			return 0;
		// //要执行的SQL语句是根据条件删除d_id(部门编号)
		String sql = "DELETE FROM teachingplan WHERE tp_id in" + (tp_id) + "";
		// 生成数据库声明对
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		return st.executeUpdate(sql);
	}

	
	/**
	 * 根据教学计划id修改数据表的数据
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :教学计划编号
	 * @return r
	 */
	// 定义一个静态整形的修改方法
	public static int update(Connection con, String tp_id, Teachingplan tp)
			throws SQLException {
		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成sql语句用于插入部门信息
		sql = "update teachingplan set tp_id='" + tp.getTp_id() + "',tp_name='"
				+ tp.getTp_name() + "',m_id='" + tp.getM_id() + "',tp_mark='" + tp.getTp_mark() + "'where tp_id='" + tp_id + "'";
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回插入记录数
		r = st.executeUpdate(sql);
		return r;
	}

	
	/**
	 * 根据教学计划id在数据表里插入数据
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :教学计划编号
	 * @return r
	 */
	// 定义一个静态整形插入的方法
	public static int insert(Connection con, Teachingplan tp)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成sql语句用于插入部门信息
		sql = "INSERT INTO teachingplan (tp_id,tp_name,m_id,tp_mark) VALUES ('" + tp.getTp_id()
				+ "', '" + tp.getTp_name() + "', '" + tp.getM_id() + "','" + tp.getTp_mark() + "')";
		// 声明对象执行SQL语句，返回插入记录数
		return st.executeUpdate(sql);
	}
			
	
	public static int insertid(Connection con, int tt_id, String cla_id)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql = "INSERT INTO techingplangrade (tt_id, cla_id) VALUES (" + tt_id
				+ ",'" + cla_id + "')";
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回影响的记录数目。
		return st.executeUpdate(sql);
	}
	
	
	
}
