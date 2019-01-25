package global.dao;

import global.model.Sublaboratory;
import global.model.View_classroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Sublaboratoryaccess {
	/**
	 * @author czy
	 * @param condition
	 *            :��ѯ����
	 * @return ��Sublaboratory����������б�
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<Sublaboratory> getSublaboratory(String condition) {
		// ���ɲ��ҡ�Sublaboratory�����select��ѯ���
		String sql = "SELECT * FROM sublaboratory";
		// ��������������Ϊ�գ���SQL�����Ӳ�������Ϊ�����������ҡ�View_classroom����ͼ����
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// ��ʼ����View_classroom����������б����
		ArrayList<Sublaboratory> sllist = new ArrayList<Sublaboratory>();
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
				int sl_id= rs.getInt("sl_id");
				int cr_id= rs.getInt("cr_id");
				String sl_name = rs.getString("sl_name");
				int sl_seating= rs.getInt("sl_seating");
				// ���ݽ�������������ɡ�View_classroom�������
				Sublaboratory s = new Sublaboratory(sl_id, cr_id, sl_name, sl_seating);
				// ����View_classroom���������ӵ���View_classroom����������б������
				sllist.add(s);
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
		return sllist;
	}
}
