package global.dao;

import global.model.Teachingplan;
import global.model.Teachingplangrade;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Teaplanaccess {
	public static ArrayList<Teachingplan> getteachingplan(String m_id) {
		// ���ɲ��ҡ�Major�����select��ѯ���
		String sql = "SELECT * FROM teachingplan";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Major����ͼ����
		if (!(m_id == null || m_id.equals(""))) {
			sql += " WHERE m_id='" + m_id + "'";
		}
		// ��ʼ����Major����������б����
		ArrayList<Teachingplan> Teachingplanlist = new ArrayList<Teachingplan>();
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
			    String  tp_id = rs.getString("tp_id");
				String tp_name = rs.getString("tp_name");
				 m_id = rs.getString("m_id");
				String tp_mark = rs.getString("tp_mark");
				// ���ݽ�������������ɡ�Major�������
				Teachingplan m = new Teachingplan(tp_id,tp_name,m_id, tp_mark);
				// ����Major���������ӵ���Major����������б������
				Teachingplanlist.add(m);
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
		// ���ء�majorlist����������б����
		return Teachingplanlist;
	}
	
	
	
	
	
	
	
	
	
	public static ArrayList<Teachingplangrade> getTeachingplangrade(String tp_id) {
		// ���ɲ��ҡ�Major�����select��ѯ���
		String sql = "SELECT * FROM teachingplangrade";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Major����ͼ����
		if (!(tp_id == null || tp_id.equals(""))) {
			sql += " WHERE tp_id='" + tp_id + "'";
		}
		// ��ʼ����Major����������б����
		ArrayList<Teachingplangrade> Teachingplangradelist = new ArrayList<Teachingplangrade>();
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
			    tp_id = rs.getString("tp_id");
				String m_id = rs.getString("m_id");
				String tg_grade = rs.getString("tg_grade");
				// ���ݽ�������������ɡ�Major�������
				Teachingplangrade  tg = new  Teachingplangrade(tp_id,m_id,tg_grade);
				// ����Major���������ӵ���Major����������б������
				Teachingplangradelist.add(tg);
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
		// ���ء�majorlist����������б����
		return Teachingplangradelist;
	}
}
