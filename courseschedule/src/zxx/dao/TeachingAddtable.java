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
	 * ���ļ���ȡ���ݲ����������ʱ����жϸ�ʽ
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

		// ����tmp_department��ʱ��
		create_teachingplan(con);
		// ��excel��ȡ�ֶ�����
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
					get_throw.add(tp_name+"��רҵ������ƥ���쳣");
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
		// �ж���ʱ���ڸ�ʽ
//		get_throw = format_teachingplan(con);

		if (get_throw.size() == 0) {
			// û����ʱ����������ӵ�department��

			try {
			//	get_throw.add(add_department(con));
				if(add_teachingplan(con)==0)
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

	private static void create_teachingplan(Connection con) {

		// ������ݿ������Ϊ�գ��򷵻ؿ�
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
		// ��������ִ��SQL��䣬����Ӱ��ļ�¼��Ŀ��

	}

	/**
	 * ������ݵ�department��
	 * 
	 * 
	 */

	public static int add_teachingplan(Connection con) {
		//�Ѳ�ѯ����ʱ���������ӵ�tmp_departemt
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
		// ��������ִ��SQL��䣬����Ӱ��ļ�¼��Ŀ��
		if (n > 0) {
			// System.out.println("����ɹ�");
			return 1;
		} else {
			// System.out.println("����ʧ��");
			return 0;
		}
	}

	/**
	 * ���excel���ݵ���ʱ����
	 * 
	 * 
	 */
	private static int add_tmp_teachingplan(Connection con, String tp_id,String tp_name,
			String m_id,String tp_mark)  {

		String sql = "INSERT INTO tmp_teachingplan (tp_id,tp_name,m_id,tp_mark) VALUES (" + "'"
				+ tp_id + "'" + ",'" + tp_name + "','" + m_id + "','" + tp_mark + "')";
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

		// ѭ��������Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
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
				teachingplan = new Teachingplan();
				// ѭ����Cell
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

	private static List<String> format_teachingplan(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.�ж� ��ѧ�ƻ����
		String sql_id = "select * from tmp_teachingplan where tp_id regexp '[^0-9{6}]'";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				System.out.println(1);
				String tp_id = rs_id.getString("tp_id");
				String str = "��ѧ�ƻ���ţ���" + tp_id + "����ʽ����";
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

//		// 1.�ж� ��ѧ�ƻ�����
//		String sql_oid = "select * from tmp_teachingplan where m_id regexp '[^0-9{4}]'";
//		try {
//			st = con.createStatement();
//			ResultSet rso_id = st.executeQuery(sql_oid);
//			while (rso_id.next()) {
//				String m_id = rso_id.getString("m_id");
//				String str = "רҵ��ţ���" + m_id + "����ʽ����";
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
				String str = "רҵ��ţ���" + m_id + "�����ȹ���";
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
		String sql = "select * from tmp_teachingplan where length(tp_mark)>200";

		ResultSet rs_mark;
		try {
			rs_mark = st.executeQuery(sql);
			while (rs_mark.next()) {
				String tp_mark = rs_mark.getString("tp_mark");
				String str = "��ע���ƣ���" + tp_mark + "�����ȹ���";
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

		// 2.�ж� ��ע����
		String sql1 = "select * from tmp_teachingplan where length(tp_name)>50";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql1);
			while (rs_name.next()) {
				String tp_name = rs_name.getString("tp_name");
				String str = "��ѧ�ƻ����ƣ���" + tp_name + "�����ȹ���";
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

		String sql_check_name = "select * from teachingplan where tp_name in (select tp_name from tmp_teachingplan)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				String tp_name = rs_check_name.getString("tp_name");
				String str = "רҵ���ƣ���" + tp_name + "���ظ�����";
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

		String sql_name_null = "select * from tmp_teachingplan where tp_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				String tp_name = rs_name_null.getString("tp_name");
				String str = "רҵ����:��" + tp_name + "����רҵ����Ϊ����";
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
				String str = "���ұ�ţ���" + m_id + "���Ŀ��ұ��Ϊ����";
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

		String sql_tp_id_null = "select * from tmp_teachingplan where tp_id =''";

		ResultSet rs_tp_id;
		try {
			rs_tp_id = st.executeQuery(sql_tp_id_null);
			while (rs_tp_id.next()) {
				String m_id = rs_tp_id.getString("m_id");
				String str = "רҵ��ţ���" + m_id + "����רҵ���Ϊ����";
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
