package global.dao;

import global.model.Department;
import global.model.Major;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * רҵ���ݹ�����
 * 
 * @author czy
 * 
 */
public class Majoraccess {
	private static ResultSet rs;
	private static String o_id;
	private static String m_id;
	private static String m_name;

	
	
	/**
	 * @author czy
	 * @param o_id
	 *            :���ұ��
	 * @return ��Major����������б�
	 * @throws SQLException
	 * 
	 */
	
	
	
	public static List<Major> getMajor(String o_id) {
		// ���ɲ��ҡ�Major�����select��ѯ���
		String sql = "SELECT * FROM major";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Major����ͼ����
		if (!(o_id == null || o_id.equals(""))) {
			sql += " WHERE o_id='" + o_id + "'";
		}
		// ��ʼ����Major����������б����
		List<Major> majorlist = new ArrayList<Major>();
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
				String m_id = rs.getString("m_id");
				o_id = rs.getString("o_id");
				String m_name = rs.getString("m_name");
				// ���ݽ�������������ɡ�Major�������
				Major m = new Major(m_id, o_id, m_name);
				// ����Major���������ӵ���Major����������б������
				majorlist.add(m);
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
		return majorlist;
	}

	
	
	
	
	/**
	 * ����רҵid�������ݱ����Ƿ����������ݳ�ͻ
	 * 
	 * @author zzt
	 * @param 	m_id
	 *            :רҵ���
	 * @return rs.next()
	 */
	
	
	
	public static boolean findid(Connection conn, String m_id)
			throws SQLException {
		// Ҫִ�е�SQL����Ǹ�����������m_id��רҵ��ţ�
		String sql = "SELECT * FROM major where m_id='" + m_id + "'";
		// �������ݿ���������
		Statement st = conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		// ���ر���rs�Ľ�������м�¼
		return rs.next();
	}
	
	public static ArrayList<Major> findoid(Connection conn)
			throws SQLException {
		// Ҫִ�е�SQL����Ǹ�����������m_id��רҵ��ţ�
		String sql = "SELECT o_id FROM major";
		// �������ݿ���������
		Statement st = conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		ArrayList<Major> majorlist = new ArrayList<Major>();
		// ���ر���rs�Ľ�������м�¼
		while(rs.next()){
			String o_id = rs.getString("o_id");
			Major m = new Major(m_id, o_id, m_name);
			majorlist.add(m);
		}
//		System.out.println(majorlist);
		return majorlist;
	}

	/**
	 * ����רҵ���Ʋ������ݱ����Ƿ����������ݳ�ͻ
	 * 
	 * @author zzt
	 * @param tp_name
	 *            :רҵ����
	 * @return rs.next()
	 */
	
	
	// ����һ����̬�������͵Ĳ�ѯ����
	public static boolean findname(Connection conn, String m_name)
			throws SQLException {
		// Ҫִ�е�SQL����Ǹ�����������m_name��רҵ���ƣ�
		String sql = "SELECT * FROM major where m_name='" + m_name + "'";
		// �������ݿ���������
		Statement st = conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		// ���ر���rs�Ľ�������м�¼
		return rs.next();
	}
	
	

	/**
	 * ���ݽ�ѧ�ƻ�idɾ�����и�������
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :��ѧ�ƻ����
	 * @return rs.next()
	 */
	
	
	// ����һ����̬����ɾ���ķ���
	public static int Delete(Connection con, String m_id) throws SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���d_id�����ű�ţ�Ϊ�յĻ����򷵻�0
		if (m_id.equals(""))
			return 0;
		// //Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
		String sql = "DELETE FROM major WHERE m_id in" + (m_id) + "";
		// �������ݿ�������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		return st.executeUpdate(sql);
	}

	
	/**
	 * ���ݽ�ѧ�ƻ�id�޸����ݱ��������
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :��ѧ�ƻ����
	 * @return r
	 */
	
	
	// ����һ����̬���ε��޸ķ���
	public static int update(Connection con, String m_id, Major mj)
			throws SQLException {
		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// ����sql������ڲ��벿����Ϣ
		sql = "update major set m_id='" + mj.getM_id() + "',o_id='"
				+ mj.getO_id() + "',m_name='" + mj.getM_name() + "' where m_id='" + m_id + "'";
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬���ز����¼��
		r = st.executeUpdate(sql);
		return r;
	}

	
	/**
	 * �����ݱ��в�������
	 * 
	 * @author zzt
	 * @param  m_id
	 *            :רҵ���
	 * @return st.executeUpdate(sql);
	 */
	// ����һ����̬���β���ķ���
	public static int insert(Connection con, Major mj)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ����sql������ڲ��벿����Ϣ
		sql = "INSERT INTO major (m_id,o_id,m_name) VALUES ('" + mj.getM_id()
				+ "', '" + mj.getO_id() + "','" + mj.getM_name() + "')";
		// ��������ִ��SQL��䣬���ز����¼��
		return st.executeUpdate(sql);
	}

}
