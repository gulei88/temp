package global.dao;

import global.model.Department;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import zzt.model.Classroomtype;

/**
 * 部门类数据访问类
 * 
 * @author czy
 * 
 */
public class Classrommtypeaccess {
	private static ResultSet rs;
	private static String ct_id;
	/**
	 * @author czy
	 * @param d_id
	 *            :部门编号
	 * @return “Department”类的数组列表
	 */
	public static ArrayList<Classroomtype> getClassroomtypes(int ct_id) {
		// 生成查找“Department”表的select查询语句
		String sql = "SELECT * FROM classroomtype";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Department”视图数据
		if (!(ct_id==0)) {
			sql += " WHERE ct_id=" + ct_id + "";
		}
		// 初始化“Department”类的数组列表对象
		ArrayList<Classroomtype> Classroomtypelist = new ArrayList<Classroomtype>();
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
				ct_id = rs.getInt("ct_id");
				String ct_name = rs.getString("ct_name");
				// 根据结果集的数据生成“Department”类对象
				Classroomtype dm = new Classroomtype(ct_id, ct_name);
				// 将“Department”类对象添加到“Department”类的数组列表对象中
				Classroomtypelist.add(dm);
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
		return Classroomtypelist;
	}
	//定义一个静态布尔类型的查询方法
	public static boolean findid(Connection conn,int ct_id) throws SQLException{
			//要执行的SQL语句是根据条件查找d_id（部门编号）
			String sql = "SELECT * FROM classroomtype where ct_id="+ct_id+"";
			// 生成数据库声明对象
			Statement st= conn.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			ResultSet rs = st.executeQuery(sql);
			//返回遍历rs的结果集所有记录
			return rs.next();
	}
	
	public static boolean find(Connection conn) throws SQLException{
		//要执行的SQL语句是根据条件查找d_id（部门编号）
		String sql = "select count(*) as ct_id from classroomtype";
		// 生成数据库声明对象s
		ArrayList<Classroomtype> count = new ArrayList<Classroomtype>();
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		//返回遍历rs的结果集所有记录
		return rs.next();
	}
	
	public static ResultSet count(Connection conn){
		//要执行的SQL语句是根据条件查找d_id（部门编号）
		String sql = "SELECT * FROM classsroomtyoe where ct_id";
		// 生成数据库声明对象
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs=null;
		try {
			Statement st= conn.createStatement();
			rs = st.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(rs);
		//返回遍历rs的结果集所有记录
		return rs;
}
	
	
	
	public static ResultSet findmax(Connection conn){
		//要执行的SQL语句是要查询部门编号的最大值
		String sql = "select max(ct_id) as ct_id from classroomtype";
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
		
	//定义一个静态布尔类型的查询方法
	public static boolean findname(Connection conn,String ct_name) throws SQLException{
			//要执行的SQL语句是根据条件查找d_name（部门名称）
			String sql = "SELECT * FROM classroomtype where ct_name='"+ct_name+"'";
			//生成数据库声明对象
			Statement st= conn.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			ResultSet rs = st.executeQuery(sql);
			//返回遍历rs的结果集所有记录
			return rs.next();
}
	//定义一个静态整形删除的方法
	public static int Delete(Connection con, String zzt)
			throws SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		//如果d_id（部门编号）为空的话，则返回0
		if (zzt.equals(""))
			return 0;
		////要执行的SQL语句是根据条件删除d_id(部门编号)
		String sql = "DELETE FROM classroomtype WHERE ct_id in"+(zzt)+"";
		//生成数据库声明对
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		return st.executeUpdate(sql);
	}
	
	//定义一个静态整形的修改方法
	public static int update(Connection con,int ct_id, Classroomtype ct)
			throws SQLException {
		int r=-1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return  -1;
		String sql;
		// 生成sql语句用于插入部门信息
	    sql = "update Classroomtype set ct_id='"+ct.getCt_id()+"',ct_name='"+ct.getCt_name()+"' where ct_id='"+ ct_id+"'";
	 // 生成数据库声明对象
	 	Statement st = con.createStatement();
	 // 声明对象执行SQL语句，返回插入记录数
		r = st.executeUpdate(sql);
		return r;
	}
	
	//定义一个静态整形插入的方法
	public static int insert(Connection con, Classroomtype ct)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return  -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成sql语句用于插入部门信息
	    sql = "INSERT INTO classroomtype (ct_id, ct_name) VALUES ('"+ct.getCt_id()+"', '"+ct.getCt_name()+"')";
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
