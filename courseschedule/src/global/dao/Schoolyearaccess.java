package global.dao;
import global.model.Schoolyear;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * ѧ����Ϣ���ݹ�����
 * 
 * @author czy
 * 
 */
public class Schoolyearaccess {
	/**
	 * ȡ��ѧ�����ݿ�����
	 * 
	 * @author czy
	 * @param condition
	 *            :��ѯ������
	 * @return ��Schoolyear����������б�
	 * 
	 */
	public static ArrayList<Schoolyear> getSchoolyear(String condition) {
		// ���ɲ��ҡ�Schoolyear�����select��ѯ���
		String sql = "SELECT * FROM schoolyear";
		// ������ݵ�������Ϊ�գ��������������sql���
		if (!(condition.equals("")))
			sql += " WHERE " + condition;
		sql += " order by sy_name";
		// ��ʼ����Schoolyear����������б����
		ArrayList<Schoolyear> Schoolyearlist = new ArrayList<Schoolyear>();
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
				int sy_id = rs.getInt("sy_id");
				String sy_name = rs.getString("sy_name");
				// ���ݽ�������������ɡ�Schoolyear�������
				Schoolyear m = new Schoolyear(sy_id, sy_name);
				// ����Schoolyear���������ӵ���Schoolyear����������б������
				Schoolyearlist.add(m);
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
		return Schoolyearlist;
	}

	/**
	 * ����ѧ�����ݵ����ݿ���
	 * 
	 * @param sy_name
	 *            ��ѧ����
	 * @return������ļ�¼����
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insertSchoolyear(Connection con, String sy_name)
			throws ClassNotFoundException, SQLException {
		// ���ɲ��롰Schoolyear�����sql���
		String sql = "INSERT INTO schoolyear (sy_name) VALUES ('" + sy_name
				+ "')";
		// ������ݿ������Ϊ�գ��򷵻ؿ�
		if (con == null)
			return -1;
		// �������ݿ���������
		Statement st = con.createStatement();
		// ��������ִ��SQL��䣬����Ӱ��ļ�¼��
		return st.executeUpdate(sql);
	}
	
	
	public static boolean findid(Connection conn,String sy_id) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
		String sql = "SELECT * FROM schoolyear where sy_id='"+sy_id+"'";
		// �������ݿ���������
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		//���ر���rs�Ľ�������м�¼
		return rs.next();
}

	//public static ResultSet findmax(Connection conn) throws SQLException{
	//	//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
	//	String sql = "select max(d_id)  from department";
	//	// �������ݿ���������
	//	Statement st= conn.createStatement();
	//	// ��������ִ��SQL��䣬�������������Ľ����
	//	ResultSet rs = st.executeQuery(sql);
	//	//���ر���rs�Ľ�������м�¼
	//	return rs;
	//}


//����һ����̬�������͵Ĳ�ѯ����
public static boolean findname(Connection conn,String sy_name) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_name���������ƣ�
		String sql = "SELECT * FROM schoolyear where sy_name='"+sy_name+"'";
		//�������ݿ���������
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		//���ر���rs�Ľ�������м�¼
		return rs.next();
}
//����һ����̬����ɾ���ķ���
public static int Delete(Connection con, String sy_id)
		throws SQLException {
	// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
	if (con == null || con.isClosed())
		return -1;
	//���d_id�����ű�ţ�Ϊ�յĻ����򷵻�0
	if (sy_id.equals(""))
		return 0;
	////Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
	String sql = "DELETE FROM schoolyear WHERE sy_id in"+(sy_id)+"";
	//�������ݿ�������
	Statement st = con.createStatement();
	// ��������ִ��SQL��䣬�������������Ľ����
	return st.executeUpdate(sql);
}

//����һ����̬���ε��޸ķ���
public static int update(Connection con,String sy_id, Schoolyear sy)
		throws SQLException {
	int r=-1;
	// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
	if (con == null || con.isClosed())
		return  -1;
	String sql;
	// ����sql������ڲ��벿����Ϣ
    sql = "update schoolyear set sy_id='"+sy.getSy_id()+"',sy_name='"+sy.getSy_name()+"' where sy_id='"+ sy_id+"'";
 // �������ݿ���������
 	Statement st = con.createStatement();
 // ��������ִ��SQL��䣬���ز����¼��
	r = st.executeUpdate(sql);
	return r;
}

//����һ����̬���β���ķ���
public static int insert(Connection con, Schoolyear sy)
		throws ClassNotFoundException, SQLException {
	// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
	if (con == null || con.isClosed())
		return  -1;
	String sql;
	// �������ݿ���������
	Statement st = con.createStatement();
	// ����sql������ڲ��벿����Ϣ
    sql = "INSERT INTO schoolyear (sy_id, sy_name) VALUES ('"+sy.getSy_id()+"', '"+sy.getSy_name()+"')";
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
