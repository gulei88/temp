package global.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * ���ݿ����Ӷ���
 * 
 * @author czy
 * 
 */
public class Databaseconnection {
	/**
	 * @author czy
	 * @return�����ݿ������
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getconnection() throws ClassNotFoundException,
			SQLException {
		// ����Connection����
		Connection con;
		// ����������
		String driver = "com.mysql.jdbc.Driver";
		// URLָ��Ҫ���ʵ����ݿ���courseschedule
		String url = "jdbc:mysql://localhost:3306/courseschedule?useUnicode=true&characterEncoding=UTF-8";
		// String url =
		// "jdbc:mysql://192.168.112.11:3306/courseschedule?useUnicode=true&characterEncoding=UTF-8";
		// MySQL����ʱ���û���
		String user = "root";
		// MySQL����ʱ������
		String password = "123456";
		// ������������
		Class.forName(driver);
		// getConnection()����������MySQL���ݿ⣡
		con = DriverManager.getConnection(url, user, password);
		if (!con.isClosed())
			return con;
		else
			return null;
	}
}
