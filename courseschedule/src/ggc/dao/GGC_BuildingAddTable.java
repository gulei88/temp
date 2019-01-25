package ggc.dao;

import global.dao.Databaseconnection;
import global.model.Buliding;

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

public class GGC_BuildingAddTable { 
	private static List<String> get_throw = new ArrayList<String>();
	public static void main(String[] args) {
		List<String> buliding_List = getBuildingThrow("C:/Users/hp/Desktop/buliding.xls");

		for (String item : buliding_List) {
			System.out.println(item);
		}
		
}
	public static List<String> getBuildingThrow(String file){

		Connection con = null;
		GGC_BuildingAddTable GGC_BuildingAddTable = new GGC_BuildingAddTable();
		Buliding buliding = null;
		List<Buliding> list = null;
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
		create_buliding(con);
		// 从excel获取字段数据
		list = GGC_BuildingAddTable.readXls(file);

		for (int i = 0; i < list.size(); i++) {
			buliding = (Buliding) list.get(i);

			try {
				int num = add_tmp_buliding(con,buliding.getB_id(),buliding.getB_name(),buliding.getB_alias(),buliding.getB_address());
				//if num <0
				
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

		}

		// 判断临时表内格式
		get_throw = format_buliding(con);
//		System.out.println(get_throw);
		if (get_throw.size() == 0) {
			// 没错误时，将数据添加到department表

			try {
//				get_throw.add(add_department(con));
				if(add_buliding(con)==0)
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

	private static void create_buliding(Connection con) {

		// 如果数据库的连接为空，则返回空
		if (con == null)
			return;
		String sql = "CREATE TEMPORARY TABLE tmp_buliding ("
				+"b_id int(10) NOT NULL,"
				+ "b_name varchar(20) NOT NULL,"
				+ "b_alias varchar(20),"
				+"b_address varchar(50)  NOT NULL)";

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

	public static int add_buliding(Connection con) {
		//把查询到临时表的数据添加到tmp_departemt
		String sql = "insert into building select * from tmp_buliding";
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
	private static int add_tmp_buliding(Connection con,int b_id,String b_name, String b_alias,
			String b_address) {

		String sql = "INSERT INTO tmp_buliding (b_id,b_name,b_alias,b_address) VALUES (" + "'"
				+ b_id + "'" + ",'" + b_name + "'"+ ",'" +b_alias + "'"+ ",'" +b_address + "')";
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
	private List<Buliding> readXls(String file) {
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

		Buliding buliding = null;
		List<Buliding> list = new ArrayList<Buliding>();

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
				buliding = new Buliding();
				// 循环列Cell
				// for (int cellNum = 0; cellNum <=4; cellNum++)		
				HSSFCell bid = hssfRow.getCell(0);
				if (bid == null) {
					continue;
				}
				System.out.println(bid);
				buliding.setB_id(Integer.parseInt(getValue(bid).toString()));

				HSSFCell name = hssfRow.getCell(1);
				if (name == null) {
					continue;
				}
				buliding.setB_name(getValue(name));
				System.out.println(name);
				HSSFCell alias = hssfRow.getCell(2);
				if (alias == null) {
					continue;
				}
				buliding.setB_alias(getValue(alias));
				System.out.println(alias);
				
				HSSFCell address = hssfRow.getCell(3);
				if (address == null) {
					continue;
				}
				System.out.println(address);
				buliding.setB_address(getValue(address));
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
	private static List<String> format_buliding(Connection con) {
		List<String> throw_content = new ArrayList<String>();

//		 1.判断 教学楼编号
		String sql_id = "select * from tmp_buliding where b_id regexp '[^0-9{2}]'";

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				int b_id = rs_id.getInt("b_id");
				String b_name = rs_id.getString("b_name");
				String b_alias = rs_id.getString("b_alias");
				String b_address = rs_id.getString("b_address");
				String str = "教学楼编号：“" + b_id + "”格式错误";
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
		// 2.判断 教学楼名称
		String sql = "select * from tmp_buliding where length(b_name)>20";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				int b_id = rs_name.getInt("b_id");
				String b_name = rs_name.getString("b_name");
				String b_alias = rs_name.getString("b_alias");
				String b_address = rs_name.getString("b_address");
				String str = "教学楼名称：“" + b_name + "”长度过长";
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
				//判断 教学楼别名
				String sql_alias = "select * from tmp_buliding where length(b_alias)>20";

				ResultSet rs_alias;
				try {
					rs_alias = st.executeQuery(sql_alias);
					while (rs_alias.next()) {
						String b_name = rs_alias.getString("b_name");
						String b_alias = rs_alias.getString("b_alias");
						String b_address = rs_alias.getString("b_address");
						String str = "教学楼别名：“" + b_alias + "”长度过长";
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
				//判断 教学楼地址
				String sql_address = "select * from tmp_buliding where length(b_address)>50";

				ResultSet rs_address;
				try {
					rs_address = st.executeQuery(sql_address);
					while (rs_address.next()) {
						int b_id = rs_address.getInt("b_id");
						String b_name = rs_address.getString("b_name");
						String b_alias = rs_address.getString("b_alias");
						String b_address = rs_address.getString("b_address");
						String str = "教学楼地址：“" + b_address + "”长度过长";
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
		String sql_check_id = "select * from building where b_id in (select b_id from tmp_buliding)";
		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				int b_id = rs_check_id.getInt("b_id");
				String b_name = rs_check_id.getString("b_name");
				String b_alias = rs_check_id.getString("b_alias");
				String b_address = rs_check_id.getString("b_address");
				String str = "教学楼编号：“" + b_id + "”重复错误";
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

		String sql_check_name = "select * from building where b_name in (select b_name from tmp_buliding)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				int b_id = rs_check_name.getInt("b_id");
				String b_name = rs_check_name.getString("b_name");
				String b_alias = rs_check_name.getString("b_alias");
				String b_address = rs_check_name.getString("b_address");
				String str = "教学楼名称：“" + b_name + "”重复错误";
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

		String sql_name_null = "select * from tmp_buliding where b_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				int b_id = rs_name_null.getInt("b_id");
				String b_name = rs_name_null.getString("b_name");
				String b_alias = rs_name_null.getString("b_alias");
				String b_address = rs_name_null.getString("b_address");
				String str = "教学楼编号：“" + b_id + "”的教学楼名称为空了";
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
		String sql_id_null = "select * from tmp_buliding where b_id =''";
		ResultSet rs_id_null;
		try {
			rs_id_null = st.executeQuery(sql_id_null);
			while (rs_id_null.next()) {
				int b_id = rs_id_null.getInt("b_id");
				String b_name = rs_id_null.getString("b_name");
				String b_alias = rs_id_null.getString("b_alias");
				String b_address = rs_id_null.getString("b_address");
				String str = "教学楼名称：“" + b_name + "”的教学楼编号为空了";
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
		System.out.println(throw_content);
		return throw_content;

	}
	}
