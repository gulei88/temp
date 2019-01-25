package zxx.dao;

import global.dao.Buildingaccess;
import global.dao.Classroomaccess;
import global.dao.Databaseconnection;
import global.dao.Departmentaccess;
import global.dao.Levelaccess;
import global.dao.Majoraccess;
import global.model.Buliding;
import global.model.Classinformation;
import global.model.Classroom;
import global.model.Department;
import global.model.Level;
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

public class ClassinformationAddTable {
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
		ClassinformationAddTable classinformationAddTable = new ClassinformationAddTable();
		Classinformation classinformation = null;
		List<Classinformation> list = null;
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
		create_classinformation(con);
		get_throw = format_classinformation(con);
		// 从excel获取字段数据
		list = classinformationAddTable.readXls(file);
		List<Major> majors;
		ArrayList<Level> levels;
		for (int i = 0; i < list.size(); i++) {
			classinformation = (Classinformation) list.get(i);
			
			String cla_id =classinformation.getCla_id();
			try {
				int num = add_tmp_classinformation(con, classinformation.getCla_id(),
						classinformation.getM_id(),classinformation.getCla_name(),classinformation.getCla_grade(),classinformation.getL_id(),classinformation.getCla_number());
				String m_id = classinformation.getM_id();
				String cla_name = classinformation.getCla_name();
				int l_id = classinformation.getL_id();
				majors = Majoraccess.getMajor(m_id);
				levels = Levelaccess.getLevels(l_id);
				if (majors.size() == 0) {
					get_throw.add(cla_name+"的专业编号外键匹配异常");
				}
				if (levels.size() == 0) {
					get_throw.add(cla_name+"层次编号外键匹配异常");
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
		get_throw.addAll(format_classinformation(con));

		if (get_throw.size() == 0) {
			// 没错误时，将数据添加到department表

			try {
			//	get_throw.add(add_department(con));
				if(add_classinfomation(con)==0)
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

	private static void create_classinformation(Connection con) {

		// 如果数据库的连接为空，则返回空
		if (con == null)
			return;
		String sql = "CREATE TEMPORARY TABLE tmp_classinformation ("
				+ "cla_id varchar(10) NOT NULL,"
				+ "m_id varchar(20) NOT NULL,"
				+ "cla_name varchar(100) NOT NULL,"
				+ "cla_grade varchar(20) NOT NULL,"
				+ "l_id int(20) NOT NULL,"
				+ "cla_number int(20) NOT NULL)";

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

	public static int add_classinfomation(Connection con) {
		//把查询到临时表的数据添加到tmp_departemt
		String sql = "insert into classinformation select * from tmp_classinformation";
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
	@SuppressWarnings("unused")
	private static int add_tmp_classinformation(Connection con, String cla_id,
			String m_id,String cla_name,String cla_grade,int l_id,int cla_number) {
		String sql = "INSERT INTO tmp_classinformation (cla_id, m_id,cla_name,cla_grade,l_id,cla_number) VALUES (" + "'"
		+ cla_id + "'" + ",'" + m_id + "','" + cla_name + "','" + cla_grade + "'," + l_id + "," + cla_number + ")";
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
	 List<Classinformation> readXls(String file) {
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

		Classinformation classinformation = null;
		List<Classinformation> list = new ArrayList<Classinformation>();

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
				classinformation = new Classinformation();
				// 循环列Cell
				HSSFCell cla_id = hssfRow.getCell(0);
				if (cla_id == null) {
					continue;
				}
				classinformation.setCla_id(getValue(cla_id));
				
				
				HSSFCell m_id = hssfRow.getCell(1);
				if (m_id == null) {
					continue;
				}
				
				classinformation.setM_id(getValue(m_id));
				
				
				
				HSSFCell cla_grade = hssfRow.getCell(3);
				if (cla_grade == null) {
					continue;
				}
				classinformation.setCla_grade(getValue(cla_grade));
				HSSFCell l_id = hssfRow.getCell(4);
				if (l_id == null) {
					continue;
				}
				
				classinformation.setL_id(Integer.parseInt(getValue(l_id).toString()));
				
				HSSFCell cla_number = hssfRow.getCell(5);
				if (cla_number == null) {
					continue;
				}
				classinformation.setCla_number(Integer.parseInt(getValue(cla_number).toString()));
				
				HSSFCell xm = hssfRow.getCell(2);
				if (xm == null) {
					String null_str = "";
					classinformation.setCla_name(null_str);
				} else
					classinformation.setCla_name(getValue(xm));

				list.add(classinformation);
			}
		}
		return list;
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

	private static List<String> format_classinformation(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.判断 部门编号
		String sql_id = "select * from tmp_classinformation where cla_id regexp '[^0-9{8}]'";

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				String cla_id = rs_id.getString("cla_id");
				String str = "部门编号：“" + cla_id + "”格式错误";
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
		String sql = "select * from tmp_classinformation where length(cla_name)>50";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				String cla_name = rs_name.getString("cla_name");
				String str = "教室名称：“" + cla_name + "”长度过长";
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
		String sql_id_channg = "select * from tmp_classinformation where length(cla_grade)>2";

		ResultSet rs_id_chang;
		try {
			rs_id_chang = st.executeQuery(sql_id_channg);
			while (rs_id_chang.next()) {
				String cla_grade = rs_id_chang.getString("cla_grade");
				String str = "年级：“" + cla_grade + "”长度过长";
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
		String sql_check_id = "select * from classinformation where cla_id in (select cla_id from tmp_classinformation)";

		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				String cla_id = rs_check_id.getString("cla_id");
				String str = "教室信息编号：“" + cla_id + "”重复错误";
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

		String sql_check_name = "select * from classinformation where cla_name in (select cla_name from tmp_classinformation)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				String cla_name = rs_check_name.getString("cla_name");
				String str = "班级信息名称：“" + cla_name + "”重复错误";
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

		String sql_name_null = "select * from tmp_classinformation where cla_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				String cla_id = rs_name_null.getString("cla_id");
				String str = "教室信息名称：“" + cla_id + "”的教室名称为空了";
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
		String sql_id_null = "select * from tmp_classinformation where cla_id =''";
		ResultSet rs_id;
		try {
			rs_id = st.executeQuery(sql_id_null);
			while (rs_id.next()) {
				String cla_id = rs_id.getString("cla_id");
				String d_name = rs_id.getString("cla_name");
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
