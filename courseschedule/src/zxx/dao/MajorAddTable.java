package zxx.dao;

import global.dao.Databaseconnection;
import global.dao.Majoraccess;
import global.dao.Officeaccess;
import global.model.Major;
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

public class MajorAddTable {

	private static List<String> get_throw = new ArrayList<String>();

	public static void main(String[] args) {

		List<String> major_List = getDepartmentThrow("C:/Users/Administrator/Desktop/111.xls");
		
//		System.out.println(major_List);
		for (String item : major_List) {
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
	public static List<String> getDepartmentThrow(String file) {

		Connection con = null;
		MajorAddTable majorAddTable = new MajorAddTable();
		Major major = null;
		List<Major> list = new ArrayList<Major>();
		

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
		create_major(con);
		get_throw = format_major(con);
//		System.out.println(get_throw);
		// 从excel获取字段数据
		list = majorAddTable.readXls(file);List<Office> office;
		for (int i = 0; i < list.size(); i++) {
			major = (Major) list.get(i);
			try {
				int num = add_tmp_major(con, major.getM_id(), major.getO_id(),
						major.getM_name());
				String o_id = major.getO_id();
				String o_name = major.getM_name();
//				System.out.println(o_id);
				office = Officeaccess.getoffice_(o_id);
				if (office.size() == 0) {
					get_throw.add(o_name+"专业编号外键匹配异常");
				}
				// System.out.println(get_throw);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		// return get_throw;
		get_throw.addAll(format_major(con));
	
		if (get_throw.size() == 0) {
			// 没错误时，将数据添加到department表
			try {
				// get_throw.add(add_department(con));
				if (add_major(con) == 0)
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
//			System.out.println(get_throw);
		
			return get_throw;
		}

		try {

			con.commit();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
//		 System.out.println(get_throw);
		return get_throw;
	}

	/**
	 * 创建临时表
	 * 
	 * 
	 */

	private static void create_major(Connection con) {

		// 如果数据库的连接为空，则返回空
		if (con == null)
			return;
		String sql = "CREATE TEMPORARY TABLE tmp_major ("
				+ "m_id VARCHAR(20) NOT NULL,"
				+ "o_id varchar(20)  NOT NULL,"
				+ "m_name varchar(100)  NOT NULL)";

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
	

	
	public static int add_major(Connection con) {
		//把查询到临时表的数据添加到tmp_departemt
		List<String> throw_content = new ArrayList<String>();
		List<String> get_throw = null;
		String sql = "insert into major select * from tmp_major";
		int n = 0;
		Statement st;
		try {
			st = con.createStatement();
			n = st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			//如果当前访问的状态值等于str,则提示相应信息		
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
	private static int add_tmp_major(Connection con, String m_id,String o_id,
			String m_name) {

		String sql = "INSERT INTO tmp_major (m_id,o_id,m_name) VALUES (" + "'"
				+ m_id + "'" + ",'" + o_id + "','" + m_name + "')";
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
	private List<Major> readXls(String file) {
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

		Major major = null;
		List<Major> list = new ArrayList<Major>();

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
				major = new Major();
				// 循环列Cell
				// for (int cellNum = 0; cellNum <=4; cellNum++)
				HSSFCell id = hssfRow.getCell(0);
				if (id == null) {
					continue;
				}

				major.setM_id(getValue(id));
				
				HSSFCell o_id = hssfRow.getCell(1);
				if (o_id == null) {
					continue;
				}

				major.setO_id(getValue(o_id));
				
				HSSFCell xm = hssfRow.getCell(2);
				if (xm == null) {
					String null_str = "";
					major.setM_name(null_str);
				} else
					major.setM_name(getValue(xm));

				list.add(major);
				
			}
		}
//		System.out.println(list);
		System.out.println(list);
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

	private static List<String> format_major(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.判断 专业编号
		String sql_id = "select * from tmp_major where m_id regexp '[^0-9{4}]'";

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				String m_id = rs_id.getString("m_id");
//				System.out.println(m_id);
				String o_id = rs_id.getString("o_id");
				String m_name = rs_id.getString("m_name");
				String str = "专业编号：“" + m_id + "”格式错误";
//				throw_content.add(str);
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
		
		// 1.判断 科室编号
		String sql_oid = "select * from tmp_major where o_id regexp '[^0-9{4}]'";
		try {
			st = con.createStatement();
			ResultSet rso_id = st.executeQuery(sql_id);
			while (rso_id.next()) {
				String m_id = rso_id.getString("m_id");
				String o_id = rso_id.getString("o_id");
				String m_name = rso_id.getString("m_name");
				String str = "科室编号：“" + o_id + "”格式错误";
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

		
		String sql_midk = "select * from tmp_major where length(m_id) > 4 ";
		try {
			st = con.createStatement();
			ResultSet rsmm_id = st.executeQuery(sql_midk);
			while (rsmm_id.next()) {
				String m_id = rsmm_id.getString("m_id");
				String o_id = rsmm_id.getString("o_id");
				String m_name = rsmm_id.getString("m_name");
				String str = "专业编号：“" + m_id + "”长度过长";
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
		String sql = "select * from tmp_major where length(m_name)>30";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				String m_id = rs_name.getString("m_id");
				String o_id = rs_name.getString("o_id");
				String m_name = rs_name.getString("m_name");
				String str = "专业名称：“" + m_name + "”长度过长";
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
		String sql_check_id = "select * from major where m_id in (select m_id from tmp_major)";

		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				String m_id = rs_check_id.getString("m_id");
				String o_id = rs_check_id.getString("o_id");
				String m_name = rs_check_id.getString("m_name");
				String str = "专业编号：“" + m_id + "”重复错误";
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
//		String sql_o_id = "select * from major where o_id in (select o_id from tmp_major)";
//
//		ResultSet rs_oid;
//		try {
//			rs_oid = st.executeQuery(sql_check_id);
//			while (rs_oid.next()) {
//				String m_id = rs_oid.getString("m_id");
//				String o_id = rs_oid.getString("o_id");
//				String m_name = rs_oid.getString("m_name");
//				String str = "专业编号：“" + o_id + "”重复错误";
//				throw_content.add(str);
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			try {
//				con.rollback();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		

		// 4.判断 name重复

		String sql_check_name = "select * from major where m_name in (select m_name from tmp_major)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				String m_id = rs_check_name.getString("m_id");
				String o_id = rs_check_name.getString("o_id");
				String m_name = rs_check_name.getString("m_name");
				String str = "专业名称：“" + m_name + "”重复错误";
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

		String sql_name_null = "select * from tmp_major where m_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				String m_id = rs_name_null.getString("m_id");
				String o_id = rs_name_null.getString("o_id");
				String m_name = rs_name_null.getString("m_name");
				String str = "专业名称:“" + m_name + "”的专业名称为空了";
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

		
		String sql_o_id_null = "select * from tmp_major where o_id =''";

		ResultSet rs_o_id;
		try {
			rs_o_id = st.executeQuery(sql_name_null);
			while (rs_o_id.next()) {
				String m_id = rs_o_id.getString("m_id");
				String o_id = rs_o_id.getString("o_id");
				String m_name = rs_o_id.getString("m_name");
				String str = "科室编号：“" + o_id + "”的科室编号为空了";
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
		String sql_id_null = "select * from tmp_major where m_id =''";
		ResultSet rs_id;
		try {
			rs_id = st.executeQuery(sql_id_null);
			while (rs_id.next()) {
//				String m_id = rs_id.getString("m_id");
//				String o_id = rs_id.getString("o_id");
				String m_id = rs_id.getString("m_id");
				String str = "专业编号：“" + m_id + "”的专业专业编号为空了";
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
//		System.out.println(throw_content);

	
		return throw_content;
	
	}
	

}
