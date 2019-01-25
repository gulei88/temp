package global.dao;

import global.model.Course;
import global.model.Teachingplan;
import global.model.View_course;
import global.model.View_course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 课程信息数据管理类
 * 
 * @author czy
 * 
 */
public class Courseaccess {
	/**
	 * 根据条件取得View_course视图中数据
	 * 
	 * @author czy
	 * @param condition
	 *            ：查询条件
	 * @return 满足条件的View_course类的数组列表
	 */
	public static ArrayList<View_course> getView_course(String condition) {
		// 生成查找“View_course”视图的select查询语句
		String sql = "SELECT * FROM view_course";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!condition.equals(""))
			sql += " WHERE tp_id =" + condition;
		// 初始化“View_course”类的数组列表对象
		ArrayList<View_course> View_courselist = new ArrayList<View_course>();
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
				String tp_mark = rs.getString("tp_mark");
				String m_id = rs.getString("m_id");
				String cou_id = rs.getString("cou_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				// 根据结果集的数据生成“View_course”类对象
				View_course vc = new View_course(tp_id, tp_name, tp_mark,
						m_id,cou_id, cou_category, cou_name, cou_credit,
						cou_theoryhour, cou_experimentalhours,
						cou_practicehour, cou_semester, cou_type, d_id, d_name);
				// 将“View_course”类对象添加到“View_course”类的数组列表对象中
				View_courselist.add(vc);
			}
			// 返回“View_course”类的数组列表对象
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
		return View_courselist;
	}
	
	
	/**
	 * 根据教学计划id查找数据表里是否和输入的数据冲突
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :教学计划编号
	 * @return rs.next()
	 */
	
	
	public static boolean findid(Connection conn,String cou_id) throws SQLException{
		//要执行的SQL语句是根据条件查找d_name（部门名称）
		String sql = "SELECT * FROM course where cou_id='"+cou_id+"'";
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
	
	
	public static boolean findname(Connection conn,String cou_name) throws SQLException{
		//要执行的SQL语句是根据条件查找d_name（部门名称）
		String sql = "SELECT * FROM course where cou_name='"+cou_name+"'";
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
	public static int Delete(Connection con, String cou_id) throws SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 如果d_id（部门编号）为空的话，则返回0
		if (cou_id.equals(""))
			return 0;
		// //要执行的SQL语句是根据条件删除d_id(部门编号)
		String sql = "DELETE FROM course WHERE cou_id in" + (cou_id) + "";
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
	public static int update(Connection con, String cou_id, Course cs)
			throws SQLException {
		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成sql语句用于插入部门信息
		sql = "update course set cou_id='" + cs.getCou_id() + "',tp_id='"
				+ cs.getTp_id() + "',cou_category='" + cs.getCou_category() + "',cou_name='" + cs.getCou_name() + "',cou_credit='" + cs.getCou_credit() + "',cou_theoryhour='" + cs.getCou_theoryhour() + "',cou_experimentalhours='" + cs.getCou_experimentalhours() + "',cou_practicehour='" + cs.getCou_practicehour() + "',cou_semester='" + cs.getCou_semester() + "',cou_type='" + cs.getCou_type() + "'where cou_id='" + cou_id + "'";
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
	public static int insert(Connection con, Course cs)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成sql语句用于插入部门信息
		sql = "INSERT INTO course (cou_id,tp_id,cou_category,cou_name,cou_credit,cou_theoryhour,cou_experimentalhours,cou_practicehour,cou_semester,cou_type,d_id) VALUES ('" + cs.getCou_id()
				+ "', '" + cs.getTp_id() + "', '" + cs.getCou_category() + "','" + cs.getCou_name() + "','" + cs.getCou_credit() + "','" + cs.getCou_theoryhour() + "','" + cs.getCou_experimentalhours() + "','" + cs.getCou_practicehour() + "','" + cs.getCou_semester() + "','" + cs.getCou_type() + "','" + cs.getD_id() + "')";
		// 声明对象执行SQL语句，返回插入记录数
		return st.executeUpdate(sql);
	}
	
	
}
