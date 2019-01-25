package global.dao;

import global.model.Department;
import global.model.Major;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 专业数据管理类
 * 
 * @author czy
 * 
 */
public class Majoraccess {
	private static ResultSet rs;
	private static String o_id;
	private static String m_id;
	private static String m_name;

	
	
	/**
	 * @author czy
	 * @param o_id
	 *            :科室编号
	 * @return “Major”类的数组列表
	 * @throws SQLException
	 * 
	 */
	
	
	
	public static List<Major> getMajor(String o_id) {
		// 生成查找“Major”表的select查询语句
		String sql = "SELECT * FROM major";
		// 如果传入的科室编号不为空，则SQL语句添加查找条件为根据科室编号查找“Major”视图数据
		if (!(o_id == null || o_id.equals(""))) {
			sql += " WHERE o_id='" + o_id + "'";
		}
		// 初始化“Major”类的数组列表对象
		List<Major> majorlist = new ArrayList<Major>();
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
				String m_id = rs.getString("m_id");
				o_id = rs.getString("o_id");
				String m_name = rs.getString("m_name");
				// 根据结果集的数据生成“Major”类对象
				Major m = new Major(m_id, o_id, m_name);
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
		// 返回“majorlist”类的数组列表对象
		return majorlist;
	}

	
	
	
	
	/**
	 * 根据专业id查找数据表里是否和输入的数据冲突
	 * 
	 * @author zzt
	 * @param 	m_id
	 *            :专业编号
	 * @return rs.next()
	 */
	
	
	
	public static boolean findid(Connection conn, String m_id)
			throws SQLException {
		// 要执行的SQL语句是根据条件查找m_id（专业编号）
		String sql = "SELECT * FROM major where m_id='" + m_id + "'";
		// 生成数据库声明对象
		Statement st = conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		// 返回遍历rs的结果集所有记录
		return rs.next();
	}
	
	public static ArrayList<Major> findoid(Connection conn)
			throws SQLException {
		// 要执行的SQL语句是根据条件查找m_id（专业编号）
		String sql = "SELECT o_id FROM major";
		// 生成数据库声明对象
		Statement st = conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		ArrayList<Major> majorlist = new ArrayList<Major>();
		// 返回遍历rs的结果集所有记录
		while(rs.next()){
			String o_id = rs.getString("o_id");
			Major m = new Major(m_id, o_id, m_name);
			majorlist.add(m);
		}
//		System.out.println(majorlist);
		return majorlist;
	}

	/**
	 * 根据专业名称查找数据表里是否和输入的数据冲突
	 * 
	 * @author zzt
	 * @param tp_name
	 *            :专业名称
	 * @return rs.next()
	 */
	
	
	// 定义一个静态布尔类型的查询方法
	public static boolean findname(Connection conn, String m_name)
			throws SQLException {
		// 要执行的SQL语句是根据条件查找m_name（专业名称）
		String sql = "SELECT * FROM major where m_name='" + m_name + "'";
		// 生成数据库声明对象
		Statement st = conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		// 返回遍历rs的结果集所有记录
		return rs.next();
	}
	
	

	/**
	 * 根据教学计划id删除表中更多数据
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :教学计划编号
	 * @return rs.next()
	 */
	
	
	// 定义一个静态整形删除的方法
	public static int Delete(Connection con, String m_id) throws SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 如果d_id（部门编号）为空的话，则返回0
		if (m_id.equals(""))
			return 0;
		// //要执行的SQL语句是根据条件删除d_id(部门编号)
		String sql = "DELETE FROM major WHERE m_id in" + (m_id) + "";
		// 生成数据库声明对
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		return st.executeUpdate(sql);
	}

	
	/**
	 * 根据教学计划id修改数据表里的数据
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :教学计划编号
	 * @return r
	 */
	
	
	// 定义一个静态整形的修改方法
	public static int update(Connection con, String m_id, Major mj)
			throws SQLException {
		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成sql语句用于插入部门信息
		sql = "update major set m_id='" + mj.getM_id() + "',o_id='"
				+ mj.getO_id() + "',m_name='" + mj.getM_name() + "' where m_id='" + m_id + "'";
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回插入记录数
		r = st.executeUpdate(sql);
		return r;
	}

	
	/**
	 * 在数据表中插入数据
	 * 
	 * @author zzt
	 * @param  m_id
	 *            :专业编号
	 * @return st.executeUpdate(sql);
	 */
	// 定义一个静态整形插入的方法
	public static int insert(Connection con, Major mj)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成sql语句用于插入部门信息
		sql = "INSERT INTO major (m_id,o_id,m_name) VALUES ('" + mj.getM_id()
				+ "', '" + mj.getO_id() + "','" + mj.getM_name() + "')";
		// 声明对象执行SQL语句，返回插入记录数
		return st.executeUpdate(sql);
	}

}
