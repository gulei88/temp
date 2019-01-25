package global.dao;

import global.model.Major;
import global.model.Teacher;
import global.model.Teachingplan;
import global.model.View_teachingplan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * ��ѧ�ƻ���Ϣ������
 * 
 * @author zzt
 * 
 */

public class Teachingplanaccess {
	/**
	 * ���ݲ���idȡ��view_teachingplan��������
	 * 
	 * @author zzt
	 * @param m_id
	 *            :רҵ���
	 * @return ��view_teachingplan����������б�
	 */
	public static ArrayList<View_teachingplan> getTeachingplan(
			String condition) {
		// ���ɲ��ҡ�Teachingplangrade����ͼ��select��ѯ���
		String sql = "SELECT * FROM view_teachingplan";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!condition.equals(""))
			sql += " WHERE m_id =" + condition;
		// ��ʼ����Teachingplangrade����������б����
		ArrayList<View_teachingplan> Teachingplanlist = new ArrayList<View_teachingplan>();
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
				// ���ݽ�������������ɡ�Teachingplangrade�������
				View_teachingplan vt = new View_teachingplan(d_id, d_name,o_id,
						o_name, m_id, m_name, tp_id,tp_name, tp_mark);
				// ����Teachingplangrade���������ӵ���Teachingplangrade����������б������
				Teachingplanlist.add(vt);
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
		// ���ء�Teachingplanlist����������б����
		return Teachingplanlist;
	}
	
	public static List<Teachingplan> getteachingplan(String m_id) {
		// ���ɲ��ҡ�Major�����select��ѯ���
		String sql = "SELECT * FROM teachingplan";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Major����ͼ����
		if (!(m_id == null || m_id.equals(""))) {
			sql += " WHERE m_id='" + m_id + "'";
		}
		// ��ʼ����Major����������б����
		List<Teachingplan> teachingplanlist = new ArrayList<Teachingplan>();
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
				String tp_name = rs.getString("tp_name");
				m_id = rs.getString("m_id");
				String tp_mark = rs.getString("tp_mark");
				// ���ݽ�������������ɡ�Major�������
				Teachingplan m = new Teachingplan(tp_id, tp_name, tp_name,tp_mark);
				// ����Major���������ӵ���Major����������б������
				teachingplanlist.add(m);
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
		return teachingplanlist;
	}
	
	
	
	
	
	/**
	 * ���ݽ�ѧ�ƻ�id�������ݱ����Ƿ����������ݳ�ͻ
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :��ѧ�ƻ����
	 * @return rs.next()
	 */
	
	
	public static boolean findid(Connection conn,String tp_id) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_name���������ƣ�
		String sql = "SELECT * FROM teachingplan where tp_id='"+tp_id+"'";
		//�������ݿ���������
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		//���ر���rs�Ľ�������м�¼
		return rs.next();
}
	/**
	 * ���ݽ�ѧ�ƻ����ֲ������ݱ����Ƿ����������ݳ�ͻ
	 * 
	 * @author zzt
	 * @param tp_name
	 *            :��ѧ�ƻ�����
	 * @return rs.next()
	 */
	
	
	public static boolean findname(Connection conn,String tp_name) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_name���������ƣ�
		String sql = "SELECT * FROM teachingplan where tp_name='"+tp_name+"'";
		//�������ݿ���������
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		//���ر���rs�Ľ�������м�¼
		return rs.next();
}
	
	/**
	 * ���ݽ�ѧ�ƻ�idɾ�����ݱ������
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :��ѧ�ƻ����
	 * @return return st.executeUpdate(sql);
	 */
	public static int Delete(Connection con, String tp_id) throws SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���d_id�����ű�ţ�Ϊ�յĻ����򷵻�0
		if (tp_id.equals(""))
			return 0;
		// //Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
		String sql = "DELETE FROM teachingplan WHERE tp_id in" + (tp_id) + "";
		// �������ݿ�������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		return st.executeUpdate(sql);
	}

	
	/**
	 * ���ݽ�ѧ�ƻ�id�޸����ݱ������
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :��ѧ�ƻ����
	 * @return r
	 */
	// ����һ����̬���ε��޸ķ���
	public static int update(Connection con, String tp_id, Teachingplan tp)
			throws SQLException {
		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// ����sql������ڲ��벿����Ϣ
		sql = "update teachingplan set tp_id='" + tp.getTp_id() + "',tp_name='"
				+ tp.getTp_name() + "',m_id='" + tp.getM_id() + "',tp_mark='" + tp.getTp_mark() + "'where tp_id='" + tp_id + "'";
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬���ز����¼��
		r = st.executeUpdate(sql);
		return r;
	}

	
	/**
	 * ���ݽ�ѧ�ƻ�id�����ݱ����������
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :��ѧ�ƻ����
	 * @return r
	 */
	// ����һ����̬���β���ķ���
	public static int insert(Connection con, Teachingplan tp)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ����sql������ڲ��벿����Ϣ
		sql = "INSERT INTO teachingplan (tp_id,tp_name,m_id,tp_mark) VALUES ('" + tp.getTp_id()
				+ "', '" + tp.getTp_name() + "', '" + tp.getM_id() + "','" + tp.getTp_mark() + "')";
		// ��������ִ��SQL��䣬���ز����¼��
		return st.executeUpdate(sql);
	}
			
	
	public static int insertid(Connection con, int tt_id, String cla_id)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql = "INSERT INTO techingplangrade (tt_id, cla_id) VALUES (" + tt_id
				+ ",'" + cla_id + "')";
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬����Ӱ��ļ�¼��Ŀ��
		return st.executeUpdate(sql);
	}
	
	
	
}
