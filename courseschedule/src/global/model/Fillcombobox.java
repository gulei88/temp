package global.model;

import global.dao.Buildingaccess;
import global.dao.Classinformationaccess;
import global.dao.Classroomaccess;
import global.dao.Databaseconnection;
import global.dao.Departmentaccess;
import global.dao.Levelaccess;
import global.dao.Majoraccess;
import global.dao.Officeaccess;
import global.dao.Schoolyearaccess;
import global.dao.Teacheraccess;
import global.dao.Teachingplanaccess;
import global.dao.Teaplanaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JComboBox;

import zzt.view.teachingplangrade;

/**
 * ���ཫ������ܵ������б�ؼ�����䷽��������һ��
 * 
 * @author czy
 * 
 */
public class Fillcombobox {
	private static String tp_id;

	/**
	 * ��䲿�������б�
	 * 
	 * @author czy
	 * 
	 * @param jcb
	 *            ����Ҫ���������б�ؼ�
	 */
	public static void filldepartment(JComboBox jcb) {
		// �Ӳ������ݷ����ࡰDepartmentaccess���е��þ�̬������getDepartment����
		// �������ݿ����е����в�����Ϣ����Department�����͵������б��С�
		ArrayList<Department> al = Departmentaccess.getDepartment(null);
		// ������Department�����͵������б��ֱ�ȡ��������Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}

	
	public static void filldepartmentadd(String d_id, JComboBox jcb) {
		// �Ӳ������ݷ����ࡰDepartmentaccess���е��þ�̬������getDepartment����
		// �������ݿ����е����в�����Ϣ����Department�����͵������б��С�
		ArrayList<Department> al = Departmentaccess.getDepartment(d_id);
		// ������Department�����͵������б��ֱ�ȡ��������Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	
	
	public static void filllevel(int m_id,JComboBox jcb) {
		// �Ӳ������ݷ����ࡰDepartmentaccess���е��þ�̬������getDepartment����
		// �������ݿ����е����в�����Ϣ����Department�����͵������б��С�
		ArrayList<Level> al = Levelaccess.getLevels(m_id);
		// ������Department�����͵������б��ֱ�ȡ��������Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	
	
	public static void fillbuilding(int b_id,JComboBox jcb) {
		// �Ӳ������ݷ����ࡰDepartmentaccess���е��þ�̬������getDepartment����
		// �������ݿ����е����в�����Ϣ����Department�����͵������б��С�
		ArrayList<Buliding> al = Buildingaccess.getbuliding(b_id);
		// ������Department�����͵������б��ֱ�ȡ��������Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	
	
	public static void fillclassroomtype(int cr_id,JComboBox jcb) {
		// �Ӳ������ݷ����ࡰDepartmentaccess���е��þ�̬������getDepartment����
		// �������ݿ����е����в�����Ϣ����Department�����͵������б��С�
		ArrayList<Classroom> al = Classroomaccess.getClassroom(cr_id);
		// ������Department�����͵������б��ֱ�ȡ��������Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	/**
	 * �����������б�
	 * 
	 * @author czy
	 * @param d_id
	 *            :���ű��
	 * @param jcb
	 *            ����Ҫ���������б�ؼ�
	 */
	public static void filloffice(String d_id, JComboBox jcb) {
		// �����б�ؼ��������
		jcb.removeAllItems();
		// �ӿ������ݷ����ࡰOfficeaccess���е��þ�̬������getoffice����
		// �������ݿ��ж�Ӧ���ŵ����п�����Ϣ����Office�����͵������б��С�
		ArrayList<Office> al = Officeaccess.getoffice(d_id);
		// ������Office�����͵������б��ֱ�ȡ��������Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
//		System.out.println(al);
	}

	/**
	 * ���רҵ�����б�
	 * 
	 * @author czy
	 * @param o_id
	 *            :���ұ��
	 * @param jcb
	 *            ����Ҫ���������б�ؼ�
	 */
	public static void fillmajor(String o_id, JComboBox jcb) {
		// �����б�ؼ��������
		jcb.removeAllItems();
		// �ӿ������ݷ����ࡰMajoraccess���е��þ�̬������getMajor����
		// �������ݿ��ж�Ӧ���ҵ�����רҵ��Ϣ����Major�����͵������б��С�
		List<Major> al = Majoraccess.getMajor(o_id);
		// ������Major�����͵������б��ֱ�ȡ��רҵ��Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
//		System.out.println(al);
	}

	
	
	
	public static void fillteachingplan(String m_id, JComboBox jcb) {
		// �����б�ؼ��������
		jcb.removeAllItems();
		// �ӿ������ݷ����ࡰMajoraccess���е��þ�̬������getMajor����
		// �������ݿ��ж�Ӧ���ҵ�����רҵ��Ϣ����Major�����͵������б��С�
		ArrayList<Teachingplan> al = Teaplanaccess.getteachingplan(m_id);
		// ������Major�����͵������б��ֱ�ȡ��רҵ��Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	
	
	
	public static void fillTeachingplangrade(JComboBox jcb) {
		// �����б�ؼ��������
		jcb.removeAllItems();
		// �ӿ������ݷ����ࡰMajoraccess���е��þ�̬������getMajor����
		// �������ݿ��ж�Ӧ���ҵ�����רҵ��Ϣ����Major�����͵������б��С�
		ArrayList<Teachingplangrade> al = Teaplanaccess.getTeachingplangrade(tp_id);
		// ������Major�����͵������б��ֱ�ȡ��רҵ��Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	/**
	 * ���ѧ�������б�
	 * 
	 * @author czy
	 * @param jcb
	 *            ����Ҫ���������б�ؼ�
	 */
	public static void fillschoolyear(JComboBox jcb) {
		// �����б�ؼ��������
		jcb.removeAllItems();

		// �ӿ������ݷ����ࡰMajoraccess���е��þ�̬������getMajor����
		// �������ݿ��ж�Ӧ���ҵ�����רҵ��Ϣ����Major�����͵������б��С�
		ArrayList<Schoolyear> al = Schoolyearaccess.getSchoolyear("");
		// ������Major�����͵������б��ֱ�ȡ��רҵ��Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
		setschoolyearselected(jcb);

	}
	
	
	public static void classinformation(JComboBox jcb) {
		// �����б�ؼ��������
		jcb.removeAllItems();
		// �ӿ������ݷ����ࡰMajoraccess���е��þ�̬������getMajor����
		// �������ݿ��ж�Ӧ���ҵ�����רҵ��Ϣ����Major�����͵������б��С�
		ArrayList<View_classinformation> al = Classinformationaccess.getView_classinformation("");
		// ������Major�����͵������б��ֱ�ȡ��רҵ��Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
		setschoolyearselected(jcb);

	}
	
	
	
	/**
	 * ����ѧ�������б�ѡ���ѡ��
	 * 
	 * @param jcb
	 *            ����Ҫ���õ������б�ؼ�
	 */
	private static void setschoolyearselected(JComboBox jcb) {
		// ȡ�õ�ǰϵͳʱ��
		Calendar now = Calendar.getInstance();
		// ȡ�����
		int year = now.get(Calendar.YEAR);
		// ȡ���·�
		int month = now.get(Calendar.MONTH);
		// ����ѧ�����ַ�������
		String sy_name;
		// �ж���ǰ���ڵ��·ݣ�����ѧ��Ϊ��һѧ��
		if (month >= 2 && month < 8)
			sy_name = year + "�＾ѧ��";
		else if (month >= 8)
			sy_name = (year + 1) + "����ѧ��";
		else
			sy_name = year + "����ѧ��";
		// ����ѧ����������ѧ�ڲ�ѯ����
		String condition = "sy_name='" + sy_name + "'";
		Connection con = null;
		try {

			// �������ݿ����Ƿ���ڴ�ѧ��
			ArrayList<Schoolyear> al = Schoolyearaccess
					.getSchoolyear(condition);
			// ������ڣ�������ѧ�������б�ѡ���ѧ��Ϊ��ѧ��
			if (al.size() > 0)
				jcb.setSelectedItem(new Schoolyear(-1, sy_name));

			else {
				// ��������ڴ�ѧ�ڣ���ѧ�����ݱ��в����ѧ��
				con = Databaseconnection.getconnection();
				int n = Schoolyearaccess.insertSchoolyear(con, sy_name);
				// ����ɹ������������ѧ�������б�
				if (n > 0)
					fillschoolyear(jcb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * ����ѧ�������꼶�����б�
	 * 
	 * @param sy_name
	 *            ��ѧ����
	 * @param jcb
	 *            ����Ҫ���õ������б�ؼ�
	 */
	public static void fillgrade(String sy_name, JComboBox jcb) {
		// ��������б�ؼ�
		jcb.removeAllItems();
		// ��ѧ���ַ�����ȡ�����
		String year = sy_name.substring(0, 4);
		// ��ѧ���ַ�����ȡ�������������ѧ��
		String name = sy_name.substring(4, 5);
		// ȡ��ѧ����ݺ���λ
		int start = Integer.parseInt(year.substring(2));
		// ������＾ѧ��
		if (name.equals("��"))
			// ��У���꼶��ʼֵΪѧ����ݺ���λ��3
			start -= 3;
		else
			// ����Ǵ���ѧ�ڣ���У���꼶��ʼֵΪѧ����ݺ���λ��4
			start -= 4;
		// ѭ��4�Σ������У�꼶
		for (int i = 0; i < 4; i++, start++) {
			if (start < 10)
				jcb.addItem("0" + start);
			else
				jcb.addItem("" + start);
		}
	}

	/**
	 * ����ʦ�����б�
	 * 
	 * @author czy
	 * @param o_id
	 *            :���ұ��
	 * @param jcb
	 *            ����Ҫ���������б�ؼ�
	 */
	public static void fillteacher(String o_id, View_teacher vt, JComboBox jcb) {
		// �����б�ؼ��������
		jcb.removeAllItems();

		// �ӽ�ʦ���ݷ����ࡰTeacheraccess���е��þ�̬������getMajor����
		// �������ݿ��ж�Ӧ���ҵ����н�ʦ��Ϣ����View_teacher�����͵������б��С�
		String condition = "o_id='" + o_id + "'";
		ArrayList<View_teacher> al = Teacheraccess.getview_teacher(condition);
		// ������View_teacher�����͵������б�ȡ����ʦ��Ϣ������������б�ؼ�
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
		if (vt != null) {
			jcb.setSelectedItem(vt);
		}

	}
}
