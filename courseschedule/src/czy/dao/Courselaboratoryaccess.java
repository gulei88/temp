package czy.dao;

import global.dao.Databaseconnection;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import czy.model.Courselaboratory;
import czy.model.View_courselaboratory;

/**
 * courselaboratory�������
 * 
 * @author czy
 * 
 */
public class Courselaboratoryaccess {
	/**
	 * ���ҿγ�ʵ�����Ƿ����
	 * @param con�����ݿ������
	 * @param cl�������ҵĿγ�ʵ����
	 * @return�����ڷ����棬�����ڷ��ؼ�
	 */
	
	private static boolean findCourselaboratory(Connection con, Courselaboratory cl) {
		boolean r = false;
		//��ȡ��Ӧ�ֶ���Ϣ
		int ce_id = cl.getCe_id();
		int cr_id = cl.getCr_id();
		int sl_id = cl.getSl_id();
		//���ݻ�ȡ���ֶ����ɲ�ѯ���
		String sql = "select * from courselaboratory where ce_id=" + ce_id
				+ " and cr_id=" + cr_id;
		if (sl_id >= 0) {
			sql += " and sl_id=" + sl_id;
		}
		ResultSet rs = null;
		//ִ��sql������Ϣ��������ҵ������棬���򷵻ؼ�
		try {
			// �������ݿ���������
			Statement st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
				r = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * ��γ�ʵ���ұ��в�������
	 * 
	 * @author czy
	 * @param con
	 *            �����ݿ������
	 * @param cl
	 *            ������ӵĿγ�ʵ������Ϣ
	 * @return ���ڵ����㣺����ɹ��ļ�¼��,-1606�������Ѵ���
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insert(Connection con, Courselaboratory cl)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		int ce_id = cl.getCe_id();
		int cr_id = cl.getCr_id();
		int sl_id = cl.getSl_id();
		if(findCourselaboratory(con,cl))
			return -1606;
		// ����ʵ����ұ���Ƿ���Ч������sql������ڲ���γ�ʵ������Ϣ
		if (sl_id < 0)
			sql = "INSERT INTO courselaboratory (ce_id,cr_id) VALUES (" + ce_id
					+ "," + cr_id + ")";
		else
			sql = "INSERT INTO courselaboratory (ce_id,cr_id,sl_id) VALUES ("
					+ ce_id + "," + cr_id + "," + sl_id + ")";
		// ��������ִ��SQL��䣬���ز����¼��
		int r = st.executeUpdate(sql);
		return r;
	}
	/**
	 * ��courselaboratory����ɾ����¼
	 * @author czy
	 * @param con�����ݿ������
	 * @param cl_id��Ҫɾ���Ŀγ�ʵ���ұ��
	 * @return��ɾ���ļ�¼��
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int delete(Connection con, int cl_id)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		sql = "DELETE FROM courselaboratory WHERE cl_id="+cl_id;
		// ��������ִ��SQL��䣬���ز����¼��
		int r = st.executeUpdate(sql);
		return r;
	}

	/**
	 * ���ݿγ�ʵ�黷����Ų�ѯ�γ���ʵ���Ҷ�Ӧ���
	 * 
	 * @author czy
	 * @param ce_id
	 *            :�γ�ʵ�黷�����
	 * 
	 * @return view_courselaboratory���������б�
	 */
	public static ArrayList<View_courselaboratory> getcourselaboratorybyceid(
			int ce_id) {
		// ��ʼ����view_courselaboratory����������б����
		ArrayList<View_courselaboratory> cllist = new ArrayList<View_courselaboratory>();
		// ���ɲ���view_courselaboratory���ݵ�sql���
		String sql = "SELECT * FROM view_courselaboratory where ce_id=" + ce_id;
		// ���ú������������������������б�
		cllist = getview_courselaboratory(sql);
		// ���ء�view_courselaboratory����������б����
		return cllist;
	}

	/**
	 * ���ݲ�ѯ������ѯ�γ���ʵ���Ҷ�Ӧ���
	 * 
	 * @author czy
	 * @param condition
	 *            :��ѯ����
	 * @return ��view_courselaboratory����������б�
	 */
	public static ArrayList<View_courselaboratory> getCourselaboratorybycondition(
			String condition) {
		// ���ɲ��ҡ�view_courselaboratory�����select��ѯ���
		String sql = "SELECT * FROM view_courselaboratory";
		// ��������������Ϊ�գ���SQL�����Ӳ�������Ϊ�����������ҡ�view_courselaboratory����ͼ����
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// ��ʼ����view_courselaboratory����������б����
		ArrayList<View_courselaboratory> cllist = new ArrayList<View_courselaboratory>();
		// ���ú������������������������б�
		cllist = getview_courselaboratory(sql);
		// ���ء�view_courselaboratory����������б����
		return cllist;
	}

	/**
	 * ���ݴ����sql��䵽���ݿ��в�Ѱ��������������
	 * 
	 * @author czy
	 * @param ���ݿ��ѯSQL���
	 * @return ���������ġ�view_courselaboratory����������б�
	 */
	private static ArrayList<View_courselaboratory> getview_courselaboratory(
			String sql) {
		// ��ʼ����view_courselaboratory����������б����
		ArrayList<View_courselaboratory> cllist = new ArrayList<View_courselaboratory>();
		Connection con = null;
		ResultSet rs = null;
		try {
			// ȡ�����ݿ������
			con = Databaseconnection.getconnection();
			// �������ݿ���������
			Statement st = con.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			rs = st.executeQuery(sql);

			while (rs.next()) {
				// ȡ���������Ӧ�ֶ�����
				String cl_id = rs.getString("cl_id");
				int ce_id = rs.getInt("ce_id");
				String sl_id = rs.getString("sl_id");
				String sl_name = rs.getString("sl_name");
				int sumsl_seating = rs.getInt("sumsl_seating");
				String sl_seating = rs.getString("sl_seating");
				int cr_id = rs.getInt("cr_id");
				String d_id = rs.getString("d_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				int cr_seating = rs.getInt("cr_seating");
				int b_id = rs.getInt("b_id");
				// ���ݽ�������������ɡ�view_courselaboratory�������
				View_courselaboratory vce = new View_courselaboratory(cl_id,
						ce_id, sl_id, sl_name, sumsl_seating, sl_seating,
						cr_id, d_id, cr_name, ct_id, cr_seating, b_id);
				// ����view_courselaboratory���������ӵ���view_courselaboratory����������б������
				cllist.add(vce);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// ���ء�view_courselaboratory����������б����
		return cllist;
	}
}
