/*
 * CZY_jif_curriculum.java
 *
 * Created on __DATE__, __TIME__
 */

package czy.view;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import czy.dao.Curriculumaccess;
import czy.model.Coordinate;
import czy.model.Tools;
import czy.model.TableCellTextAreaRenderer;
import czy.model.Rowcolor;

import global.dao.Classroomaccess;
import czy.dao.Courselaboratoryaccess;
import global.dao.Databaseconnection;
import global.dao.Majoraccess;
import global.dao.Teachingtaskaccess;
import global.model.Classroom;
import global.model.Curriculum;
import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.Schoolyear;
import global.model.Sublaboratory;
import global.model.View_classroom;
import global.model.View_curriculum;
import global.model.View_teacher;
import global.model.View_teachingtask;
import czy.model.View_courselaboratory;

/**
 * 
 * @author __USER__
 */
public class CZY_jif_curriculum extends javax.swing.JInternalFrame {
	private View_teacher vt;
	private JCheckBox jcb[] = new JCheckBox[16];
	private ArrayList<Integer> rowlist = new ArrayList<Integer>();
	private String inweeks = "";
	ActionTry select = new ActionTry();

	/** Creates new form CZY_jif_curriculum */
	public CZY_jif_curriculum() {
		initComponents();
	}

	/**
	 * 构造界面
	 * 
	 * @author czy
	 * @param vt
	 *            ：登录用户的视图信息
	 */
	public CZY_jif_curriculum(View_teacher vt) {
		// 初始化界面
		initComponents();
		// 将传递的参数（登录用户的视图信息）传递给类的对应属性字段
		this.vt = vt;
		// 调用函数初始化学院下拉列表
		fillcomboboxdepartment();
		// 调用类的静态函数初始化学期下拉列表
		Fillcombobox.fillschoolyear(jComboBox4);
		// 设置授课任务表中各列宽度
		setColumnWidth();
		// 将授课周数复选按钮组的变量名转化为复选按钮数组，便于后续操作
		initCheckBoxArray();
		ActionTry select = new ActionTry();
		for (int i = 0; i < jcb.length; i++) {
			jcb[i].addActionListener(select);
			jcb[i].setSelected(true);
		}
		inweeks = "abcdefghijklmnop";
		// 设置jtable表格行单选
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * 内部类,实现了ActionListener接口
	 * 
	 * @author czy
	 * 
	 */
	class ActionTry implements ActionListener {
		/**
		 * 活动侦听方法，当对应控价触发本事件后，将复选按钮数组的选中状态转换为字符，并赋值给CZY_jif_curriculum类的属性字段。
		 * 
		 * @author czy
		 */
		public void actionPerformed(ActionEvent e) {
			// 初始化字符串
			inweeks = "";
			// 遍历复选按钮数组
			for (int i = 0; i < jcb.length; i++) {
				// 如果数组元素被选中，则将代表该复选按钮的小写字母添加到字符串的尾部
				if (jcb[i].isSelected()) {
					inweeks += (char) (i + 97);
				}
			}
			// 填充课程表表格
			filltablecurriculum();
		}
	}

	/**
	 * 初始化学院下拉列表
	 * 
	 * @author czy
	 */
	private void fillcomboboxdepartment() {
		// 如果登录的权限为系部排课或部门管理，则学院下拉列表的内容之初始化为登录用户所在学院
		if (vt.getT_power().equals("系部排课") || vt.getT_power().equals("部门管理")) {
			Department dm = new Department(vt.getD_id(), vt.getD_name());
			jComboBox1.addItem(dm);
		}
		// 如果权限为学校管理，则学院下拉列表调用类的静态函数初始化为全部学院
		else if (vt.getT_power().equals("学校管理")) {
			Fillcombobox.filldepartment(jComboBox1);
		}
	}

	/**
	 * 初始化教学任务表格
	 * 
	 * @author czy
	 */
	private void fillteachingtasktable() {
		// 取得表格的model信息
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		// 清空表格中显示的数据
		dtm.setRowCount(0);
		// 取得系部下拉列表选择内容
		Office o = (Office) jComboBox2.getSelectedItem();
		// 如果为空，则退出
		if (o == null)
			return;
		// 取得学年下拉列表选择内容
		Schoolyear sy = (Schoolyear) jComboBox4.getSelectedItem();
		// 如果为空，则退出
		if (sy == null)
			return;

		List<Major> al = Majoraccess.getMajor(o.getO_id());
		if (al.size() == 0)
			return;
		// 生成查询条件用于查询指定学期对应的教学任务
		String condition = " sy_id=" + sy.getSy_id() + " and (m_id='"
				+ al.get(0).getM_id() + "'";
		for (int i = 1; i < al.size(); i++) {
			condition += " or m_id='" + al.get(i).getM_id() + "'";
		}
		condition += ") order by m_id,cou_id";
		// 查找满足条件的教学计划试图信息到指定的课程视图类型的数组列表中
		ArrayList<View_teachingtask> aList = Teachingtaskaccess
				.getView_teachingtask(condition);
		// 遍历课程视图类型的数组列表

		for (int i = 0; i < aList.size(); i++) {
			// 取出对应字段信息
			int tt_id = aList.get(i).getTt_id();
			String cou_id = aList.get(i).getCou_id();
			String cou_name = aList.get(i).getCou_name();
			float Cou_credit = aList.get(i).getCou_credit();
			int cou_theoryhour = aList.get(i).getCou_theoryhour();
			int cou_experimentalhours = aList.get(i).getCou_experimentalhours();
			int cou_practicehour = aList.get(i).getCou_practicehour();
			int cou_type = aList.get(i).getCou_type();
			String cla_name = aList.get(i).getCla_name();
			int cla_number = aList.get(i).getCla_number();
			String tt_grade = aList.get(i).getTt_grade();
			String t_id = aList.get(i).getT_id();
			String t_name = aList.get(i).getT_name();
			int sy_id = aList.get(i).getSy_id();
			String sy_name = aList.get(i).getSy_name();
			String multimedia = aList.get(i).getMultimedia();
			String Practicescheduling = aList.get(i).getPracticescheduling();
			int tt_state = aList.get(i).getTt_state();
			// 生成向量类型变量
			Vector v = new Vector();
			// 将取得数据添加到向量中
			v.add(tt_id);
			v.add(cou_id);
			v.add(cou_name);
			v.add(Cou_credit);
			v.add(cou_theoryhour + cou_experimentalhours + cou_practicehour);
			v.add(cou_theoryhour);
			v.add(cou_experimentalhours);
			v.add(cou_practicehour);
			if (cou_type == 1)
				v.add("必修");
			else
				v.add("选修");
			v.add(cla_name);
			v.add(cla_number);
			v.add(tt_grade);
			v.add(t_id);
			v.add(t_name);
			v.add(new Schoolyear(sy_id, sy_name));
			v.add(multimedia);
			v.add(Practicescheduling);
			if (tt_state == 1) {
				v.add("完成排课");
				rowlist.add(i);
			} else
				v.add("未完成排课");
			// 将向量添加到表格中
			dtm.addRow(v);
			Rowcolor.setColor(jTable1, rowlist, Color.green);
		}

	}

	/**
	 * 根据实验分室名称生成实验室查询的条件语句
	 * 
	 * @param sl_name
	 *            ：实验分室名称
	 * @return
	 */
	private String getconditionsublaboratory(String sl_name) {
		// 生成条件字符串，并根据实验室编号为初始化条件
		String condition = "(cr_id="
				+ ((Classroom) jComboBox5.getSelectedItem()).getCr_id() + ")";
		// 如果传递的实验分室名称不为“”
		if (!sl_name.equals("")) {
			// 将实验室名称以逗号为分隔符，将多个实验分室分解开，存到字符串数组name中
			String[] name = sl_name.split(",");
			// 根据多个实验分室，分别生成查询条件，并添加到条件字符串里
			condition = condition.substring(0, condition.length() - 1);
			condition += " and (sl_name like '%" + name[0] + "%' ";
			for (int i = 1; i < name.length; i++) {
				condition += " or sl_name like '%" + name[i] + "%' ";
			}
			condition += "))";
		}
		// 返回条件字符串
		return condition;
	}

	/**
	 * 生成教学周数查询条件字符串
	 * 
	 * @author czy
	 * @return 教学周数查询条件字符串
	 */
	private String getconditioninweeks() {
		// 初始化字符串变量值为“”
		String condition = "";
		// 如果教学周数字符串不等于全部
		if (!inweeks.equals("abcdefghijklmnop")) {
			// 将教学周数字符串转换为字符数组
			char[] weeks = inweeks.toCharArray();
			// 遍历教学周数字符数组，生成数据库查询条件
			for (int i = 0; i < weeks.length; i++)
				condition += "or Inweeks like '%" + weeks[i] + "%' ";
			// 去掉查询字符串的第一个“or”字符串
			condition = condition.replaceFirst("or", "");
			// 查询条件字符串添加括号
			condition = "(" + condition + ")";
		}
		// 返回条件字符串
		return condition;
	}

	/**
	 * 填充课程表表格
	 * 
	 * @author czy
	 * 
	 */
	private void filltablecurriculum() {
		// 清空课程表
		DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
		dtm.setRowCount(0);
		int row = jTable1.getSelectedRow();
		// 如果未选择教学任务，则提示错误信息
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "请选择教学任务");
			return;
		}
		// 如果未选择授课周数，则提示错误信息
		if (inweeks.equals("")) {
			JOptionPane.showMessageDialog(this, "请选择教学周");
			return;
		}
		int seat = 0;
		// 如果实验分室列表框内存在数据
		if (jList1.isEnabled()) {
			// 得到列表控件中选择的实验分室
			Object[] sl = jList1.getSelectedValues();
			// 计算选择的实验分室总座位数
			for (int i = 0; i < sl.length; i++) {
				seat += ((Sublaboratory) sl[i]).getSl_seating();
			}
		} else {
			Classroom cr=(Classroom) jComboBox5.getSelectedItem();
			if(cr==null)
				return;
			seat = cr.getCr_seating();
		}
		// 获得教学任务的人数
		int seating = (Integer) jTable1.getValueAt(row, 10);
		// 如果实验分室的总座位数小于教学任务的人数，则退出本段代码的执行
		if (seat <= seating) {
			return;
		}
		// 如果没有教室信息，则退出本段代码的执行
		if (jComboBox5.getItemCount() == 0)
			return;
		// 判断授课周数复选按钮组，如果所有的复选按钮都没有选中，则退出本段代码的执行
		int i;
		for (i = 0; i < jcb.length; i++) {
			if (jcb[i].isSelected()) {
				break;
			}
		}
		if (i >= jcb.length)
			return;
		// 生成填充课程表表格的向量数组，并初始化课节
		Vector[] v = new Vector[10];
		for (i = 0; i < 10; i++) {
			String x = "第" + (i + 1) + "节";
			v[i] = new Vector();
			v[i].add(x);
			for (int j = 1; j < 8; j++) {
				v[i].add("");
			}
		}
		String weeks = getconditioninweeks();
		String sl_name = getconditionsublaboratory(getsublaboratory());
		// 获得学期编号
		int sy_id = ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id();
		// 设置基础查询条件是学期为设置的学期
		String base = "sy_id=" + sy_id;
		// 查询条件为学期+教室的课程信息
		String condition = base + " and " + sl_name;
		// 如果授课周数已设定，则查询条件里添加授课周数条件
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// 在数据库中查询指定条件的课程信息
		ArrayList<View_curriculum> aList = Curriculumaccess
				.getCurriculums(condition);
		// 根据查询结果个数生成教室的表格坐标数组
		Coordinate[] classroom = new Coordinate[aList.size()];
		// 根据课程表信息列表给Coordinate类型的数组及传递的向量赋值
		Coordinate.fillCoordinateArray(aList, classroom, v);
		// 查找教师课程信息
		// 取得教师编号
		String t_id = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 12);
		// 查询条件为学期+教师的课程信息
		condition = base + " and t_id='" + t_id + "'";
		// 如果授课周数已设定，则查询条件里添加授课周数条件
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// 在数据库中查询指定条件的课程信息
		aList = Curriculumaccess.getCurriculums(condition);
		// 根据查询结果个数生成教师的表格坐标数组
		Coordinate[] teacher = new Coordinate[aList.size()];
		// 根据课程表信息列表给Coordinate类型的数组及传递的向量赋值
		Coordinate.fillCoordinateArray(aList, teacher, v);
		// 取得班级名称信息
		String cla_name = jTextField1.getText();
		// 将班级名称字符串以逗号为分隔符进行分解（分解多个班级）
		String[] cla = cla_name.split(",");
		// 查询条件为学期+班级的课程信息
		condition = base + " and (cla_name like '%" + cla[0] + "%'";
		for (i = 1; i < cla.length; i++)
			condition += " or cla_name like '%" + cla[i] + "%'";
		condition += ")";
		// 如果授课周数已设定，则查询条件里添加授课周数条件
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// 在数据库中查询指定条件的课程信息
		aList = Curriculumaccess.getCurriculums(condition);
		// 根据查询结果个数生成学生的表格坐标数组
		Coordinate[] student = new Coordinate[aList.size()];
		// 根据课程表信息列表给Coordinate类型的数组及传递的向量赋值
		Coordinate.fillCoordinateArray(aList, student, v);
		// 获取当前任务编号
		int tt_id = (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
		// 查询条件为学期+当前任务的课程信息
		condition = base + " and tt_id=" + tt_id;
		// 如果授课周数已设定，则查询条件里添加授课周数条件
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// 在数据库中查询指定条件的课程信息
		aList = Curriculumaccess.getCurriculums(condition);
		// 根据查询结果个数生成当前课程的表格坐标数组
		Coordinate[] current = new Coordinate[aList.size()];
		// 根据课程表信息列表给Coordinate类型的数组及传递的向量赋值
		Coordinate.fillCoordinateArray(aList, current, v);

		Color studentcolor = Color.GREEN;
		Color teachercolor = Color.YELLOW;
		Color classroomcolor = Color.CYAN;
		Color currentcolor = Color.RED;
		// 将向量数组添加到课程表表格
		for (i = 0; i < v.length; i++) {
			dtm.addRow(v[i]);
			jTable2.setRowHeight(i, 40);
		}
		// 设置jtable2对应格的背景颜色
		jTable2.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer(
				student, teacher, classroom, current, studentcolor,
				teachercolor, classroomcolor, currentcolor));
	}

	private ArrayList<Classroom> findclassroom() {
		int row = jTable1.getSelectedRow();
		int cla_number = (Integer) jTable1.getValueAt(row, 10);
		String multimedia = (String) jTable1.getValueAt(row, 15);
		String condition = " seating>=" + cla_number;
		if (multimedia.equals("是"))
			condition += " and ct_id=2";
		else
			condition += " and ct_id=1";
		ArrayList<View_classroom> aList = Classroomaccess
				.getView_classroom(condition);
		ArrayList<Classroom> crlist = new ArrayList<Classroom>();
		for (int i = 0; i < aList.size(); i++) {
			int cr_id = aList.get(i).getCr_id();
			String d_id = aList.get(i).getD_id();
			String cr_name = aList.get(i).getCr_name();
			int ct_id = aList.get(i).getCt_id();
			int cr_seating = aList.get(i).getSeating();
			int b_id = aList.get(i).getB_id();
			crlist.add(new Classroom(cr_id, d_id, cr_name, ct_id, cr_seating,
					b_id));
		}
		return crlist;
	}

	private ArrayList<Classroom> findlaboratory() {
		int row = jTable1.getSelectedRow();
		 int cla_number = (Integer) jTable1.getValueAt(row, 10);
		// int tt_id = (Integer) jTable1.getValueAt(row, 0);
		String cou_id = (String) jTable1.getValueAt(row, 1);
		String grade = (String) jTable1.getValueAt(row, 11);
		String condition = "((sumsl_seating is null and cr_seating>"+cla_number+")or sumsl_seating>"+cla_number+") and ce_id in (select ce_id from courseexperimentalenvironment where grade='"
				+ grade + "' and cou_id='" + cou_id + "')";
		ArrayList<View_courselaboratory> aList = Courselaboratoryaccess
				.getCourselaboratorybycondition(condition);
		ArrayList<Classroom> crlist = new ArrayList<Classroom>();
		for (int i = 0; i < aList.size(); i++) {
			int cr_id = aList.get(i).getCr_id();
			String d_id = aList.get(i).getD_id();
			String cr_name = aList.get(i).getCr_name();
			int ct_id = aList.get(i).getCt_id();
			int cr_seating = aList.get(i).getCr_seating();
			int b_id = aList.get(i).getB_id();
			crlist.add(new Classroom(cr_id, d_id, cr_name, ct_id, cr_seating,
					b_id));
		}
		return crlist;
	}

	private void fillcomboxclassroom() {
		// 清空授课教室下拉列表
		jComboBox5.removeAllItems();
		// 获取教学任务表格选中的行
		int row = jTable1.getSelectedRow();
		// 如果没有选中的行，则退出本段代码
		if (row < 0)
			return;
		// 获取班级人数
		int cla_number = (Integer) jTable1.getValueAt(row, 10);
		// 获取实验课时
		int cou_experimentalhours = (Integer) jTable1.getValueAt(row, 6);
		// 获取实践课时
		int cou_practicehour = (Integer) jTable1.getValueAt(row, 7);
		// 如果实验和实践课时都为0，则实验室复选按钮停用
		if (cou_experimentalhours == 0 && cou_practicehour == 0) {
			jCheckBox17.setSelected(false);
			jCheckBox17.setEnabled(false);
		} else {
			// 实验室复选按钮启用
			jCheckBox17.setEnabled(true);
		}
		ArrayList<Classroom> aList = null;

		if (jCheckBox17.isSelected()) {
			aList = findlaboratory();
		} else {
			aList = findclassroom();
		}
		if (aList == null)
			return;
		for (int i = 0; i < aList.size(); i++) {
			Classroom cr = aList.get(i);
			boolean r = false;
			for (int j = 0; j < jComboBox5.getItemCount(); j++) {
				if (jComboBox5.getItemAt(j).equals(cr)) {
					r = true;
					break;
				}
			}
			if (!r)
				jComboBox5.addItem(aList.get(i));
		}
		if (jComboBox5.getItemCount() == 0) {
			JOptionPane.showMessageDialog(this,
					"没有教室或实验室能提供给该课程的授课，请尝试进行分班、合班，或调整其他课程时间！");
			// 取得表格的model信息
			DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
			// 清空表格中显示的数据
			dtm.setRowCount(0);
			jTable2.setEnabled(false);
		} else {
			jTable2.setEnabled(true);
			filltablecurriculum();
		}
	}

	private void initCheckBoxArray() {
		jcb[0] = jCheckBox1;
		jcb[1] = jCheckBox2;
		jcb[2] = jCheckBox3;
		jcb[3] = jCheckBox4;
		jcb[4] = jCheckBox5;
		jcb[5] = jCheckBox6;
		jcb[6] = jCheckBox7;
		jcb[7] = jCheckBox8;
		jcb[8] = jCheckBox9;
		jcb[9] = jCheckBox10;
		jcb[10] = jCheckBox11;
		jcb[11] = jCheckBox12;
		jcb[12] = jCheckBox13;
		jcb[13] = jCheckBox14;
		jcb[14] = jCheckBox15;
		jcb[15] = jCheckBox16;
	}

	private void setColumnWidth() {
		TableColumn Column;
		// 设置教学任务表格各列宽度
		Column = jTable1.getColumnModel().getColumn(0);
		Column.setPreferredWidth(50);
		Column = jTable1.getColumnModel().getColumn(1);
		Column.setPreferredWidth(80);
		Column = jTable1.getColumnModel().getColumn(2);
		Column.setPreferredWidth(180);
		Column = jTable1.getColumnModel().getColumn(3);
		Column.setPreferredWidth(40);
		Column = jTable1.getColumnModel().getColumn(4);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(5);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(6);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(7);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(8);
		Column.setPreferredWidth(50);
		Column = jTable1.getColumnModel().getColumn(9);
		Column.setPreferredWidth(180);
		Column = jTable1.getColumnModel().getColumn(10);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(11);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(12);
		Column.setPreferredWidth(60);
		Column = jTable1.getColumnModel().getColumn(13);
		Column.setPreferredWidth(90);
		Column = jTable1.getColumnModel().getColumn(14);
		Column.setPreferredWidth(60);
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel2 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		jComboBox4 = new javax.swing.JComboBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jPanel6 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jCheckBox1 = new javax.swing.JCheckBox();
		jCheckBox2 = new javax.swing.JCheckBox();
		jCheckBox3 = new javax.swing.JCheckBox();
		jCheckBox4 = new javax.swing.JCheckBox();
		jCheckBox5 = new javax.swing.JCheckBox();
		jCheckBox6 = new javax.swing.JCheckBox();
		jCheckBox7 = new javax.swing.JCheckBox();
		jCheckBox8 = new javax.swing.JCheckBox();
		jCheckBox9 = new javax.swing.JCheckBox();
		jCheckBox10 = new javax.swing.JCheckBox();
		jCheckBox11 = new javax.swing.JCheckBox();
		jCheckBox12 = new javax.swing.JCheckBox();
		jCheckBox13 = new javax.swing.JCheckBox();
		jCheckBox14 = new javax.swing.JCheckBox();
		jCheckBox15 = new javax.swing.JCheckBox();
		jCheckBox16 = new javax.swing.JCheckBox();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jComboBox5 = new javax.swing.JComboBox();
		jTextField1 = new javax.swing.JTextField();
		jScrollPane3 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jCheckBox17 = new javax.swing.JCheckBox();
		jPanel7 = new javax.swing.JPanel();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jTextField5 = new javax.swing.JTextField();
		jTextField6 = new javax.swing.JTextField();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();

		setClosable(true);
		setIconifiable(true);
		setTitle("\u6392\u8bfe\u7ba1\u7406");

		jLabel1.setText("\u5b66\u9662\uff1a");

		jComboBox1.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox1ItemStateChanged(evt);
			}
		});

		jLabel2.setText("\u7cfb\u90e8\uff1a");

		jComboBox2.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox2ItemStateChanged(evt);
			}
		});

		jLabel4.setText("\u5b66\u671f\uff1a");

		jComboBox4.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox4ItemStateChanged(evt);
			}
		});

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "编号", "课程编号", "课程名称", "学分", "总学时", "理论学时",
						"实验学时", "实践学时", "类型", "开课班级", "班级人数", "年级", "教师编号",
						"授课教师", "开课学期", "多媒体教室", "实践是否排课", "排课状态" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false, false, false, false, false, false, false,
					false, false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jTable1MousePressed(evt);
			}
		});
		jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				jTable1KeyPressed(evt);
			}
		});
		jScrollPane1.setViewportView(jTable1);

		jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u8bfe\u7a0b\u4fe1\u606f"));

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u6388\u8bfe\u5468\u6570"));

		jCheckBox1.setText("1");

		jCheckBox2.setText("2");

		jCheckBox3.setText("3");

		jCheckBox4.setText("4");

		jCheckBox5.setText("5");

		jCheckBox6.setText("6");

		jCheckBox7.setText("7");

		jCheckBox8.setText("8");

		jCheckBox9.setText("9");

		jCheckBox10.setText("10");

		jCheckBox11.setText("11");

		jCheckBox12.setText("12");

		jCheckBox13.setText("13");

		jCheckBox14.setText("14");

		jCheckBox15.setText("15");

		jCheckBox16.setText("16");

		jButton1.setText("\u5168\u9009");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("\u6e05\u7a7a");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("\u524d\u516b\u5468");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton4.setText("\u540e\u516b\u5468");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jButton5.setText("\u5355\u5468");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jButton6.setText("\u53cc\u5468");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addGap(16, 16, 16)
										.addComponent(jCheckBox1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox3)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox4)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox5)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox6)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox7)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox8)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox9)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox10)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox11)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox12)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox13)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox14)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox15)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jCheckBox16)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton3)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton4)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton5)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton6)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1)
														.addComponent(jButton2)
														.addComponent(jButton3)
														.addComponent(jButton4)
														.addComponent(jButton5)
														.addComponent(jButton6)
														.addComponent(
																jCheckBox1)
														.addComponent(
																jCheckBox2)
														.addComponent(
																jCheckBox3)
														.addComponent(
																jCheckBox4)
														.addComponent(
																jCheckBox5)
														.addComponent(
																jCheckBox6)
														.addComponent(
																jCheckBox7)
														.addComponent(
																jCheckBox8)
														.addComponent(
																jCheckBox9)
														.addComponent(
																jCheckBox10)
														.addComponent(
																jCheckBox11)
														.addComponent(
																jCheckBox12)
														.addComponent(
																jCheckBox13)
														.addComponent(
																jCheckBox14)
														.addComponent(
																jCheckBox15)
														.addComponent(
																jCheckBox16))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jLabel6.setText("\u6388\u8bfe\u73ed\u7ea7\uff1a");

		jLabel7.setText("\u6388\u8bfe\u6559\u5e08\uff1a");

		jTextField2.setEditable(false);

		jLabel8.setText("\u6388\u8bfe\u6559\u5ba4\uff1a");

		jComboBox5.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox5ItemStateChanged(evt);
			}
		});

		jTextField1.setEditable(false);

		jList1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("\u5b9e\u9a8c\u5206\u5ba4"));
		jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
			public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
				jList1ValueChanged(evt);
			}
		});
		jScrollPane3.setViewportView(jList1);

		jCheckBox17.setText("\u5b9e\u9a8c\u5ba4");
		jCheckBox17.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jCheckBox17ItemStateChanged(evt);
			}
		});
		jCheckBox17.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jCheckBox17ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel6)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jTextField1)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jLabel7)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jTextField2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				170,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jCheckBox17)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jLabel8,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				67,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jComboBox5,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				213,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jPanel5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												119,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(67, Short.MAX_VALUE)));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jScrollPane3,
																0, 0,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																jPanel6Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel6)
																						.addComponent(
																								jComboBox5,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel8)
																						.addComponent(
																								jCheckBox17)
																						.addComponent(
																								jTextField2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel7)
																						.addComponent(
																								jTextField1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jPanel5,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u989c\u8272\u56fe\u4f8b"));

		jTextField3.setBackground(new java.awt.Color(255, 0, 0));
		jTextField3.setEditable(false);
		jTextField3.setText("  \u5f53\u524d\u8bfe\u7a0b");
		jTextField3.setBorder(null);

		jTextField4.setBackground(new java.awt.Color(0, 128, 0));
		jTextField4.setEditable(false);
		jTextField4.setText("  \u5b66\u751f\u8bfe\u7a0b");
		jTextField4.setBorder(null);

		jTextField5.setBackground(new java.awt.Color(255, 255, 0));
		jTextField5.setEditable(false);
		jTextField5.setText("  \u6559\u5e08\u8bfe\u7a0b");
		jTextField5.setBorder(null);

		jTextField6.setBackground(new java.awt.Color(0, 255, 255));
		jTextField6.setEditable(false);
		jTextField6.setText("  \u6559\u5ba4\u8bfe\u7a0b");
		jTextField6.setBorder(null);

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
				jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jTextField3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												65,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jTextField4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												70,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jTextField5,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												66, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jTextField6,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												65, Short.MAX_VALUE)
										.addGap(13, 13, 13)));
		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextField3,
								javax.swing.GroupLayout.DEFAULT_SIZE, 31,
								Short.MAX_VALUE)
						.addComponent(jTextField4,
								javax.swing.GroupLayout.DEFAULT_SIZE, 31,
								Short.MAX_VALUE)
						.addComponent(jTextField5,
								javax.swing.GroupLayout.DEFAULT_SIZE, 31,
								Short.MAX_VALUE)
						.addComponent(jTextField6,
								javax.swing.GroupLayout.DEFAULT_SIZE, 31,
								Short.MAX_VALUE));

		jTable2.setFont(new java.awt.Font("宋体", 0, 10));
		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "课节", "星期一", "星期二", "星期三", "星期四", "星期五",
						"星期六", "星期日" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable2.setEnabled(false);
		jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable2MouseClicked(evt);
			}
		});
		jScrollPane2.setViewportView(jTable2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														jScrollPane2,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														1236, Short.MAX_VALUE)
												.addComponent(
														jPanel6,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														1236, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addComponent(
																		jLabel1)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jComboBox1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		157,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel2)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jComboBox2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		174,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel4)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jComboBox4,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		147,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														jPanel7,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(jLabel2)
												.addComponent(
														jComboBox2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jComboBox1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel4)
												.addComponent(
														jComboBox4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										168,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel6,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel7,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane2,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										197, Short.MAX_VALUE).addContainerGap()));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents
	private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {
		filltablecurriculum();
	}

	private void jCheckBox17ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {

		if (jComboBox5.getSelectedItem() == null) {
			jList1.setEnabled(false);
			DefaultListModel dlm = new DefaultListModel();
			jList1.setModel(dlm);
			return;
		}
		if (jCheckBox17.isSelected()) {
			int cr_id = ((Classroom) jComboBox5.getSelectedItem()).getCr_id();
			String cou_id = (String) jTable1.getValueAt(
					jTable1.getSelectedRow(), 1);
			String grade = (String) jTable1.getValueAt(
					jTable1.getSelectedRow(), 11);
			String condition = "cr_id="
					+ cr_id
					+ " and ce_id in (select ce_id from courseexperimentalenvironment where grade='"
					+ grade + "' and cou_id='" + cou_id + "')";
			ArrayList<View_courselaboratory> aList = Courselaboratoryaccess
					.getCourselaboratorybycondition(condition);
			DefaultListModel dlm = new DefaultListModel();
			jList1.setModel(dlm);
			String sl_idString = aList.get(0).getSl_id();
			if (sl_idString != null) {
				String[] array_sl_id = sl_idString.split(",");
				String[] array_sl_name = aList.get(0).getSl_name().split(",");
				String[] array_sl_seating = aList.get(0).getSl_seating()
						.split(",");
				for (int i = 0; i < array_sl_id.length; i++) {
					int sl_id = Integer.parseInt(array_sl_id[i]);
					String sl_name = array_sl_name[i];
					int sl_seating = Integer.parseInt(array_sl_seating[i]);
					dlm.addElement(new Sublaboratory(sl_id, cr_id, sl_name,
							sl_seating));
				}
			}
			if (dlm.getSize() > 0)
				jList1.setEnabled(true);
			else
				jList1.setEnabled(false);
		} else {
			jList1.setEnabled(false);
			DefaultListModel dlm = new DefaultListModel();
			jList1.setModel(dlm);
		}
		filltablecurriculum();
	}

	private void jCheckBox17ItemStateChanged(java.awt.event.ItemEvent evt) {
		// 取得班级名称
		int row = jTable1.getSelectedRow();
		if (row < 0)
			return;
		// 如果实验室单选按钮被选中，则按单个班级名称为单位将获得的班级名信息分解，存到字符串数组中
		if (jCheckBox17.isSelected()) {
			jLabel8.setText(" 实验室：");
		} else {
			jLabel8.setText("授课教室：");
		}
		// 依据得到的班级名称填充班级下拉列表
		fillcomboxclassroom();
	}

	private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {
		jTable1MousePressed(null);
	}

	private void jTable1MousePressed(java.awt.event.MouseEvent evt) {
		// 得到选中的行
		int row = jTable1.getSelectedRow();
		// 如果没有选中的行，则退出当前代码执行
		if (row < 0)
			return;

		// 获取班级名称，并对授课班级文本框赋值
		jTextField1.setText((String) jTable1.getValueAt(row, 9));
		// 如果授课任务未选择教师，则输出提示信息，并设置控件初始值后退出当前代码的执行
		if (((String) jTable1.getValueAt(row, 12)).equals("-1")) {
			JOptionPane.showMessageDialog(this,
					"课程任务未选择授课教师，请到教学任务管理页面中设置本课程授课教师！");
			// 取得表格的model信息
			DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
			// 清空表格中显示的数据
			dtm.setRowCount(0);
			jTable2.setEnabled(false);
			return;
		} else {
			int cou_theoryhour = (Integer) jTable1.getValueAt(row, 5);
			if (cou_theoryhour > 0)
				jCheckBox17.setSelected(false);
			else
				jCheckBox17.setSelected(true);
			// 否则的话，设置控件对应的值
			jTextField2.setText((String) jTable1.getValueAt(row, 12));
			fillcomboxclassroom();
			jTable2.setEnabled(true);
		}
	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton2ActionPerformed(null);
		for (int i = 0; i < jcb.length; i++)
			if (i % 2 == 1)
				jcb[i].setSelected(true);
		inweeks = "bdfhjlnp";
		filltablecurriculum();
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton2ActionPerformed(null);
		for (int i = 0; i < jcb.length; i++)
			if (i % 2 == 0)
				jcb[i].setSelected(true);
		inweeks = "acegikmo";
		filltablecurriculum();
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton2ActionPerformed(null);
		for (int i = 8; i < jcb.length; i++)
			jcb[i].setSelected(true);
		inweeks = "ijklmnop";
		filltablecurriculum();
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		jButton2ActionPerformed(null);
		for (int i = 0; i < 8; i++)
			jcb[i].setSelected(true);
		inweeks = "abcdefgh";
		filltablecurriculum();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		for (int i = 0; i < jcb.length; i++)
			jcb[i].setSelected(false);
		DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
		dtm.setRowCount(0);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		for (int i = 0; i < jcb.length; i++)
			jcb[i].setSelected(true);
		inweeks = "abcdefghijklmnop";
		filltablecurriculum();
	}

	private void jTable2MousePressed(java.awt.event.MouseEvent evt) {

	}

	/**
	 * 判定指定的星期与课节是否被占用
	 * 
	 * @param row
	 *            判定的课节
	 * @param column
	 *            判定的星期
	 * @return 制定的日期对应的课节是否被占用，占用返回真，未占用返回假
	 */
	private String getsublaboratory() {
		if (!jCheckBox17.isSelected())
			return "";
		Object[] sl = jList1.getSelectedValues();
		if (sl.length == 0)
			return "";
		String sl_name = ((Sublaboratory) sl[0]).getSl_name();
		for (int i = 1; i < sl.length; i++) {
			sl_name += "," + ((Sublaboratory) sl[i]).getSl_name();
		}
		return sl_name;
	}

	private boolean isOccupied(int row, int column) {
		String cla_name = jTextField1.getText();
		String[] cla = cla_name.split(",");
		String sl_name = getconditionsublaboratory(getsublaboratory());
		// 设置条件用于查找数据库中该节课与教师、教室、学生时间是否冲突
		String condition = "sy_id="
				+ ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id()
				+ " and (cla_name like '%" + cla[0] + "%'";
		for (int i = 1; i < cla.length; i++)
			condition += " or cla_name like '%" + cla[i] + "%'";
		condition += " or t_id='"
				+ (String) jTable1.getValueAt(jTable1.getSelectedRow(), 11)
				+ "'or " + sl_name + ") and lessons=" + (row + 1)
				+ " and week=" + column + " and tt_id!="
				+ (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
		// 添加教学周条件
		String weeks = getconditioninweeks();
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// 根据条件查询数据库
		ArrayList<View_curriculum> aList = Curriculumaccess
				.getCurriculums(condition);
		// 如果有数据，则代表有冲突，该时间段不能排课
		if (aList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判定指定的星期与课节是否被当前课程占用
	 * 
	 * @param row
	 *            判定的课节
	 * @param column
	 *            判定的星期
	 * @return 是的话返回排课编号，否的话返回-1
	 */
	private int Havebeeninclass(int row, int column) {

		// 设置条件用于查找数据库中该节课是否已经被本课程占用
		String condition = "sy_id="
				+ ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id()
				+ " and lessons=" + (row + 1) + " and week=" + column
				+ " and tt_id="
				+ (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
		String weeks = getconditioninweeks();
		if (!weeks.equals(""))
			condition += " and " + weeks;
		// 根据条件查询数据库
		ArrayList<View_curriculum> aList = Curriculumaccess
				.getCurriculums(condition);
		if (aList.size() > 0)
			return aList.get(0).getCc_id();
		else
			return -1;
	}

	/**
	 * 删除指定的排课信息
	 * 
	 * @param cc_id
	 *            ：要删除的排课编号
	 * @return：删除是否成功。
	 */
	private boolean deletecurriculum(int cc_id) {
		boolean b = false;

		return b;
	}

	private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {
		// 获取鼠标点击次数
		int clicked = evt.getClickCount();
		// 如果不等于2则退出函数执行(鼠标双击)
		if (clicked != 2)
			return;
		// 获取当前点击的行（代表的课节数）
		int row = jTable2.getSelectedRow();
		// 获取当前点击的列（代表的星期）
		int column = jTable2.getSelectedColumn();
		// 如果选中的不是课节的位置（即是行头和列头的位置），则退出
		if (row < 0 || column <= 0)
			return;
		// 取得教学任务编号
		int tt_id = (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
		// 判定在这个时间段本课程是否已经排课
		int cc_id = Havebeeninclass(row, column);
		// 如果已经排课
		if (cc_id >= 0) {
			// 提示信息
			int r = JOptionPane.showConfirmDialog(this, "是否要删除本课程在此时间段的排课？",
					"提示信息", JOptionPane.YES_NO_OPTION);
			// 如果选择是按钮,则删除本时间段本课程的排课信息
			if (r == 0) {
				Connection con = null;
				try {
					con = Databaseconnection.getconnection();
					con.setAutoCommit(false);
					int re = Curriculumaccess.delete(con, cc_id);
					if (re < 1) {
						Tools.connectionroolback(con, "排课取消失败，请联系系统管理员！");
						return;
					}
					re = Teachingtaskaccess.updatestate(con, tt_id, 0);
					if (re < 1) {
						Tools.connectionroolback(con, "教学任务状态修改失败，请联系系统管理员");
						return;
					}
					jTable1.setValueAt("未完成排课", jTable1.getSelectedRow(), 17);
					con.commit();
					filltablecurriculum();
					boolean b = rowlist.remove((Integer) jTable1
							.getSelectedRow());
					if (b) {
						Rowcolor.setColor(jTable1, rowlist, Color.green);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (!con.isClosed())
							con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			return;
		}
		// 如果有冲突，该时间段不能排课
		if (isOccupied(row, column)) {
			JOptionPane.showMessageDialog(this, "该时段已被占用，不能排课！");
			return;
		}
		// 取得教师编号
		int cr_id = ((Classroom) jComboBox5.getSelectedItem()).getCr_id();
		// 获取课程总学时
		int counthour = (Integer) jTable1.getValueAt(jTable1.getSelectedRow(),
				4);
		// 获得课程的理论学时
		int theoryhour = (Integer) jTable1.getValueAt(jTable1.getSelectedRow(),
				5);
		// 获得课程的实验与实践学时
		int experimentalhours = (Integer) jTable1.getValueAt(
				jTable1.getSelectedRow(), 6);
		int practicehour = (Integer) jTable1.getValueAt(
				jTable1.getSelectedRow(), 7);
		String Practicescheduling = (String) jTable1.getValueAt(
				jTable1.getSelectedRow(), 16);
		if (Practicescheduling.equals("否")) {
			counthour -= practicehour;
			practicehour = 0;
		}
		// 设置查找本门课程已排课时数的查询条件
		String condition = "tt_id=" + tt_id;
		// 查找本门课程已排课时数
		int classhour = Curriculumaccess.getClasshour(condition);
		// 取得当前拟排课的课时
		int currentinweeks = Tools.numberofweek(inweeks);
		// 新排课后总课时
		int newhour = currentinweeks + classhour;
		// 判定如果排课后总课时超限定，则终止排课
		if (newhour > counthour) {
			JOptionPane.showMessageDialog(this, "排课学时已超出总课时限制，不能继续排课");
			return;
		}
		// 如果当前选择是实验室的话
		if (jCheckBox17.isSelected()) {
			// 设置查找本门课程已排理论课时数的查询条件
			condition = "tt_id=" + tt_id + " and ct_id=3";
			// 查找本门课程已排实验实践课时数
			classhour = Curriculumaccess.getClasshour(condition);
			// 判定如果排课后实验实践总课时超限定，则提示信息
			if ((currentinweeks + classhour) > (experimentalhours + practicehour)) {
				int r = JOptionPane.showConfirmDialog(this,
						"实验实践排课学时已超出课时限制，是否继续排课？", "提示信息",
						JOptionPane.YES_NO_OPTION);
				// 如果选择不继续排课,则退出代码执行
				if (r == 1)
					return;
			}
		} else {
			// 设置查找本门课程已排理论课时数的查询条件
			condition = "tt_id=" + tt_id + " and (ct_id=1 or ct_id=2)";
			// 查找本门课程已排理论课时数
			classhour = Curriculumaccess.getClasshour(condition);
			// 判定如果排课后理论总课时超限定，则提示信息
			if (currentinweeks + classhour > theoryhour) {
				int r = JOptionPane.showConfirmDialog(this,
						"理论排课学时已超出理论课时限制，是否继续排课？", "提示信息",
						JOptionPane.YES_NO_OPTION);
				// 如果选择不继续排课,则退出代码执行
				if (r == 1)
					return;
			}
		}
		// 根据以上获取的信息生成课表类的对象
		Curriculum cc = new Curriculum(tt_id, row + 1, column, inweeks, cr_id,
				getsublaboratory());
		// 生成数据库的连接对象
		Connection con = null;
		try {
			// 取得数据库的连接
			con = Databaseconnection.getconnection();
			// 数据库连接的自动提交设为假
			con.setAutoCommit(false);

			// 生成查验该时间段课程占用情况的条件语句
			String cla_name = jTextField1.getText();
			String[] cla = cla_name.split(",");
			String sl_name = getconditionsublaboratory(getsublaboratory());
			// 设置条件用于查找数据库中该节课与教师、教室、学生时间是否冲突
			condition = "sy_id="
					+ ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id()
					+ " and (cla_name like '%" + cla[0] + "%'";
			for (int i = 1; i < cla.length; i++)
				condition += " or cla_name like '%" + cla[i] + "%'";
			condition += " or t_id='"
					+ (String) jTable1.getValueAt(jTable1.getSelectedRow(), 11)
					+ "'or " + sl_name + ") and lessons=" + (row + 1)
					+ " and week=" + column + " and tt_id!="
					+ (Integer) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
			String weeks = getconditioninweeks();
			if (!weeks.equals(""))
				condition += " and " + weeks;
			// 调用方法将课表对象信息插入到数据库中
			int r = Curriculumaccess.insert(con, cc, condition);
			// 插入不成功，提示错误信息后数据库回滚，然后退出本代码的执行
			if (r == -2) {
				Tools.connectionroolback(con, "此时间段已经被其他课程占用，请选择其他时间段");
				filltablecurriculum();
				return;
			}
			if (r < 1) {
				Tools.connectionroolback(con, "排课信息插入失败，请联系系统管理员");
				return;
			}
			boolean b = false;
			if (newhour == counthour) {
				r = Teachingtaskaccess.updatestate(con, tt_id, 1);
				if (r < 1) {
					Tools.connectionroolback(con, "教学任务状态修改失败，请联系系统管理员");
					return;
				}
				jTable1.setValueAt("完成排课", jTable1.getSelectedRow(), 17);
				b = rowlist.add(jTable1.getSelectedRow());
			}
			// 数据连接提交数据
			con.commit();
			// 刷新课程表格数据
			filltablecurriculum();
			if (b) {
				Rowcolor.setColor(jTable1, rowlist, Color.green);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {
		fillteachingtasktable();
	}

	private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
		fillteachingtasktable();
	}

	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		if (vt.getT_power().equals("系部排课")) {
			Office o = new Office(vt.getO_id(), vt.getD_id(), vt.getO_name());
			jComboBox2.addItem(o);
			return;
		}
		Department dm = (Department) jComboBox1.getSelectedItem();
		if (dm == null)
			return;
		String d_id = dm.getD_id();
		Fillcombobox.filloffice(d_id, jComboBox2);
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JCheckBox jCheckBox10;
	private javax.swing.JCheckBox jCheckBox11;
	private javax.swing.JCheckBox jCheckBox12;
	private javax.swing.JCheckBox jCheckBox13;
	private javax.swing.JCheckBox jCheckBox14;
	private javax.swing.JCheckBox jCheckBox15;
	private javax.swing.JCheckBox jCheckBox16;
	private javax.swing.JCheckBox jCheckBox17;
	private javax.swing.JCheckBox jCheckBox2;
	private javax.swing.JCheckBox jCheckBox3;
	private javax.swing.JCheckBox jCheckBox4;
	private javax.swing.JCheckBox jCheckBox5;
	private javax.swing.JCheckBox jCheckBox6;
	private javax.swing.JCheckBox jCheckBox7;
	private javax.swing.JCheckBox jCheckBox8;
	private javax.swing.JCheckBox jCheckBox9;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JList jList1;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable jTable2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JTextField jTextField6;
	// End of variables declaration//GEN-END:variables

}