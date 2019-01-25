package global.dao;

import global.model.Department;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * ���������ݷ�����
 * 
 * @author czy
 * 
 */
public class Departmentaccess {
	private static ResultSet rs;
	private static String d_id;
	
	
	/**
	 * @author czy
	 * @param d_id
	 *            :���ű��
	 * @return ��Department����������б�
	 */
	
	
	public static ArrayList<Department> getDepartment(String d_id) {
		// ���ɲ��ҡ�Department�����select��ѯ���
		String sql = "SELECT * FROM department";
		// �������Ĳ��ű�Ų�Ϊ�գ���SQL�����Ӳ�������Ϊ���ݲ��ű�Ų��ҡ�Department����ͼ����
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// ��ʼ����Department����������б����
		ArrayList<Department> departmentlist = new ArrayList<Department>();
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
				d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				// ���ݽ�������������ɡ�Department�������
				Department dm = new Department(d_id, d_name);
				// ����Department���������ӵ���Department����������б������
				departmentlist.add(dm);
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
		return departmentlist;
	}
	
	/**
	 * ���ݲ���id�������ݱ����Ƿ����������ݳ�ͻ
	 * 
	 * @author zzt
	 * @param d_id
	 *            :���ű��
	 * @return rs.next()
	 */
	
	
	//����һ����̬�������͵Ĳ�ѯ����
	public static boolean findid(Connection conn,String d_id) throws SQLException{
			//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
			String sql = "SELECT * FROM department where d_id='"+d_id+"'";
			// �������ݿ���������
			Statement st= conn.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			ResultSet rs = st.executeQuery(sql);
			//���ر���rs�Ľ�������м�¼
			return rs.next();
	}
	
	
	/**
	 * ���ݲ���id�������ݱ����Ƿ����������ݳ�ͻ
	 * 
	 * @author zzt
	 * @param d_id
	 *            :���ű��
	 * @return return rs.next()
	 */
	
	
	public static boolean find(Connection conn) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
		String sql = "select count(*) as d_id from department";
		// �������ݿ���������
		ArrayList<Department> count = new ArrayList<Department>();
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		//���ر���rs�Ľ�������м�¼
		return rs.next();
	}
	
	
	/**
	 * ���ݲ���id�������ݱ�
	 * 
	 * @author zzt
	 * @param d_id
	 *            :���ű��
	 * @return return rs.next()
	 */
	
	
	public static ResultSet count(Connection conn){
		//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
		String sql = "SELECT * FROM department where d_id";
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs=null;
		try {
			Statement st= conn.createStatement();
			rs = st.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * ���ݲ���id�������ݱ�������idֵ
	 * 
	 * @author zzt
	 * @param d_id
	 *            :���ű��
	 * @return return rs.next()
	 */
	
	
	
	public static ResultSet findmax(Connection conn){
		//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
		String sql = "select max(d_id) as d_id from department";
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
	 * ���ݲ���id�������ݱ�����ֵ
	 * 
	 * @author zzt
	 * @param  d_id
	 *            :���ű��
	 * @return return rs.next()
	 */
	
	public static boolean findmaxtwo(Connection conn) throws SQLException{
		//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
		String sql = "select max(d_id) as d_id from department";
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		return rs.next();
	}
	
	/**
	 * ���ݲ��������������ݱ����Ƿ����������ݳ�ͻ
	 * 
	 * @author zzt
	 * @param d_id
	 *            :���ű��
	 * @return return rs.next()
	 */
	
	
	
	//����һ����̬�������͵Ĳ�ѯ����
	public static boolean findname(Connection conn,String d_name) throws SQLException{
			//Ҫִ�е�SQL����Ǹ�����������d_name���������ƣ�
			String sql = "SELECT * FROM department where d_name='"+d_name+"'";
			//�������ݿ���������
			Statement st= conn.createStatement();
			// ��������ִ��SQL��䣬�������������Ľ����
			ResultSet rs = st.executeQuery(sql);
			//���ر���rs�Ľ�������м�¼
			return rs.next();
	}
	
	
	
	/**
	 * ���ݲ���idɾ�����ݱ��������
	 * 
	 * @author zzt
	 * @param d_id
	 *            :���ű��
	 * @return return rs.next()
	 */
	
	
	//����һ����̬����ɾ���ķ���
	public static int Delete(Connection con, String d_id)
			throws SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return -1;
		//���d_id�����ű�ţ�Ϊ�յĻ����򷵻�0
		if (d_id.equals(""))
			return 0;
		////Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
		String sql = "DELETE FROM department WHERE d_id in"+(d_id)+"";
		//�������ݿ�������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		return st.executeUpdate(sql);
	}
	
	
	/**
	 * ���ݲ���id�޸����ݱ��������
	 * 
	 * @author zzt
	 * @param d_id
	 *            :���ű��
	 * @return return rs.next()
	 */
	
	
	
	//����һ����̬���ε��޸ķ���
	public static int update(Connection con,String d_id, Department dt)
			throws SQLException {
		int r=-1;
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return  -1;
		String sql;
		// ����sql������ڲ��벿����Ϣ
	    sql = "update department set d_id='"+dt.getD_id()+"',d_name='"+dt.getD_name()+"' where d_id='"+ d_id+"'";
	    // �������ݿ���������
	 	Statement st = con.createStatement();
	 	// ��������ִ��SQL��䣬���ز����¼��
		r = st.executeUpdate(sql);
		return r;
	}
	
	
	/**
	 * �������ݱ�����
	 * 
	 * @author zzt
	 * @param d_id
	 *            :���ű��
	 * @return return rs.next()
	 */
	//����һ����̬���β���ķ���
	public static int insert(Connection con, Department dt)
			throws ClassNotFoundException, SQLException {
		// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
		if (con == null || con.isClosed())
			return  -1;
		String sql;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ����sql������ڲ��벿����Ϣ
	    sql = "INSERT INTO department (d_id, d_name) VALUES ('"+dt.getD_id()+"', '"+dt.getD_name()+"')";
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
