package global.dao;

import global.model.Office;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 科室信息数据管理类
 * 
 * @author czy
 * 
 */
public class Officeaccess {
	/**
	 * 根据部门id取得Office表中数据
	 * 
	 * @author czy
	 * @param d_id
	 *            :部门编号
	 * @return “Office”类的数组列表
	 */
	public static ArrayList<Office> getoffice(String d_id) {
		// 生成查找“Office”表的select查询语句
		String sql = "SELECT * FROM office";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Office”视图数据
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// 初始化“Office”类的数组列表对象
		ArrayList<Office> officelist = new ArrayList<Office>();
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
				String o_id = rs.getString("o_id");
				d_id = rs.getString("d_id");
				String o_name = rs.getString("o_name");
				// 根据结果集的数据生成“Office”类对象
				Office of = new Office(o_id, d_id, o_name);
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
		// 返回“View_teacher”类的数组列表对象
		return officelist;
	
	}
	
	
	
	public static ArrayList<Office> getoffice_(String o_id) {
		// 生成查找“Office”表的select查询语句
		String sql = "SELECT * FROM office";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Office”视图数据
		if (!(o_id == null || o_id.equals(""))) {
			sql += " WHERE o_id='" + o_id + "'";
		}
		// 初始化“Office”类的数组列表对象
		ArrayList<Office> officelist = new ArrayList<Office>();
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
				o_id = rs.getString("o_id");
				String d_id = rs.getString("d_id");
				String o_name = rs.getString("o_name");
				// 根据结果集的数据生成“Office”类对象
				Office of = new Office(o_id, d_id, o_name);
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
		// 返回“View_teacher”类的数组列表对象
		return officelist;
	
	}
	public static boolean findid(Connection conn, String o_id)
			throws SQLException {
		// 要执行的SQL语句是根据条件查找o_id（科室编号）
		String sql = "SELECT * FROM office where o_id='" + o_id + "'";
		// 生成数据库声明对象
		Statement st = conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		// 返回遍历rs的结果集所有记录
		return rs.next();
	}
	// 定义一个静态布尔类型的查询方法
	public static boolean findname(Connection conn, String o_name)
			throws SQLException {
		// 要执行的SQL语句是根据条件查找o_name（科室名称）
		String sql = "SELECT * FROM office where o_name='" + o_name + "'";
		// 生成数据库声明对象
		Statement st = conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		// 返回遍历rs的结果集所有记录
		return rs.next();
	}

	// 定义一个静态整形删除的方法
	public static int Delete(Connection con, String o_id) throws SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 如果o_id（科室编号）为空的话，则返回0
		if (o_id.equals(""))
			return 0;
		// //要执行的SQL语句是根据条件删除o_id(科室编号)
		String sql = "DELETE FROM office WHERE o_id in" + (o_id) + "";
		// 生成数据库声明对
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		return st.executeUpdate(sql);
	}

	// 定义一个静态整形的修改方法
	public static int update(Connection con, String o_id, Office of)
			throws SQLException {
		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成sql语句用于插入科室信息
		sql = "UPDATE office SET o_id='"+of.getO_id()+"', d_id='"+of.getD_id()+"', o_name='"+of.getO_name()+"' WHERE o_id='"+o_id+"'";
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回插入记录数
		r = st.executeUpdate(sql);
		return r;
	}

	public static ResultSet findmax(Connection conn){
		//要执行的SQL语句是要查询部门编号的最大值
		String sql = "select max(o_id) as o_id from office";
		ResultSet rs=null;
		try {
			// 生成数据库声明对象
			Statement st= conn.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回rs的结果集所有记录
		return rs;
	}
	
	
	// 定义一个静态整形插入的方法
	public static int insert(Connection con, Office of)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成sql语句用于插入科室信息
		sql = "INSERT INTO office (o_id,d_id,o_name) VALUES ('"+of.getO_id()+"', '"+of.getD_id()+"', '"+of.getO_name()+"')";
		// 声明对象执行SQL语句，返回插入记录数
		return st.executeUpdate(sql);
	}

}
