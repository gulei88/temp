package ggc.dao;

import global.dao.Courseaccess;
import global.dao.Databaseconnection;
import global.dao.Departmentaccess;
import global.dao.Officeaccess;
import global.model.Course;
import global.model.Department;
import global.model.Office;
import global.model.View_course;

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

public class GGC_CourseAddTable {
	private static String file;
	private static List<String> get_throw = new ArrayList<String>();
	public static void main(String[] args) {

		List<String> course_List = getCourseThrow("C:/Users/hp/Desktop/course.xls");

		for (String item : course_List) {
			System.out.println(item);
		}

}
	public static List<String> getCourseThrow(String file){

		Connection con = null;
		GGC_CourseAddTable GGC_CourseAddTable = new GGC_CourseAddTable();
		Course course = null;
		List<Course> list = new ArrayList<Course>();
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
		create_course(con);
		get_throw=format_course(con);
		// ��excel��ȡ�ֶ�����
		list = GGC_CourseAddTable.readXls(file);
		List<Department>a;

		for (int i = 0; i < list.size(); i++) {
			course = (Course) list.get(i);
			try {
				int num = add_tmp_course(con,course.getCou_id(),course.getTp_id(),course.getCou_category(),course.getCou_name(),course.getCou_credit(),course.getCou_theoryhour(),course.getCou_experimentalhours(),course.getCou_practicehour(),course.getCou_semester(),course.getCou_type(),course.getD_id());
				//if num <0
				String d_id = course.getD_id();
				a=Departmentaccess.getDepartment(d_id);
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
//		get_throw = format_office(con);
		get_throw.addAll(format_course(con));
		if (get_throw.size() == 0) {
			// û����ʱ����������ӵ�department��

			try {
//				get_throw.add(add_department(con));
				if(add_course(con)==0)
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
	
//	private static int add_tmp_course(Connection con, String cou_id,
//			String tp_id, String cou_category, String cou_name,
//			float cou_credit, int cou_theoryhour, int cou_experimentalhours,
//			int cou_practicehour, int cou_semester, int cou_type, String d_id) {
//		// TODO Auto-generated method stub
//		String sql = "INSERT INTO tmp_course (cou_id,tp_id,cou_category,cou_name,cou_credit,cou_theoryhour, cou_experimentalhours,cou_practicehour,cou_semester,cou_type,d_id ) " +
//				"VALUES (" + "'"+ cou_id + "'" + ",'" + tp_id + "'"+ ",'" + cou_category + "'"+ ",'" + cou_name + "'"+ ",'" + cou_credit + "'"+ ",'" +cou_theoryhour + "'"+ ",'" +cou_experimentalhours + "'"+ ",'" + cou_practicehour + "'"+ ",'" + cou_semester + "'"+ ",'" + d_id + "')";
//		// �������ݿ���������
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
//		// ��������ִ��SQL��䣬����Ӱ��ļ�¼��Ŀ��
//
//	}
	
	/**
	 * ������ʱ��
	 * 
	 * 
	 */

	private static void create_course(Connection con) {

		// ������ݿ������Ϊ�գ��򷵻ؿ�
		if (con == null)
			return; 
		String sql = "CREATE TEMPORARY TABLE tmp_course ("
				+"cou_id varchar(15) NOT NULL,"
				+"tp_id char(6) ,"
				+"cou_category varchar(50),"
				+"cou_name  varchar(50) ,"
				+"cou_credit float,"
				+"cou_theoryhour int(10),"
				+"cou_experimentalhours int(10),"
				+"cou_practicehour int(10),"
				+"cou_semester int(10),"
				+ "cou_type int(11),"
				+ "d_id char(2))";

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

	public static int add_course(Connection con) {
		//�Ѳ�ѯ����ʱ���������ӵ�tmp_departemt
		String sql = "insert into course select * from tmp_course";
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
	private static int add_tmp_course(Connection con, String cou_id,
			String tp_id, String cou_category, String cou_name,
			float cou_credit, int cou_theoryhour, int cou_experimentalhours,
			int cou_practicehour, int cou_semester, int cou_type, String d_id) {

//		String sql = "INSERT INTO tmp_office (o_id,d_id, o_name) VALUES (" + "'"
//				+ o_id + "'" + ",'" + d_id + "'"+ ",'" + o_name + "')";
		String sql = "INSERT INTO tmp_course (cou_id,tp_id,cou_category,cou_name,cou_credit,cou_theoryhour, cou_experimentalhours,cou_practicehour,cou_semester,cou_type,d_id ) " +
				"VALUES (" + "'"+ cou_id + "'" + ",'" + tp_id + "'"+ ",'" + cou_category + "'"+ ",'" + cou_name + "'"+ ",'" + cou_credit + "'"+ ",'" +cou_theoryhour + "'"+ ",'" +cou_experimentalhours + "'"+ ",'" + cou_practicehour + "'"+ ",'" + cou_semester + "'"+ ",'" + cou_type + "'"+ ",'" + d_id + "')";
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
	private List<Course> readXls(String file) {
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

		Course course = null;
		List<Course> list = new ArrayList<Course>();

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
				course = new Course();
				// ѭ����Cell
				// for (int cellNum = 0; cellNum <=4; cellNum++)
				
				HSSFCell couid = hssfRow.getCell(0);
				System.out.println(couid);
				if (couid == null) {
					continue;
				}

				course.setCou_id(getValue(couid));
				
				HSSFCell tpid = hssfRow.getCell(1);
				System.out.println(tpid);
				if (tpid == null) {
					continue;
				}
				course.setTp_id(getValue(tpid));
				
				HSSFCell category = hssfRow.getCell(2);
				System.out.println(category);
				if (category == null) {
					continue;
				}
				course.setCou_category(getValue(category));
				
				HSSFCell xm = hssfRow.getCell(3);
				if (xm == null) {
					String null_str = "";
					course.setCou_name(null_str);
				} else
					course.setCou_name(getValue(xm));
				
				HSSFCell credit = hssfRow.getCell(4);
				System.out.println(credit);
				if (credit == null) {
					continue;
				}
				course.setCou_credit(Float.valueOf(getValue(credit)));
				
				HSSFCell theoryhour = hssfRow.getCell(5);
				System.out.println(theoryhour);
				if (theoryhour == null) {
					continue;
				}
				course.setCou_theoryhour(Integer.parseInt(getValue(theoryhour).toString()));
				
				HSSFCell experimentalhours = hssfRow.getCell(6);
				System.out.println(experimentalhours);
				if (experimentalhours == null) {
					continue;
				}
				course.setCou_experimentalhours(Integer.parseInt(getValue(experimentalhours).toString()));
				
				HSSFCell practicehour = hssfRow.getCell(7);
				System.out.println(practicehour);
				if (practicehour == null) {
					continue;
				}
				course.setCou_practicehour(Integer.parseInt(getValue(practicehour).toString()));
				
				HSSFCell semester = hssfRow.getCell(8);
				System.out.println(semester);
				if (semester == null) {
					continue;
				}
				course.setCou_semester(Integer.parseInt(getValue(semester).toString()));
				
				HSSFCell type = hssfRow.getCell(9);
				System.out.println(type);
				if (type == null) {
					continue;
				}
				course.setCou_type(Integer.parseInt(getValue(type).toString()));
				
				HSSFCell did = hssfRow.getCell(10);
				System.out.println(did);
				if (did == null) {
					continue;
				}
				course.setD_id(getValue(did));
				
				list.add(course);
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
	private static List<String> format_course(Connection con) {
		List<String> throw_content = new ArrayList<String>();

		// 1.�ж� �γ̱��
		String sql_id = "select * from tmp_course where cou_id regexp 'U+^[0-9]{10}'";

		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs_id = st.executeQuery(sql_id);
			while (rs_id.next()) {
				String cou_id = rs_id.getString("cou_id");
				String tp_id = rs_id.getString("tp_id");
				String cou_category = rs_id.getString("cou_category");
				String cou_name = rs_id.getString("cou_name");
				float cou_credit = rs_id.getFloat("cou_credit");
				int cou_theoryhour = rs_id.getInt("cou_theoryhour");
				int cou_experimentalhours = rs_id.getInt("cou_experimentalhours");
				int cou_practicehour = rs_id.getInt("cou_practicehour");
				int cou_semester = rs_id.getInt("cou_semester");
				int cou_type = rs_id.getInt("cou_type");
				String d_id = rs_id.getString("d_id");
				String str = "�γ̱�ţ���" + cou_id + "����ʽ����";
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
//		�ж� ��ѧ�������
		String sql_tpid = "select * from tmp_course where tp_id regexp '[^0-9{6}]'";

		Statement st_tp = null;
		try {
			st = con.createStatement();
			ResultSet rs_tpid = st.executeQuery(sql_id);
			while (rs_tpid.next()) {
				String cou_id = rs_tpid.getString("cou_id");
				String tp_id = rs_tpid.getString("tp_id");
				String cou_category = rs_tpid.getString("cou_category");
				String cou_name = rs_tpid.getString("cou_name");
				float cou_credit = rs_tpid.getFloat("cou_credit");
				int cou_theoryhour = rs_tpid.getInt("cou_theoryhour");
				int cou_experimentalhours = rs_tpid.getInt("cou_experimentalhours");
				int cou_practicehour = rs_tpid.getInt("cou_practicehour");
				int cou_semester = rs_tpid.getInt("cou_semester");
				int cou_type = rs_tpid.getInt("cou_type");
				String d_id = rs_tpid.getString("d_id");
				String str = "��ѧ������ţ���" + tp_id + "����ʽ����";
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
//		String sql_did = "select * from tmp_course where d_id regexp '[^0-9{2}]'";
//
//		Statement st_d = null;
//		try {
//			st = con.createStatement();
//			ResultSet rs_did = st.executeQuery(sql_id);
//			while (rs_did.next()) {
//				String cou_id = rs_did.getString("cou_id");
//				String tp_id = rs_did.getString("tp_id");
//				String cou_category = rs_did.getString("cou_category");
//				String cou_name = rs_did.getString("cou_name");
//				float cou_credit = rs_did.getFloat("cou_credit");
//				int cou_theoryhour = rs_did.getInt("cou_theoryhour");
//				int cou_experimentalhours = rs_did.getInt("cou_experimentalhours");
//				int cou_practicehour = rs_did.getInt("cou_practicehour");
//				int cou_semester = rs_did.getInt("cou_semester");
//				int cou_type = rs_did.getInt("cou_type");
//				String d_id = rs_did.getString("d_id");
//				String str = "ѧԺ��ţ���" + d_id + "����ʽ����";
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
		// �жϿγ̷���
		String sql_ca = "select * from tmp_course where length(cou_category)>50";

		ResultSet rs_category;
		try {
			rs_category = st.executeQuery(sql_ca);
			while (rs_category.next()) {
				String cou_id = rs_category.getString("cou_id");
				String tp_id = rs_category.getString("tp_id");
				String cou_category = rs_category.getString("cou_category");
				String cou_name = rs_category.getString("cou_name");
				float cou_credit = rs_category.getFloat("cou_credit");
				int cou_theoryhour = rs_category.getInt("cou_theoryhour");
				int cou_experimentalhours = rs_category.getInt("cou_experimentalhours");
				int cou_practicehour = rs_category.getInt("cou_practicehour");
				int cou_semester = rs_category.getInt("cou_semester");
				int cou_type = rs_category.getInt("cou_type");
				String d_id = rs_category.getString("d_id");
				String str = "�γ���𣺡�" + cou_category + "�����ȹ���";
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

		// 2.�жϿγ�����
		String sql = "select * from tmp_course where length(cou_name)>50";

		ResultSet rs_name;
		try {
			rs_name = st.executeQuery(sql);
			while (rs_name.next()) {
				String cou_id = rs_name.getString("cou_id");
				String tp_id = rs_name.getString("tp_id");
				String cou_category = rs_name.getString("cou_category");
				String cou_name = rs_name.getString("cou_name");
				float cou_credit = rs_name.getFloat("cou_credit");
				int cou_theoryhour = rs_name.getInt("cou_theoryhour");
				int cou_experimentalhours = rs_name.getInt("cou_experimentalhours");
				int cou_practicehour = rs_name.getInt("cou_practicehour");
				int cou_semester = rs_name.getInt("cou_semester");
				int cou_type = rs_name.getInt("cou_type");
				String d_id = rs_name.getString("d_id");
				String str = "�������ƣ���" + cou_name + "�����ȹ���";
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
		String sql_check_id = "select * from course where cou_id in (select cou_id from tmp_course)";

		ResultSet rs_check_id;
		try {
			rs_check_id = st.executeQuery(sql_check_id);
			while (rs_check_id.next()) {
				String cou_id = rs_check_id.getString("cou_id");
				String tp_id = rs_check_id.getString("tp_id");
				String cou_category = rs_check_id.getString("cou_category");
				String cou_name = rs_check_id.getString("cou_name");
				float cou_credit = rs_check_id.getFloat("cou_credit");
				int cou_theoryhour = rs_check_id.getInt("cou_theoryhour");
				int cou_experimentalhours = rs_check_id.getInt("cou_experimentalhours");
				int cou_practicehour = rs_check_id.getInt("cou_practicehour");
				int cou_semester = rs_check_id.getInt("cou_semester");
				int cou_type = rs_check_id.getInt("cou_type");
				String d_id = rs_check_id.getString("d_id");
				String str = "�γ̱�ţ���" + cou_id + "���ظ�����";
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

		String sql_check_name = "select * from course where cou_name in (select cou_name from tmp_course)";

		ResultSet rs_check_name;
		try {
			rs_check_name = st.executeQuery(sql_check_name);
			while (rs_check_name.next()) {
				String cou_id = rs_check_name.getString("cou_id");
				String tp_id = rs_check_name.getString("tp_id");
				String cou_category = rs_check_name.getString("cou_category");
				String cou_name = rs_check_name.getString("cou_name");
				float cou_credit = rs_check_name.getFloat("cou_credit");
				int cou_theoryhour = rs_check_name.getInt("cou_theoryhour");
				int cou_experimentalhours = rs_check_name.getInt("cou_experimentalhours");
				int cou_practicehour = rs_check_name.getInt("cou_practicehour");
				int cou_semester = rs_check_name.getInt("cou_semester");
				int cou_type = rs_check_name.getInt("cou_type");
				String d_id = rs_check_name.getString("d_id");
				String str = "�γ����ƣ���" + cou_name + "���ظ�����";
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

		String sql_name_null = "select * from tmp_course where cou_name =''";

		ResultSet rs_name_null;
		try {
			rs_name_null = st.executeQuery(sql_name_null);
			while (rs_name_null.next()) {
				String cou_id = rs_name_null.getString("cou_id");
				String tp_id = rs_name_null.getString("tp_id");
				String cou_category = rs_name_null.getString("cou_category");
				String cou_name = rs_name_null.getString("cou_name");
				float cou_credit = rs_name_null.getFloat("cou_credit");
				int cou_theoryhour = rs_name_null.getInt("cou_theoryhour");
				int cou_experimentalhours = rs_name_null.getInt("cou_experimentalhours");
				int cou_practicehour = rs_name_null.getInt("cou_practicehour");
				int cou_semester = rs_name_null.getInt("cou_semester");
				int cou_type = rs_name_null.getInt("cou_type");
				String d_id = rs_name_null.getString("d_id");
				String str = "�γ̱�ţ���" + cou_id + "���Ŀγ�����Ϊ����";
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
		String sql_id_null = "select * from tmp_course where cou_id =''";
		ResultSet rs_id_null;
		try {
			rs_id_null = st.executeQuery(sql_id_null);
			while (rs_id_null.next()) {
				String cou_id = rs_id_null.getString("cou_id");
				String tp_id = rs_id_null.getString("tp_id");
				String cou_category = rs_id_null.getString("cou_category");
				String cou_name = rs_id_null.getString("cou_name");
				float cou_credit = rs_id_null.getFloat("cou_credit");
				int cou_theoryhour = rs_id_null.getInt("cou_theoryhour");
				int cou_experimentalhours = rs_id_null.getInt("cou_experimentalhours");
				int cou_practicehour = rs_id_null.getInt("cou_practicehour");
				int cou_semester = rs_id_null.getInt("cou_semester");
				int cou_type = rs_id_null.getInt("cou_type");
				String d_id = rs_id_null.getString("d_id");
				String str = "�γ����ƣ���" + cou_name + "���Ŀγ̱��Ϊ����";
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
