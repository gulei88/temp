package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login_access {
	/*
	 * �жϵ�½���
	 * 
	 */
	public static int T_login(Connection con,String t_id,String t_passwd) throws SQLException {
		int re=0;//�����жϵ�½���
		String sql="select t_password from teacher";//�洢��ѯ���
		if (t_id!=null&&!t_id.equals("")) {//����˺��ֶβ�Ϊ�����������
			sql+=" where t_id='"+t_id+"'";
		}
		ResultSet resultSet = null;
		try {
			Statement statement=con.createStatement();//�������ò�ѯ���
			resultSet=statement.executeQuery(sql);//���в�ѯ
			if (resultSet.next()) {//�жϲ�ѯ�����һ��
				String pasString=resultSet.getString("t_password");//ȡ��һ�����t_password�ֶ�
				if (pasString.equals(t_passwd)) {//�ж��������������ݿ������Ƿ���ȣ�1Ϊ��ȷ��2Ϊ�������-1Ϊ�˺Ŵ���
					re=1;
				}else re=2;
			}else re=-1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			resultSet.close();
			con.close();
		}
		return re;
	}
}
