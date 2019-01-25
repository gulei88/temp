package ggc.dao;

import global.dao.Databaseconnection;
import global.dao.Officeaccess;
import global.dao.Teacheraccess;
import global.model.Office;
import global.model.Teacher;
import global.model.View_teacher;

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

public class GGC_TeacherAddTable {
	private static String file;
	private static List<String> get_throw = new ArrayList<String>();
	public static void main(String[] args) {

		List<String> teacher_List = getteacherThrow("C:/Users/hp/Desktop/teacher.xls");

		for (String item : teacher_List) {
			System.out.println(item);
		}

}
	public static List<String> getteacherThrow(String file){

		Connection con = null;
		GGC_TeacherAddTable ggc_TeacherAddTable = new GGC_TeacherAddTable();
		Teacher teacher = null;
		List<Teacher> list = new ArrayList<Teacher>();
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
		create_teacher(con);
		get_throw=format_teacher(con);
		// ��excel��ȡ�ֶ�����
		list = ggc_TeacherAddTable.readXls(file);
		List<Office> a;


		for (int i = 0; i < list.size(); i++) {
			teacher = (Teacher) list.get(i);

			try {
				int num = add_tmp_teacher(con, teacher.getT_id(), teacher.getT_name(), teacher.getT_password(), teacher.getT_power(), teacher.getO_id(),teacher.getT_tel());
				//if num <0
				String o_id= teacher.getO_id();
				a=Officeaccess.getoffice_(o_id);
				if (a.size()==0) {
					get_throw.add("������ƥ���쳣");
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

		// �ж���ʱ���ڸ�ʽ
//		get_throw = format_teacher(con);
		get_throw.addAll(format_teacher(con));
		if (get_throw.size() == 0) {
			// û����ʱ����������ӵ�department��

			try {
//				get_throw.add(add_department(con));
				if(add_teacher(con)==0)
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

	private static void create_teacher(Connection con) {

		// ������ݿ������Ϊ�գ��򷵻ؿ�
		if (con == null)
			return;
		String sql = "CREATE TEMPORARY TABLE tmp_teacher ("
				+"t_id varchar(6) NOT NULL,"
				+ "t_name varchar(20)  NOT NULL,"
				+ "t_password varchar(20) NOT NULL,"
				+ "t_power varchar(10) NOT NULL,"
				+ "o_id char(4) NOT NULL,"
				+ "t_tel varchar(10) NOT NULL)";

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

	public static int add_teacher(Connection con) {
		//�Ѳ�ѯ����ʱ���������ӵ�tmp_departemt
		String sql = "insert into teacher select * from tmp_teacher";
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
	private static int add_tmp_teacher(Connection con,String t_id, String t_name,
			String t_password,String t_power,String o_id,String t_tel) {

		String sql = "INSERT INTO tmp_teacher (t_id,t_name,t_password,t_power,o_id,t_tel) VALUES (" + "'"
				+ t_id + "'" + ",'" + t_name + "'"+ ",'" + t_password + "','" + t_power + "','" + o_id + "','" + t_tel + "')";
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
	private List<Teacher> readXls(String file) {
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

		Teacher teacher = null;
		List<Teacher> list = new ArrayList<Teacher>();

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
				teacher = new Teacher();
				// ѭ����Cell
				// for (int cellNum = 0; cellNum <=4; cellNum++)		
				HSSFCell tid = hssfRow.getCell(0);
				System.out.println(tid);
				if (tid == null) {
					continue;
				}
				teacher.setT_id(getValue(tid));
				
				HSSFCell xm = hssfRow.getCell(1);
				if (xm == null) {
					String null_str = "";
					teacher.setT_name(null_str);
				} else
					teacher.setT_name(getValue(xm));

				list.add(teacher);
				
				HSSFCell paw = hssfRow.getCell(2);
				System.out.println(paw);
				if (paw == null) {
					continue;
				}

				teacher.setT_password(getValue(paw));
				
				HSSFCell power = hssfRow.getCell(3);
				System.out.println(power);
				if (power == null) {
					continue;
				}

				teacher.setT_power(getValue(power));
				
				HSSFCell id = hssfRow.getCell(4);
				System.out.println(id);
				if (id == null) {
					continue;
				}

				teacher.setO_id(getValue(id));
				
				HSSFCell tel = hssfRow.getCell(5);
				System.out.println(tel);
				if (tel == null) {
					continue;
				}

				teacher.setT_tel(getValue(tel));
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
	private static List<String> format_teacher(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.�ж� ���ű��
		String sql_id = "select * from tmp_teacher where t_id regexp '[^0-9{6}]'";

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				String t_id = rs_id.getString("d_id");
				String t_name =rs_id.getString("t_name");
				String t_password =rs_id.getString("t_password");
				String t_power =rs_id.getString("t_power");
				String o_id = rs_id.getString("o_id");
				String t_tel = rs_id.getString("t_tel");
				String str = "��ʦ��ţ���" + t_id + "����ʽ����";
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
		String sql_oid = "select * from tmp_teacher where o_id regexp '[^0-9{4}]'";

		Statement st_o = null;
		try {
			st = con.createStatement();
			ResultSet rs_oid = st.executeQuery(sql_id);
			while (rs_oid.next()) {
				String t_id = rs_oid.getString("d_id");
				String t_name =rs_oid.getString("t_name");
				String t_password =rs_oid.getString("t_password");
				String t_power =rs_oid.getString("t_power");
				String o_id = rs_oid.getString("o_id");
				String t_tel = rs_oid.getString("t_tel");
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
		// 2.�ж� ��ʦ����
		String sql = "select * from tmp_teacher where length(t_name)>20";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				String t_id = rs_name.getString("t_id");
				String t_name = rs_name.getString("t_name");
				String t_password =rs_name.getString("t_password");
				String t_power =rs_name.getString("t_power");
				String o_id = rs_name.getString("o_id");
				String t_tel = rs_name.getString("t_tel");
				String str = "�������ƣ���" + t_name + "�����ȹ���";
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
		
		// �ж� ��ʦ����
				String sql_paw = "select * from tmp_teacher where length(t_password)>20";

				ResultSet rs_paw;
				try {
					rs_paw = st.executeQuery(sql);
					while (rs_paw.next()) {
						String t_id = rs_paw.getString("t_id");
						String t_name = rs_paw.getString("t_name");
						String t_password =rs_paw.getString("t_password");
						String t_power =rs_paw.getString("t_power");
						String o_id = rs_paw.getString("o_id");
						String t_tel = rs_paw.getString("t_tel");
						String str = "��ʦ���룺��" + t_password + "�����ȹ���";
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
				
				// �ж� ��ʦȨ��
				String sql_power = "select * from tmp_teacher where length(t_power)>10";

				ResultSet rs_power;
				try {
					rs_power = st.executeQuery(sql);
					while (rs_power.next()) {
						String t_id = rs_power.getString("t_id");
						String t_name = rs_power.getString("t_name");
						String t_password =rs_power.getString("t_password");
						String t_power =rs_power.getString("t_power");
						String o_id = rs_power.getString("o_id");
						String t_tel = rs_power.getString("t_tel");
						String str = "��ʦȨ�ޣ���" + t_power + "�����ȹ���";
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
				
				// �ж� ��ʦ��ϵ�绰
				String sql_tel = "select * from tmp_teacher where length(t_tel)>20";

				ResultSet rs_tel;
				try {
					rs_tel = st.executeQuery(sql);
					while (rs_tel.next()) {
						String t_id = rs_tel.getString("t_id");
						String t_name = rs_tel.getString("t_name");
						String t_password =rs_tel.getString("t_password");
						String t_power =rs_tel.getString("t_power");
						String o_id = rs_tel.getString("o_id");
						String t_tel = rs_tel.getString("t_tel");
						String str = "��ϵ�绰����" + t_tel + "�����ȹ���";
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
		String sql_check_id = "select * from teacher where t_id in (select t_id from tmp_teacher)";

		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				String t_id = rs_check_id.getString("t_id");
				String t_name = rs_check_id.getString("t_name");
				String t_password =rs_check_id.getString("t_password");
				String t_power =rs_check_id.getString("t_power");
				String o_id = rs_check_id.getString("o_id");
				String t_tel = rs_check_id.getString("t_tel");
				String str = "��ʦ��ţ���" + t_id + "���ظ�����";
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

		// 4.�ж� ��ϵ�绰 �ظ�

//		String sql_check_name = "select * from teacher where t_tel in (select o_name from tmp_office)";
//
//		ResultSet rs_check_name;
//		try {
//			rs_check_name = st.executeQuery(sql_check_name);
//			while (rs_check_name.next()) {
//				String d_id = rs_check_name.getString("d_id");
//				String o_id = rs_check_name.getString("o_id");
//				String o_name = rs_check_name.getString("o_name");
//				String str = "�������ƣ���" + o_name + "���ظ�����";
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

		// 5.�ж� name null

		String sql_name_null = "select * from tmp_teacher where t_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				String t_id = rs_name_null.getString("t_id");
				String t_name = rs_name_null.getString("t_name");
				String t_password =rs_name_null.getString("t_password");
				String t_power =rs_name_null.getString("t_power");
				String o_id = rs_name_null.getString("o_id");
				String t_tel = rs_name_null.getString("t_tel");
				String str = "��ʦ��ţ���" + t_id + "���Ľ�ʦ����Ϊ����";
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
		String sql_id_null = "select * from tmp_teacher where t_id =''";
		ResultSet rs_id_null;
		try {
			rs_id_null = st.executeQuery(sql_id_null);
			while (rs_id_null.next()) {
				String t_id = rs_id_null.getString("t_id");
				String t_name = rs_id_null.getString("t_name");
				String t_password =rs_id_null.getString("t_password");
				String t_power =rs_id_null.getString("t_power");
				String o_id = rs_id_null.getString("o_id");
				String t_tel = rs_id_null.getString("t_tel");
				String str = "��ʦ���ƣ���" + t_name + "���Ľ�ʦ���Ϊ����";
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
