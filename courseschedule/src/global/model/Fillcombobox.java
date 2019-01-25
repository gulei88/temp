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
 * 此类将多个功能的下拉列表控件的填充方法集中在一起。
 * 
 * @author czy
 * 
 */
public class Fillcombobox {
	private static String tp_id;

	/**
	 * 填充部门下拉列表
	 * 
	 * @author czy
	 * 
	 * @param jcb
	 *            ：需要填充的下拉列表控件
	 */
	public static void filldepartment(JComboBox jcb) {
		// 从部门数据访问类“Departmentaccess”中调用静态方法“getDepartment”，
		// 返回数据库现有的所有部门信息到“Department”类型的数组列表中。
		ArrayList<Department> al = Departmentaccess.getDepartment(null);
		// 遍历“Department”类型的数组列表，分别取出部门信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}

	
	public static void filldepartmentadd(String d_id, JComboBox jcb) {
		// 从部门数据访问类“Departmentaccess”中调用静态方法“getDepartment”，
		// 返回数据库现有的所有部门信息到“Department”类型的数组列表中。
		ArrayList<Department> al = Departmentaccess.getDepartment(d_id);
		// 遍历“Department”类型的数组列表，分别取出部门信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	
	
	public static void filllevel(int m_id,JComboBox jcb) {
		// 从部门数据访问类“Departmentaccess”中调用静态方法“getDepartment”，
		// 返回数据库现有的所有部门信息到“Department”类型的数组列表中。
		ArrayList<Level> al = Levelaccess.getLevels(m_id);
		// 遍历“Department”类型的数组列表，分别取出部门信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	
	
	public static void fillbuilding(int b_id,JComboBox jcb) {
		// 从部门数据访问类“Departmentaccess”中调用静态方法“getDepartment”，
		// 返回数据库现有的所有部门信息到“Department”类型的数组列表中。
		ArrayList<Buliding> al = Buildingaccess.getbuliding(b_id);
		// 遍历“Department”类型的数组列表，分别取出部门信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	
	
	public static void fillclassroomtype(int cr_id,JComboBox jcb) {
		// 从部门数据访问类“Departmentaccess”中调用静态方法“getDepartment”，
		// 返回数据库现有的所有部门信息到“Department”类型的数组列表中。
		ArrayList<Classroom> al = Classroomaccess.getClassroom(cr_id);
		// 遍历“Department”类型的数组列表，分别取出部门信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	/**
	 * 填充科室下拉列表
	 * 
	 * @author czy
	 * @param d_id
	 *            :部门编号
	 * @param jcb
	 *            ：需要填充的下拉列表控件
	 */
	public static void filloffice(String d_id, JComboBox jcb) {
		// 下拉列表控件内容清空
		jcb.removeAllItems();
		// 从科室数据访问类“Officeaccess”中调用静态方法“getoffice”，
		// 返回数据库中对应部门的所有科室信息到“Office”类型的数组列表中。
		ArrayList<Office> al = Officeaccess.getoffice(d_id);
		// 遍历“Office”类型的数组列表，分别取出科室信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
//		System.out.println(al);
	}

	/**
	 * 填充专业下拉列表
	 * 
	 * @author czy
	 * @param o_id
	 *            :科室编号
	 * @param jcb
	 *            ：需要填充的下拉列表控件
	 */
	public static void fillmajor(String o_id, JComboBox jcb) {
		// 下拉列表控件内容清空
		jcb.removeAllItems();
		// 从科室数据访问类“Majoraccess”中调用静态方法“getMajor”，
		// 返回数据库中对应科室的所有专业信息到“Major”类型的数组列表中。
		List<Major> al = Majoraccess.getMajor(o_id);
		// 遍历“Major”类型的数组列表，分别取出专业信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
//		System.out.println(al);
	}

	
	
	
	public static void fillteachingplan(String m_id, JComboBox jcb) {
		// 下拉列表控件内容清空
		jcb.removeAllItems();
		// 从科室数据访问类“Majoraccess”中调用静态方法“getMajor”，
		// 返回数据库中对应科室的所有专业信息到“Major”类型的数组列表中。
		ArrayList<Teachingplan> al = Teaplanaccess.getteachingplan(m_id);
		// 遍历“Major”类型的数组列表，分别取出专业信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	
	
	
	public static void fillTeachingplangrade(JComboBox jcb) {
		// 下拉列表控件内容清空
		jcb.removeAllItems();
		// 从科室数据访问类“Majoraccess”中调用静态方法“getMajor”，
		// 返回数据库中对应科室的所有专业信息到“Major”类型的数组列表中。
		ArrayList<Teachingplangrade> al = Teaplanaccess.getTeachingplangrade(tp_id);
		// 遍历“Major”类型的数组列表，分别取出专业信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
	}
	/**
	 * 填充学期下拉列表
	 * 
	 * @author czy
	 * @param jcb
	 *            ：需要填充的下拉列表控件
	 */
	public static void fillschoolyear(JComboBox jcb) {
		// 下拉列表控件内容清空
		jcb.removeAllItems();

		// 从科室数据访问类“Majoraccess”中调用静态方法“getMajor”，
		// 返回数据库中对应科室的所有专业信息到“Major”类型的数组列表中。
		ArrayList<Schoolyear> al = Schoolyearaccess.getSchoolyear("");
		// 遍历“Major”类型的数组列表，分别取出专业信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
		setschoolyearselected(jcb);

	}
	
	
	public static void classinformation(JComboBox jcb) {
		// 下拉列表控件内容清空
		jcb.removeAllItems();
		// 从科室数据访问类“Majoraccess”中调用静态方法“getMajor”，
		// 返回数据库中对应科室的所有专业信息到“Major”类型的数组列表中。
		ArrayList<View_classinformation> al = Classinformationaccess.getView_classinformation("");
		// 遍历“Major”类型的数组列表，分别取出专业信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
		setschoolyearselected(jcb);

	}
	
	
	
	/**
	 * 设置学期下拉列表选择的选项
	 * 
	 * @param jcb
	 *            ：需要设置的下拉列表控件
	 */
	private static void setschoolyearselected(JComboBox jcb) {
		// 取得当前系统时间
		Calendar now = Calendar.getInstance();
		// 取得年份
		int year = now.get(Calendar.YEAR);
		// 取得月份
		int month = now.get(Calendar.MONTH);
		// 生成学期名字符串对象
		String sy_name;
		// 判定当前日期的月份，设置学期为下一学期
		if (month >= 2 && month < 8)
			sy_name = year + "秋季学期";
		else if (month >= 8)
			sy_name = (year + 1) + "春季学期";
		else
			sy_name = year + "春季学期";
		// 根据学期名，生成学期查询条件
		String condition = "sy_name='" + sy_name + "'";
		Connection con = null;
		try {

			// 查找数据库中是否存在此学期
			ArrayList<Schoolyear> al = Schoolyearaccess
					.getSchoolyear(condition);
			// 如果存在，则设置学期下拉列表选择的学期为此学期
			if (al.size() > 0)
				jcb.setSelectedItem(new Schoolyear(-1, sy_name));

			else {
				// 如果不存在此学期，向学期数据表中插入此学期
				con = Databaseconnection.getconnection();
				int n = Schoolyearaccess.insertSchoolyear(con, sy_name);
				// 插入成功，则重新填充学期下拉列表
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
	 * 根据学期设置年级下拉列表
	 * 
	 * @param sy_name
	 *            ：学期名
	 * @param jcb
	 *            ：需要设置的下拉列表控件
	 */
	public static void fillgrade(String sy_name, JComboBox jcb) {
		// 清空下拉列表控件
		jcb.removeAllItems();
		// 从学期字符串中取出年份
		String year = sy_name.substring(0, 4);
		// 从学期字符串中取出“春”，“秋”学期
		String name = sy_name.substring(4, 5);
		// 取得学期年份后两位
		int start = Integer.parseInt(year.substring(2));
		// 如果是秋季学期
		if (name.equals("秋"))
			// 在校的年级起始值为学期年份后两位减3
			start -= 3;
		else
			// 如果是春季学期，在校的年级起始值为学期年份后两位减4
			start -= 4;
		// 循环4次，添加在校年级
		for (int i = 0; i < 4; i++, start++) {
			if (start < 10)
				jcb.addItem("0" + start);
			else
				jcb.addItem("" + start);
		}
	}

	/**
	 * 填充教师下拉列表
	 * 
	 * @author czy
	 * @param o_id
	 *            :科室编号
	 * @param jcb
	 *            ：需要填充的下拉列表控件
	 */
	public static void fillteacher(String o_id, View_teacher vt, JComboBox jcb) {
		// 下拉列表控件内容清空
		jcb.removeAllItems();

		// 从教师数据访问类“Teacheraccess”中调用静态方法“getMajor”，
		// 返回数据库中对应科室的所有教师信息到“View_teacher”类型的数组列表中。
		String condition = "o_id='" + o_id + "'";
		ArrayList<View_teacher> al = Teacheraccess.getview_teacher(condition);
		// 遍历“View_teacher”类型的数组列表，取出教师信息，并填充下拉列表控件
		for (int i = 0; i < al.size(); i++)
			jcb.addItem(al.get(i));
		if (vt != null) {
			jcb.setSelectedItem(vt);
		}

	}
}
