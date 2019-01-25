package global.dao;

import global.model.Courseclass;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;

/**
 * 教学任务对应班级表数据管理类
 * 
 * @author czy
 */
public class Courseclassaccess {
	/**
	 * 插入数据到教学任务对应班级表
	 * 
	 * @param con
	 *            :数据库的连接
	 * @param tt_id
	 *            ：教学任务id
	 * @param cla_id
	 *            ：班级id
	 * @return 大于 等于0：插入记录数目 -1：数据库连接失败
	 * @throws SQLException
	 */
	public static int insert(Connection con, int tt_id, String cla_id)
			throws ClassNotFoundException, SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		String sql = "INSERT INTO courseclass (tt_id, cla_id) VALUES (" + tt_id
				+ ",'" + cla_id + "')";
		// 生成数据库声明对象
		Statement st = con.createStatement();
		// 声明对象执行SQL语句，返回影响的记录数目。
		return st.executeUpdate(sql);
	}

	/**
	 * 根据条件删除courseclass表数据
	 * 
	 * @param con
	 *            ： 数据库的连接
	 * @param condition
	 *            ：删除条件
	 * @return 删除影响的记录数
	 * @throws SQLException
	 */
	public static int Delete(Connection con, String condition)
			throws SQLException {
		// 如果数据库的连接为空或已关闭，则返回空
		if (con == null || con.isClosed())
			return -1;
		if (condition.equals(""))
			return 0;
		String sql = "DELETE FROM courseclass WHERE " + condition;
		Statement st = con.createStatement();
		return st.executeUpdate(sql);
	}

	/**
	 * 查找教学任务对应班级表数据
	 * 
	 * @author czy
	 * @param tt_id
	 *            :教学任务编号
	 * @return “Courseclass”类的数组列表
	 * 
	 */
	public static ArrayList<Courseclass> getCourseclass(int tt_id) {
		// 生成查找“Courseclass”表的select查询语句
		String sql = "SELECT * FROM courseclass";
		// 如果传入的教学任务编号不为-1，则SQL语句添加查找条件为根据教学任务编号查找“Courseclass”表数据
		if (tt_id != -1) {
			sql += " WHERE tt_id=" + tt_id;
		}
		// 初始化“Courseclass”类的数组列表对象
		ArrayList<Courseclass> Courseclasslist = new ArrayList<Courseclass>();
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
				tt_id = rs.getInt("tt_id");
				String cla_id = rs.getString("cla_id");
				// 根据结果集的数据生成“Courseclass”类对象
				Courseclass cc = new Courseclass(tt_id, cla_id);
				// 将“Courseclass”类对象添加到“Courseclass”类的数组列表对象中
				Courseclasslist.add(cc);
			}
			// 返回“Courseclass”类的数组列表对象
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return Courseclasslist;
	}
}
