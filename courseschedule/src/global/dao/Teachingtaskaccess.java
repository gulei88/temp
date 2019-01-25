package global.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import global.model.Teachingtask;
import global.model.View_teacher;
import global.model.View_teachingtask;

/**
 * ��ѧ�������ݷ�����
 * 
 * @author czy
 * 
 */
public class Teachingtaskaccess {
	/**
	 * ����ѧ������Ϣ�������ݿ���
	 * 
	 * @author czy
	 * @param con
	 *            :���ݿ������
	 * @param tt
	 *            ��������Ľ�ѧ������Ϣ
	 * @return���������ɹ������ؽ�ѧ����id�����򷵻�-1��-2��
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insert(Connection con, Teachingtask tt)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ���ɲ�����һ����ѧ����id��sql���
		sql = "SELECT max(tt_id)+1 as next_id FROM courseschedule.teachingtask";
		// ִ��sql��䣬���ؼ�¼��
		ResultSet rs = st.executeQuery(sql);
		// �����ѧ����id������ʼ��Ϊ1
		int tt_id = 1;
		// �������������ݣ���ȡ����һ����ѧ����id
		if (rs.next())
			tt_id = rs.getInt("next_id");
		// ����sql������ڲ����ѧ������Ϣ
		sql = "INSERT INTO teachingtask (tt_id, cou_id,cou_theoryhour,cou_experimentalhours,cou_practicehour, t_id, sy_id, multimedia,m_id,tt_grade,Practicescheduling,tt_state) VALUES ("
				+ tt_id
				+ ", '"
				+ tt.getCou_id()
				+ "', "
				+ tt.getCou_theoryhour()
				+ ", "
				+ tt.getCou_experimentalhours()
				+ ", "
				+ tt.getCou_practicehour()
				+ ", '"
				+ tt.getT_id()
				+ "', '"
				+ tt.getSy_id()
				+ "', '"
				+ tt.getMultimedia()
				+ "', '"
				+ tt.getM_id()
				+ "', '"
				+ tt.getTt_grade()
				+ "', '"
				+ tt.getPracticescheduling() + "', " + tt.getTt_state() + ")";

		// ��������ִ��SQL��䣬���ؽ�ѧ����id
		if (st.executeUpdate(sql) > 0)
			return tt_id;
		// ���򷵻�-2
		else
			return -2;
	}

	/**
	 * ��������ȡ��View_teachingtask��ͼ������
	 * 
	 * @author czy
	 * @param condition
	 *            ����ѯ����
	 * @return ����������View_teachingtask��������б�
	 */
	public static ArrayList<View_teachingtask> getView_teachingtask(
			String condition) {
		// ���ɲ��ҡ�View_teachingtask����ͼ��select��ѯ���
		String sql = "SELECT * FROM view_teachingtask";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!condition.equals(""))
			sql += " WHERE " + condition;
		// ��ʼ����View_teachingtask����������б����
		ArrayList<View_teachingtask> View_teachingtasklist = new ArrayList<View_teachingtask>();
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
				int tt_id = rs.getInt("tt_id");
				String cou_id = rs.getString("cou_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String cla_name = rs.getString("cla_name");
				int cla_number = rs.getInt("cla_number");
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String t_tel = rs.getString("t_tel");
				int sy_id = rs.getInt("sy_id");
				String sy_name = rs.getString("sy_name");
				String multimedia = rs.getString("multimedia");
				String m_id = rs.getString("m_id");
				String tt_grade = rs.getString("tt_grade");
				String Practicescheduling = rs.getString("Practicescheduling");
				int tt_state = rs.getInt("tt_state");

				// ���ݽ�������������ɡ�View_teachingtask�������
				View_teachingtask vt = new View_teachingtask(tt_id, cou_id,
						cou_category, cou_name, cou_credit, cou_theoryhour,
						cou_experimentalhours, cou_practicehour, cou_semester,
						cou_type, cla_name, cla_number, t_id, t_name, t_tel,
						sy_id, sy_name, multimedia, m_id, tt_grade,
						Practicescheduling, tt_state);
				// ����View_teachingtask���������ӵ���View_teachingtask����������б������
				View_teachingtasklist.add(vt);
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
		// ���ء�View_teachingtask����������б����
		return View_teachingtasklist;
	}

	/**
	 * ��������ȡ��teachingtask��������
	 * 
	 * @author czy
	 * @param condition
	 *            ����ѯ����
	 * @return ����������Teachingtask��������б�
	 * 
	 */
	public static ArrayList<Teachingtask> getTeachingtask(String condition) {
		// ���ɲ��ҡ�teachingtask�����select��ѯ���
		String sql = "SELECT * FROM teachingtask";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!condition.equals(""))
			sql += " WHERE " + condition;
		// ��ʼ����teachingtask����������б����
		ArrayList<Teachingtask> teachingtasklist = new ArrayList<Teachingtask>();
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
				int tt_id = rs.getInt("tt_id");
				String cou_id = rs.getString("cou_id");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				String t_id = rs.getString("t_id");
				int sy_id = rs.getInt("sy_id");
				String multimedia = rs.getString("multimedia");
				String m_id = rs.getString("m_id");
				String tt_grade = rs.getString("tt_grade");
				String practicescheduling = rs.getString("Practicescheduling");
				int tt_state = rs.getInt("tt_state");
				// ���ݽ�������������ɡ�View_teachingtask�������
				Teachingtask tt = new Teachingtask(tt_id, cou_id,
						cou_theoryhour, cou_experimentalhours,
						cou_practicehour, t_id, sy_id, multimedia, m_id,
						tt_grade, practicescheduling, tt_state);
				// ����View_teachingtask���������ӵ���View_teachingtask����������б������
				teachingtasklist.add(tt);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		// ���ء�View_teachingtask����������б����
		return teachingtasklist;
	}

	/**
	 * �ڽ�ѧ�������ɾ����ѧ����
	 * 
	 * @param con
	 *            :���ݿ������
	 * @param tt_id
	 *            ����ɾ���Ľ�ѧ������
	 * @return ɾ���ļ�¼��
	 * @throws SQLException
	 */
	public static int delete(Connection con, int tt_id) throws SQLException {
		// ȡ�����ݿ������
		int r;

		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ����ɾ����ѧ�����sql���
		sql = "DELETE FROM teachingtask WHERE tt_id=" + tt_id;
		// ִ��sql��䣬���ؼ�¼��
		r = st.executeUpdate(sql);
		return r;
	}

	/**
	 * 
	 * @param con
	 *            :���ݿ������
	 * @param vt
	 *            :�޸ĵĽ�ѧ������Ϣ
	 * @return �޸ĳɹ��ļ�¼��
	 * @throws SQLException
	 */
	public static int update(Connection con, View_teachingtask vt)
			throws SQLException {

		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���ɲ����sql���
		String sql = "UPDATE teachingtask SET t_id='" + vt.getT_id()
				+ "', multimedia='" + vt.getMultimedia()
				+ "', Practicescheduling='" + vt.getPracticescheduling()
				+ "' WHERE `tt_id`=" + vt.getTt_id();
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		r = st.executeUpdate(sql);

		return r;
	}

	/**
	 * 
	 * @param con
	 *            :���ݿ������
	 * @param tt_id
	 *            :Ҫ�޸ĵĽ�ѧ������
	 * @param tt_state
	 *            :Ҫ�޸ĵĽ�ѧ����״̬
	 * @return �޸ĳɹ��ļ�¼��
	 * @throws SQLException
	 */
	public static int updatestate(Connection con, int tt_id, int tt_state)
			throws SQLException {

		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���ɲ����sql���
		String sql = "UPDATE teachingtask SET tt_state=" + tt_state
				+ " WHERE `tt_id`=" + tt_id;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		r = st.executeUpdate(sql);

		return r;
	}

	/**
	 * 
	 * @param con
	 *            :���ݿ������
	 * @param tt_id
	 *            :Ҫ�޸ĵĽ�ѧ������
	 * @param cou_experimentalhours
	 *            :Ҫ�޸ĵ�ʵ���ʱ
	 * @return �޸ĳɹ��ļ�¼��
	 * @throws SQLException
	 */
	public static int updateexperimentalhours(Connection con, int tt_id,
			int cou_experimentalhours) throws SQLException {
		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���ɲ����sql���
		String sql = "UPDATE teachingtask SET cou_experimentalhours="
				+ cou_experimentalhours + " WHERE `tt_id`=" + tt_id;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		r = st.executeUpdate(sql);
		return r;
	}

	/**
	 * 
	 * @param con
	 *            :���ݿ������
	 * @param tt_id
	 *            :Ҫ�޸ĵĽ�ѧ������
	 * @param cou_practicehour
	 *            :Ҫ�޸ĵ�ʵ����ʱ
	 * @return �޸ĳɹ��ļ�¼��
	 * @throws SQLException
	 */
	public static int updatepracticehour(Connection con, int tt_id,
			int cou_practicehour) throws SQLException {
		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���ɲ����sql���
		String sql = "UPDATE teachingtask SET cou_practicehour="
				+ cou_practicehour + " WHERE `tt_id`=" + tt_id;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		r = st.executeUpdate(sql);
		return r;
	}
}
