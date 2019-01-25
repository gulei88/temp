package czy.dao;

import global.dao.Databaseconnection;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import czy.model.Courselaboratory;
import czy.model.View_courselaboratory;

/**
 * courselaboratory表管理类
 * 
 * @author czy
 * 
 */
public class Courselaboratoryaccess {
	/**
	 * 查找课程实验室是否存在
	 * @param con：数据库的连接
	 * @param cl：待查找的课程实验室
	 * @return：存在返回真，不存在返回假
	 */
	
	private static boolean findCourselaboratory(Connection con, Courselaboratory cl) {
		boolean r = false;
		//获取对应字段信息
		int ce_id = cl.getCe_id();
		int cr_id = cl.getCr_id();
		int sl_id = cl.getSl_id();
		//根据获取的字段生成查询语句
		String sql = "select * from courselaboratory where ce_id=" + ce_id
				+ " and cr_id=" + cr_id;
		if (sl_id >= 0) {
			sql += " and sl_id=" + sl_id;
		}
		ResultSet rs = null;
		//执行sql查找信息，如果查找到返回真，否则返回假
		try {
			// 生成数据库声明对象
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
				r = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * 向课程实验室表中插入数据
	 * 
	 * @author czy
	 * @param con
	 *            ：数据库的连接
	 * @param cl
	 *            ：待添加的课程实验室信息
	 * @return 大于等于零：插入成功的记录数,-1606：数据已存在
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insert(Connection con, Courselaboratory cl)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		int ce_id = cl.getCe_id();
		int cr_id = cl.getCr_id();
		int sl_id = cl.getSl_id();
		if(findCourselaboratory(con,cl))
			return -1606;
		// 根据实验分室编号是否有效，生成sql语句用于插入课程实验室信息
		if (sl_id < 0)
			sql = "INSERT INTO courselaboratory (ce_id,cr_id) VALUES (" + ce_id
					+ "," + cr_id + ")";
		else
			sql = "INSERT INTO courselaboratory (ce_id,cr_id,sl_id) VALUES ("
					+ ce_id + "," + cr_id + "," + sl_id + ")";
		// 声明对象执行SQL语句，返回插入记录数
		int r = st.executeUpdate(sql);
		return r;
	}
	/**
	 * 从courselaboratory表中删除记录
	 * @author czy
	 * @param con：数据库的连接
	 * @param cl_id：要删除的课程实验室编号
	 * @return：删除的记录数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int delete(Connection con, int cl_id)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		sql = "DELETE FROM courselaboratory WHERE cl_id="+cl_id;
		// 声明对象执行SQL语句，返回插入记录数
		int r = st.executeUpdate(sql);
		return r;
	}

	/**
	 * 根据课程实验环境编号查询课程与实验室对应情况
	 * 
	 * @author czy
	 * @param ce_id
	 *            :课程实验环境编号
	 * 
	 * @return view_courselaboratory类型数组列表
	 */
	public static ArrayList<View_courselaboratory> getcourselaboratorybyceid(
			int ce_id) {
		// 初始化“view_courselaboratory”类的数组列表对象
		ArrayList<View_courselaboratory> cllist = new ArrayList<View_courselaboratory>();
		// 生成查找view_courselaboratory数据的sql语句
		String sql = "SELECT * FROM view_courselaboratory where ce_id=" + ce_id;
		// 调用函数，返回满足条件的数组列表
		cllist = getview_courselaboratory(sql);
		// 返回“view_courselaboratory”类的数组列表对象
		return cllist;
	}

	/**
	 * 根据查询条件查询课程与实验室对应情况
	 * 
	 * @author czy
	 * @param condition
	 *            :查询条件
	 * @return “view_courselaboratory”类的数组列表
	 */
	public static ArrayList<View_courselaboratory> getCourselaboratorybycondition(
			String condition) {
		// 生成查找“view_courselaboratory”表的select查询语句
		String sql = "SELECT * FROM view_courselaboratory";
		// 如果传入的条件不为空，则SQL语句添加查找条件为根据条件查找“view_courselaboratory”视图数据
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// 初始化“view_courselaboratory”类的数组列表对象
		ArrayList<View_courselaboratory> cllist = new ArrayList<View_courselaboratory>();
		// 调用函数，返回满足条件的数组列表
		cllist = getview_courselaboratory(sql);
		// 返回“view_courselaboratory”类的数组列表对象
		return cllist;
	}

	/**
	 * 根据传入的sql语句到数据库中查寻满足条件的数据
	 * 
	 * @author czy
	 * @param 数据库查询SQL语句
	 * @return 满足条件的“view_courselaboratory”类的数组列表
	 */
	private static ArrayList<View_courselaboratory> getview_courselaboratory(
			String sql) {
		// 初始化“view_courselaboratory”类的数组列表对象
		ArrayList<View_courselaboratory> cllist = new ArrayList<View_courselaboratory>();
		Connection con = null;
		ResultSet rs = null;
		try {
			// 取得数据库的连接
			con = Databaseconnection.getconnection();
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);

			while (rs.next()) {
				// 取出结果集对应字段数据
				String cl_id = rs.getString("cl_id");
				int ce_id = rs.getInt("ce_id");
				String sl_id = rs.getString("sl_id");
				String sl_name = rs.getString("sl_name");
				int sumsl_seating = rs.getInt("sumsl_seating");
				String sl_seating = rs.getString("sl_seating");
				int cr_id = rs.getInt("cr_id");
				String d_id = rs.getString("d_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				int cr_seating = rs.getInt("cr_seating");
				int b_id = rs.getInt("b_id");
				// 根据结果集的数据生成“view_courselaboratory”类对象
				View_courselaboratory vce = new View_courselaboratory(cl_id,
						ce_id, sl_id, sl_name, sumsl_seating, sl_seating,
						cr_id, d_id, cr_name, ct_id, cr_seating, b_id);
				// 将“view_courselaboratory”类对象添加到“view_courselaboratory”类的数组列表对象中
				cllist.add(vce);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 返回“view_courselaboratory”类的数组列表对象
		return cllist;
	}
}
