package global.dao;

import global.model.Major;
import global.model.Teacher;
import global.model.View_teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import zzt.model.Zzt_Teacher_login;

/**
 * 教师信息数据管理类
 * 
 * @author czy
 * 
 */
public class Teacheraccess {
//	private static String u;
	/**
	 * 根据条件取得View_teacher视图中数据
	 * 
	 * @author czy
	 * @param condition
	 *            :查询条件
	 * @return: “View_teacher”类的数组列表
	 */
	public static ArrayList<View_teacher> getview_teacher(String condition) {
		// 生成查找“view_teacher”视图的select查询语句
		String sql = "SELECT * FROM view_teacher";
		// 如果传入的教师编号不为空，则SQL语句添加查找条件为根据教师编号查找“view_teacher”视图数据
		if (!(condition.equals(""))) {
			sql += " WHERE o_id=" + condition;
		}
		// 初始化“View_teacher”类的数组列表对象
		ArrayList<View_teacher> teacherlist = new ArrayList<View_teacher>();
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
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String t_password = rs.getString("t_password");
				String t_power = rs.getString("t_power");
				String t_tel = rs.getString("t_tel");
				// 根据结果集的数据生成“View_teacher”类对象
				View_teacher vt = new View_teacher(d_id, d_name, o_id, o_name,
						t_id, t_name, t_password, t_power, t_tel);
				// 将“View_teacher”类对象添加到“View_teacher”类的数组列表对象中
				teacherlist.add(vt);
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
//		System.out.println(teacherlist);
		return teacherlist;
	}
	
	
	public static ArrayList<View_teacher> getview_teachertwo(String condition) {
		// 生成查找“view_teacher”视图的select查询语句
		String sql = "SELECT * FROM view_teacher";
		// 如果传入的教师编号不为空，则SQL语句添加查找条件为根据教师编号查找“view_teacher”视图数据
		if (!(condition.equals(""))) {
			sql += " WHERE t_id=" + condition;
		}
		// 初始化“View_teacher”类的数组列表对象
		ArrayList<View_teacher> teacherlist = new ArrayList<View_teacher>();
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
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String t_password = rs.getString("t_password");
				String t_power = rs.getString("t_power");
				String t_tel = rs.getString("t_tel");
				// 根据结果集的数据生成“View_teacher”类对象
				View_teacher vt = new View_teacher(d_id, d_name, o_id, o_name,
						t_id, t_name, t_password, t_power, t_tel);
				// 将“View_teacher”类对象添加到“View_teacher”类的数组列表对象中
				teacherlist.add(vt);
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
//		System.out.println(teacherlist);
		return teacherlist;
		
	}
	
	/**
	 * 根据教师名字查找数据表里是否和输入的数据冲突
	 * 
	 * @author zzt
	 * @param t_name
	 *            :教师姓名
	 * @return rs.next()
	 */
	
	
	public static boolean findname(Connection conn,String t_name) throws SQLException{
		//要执行的SQL语句是根据条件查找d_name（部门名称）
		String sql = "SELECT * FROM teacher where t_name='"+t_name+"'";
		//生成数据库声明对象
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		//返回遍历rs的结果集所有记录
		return rs.next();
}
	
	
	/**
	 * 根据教师id删除数据表的数据
	 * 
	 * @author zzt
	 * @param t_id
	 *            ：教师id
	 * @return return st.executeUpdate(sql);
	 */
	
	
	public static int Delete(Connection con, String t_id) throws SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 如果d_id（部门编号）为空的话，则返回0
		if (t_id.equals(""))
			return 0;
		// //要执行的SQL语句是根据条件删除d_id(部门编号)
		String sql = "DELETE FROM teacher WHERE t_id in" + (t_id) + "";
		// 生成数据库声明对
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		return st.executeUpdate(sql);
	}
	
	
	/**
	 * 根据教师密码字段修改数据表的教师密码
	 * 
	 * @author zzt
	 * @param t_id
	 *            ：教师id
	 * @return re
	 * @throws SQLException 
	 */
	
	
	public static int changepassword(Connection con,View_teacher teacher) throws SQLException {
		Statement statement;
		if (con == null || con.isClosed())
			return -1;
		int re = -1;
		try {
			statement = con.createStatement();
			// 要执行的SQL语句
			String sql = "UPDATE teacher SET t_password='"+teacher.getT_password()+"'WHERE t_id='"+teacher.getT_id()+"'";
			// 3.ResultSet类，用来存放获取的结果集！！
			re = statement.executeUpdate(sql);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	
	
	/**
	 * 根据教师id修改数据表的数据
	 * 
	 * @author zzt
	 * @param t_id
	 *            ：教师id
	 * @return r
	 */
	
	
	// 定义一个静态整形的修改方法
	public static int update(Connection con, String t_id, Teacher tc)
			throws SQLException {
		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成sql语句用于插入部门信息
		sql = "update teacher set t_id='" + tc.getT_id() + "',t_name='"
				+ tc.getT_name() + "',t_power='" + tc.getT_power() + "',o_id='" + tc.getO_id() + "',t_tel='" + tc.getT_tel() + "' where t_id='" + t_id + "'";
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回插入记录数
		r = st.executeUpdate(sql);
		return r;
	}
	
	
	/**
	 * 根据教师id插入数据表的数据
	 * 
	 * @author zzt
	 * @param t_id
	 *            ：教师id
	 * @return return st.executeUpdate(sql);
	 */
	
	
	// 定义一个静态整形插入的方法
	public static int insert(Connection con, Teacher tc)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成sql语句用于插入部门信息
		sql = "INSERT INTO teacher (t_id,t_name,t_password,t_power,o_id,t_tel) VALUES ('" + tc.getT_id()
				+ "', '" + tc.getT_name() + "', '" + tc.getT_password() + "','" + tc.getT_power() + "','" + tc.getO_id() + "','" + tc.getT_tel() + "')";
		// 声明对象执行SQL语句，返回插入记录数
		return st.executeUpdate(sql);
	}
	
	
	/**
	 * 根据教师编号查找数据表的最大值
	 * 
	 * @author zzt
	 * @param t_id
	 *            ：教师id
	 * @return return st.executeUpdate(sql);
	 */
	
	public static ResultSet findmax(Connection conn){
		//要执行的SQL语句是要查询部门编号的最大值
		String sql = "select max(t_id) as t_id,max(t_password) as t_password from teacher";
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
	
	
	/**
	 * 根据教师id删除数据表的数据
	 * 
	 * @author zzt
	 * @param t_password
	 *            ：教师密码
	 * @return rs
	 */
	public static ResultSet maxpasswrod(Connection conn){
		//要执行的SQL语句是要查询部门编号的最大值
		String sql = "select max(t_password) as t_password from teacher";
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
}
