package global.dao;
 
import java.sql.*;
import global.model.Department;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.Spring;
/**
 * @author liyinqiang01
 * 数据库操作类
 */
public class Sql_fz {
	public static void deleteStudents(String tab,String condition,String argument) throws ClassNotFoundException, SQLException{
		Connection con = Databaseconnection.getconnection();
		if (!con.isClosed()){
		System.out.println("Succeeded connecting to the Database!");
		Statement statement = con.createStatement();
		String sql ="delete from test where condition = '"+argument+"'";
		boolean rs = statement.execute(sql);
		con.close();
		}
	}
}
