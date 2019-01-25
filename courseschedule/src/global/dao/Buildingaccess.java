package global.dao;

import global.model.Buliding;
import global.model.Major;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * ��ѧ¥���ݹ�����
 * 
 * @author ggc
 * 
 */
public class Buildingaccess {
	private static ResultSet rs;
	private static int b_id;

	/**
	 * @author ggc
	 * @param b_id
	 *            :��ѧ¥���
	 * @return ��building����������б�
	 * @throws SQLException
	 * 
	 */
	public static ArrayList<Buliding> getbuliding(int b_id) {
		// ���ɲ��ҡ�buliding�����select��ѯ���
		String sql = "SELECT * FROM building";
		// �������Ľ�ѧ¥��Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݿ��ұ�Ų��ҡ�buliding����ͼ����
		if (!(b_id ==0)) {
			sql += " WHERE b_id='" + b_id + "'";
		}
		// ��ʼ����building����������б����
		ArrayList<Buliding> bulidinglist = new ArrayList<Buliding>();
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
				b_id = rs.getInt("b_id");
				String b_name = rs.getString("b_name");
				String b_alias = rs.getString("b_alias");
				String b_address = rs.getString("b_address");
				// ���ݽ�������������ɡ�Major�������
				Buliding b = new Buliding(b_id, b_name, b_alias, b_address);
				// ����Major���������ӵ���Major����������б������
				bulidinglist.add(b);
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
		return bulidinglist;
	}
	//����һ����̬�������͵Ĳ�ѯ����
		public static boolean findid(Connection conn,int b_id) throws SQLException{
				//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
				String sql = "SELECT * FROM building where b_id="+b_id+"";
				// �������ݿ���������
				Statement st= conn.createStatement();
				// ��������ִ��SQL��䣬�������������Ľ����
				ResultSet rs = st.executeQuery(sql);
				//���ر���rs�Ľ�������м�¼
				return rs.next();
		}
		
		public static boolean find(Connection conn) throws SQLException{
			//Ҫִ�е�SQL����Ǹ�����������b_id����ѧ¥��ţ�
			String sql = "select count(*) as b_id from building";
			// �������ݿ���������s
			ArrayList<Buliding> count = new ArrayList<Buliding>();
			Statement st= conn.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			ResultSet rs = st.executeQuery(sql);
			//���ر���rs�Ľ�������м�¼
			return rs.next();
		}
		
//		public static ResultSet count(Connection conn){
//			//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
//			String sql = "SELECT * FROM classsroomtyoe where ct_id";
//			// �������ݿ���������
//			// ��������ִ��SQL��䣬�������������Ľ����
//			ResultSet rs=null;
//			try {
//				Statement st= conn.createStatement();
//				rs = st.executeQuery(sql);
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			System.out.println(rs);
//			//���ر���rs�Ľ�������м�¼
//			return rs;
//	}
//		
		
		
		public static ResultSet findmax(Connection conn){
			//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
			String sql = "select max(b_id) as b_id from building";
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
		public static boolean findname(Connection conn,String b_name) throws SQLException{
				//Ҫִ�е�SQL����Ǹ�����������b_name����ѧ¥���ƣ�
				String sql = "SELECT * FROM building where b_name='"+b_name+"'";
				//�������ݿ���������
				Statement st= conn.createStatement();
				// ��������ִ��SQL��䣬�������������Ľ����
				ResultSet rs = st.executeQuery(sql);
				//���ر���rs�Ľ�������м�¼
				return rs.next();
	}
		//����һ����̬�������͵Ĳ�ѯ����
				public static boolean findaddress(Connection conn,String b_address) throws SQLException{
						//Ҫִ�е�SQL����Ǹ�����������b_name����ѧ¥���ƣ�
						String sql = "SELECT * FROM building where b_address='"+b_address+"'";
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
			//���b_id����ѧ¥��ţ�Ϊ�յĻ����򷵻�0
			if (ggc.equals(""))
				return 0;
			////Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
			String sql = "DELETE FROM building WHERE b_id in"+(ggc)+"";
			//�������ݿ�������
			Statement st = con.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			return st.executeUpdate(sql);
		}
		
		//����һ����̬���ε��޸ķ���
		public static int update(Connection con,int b_id, Buliding bu)
				throws SQLException {
			int r=-1;
			// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
			if (con == null || con.isClosed())
				return  -1;
			String sql;
			// ����sql������ڲ����ѧ¥��Ϣ
		    sql = "update  building set b_id='"+bu.getB_id()+"',b_name='"+bu.getB_name()+"',b_alias='"+bu.getB_alias()+"',b_address='"+bu.getB_address()+"' where b_id='"+ b_id+"'";
		 // �������ݿ���������
		 	Statement st = con.createStatement();
		 // ��������ִ��SQL��䣬���ز����¼��
			r = st.executeUpdate(sql);
			return r;
		}
		
		//����һ����̬���β���ķ���
		public static int insert(Connection con, Buliding bu)
				throws ClassNotFoundException, SQLException {
			// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
			if (con == null || con.isClosed())
				return  -1;
			String sql;
			// �������ݿ���������
			Statement st = con.createStatement();
			// ����sql������ڲ����ѧ¥��Ϣ
		    sql = "INSERT INTO building (b_id, b_name, b_alias, b_address) VALUES ('"+bu.getB_id()+"', '"+bu.getB_name()+"', '"+bu.getB_alias()+"', '"+bu.getB_address()+"')";
			// ��������ִ��SQL��䣬���ز����¼��
			return st.executeUpdate(sql);
		}
}
