package czy.dao;

import global.dao.Databaseconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import czy.model.Tools;
import czy.model.Courseexperimentalenvironment;
import czy.model.View_courseexperimentalenvironment;


/**
 * courseexperimentalenvironment表管理类
 * 
 * @author czy
 * 
 */
public class Courseexperimentalenvironmentaccess {
	/**
	 * 向课程实验环境表中插入数据
	 * @author czy
	 * @param con
	 *            ：数据库的连接
	 * @param ce
	 *            ：待添加的课程实验环境
	 * @return 插入成功的记录数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insert(Connection con, Courseexperimentalenvironment ce)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();

		// 生成sql语句用于插入课程实验环境信息
		sql = "INSERT INTO courseexperimentalenvironment (cou_id, grade) VALUES ('"
				+ ce.getCou_id() + "', '" + ce.getGrade() + "')";
		// 声明对象执行SQL语句，返回插入记录数
		int r = st.executeUpdate(sql);
		return r;
	}
	/**
	 * 从课程实验环境表中删除数据
	 * @author czy
	 * @param con
	 *            ：数据库的连接
	 * @param ce_id
	 *            ：待删除的课程实验环境编号
	 * @return 删除成功的记录数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int delete(Connection con, int ce_id)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();

		// 生成sql语句用于删除课程实验环境信息
		sql = "DELETE FROM courseexperimentalenvironment WHERE ce_id="+ce_id;
		// 声明对象执行SQL语句，返回插入记录数
		int r = st.executeUpdate(sql);
		return r;
	}
	/**
	 * 从课程实验环境表中删除数据
	 * @author czy
	 * @param con
	 *            ：数据库的连接
	 * @param ce_id
	 *            ：待删除的课程实验环境编号
	 * @return 删除成功的记录数
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int deletebycouidgrade(Connection con, String cou_id,String grade)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// 生成数据库声明对象
		Statement st = con.createStatement();

		// 生成sql语句用于删除课程实验环境信息
		sql = "DELETE FROM courseexperimentalenvironment WHERE cou_id='"+cou_id+"' and grade='"+grade+"'";
		// 声明对象执行SQL语句，返回插入记录数
		int r = st.executeUpdate(sql);
		return r;
	}

	/**
	 * 在“教学任务汇总视图”中查找“课程实验环境视图”中不存在的课程，将其初始插入到课程实验环境表中
	 * 
	 * @author czy
	 * @param sy_id
	 *            :学期编号
	 * @param m_id
	 *            ：专业编号
	 * @return 布尔值，代表是否初始插入成功
	 */
	private static boolean Initinsert(int sy_id, String m_id) {
		boolean r = false;
		// 生成查找“view_teachingtaskstatistics”视图的select查询语句(查询条件为学期与专业与输入参数相等且课程在课程实验环境表中不存在)
		String sql = "SELECT * FROM view_teachingtaskstatistics where  sy_id="
				+ sy_id
				+ " and m_id='"
				+ m_id
				+ "' and cou_id not in(SELECT cou_id FROM view_courseexperimentalenvironment where sy_id="
				+ sy_id + " and m_id='" + m_id + "')";
		Connection con = null;
		ResultSet rs = null;

		// 取得数据库的连接
		try {
			con = Databaseconnection.getconnection();

			// 如果数据库的连接为空，则返回假
			if (con == null)
				return false;
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);
			// 生成新的数据库连接con1
			Connection con1 = null;
			try {
				con1 = Databaseconnection.getconnection();
				// con1自动提交设为假
				con1.setAutoCommit(false);
				// 如果结果集有数据
				while (rs.next()) {
					// 取出课程号
					String cou_id = rs.getString("cou_id");
					// 取出年级
					String grade = rs.getString("tt_grade");
					// 生成课程实验环境类对象
					Courseexperimentalenvironment ce = new Courseexperimentalenvironment(
							cou_id, grade, null);
					// 将生成的课程实验环境对象插入到数据库课程实验环境表中
					insert(con1, ce);
				}
				// 数据连接提交数据
				con1.commit();
				r = true;
			} catch (Exception e) {
				Tools.connectionroolback(con1, "课程实验环境初始化输入失败，请联系系统管理员！");
			} finally {
				// 如果数据连接未关闭，则关闭该数据连接
				if (!con1.isClosed()) {
					con1.close();
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "课程实验环境初始化输入失败，请联系系统管理员！");
		}
		return r;
	}
	/**
	 * @author czy
	 * @param sy_id
	 *            :学期编号
	 * @param m_id
	 *            ：专业编号
	 * @return view_courseexperimentalenvironment类型数组列表
	 */
	public static ArrayList<View_courseexperimentalenvironment> getview_courseexperimentalenvironment(
			int sy_id, String m_id) {
		//初始插入失败，返回空
		if (!Initinsert(sy_id, m_id))
			return null;
		// 初始化“view_courseexperimentalenvironment”类的数组列表对象
		ArrayList<View_courseexperimentalenvironment> ceslist = new ArrayList<View_courseexperimentalenvironment>();
		Connection con = null;
		ResultSet rs = null;
		try {
			// 生成查找view_courseexperimentalenvironment数据的sql语句
			String sql = "SELECT * FROM view_courseexperimentalenvironment where sy_id="
					+ sy_id + " and m_id='" + m_id + "' order by grade,cou_id";
			// 取得数据库的连接
			con = Databaseconnection.getconnection();
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);

			while (rs.next()) {
				// 取出结果集对应字段数据
				int ce_id = rs.getInt("ce_id");
				String grade = rs.getString("grade");
				String Experimentalenvironment = rs
						.getString("Experimentalenvironment");
				String cou_id = rs.getString("cou_id");
				// int sy_id= rs.getInt("sy_id");
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String tp_id = rs.getString("tp_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String d_id = rs.getString("d_id");
				// String m_id = rs.getString("m_id");
				String m_name = rs.getString("m_name");
				String cla_grade = rs.getString("cla_grade");
				int l_id = rs.getInt("l_id");
				String cla_name = rs.getString("cla_name");
				String cla_number = rs.getString("cla_number");
				String Numberofcombinedclasses=rs.getString("Numberofcombinedclasses");

				// 根据结果集的数据生成“view_courseexperimentalenvironment”类对象
				View_courseexperimentalenvironment vce = new View_courseexperimentalenvironment(
						ce_id, grade, Experimentalenvironment, cou_id, sy_id,
						t_id, t_name, tp_id, cou_category, cou_name,
						cou_credit, cou_theoryhour, cou_experimentalhours,
						cou_practicehour, cou_semester, cou_type, d_id, m_id,
						m_name, cla_grade, l_id, cla_name, cla_number,Numberofcombinedclasses);
				// 将“view_courseexperimentalenvironment”类对象添加到“View_curriculum”类的数组列表对象中
				ceslist.add(vce);
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
		// 返回“view_courseexperimentalenvironment”类的数组列表对象
		return ceslist;
	}

	/**
	 * 修改courseexperimentalenvironment表experimentalenvironment字段
	 * 
	 * @author czy
	 * @param con
	 *            :数据库的连接
	 * @param ce
	 *            :要修改的课程任务环境类对象
	 * @return 修改成功的记录数
	 * @throws SQLException
	 */
	public static int updateexperimentalenvironment(Connection con,
			Courseexperimentalenvironment ce) throws SQLException {

		int r = -1;
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		// 生成插入的sql语句
		String sql = "UPDATE courseexperimentalenvironment SET Experimentalenvironment='"
				+ ce.getExperimentalenvironment()
				+ "' WHERE ce_id="
				+ ce.getCe_id() + "";
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回满足条件的结果集
		r = st.executeUpdate(sql);
		return r;
	}
}
