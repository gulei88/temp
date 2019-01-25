//package zzt.dao;
//
//import global.dao.Databaseconnection;
//import global.model.Major;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//public class MajorAddTable {
//
//	public static void main(String[] args) {
//
//		List<String> department_List = getDepartmentThrow("C:/Users/Administrator/Desktop/department.xls");
//
//		for (String item : department_List) {
//			System.out.println(item);
//		}
//	}
//
//	/**
//	 * 从文件读取数据并调用添加临时表和判断格式
//	 * 
//	 * @param file
//	 * @return
//	 * 
//	 */
//	public static List<String> getDepartmentThrow(String file) {
//
//		Connection con = null;
//		MajorAddTable majorAddTable = new MajorAddTable();
//		Major Major = null;
//		List<Major> list = null;
//		List<String> get_throw = null;
//
//		try {
//			con = Databaseconnection.getconnection();
//			con.setAutoCommit(false);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			try {
//				con.close();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//				con.rollback();
//			} finally {
//				return null;
//			}
//
//		}
//
//		// 创建tmp_department临时表
//		create_major(con);
//		// 从excel获取字段数据
//		list = majorAddTable.readXls(file);
//
//		for (int i = 0; i < list.size(); i++) {
//			Major = (Major) list.get(i);
//
//			try {
//				int num = add_tmp_marjor(con, Major.getM_id(),
//						Major.getM_name(),Major.getO_id());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//
//				try {
//					con.rollback();
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//
////			System.out.println(department.getD_id() + "        "
////					+ department.getD_name());
//
//		}
//
//		// 判断临时表内格式
//		get_throw = format_department(con);
//
//		if (get_throw.size() == 0) {
//			// 没错误时，将数据添加到department表
//
//			try {
//			//	get_throw.add(add_department(con));
//				if(add_department(con)==0)
//					get_throw.add("导入到数据库出现问题");
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			try {
//				con.commit();
//				con.close();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//
//				e.printStackTrace();
//				try {
//					con.rollback();
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//			return get_throw;
//		}
//
//		try {
//
//			con.commit();
//			con.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			try {
//				con.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//		return get_throw;
//
//	}
//
//	private static void create_major(Connection con) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	/**
//	 * 创建临时表
//	 * 
//	 * 
//	 */
//
//	private static void create_department(Connection con) {
//
//		// 如果数据库的连接为空，则返回空
//		if (con == null)
//			return;
//		String sql = "CREATE TEMPORARY TABLE tmp_department ("
//				+ "d_id VARCHAR(20) NOT NULL,"
//				+ "d_name varchar(100)  NOT NULL)";
//
//		Statement st;
//		try {
//			st = con.createStatement();
//			st.executeUpdate(sql);
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
//		// 声明对象执行SQL语句，返回影响的记录数目。
//
//	}
//
//	/**
//	 * 添加数据到department表
//	 * 
//	 * 
//	 */
//
//	public static int add_department(Connection con) {
//		String sql = "insert into department select * from tmp_department";
//		int n = 0;
//		Statement st;
//		try {
//			st = con.createStatement();
//			n = st.executeUpdate(sql);
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
//		// 声明对象执行SQL语句，返回影响的记录数目。
//		if (n > 0) {
//			// System.out.println("导入成功");
//			return 1;
//		} else {
//			// System.out.println("导入失败");
//			return 0;
//		}
//	}
//
//	/**
//	 * 添加excel数据到临时表中
//	 * 
//	 * 
//	 */
//	private static int add_tmp_marjor(Connection con,String o_id, String m_id,
//			String m_name) {
//
//		String sql = "INSERT INTO tmp_marjor (o_id,m_id,m_name) VALUES (" + "'"
//				+ o_id + "'" + ",'" + m_id + "','" + m_name + "')";
//		// 生成数据库声明对象
//		Statement st;
//		int result = 0;
//		try {
//			st = con.createStatement();
//			result = st.executeUpdate(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			try {
//				con.rollback();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//		return result;
//		// 声明对象执行SQL语句，返回影响的记录数目。
//
//	}
//
//	/**
//	 * 读取文件内容
//	 * 
//	 * @return List<Department>对象
//	 * 
//	 */
//	private List<Major> readXls(String file) {
//		InputStream is;
//		HSSFWorkbook hssfWorkbook = null;
//		try {
//			is = new FileInputStream(file);
//			try {
//				hssfWorkbook = new HSSFWorkbook(is);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Major major = null;
//		List<Major> list = new ArrayList<Major>();
//
//		// 循环工作表Sheet
//		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
//			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
//			if (hssfSheet == null) {
//				continue;
//			}
//			// 循环行Row
//			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
//				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
//				if (hssfRow == null) {
//					continue;
//				}
//				major = new Major();
//				// 循环列Cell
//				// for (int cellNum = 0; cellNum <=4; cellNum++)
//				HSSFCell id = hssfRow.getCell(0);
//				if (id == null) {
//					continue;
//				}
//
//				major.setM_id(getValue(id));
//
//				HSSFCell xm = hssfRow.getCell(1);
//				if (xm == null) {
//					String null_str = "";
//					major.setM_name(null_str);
//				} else
//					major.setM_name(getValue(xm));
//
//				list.add(major);
//			}
//		}
//		return list;
//	}
//
//	/**
//	 * 得到Excel表中的值
//	 * 
//	 * @param hssfCell
//	 *            Excel中的每一个格子
//	 * @return Excel中每一个格子中的值
//	 */
//	@SuppressWarnings("static-access")
//	private String getValue(HSSFCell hssfCell) {
//		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
//			// 返回布尔类型的值
//			return String.valueOf(hssfCell.getBooleanCellValue());
//		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
//			// 返回数值类型的值
//			return String.valueOf(hssfCell.getNumericCellValue());
//		} else {
//			// 返回字符串类型的值
//			return String.valueOf(hssfCell.getStringCellValue());
//		}
//	}
//
//	/**
//	 * 格式判断
//	 * 
//	 * 
//	 */
//
//	private static List<String> format_department(Connection con) {
//		List<String> throw_content = new ArrayList<String>();
//
//		// 1.判断 部门编号
//		String sql_id = "select * from tmp_department where d_id regexp '[^0-9{2}]'";
//
//		Statement st = null;
//		try {
//			st = con.createStatement();
//			ResultSet rs_id = st.executeQuery(sql_id);
//			while (rs_id.next()) {
//				String d_id = rs_id.getString("d_id");
//				String d_name = rs_id.getString("d_name");
//				String str = "部门编号：“" + d_id + "”格式错误";
//				throw_content.add(str);
//			}
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
//
//		// 2.判断 部门名称
//		String sql = "select * from tmp_department where length(d_name)>30";
//
//		ResultSet rs_name;
//		try {
//			rs_name = st.executeQuery(sql);
//			while (rs_name.next()) {
//				String d_id = rs_name.getString("d_id");
//				String d_name = rs_name.getString("d_name");
//				String str = "部门名称：“" + d_name + "”长度过长";
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
//
//		// 3.判断 id重复
//		String sql_check_id = "select * from department where d_id in (select d_id from tmp_department)";
//
//		ResultSet rs_check_id;
//		try {
//			rs_check_id = st.executeQuery(sql_check_id);
//			while (rs_check_id.next()) {
//				String d_id = rs_check_id.getString("d_id");
//				String d_name = rs_check_id.getString("d_name");
//				String str = "部门编号：“" + d_id + "”重复错误";
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
//
//		// 4.判断 name重复
//
//		String sql_check_name = "select * from department where d_name in (select d_name from tmp_department)";
//
//		ResultSet rs_check_name;
//		try {
//			rs_check_name = st.executeQuery(sql_check_name);
//			while (rs_check_name.next()) {
//				String d_id = rs_check_name.getString("d_id");
//				String d_name = rs_check_name.getString("d_name");
//				String str = "部门名称：“" + d_name + "”重复错误";
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
//
//		// 5.判断 name null
//
//		String sql_name_null = "select * from tmp_department where d_name =''";
//
//		ResultSet rs_name_null;
//		try {
//			rs_name_null = st.executeQuery(sql_name_null);
//			while (rs_name_null.next()) {
//				String d_id = rs_name_null.getString("d_id");
//				String d_name = rs_name_null.getString("d_name");
//				String str = "部门编号：“" + d_id + "”的部门名称为空了";
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
//
//		// 6.判断id null
//		String sql_id_null = "select * from tmp_department where d_id =''";
//		ResultSet rs_id_null;
//		try {
//			rs_id_null = st.executeQuery(sql_id_null);
//			while (rs_id_null.next()) {
//				String d_id = rs_id_null.getString("d_id");
//				String d_name = rs_id_null.getString("d_name");
//				String str = "部门名称：“" + d_name + "”的部门编号为空了";
//				throw_content.add(str);
//			}
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
//
//		return throw_content;
//
//	}
//
//}
