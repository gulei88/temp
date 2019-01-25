package zzt.view;

import global.dao.Databaseconnection;
import global.model.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ccc {
	public static ArrayList<Department> getModel(String sql,String d_id) {
		// 生成查找“Department”表的select查询语句
		sql = "SELECT * FROM department";
		// 如果传入的部门编号不为空，则SQL语句添加查找条件为根据部门编号查找“Department”视图数据
		if (!(d_id == null || d_id.equals(""))) {
			sql += " WHERE d_id='" + d_id + "'";
		}
		// 初始化“Department”类的数组列表对象
		ArrayList<Department> departmentlist = new ArrayList<Department>();
		// 取得数据库的连接
		Connection con = null;
		ResultSet rs = null;
		try {
			con = Databaseconnection.getconnection();
			// 如果数据库的连接为空，则返回空
			if (con == null)
				return null;
			// 生成数据库声明对象
			Statement st = con.createStatement();
			// 声明对象执行SQL语句，返回满足条件的结果集
			rs = st.executeQuery(sql);
			// 如果结果集有数据
			while (rs.next()) {
				// 取出结果集对应字段数据
				d_id = rs.getString("d_id");
				String d_name = rs.getString("d_name");
				// 根据结果集的数据生成“Department”类对象
				Department dm = new Department(d_id, d_name);
				// 将“Department”类对象添加到“Department”类的数组列表对象中
				departmentlist.add(dm);
			}
			// 返回“Department”类的数组列表对象
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

}
