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

		// ����tmp_department��ʱ��
		create_buliding(con);
		// ��excel��ȡ�ֶ�����
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

		// �ж���ʱ���ڸ�ʽ
		get_throw = format_buliding(con);
//		System.out.println(get_throw);
		if (get_throw.size() == 0) {
			// û����ʱ����������ӵ�department��

			try {
//				get_throw.add(add_department(con));
				if(add_buliding(con)==0)
					get_throw.add("���뵽���ݿ��������");

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
	 * ������ʱ��
	 * 
	 * 
	 */

	private static void create_buliding(Connection con) {

		// ������ݿ������Ϊ�գ��򷵻ؿ�
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
		// ��������ִ��SQL��䣬����Ӱ��ļ�¼��Ŀ��

	}
	/**
	 * ������ݵ�office��
	 * 
	 * 
	 */

	public static int add_buliding(Connection con) {
		//�Ѳ�ѯ����ʱ���������ӵ�tmp_departemt
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
		// ��������ִ��SQL��䣬����Ӱ��ļ�¼��Ŀ��
		if (n > 0) {
			 System.out.println("����ɹ�");
			return 1;
		} else {
			 System.out.println("����ʧ��");
			return 0;
		}
	}
	/**
	 * ���excel���ݵ���ʱ����
	 * 
	 * 
	 */
	private static int add_tmp_buliding(Connection con,int b_id,String b_name, String b_alias,
			String b_address) {

		String sql = "INSERT INTO tmp_buliding (b_id,b_name,b_alias,b_address) VALUES (" + "'"
				+ b_id + "'" + ",'" + b_name + "'"+ ",'" +b_alias + "'"+ ",'" +b_address + "')";
		// �������ݿ���������
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
		// ��������ִ��SQL��䣬����Ӱ��ļ�¼��Ŀ��

	}
	/**
	 * ��ȡ�ļ�����
	 * 
	 * @return List<Department>����
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

		// ѭ��������Sheet
		for (int numSheet = 0; numSheet<hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// ѭ����Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				buliding = new Buliding();
				// ѭ����Cell
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
	 * �õ�Excel���е�ֵ
	 * 
	 * @param hssfCell
	 *            Excel�е�ÿһ������
	 * @return Excel��ÿһ�������е�ֵ
	 */
	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// ���ز������͵�ֵ
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// ������ֵ���͵�ֵ
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			// �����ַ������͵�ֵ
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	/**
	 * ��ʽ�ж�
	 * 
	 * 
	 */
	private static List<String> format_buliding(Connection con) {
		List<String> throw_content = new ArrayList<String>();

//		 1.�ж� ��ѧ¥���
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
				String str = "��ѧ¥��ţ���" + b_id + "����ʽ����";
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
		// 2.�ж� ��ѧ¥����
		String sql = "select * from tmp_buliding where length(b_name)>20";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				int b_id = rs_name.getInt("b_id");
				String b_name = rs_name.getString("b_name");
				String b_alias = rs_name.getString("b_alias");
				String b_address = rs_name.getString("b_address");
				String str = "��ѧ¥���ƣ���" + b_name + "�����ȹ���";
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
				//�ж� ��ѧ¥����
				String sql_alias = "select * from tmp_buliding where length(b_alias)>20";

				ResultSet rs_alias;
				try {
					rs_alias = st.executeQuery(sql_alias);
					while (rs_alias.next()) {
						String b_name = rs_alias.getString("b_name");
						String b_alias = rs_alias.getString("b_alias");
						String b_address = rs_alias.getString("b_address");
						String str = "��ѧ¥��������" + b_alias + "�����ȹ���";
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
				//�ж� ��ѧ¥��ַ
				String sql_address = "select * from tmp_buliding where length(b_address)>50";

				ResultSet rs_address;
				try {
					rs_address = st.executeQuery(sql_address);
					while (rs_address.next()) {
						int b_id = rs_address.getInt("b_id");
						String b_name = rs_address.getString("b_name");
						String b_alias = rs_address.getString("b_alias");
						String b_address = rs_address.getString("b_address");
						String str = "��ѧ¥��ַ����" + b_address + "�����ȹ���";
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
		// 3.�ж� id�ظ�
		String sql_check_id = "select * from building where b_id in (select b_id from tmp_buliding)";
		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				int b_id = rs_check_id.getInt("b_id");
				String b_name = rs_check_id.getString("b_name");
				String b_alias = rs_check_id.getString("b_alias");
				String b_address = rs_check_id.getString("b_address");
				String str = "��ѧ¥��ţ���" + b_id + "���ظ�����";
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

		// 4.�ж� name�ظ�

		String sql_check_name = "select * from building where b_name in (select b_name from tmp_buliding)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				int b_id = rs_check_name.getInt("b_id");
				String b_name = rs_check_name.getString("b_name");
				String b_alias = rs_check_name.getString("b_alias");
				String b_address = rs_check_name.getString("b_address");
				String str = "��ѧ¥���ƣ���" + b_name + "���ظ�����";
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

		// 5.�ж� name null

		String sql_name_null = "select * from tmp_buliding where b_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				int b_id = rs_name_null.getInt("b_id");
				String b_name = rs_name_null.getString("b_name");
				String b_alias = rs_name_null.getString("b_alias");
				String b_address = rs_name_null.getString("b_address");
				String str = "��ѧ¥��ţ���" + b_id + "���Ľ�ѧ¥����Ϊ����";
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

		// 6.�ж�id null
		String sql_id_null = "select * from tmp_buliding where b_id =''";
		ResultSet rs_id_null;
		try {
			rs_id_null = st.executeQuery(sql_id_null);
			while (rs_id_null.next()) {
				int b_id = rs_id_null.getInt("b_id");
				String b_name = rs_id_null.getString("b_name");
				String b_alias = rs_id_null.getString("b_alias");
				String b_address = rs_id_null.getString("b_address");
				String str = "��ѧ¥���ƣ���" + b_name + "���Ľ�ѧ¥���Ϊ����";
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
