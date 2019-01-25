package global.dao;

import global.model.Department;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 部门类数据访问类
 * 
 * @author czy
 * 
 */
public class Departmentaccess {
	private static ResultSet rs;
	private static String d_id;
	
	
	/**
	 * @author czy
	 * @param d_id
	 *            :部门编号
	 * @return “Department”类的数组列表
	 */
	
	
	public static ArrayList<Department> getDepartment(String d_id) {
		// 生成查找“Department”表的select查询语句
		String sql = "SELECT * FROM department";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Department”视图数据
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// 初始化“Department”类的数组列表对象
		ArrayList<Department> departmentlist = new ArrayList<Department>();
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
				// 根据结果集的数据生成“Department”类对象
				Department dm = new Department(d_id, d_name);
				// 将“Department”类对象添加到“Department”类的数组列表对象中
				departmentlist.add(dm);
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
		return departmentlist;
	}
	
	/**
	 * 根据部门id查找数据表里是否和输入的数据冲突
	 * 
	 * @author zzt
	 * @param d_id
	 *            :部门编号
	 * @return rs.next()
	 */
	
	
	//定义一个静态布尔类型的查询方法
	public static boolean findid(Connection conn,String d_id) throws SQLException{
			//要执行的SQL语句是根据条件查找d_id（部门编号）
			String sql = "SELECT * FROM department where d_id='"+d_id+"'";
			// 生成数据库声明对象
			Statement st= conn.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			ResultSet rs = st.executeQuery(sql);
			//返回遍历rs的结果集所有记录
			return rs.next();
	}
	
	
	/**
	 * 根据部门id查找数据表里是否和输入的数据冲突
	 * 
	 * @author zzt
	 * @param d_id
	 *            :部门编号
	 * @return return rs.next()
	 */
	
	
	public static boolean find(Connection conn) throws SQLException{
		//要执行的SQL语句是根据条件查找d_id（部门编号）
		String sql = "select count(*) as d_id from department";
		// 生成数据库声明对象
		ArrayList<Department> count = new ArrayList<Department>();
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		//返回遍历rs的结果集所有记录
		return rs.next();
	}
	
	
	/**
	 * 根据部门id查找数据表
	 * 
	 * @author zzt
	 * @param d_id
	 *            :部门编号
	 * @return return rs.next()
	 */
	
	
	public static ResultSet count(Connection conn){
		//要执行的SQL语句是根据条件查找d_id（部门编号）
		String sql = "SELECT * FROM department where d_id";
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs=null;
		try {
			Statement st= conn.createStatement();
			rs = st.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * 根据部门id查找数据表中最大的id值
	 * 
	 * @author zzt
	 * @param d_id
	 *            :部门编号
	 * @return return rs.next()
	 */
	
	
	
	public static ResultSet findmax(Connection conn){
		//要执行的SQL语句是要查询部门编号的最大值
		String sql = "select max(d_id) as d_id from department";
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
	 * 根据部门id查找数据表的最大值
	 * 
	 * @author zzt
	 * @param  d_id
	 *            :部门编号
	 * @return return rs.next()
	 */
	
	public static boolean findmaxtwo(Connection conn) throws SQLException{
		//要执行的SQL语句是要查询部门编号的最大值
		String sql = "select max(d_id) as d_id from department";
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		return rs.next();
	}
	
	/**
	 * 根据部门姓名查找数据表里是否和输入的数据冲突
	 * 
	 * @author zzt
	 * @param d_id
	 *            :部门编号
	 * @return return rs.next()
	 */
	
	
	
	//定义一个静态布尔类型的查询方法
	public static boolean findname(Connection conn,String d_name) throws SQLException{
			//要执行的SQL语句是根据条件查找d_name（部门名称）
			String sql = "SELECT * FROM department where d_name='"+d_name+"'";
			//生成数据库声明对象
			Statement st= conn.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			ResultSet rs = st.executeQuery(sql);
			//返回遍历rs的结果集所有记录
			return rs.next();
	}
	
	
	
	/**
	 * 根据部门id删除数据表里的数据
	 * 
	 * @author zzt
	 * @param d_id
	 *            :部门编号
	 * @return return rs.next()
	 */
	
	
	//定义一个静态整形删除的方法
	public static int Delete(Connection con, String d_id)
			throws SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		//如果d_id（部门编号）为空的话，则返回0
		if (d_id.equals(""))
			return 0;
		////要执行的SQL语句是根据条件删除d_id(部门编号)
		String sql = "DELETE FROM department WHERE d_id in"+(d_id)+"";
		//生成数据库声明对
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		return st.executeUpdate(sql);
	}
	
	
	/**
	 * 根据部门id修改数据表里的数据
	 * 
	 * @author zzt
	 * @param d_id
	 *            :部门编号
	 * @return return rs.next()
	 */
	
	
	
	//定义一个静态整形的修改方法
	public static int update(Connection con,String d_id, Department dt)
			throws SQLException {
		int r=-1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return  -1;
		String sql;
		// 生成sql语句用于插入部门信息
	    sql = "update department set d_id='"+dt.getD_id()+"',d_name='"+dt.getD_name()+"' where d_id='"+ d_id+"'";
	    // 生成数据库声明对象
	 	Statement st = con.createStatement();
	 	// 声明对象执行SQL语句，返回插入记录数
		r = st.executeUpdate(sql);
		return r;
	}
	
	
	/**
	 * 插入数据表数据
	 * 
	 * @author zzt
	 * @param d_id
	 *            :部门编号
	 * @return return rs.next()
	 */
	//定义一个静态整形插入的方法
	public static int insert(Connection con, Department dt)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return  -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成sql语句用于插入部门信息
	    sql = "INSERT INTO department (d_id, d_name) VALUES ('"+dt.getD_id()+"', '"+dt.getD_name()+"')";
		// 声明对象执行SQL语句，返回插入记录数
		return st.executeUpdate(sql);
	}
	
	public static ResultSet findoffice(Connection con) throws SQLException{
		Statement statement;
		statement=con.createStatement();
		String sql="select * from office";
		ResultSet rs=statement.executeQuery(sql);
		return rs;
	}
	
	
}
