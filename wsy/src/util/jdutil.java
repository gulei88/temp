package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdutil {


	
	
	/*
	 * �������ݿ�����
	 * 
	 */
	public static Connection getCon() throws Exception {
	String url="jdbc:mysql://localhost:3306/courseschedule?useUnicode=true&characterEncoding=UTF-8";
	String username="root";
	String userpd="123456";
	String jdbcName="com.mysql.jdbc.Driver";
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(url, username, userpd);
		return con;
	}
	/*
	 * �ر����ݿ�����
	 */
	public void closecon (Connection con) throws Exception {
		if (con!=null) {
			con.close();			
		} 
	}
//	public static void main(String[] args) {
//		jdutil q=new jdutil();
//		try {
//			q.getCon();
//			System.out.println("���ݿ����ӳɹ�");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
}
