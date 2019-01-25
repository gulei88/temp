package ggc.dao;

import global.dao.Databaseconnection;
import global.model.Course;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Courseaccess {
	public static ArrayList<Course> getcourse(String cou_id) {
		// ���ɲ��ҡ�Office�����select��ѯ���
		String sql = "SELECT * FROM course";
		// �������Ĳ��ű�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݲ��ű�Ų��ҡ�Office����ͼ����
		if (!(cou_id == null || cou_id.equals(""))) {
			sql += " WHERE cou_id='" + cou_id + "'";
		}
		// ��ʼ����Office����������б����
		ArrayList<Course> courselist = new ArrayList<Course>();
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
				cou_id=rs.getString("cou_id");
				String tp_id = rs.getString("tp_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String d_id = rs.getString("d_id");
				// ���ݽ�������������ɡ�Office�������
				Course cou = new Course(cou_id, tp_id, cou_category, cou_name, cou_credit, cou_theoryhour, cou_experimentalhours, cou_practicehour, cou_semester, cou_type, d_id);
				// ����Office���������ӵ���Office����������б������
				courselist.add(cou);
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
		return courselist;
	
	}
}
