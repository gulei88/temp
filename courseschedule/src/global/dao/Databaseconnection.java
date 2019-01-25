package global.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * 数据库连接对象
 * 
 * @author czy
 * 
 */
public class Databaseconnection {
	/**
	 * @author czy
	 * @return：数据库的连接
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getconnection() throws ClassNotFoundException,
			SQLException {
		// 声明Connection对象
		Connection con;
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		// URL指向要访问的数据库名courseschedule
		String url = "jdbc:mysql://localhost:3306/courseschedule?useUnicode=true&characterEncoding=UTF-8";
		// String url =
		// "jdbc:mysql://192.168.112.11:3306/courseschedule?useUnicode=true&characterEncoding=UTF-8";
		// MySQL配置时的用户名
		String user = "root";
		// MySQL配置时的密码
		String password = "123456";
		// 加载驱动程序
		Class.forName(driver);
		// getConnection()方法，连接MySQL数据库！
		con = DriverManager.getConnection(url, user, password);
		if (!con.isClosed())
			return con;
		else
			return null;
	}
}
