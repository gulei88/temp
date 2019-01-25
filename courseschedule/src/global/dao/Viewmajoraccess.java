package global.dao;

import global.model.Major;
import global.model.View_major;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Viewmajoraccess {
	public static ArrayList<View_major> getMajor(String o_id) {
		// ���ɲ��ҡ�Major�����select��ѯ���
		String sql = "SELECT * FROM View_major";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Major����ͼ����
		if (!(o_id == null || o_id.equals(""))) {
			sql += " WHERE o_id='" + o_id + "'";
		}
		// ��ʼ����Major����������б����
		ArrayList<View_major> majorlist = new ArrayList<View_major>();
		// ȡ�����ݿ������
		Connection con=null;
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
				String d_id=rs.getString("d_id");
				String d_name=rs.getString("d_name");
				o_id = rs.getString("o_id");
				String o_name=rs.getString("o_name");
				String m_id = rs.getString("m_id");
				String m_name = rs.getString("m_name");
				// ���ݽ�������������ɡ�Major�������
				View_major m = new View_major(d_id, d_name, o_id, o_name, m_id, m_name);
				// ����Major���������ӵ���Major����������б������
				majorlist.add(m);
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
		// ���ء�View_teacher����������б����
		return majorlist;
	}
	
}
