package czy.dao;

import global.dao.Databaseconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import czy.model.Tools;
import czy.model.Courseexperimentalenvironment;
import czy.model.View_courseexperimentalenvironment;


/**
 * courseexperimentalenvironment�������
 * 
 * @author czy
 * 
 */
public class Courseexperimentalenvironmentaccess {
	/**
	 * ��γ�ʵ�黷�����в�������
	 * @author czy
	 * @param con
	 *            �����ݿ������
	 * @param ce
	 *            ������ӵĿγ�ʵ�黷��
	 * @return ����ɹ��ļ�¼��
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insert(Connection con, Courseexperimentalenvironment ce)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();

		// ����sql������ڲ���γ�ʵ�黷����Ϣ
		sql = "INSERT INTO courseexperimentalenvironment (cou_id, grade) VALUES ('"
				+ ce.getCou_id() + "', '" + ce.getGrade() + "')";
		// ��������ִ��SQL��䣬���ز����¼��
		int r = st.executeUpdate(sql);
		return r;
	}
	/**
	 * �ӿγ�ʵ�黷������ɾ������
	 * @author czy
	 * @param con
	 *            �����ݿ������
	 * @param ce_id
	 *            ����ɾ���Ŀγ�ʵ�黷�����
	 * @return ɾ���ɹ��ļ�¼��
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int delete(Connection con, int ce_id)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();

		// ����sql�������ɾ���γ�ʵ�黷����Ϣ
		sql = "DELETE FROM courseexperimentalenvironment WHERE ce_id="+ce_id;
		// ��������ִ��SQL��䣬���ز����¼��
		int r = st.executeUpdate(sql);
		return r;
	}
	/**
	 * �ӿγ�ʵ�黷������ɾ������
	 * @author czy
	 * @param con
	 *            �����ݿ������
	 * @param ce_id
	 *            ����ɾ���Ŀγ�ʵ�黷�����
	 * @return ɾ���ɹ��ļ�¼��
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int deletebycouidgrade(Connection con, String cou_id,String grade)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();

		// ����sql�������ɾ���γ�ʵ�黷����Ϣ
		sql = "DELETE FROM courseexperimentalenvironment WHERE cou_id='"+cou_id+"' and grade='"+grade+"'";
		// ��������ִ��SQL��䣬���ز����¼��
		int r = st.executeUpdate(sql);
		return r;
	}

	/**
	 * �ڡ���ѧ���������ͼ���в��ҡ��γ�ʵ�黷����ͼ���в����ڵĿγ̣������ʼ���뵽�γ�ʵ�黷������
	 * 
	 * @author czy
	 * @param sy_id
	 *            :ѧ�ڱ��
	 * @param m_id
	 *            ��רҵ���
	 * @return ����ֵ�������Ƿ��ʼ����ɹ�
	 */
	private static boolean Initinsert(int sy_id, String m_id) {
		boolean r = false;
		// ���ɲ��ҡ�view_teachingtaskstatistics����ͼ��select��ѯ���(��ѯ����Ϊѧ����רҵ�������������ҿγ��ڿγ�ʵ�黷�����в�����)
		String sql = "SELECT * FROM view_teachingtaskstatistics where  sy_id="
				+ sy_id
				+ " and m_id='"
				+ m_id
				+ "' and cou_id not in(SELECT cou_id FROM view_courseexperimentalenvironment where sy_id="
				+ sy_id + " and m_id='" + m_id + "')";
		Connection con = null;
		ResultSet rs = null;

		// ȡ�����ݿ������
		try {
			con = Databaseconnection.getconnection();

			// ������ݿ������Ϊ�գ��򷵻ؼ�
			if (con == null)
				return false;
			// �������ݿ���������
			Statement st = con.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			rs = st.executeQuery(sql);
			// �����µ����ݿ�����con1
			Connection con1 = null;
			try {
				con1 = Databaseconnection.getconnection();
				// con1�Զ��ύ��Ϊ��
				con1.setAutoCommit(false);
				// ��������������
				while (rs.next()) {
					// ȡ���γ̺�
					String cou_id = rs.getString("cou_id");
					// ȡ���꼶
					String grade = rs.getString("tt_grade");
					// ���ɿγ�ʵ�黷�������
					Courseexperimentalenvironment ce = new Courseexperimentalenvironment(
							cou_id, grade, null);
					// �����ɵĿγ�ʵ�黷��������뵽���ݿ�γ�ʵ�黷������
					insert(con1, ce);
				}
				// ���������ύ����
				con1.commit();
				r = true;
			} catch (Exception e) {
				Tools.connectionroolback(con1, "�γ�ʵ�黷����ʼ������ʧ�ܣ�����ϵϵͳ����Ա��");
			} finally {
				// �����������δ�رգ���رո���������
				if (!con1.isClosed()) {
					con1.close();
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "�γ�ʵ�黷����ʼ������ʧ�ܣ�����ϵϵͳ����Ա��");
		}
		return r;
	}
	/**
	 * @author czy
	 * @param sy_id
	 *            :ѧ�ڱ��
	 * @param m_id
	 *            ��רҵ���
	 * @return view_courseexperimentalenvironment���������б�
	 */
	public static ArrayList<View_courseexperimentalenvironment> getview_courseexperimentalenvironment(
			int sy_id, String m_id) {
		//��ʼ����ʧ�ܣ����ؿ�
		if (!Initinsert(sy_id, m_id))
			return null;
		// ��ʼ����view_courseexperimentalenvironment����������б����
		ArrayList<View_courseexperimentalenvironment> ceslist = new ArrayList<View_courseexperimentalenvironment>();
		Connection con = null;
		ResultSet rs = null;
		try {
			// ���ɲ���view_courseexperimentalenvironment���ݵ�sql���
			String sql = "SELECT * FROM view_courseexperimentalenvironment where sy_id="
					+ sy_id + " and m_id='" + m_id + "' order by grade,cou_id";
			// ȡ�����ݿ������
			con = Databaseconnection.getconnection();
			// �������ݿ���������
			Statement st = con.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			rs = st.executeQuery(sql);

			while (rs.next()) {
				// ȡ���������Ӧ�ֶ�����
				int ce_id = rs.getInt("ce_id");
				String grade = rs.getString("grade");
				String Experimentalenvironment = rs
						.getString("Experimentalenvironment");
				String cou_id = rs.getString("cou_id");
				// int sy_id= rs.getInt("sy_id");
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String tp_id = rs.getString("tp_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String d_id = rs.getString("d_id");
				// String m_id = rs.getString("m_id");
				String m_name = rs.getString("m_name");
				String cla_grade = rs.getString("cla_grade");
				int l_id = rs.getInt("l_id");
				String cla_name = rs.getString("cla_name");
				String cla_number = rs.getString("cla_number");
				String Numberofcombinedclasses=rs.getString("Numberofcombinedclasses");

				// ���ݽ�������������ɡ�view_courseexperimentalenvironment�������
				View_courseexperimentalenvironment vce = new View_courseexperimentalenvironment(
						ce_id, grade, Experimentalenvironment, cou_id, sy_id,
						t_id, t_name, tp_id, cou_category, cou_name,
						cou_credit, cou_theoryhour, cou_experimentalhours,
						cou_practicehour, cou_semester, cou_type, d_id, m_id,
						m_name, cla_grade, l_id, cla_name, cla_number,Numberofcombinedclasses);
				// ����view_courseexperimentalenvironment���������ӵ���View_curriculum����������б������
				ceslist.add(vce);
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
		// ���ء�view_courseexperimentalenvironment����������б����
		return ceslist;
	}

	/**
	 * �޸�courseexperimentalenvironment��experimentalenvironment�ֶ�
	 * 
	 * @author czy
	 * @param con
	 *            :���ݿ������
	 * @param ce
	 *            :Ҫ�޸ĵĿγ����񻷾������
	 * @return �޸ĳɹ��ļ�¼��
	 * @throws SQLException
	 */
	public static int updateexperimentalenvironment(Connection con,
			Courseexperimentalenvironment ce) throws SQLException {

		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���ɲ����sql���
		String sql = "UPDATE courseexperimentalenvironment SET Experimentalenvironment='"
				+ ce.getExperimentalenvironment()
				+ "' WHERE ce_id="
				+ ce.getCe_id() + "";
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		r = st.executeUpdate(sql);
		return r;
	}
}
