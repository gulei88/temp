package global.dao;
import global.model.Schoolyear;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 学期信息数据管理类
 * 
 * @author czy
 * 
 */
public class Schoolyearaccess {
	/**
	 * 取得学期数据库数据
	 * 
	 * @author czy
	 * @param condition
	 *            :查询的条件
	 * @return “Schoolyear”类的数组列表
	 * 
	 */
	public static ArrayList<Schoolyear> getSchoolyear(String condition) {
		// 生成查找“Schoolyear”表的select查询语句
		String sql = "SELECT * FROM schoolyear";
		// 如果传递的条件不为空，则根据条件生成sql语句
		if (!(condition.equals("")))
			sql += " WHERE " + condition;
		sql += " order by sy_name";
		// 初始化“Schoolyear”类的数组列表对象
		ArrayList<Schoolyear> Schoolyearlist = new ArrayList<Schoolyear>();
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
				int sy_id = rs.getInt("sy_id");
				String sy_name = rs.getString("sy_name");
				// 根据结果集的数据生成“Schoolyear”类对象
				Schoolyear m = new Schoolyear(sy_id, sy_name);
				// 将“Schoolyear”类对象添加到“Schoolyear”类的数组列表对象中
				Schoolyearlist.add(m);
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
		return Schoolyearlist;
	}

	/**
	 * 插入学年数据到数据库中
	 * 
	 * @param sy_name
	 *            ：学年名
	 * @return：插入的记录数量
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insertSchoolyear(Connection con, String sy_name)
			throws ClassNotFoundException, SQLException {
		// 生成插入“Schoolyear”表的sql语句
		String sql = "INSERT INTO schoolyear (sy_name) VALUES ('" + sy_name
				+ "')";
		// 如果数据库的连接为空，则返回空
		if (con == null)
			return -1;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回影响的记录数
		return st.executeUpdate(sql);
	}
	
	
	public static boolean findid(Connection conn,String sy_id) throws SQLException{
		//要执行的SQL语句是根据条件查找d_id（部门编号）
		String sql = "SELECT * FROM schoolyear where sy_id='"+sy_id+"'";
		// 生成数据库声明对象
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		//返回遍历rs的结果集所有记录
		return rs.next();
}

	//public static ResultSet findmax(Connection conn) throws SQLException{
	//	//要执行的SQL语句是根据条件查找d_id（部门编号）
	//	String sql = "select max(d_id)  from department";
	//	// 生成数据库声明对象
	//	Statement st= conn.createStatement();
	//	// 声明对象执行SQL语句，返回满足条件的结果集
	//	ResultSet rs = st.executeQuery(sql);
	//	//返回遍历rs的结果集所有记录
	//	return rs;
	//}


//定义一个静态布尔类型的查询方法
public static boolean findname(Connection conn,String sy_name) throws SQLException{
		//要执行的SQL语句是根据条件查找d_name（部门名称）
		String sql = "SELECT * FROM schoolyear where sy_name='"+sy_name+"'";
		//生成数据库声明对象
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		//返回遍历rs的结果集所有记录
		return rs.next();
}
//定义一个静态整形删除的方法
public static int Delete(Connection con, String sy_id)
		throws SQLException {
	// 如果数据库的连接为空或已关闭，则返回空
	if (con == null || con.isClosed())
		return -1;
	//如果d_id（部门编号）为空的话，则返回0
	if (sy_id.equals(""))
		return 0;
	////要执行的SQL语句是根据条件删除d_id(部门编号)
	String sql = "DELETE FROM schoolyear WHERE sy_id in"+(sy_id)+"";
	//生成数据库声明对
	Statement st = con.createStatement();
	// 声明对象执行SQL语句，返回满足条件的结果集
	return st.executeUpdate(sql);
}

//定义一个静态整形的修改方法
public static int update(Connection con,String sy_id, Schoolyear sy)
		throws SQLException {
	int r=-1;
	// 如果数据库的连接为空或已关闭，则返回空
	if (con == null || con.isClosed())
		return  -1;
	String sql;
	// 生成sql语句用于插入部门信息
    sql = "update schoolyear set sy_id='"+sy.getSy_id()+"',sy_name='"+sy.getSy_name()+"' where sy_id='"+ sy_id+"'";
 // 生成数据库声明对象
 	Statement st = con.createStatement();
 // 声明对象执行SQL语句，返回插入记录数
	r = st.executeUpdate(sql);
	return r;
}

//定义一个静态整形插入的方法
public static int insert(Connection con, Schoolyear sy)
		throws ClassNotFoundException, SQLException {
	// 如果数据库的连接为空或已关闭，则返回空
	if (con == null || con.isClosed())
		return  -1;
	String sql;
	// 生成数据库声明对象
	Statement st = con.createStatement();
	// 生成sql语句用于插入部门信息
    sql = "INSERT INTO schoolyear (sy_id, sy_name) VALUES ('"+sy.getSy_id()+"', '"+sy.getSy_name()+"')";
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
