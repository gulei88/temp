package zxx.dao;

import global.dao.Buildingaccess;
import global.dao.Classroomaccess;
import global.dao.Databaseconnection;
import global.dao.Departmentaccess;
import global.dao.Majoraccess;
import global.model.Buliding;
import global.model.Classroom;
import global.model.Department;
import global.model.Major;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ClassroomAddTable {
	private static List<String> get_throw = new ArrayList<String>();
	public static void main(String[] args) {

		List<String> department_List = getDepartmentThrow("C:/Users/Administrator/Desktop/department1.xls");
		for (String item : department_List) {
			System.out.println(item);
		}
	}

	/**
	 * 从文件读取数据并调用添加临时表和判断格式
	 * 
	 * @param file
	 * @return
	 * 
	 */
	public static List<String> getDepartmentThrow(String file){

		Connection con = null;
		ClassroomAddTable classroomAddTable = new ClassroomAddTable();
		Classroom classroom = null;
		List<Classroom> list = null;
//		List<String> get_throw = null;

		try {
			con = Databaseconnection.getconnection();
			con.setAutoCommit(false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.close();
			} catch (Exception e1) {
				e1.printStackTrace();
				con.rollback();
			} finally {
				return null;
			}

		}

		// 创建tmp_department临时表
		create_department(con);
		get_throw = format_classroom(con);
		// 从excel获取字段数据
		list = classroomAddTable.readXls(file);
		List<Department> departments;
		ArrayList<Buliding> bulidings;
		for (int i = 0; i < list.size(); i++) {
			classroom = (Classroom) list.get(i);
			
			int cr_id =classroom.getCr_id();
			try {
				int num = add_tmp_classroom(con, cr_id,
						classroom.getD_id(),classroom.getCr_name(),classroom.getCt_id(),classroom.getCr_seating(),classroom.getB_id());
				String d_id = classroom.getD_id();
				int b_id = classroom.getB_id();
				bulidings = Buildingaccess.getbuliding(b_id);
				departments = Departmentaccess.getDepartment(d_id);
				if (departments.size() == 0) {
					get_throw.add(d_id+"部门编号外键匹配异常");
				}
				if (bulidings.size() == 0) {
					get_throw.add(b_id+"编号外键匹配异常");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				try {
					con.rollback();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

//			System.out.println(department.getD_id() + "        "
//					+ department.getD_name());

		}

		// 判断临时表内格式
		get_throw.addAll(format_classroom(con));

		if (get_throw.size() == 0) {
			// 没错误时，将数据添加到department表

			try {
			//	get_throw.add(add_department(con));
				if(add_classroom(con)==0)
					get_throw.add("导入到数据库出现问题");

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				con.commit();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				try {
					con.rollback();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return get_throw;
		}

		try {

			con.commit();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return get_throw;

	}




	/**
	 * 创建临时表
	 * 
	 * 
	 */

	private static void create_department(Connection con) {

		// 如果数据库的连接为空，则返回空
		if (con == null)
			return;
		String sql = "CREATE TEMPORARY TABLE tmp_classroom ("
				+ "cr_id int(20) NOT NULL,"
				+ "d_id varchar(20) NOT NULL,"
				+ "cr_name varchar(100) NOT NULL,"
				+ "ct_id int(20) NOT NULL,"
				+ "cr_seating int(20) NOT NULL,"
				+ "b_id int(20) NOT NULL)";

		Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// 声明对象执行SQL语句，返回影响的记录数目。

	}

	/**
	 * 添加数据到department表
	 * 
	 * 
	 */

	public static int add_classroom(Connection con) {
		//把查询到临时表的数据添加到tmp_departemt
		String sql = "insert into classroom select * from tmp_classroom";
		int n = 0;
		Statement st;
		try {
			st = con.createStatement();
			n = st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		// 声明对象执行SQL语句，返回影响的记录数目。
		if (n > 0) {
			// System.out.println("导入成功");
			return 1;
		} else {
			// System.out.println("导入失败");
			return 0;
		}
	}

	/**
	 * 添加excel数据到临时表中
	 * 
	 * 
	 */
	private static int add_tmp_classroom(Connection con, int cr_id,
			String d_id,String cr_name,int ct_id,int cr_seating,int b_id) {
		String sql = "INSERT INTO tmp_classroom (cr_id, d_id,cr_name,ct_id,cr_seating,b_id) VALUES (" + ""
		+ cr_id + "" + ",'" + d_id + "'," + cr_name + "," + ct_id + "," + cr_seating + "," + b_id + ")";
		// 生成数据库声明对象
		Statement st;
		int result = 0;
		try {
			st = con.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;
		// 声明对象执行SQL语句，返回影响的记录数目。

	}

	/**
	 * 读取文件内容
	 * 
	 * @return List<Department>对象
	 * 
	 */
	 List<Classroom> readXls(String file) {
		InputStream is;
		HSSFWorkbook hssfWorkbook = null;
		try {
			is = new FileInputStream(file);
			try {
				hssfWorkbook = new HSSFWorkbook(is);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Classroom classroom = null;
		List<Classroom> list = new ArrayList<Classroom>();

		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				classroom = new Classroom();
				// 循环列Cell
				HSSFCell cr_id = hssfRow.getCell(0);
				if (cr_id == null) {
					continue;
				}
				classroom.setCr_id(Integer.parseInt(getValue(cr_id).toString()));
				
				
				HSSFCell d_id = hssfRow.getCell(1);
				if (d_id == null) {
					continue;
				}
				
				classroom.setD_id(getValue(d_id));

				
				
				HSSFCell ct_id = hssfRow.getCell(3);
				if (ct_id == null) {
					continue;
				}
				classroom.setCt_id(Integer.parseInt(getValue(ct_id).toString()));
				HSSFCell cr_seating = hssfRow.getCell(4);
				if (cr_seating == null) {
					continue;
				}
				
				
				classroom.setCr_seating(Integer.parseInt(getValue(cr_seating).toString()));
				HSSFCell b_id = hssfRow.getCell(5);
				if (b_id == null) {
					continue;
				}
				classroom.setB_id(Integer.parseInt(getValue(b_id).toString()));
				
				HSSFCell xm = hssfRow.getCell(2);
				if (xm == null) {
					String null_str = "";
					classroom.setCr_name(null_str);
				} else
					classroom.setCr_name(getValue(xm));

				list.add(classroom);
//				System.out.println(classroom.getCr_id());
			}
		}
		return list;
	}

	private int Integer(String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 得到Excel表中的值
	 * 
	 * @param hssfCell
	 *            Excel中的每一个格子
	 * @return Excel中每一个格子中的值
	 */
	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			return String.valueOf(hssfCell.getColumnIndex());
		} else {
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	/**
	 * 格式判断
	 * 
	 * 
	 */

	private static List<String> format_classroom(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.判断 部门编号
		String sql_id = "select * from tmp_classroom where cr_id regexp '[^0-9{10}]'";

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				String cr_id = rs_id.getString("cr_id");
				String str = "部门编号：“" + cr_id + "”格式错误";
				throw_content.add(str);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// 2.判断 部门名称
		String sql = "select * from tmp_classroom where length(cr_name)>50";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				String cr_name = rs_name.getString("cr_name");
				String str = "教室名称：“" + cr_name + "”长度过长";
				throw_content.add(str);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//判断id长度
		String sql_id_channg = "select * from tmp_classroom where length(d_id)>2";

		ResultSet rs_id_chang;
		try {
			rs_id_chang = st.executeQuery(sql_id_channg);
			while (rs_id_chang.next()) {
				String d_id = rs_id_chang.getString("d_id");
				String str = "部门编号：“" + d_id + "”长度过长";
				throw_content.add(str);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// 3.判断 id重复
		String sql_check_id = "select * from classroom where cr_id in (select cr_id from tmp_classroom)";

		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				String cr_id = rs_check_id.getString("cr_id");
				String str = "教室编号：“" + cr_id + "”重复错误";
				throw_content.add(str);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// 4.判断 name重复

		String sql_check_name = "select * from classroom where cr_name in (select cr_name from tmp_classroom)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				String cr_name = rs_check_name.getString("cr_name");
				String str = "部门名称：“" + cr_name + "”重复错误";
				throw_content.add(str);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// 5.判断 name null

		String sql_name_null = "select * from tmp_classroom where cr_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				String cr_id = rs_name_null.getString("cr_id");
				String str = "教室编号：“" + cr_id + "”的教室名称为空了";
				throw_content.add(str);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// 6.判断id null
		String sql_id_null = "select * from tmp_classroom where d_id =''";
		ResultSet rs_id;
		try {
			rs_id = st.executeQuery(sql_id_null);
			while (rs_id.next()) {
				String d_id = rs_id.getString("d_id");
				String d_name = rs_id.getString("d_name");
				String str = "部门名称：“" + d_name + "”的部门编号为空了";
				throw_content.add(str);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return throw_content;

	}

}
