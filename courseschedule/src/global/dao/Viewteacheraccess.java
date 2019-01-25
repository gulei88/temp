package global.dao;

import global.model.View_major;
import global.model.View_teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Viewteacheraccess {
	public static ArrayList<View_teacher> getTeacher(String t_id) {
		// ���ɲ��ҡ�Major�����select��ѯ���
		String sql = "SELECT * FROM View_teacher";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Major����ͼ����
		if (!(t_id == null || t_id.equals(""))) {
			sql += " WHERE t_id='" + t_id + "'";
		}
		// ��ʼ����Major����������б����
		ArrayList<View_teacher> teacherlist = new ArrayList<View_teacher>();
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
				String o_id = rs.getString("o_id");
				String o_name = rs.getString("o_name");
				t_id = rs.getString("t_id");
				String t_name=rs.getString("t_name");
				String t_password=rs.getString("t_password");
				String t_power =rs.getString("t_power");
				String t_tel =rs.getString("t_tel");
				// ���ݽ�������������ɡ�Major�������
				View_teacher m = new View_teacher(d_id, d_name, o_id, o_name, t_id, t_name, t_password, t_power, t_tel);
				// ����Major���������ӵ���Major����������б������
				teacherlist.add(m);
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
		return teacherlist;
	}
	
}
