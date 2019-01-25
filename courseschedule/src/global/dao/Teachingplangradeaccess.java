package global.dao;

import global.model.Classinformation;
import global.model.Department;
import global.model.Teachingplangrade;
import global.model.Teachingtask;
import global.model.View_teachingplan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import zzt.view.teachingplangrade;

/**
 * ��ѧ�ƻ���רҵ���꼶��Ӧ��ϵ�����ݹ�����
 * 
 * @author czy
 * 
 */
public class Teachingplangradeaccess {
	/**
	 * ��������ȡ��Teachingplangrade��������
	 * 
	 * @author czy
	 * @param condition
	 *            ����ѯ����
	 * @return ����������Teachingplangrade��������б�
	 */
	public static ArrayList<Teachingplangrade> getTeachingplangrade(
			String condition) {
		// ���ɲ��ҡ�Teachingplangrade����ͼ��select��ѯ���
		String sql = "select tg_grade,max(tg_grade) from teachingplangrade";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!condition.equals(""))
			sql += " WHERE tp_id='" + condition +"'group by tg_grade" + "";
		// ��ʼ����Teachingplangrade����������б����
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
				String tp_id = null;
				String m_id = null;
				String tg_grade = rs.getString("tg_grade");
				// ���ݽ�������������ɡ�Teachingplangrade�������
				Teachingplangrade tg = new Teachingplangrade(tp_id, m_id,
						tg_grade);
				// ����Teachingplangrade���������ӵ���Teachingplangrade����������б������
				Teachingplangradelist.add(tg);
			}
			// ���ء�Teachingplangrade����������б����
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
//		System.out.println(Teachingplangradelist);
		return Teachingplangradelist;
	}
	
	
	
	public static ArrayList<Teachingplangrade> getTeachingplangrade_two(
			String condition) {
		// ���ɲ��ҡ�Teachingplangrade����ͼ��select��ѯ���
		String sql = "select tg_grade,max(tg_grade) from teachingplangrade";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!condition.equals(""))
			sql += " WHERE tp_id='" + condition +"'group by tg_grade" + "";
		// ��ʼ����Teachingplangrade����������б����
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
				String tp_id = rs.getString("tp_id");
				String m_id = rs.getString("m_id");
				String tg_grade = rs.getString("tg_grade");
				// ���ݽ�������������ɡ�Teachingplangrade�������
				Teachingplangrade tg = new Teachingplangrade(tp_id, m_id,
						tg_grade);
				// ����Teachingplangrade���������ӵ���Teachingplangrade����������б������
				Teachingplangradelist.add(tg);
			}
			// ���ء�Teachingplangrade����������б����
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
//		System.out.println(Teachingplangradelist);
		return Teachingplangradelist;
	}
	public static ArrayList<Teachingplangrade> getTeachingplangradetwo(
			String condition) {
		// ���ɲ��ҡ�Teachingplangrade����ͼ��select��ѯ���
		String sql = "select * from teachingplangrade";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!condition.equals(""))
			sql += " WHERE m_id=" + condition;
		// ��ʼ����Teachingplangrade����������б����
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
				String tp_id = rs.getString("tp_id");
				String m_id = rs.getString("m_id");
				String tg_grade = rs.getString("tg_grade");
				// ���ݽ�������������ɡ�Teachingplangrade�������
				Teachingplangrade tg = new Teachingplangrade(tp_id, m_id,
						tg_grade);
				// ����Teachingplangrade���������ӵ���Teachingplangrade����������б������
				Teachingplangradelist.add(tg);
			}
			// ���ء�Teachingplangrade����������б����
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
//		System.out.println(Teachingplangradelist);
		return Teachingplangradelist;
	}
	
	
	
	
	
	
	
	
	
	public static int insert(Connection con, Teachingplangrade tap) throws SQLException{
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ִ��sql��䣬���ؼ�¼��
		sql = "INSERT INTO teachingplangrade (tp_id,m_id,tg_grade) VALUES ('"+ tap.getTp_id()+ "', '"+ tap.getM_id()+ "', '"+ tap.getTg_grade()
				+ "')";
		// ��������ִ��SQL��䣬���ؽ�ѧ����id
		return st.executeUpdate(sql);
	}
	
	public static int Delete(Connection con, String tg_grade)
			throws SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		//���d_id�����ű�ţ�Ϊ�յĻ����򷵻�0
		if (tg_grade.equals(""))
			return 0;
		////Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
		String sql = "DELETE FROM teachingplangrade WHERE tg_grade in"+(tg_grade)+"";
		//�������ݿ�������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		return st.executeUpdate(sql);
	}
	
	
	
	
//	public static int insert(Connection con, Classinformation cf)
//			throws ClassNotFoundException, SQLException {
//		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
//		if (con == null || con.isClosed())
//			return  -1;
//		String sql;
//		// �������ݿ���������
//		Statement st = con.createStatement();
//		// ����sql������ڲ��벿����Ϣ
//	    sql = "INSERT INTO classinformation (cla_id, m_id,cla_name,cla_grade,l_id,cla_number) VALUES ('"+cf.getCla_id()+"', '"+cf.getM_id()+"','"+cf.getCla_name()+"','"+cf.getCla_grade()+"','"+cf.getL_id()+"','"+cf.getCla_number()+"')";
//		// ��������ִ��SQL��䣬���ز����¼��
//		return st.executeUpdate(sql);
//	}
//	
	
	
	
	
	public static ArrayList<View_teachingplan> getTeachingplansss(String condition) {
		// ���ɲ��ҡ�Department�����select��ѯ���
		String sql = "SELECT * FROM view_teachingplan";
		// �������Ĳ��ű�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݲ��ű�Ų��ҡ�Department����ͼ����
		if (!condition.equals(""))
			sql += " WHERE m_id=" + condition;
		// ��ʼ����Department����������б����
		ArrayList<View_teachingplan> teachingplanlist = new ArrayList<View_teachingplan>();
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
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				String o_id = rs.getString("o_id");
				String o_name = rs.getString("o_name");
				String m_id = rs.getString("m_id");   
				String m_name = rs.getString("m_name");
				String tp_id = rs.getString("tp_id");
				String tp_name = rs.getString("tp_name");
				String tp_mark = rs.getString("tp_mark");
				// ���ݽ�������������ɡ�Department�������
				View_teachingplan vt = new View_teachingplan(d_id, d_name, o_id, o_name, m_id, m_name, tp_id, tp_name, tp_mark);
				// ����Department���������ӵ���Department����������б������
				teachingplanlist.add(vt);
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
		return teachingplanlist;
}
}
