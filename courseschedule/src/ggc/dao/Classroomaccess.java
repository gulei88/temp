package ggc.dao;

import global.dao.Databaseconnection;
import global.model.Classroom;
import global.model.Level;
import global.model.View_classroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Classroomaccess {
	/**
	 * @author czy
	 * @param o_id
	 *            :科室编号
	 * @return “View_classroom”类的数组列表
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<View_classroom> getView_classroom(String condition) {
		// 生成查找“View_classroom”表的select查询语句
		String sql = "SELECT * FROM view_classroom";
		// 如果传入的条件不为空，则SQL语句添加查找条件为根据条件查找“View_classroom”视图数据
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// 初始化“View_classroom”类的数组列表对象
		ArrayList<View_classroom> View_classroomlist = new ArrayList<View_classroom>();
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
				int b_id = rs.getInt("b_id");
				String b_name = rs.getString("b_name");
				String b_alias = rs.getString("b_alias");
				String b_address = rs.getString("b_address");
				int cr_id = rs.getInt("cr_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				String ct_name = rs.getString("ct_name");
				int seating = rs.getInt("seating");
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				String sl_name = rs.getString("sl_name");
				// 根据结果集的数据生成“View_classroom”类对象
				View_classroom m = new View_classroom(d_id, d_name, cr_id,
						cr_name, sl_name, seating, ct_id, ct_name, b_id,
						b_name, b_alias, b_address);
				// 将“View_classroom”类对象添加到“View_classroom”类的数组列表对象中
				View_classroomlist.add(m);
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
		return View_classroomlist;
	}
	
	
	
	public static ArrayList<Classroom> getClassroom(int cr_id) {
		// 生成查找“Level”表的select查询语句
		String sql = "SELECT * FROM classroom";
		// 如果传入的科室编号不为空，则SQL语句添加查找条件为根据科室编号查找“Level”视图数据
		if (!(cr_id == 0)) {
			sql += " WHERE cr_id='" + cr_id + "'";
		}
		// 初始化“Major”类的数组列表对象
		ArrayList<Classroom> levellist = new ArrayList<Classroom>();
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
				cr_id = rs.getInt("cr_id");
				String d_id = rs.getString("d_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				int cr_seating = rs.getInt("cr_seating");
				int b_id = rs.getInt("b_id");
				// 根据结果集的数据生成“Level”类对象
				Classroom m = new Classroom(cr_id, d_id,cr_name,ct_id,cr_seating,b_id);
				// 将“Level”类对象添加到“Level”类的数组列表对象中
				levellist.add(m);
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
		// 返回“levellist”类的数组列表对象
		return levellist;
	}
	//定义一个静态布尔类型的查询方法
		public static boolean findid(Connection conn,int cr_id) throws SQLException{
				//要执行的SQL语句是根据条件查找cr_id（教室编号）
				String sql = "SELECT * FROM classroom where cr_id="+cr_id+"";
				// 生成数据库声明对象
				Statement st= conn.createStatement();
				// 声明对象执行SQL语句，返回满足条件的结果集
				ResultSet rs = st.executeQuery(sql);
				//返回遍历rs的结果集所有记录
				return rs.next();
		}
		
		public static boolean find(Connection conn) throws SQLException{
			//要执行的SQL语句是根据条件查找cr_id（教室编号）
			String sql = "select count(*) as cr_id from classroom";
			// 生成数据库声明对象s
			ArrayList<Classroom> count = new ArrayList<Classroom>();
			Statement st= conn.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			ResultSet rs = st.executeQuery(sql);
			//返回遍历rs的结果集所有记录
			return rs.next();
		}
						
		public static ResultSet findmax(Connection conn){
			//要执行的SQL语句是要查询教室编号的最大值
			String sql = "select max(cr_id) as cr_id from classroom";
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
		public static boolean findname(Connection conn,String cr_name) throws SQLException{
				//要执行的SQL语句是根据条件查找cr_name（教室名称）
				String sql = "SELECT * FROM classroom where cr_name='"+cr_name+"'";
				//生成数据库声明对象
				Statement st= conn.createStatement();
				// 声明对象执行SQL语句，返回满足条件的结果集
				ResultSet rs = st.executeQuery(sql);
				//返回遍历rs的结果集所有记录
				return rs.next();
	}
		//定义一个静态布尔类型的查询方法
				public static boolean findaddress(Connection conn,int cr_seating) throws SQLException{
						//要执行的SQL语句是根据条件查找cr_seating（教室座位）
						String sql = "SELECT * FROM classroom where cr_seating='"+cr_seating+"'";
						//生成数据库声明对象
						Statement st= conn.createStatement();
						// 声明对象执行SQL语句，返回满足条件的结果集
						ResultSet rs = st.executeQuery(sql);
						//返回遍历rs的结果集所有记录
						return rs.next();
			}
		//定义一个静态整形删除的方法
		public static int Delete(Connection con, String ggc)
				throws SQLException {
			// 如果数据库的连接为空或已关闭，则返回空
			if (con == null || con.isClosed())
				return -1;
			//如果cr_id（教室编号）为空的话，则返回0
			if (ggc.equals(""))
				return 0;
			////要执行的SQL语句是根据条件删除cr_id(教室编号)
			String sql = "DELETE FROM classroom WHERE cr_id in"+(ggc)+"";
			//生成数据库声明对
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			return st.executeUpdate(sql);
		}
		
		//定义一个静态整形的修改方法
		public static int update(Connection con,int cr_id,Classroom cr)
				throws SQLException {
			int r=-1;
			// 如果数据库的连接为空或已关闭，则返回空
			if (con == null || con.isClosed())
				return  -1;
			String sql;
			// 生成sql语句用于修改教室信息
			 sql = "UPDATE classroom SET cr_id='"+cr.getCr_id()+"', d_id='"+cr.getD_id()+"', cr_name='"+cr.getCr_name()+"', ct_id='"+cr.getCt_id()+"', cr_seating='"+cr.getCr_seating()+"', b_id='"+cr.getB_id()+"' WHERE `cr_id`='"+cr_id+"'";
			
		 // 生成数据库声明对象
		 	Statement st = con.createStatement();
		 // 声明对象执行SQL语句，返回修改记录数
			r = st.executeUpdate(sql);
			return r;
		}
		
		//定义一个静态整形插入的方法
		public static int insert(Connection con, Classroom cr)
				throws ClassNotFoundException, SQLException {
			// 如果数据库的连接为空或已关闭，则返回空
			if (con == null || con.isClosed())
				return  -1;
			String sql;
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 生成sql语句用于插入教室信息
//		    sql = "INSERT INTO building (b_id, b_name, b_alias, b_address) VALUES ('"+bu.getB_id()+"', '"+bu.getB_name()+"', '"+bu.getB_alias()+"', '"+bu.getB_address()+"')";
		    sql = "INSERT INTO classroom (cr_id, d_id, cr_name, ct_id, cr_seating, b_id) VALUES ('"+cr.getCr_id()+"', '"+cr.getD_id()+"', '"+cr.getCr_name()+"', '"+cr.getCt_id()+"', '"+cr.getCr_seating()+"', '"+cr.getB_id()+"')";
			// 声明对象执行SQL语句，返回插入记录数
			return st.executeUpdate(sql);
		}
}

