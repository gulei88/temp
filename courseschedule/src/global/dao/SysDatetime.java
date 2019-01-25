package global.dao;

import global.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * 系统时间类
 * 
 * @author czy
 * 
 */
public class SysDatetime {
	/**
	 * 获取数据库系统时间
	 * 
	 * @return 系统时间
	 */
	public static Date getSysdatetime() {
		// 生成查找数据库服务器系统时间的select查询语句
		String sql = "select sysdate() as now ";
		// 取得数据库的连接
		Connection con = null;
		ResultSet rs = null;
		Date date = null;
		try {
			con = Databaseconnection.getconnection();

			// 如果数据库的连接为空，则返回空
			if (con == null)
				return null;
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);
			// 返回系统时间
			rs.next();
			date = rs.getDate("now");
			// 返回“Department”类的数组列表对象
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
		return date;
	}
}
