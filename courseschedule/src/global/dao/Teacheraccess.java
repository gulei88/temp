package global.dao;

import global.model.Major;
import global.model.Teacher;
import global.model.View_teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import zzt.model.Zzt_Teacher_login;

/**
 * ��ʦ��Ϣ���ݹ�����
 * 
 * @author czy
 * 
 */
public class Teacheraccess {
//	private static String u;
	/**
	 * ��������ȡ��View_teacher��ͼ������
	 * 
	 * @author czy
	 * @param condition
	 *            :��ѯ����
	 * @return: ��View_teacher����������б�
	 */
	public static ArrayList<View_teacher> getview_teacher(String condition) {
		// ���ɲ��ҡ�view_teacher����ͼ��select��ѯ���
		String sql = "SELECT * FROM view_teacher";
		// �������Ľ�ʦ��Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݽ�ʦ��Ų��ҡ�view_teacher����ͼ����
		if (!(condition.equals(""))) {
			sql += " WHERE o_id=" + condition;
		}
		// ��ʼ����View_teacher����������б����
		ArrayList<View_teacher> teacherlist = new ArrayList<View_teacher>();
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
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String t_password = rs.getString("t_password");
				String t_power = rs.getString("t_power");
				String t_tel = rs.getString("t_tel");
				// ���ݽ�������������ɡ�View_teacher�������
				View_teacher vt = new View_teacher(d_id, d_name, o_id, o_name,
						t_id, t_name, t_password, t_power, t_tel);
				// ����View_teacher���������ӵ���View_teacher����������б������
				teacherlist.add(vt);
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
//		System.out.println(teacherlist);
		return teacherlist;
	}
	
	
	public static ArrayList<View_teacher> getview_teachertwo(String condition) {
		// ���ɲ��ҡ�view_teacher����ͼ��select��ѯ���
		String sql = "SELECT * FROM view_teacher";
		// �������Ľ�ʦ��Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݽ�ʦ��Ų��ҡ�view_teacher����ͼ����
		if (!(condition.equals(""))) {
			sql += " WHERE t_id=" + condition;
		}
		// ��ʼ����View_teacher����������б����
		ArrayList<View_teacher> teacherlist = new ArrayList<View_teacher>();
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
				String t_id = rs.getString("t_id");
				String t_name = rs.getString("t_name");
				String t_password = rs.getString("t_password");
				String t_power = rs.getString("t_power");
				String t_tel = rs.getString("t_tel");
				// ���ݽ�������������ɡ�View_teacher�������
				View_teacher vt = new View_teacher(d_id, d_name, o_id, o_name,
						t_id, t_name, t_password, t_power, t_tel);
				// ����View_teacher���������ӵ���View_teacher����������б������
				teacherlist.add(vt);
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
//		System.out.println(teacherlist);
		return teacherlist;
		
	}
	
	/**
	 * ���ݽ�ʦ���ֲ������ݱ����Ƿ����������ݳ�ͻ
	 * 
	 * @author zzt
	 * @param t_name
	 *            :��ʦ����
	 * @return rs.next()
	 */
	
	
	public static boolean findname(Connection conn,String t_name) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_name���������ƣ�
		String sql = "SELECT * FROM teacher where t_name='"+t_name+"'";
		//�������ݿ���������
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		//���ر���rs�Ľ�������м�¼
		return rs.next();
}
	
	
	/**
	 * ���ݽ�ʦidɾ�����ݱ������
	 * 
	 * @author zzt
	 * @param t_id
	 *            ����ʦid
	 * @return return st.executeUpdate(sql);
	 */
	
	
	public static int Delete(Connection con, String t_id) throws SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		// ���d_id�����ű�ţ�Ϊ�յĻ����򷵻�0
		if (t_id.equals(""))
			return 0;
		// //Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
		String sql = "DELETE FROM teacher WHERE t_id in" + (t_id) + "";
		// �������ݿ�������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		return st.executeUpdate(sql);
	}
	
	
	/**
	 * ���ݽ�ʦ�����ֶ��޸����ݱ�Ľ�ʦ����
	 * 
	 * @author zzt
	 * @param t_id
	 *            ����ʦid
	 * @return re
	 * @throws SQLException 
	 */
	
	
	public static int changepassword(Connection con,View_teacher teacher) throws SQLException {
		Statement statement;
		if (con == null || con.isClosed())
			return -1;
		int re = -1;
		try {
			statement = con.createStatement();
			// Ҫִ�е�SQL���
			String sql = "UPDATE teacher SET t_password='"+teacher.getT_password()+"'WHERE t_id='"+teacher.getT_id()+"'";
			// 3.ResultSet�࣬������Ż�ȡ�Ľ��������
			re = statement.executeUpdate(sql);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	}
	
	
	/**
	 * ���ݽ�ʦid�޸����ݱ������
	 * 
	 * @author zzt
	 * @param t_id
	 *            ����ʦid
	 * @return r
	 */
	
	
	// ����һ����̬���ε��޸ķ���
	public static int update(Connection con, String t_id, Teacher tc)
			throws SQLException {
		int r = -1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// ����sql������ڲ��벿����Ϣ
		sql = "update teacher set t_id='" + tc.getT_id() + "',t_name='"
				+ tc.getT_name() + "',t_power='" + tc.getT_power() + "',o_id='" + tc.getO_id() + "',t_tel='" + tc.getT_tel() + "' where t_id='" + t_id + "'";
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬���ز����¼��
		r = st.executeUpdate(sql);
		return r;
	}
	
	
	/**
	 * ���ݽ�ʦid�������ݱ������
	 * 
	 * @author zzt
	 * @param t_id
	 *            ����ʦid
	 * @return return st.executeUpdate(sql);
	 */
	
	
	// ����һ����̬���β���ķ���
	public static int insert(Connection con, Teacher tc)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ����sql������ڲ��벿����Ϣ
		sql = "INSERT INTO teacher (t_id,t_name,t_password,t_power,o_id,t_tel) VALUES ('" + tc.getT_id()
				+ "', '" + tc.getT_name() + "', '" + tc.getT_password() + "','" + tc.getT_power() + "','" + tc.getO_id() + "','" + tc.getT_tel() + "')";
		// ��������ִ��SQL��䣬���ز����¼��
		return st.executeUpdate(sql);
	}
	
	
	/**
	 * ���ݽ�ʦ��Ų������ݱ�����ֵ
	 * 
	 * @author zzt
	 * @param t_id
	 *            ����ʦid
	 * @return return st.executeUpdate(sql);
	 */
	
	public static ResultSet findmax(Connection conn){
		//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
		String sql = "select max(t_id) as t_id,max(t_password) as t_password from teacher";
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
	
	
	/**
	 * ���ݽ�ʦidɾ�����ݱ������
	 * 
	 * @author zzt
	 * @param t_password
	 *            ����ʦ����
	 * @return rs
	 */
	public static ResultSet maxpasswrod(Connection conn){
		//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
		String sql = "select max(t_password) as t_password from teacher";
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
}
