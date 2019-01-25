package zzt.view;

import global.dao.Databaseconnection;
import global.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ccc {
	public static ArrayList<Department> getModel(String sql,String d_id) {
		// ���ɲ��ҡ�Department�����select��ѯ���
		sql = "SELECT * FROM department";
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

}
