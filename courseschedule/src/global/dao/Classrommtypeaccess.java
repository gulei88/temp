package global.dao;

import global.model.Department;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import zzt.model.Classroomtype;

/**
 * ���������ݷ�����
 * 
 * @author czy
 * 
 */
public class Classrommtypeaccess {
	private static ResultSet rs;
	private static String ct_id;
	/**
	 * @author czy
	 * @param d_id
	 *            :���ű��
	 * @return ��Department����������б�
	 */
	public static ArrayList<Classroomtype> getClassroomtypes(int ct_id) {
		// ���ɲ��ҡ�Department�����select��ѯ���
		String sql = "SELECT * FROM classroomtype";
		// �������Ĳ��ű�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݲ��ű�Ų��ҡ�Department����ͼ����
		if (!(ct_id==0)) {
			sql += " WHERE ct_id=" + ct_id + "";
		}
		// ��ʼ����Department����������б����
		ArrayList<Classroomtype> Classroomtypelist = new ArrayList<Classroomtype>();
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
				ct_id = rs.getInt("ct_id");
				String ct_name = rs.getString("ct_name");
				// ���ݽ�������������ɡ�Department�������
				Classroomtype dm = new Classroomtype(ct_id, ct_name);
				// ����Department���������ӵ���Department����������б������
				Classroomtypelist.add(dm);
			}
			// ���ء�Department����������б����
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
		return Classroomtypelist;
	}
	//����һ����̬�������͵Ĳ�ѯ����
	public static boolean findid(Connection conn,int ct_id) throws SQLException{
			//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
			String sql = "SELECT * FROM classroomtype where ct_id="+ct_id+"";
			// �������ݿ���������
			Statement st= conn.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			ResultSet rs = st.executeQuery(sql);
			//���ر���rs�Ľ�������м�¼
			return rs.next();
	}
	
	public static boolean find(Connection conn) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
		String sql = "select count(*) as ct_id from classroomtype";
		// �������ݿ���������s
		ArrayList<Classroomtype> count = new ArrayList<Classroomtype>();
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		//���ر���rs�Ľ�������м�¼
		return rs.next();
	}
	
	public static ResultSet count(Connection conn){
		//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
		String sql = "SELECT * FROM classsroomtyoe where ct_id";
		// �������ݿ���������
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs=null;
		try {
			Statement st= conn.createStatement();
			rs = st.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(rs);
		//���ر���rs�Ľ�������м�¼
		return rs;
}
	
	
	
	public static ResultSet findmax(Connection conn){
		//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
		String sql = "select max(ct_id) as ct_id from classroomtype";
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
	public static boolean findname(Connection conn,String ct_name) throws SQLException{
			//Ҫִ�е�SQL����Ǹ�����������d_name���������ƣ�
			String sql = "SELECT * FROM classroomtype where ct_name='"+ct_name+"'";
			//�������ݿ���������
			Statement st= conn.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			ResultSet rs = st.executeQuery(sql);
			//���ر���rs�Ľ�������м�¼
			return rs.next();
}
	//����һ����̬����ɾ���ķ���
	public static int Delete(Connection con, String zzt)
			throws SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		//���d_id�����ű�ţ�Ϊ�յĻ����򷵻�0
		if (zzt.equals(""))
			return 0;
		////Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
		String sql = "DELETE FROM classroomtype WHERE ct_id in"+(zzt)+"";
		//�������ݿ�������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		return st.executeUpdate(sql);
	}
	
	//����һ����̬���ε��޸ķ���
	public static int update(Connection con,int ct_id, Classroomtype ct)
			throws SQLException {
		int r=-1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return  -1;
		String sql;
		// ����sql������ڲ��벿����Ϣ
	    sql = "update Classroomtype set ct_id='"+ct.getCt_id()+"',ct_name='"+ct.getCt_name()+"' where ct_id='"+ ct_id+"'";
	 // �������ݿ���������
	 	Statement st = con.createStatement();
	 // ��������ִ��SQL��䣬���ز����¼��
		r = st.executeUpdate(sql);
		return r;
	}
	
	//����һ����̬���β���ķ���
	public static int insert(Connection con, Classroomtype ct)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return  -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ����sql������ڲ��벿����Ϣ
	    sql = "INSERT INTO classroomtype (ct_id, ct_name) VALUES ('"+ct.getCt_id()+"', '"+ct.getCt_name()+"')";
		// ��������ִ��SQL��䣬���ز����¼��
		return st.executeUpdate(sql);
	}
	
	public static ResultSet findoffice(Connection con) throws SQLException{
		Statement statement;
		statement=con.createStatement();
		String sql="select * from office";
		ResultSet rs=statement.executeQuery(sql);
		return rs;
	}
	
	
}
