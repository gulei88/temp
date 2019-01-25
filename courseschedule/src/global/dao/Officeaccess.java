package global.dao;

import global.model.Office;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * ������Ϣ���ݹ�����
 * 
 * @author czy
 * 
 */
public class Officeaccess {
	/**
	 * ���ݲ���idȡ��Office��������
	 * 
	 * @author czy
	 * @param d_id
	 *            :���ű��
	 * @return ��Office����������б�
	 */
	public static ArrayList<Office> getoffice(String d_id) {
		// ���ɲ��ҡ�Office�����select��ѯ���
		String sql = "SELECT * FROM office";
		// �������Ĳ��ű�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݲ��ű�Ų��ҡ�Office����ͼ����
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// ��ʼ����Office����������б����
		ArrayList<Office> officelist = new ArrayList<Office>();
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
				String o_id = rs.getString("o_id");
				d_id = rs.getString("d_id");
				String o_name = rs.getString("o_name");
				// ���ݽ�������������ɡ�Office�������
				Office of = new Office(o_id, d_id, o_name);
				// ����Office���������ӵ���Office����������б������
				officelist.add(of);
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
		return officelist;
	
	}
	
	
	
	public static ArrayList<Office> getoffice_(String o_id) {
		// ���ɲ��ҡ�Office�����select��ѯ���
		String sql = "SELECT * FROM office";
		// �������Ĳ��ű�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݲ��ű�Ų��ҡ�Office����ͼ����
		if (!(o_id == null || o_id.equals(""))) {
			sql += " WHERE o_id='" + o_id + "'";
		}
		// ��ʼ����Office����������б����
		ArrayList<Office> officelist = new ArrayList<Office>();
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
				o_id = rs.getString("o_id");
				String d_id = rs.getString("d_id");
				String o_name = rs.getString("o_name");
				// ���ݽ�������������ɡ�Office�������
				Office of = new Office(o_id, d_id, o_name);
				// ����Office���������ӵ���Office����������б������
				officelist.add(of);
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
		return officelist;
	
	}
	public static boolean findid(Connection conn, String o_id)
			throws SQLException {
		// Ҫִ�е�SQL����Ǹ�����������o_id�����ұ�ţ�
		String sql = "SELECT * FROM office where o_id='" + o_id + "'";
		// �������ݿ���������
		Statement st = conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		// ���ر���rs�Ľ�������м�¼
		return rs.next();
	}
	// ����һ����̬�������͵Ĳ�ѯ����
	public static boolean findname(Connection conn, String o_name)
			throws SQLException {
		// Ҫִ�е�SQL����Ǹ�����������o_name���������ƣ�
		String sql = "SELECT * FROM office where o_name='" + o_name + "'";
		// �������ݿ���������
		Statement st = conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		// ���ر���rs�Ľ�������м�¼
		return rs.next();
	}

	// ����һ����̬����ɾ���ķ���
	public static int Delete(Connection con, String o_id) throws SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���o_id�����ұ�ţ�Ϊ�յĻ����򷵻�0
		if (o_id.equals(""))
			return 0;
		// //Ҫִ�е�SQL����Ǹ�������ɾ��o_id(���ұ��)
		String sql = "DELETE FROM office WHERE o_id in" + (o_id) + "";
		// �������ݿ�������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		return st.executeUpdate(sql);
	}

	// ����һ����̬���ε��޸ķ���
	public static int update(Connection con, String o_id, Office of)
			throws SQLException {
		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// ����sql������ڲ��������Ϣ
		sql = "UPDATE office SET o_id='"+of.getO_id()+"', d_id='"+of.getD_id()+"', o_name='"+of.getO_name()+"' WHERE o_id='"+o_id+"'";
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬���ز����¼��
		r = st.executeUpdate(sql);
		return r;
	}

	public static ResultSet findmax(Connection conn){
		//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
		String sql = "select max(o_id) as o_id from office";
		ResultSet rs=null;
		try {
			// �������ݿ���������
			Statement st= conn.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����rs�Ľ�������м�¼
		return rs;
	}
	
	
	// ����һ����̬���β���ķ���
	public static int insert(Connection con, Office of)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ����sql������ڲ��������Ϣ
		sql = "INSERT INTO office (o_id,d_id,o_name) VALUES ('"+of.getO_id()+"', '"+of.getD_id()+"', '"+of.getO_name()+"')";
		// ��������ִ��SQL��䣬���ز����¼��
		return st.executeUpdate(sql);
	}

}
