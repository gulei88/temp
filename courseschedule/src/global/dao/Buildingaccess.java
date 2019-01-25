package global.dao;

import global.model.Buliding;
import global.model.Major;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 教学楼数据管理类
 * 
 * @author ggc
 * 
 */
public class Buildingaccess {
	private static ResultSet rs;
	private static int b_id;

	/**
	 * @author ggc
	 * @param b_id
	 *            :教学楼编号
	 * @return “building”类的数组列表
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<Buliding> getbuliding(int b_id) {
		// 生成查找“buliding”表的select查询语句
		String sql = "SELECT * FROM building";
		// 如果传入的教学楼编号不为空，则SQL语句添加查找条件为根据科室编号查找“buliding”视图数据
		if (!(b_id ==0)) {
			sql += " WHERE b_id='" + b_id + "'";
		}
		// 初始化“building”类的数组列表对象
		ArrayList<Buliding> bulidinglist = new ArrayList<Buliding>();
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
				b_id = rs.getInt("b_id");
				String b_name = rs.getString("b_name");
				String b_alias = rs.getString("b_alias");
				String b_address = rs.getString("b_address");
				// 根据结果集的数据生成“Major”类对象
				Buliding b = new Buliding(b_id, b_name, b_alias, b_address);
				// 将“Major”类对象添加到“Major”类的数组列表对象中
				bulidinglist.add(b);
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
		return bulidinglist;
	}
	//定义一个静态布尔类型的查询方法
		public static boolean findid(Connection conn,int b_id) throws SQLException{
				//要执行的SQL语句是根据条件查找d_id（部门编号）
				String sql = "SELECT * FROM building where b_id="+b_id+"";
				// 生成数据库声明对象
				Statement st= conn.createStatement();
				// 声明对象执行SQL语句，返回满足条件的结果集
				ResultSet rs = st.executeQuery(sql);
				//返回遍历rs的结果集所有记录
				return rs.next();
		}
		
		public static boolean find(Connection conn) throws SQLException{
			//要执行的SQL语句是根据条件查找b_id（教学楼编号）
			String sql = "select count(*) as b_id from building";
			// 生成数据库声明对象s
			ArrayList<Buliding> count = new ArrayList<Buliding>();
			Statement st= conn.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			ResultSet rs = st.executeQuery(sql);
			//返回遍历rs的结果集所有记录
			return rs.next();
		}
		
//		public static ResultSet count(Connection conn){
//			//要执行的SQL语句是根据条件查找d_id（部门编号）
//			String sql = "SELECT * FROM classsroomtyoe where ct_id";
//			// 生成数据库声明对象
//			// 声明对象执行SQL语句，返回满足条件的结果集
//			ResultSet rs=null;
//			try {
//				Statement st= conn.createStatement();
//				rs = st.executeQuery(sql);
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			System.out.println(rs);
//			//返回遍历rs的结果集所有记录
//			return rs;
//	}
//		
		
		
		public static ResultSet findmax(Connection conn){
			//要执行的SQL语句是要查询部门编号的最大值
			String sql = "select max(b_id) as b_id from building";
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
		public static boolean findname(Connection conn,String b_name) throws SQLException{
				//要执行的SQL语句是根据条件查找b_name（教学楼名称）
				String sql = "SELECT * FROM building where b_name='"+b_name+"'";
				//生成数据库声明对象
				Statement st= conn.createStatement();
				// 声明对象执行SQL语句，返回满足条件的结果集
				ResultSet rs = st.executeQuery(sql);
				//返回遍历rs的结果集所有记录
				return rs.next();
	}
		//定义一个静态布尔类型的查询方法
				public static boolean findaddress(Connection conn,String b_address) throws SQLException{
						//要执行的SQL语句是根据条件查找b_name（教学楼名称）
						String sql = "SELECT * FROM building where b_address='"+b_address+"'";
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
			//如果b_id（教学楼编号）为空的话，则返回0
			if (ggc.equals(""))
				return 0;
			////要执行的SQL语句是根据条件删除d_id(部门编号)
			String sql = "DELETE FROM building WHERE b_id in"+(ggc)+"";
			//生成数据库声明对
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			return st.executeUpdate(sql);
		}
		
		//定义一个静态整形的修改方法
		public static int update(Connection con,int b_id, Buliding bu)
				throws SQLException {
			int r=-1;
			// 如果数据库的连接为空或已关闭，则返回空
			if (con == null || con.isClosed())
				return  -1;
			String sql;
			// 生成sql语句用于插入教学楼信息
		    sql = "update  building set b_id='"+bu.getB_id()+"',b_name='"+bu.getB_name()+"',b_alias='"+bu.getB_alias()+"',b_address='"+bu.getB_address()+"' where b_id='"+ b_id+"'";
		 // 生成数据库声明对象
		 	Statement st = con.createStatement();
		 // 声明对象执行SQL语句，返回插入记录数
			r = st.executeUpdate(sql);
			return r;
		}
		
		//定义一个静态整形插入的方法
		public static int insert(Connection con, Buliding bu)
				throws ClassNotFoundException, SQLException {
			// 如果数据库的连接为空或已关闭，则返回空
			if (con == null || con.isClosed())
				return  -1;
			String sql;
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 生成sql语句用于插入教学楼信息
		    sql = "INSERT INTO building (b_id, b_name, b_alias, b_address) VALUES ('"+bu.getB_id()+"', '"+bu.getB_name()+"', '"+bu.getB_alias()+"', '"+bu.getB_address()+"')";
			// 声明对象执行SQL语句，返回插入记录数
			return st.executeUpdate(sql);
		}
}
