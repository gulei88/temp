package zxx.dao;

import global.dao.Buildingaccess;
import global.dao.Classroomaccess;
import global.dao.Databaseconnection;
import global.dao.Departmentaccess;
import global.dao.Majoraccess;
import global.model.Buliding;
import global.model.Classroom;
import global.model.Department;
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

public class ClassroomAddTable {
	private static List<String> get_throw = new ArrayList<String>();
	public static void main(String[] args) {

		List<String> department_List = getDepartmentThrow("C:/Users/Administrator/Desktop/department1.xls");
		for (String item : department_List) {
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
		ClassroomAddTable classroomAddTable = new ClassroomAddTable();
		Classroom classroom = null;
		List<Classroom> list = null;
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
		create_department(con);
		get_throw = format_classroom(con);
		// ��excel��ȡ�ֶ�����
		list = classroomAddTable.readXls(file);
		List<Department> departments;
		ArrayList<Buliding> bulidings;
		for (int i = 0; i < list.size(); i++) {
			classroom = (Classroom) list.get(i);
			
			int cr_id =classroom.getCr_id();
			try {
				int num = add_tmp_classroom(con, cr_id,
						classroom.getD_id(),classroom.getCr_name(),classroom.getCt_id(),classroom.getCr_seating(),classroom.getB_id());
				String d_id = classroom.getD_id();
				int b_id = classroom.getB_id();
				bulidings = Buildingaccess.getbuliding(b_id);
				departments = Departmentaccess.getDepartment(d_id);
				if (departments.size() == 0) {
					get_throw.add(d_id+"���ű�����ƥ���쳣");
				}
				if (bulidings.size() == 0) {
					get_throw.add(b_id+"������ƥ���쳣");
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
		get_throw.addAll(format_classroom(con));

		if (get_throw.size() == 0) {
			// û����ʱ����������ӵ�department��

			try {
			//	get_throw.add(add_department(con));
				if(add_classroom(con)==0)
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

	private static void create_department(Connection con) {

		// ������ݿ������Ϊ�գ��򷵻ؿ�
		if (con == null)
			return;
		String sql = "CREATE TEMPORARY TABLE tmp_classroom ("
				+ "cr_id int(20) NOT NULL,"
				+ "d_id varchar(20) NOT NULL,"
				+ "cr_name varchar(100) NOT NULL,"
				+ "ct_id int(20) NOT NULL,"
				+ "cr_seating int(20) NOT NULL,"
				+ "b_id int(20) NOT NULL)";

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

	public static int add_classroom(Connection con) {
		//�Ѳ�ѯ����ʱ���������ӵ�tmp_departemt
		String sql = "insert into classroom select * from tmp_classroom";
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
	private static int add_tmp_classroom(Connection con, int cr_id,
			String d_id,String cr_name,int ct_id,int cr_seating,int b_id) {
		String sql = "INSERT INTO tmp_classroom (cr_id, d_id,cr_name,ct_id,cr_seating,b_id) VALUES (" + ""
		+ cr_id + "" + ",'" + d_id + "'," + cr_name + "," + ct_id + "," + cr_seating + "," + b_id + ")";
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
	 List<Classroom> readXls(String file) {
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

		Classroom classroom = null;
		List<Classroom> list = new ArrayList<Classroom>();

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
				classroom = new Classroom();
				// ѭ����Cell
				HSSFCell cr_id = hssfRow.getCell(0);
				if (cr_id == null) {
					continue;
				}
				classroom.setCr_id(Integer.parseInt(getValue(cr_id).toString()));
				
				
				HSSFCell d_id = hssfRow.getCell(1);
				if (d_id == null) {
					continue;
				}
				
				classroom.setD_id(getValue(d_id));

				
				
				HSSFCell ct_id = hssfRow.getCell(3);
				if (ct_id == null) {
					continue;
				}
				classroom.setCt_id(Integer.parseInt(getValue(ct_id).toString()));
				HSSFCell cr_seating = hssfRow.getCell(4);
				if (cr_seating == null) {
					continue;
				}
				
				
				classroom.setCr_seating(Integer.parseInt(getValue(cr_seating).toString()));
				HSSFCell b_id = hssfRow.getCell(5);
				if (b_id == null) {
					continue;
				}
				classroom.setB_id(Integer.parseInt(getValue(b_id).toString()));
				
				HSSFCell xm = hssfRow.getCell(2);
				if (xm == null) {
					String null_str = "";
					classroom.setCr_name(null_str);
				} else
					classroom.setCr_name(getValue(xm));

				list.add(classroom);
//				System.out.println(classroom.getCr_id());
			}
		}
		return list;
	}

	private int Integer(String value) {
		// TODO Auto-generated method stub
		return 0;
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
			return String.valueOf(hssfCell.getColumnIndex());
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

	private static List<String> format_classroom(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.�ж� ���ű��
		String sql_id = "select * from tmp_classroom where cr_id regexp '[^0-9{10}]'";

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				String cr_id = rs_id.getString("cr_id");
				String str = "���ű�ţ���" + cr_id + "����ʽ����";
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
		String sql = "select * from tmp_classroom where length(cr_name)>50";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				String cr_name = rs_name.getString("cr_name");
				String str = "�������ƣ���" + cr_name + "�����ȹ���";
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
		
		//�ж�id����
		String sql_id_channg = "select * from tmp_classroom where length(d_id)>2";

		ResultSet rs_id_chang;
		try {
			rs_id_chang = st.executeQuery(sql_id_channg);
			while (rs_id_chang.next()) {
				String d_id = rs_id_chang.getString("d_id");
				String str = "���ű�ţ���" + d_id + "�����ȹ���";
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
		String sql_check_id = "select * from classroom where cr_id in (select cr_id from tmp_classroom)";

		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				String cr_id = rs_check_id.getString("cr_id");
				String str = "���ұ�ţ���" + cr_id + "���ظ�����";
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

		String sql_check_name = "select * from classroom where cr_name in (select cr_name from tmp_classroom)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				String cr_name = rs_check_name.getString("cr_name");
				String str = "�������ƣ���" + cr_name + "���ظ�����";
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

		String sql_name_null = "select * from tmp_classroom where cr_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				String cr_id = rs_name_null.getString("cr_id");
				String str = "���ұ�ţ���" + cr_id + "���Ľ�������Ϊ����";
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
		String sql_id_null = "select * from tmp_classroom where d_id =''";
		ResultSet rs_id;
		try {
			rs_id = st.executeQuery(sql_id_null);
			while (rs_id.next()) {
				String d_id = rs_id.getString("d_id");
				String d_name = rs_id.getString("d_name");
				String str = "�������ƣ���" + d_name + "���Ĳ��ű��Ϊ����";
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
