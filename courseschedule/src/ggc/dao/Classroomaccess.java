package ggc.dao;

import global.dao.Databaseconnection;
import global.model.Classroom;
import global.model.Level;
import global.model.View_classroom;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Classroomaccess {
	/**
	 * @author czy
	 * @param o_id
	 *            :���ұ��
	 * @return ��View_classroom����������б�
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<View_classroom> getView_classroom(String condition) {
		// ���ɲ��ҡ�View_classroom�����select��ѯ���
		String sql = "SELECT * FROM view_classroom";
		// ��������������Ϊ�գ���SQL�����Ӳ�������Ϊ�����������ҡ�View_classroom����ͼ����
		if (!(condition == null || condition.equals(""))) {
			sql += " WHERE " + condition;
		}
		// ��ʼ����View_classroom����������б����
		ArrayList<View_classroom> View_classroomlist = new ArrayList<View_classroom>();
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
				int b_id = rs.getInt("b_id");
				String b_name = rs.getString("b_name");
				String b_alias = rs.getString("b_alias");
				String b_address = rs.getString("b_address");
				int cr_id = rs.getInt("cr_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				String ct_name = rs.getString("ct_name");
				int seating = rs.getInt("seating");
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				String sl_name = rs.getString("sl_name");
				// ���ݽ�������������ɡ�View_classroom�������
				View_classroom m = new View_classroom(d_id, d_name, cr_id,
						cr_name, sl_name, seating, ct_id, ct_name, b_id,
						b_name, b_alias, b_address);
				// ����View_classroom���������ӵ���View_classroom����������б������
				View_classroomlist.add(m);
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
		return View_classroomlist;
	}
	
	
	
	public static ArrayList<Classroom> getClassroom(int cr_id) {
		// ���ɲ��ҡ�Level�����select��ѯ���
		String sql = "SELECT * FROM classroom";
		// �������Ŀ��ұ�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�Level����ͼ����
		if (!(cr_id == 0)) {
			sql += " WHERE cr_id='" + cr_id + "'";
		}
		// ��ʼ����Major����������б����
		ArrayList<Classroom> levellist = new ArrayList<Classroom>();
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
				cr_id = rs.getInt("cr_id");
				String d_id = rs.getString("d_id");
				String cr_name = rs.getString("cr_name");
				int ct_id = rs.getInt("ct_id");
				int cr_seating = rs.getInt("cr_seating");
				int b_id = rs.getInt("b_id");
				// ���ݽ�������������ɡ�Level�������
				Classroom m = new Classroom(cr_id, d_id,cr_name,ct_id,cr_seating,b_id);
				// ����Level���������ӵ���Level����������б������
				levellist.add(m);
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
		// ���ء�levellist����������б����
		return levellist;
	}
	//����һ����̬�������͵Ĳ�ѯ����
		public static boolean findid(Connection conn,int cr_id) throws SQLException{
				//Ҫִ�е�SQL����Ǹ�����������cr_id�����ұ�ţ�
				String sql = "SELECT * FROM classroom where cr_id="+cr_id+"";
				// �������ݿ���������
				Statement st= conn.createStatement();
				// ��������ִ��SQL��䣬�������������Ľ����
				ResultSet rs = st.executeQuery(sql);
				//���ر���rs�Ľ�������м�¼
				return rs.next();
		}
		
		public static boolean find(Connection conn) throws SQLException{
			//Ҫִ�е�SQL����Ǹ�����������cr_id�����ұ�ţ�
			String sql = "select count(*) as cr_id from classroom";
			// �������ݿ���������s
			ArrayList<Classroom> count = new ArrayList<Classroom>();
			Statement st= conn.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			ResultSet rs = st.executeQuery(sql);
			//���ر���rs�Ľ�������м�¼
			return rs.next();
		}
						
		public static ResultSet findmax(Connection conn){
			//Ҫִ�е�SQL�����Ҫ��ѯ���ұ�ŵ����ֵ
			String sql = "select max(cr_id) as cr_id from classroom";
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
			
		//����һ����̬�������͵Ĳ�ѯ����
		public static boolean findname(Connection conn,String cr_name) throws SQLException{
				//Ҫִ�е�SQL����Ǹ�����������cr_name���������ƣ�
				String sql = "SELECT * FROM classroom where cr_name='"+cr_name+"'";
				//�������ݿ���������
				Statement st= conn.createStatement();
				// ��������ִ��SQL��䣬�������������Ľ����
				ResultSet rs = st.executeQuery(sql);
				//���ر���rs�Ľ�������м�¼
				return rs.next();
	}
		//����һ����̬�������͵Ĳ�ѯ����
				public static boolean findaddress(Connection conn,int cr_seating) throws SQLException{
						//Ҫִ�е�SQL����Ǹ�����������cr_seating��������λ��
						String sql = "SELECT * FROM classroom where cr_seating='"+cr_seating+"'";
						//�������ݿ���������
						Statement st= conn.createStatement();
						// ��������ִ��SQL��䣬�������������Ľ����
						ResultSet rs = st.executeQuery(sql);
						//���ر���rs�Ľ�������м�¼
						return rs.next();
			}
		//����һ����̬����ɾ���ķ���
		public static int Delete(Connection con, String ggc)
				throws SQLException {
			// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
			if (con == null || con.isClosed())
				return -1;
			//���cr_id�����ұ�ţ�Ϊ�յĻ����򷵻�0
			if (ggc.equals(""))
				return 0;
			////Ҫִ�е�SQL����Ǹ�������ɾ��cr_id(���ұ��)
			String sql = "DELETE FROM classroom WHERE cr_id in"+(ggc)+"";
			//�������ݿ�������
			Statement st = con.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			return st.executeUpdate(sql);
		}
		
		//����һ����̬���ε��޸ķ���
		public static int update(Connection con,int cr_id,Classroom cr)
				throws SQLException {
			int r=-1;
			// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
			if (con == null || con.isClosed())
				return  -1;
			String sql;
			// ����sql��������޸Ľ�����Ϣ
			 sql = "UPDATE classroom SET cr_id='"+cr.getCr_id()+"', d_id='"+cr.getD_id()+"', cr_name='"+cr.getCr_name()+"', ct_id='"+cr.getCt_id()+"', cr_seating='"+cr.getCr_seating()+"', b_id='"+cr.getB_id()+"' WHERE `cr_id`='"+cr_id+"'";
			
		 // �������ݿ���������
		 	Statement st = con.createStatement();
		 // ��������ִ��SQL��䣬�����޸ļ�¼��
			r = st.executeUpdate(sql);
			return r;
		}
		
		//����һ����̬���β���ķ���
		public static int insert(Connection con, Classroom cr)
				throws ClassNotFoundException, SQLException {
			// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
			if (con == null || con.isClosed())
				return  -1;
			String sql;
			// �������ݿ���������
			Statement st = con.createStatement();
			// ����sql������ڲ��������Ϣ
//		    sql = "INSERT INTO building (b_id, b_name, b_alias, b_address) VALUES ('"+bu.getB_id()+"', '"+bu.getB_name()+"', '"+bu.getB_alias()+"', '"+bu.getB_address()+"')";
		    sql = "INSERT INTO classroom (cr_id, d_id, cr_name, ct_id, cr_seating, b_id) VALUES ('"+cr.getCr_id()+"', '"+cr.getD_id()+"', '"+cr.getCr_name()+"', '"+cr.getCt_id()+"', '"+cr.getCr_seating()+"', '"+cr.getB_id()+"')";
			// ��������ִ��SQL��䣬���ز����¼��
			return st.executeUpdate(sql);
		}
}

