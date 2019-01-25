package global.dao;

import global.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 * ϵͳʱ����
 * 
 * @author czy
 * 
 */
public class SysDatetime {
	/**
	 * ��ȡ���ݿ�ϵͳʱ��
	 * 
	 * @return ϵͳʱ��
	 */
	public static Date getSysdatetime() {
		// ���ɲ������ݿ������ϵͳʱ���select��ѯ���
		String sql = "select sysdate() as now ";
		// ȡ�����ݿ������
		Connection con = null;
		ResultSet rs = null;
		Date date = null;
		try {
			con = Databaseconnection.getconnection();

			// ������ݿ������Ϊ�գ��򷵻ؿ�
			if (con == null)
				return null;
			// �������ݿ���������
			Statement st = con.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			rs = st.executeQuery(sql);
			// ����ϵͳʱ��
			rs.next();
			date = rs.getDate("now");
			// ���ء�Department����������б����
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
