package zzt.dao;

import global.dao.Databaseconnection;
import global.model.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import zzt.model.Zzt_Teacher_login;

/**
 * 
 * @author zzt
 * @param t_id
 * 			  :��ʦ���
 */
public class Zzt_login_access {
	public static int T_login(String conn,String t_id,String t_password){
		int re = 0;
		String sql="select t_password from teacher";
		if (!(t_id == null||t_id.equals("") )) {
			sql += " WHERE t_id='" + t_id + "'";
		}
		Connection con = null;
		try {
			con = Databaseconnection.getconnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ������ݿ������Ϊ�գ��򷵻ؿ�
		if (con == null)
			return (Integer) null;
		// �������ݿ���������
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				String pas=rs.getString("t_password");
				if(pas.equals(t_password)){
					re=1;
				}else{
					re=2;
				}
			}else{
				re=-1;
				rs.close();
				con.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��������ִ��SQL��䣬�������������Ľ����
		return re;
	}
}
