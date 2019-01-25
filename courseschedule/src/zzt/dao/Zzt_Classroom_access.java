package zzt.dao;

import global.dao.Databaseconnection;
import global.model.Classroom;
import global.model.Office;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import zzt.model.Zzt_Teacher_login;
import zzt.view.Zzt_JIF_Classroom;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Zzt_Classroom_access {
	public static ArrayList<Classroom> getClassroom(String d_id) throws SQLException,
			ClassNotFoundException {
		// ���ɲ��ҡ�Office�����select��ѯ���
		String sql = "SELECT * FROM classroom";
		// �������Ĳ��ű�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݲ��ű�Ų��ҡ�Office����ͼ����
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// ��ʼ����Office����������б����
		ArrayList<Classroom> Classroomlist = new ArrayList<Classroom>();
		// ȡ�����ݿ������
		java.sql.Connection con = Databaseconnection.getconnection();
		// ������ݿ������Ϊ�գ��򷵻ؿ�
		if (con == null)
			return null;
		// �������ݿ���������
		Statement st = (Statement) con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		// ��������������
		while (rs.next()) {
//			private int cr_id;
//			private String d_id;
//			private String cr_name;
//			private int ct_id;
//			private int cr_seating;
//			private int b_id;
			int cr_id=rs.getInt("cr_id");	
			String cr_name = rs.getString("cr_name");
			d_id=rs.getString("d_id");
			int ct_id=rs.getInt("ct_id");
			int cr_seating = rs.getInt("cr_seating");
			int b_id=rs.getInt("b_id");
			
//			String b_name = rs.getString("b_name");
//			String b_alias = rs.getString("b_alias");
//			String b_address = rs.getString("b_address");
//			int cr_seating = rs.getInt("cr_seating");
//			String d_name = rs.getString("d_name");
			// ���ݽ�������������ɡ�Office�������
			Classroom of = new	Classroom(cr_id, d_id, cr_name, ct_id, cr_seating, b_id);
//			 ����Office���������ӵ���Office����������б������
			Classroomlist.add(of);
		}
		// ���ء�View_teacher����������б����
		return Classroomlist;
	}
}
