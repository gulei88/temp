package global.dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import global.model.Classinformation;
import global.model.Department;
import global.model.View_classinformation;

/**
 * 班级数据管理类
 * 
 * @author Administrator
 * 
 */
public class Classinformationtwoaccess {
	private static String d_id;
	private static String o_id;
	private static String m_id;
	private static int l_id;

	/**
	 * 根据条件取得View_classinformation视图中数据
	 * 
	 * @author czy
	 * @param condition
	 *            ：查询条件
	 * @return 满足条件的View_classinformation类的数组列表
	 * 
	 */
	public static ArrayList<View_classinformation> getView_classinformation(
			int condition) {
		// 生成查找“View_classinformation”视图的select查询语句
		String sql = "SELECT * FROM View_classinformation";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!(condition == 0))
			sql += " WHERE l_id =" + condition;
		// 初始化“View_classinformation”类的数组列表对象
		ArrayList<View_classinformation> View_classinformationlist = new ArrayList<View_classinformation>();
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
//			ResultSet tg_grade = Classinformationtwoaccess.findgrade(cla_grade);
			while (rs.next()) {
				// 取出结果集对应字段数据
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				String o_id = rs.getString("o_id");
				String o_name = rs.getString("o_name");
				String m_id = rs.getString("m_id");
				String m_name = rs.getString("m_name");
				String cla_id = rs.getString("cla_id");
				String cla_name = rs.getString("cla_name");
//				ResultSet tg_grade = Classinformationtwoaccess.findgrade(cla_grade);
				String cla_grade = rs.getString("cla_grade");
				int l_id = rs.getInt("l_id");
				String l_name = rs.getString("l_name");
				int cla_number = rs.getInt("cla_number");
				// 根据结果集的数据生成“View_classinformation”类对象
				View_classinformation vc = new View_classinformation(d_id,
						d_name, o_id, o_name, m_id, m_name, cla_id, cla_name,
						cla_grade, l_id, l_name, cla_number);
				// 将“View_classinformation”类对象添加到“View_classinformation”类的数组列表对象中
				View_classinformationlist.add(vc);
			}
			// 返回“View_classinformation”类的数组列表对象
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return View_classinformationlist;
	}
	


	
	
	
	
	public static ArrayList<View_classinformation> getView_classinformationgrade(
			String condition) {
		// 生成查找“View_classinformation”视图的select查询语句
		String sql = "select cla_grade,max(cla_grade) from view_classinformation ";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!(condition.equals("")))
			sql += " WHERE m_id='" + condition +"'group by cla_grade" + "";
//		 select cla_grade,max(cla_grade) from view_classinformation group by cla_grade
		// 初始化“View_classinformation”类的数组列表对象
		ArrayList<View_classinformation> View_classinformationlist = new ArrayList<View_classinformation>();
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
				String d_id = null;
				String d_name= null;
				String o_id= null; 
				String o_name= null;
				String m_id= null;
				String m_name= null;
				String cla_id= null;
				String cla_name= null;
				String cla_grade = rs.getString("cla_grade");
				int l_id=0;
				String l_name= null;
				int cla_number= 0;;
				// 根据结果集的数据生成“View_classinformation”类对象
				View_classinformation vc = new View_classinformation(d_id,
						d_name, o_id, o_name, m_id, m_name, cla_id, cla_name,
						cla_grade, l_id, l_name, cla_number);
				// 将“View_classinformation”类对象添加到“View_classinformation”类的数组列表对象中
				View_classinformationlist.add(vc);
//				System.out.println(vc);
			}
			// 返回“View_classinformation”类的数组列表对象
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
//		System.out.println(View_classinformationlist);
		return View_classinformationlist;
	}
	
	
	
public static boolean findid(Connection conn,String cla_id) throws SQLException{
		//要执行的SQL语句是根据条件查找d_id（部门编号）
		String sql = "SELECT * FROM classinformation where cla_id='"+cla_id+"'";
		// 生成数据库声明对象
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		//返回遍历rs的结果集所有记录
		return rs.next();
}

public static boolean find(Connection conn) throws SQLException{
	//要执行的SQL语句是根据条件查找d_id（部门编号）
	String sql = "select count(*) as cla_id from classinformation";
	// 生成数据库声明对象s
	ArrayList<Classinformation> count = new ArrayList<Classinformation>();
	Statement st= conn.createStatement();
	// 声明对象执行SQL语句，返回满足条件的结果集
	// 声明对象执行SQL语句，返回满足条件的结果集
	ResultSet rs = st.executeQuery(sql);
	//返回遍历rs的结果集所有记录
	return rs.next();
}

public static ResultSet count(Connection conn){
	//要执行的SQL语句是根据条件查找d_id（部门编号）
	String sql = "SELECT * FROM classinformation where cla_id";
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
//	System.out.println(rs);
	//返回遍历rs的结果集所有记录
	return rs;
}


public static ResultSet findgrade(Connection conn){
	//要执行的SQL语句是要查询部门编号的最大值
	String sql = "select cla_grade,max(cla_grade) from view_classinformation group by cla_grade";
	ResultSet rs=null;
	try {
		Statement st = conn.createStatement();
		rs = st.executeQuery(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return rs;
}

public static ResultSet findmax(Connection conn){
	//要执行的SQL语句是要查询部门编号的最大值
	String sql = "select max(cla_id) as cla_id from classinformation";
//	if ((d_id == null || d_id.equals(""))) {
//		sql += " WHERE d_id='" + "01" + "'";
//	}
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



public static boolean findmaxtwo(Connection conn) throws SQLException{
	//要执行的SQL语句是要查询部门编号的最大值
	String sql = "select max(cla_id) as cla_id from classinformation";
	Statement st= conn.createStatement();
	// 声明对象执行SQL语句，返回满足条件的结果集
	ResultSet rs = st.executeQuery(sql);
	return rs.next();
}


//定义一个静态布尔类型的查询方法
public static boolean findname(Connection conn,String cla_name) throws SQLException{
		//要执行的SQL语句是根据条件查找d_name（部门名称）
		String sql = "SELECT * FROM classinformation where cla_name='"+cla_name+"'";
		//生成数据库声明对象
		Statement st= conn.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		ResultSet rs = st.executeQuery(sql);
		//返回遍历rs的结果集所有记录
		return rs.next();
}
//定义一个静态整形删除的方法
public static int Delete(Connection con, String cla_id)
		throws SQLException {
	// 如果数据库的连接为空或已关闭，则返回空
	if (con == null || con.isClosed())
		return -1;
	//如果d_id（部门编号）为空的话，则返回0
	if (cla_id.equals(""))
		return 0;
	////要执行的SQL语句是根据条件删除d_id(部门编号)
	String sql = "DELETE FROM classinformation WHERE cla_id in"+(cla_id)+"";
	//生成数据库声明对
	Statement st = con.createStatement();
	// 声明对象执行SQL语句，返回满足条件的结果集
	return st.executeUpdate(sql);
}

//定义一个静态整形的修改方法
public static int update(Connection con,String cla_id, Classinformation cf)
		throws SQLException {
	int r=-1;
	// 如果数据库的连接为空或已关闭，则返回空
	if (con == null || con.isClosed())
		return  -1;
	String sql;
	// 生成sql语句用于插入部门信息
    sql = "update classinformation set cla_id='"+cf.getCla_id()+"',m_id='"+cf.getM_id()+"',cla_name='"+cf.getCla_name()+"',cla_grade='"+cf.getCla_grade()+"',l_id='"+cf.getL_id()+"',cla_number='"+cf.getCla_number()+"'where cla_id='"+ cla_id+"'";
 // 生成数据库声明对象
 	Statement st = con.createStatement();
 // 声明对象执行SQL语句，返回插入记录数
	r = st.executeUpdate(sql);
	return r;
}

//定义一个静态整形插入的方法
public static int insert(Connection con, Classinformation cf)
		throws ClassNotFoundException, SQLException {
	// 如果数据库的连接为空或已关闭，则返回空
	if (con == null || con.isClosed())
		return  -1;
	String sql;
	// 生成数据库声明对象
	Statement st = con.createStatement();
	// 生成sql语句用于插入部门信息
    sql = "INSERT INTO department (cla_id, m_id,cla_name,cla_grade,l_id,cla_number) VALUES ('"+cf.getCla_id()+"', '"+cf.getM_id()+"','"+cf.getCla_name()+"','"+cf.getCla_id()+"','"+cf.getCla_number()+"')";
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

