package global.dao;

import global.model.Course;
import global.model.Teachingplan;
import global.model.View_course;
import global.model.View_course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * �γ���Ϣ���ݹ�����
 * 
 * @author czy
 * 
 */
public class Courseaccess {
	/**
	 * ��������ȡ��View_course��ͼ������
	 * 
	 * @author czy
	 * @param condition
	 *            ����ѯ����
	 * @return ����������View_course��������б�
	 */
	public static ArrayList<View_course> getView_course(String condition) {
		// ���ɲ��ҡ�View_course����ͼ��select��ѯ���
		String sql = "SELECT * FROM view_course";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!condition.equals(""))
			sql += " WHERE tp_id =" + condition;
		// ��ʼ����View_course����������б����
		ArrayList<View_course> View_courselist = new ArrayList<View_course>();
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
				String tp_mark = rs.getString("tp_mark");
				String m_id = rs.getString("m_id");
				String cou_id = rs.getString("cou_id");
				String cou_category = rs.getString("cou_category");
				String cou_name = rs.getString("cou_name");
				float cou_credit = rs.getFloat("cou_credit");
				int cou_theoryhour = rs.getInt("cou_theoryhour");
				int cou_experimentalhours = rs.getInt("cou_experimentalhours");
				int cou_practicehour = rs.getInt("cou_practicehour");
				int cou_semester = rs.getInt("cou_semester");
				int cou_type = rs.getInt("cou_type");
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				// ���ݽ�������������ɡ�View_course�������
				View_course vc = new View_course(tp_id, tp_name, tp_mark,
						m_id,cou_id, cou_category, cou_name, cou_credit,
						cou_theoryhour, cou_experimentalhours,
						cou_practicehour, cou_semester, cou_type, d_id, d_name);
				// ����View_course���������ӵ���View_course����������б������
				View_courselist.add(vc);
			}
			// ���ء�View_course����������б����
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
		return View_courselist;
	}
	
	
	/**
	 * ���ݽ�ѧ�ƻ�id�������ݱ����Ƿ����������ݳ�ͻ
	 * 
	 * @author zzt
	 * @param tp_id
	 *            :��ѧ�ƻ����
	 * @return rs.next()
	 */
	
	
	public static boolean findid(Connection conn,String cou_id) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_name���������ƣ�
		String sql = "SELECT * FROM course where cou_id='"+cou_id+"'";
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
	
	
	public static boolean findname(Connection conn,String cou_name) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_name���������ƣ�
		String sql = "SELECT * FROM course where cou_name='"+cou_name+"'";
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
	public static int Delete(Connection con, String cou_id) throws SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���d_id�����ű�ţ�Ϊ�յĻ����򷵻�0
		if (cou_id.equals(""))
			return 0;
		// //Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
		String sql = "DELETE FROM course WHERE cou_id in" + (cou_id) + "";
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
	public static int update(Connection con, String cou_id, Course cs)
			throws SQLException {
		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// ����sql������ڲ��벿����Ϣ
		sql = "update course set cou_id='" + cs.getCou_id() + "',tp_id='"
				+ cs.getTp_id() + "',cou_category='" + cs.getCou_category() + "',cou_name='" + cs.getCou_name() + "',cou_credit='" + cs.getCou_credit() + "',cou_theoryhour='" + cs.getCou_theoryhour() + "',cou_experimentalhours='" + cs.getCou_experimentalhours() + "',cou_practicehour='" + cs.getCou_practicehour() + "',cou_semester='" + cs.getCou_semester() + "',cou_type='" + cs.getCou_type() + "'where cou_id='" + cou_id + "'";
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
	public static int insert(Connection con, Course cs)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ����sql������ڲ��벿����Ϣ
		sql = "INSERT INTO course (cou_id,tp_id,cou_category,cou_name,cou_credit,cou_theoryhour,cou_experimentalhours,cou_practicehour,cou_semester,cou_type,d_id) VALUES ('" + cs.getCou_id()
				+ "', '" + cs.getTp_id() + "', '" + cs.getCou_category() + "','" + cs.getCou_name() + "','" + cs.getCou_credit() + "','" + cs.getCou_theoryhour() + "','" + cs.getCou_experimentalhours() + "','" + cs.getCou_practicehour() + "','" + cs.getCou_semester() + "','" + cs.getCou_type() + "','" + cs.getD_id() + "')";
		// ��������ִ��SQL��䣬���ز����¼��
		return st.executeUpdate(sql);
	}
	
	
}
