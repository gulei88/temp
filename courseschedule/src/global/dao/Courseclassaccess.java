package global.dao;

import global.model.Courseclass;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;

/**
 * ��ѧ�����Ӧ�༶�����ݹ�����
 * 
 * @author czy
 */
public class Courseclassaccess {
	/**
	 * �������ݵ���ѧ�����Ӧ�༶��
	 * 
	 * @param con
	 *            :���ݿ������
	 * @param tt_id
	 *            ����ѧ����id
	 * @param cla_id
	 *            ���༶id
	 * @return ���� ����0�������¼��Ŀ -1�����ݿ�����ʧ��
	 * @throws SQLException
	 */
	public static int insert(Connection con, int tt_id, String cla_id)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql = "INSERT INTO courseclass (tt_id, cla_id) VALUES (" + tt_id
				+ ",'" + cla_id + "')";
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬����Ӱ��ļ�¼��Ŀ��
		return st.executeUpdate(sql);
	}

	/**
	 * ��������ɾ��courseclass������
	 * 
	 * @param con
	 *            �� ���ݿ������
	 * @param condition
	 *            ��ɾ������
	 * @return ɾ��Ӱ��ļ�¼��
	 * @throws SQLException
	 */
	public static int Delete(Connection con, String condition)
			throws SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		if (condition.equals(""))
			return 0;
		String sql = "DELETE FROM courseclass WHERE " + condition;
		Statement st = con.createStatement();
		return st.executeUpdate(sql);
	}

	/**
	 * ���ҽ�ѧ�����Ӧ�༶������
	 * 
	 * @author czy
	 * @param tt_id
	 *            :��ѧ������
	 * @return ��Courseclass����������б�
	 * 
	 */
	public static ArrayList<Courseclass> getCourseclass(int tt_id) {
		// ���ɲ��ҡ�Courseclass�����select��ѯ���
		String sql = "SELECT * FROM courseclass";
		// �������Ľ�ѧ�����Ų�Ϊ-1����SQL�����Ӳ�������Ϊ���ݽ�ѧ�����Ų��ҡ�Courseclass��������
		if (tt_id != -1) {
			sql += " WHERE tt_id=" + tt_id;
		}
		// ��ʼ����Courseclass����������б����
		ArrayList<Courseclass> Courseclasslist = new ArrayList<Courseclass>();
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
				tt_id = rs.getInt("tt_id");
				String cla_id = rs.getString("cla_id");
				// ���ݽ�������������ɡ�Courseclass�������
				Courseclass cc = new Courseclass(tt_id, cla_id);
				// ����Courseclass���������ӵ���Courseclass����������б������
				Courseclasslist.add(cc);
			}
			// ���ء�Courseclass����������б����
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return Courseclasslist;
	}
}
