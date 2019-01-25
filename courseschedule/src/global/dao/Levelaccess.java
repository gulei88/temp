package global.dao;

import global.model.Department;
import global.model.Level;
import global.model.Major;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * ��ι�����
 * 
 * @author zzt
 * 
 */
public class Levelaccess {
	private static ResultSet rs;

	/**
	 * @author czy
	 * @param l_id
	 *            :��α��
	 * @return ��Level����������б�
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<Level> getLevels(int l_id) {
		// ���ɲ��ҡ�Level�����select��ѯ���
		String sql = "SELECT * FROM level";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Level����ͼ����
		if (!(l_id == 0)) {
			sql += " WHERE l_id='" + l_id + "'";
		}
		// ��ʼ����Major����������б����
		ArrayList<Level> levellist = new ArrayList<Level>();
		// ȡ�����ݿ������
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Databaseconnection.getconnection();
			// ������ݿ������Ϊ�գ��򷵻ؿ�
			if (con == null)
				return null;
			// �������ݿ���������
			Statement st = con.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			rs = st.executeQuery(sql);
			// ��������������
			while (rs.next()) {
				// ȡ���������Ӧ�ֶ�����
				l_id = rs.getInt("l_id");
				String l_name = rs.getString("l_name");
				// ���ݽ�������������ɡ�Level�������
				Level m = new Level(l_id, l_name);
				// ����Level���������ӵ���Level����������б������
				levellist.add(m);
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
		// ���ء�levellist����������б����
		return levellist;
	}

	public static ArrayList<Level> getLevels(String m_id) {
		// TODO Auto-generated method stub
		return null;
	}}

