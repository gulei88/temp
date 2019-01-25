package global.dao;

import global.model.Classroom;
import global.model.Department;
import global.model.Level;
import global.model.View_classroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Classroomaccess {
	/**
	 * @author czy
	 * @param o_id
	 *            :���ұ��
	 * @return ��View_classroom����������б�
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<View_classroom> getView_classroom(String condition) {
		// ���ɲ��ҡ�View_classroom�����select��ѯ���
		String sql = "SELECT * FROM view_classroom";
		// ��������������Ϊ�գ���SQL�����Ӳ�������Ϊ�����������ҡ�View_classroom����ͼ����
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// ��ʼ����View_classroom����������б����
		ArrayList<View_classroom> View_classroomlist = new ArrayList<View_classroom>();
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
				int b_id = rs.getInt("b_id");
				String b_name = rs.getString("b_name");
				String b_alias = rs.getString("b_alias");
				String b_address = rs.getString("b_address");
				int cr_id = rs.getInt("cr_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				String ct_name = rs.getString("ct_name");
				int seating = rs.getInt("seating");
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				String sl_name = rs.getString("sl_name");
				// ���ݽ�������������ɡ�View_classroom�������
				View_classroom m = new View_classroom(d_id, d_name, cr_id,
						cr_name, sl_name, seating, ct_id, ct_name, b_id,
						b_name, b_alias, b_address);
				// ����View_classroom���������ӵ���View_classroom����������б������
				View_classroomlist.add(m);
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
		return View_classroomlist;
	}
	
	public static ArrayList<Department> getDepartment(String d_id) {
		// ���ɲ��ҡ�Department�����select��ѯ���
		String sql = "SELECT * FROM department";
		// �������Ĳ��ű�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݲ��ű�Ų��ҡ�Department����ͼ����
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// ��ʼ����Department����������б����
		ArrayList<Department> departmentlist = new ArrayList<Department>();
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
				// ���ݽ�������������ɡ�Department�������
				Department dm = new Department(d_id, d_name);
				// ����Department���������ӵ���Department����������б������
				departmentlist.add(dm);
			}
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
		return departmentlist;
	}
	
	
	
	
	public static ArrayList<Classroom> getClassroom(int cr_id) {
		// ���ɲ��ҡ�Level�����select��ѯ���
		String sql = "SELECT * FROM classroom";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Level����ͼ����
		if (!(cr_id == 0)) {
			sql += " WHERE cr_id='" + cr_id + "'";
		}
		// ��ʼ����Major����������б����
		ArrayList<Classroom> levellist = new ArrayList<Classroom>();
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
				cr_id = rs.getInt("cr_id");
				String d_id = rs.getString("d_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				int cr_seating = rs.getInt("cr_seating");
				int b_id = rs.getInt("b_id");
				// ���ݽ�������������ɡ�Level�������
				Classroom m = new Classroom(cr_id, d_id,cr_name,ct_id,cr_seating,b_id);
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
}
