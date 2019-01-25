package global.dao;

import global.model.Office;
import global.model.View_office;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Viewofficeaccess {
	public static ArrayList<View_office> getoffice(String d_id) {
		// ���ɲ��ҡ�Office�����select��ѯ���
		String sql = "SELECT * FROM view_office";
		// �������Ĳ��ű�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݲ��ű�Ų��ҡ�Office����ͼ����
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// ��ʼ����Office����������б����
		ArrayList<View_office> officelist = new ArrayList<View_office>();
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
				 d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				String o_id = rs.getString("o_id");
				String o_name = rs.getString("o_name");
				// ���ݽ�������������ɡ�Office�������
				View_office of = new View_office(d_id, d_name, o_id, o_name);
				// ����Office���������ӵ���Office����������б������
				officelist.add(of);
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
//		System.out.println(officelist);
		// ���ء�View_teacher����������б����
		return officelist;
	}
}
