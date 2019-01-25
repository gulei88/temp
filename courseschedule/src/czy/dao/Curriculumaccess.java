package czy.dao;

import global.dao.Databaseconnection;
import global.model.Curriculum;
import global.model.View_curriculum;
import global.model.View_curriculum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Curriculumaccess {
	/**
	 * ���ſ���Ϣ�������ݿ���
	 * 
	 * @author czy
	 * @param con
	 *            :���ݿ������
	 * @param tt
	 *            ����������ſ���Ϣ
	 * @return���������ɹ������ز����¼��;���ݿ�����ʧ�ܷ���-1������ʱ���������γ̳�ͻ����-2��
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insert(Connection con, Curriculum cc, String condition)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// �������ڶԱ�curriculum��д����sql���
		String locksqlString = "lock tables curriculum write";
		// �������ڶԱ��д����sql���
		String unlocksqlString = "unlock tables";
		// ���ɲ�ѯ���ݿ��Ƿ���ڳ�ͻ��SQL���
		sql = "SELECT * FROM View_curriculum WHERE " + condition;
		// �Ա�curriculum��д��
		st.executeQuery(locksqlString);
		locksqlString = "lock tables View_curriculum write";
		st.executeQuery(locksqlString);
		// ��ѯ���ݿ��Ƿ���ڳ�ͻ
		ResultSet rs = st.executeQuery(sql);
		// ������ڳ�ͻ����رռ�¼����������-2
		if (rs.next()) {
			rs.close();
			// �Ա��д��
			st.executeQuery(unlocksqlString);
			return -2;
		}
		rs.close();
		// ����sql������ڲ����ſ���Ϣ��Ϣ
		sql = "INSERT INTO curriculum (tt_id, lessons, week, Inweeks, cr_id,sl_name) VALUES ("
				+ cc.getTt_id()
				+ ", "
				+ cc.getLessons()
				+ ", "
				+ cc.getWeek()
				+ ", '"
				+ cc.getInweeks()
				+ "',"
				+ cc.getCr_id()
				+ ",'"
				+ cc.getSl_name() + "')";
		// ��������ִ��SQL��䣬���ز����¼��
		int r = st.executeUpdate(sql);
		// �Ա��д��
		st.executeQuery(unlocksqlString);
		return r;
	}

	/**
	 * ���ſ���Ϣ��Ϣ�����ݿ���ɾ��
	 * 
	 * @author czy
	 * @param con
	 *            :���ݿ������
	 * @param cc_id
	 *            ����ɾ�����ſ���Ϣ��Ϣ���
	 * @return�����ɾ���ɹ�������ɾ����¼�������򷵻�-1��
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int delete(Connection con, int cc_id)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻�-1
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ����sql�������ɾ���ſ���Ϣ
		sql = "DELETE FROM curriculum WHERE cc_id=" + cc_id;
		// ��������ִ��SQL��䣬����ɾ����¼��
		return st.executeUpdate(sql);
	}

	/**
	 * ��������ȡ��View_curriculum��ͼ������
	 * 
	 * @author czy
	 * @param condition
	 *            ����ѯ����
	 * @return ����������View_curriculum��������б�
	 */
	public static ArrayList<View_curriculum> getCurriculums(String condition) {
		// ���ɲ��ҡ�View_curriculum����ͼ��select��ѯ���
		String sql = "SELECT * FROM View_curriculum";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!condition.equals(""))
			sql += " WHERE " + condition;
		// ��ʼ����View_curriculum����������б����
		ArrayList<View_curriculum> View_curriculumlist = new ArrayList<View_curriculum>();
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
				int cc_id = rs.getInt("cc_id");
				int tt_id = rs.getInt("tt_id");
				String cou_id = rs.getString("cou_id");
				String cou_name = rs.getString("cou_name");
				String cla_name = rs.getString("cla_name");
				String cla_number = rs.getString("cla_number");
				int lessons = rs.getInt("lessons");
				int week = rs.getInt("week");
				String inweeks = rs.getString("Inweeks");
				int classhour = rs.getInt("classhour");
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String t_tel = rs.getString("t_tel");
				int sy_id = rs.getInt("sy_id");
				String sy_name = rs.getString("sy_name");
				int b_id = rs.getInt("b_id");
				String b_name = rs.getString("b_name");
				String b_address = rs.getString("b_address");
				int cr_id = rs.getInt("cr_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				String sl_name = rs.getString("sl_name");
				// ���ݽ�������������ɡ�View_curriculum�������
				View_curriculum vcc = new View_curriculum(cc_id, tt_id, cou_id,
						cou_name, cla_name, cla_number, lessons, week, inweeks,
						classhour, t_id, t_name, t_tel, sy_id, sy_name, b_id,
						b_name, b_address, cr_id, cr_name, ct_id, sl_name);
				// ����View_curriculum���������ӵ���View_curriculum����������б������
				View_curriculumlist.add(vcc);
			}
			// ���ء�View_curriculum����������б����
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
		return View_curriculumlist;
	}

	/**
	 * ��ȡĳ�ſγ��ſε��ܿ�ʱ
	 * 
	 * @param condition
	 *            ����ѯ����
	 * @return 0���޸������ſ���Ϣ�� ����0���������ſο�ʱ���� -1�������쳣�������ݿ����
	 */
	public static int getClasshour(String condition) {
		// ���ɸ����������ҡ�View_curriculum����ͼ�е��ܿ�ʱ�ֶε�select��ѯ���
		String sql = "SELECT sum(classhour) FROM View_curriculum where "
				+ condition;
		// �������ݿ�����Ӷ���
		Connection con = null;
		// ���ɽ��������
		ResultSet rs = null;
		try {
			// ȡ�����ݿ������
			con = Databaseconnection.getconnection();
			// ������ݿ������Ϊ�գ��򷵻ؿ�
			if (con == null)
				return -1;
			// �������ݿ���������
			Statement st = con.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			rs = st.executeQuery(sql);
			// ��������������,�����ſο�ʱ�������򷵻�0
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
