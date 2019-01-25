package global.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import global.model.Teachingtask;
import global.model.View_teacher;
import global.model.View_teachingtask;

/**
 * 教学任务数据访问类
 * 
 * @author czy
 * 
 */
public class Teachingtaskaccess {
	/**
	 * 将教学任务信息插入数据库中
	 * 
	 * @author czy
	 * @param con
	 *            :数据库的连接
	 * @param tt
	 *            ：待插入的教学任务信息
	 * @return：如果插入成功，返回教学任务id，否则返回-1或-2。
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insert(Connection con, Teachingtask tt)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成查找下一个教学任务id的sql语句
		sql = "SELECT max(tt_id)+1 as next_id FROM courseschedule.teachingtask";
		// 执行sql语句，返回记录集
		ResultSet rs = st.executeQuery(sql);
		// 定义教学任务id，并初始化为1
		int tt_id = 1;
		// 如果结果集有数据，则取出下一个教学任务id
		if (rs.next())
			tt_id = rs.getInt("next_id");
		// 生成sql语句用于插入教学任务信息
		sql = "INSERT INTO teachingtask (tt_id, cou_id,cou_theoryhour,cou_experimentalhours,cou_practicehour, t_id, sy_id, multimedia,m_id,tt_grade,Practicescheduling,tt_state) VALUES ("
				+ tt_id
				+ ", '"
				+ tt.getCou_id()
				+ "', "
				+ tt.getCou_theoryhour()
				+ ", "
				+ tt.getCou_experimentalhours()
				+ ", "
				+ tt.getCou_practicehour()
				+ ", '"
				+ tt.getT_id()
				+ "', '"
				+ tt.getSy_id()
				+ "', '"
				+ tt.getMultimedia()
				+ "', '"
				+ tt.getM_id()
				+ "', '"
				+ tt.getTt_grade()
				+ "', '"
				+ tt.getPracticescheduling() + "', " + tt.getTt_state() + ")";

		// 声明对象执行SQL语句，返回教学任务id
		if (st.executeUpdate(sql) > 0)
			return tt_id;
		// 否则返回-2
		else
			return -2;
	}

	/**
	 * 根据条件取得View_teachingtask视图中数据
	 * 
	 * @author czy
	 * @param condition
	 *            ：查询条件
	 * @return 满足条件的View_teachingtask类的数组列表
	 */
	public static ArrayList<View_teachingtask> getView_teachingtask(
			String condition) {
		// 生成查找“View_teachingtask”视图的select查询语句
		String sql = "SELECT * FROM view_teachingtask";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!condition.equals(""))
			sql += " WHERE " + condition;
		// 初始化“View_teachingtask”类的数组列表对象
		ArrayList<View_teachingtask> View_teachingtasklist = new ArrayList<View_teachingtask>();
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
				int tt_id = rs.getInt("tt_id");
				String cou_id = rs.getString("cou_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String cla_name = rs.getString("cla_name");
				int cla_number = rs.getInt("cla_number");
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String t_tel = rs.getString("t_tel");
				int sy_id = rs.getInt("sy_id");
				String sy_name = rs.getString("sy_name");
				String multimedia = rs.getString("multimedia");
				String m_id = rs.getString("m_id");
				String tt_grade = rs.getString("tt_grade");
				String Practicescheduling = rs.getString("Practicescheduling");
				int tt_state = rs.getInt("tt_state");

				// 根据结果集的数据生成“View_teachingtask”类对象
				View_teachingtask vt = new View_teachingtask(tt_id, cou_id,
						cou_category, cou_name, cou_credit, cou_theoryhour,
						cou_experimentalhours, cou_practicehour, cou_semester,
						cou_type, cla_name, cla_number, t_id, t_name, t_tel,
						sy_id, sy_name, multimedia, m_id, tt_grade,
						Practicescheduling, tt_state);
				// 将“View_teachingtask”类对象添加到“View_teachingtask”类的数组列表对象中
				View_teachingtasklist.add(vt);
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
		// 返回“View_teachingtask”类的数组列表对象
		return View_teachingtasklist;
	}

	/**
	 * 根据条件取得teachingtask表中数据
	 * 
	 * @author czy
	 * @param condition
	 *            ：查询条件
	 * @return 满足条件的Teachingtask类的数组列表
	 * 
	 */
	public static ArrayList<Teachingtask> getTeachingtask(String condition) {
		// 生成查找“teachingtask”表的select查询语句
		String sql = "SELECT * FROM teachingtask";
		// 如果传入的条件字符串不为空，则根据条件生成sql语句
		if (!condition.equals(""))
			sql += " WHERE " + condition;
		// 初始化“teachingtask”类的数组列表对象
		ArrayList<Teachingtask> teachingtasklist = new ArrayList<Teachingtask>();
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
				int tt_id = rs.getInt("tt_id");
				String cou_id = rs.getString("cou_id");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				String t_id = rs.getString("t_id");
				int sy_id = rs.getInt("sy_id");
				String multimedia = rs.getString("multimedia");
				String m_id = rs.getString("m_id");
				String tt_grade = rs.getString("tt_grade");
				String practicescheduling = rs.getString("Practicescheduling");
				int tt_state = rs.getInt("tt_state");
				// 根据结果集的数据生成“View_teachingtask”类对象
				Teachingtask tt = new Teachingtask(tt_id, cou_id,
						cou_theoryhour, cou_experimentalhours,
						cou_practicehour, t_id, sy_id, multimedia, m_id,
						tt_grade, practicescheduling, tt_state);
				// 将“View_teachingtask”类对象添加到“View_teachingtask”类的数组列表对象中
				teachingtasklist.add(tt);
			}
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
		// 返回“View_teachingtask”类的数组列表对象
		return teachingtasklist;
	}

	/**
	 * 在教学任务表中删除教学任务
	 * 
	 * @param con
	 *            :数据库的连接
	 * @param tt_id
	 *            ：拟删除的教学任务编号
	 * @return 删除的记录数
	 * @throws SQLException
	 */
	public static int delete(Connection con, int tt_id) throws SQLException {
		// 取得数据库的连接
		int r;

		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 生成删除教学任务的sql语句
		sql = "DELETE FROM teachingtask WHERE tt_id=" + tt_id;
		// 执行sql语句，返回记录集
		r = st.executeUpdate(sql);
		return r;
	}

	/**
	 * 
	 * @param con
	 *            :数据库的连接
	 * @param vt
	 *            :修改的教学任务信息
	 * @return 修改成功的记录数
	 * @throws SQLException
	 */
	public static int update(Connection con, View_teachingtask vt)
			throws SQLException {

		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 生成插入的sql语句
		String sql = "UPDATE teachingtask SET t_id='" + vt.getT_id()
				+ "', multimedia='" + vt.getMultimedia()
				+ "', Practicescheduling='" + vt.getPracticescheduling()
				+ "' WHERE `tt_id`=" + vt.getTt_id();
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		r = st.executeUpdate(sql);

		return r;
	}

	/**
	 * 
	 * @param con
	 *            :数据库的连接
	 * @param tt_id
	 *            :要修改的教学任务编号
	 * @param tt_state
	 *            :要修改的教学任务状态
	 * @return 修改成功的记录数
	 * @throws SQLException
	 */
	public static int updatestate(Connection con, int tt_id, int tt_state)
			throws SQLException {

		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 生成插入的sql语句
		String sql = "UPDATE teachingtask SET tt_state=" + tt_state
				+ " WHERE `tt_id`=" + tt_id;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		r = st.executeUpdate(sql);

		return r;
	}

	/**
	 * 
	 * @param con
	 *            :数据库的连接
	 * @param tt_id
	 *            :要修改的教学任务编号
	 * @param cou_experimentalhours
	 *            :要修改的实验课时
	 * @return 修改成功的记录数
	 * @throws SQLException
	 */
	public static int updateexperimentalhours(Connection con, int tt_id,
			int cou_experimentalhours) throws SQLException {
		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 生成插入的sql语句
		String sql = "UPDATE teachingtask SET cou_experimentalhours="
				+ cou_experimentalhours + " WHERE `tt_id`=" + tt_id;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		r = st.executeUpdate(sql);
		return r;
	}

	/**
	 * 
	 * @param con
	 *            :数据库的连接
	 * @param tt_id
	 *            :要修改的教学任务编号
	 * @param cou_practicehour
	 *            :要修改的实践课时
	 * @return 修改成功的记录数
	 * @throws SQLException
	 */
	public static int updatepracticehour(Connection con, int tt_id,
			int cou_practicehour) throws SQLException {
		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 生成插入的sql语句
		String sql = "UPDATE teachingtask SET cou_practicehour="
				+ cou_practicehour + " WHERE `tt_id`=" + tt_id;
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		r = st.executeUpdate(sql);
		return r;
	}
}
