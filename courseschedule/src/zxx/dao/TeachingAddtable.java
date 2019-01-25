package zxx.dao;

import global.dao.Databaseconnection;
import global.dao.Teachingplanaccess;
import global.model.Department;
import global.model.Major;
import global.model.Teachingplan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TeachingAddtable {

	private static List<String> get_throw = new ArrayList<String>();
	public static void main(String[] args) {

		List<String> teaching_list = getDepartmentThrow("C:/Users/Administrator/Desktop/department1.xls");
		for (String item : teaching_list) {
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
		TeachingAddtable teachingaddtable = new TeachingAddtable();
		Teachingplan teachingplan = null;
		List<Teachingplan> list = null;
		List<String> get_throw = null;

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
		create_teachingplan(con);
		// 从excel获取字段数据
		get_throw = format_teachingplan(con);
		list = teachingaddtable.readXls(file);
		for (int i = 0; i < list.size(); i++) {
			teachingplan = (Teachingplan) list.get(i);List<Teachingplan> a;

			try {
				int num = add_tmp_teachingplan(con, teachingplan.getTp_id(), teachingplan.getTp_name(),
						teachingplan.getM_id(),teachingplan.getTp_mark());
				String m_id = teachingplan.getM_id();
				String tp_name = teachingplan.getTp_name();
//				String o_id = department.getD_id();
//				String m_id = department.getD_name();
//				System.out.println(o_id);
				a = Teachingplanaccess.getteachingplan(m_id);
				if (a.size() == 0) {
					get_throw.add(tp_name+"的专业编号外键匹配异常");
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
		get_throw.addAll(format_teachingplan(con));
		// 判断临时表内格式
//		get_throw = format_teachingplan(con);

		if (get_throw.size() == 0) {
			// 没错误时，将数据添加到department表

			try {
			//	get_throw.add(add_department(con));
				if(add_teachingplan(con)==0)
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

	private static void create_teachingplan(Connection con) {

		// 如果数据库的连接为空，则返回空
		if (con == null)
			return;
		String sql = "CREATE TEMPORARY TABLE tmp_teachingplan ("
				+ "tp_id varchar(20) NOT NULL,"
				+ "tp_name varchar(100)  NOT NULL,"
				+ "m_id varchar(100) NOT NULL,"
				+ "tp_mark varchar(100))";

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

	public static int add_teachingplan(Connection con) {
		//把查询到临时表的数据添加到tmp_departemt
		String sql = "insert into teachingplan select * from tmp_teachingplan";
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
	private static int add_tmp_teachingplan(Connection con, String tp_id,String tp_name,
			String m_id,String tp_mark)  {

		String sql = "INSERT INTO tmp_teachingplan (tp_id,tp_name,m_id,tp_mark) VALUES (" + "'"
				+ tp_id + "'" + ",'" + tp_name + "','" + m_id + "','" + tp_mark + "')";
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
	List<Teachingplan> readXls(String file) {
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

		Teachingplan teachingplan = null;
		List<Teachingplan> list = new ArrayList<Teachingplan>();

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
				teachingplan = new Teachingplan();
				// 循环列Cell
				// for (int cellNum = 0; cellNum <=4; cellNum++)
				HSSFCell id = hssfRow.getCell(0);
				if (id == null) {
					continue;
				}

				teachingplan.setTp_id(getValue(id));

				HSSFCell xm = hssfRow.getCell(1);
				if (xm == null) {
					String null_str = "";
					teachingplan.setTp_name(null_str);
				} else{
					teachingplan.setTp_name(getValue(xm));
				}
				HSSFCell mid = hssfRow.getCell(2);
				if (mid == null) {
					continue;
				}
				teachingplan.setM_id(getValue(mid));
				
				HSSFCell tpmark = hssfRow.getCell(3);
				if (tpmark == null) {
					continue;
				}
				teachingplan.setTp_mark(getValue(tpmark));
				list.add(teachingplan);
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

	private static List<String> format_teachingplan(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.判断 教学计划编号
		String sql_id = "select * from tmp_teachingplan where tp_id regexp '[^0-9{6}]'";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				System.out.println(1);
				String tp_id = rs_id.getString("tp_id");
				String str = "教学计划编号：“" + tp_id + "”格式错误";
				System.out.println(str);
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

//		// 1.判断 教学计划名称
//		String sql_oid = "select * from tmp_teachingplan where m_id regexp '[^0-9{4}]'";
//		try {
//			st = con.createStatement();
//			ResultSet rso_id = st.executeQuery(sql_oid);
//			while (rso_id.next()) {
//				String m_id = rso_id.getString("m_id");
//				String str = "专业编号：“" + m_id + "”格式错误";
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

		String sql_midk = "select * from tmp_teachingplan where length(m_id) > 4 ";
		try {
			st = con.createStatement();
			ResultSet rsmm_id = st.executeQuery(sql_midk);
			while (rsmm_id.next()) {
				String m_id = rsmm_id.getString("m_id");
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
		String sql = "select * from tmp_teachingplan where length(tp_mark)>200";

		ResultSet rs_mark;
		try {
			rs_mark = st.executeQuery(sql);
			while (rs_mark.next()) {
				String tp_mark = rs_mark.getString("tp_mark");
				String str = "备注名称：“" + tp_mark + "”长度过长";
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

		// 2.判断 备注名称
		String sql1 = "select * from tmp_teachingplan where length(tp_name)>50";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql1);
			while (rs_name.next()) {
				String tp_name = rs_name.getString("tp_name");
				String str = "教学计划名称：“" + tp_name + "”长度过长";
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

		String sql_check_name = "select * from teachingplan where tp_name in (select tp_name from tmp_teachingplan)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				String tp_name = rs_check_name.getString("tp_name");
				String str = "专业名称：“" + tp_name + "”重复错误";
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

		String sql_name_null = "select * from tmp_teachingplan where tp_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				String tp_name = rs_name_null.getString("tp_name");
				String str = "专业名称:“" + tp_name + "”的专业名称为空了";
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

		String sql_o_id_null = "select * from tmp_teachingplan where m_id =''";

		ResultSet rs_o_id;
		try {
			rs_o_id = st.executeQuery(sql_o_id_null);
			while (rs_o_id.next()) {
				String m_id = rs_o_id.getString("m_id");
				String str = "科室编号：“" + m_id + "”的科室编号为空了";
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

		String sql_tp_id_null = "select * from tmp_teachingplan where tp_id =''";

		ResultSet rs_tp_id;
		try {
			rs_tp_id = st.executeQuery(sql_tp_id_null);
			while (rs_tp_id.next()) {
				String m_id = rs_tp_id.getString("m_id");
				String str = "专业编号：“" + m_id + "”的专业编号为空了";
				throw_content.add(str);
				System.out.println(111);
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
