package global.dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import global.model.Classinformation;
import global.model.Department;
import global.model.View_classinformation;

/**
 * �༶���ݹ�����
 * 
 * @author Administrator
 * 
 */
public class Classinformationtwoaccess {
	private static String d_id;
	private static String o_id;
	private static String m_id;
	private static int l_id;

	/**
	 * ��������ȡ��View_classinformation��ͼ������
	 * 
	 * @author czy
	 * @param condition
	 *            ����ѯ����
	 * @return ����������View_classinformation��������б�
	 * 
	 */
	public static ArrayList<View_classinformation> getView_classinformation(
			int condition) {
		// ���ɲ��ҡ�View_classinformation����ͼ��select��ѯ���
		String sql = "SELECT * FROM View_classinformation";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!(condition == 0))
			sql += " WHERE l_id =" + condition;
		// ��ʼ����View_classinformation����������б����
		ArrayList<View_classinformation> View_classinformationlist = new ArrayList<View_classinformation>();
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
//			ResultSet tg_grade = Classinformationtwoaccess.findgrade(cla_grade);
			while (rs.next()) {
				// ȡ���������Ӧ�ֶ�����
				String d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				String o_id = rs.getString("o_id");
				String o_name = rs.getString("o_name");
				String m_id = rs.getString("m_id");
				String m_name = rs.getString("m_name");
				String cla_id = rs.getString("cla_id");
				String cla_name = rs.getString("cla_name");
//				ResultSet tg_grade = Classinformationtwoaccess.findgrade(cla_grade);
				String cla_grade = rs.getString("cla_grade");
				int l_id = rs.getInt("l_id");
				String l_name = rs.getString("l_name");
				int cla_number = rs.getInt("cla_number");
				// ���ݽ�������������ɡ�View_classinformation�������
				View_classinformation vc = new View_classinformation(d_id,
						d_name, o_id, o_name, m_id, m_name, cla_id, cla_name,
						cla_grade, l_id, l_name, cla_number);
				// ����View_classinformation���������ӵ���View_classinformation����������б������
				View_classinformationlist.add(vc);
			}
			// ���ء�View_classinformation����������б����
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
		return View_classinformationlist;
	}
	


	
	
	
	
	public static ArrayList<View_classinformation> getView_classinformationgrade(
			String condition) {
		// ���ɲ��ҡ�View_classinformation����ͼ��select��ѯ���
		String sql = "select cla_grade,max(cla_grade) from view_classinformation ";
		// �������������ַ�����Ϊ�գ��������������sql���
		if (!(condition.equals("")))
			sql += " WHERE m_id='" + condition +"'group by cla_grade" + "";
//		 select cla_grade,max(cla_grade) from view_classinformation group by cla_grade
		// ��ʼ����View_classinformation����������б����
		ArrayList<View_classinformation> View_classinformationlist = new ArrayList<View_classinformation>();
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
				String d_id = null;
				String d_name= null;
				String o_id= null; 
				String o_name= null;
				String m_id= null;
				String m_name= null;
				String cla_id= null;
				String cla_name= null;
				String cla_grade = rs.getString("cla_grade");
				int l_id=0;
				String l_name= null;
				int cla_number= 0;;
				// ���ݽ�������������ɡ�View_classinformation�������
				View_classinformation vc = new View_classinformation(d_id,
						d_name, o_id, o_name, m_id, m_name, cla_id, cla_name,
						cla_grade, l_id, l_name, cla_number);
				// ����View_classinformation���������ӵ���View_classinformation����������б������
				View_classinformationlist.add(vc);
//				System.out.println(vc);
			}
			// ���ء�View_classinformation����������б����
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
//		System.out.println(View_classinformationlist);
		return View_classinformationlist;
	}
	
	
	
public static boolean findid(Connection conn,String cla_id) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
		String sql = "SELECT * FROM classinformation where cla_id='"+cla_id+"'";
		// �������ݿ���������
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		//���ر���rs�Ľ�������м�¼
		return rs.next();
}

public static boolean find(Connection conn) throws SQLException{
	//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
	String sql = "select count(*) as cla_id from classinformation";
	// �������ݿ���������s
	ArrayList<Classinformation> count = new ArrayList<Classinformation>();
	Statement st= conn.createStatement();
	// ��������ִ��SQL��䣬�������������Ľ����
	// ��������ִ��SQL��䣬�������������Ľ����
	ResultSet rs = st.executeQuery(sql);
	//���ر���rs�Ľ�������м�¼
	return rs.next();
}

public static ResultSet count(Connection conn){
	//Ҫִ�е�SQL����Ǹ�����������d_id�����ű�ţ�
	String sql = "SELECT * FROM classinformation where cla_id";
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
//	System.out.println(rs);
	//���ر���rs�Ľ�������м�¼
	return rs;
}


public static ResultSet findgrade(Connection conn){
	//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
	String sql = "select cla_grade,max(cla_grade) from view_classinformation group by cla_grade";
	ResultSet rs=null;
	try {
		Statement st = conn.createStatement();
		rs = st.executeQuery(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return rs;
}

public static ResultSet findmax(Connection conn){
	//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
	String sql = "select max(cla_id) as cla_id from classinformation";
//	if ((d_id == null || d_id.equals(""))) {
//		sql += " WHERE d_id='" + "01" + "'";
//	}
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



public static boolean findmaxtwo(Connection conn) throws SQLException{
	//Ҫִ�е�SQL�����Ҫ��ѯ���ű�ŵ����ֵ
	String sql = "select max(cla_id) as cla_id from classinformation";
	Statement st= conn.createStatement();
	// ��������ִ��SQL��䣬�������������Ľ����
	ResultSet rs = st.executeQuery(sql);
	return rs.next();
}


//����һ����̬�������͵Ĳ�ѯ����
public static boolean findname(Connection conn,String cla_name) throws SQLException{
		//Ҫִ�е�SQL����Ǹ�����������d_name���������ƣ�
		String sql = "SELECT * FROM classinformation where cla_name='"+cla_name+"'";
		//�������ݿ���������
		Statement st= conn.createStatement();
		// ��������ִ��SQL��䣬�������������Ľ����
		ResultSet rs = st.executeQuery(sql);
		//���ر���rs�Ľ�������м�¼
		return rs.next();
}
//����һ����̬����ɾ���ķ���
public static int Delete(Connection con, String cla_id)
		throws SQLException {
	// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
	if (con == null || con.isClosed())
		return -1;
	//���d_id�����ű�ţ�Ϊ�յĻ����򷵻�0
	if (cla_id.equals(""))
		return 0;
	////Ҫִ�е�SQL����Ǹ�������ɾ��d_id(���ű��)
	String sql = "DELETE FROM classinformation WHERE cla_id in"+(cla_id)+"";
	//�������ݿ�������
	Statement st = con.createStatement();
	// ��������ִ��SQL��䣬�������������Ľ����
	return st.executeUpdate(sql);
}

//����һ����̬���ε��޸ķ���
public static int update(Connection con,String cla_id, Classinformation cf)
		throws SQLException {
	int r=-1;
	// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
	if (con == null || con.isClosed())
		return  -1;
	String sql;
	// ����sql������ڲ��벿����Ϣ
    sql = "update classinformation set cla_id='"+cf.getCla_id()+"',m_id='"+cf.getM_id()+"',cla_name='"+cf.getCla_name()+"',cla_grade='"+cf.getCla_grade()+"',l_id='"+cf.getL_id()+"',cla_number='"+cf.getCla_number()+"'where cla_id='"+ cla_id+"'";
 // �������ݿ���������
 	Statement st = con.createStatement();
 // ��������ִ��SQL��䣬���ز����¼��
	r = st.executeUpdate(sql);
	return r;
}

//����һ����̬���β���ķ���
public static int insert(Connection con, Classinformation cf)
		throws ClassNotFoundException, SQLException {
	// ������ݿ������Ϊ�ջ��ѹرգ��򷵻ؿ�
	if (con == null || con.isClosed())
		return  -1;
	String sql;
	// �������ݿ���������
	Statement st = con.createStatement();
	// ����sql������ڲ��벿����Ϣ
    sql = "INSERT INTO department (cla_id, m_id,cla_name,cla_grade,l_id,cla_number) VALUES ('"+cf.getCla_id()+"', '"+cf.getM_id()+"','"+cf.getCla_name()+"','"+cf.getCla_id()+"','"+cf.getCla_number()+"')";
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

