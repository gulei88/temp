package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login_access {
	/*
	 * 判断登陆情况
	 * 
	 */
	public static int T_login(Connection con,String t_id,String t_passwd) throws SQLException {
		int re=0;//用来判断登陆情况
		String sql="select t_password from teacher";//存储查询语句
		if (t_id!=null&&!t_id.equals("")) {//如果账号字段不为空则添加条件
			sql+=" where t_id='"+t_id+"'";
		}
		ResultSet resultSet = null;
		try {
			Statement statement=con.createStatement();//用来调用查询语句
			resultSet=statement.executeQuery(sql);//进行查询
			if (resultSet.next()) {//判断查询结果第一条
				String pasString=resultSet.getString("t_password");//取第一条结果t_password字段
				if (pasString.equals(t_passwd)) {//判断输入密码与数据库密码是否相等，1为正确，2为密码错误，-1为账号错误
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
