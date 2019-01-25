package ggc.dao;

import global.dao.Databaseconnection;
import global.dao.Officeaccess;
import global.model.Department;
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
import org.apache.poi.xslf.model.geom.AddDivideExpression;


public class GGC_OfficeAddTable {
	private static List<String> get_throw = new ArrayList<String>();
	public static void main(String[] args) {

		List<String> office_List = getOfficeThrow("C:/Users/hp/Desktop/office.xls");

		for (String item : office_List) {
			System.out.println(item);
		}

}
	public static List<String> getOfficeThrow(String file){

		Connection con = null;
		GGC_OfficeAddTable GGC_OfficeAddTable = new GGC_OfficeAddTable();
		Office office = null;
		List<Office> list = new ArrayList<Office>();
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

		// ����tmp_department��ʱ��
		create_office(con);
		get_throw=format_office(con);
		// ��excel��ȡ�ֶ�����
		list = GGC_OfficeAddTable.readXls(file);
		List<Office>a;

		for (int i = 0; i < list.size(); i++) {
			office = (Office) list.get(i);

			try {
				int num = add_tmp_office(con,office.getO_id(),office.getD_id(),office.getO_name());
				//if num <0
				String d_id = office.getD_id();
				a=Officeaccess.getoffice(d_id);
				
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

		// �ж���ʱ���ڸ�ʽ
//		get_throw = format_office(con);
		get_throw.addAll(format_office(con));
		if (get_throw.size() == 0) {
			// û����ʱ����������ӵ�department��

			try {
//				get_throw.add(add_department(con));
				if(add_office(con)==0)
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
	 * ������ʱ��
	 * 
	 * 
	 */

	private static void create_office(Connection con) {

		// ������ݿ������Ϊ�գ��򷵻ؿ�
		if (con == null)
			return;
		String sql = "CREATE TEMPORARY TABLE tmp_office ("
				+"o_id CHAR(4) NOT NULL,"
				+ "d_id CHAR(2) NOT NULL,"
				+ "o_name varchar(20)  NOT NULL)";

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

	public static int add_office(Connection con) {
		//�Ѳ�ѯ����ʱ���������ӵ�tmp_departemt
		String sql = "insert into office select * from tmp_office";
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
	private static int add_tmp_office(Connection con,String o_id, String d_id,
			String o_name) {

		String sql = "INSERT INTO tmp_office (o_id,d_id, o_name) VALUES (" + "'"
				+ o_id + "'" + ",'" + d_id + "'"+ ",'" + o_name + "')";
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
	private List<Office> readXls(String file) {
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

		Office office = null;
		List<Office> list = new ArrayList<Office>();

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
				office = new Office();
				// ѭ����Cell
				// for (int cellNum = 0; cellNum <=4; cellNum++)		
				HSSFCell oid = hssfRow.getCell(0);
				System.out.println(oid);
				if (oid == null) {
					continue;
				}

				office.setO_id(getValue(oid));
				HSSFCell id = hssfRow.getCell(1);
				System.out.println(id);
				if (id == null) {
					continue;
				}

				office.setD_id(getValue(id));
				HSSFCell xm = hssfRow.getCell(2);
				if (xm == null) {
					String null_str = "";
					office.setO_name(null_str);
				} else
					office.setO_name(getValue(xm));

				list.add(office);
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
	private static List<String> format_office(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.�ж� ���ű��
		String sql_id = "select * from tmp_office where d_id regexp '[^0-9{2}]'";

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				String d_id = rs_id.getString("d_id");
				String o_id = rs_id.getString("o_id");
				String o_name = rs_id.getString("o_name");
				String str = "���ű�ţ���" + d_id + "����ʽ����";
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
//		�ж� ���ұ��
		String sql_oid = "select * from tmp_office where o_id regexp '[^0-9{4}]'";

		Statement st_o = null;
		try {
			st = con.createStatement();
			ResultSet rs_oid = st.executeQuery(sql_id);
			while (rs_oid.next()) {
				String o_id = rs_oid.getString("o_id");
				String d_id = rs_oid.getString("d_id");
				String o_name = rs_oid.getString("o_name");
				String str = "���ұ�ţ���" + o_id + "����ʽ����";
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
		// 2.�ж� ��������
		String sql = "select * from tmp_office where length(o_name)>30";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				String o_id = rs_name.getString("o_id");
				String d_id = rs_name.getString("d_id");
				String o_name = rs_name.getString("o_name");
				String str = "�������ƣ���" + o_name + "�����ȹ���";
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
		String sql_check_id = "select * from office where o_id in (select o_id from tmp_office)";

		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				String d_id = rs_check_id.getString("d_id");
				String o_id = rs_check_id.getString("o_id");
				String o_name = rs_check_id.getString("o_name");
				String str = "���ұ�ţ���" + o_id + "���ظ�����";
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

		String sql_check_name = "select * from office where o_name in (select o_name from tmp_office)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				String d_id = rs_check_name.getString("d_id");
				String o_id = rs_check_name.getString("o_id");
				String o_name = rs_check_name.getString("o_name");
				String str = "�������ƣ���" + o_name + "���ظ�����";
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

		String sql_name_null = "select * from tmp_office where o_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				String d_id = rs_name_null.getString("d_id");
				String o_id = rs_name_null.getString("o_id");
				String o_name = rs_name_null.getString("o_name");
				String str = "���ұ�ţ���" + o_id + "���Ŀ�������Ϊ����";
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
		String sql_id_null = "select * from tmp_office where o_id =''";
		ResultSet rs_id_null;
		try {
			rs_id_null = st.executeQuery(sql_id_null);
			while (rs_id_null.next()) {
				String d_id = rs_id_null.getString("d_id");
				String o_id = rs_id_null.getString("o_id");
				String o_name = rs_id_null.getString("o_name");
				String str = "�������ƣ���" + o_name + "���Ŀ��ұ��Ϊ����";
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
