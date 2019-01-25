package global.dao;

import global.model.view_courselaboratory;
import global.model.view_courselaboratory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Courselaboratoryaccess {
	/**
	 * @author czy
	 * @param condition
	 *            :��ѯ����
	 * @return ��view_courselaboratory����������б�
	 */
	public static ArrayList<view_courselaboratory> getCourselaboratory(
			String condition) {
		// ���ɲ��ҡ�view_courselaboratory�����select��ѯ���
		String sql = "SELECT * FROM view_courselaboratory";
		// ��������������Ϊ�գ���SQL�����Ӳ�������Ϊ�����������ҡ�view_courselaboratory����ͼ����
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// ��ʼ����view_courselaboratory����������б����
		ArrayList<view_courselaboratory> view_courselaboratorylist = new ArrayList<view_courselaboratory>();
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
				int ul_id = rs.getInt("ul_id");
				String grade = rs.getString("grade");
				String Experimentalenvironment = rs
						.getString("Experimentalenvironment");
				String cou_id = rs.getString("cou_id");
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
				int cr_id = rs.getInt("cr_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				int cr_seating = rs.getInt("cr_seating");
				int b_id = rs.getInt("b_id");
				int sl_id = rs.getInt("sl_id");
				String sl_name = rs.getString("sl_name");
				int sl_seating = rs.getInt("sl_seating");

				// ���ݽ�������������ɡ�view_courselaboratory�������
				view_courselaboratory dm = new view_courselaboratory(ul_id,
						grade, Experimentalenvironment, cou_id, tp_id,
						cou_category, cou_name, cou_credit, cou_theoryhour,
						cou_experimentalhours, cou_practicehour, cou_semester,
						cou_type, d_id, cr_id, cr_name, ct_id, cr_seating,
						b_id, sl_id, sl_name, sl_seating);
				// ����view_courselaboratory���������ӵ���view_courselaboratory����������б������
				view_courselaboratorylist.add(dm);
			}
			// ���ء�view_courselaboratory����������б����
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
		return view_courselaboratorylist;
	}

}
