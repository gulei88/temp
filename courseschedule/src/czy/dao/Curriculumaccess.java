package czy.dao;

import global.dao.Databaseconnection;
import global.model.Curriculum;
import global.model.View_curriculum;
import global.model.View_curriculum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Curriculumaccess {
	/**
	 * 将排课信息插入数据库中
	 * 
	 * @author czy
	 * @param con
	 *            :数据库的连接
	 * @param tt
	 *            ：待插入的排课信息
	 * @return：如果插入成功，返回插入记录数;数据库连接失败返回-1；插入时间与其他课程冲突返回-2。
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insert(Connection con, Curriculum cc, String condition)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成用于对表curriculum加写锁的sql语句
		String locksqlString = "lock tables curriculum write";
		// 生成用于对表解写锁的sql语句
		String unlocksqlString = "unlock tables";
		// 生成查询数据库是否存在冲突的SQL语句
		sql = "SELECT * FROM View_curriculum WHERE " + condition;
		// 对表curriculum加写锁
		st.executeQuery(locksqlString);
		locksqlString = "lock tables View_curriculum write";
		st.executeQuery(locksqlString);
		// 查询数据库是否存在冲突
		ResultSet rs = st.executeQuery(sql);
		// 如果存在冲突，则关闭记录集，并返回-2
		if (rs.next()) {
			rs.close();
			// 对表解写锁
			st.executeQuery(unlocksqlString);
			return -2;
		}
		rs.close();
		// 生成sql语句用于插入排课信息信息
		sql = "INSERT INTO curriculum (tt_id, lessons, week, Inweeks, cr_id,sl_name) VALUES ("
				+ cc.getTt_id()
				+ ", "
				+ cc.getLessons()
				+ ", "
				+ cc.getWeek()
				+ ", '"
				+ cc.getInweeks()
				+ "',"
				+ cc.getCr_id()
				+ ",'"
				+ cc.getSl_name() + "')";
		// 声明对象执行SQL语句，返回插入记录数
		int r = st.executeUpdate(sql);
		// 对表解写锁
		st.executeQuery(unlocksqlString);
		return r;
	}

	/**
	 * 将排课信息信息从数据库中删除
	 * 
	 * @author czy
	 * @param con
	 *            :数据库的连接
	 * @param cc_id
	 *            ：待删除的排课信息信息编号
	 * @return：如果删除成功，返回删除记录数，否则返回-1。
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int delete(Connection con, int cc_id)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回-1
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成sql语句用于删除排课信息
		sql = "DELETE FROM curriculum WHERE cc_id=" + cc_id;
		// 声明对象执行SQL语句，返回删除记录数
		return st.executeUpdate(sql);
	}

	/**
	 * 根据条件取得View_curriculum视图中数据
	 * 
	 * @author czy
	 * @param condition
	 *            ：查询条件
	 * @return 满足条件的View_curriculum类的数组列表
	 */
	public static ArrayList<View_curriculum> getCurriculums(String condition) {
		// 生成查找“View_curriculum”视图的select查询语句
		String sql = "SELECT * FROM View_curriculum";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!condition.equals(""))
			sql += " WHERE " + condition;
		// 初始化“View_curriculum”类的数组列表对象
		ArrayList<View_curriculum> View_curriculumlist = new ArrayList<View_curriculum>();
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
				int cc_id = rs.getInt("cc_id");
				int tt_id = rs.getInt("tt_id");
				String cou_id = rs.getString("cou_id");
				String cou_name = rs.getString("cou_name");
				String cla_name = rs.getString("cla_name");
				String cla_number = rs.getString("cla_number");
				int lessons = rs.getInt("lessons");
				int week = rs.getInt("week");
				String inweeks = rs.getString("Inweeks");
				int classhour = rs.getInt("classhour");
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String t_tel = rs.getString("t_tel");
				int sy_id = rs.getInt("sy_id");
				String sy_name = rs.getString("sy_name");
				int b_id = rs.getInt("b_id");
				String b_name = rs.getString("b_name");
				String b_address = rs.getString("b_address");
				int cr_id = rs.getInt("cr_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				String sl_name = rs.getString("sl_name");
				// 根据结果集的数据生成“View_curriculum”类对象
				View_curriculum vcc = new View_curriculum(cc_id, tt_id, cou_id,
						cou_name, cla_name, cla_number, lessons, week, inweeks,
						classhour, t_id, t_name, t_tel, sy_id, sy_name, b_id,
						b_name, b_address, cr_id, cr_name, ct_id, sl_name);
				// 将“View_curriculum”类对象添加到“View_curriculum”类的数组列表对象中
				View_curriculumlist.add(vcc);
			}
			// 返回“View_curriculum”类的数组列表对象
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
		return View_curriculumlist;
	}

	/**
	 * 获取某门课程排课的总课时
	 * 
	 * @param condition
	 *            ：查询条件
	 * @return 0：无该任务排课信息； 大于0：该任务排课课时数； -1：产生异常或发生数据库错误。
	 */
	public static int getClasshour(String condition) {
		// 生成根据条件查找“View_curriculum”视图中的总课时字段的select查询语句
		String sql = "SELECT sum(classhour) FROM View_curriculum where "
				+ condition;
		// 生成数据库的连接对象
		Connection con = null;
		// 生成结果集对象
		ResultSet rs = null;
		try {
			// 取得数据库的连接
			con = Databaseconnection.getconnection();
			// 如果数据库的连接为空，则返回空
			if (con == null)
				return -1;
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);
			// 如果结果集有数据,返回排课课时数，否则返回0
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
