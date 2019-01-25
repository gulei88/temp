package global.dao;

import global.model.View_course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Viewcourseaccess {
	public static ArrayList<View_course> getCourses(String cou_id) {
		// ���ɲ��ҡ�Major�����select��ѯ���
		String sql = "SELECT * FROM View_course";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Major����ͼ����
		if (!(cou_id == null || cou_id.equals(""))) {
			sql += " WHERE cou_id='" + cou_id + "'";
		}
		// ��ʼ����Major����������б����
		ArrayList<View_course> courselist = new ArrayList<View_course>();
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
				String tp_id = rs.getString("tp_id");
				String tp_name=rs.getString("tp_name");
				String tp_mark=rs.getString("tp_mark");
				String m_id=rs.getString("m_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String d_id = rs.getString("d_id");
				String d_name=rs.getString("d_name");
				// ���ݽ�������������ɡ�Major�������
				View_course cou = new View_course(tp_id, tp_name, tp_mark, m_id, cou_id, cou_category, cou_name, cou_credit, cou_theoryhour, cou_experimentalhours, cou_practicehour, cou_semester, cou_type, d_id, d_name);
				// ����Major���������ӵ���Major����������б������
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
