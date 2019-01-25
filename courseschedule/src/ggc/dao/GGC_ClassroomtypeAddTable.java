package ggc.dao;

import global.dao.Classrommtypeaccess;
import global.dao.Databaseconnection;
import global.dao.Officeaccess;
import global.model.Office;

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

import zzt.model.Classroomtype;

public class GGC_ClassroomtypeAddTable {
	private static String file;
	private static List<String> get_throw = new ArrayList<String>();
	public static void main(String[] args) {

		List<String> classroomtype_List = getClassroomtypeThrow("C:/Users/hp/Desktop/classroomtype.xls");

		for (String item : classroomtype_List) {
			System.out.println(item);
		}

}
	public static List<String> getClassroomtypeThrow(String file){

		Connection con = null;
		GGC_ClassroomtypeAddTable GGC_ClassroomtypeAddTable = new GGC_ClassroomtypeAddTable();
		Classroomtype classroomtype = null;
		List<Classroomtype> list = new ArrayList<Classroomtype>();
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
		create_classroomtype(con);
		get_throw=format_classroomtype(con);
		// 从excel获取字段数据
		list = GGC_ClassroomtypeAddTable.readXls(file);
		List<Classroomtype>a;

		for (int i = 0; i < list.size(); i++) {
			classroomtype = (Classroomtype) list.get(i);

			try {
				int num = add_tmp_classroomtype(con, classroomtype.getCt_id(), classroomtype.getCt_name());
				//if num <0
				int ct_id = classroomtype.getCt_id();
				a=Classrommtypeaccess.getClassroomtypes(ct_id);
				
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
//		get_throw = format_office(con);
		get_throw.addAll(format_classroomtype(con));
		if (get_throw.size() == 0) {
			// 没错误时，将数据添加到department表

			try {
//				get_throw.add(add_department(con));
				if(add_classroomtype(con)==0)
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
			System.out.println(get_throw);
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

	private static void create_classroomtype(Connection con) {

		// 如果数据库的连接为空，则返回空
		if (con == null)
			return;
		String sql = "CREATE TEMPORARY TABLE tmp_classroomtype ("
				+"ct_id int(10) NOT NULL,"
				+ "ct_name varchar(45)  NOT NULL)";

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
	 * 添加数据到office表
	 * 
	 * 
	 */

	public static int add_classroomtype(Connection con) {
		//把查询到临时表的数据添加到tmp_departemt
		String sql = "insert into classroomtype select * from tmp_classroomtype";
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
			 System.out.println("导入成功");
			return 1;
		} else {
			 System.out.println("导入失败");
			return 0;
		}
	}
	/**
	 * 添加excel数据到临时表中
	 * 
	 * 
	 */
	private static int add_tmp_classroomtype(Connection con,int ct_id, 
			String ct_name) {

		String sql = "INSERT INTO tmp_classroomtype (ct_id, ct_name) VALUES (" + "'"
				+ ct_id + "'" + ",'" + ct_name + "')";
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
	private List<Classroomtype> readXls(String file) {
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

		Classroomtype classroomtype = null;
		List<Classroomtype> list = new ArrayList<Classroomtype>();

		// 循环工作表Sheet
		for (int numSheet = 0; numSheet<hssfWorkbook.getNumberOfSheets(); numSheet++) {
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
				classroomtype = new Classroomtype();
				// 循环列Cell
				// for (int cellNum = 0; cellNum <=4; cellNum++)		
				HSSFCell id = hssfRow.getCell(0);
				System.out.println(id);
				if (id == null) {
					continue;
				}
				classroomtype.setCt_id(Integer.parseInt(getValue(id).toString()));
				
				HSSFCell xm = hssfRow.getCell(1);
				if (xm == null) {
					String null_str = "";
					classroomtype.setCt_name(null_str);
				} else
					classroomtype.setCt_name(getValue(xm));

				list.add(classroomtype);
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
			return String.valueOf(hssfCell.getNumericCellValue());
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
	private static List<String> format_classroomtype(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.判断 教室类型编号
		String sql_id = "select * from tmp_classroomtype where ct_id regexp '[^0-9{10}]'";

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				int ct_id = rs_id.getInt("ct_id");
				String ct_name = rs_id.getString("ct_name");
				String str = "教室类型编号：“" + ct_id + "”格式错误";
				throw_content.add(str);
//				System.out.println(d_id);
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
		// 2.判断 教室类型名称
		String sql = "select * from tmp_classroomtype where length(ct_name)>45";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				int ct_id = rs_name.getInt("ct_id");
				String ct_name = rs_name.getString("ct_name");
				String str = "教室类型名称：“" + ct_name + "”长度过长";
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
		String sql_check_id = "select * from classroomtype where ct_id in (select ct_id from tmp_classroomtype)";

		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				int ct_id = rs_check_id.getInt("ct_id");
				String ct_name = rs_check_id.getString("ct_name");
				String str = "教室类型编号：“" + ct_id + "”重复错误";
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

		String sql_check_name = "select * from classroomtype where ct_name in (select ct_name from tmp_classroomtype)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				int ct_id = rs_check_name.getInt("ct_id");
				String ct_name = rs_check_name.getString("o_name");
				String str = "教室类型名称：“" + ct_name + "”重复错误";
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

		String sql_name_null = "select * from tmp_classroomtype where ct_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				int ct_id = rs_name_null.getInt("ct_id");
				String ct_name = rs_name_null.getString("ct_name");
				String str = "教室类型编号：“" + ct_id + "”的教室类型名称为空了";
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
		String sql_id_null = "select * from tmp_classroomtype where ct_id =''";
		ResultSet rs_id_null;
		try {
			rs_id_null = st.executeQuery(sql_id_null);
			while (rs_id_null.next()) {
				int ct_id = rs_id_null.getInt("ct_id");
				String ct_name = rs_id_null.getString("ct_name");
				String str = "教室类型名称：“" + ct_name + "”的教室类型编号为空了";
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
