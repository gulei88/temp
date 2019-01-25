/*
 * CZY_jif_teachingtask.java
 *
 * Created on __DATE__, __TIME__
 */

package czy.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.ResourceBundle.Control;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import czy.dao.Curriculumaccess;
import czy.dao.Courseexperimentalenvironmentaccess;
import czy.model.Tools;

import global.dao.Classinformationaccess;
import global.dao.Courseaccess;
import global.dao.Courseclassaccess;
import global.dao.Databaseconnection;
import global.dao.Teachingplangradeaccess;
import global.dao.Teachingtaskaccess;
import global.model.Classroom;
import global.model.Courseclass;
import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.Schoolyear;
import global.model.Teachingplangrade;
import global.model.Teachingtask;
import global.model.View_classinformation;
import global.model.View_course;
import global.model.View_curriculum;
import global.model.View_teacher;
import global.model.View_teachingtask;

/**
 * 
 * @author __USER__
 */
public class CZY_jif_teachingtask extends javax.swing.JInternalFrame {
	private View_teacher vt;

	/** Creates new form CZY_jif_teachingtask */
	public CZY_jif_teachingtask() {
		initComponents();
	}

	/**
	 * 构造界面
	 * 
	 * @author czy
	 * @param vt
	 *            ：登录用户的视图信息
	 */
	public CZY_jif_teachingtask(View_teacher vt) {
		// 初始化界面
		initComponents();
		System.out.println(vt);
		// 将传递的参数（登录用户的视图信息）传递给类的对应属性字段
		this.vt = vt;
		// 调用函数初始化学院下拉列表
		fillcomboboxdepartment();
		// 调用类的静态函数初始化学期下拉列表
		Fillcombobox.fillschoolyear(jComboBox4);
		setColumnWidth();
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
		DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
		// 清空表格中显示的数据
		dtm.setRowCount(0);
		// 取得专业下拉列表选择内容
		Major m = (Major) jComboBox3.getSelectedItem();
		// 如果为空，则退出
		if (m == null)
			return;
		// 取得学年下拉列表选择内容
		Schoolyear sy = (Schoolyear) jComboBox4.getSelectedItem();
		// 如果为空，则退出
		if (sy == null)
			return;
		// 取得年级下拉列表选择内容
		String grade = (String) jComboBox5.getSelectedItem();
		// 如果为空，则退出
		if (grade == null)
			return;
		// 生成查询条件用于查询指定专业、年级、学期对应的教学任务
		String condition = " m_id='"
				+ m.getM_id()
				+ "' and tt_grade='"
				+ grade
				+ "' and sy_name='"
				+ sy.getSy_name()
				+ "' order by cou_id,cou_theoryhour DESC,cou_experimentalhours  DESC,cou_practicehour  DESC";
		// 查找满足条件的教学计划试图信息到指定的课程视图类型的数组列表中
		ArrayList<View_teachingtask> aList = Teachingtaskaccess
				.getView_teachingtask(condition);
		// 遍历课程视图类型的数组列表
		for (int i = 0; i < aList.size(); i++) {
			// 取出对应字段信息
			int tt_id = aList.get(i).getTt_id();
			String cou_id = aList.get(i).getCou_id();
			String cou_category = aList.get(i).getCou_category();
			String cou_name = aList.get(i).getCou_name();
			int cou_theoryhour = aList.get(i).getCou_theoryhour();
			int cou_experimentalhours = aList.get(i).getCou_experimentalhours();
			int cou_practicehour = aList.get(i).getCou_practicehour();
			String t_name = aList.get(i).getT_name();
			String cla_name = aList.get(i).getCla_name();
			int cla_number = aList.get(i).getCla_number();
			String multimedia = aList.get(i).getMultimedia();
			String Practicescheduling = aList.get(i).getPracticescheduling();
			// 生成向量类型变量
			Vector v = new Vector();
			// 将取得数据添加到向量中
			v.add(tt_id);
			v.add(cou_id);
			v.add(cou_category);
			v.add(cou_name);
			v.add(cou_theoryhour);
			v.add(cou_experimentalhours);
			v.add(cou_practicehour);
			v.add(t_name);
			v.add(cla_name);
			v.add(cla_number);
			v.add(multimedia);
			v.add(Practicescheduling);
			// 将向量添加到表格中
			dtm.addRow(v);
		}

	}

	/**
	 * 填充课程表格
	 * 
	 * @author czy
	 */
	private void fillcoursetable() {
		// 取得表格的model信息
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		// 清空表格中显示的数据
		dtm.setRowCount(0);
		// 取得专业下拉列表选择内容
		Major m = (Major) jComboBox3.getSelectedItem();
		// 如果为空，则退出
		if (m == null)
			return;
		// 取得学年下拉列表选择内容
		Schoolyear sy = (Schoolyear) jComboBox4.getSelectedItem();
		// 如果为空，则退出
		if (sy == null)
			return;
		// 取得年级下拉列表选择内容
		String grade = (String) jComboBox5.getSelectedItem();
		// 如果为空，则退出
		if (grade == null)
			return;
		// 生成查询条件用于查询指定专业，年级对应的教学计划编号
		String condition = " m_id='" + m.getM_id() + "' and tg_grade='" + grade
				+ "'";
		// 将生成条件作为参数传递给函数，返回教学计划信息类型的数组列表
		ArrayList<Teachingplangrade> al = Teachingplangradeaccess
				.getTeachingplangradetwo(condition);
		// 如果数组列表中存在数据
		if (al.size() > 0) {
			// 获取教学计划编号
			String tp_id = al.get(0).getTp_id();
			// 根据选定的学期和年级转换出对应的整形学期
			int semester = Tools.Semesterconversion(sy.getSy_name(), grade);
			// 根据教学计划编号和学期生成查询条件
			condition = " tp_id='"
					+ tp_id
					+ "' and cou_semester="
					+ semester
					+ " and cou_id not in(SELECT cou_id FROM courseschedule.teachingtask where m_id='"
					+ m.getM_id() + "' and tt_grade='" + grade + "')";
			// 查找满足条件的课程信息到指定的课程视图类型的数组列表中
			ArrayList<View_course> al1 = Courseaccess.getView_course(condition);
			// 遍历课程视图类型的数组列表
			for (int i = 0; i < al1.size(); i++) {
				// 取出对应字段信息
				String cou_id = al1.get(i).getCou_id();
				String cou_category = al1.get(i).getCou_category();
				String cou_name = al1.get(i).getCou_name();
				int cou_theoryhour = al1.get(i).getCou_theoryhour();
				int cou_experimentalhours = al1.get(i)
						.getCou_experimentalhours();
				int cou_practicehour = al1.get(i).getCou_practicehour();
				int cou_type = al1.get(i).getCou_type();
				// 生成向量类型变量
				Vector v = new Vector();
				// 将取得数据添加到向量中
				v.add(tp_id);
				v.add(cou_id);
				v.add(cou_category);
				v.add(cou_name);
				v.add(cou_theoryhour);
				v.add(cou_experimentalhours);
				v.add(cou_practicehour);
				if (cou_type == 1)
					v.add("必修");
				else
					v.add("选修");
				// 将向量添加到表格中
				dtm.addRow(v);
			}
		}
	}

	private void setColumnWidth() {
		TableColumn Column;
		// 设置课程表格各列宽度
		Column = jTable1.getColumnModel().getColumn(0);
		Column.setPreferredWidth(50);
		Column = jTable1.getColumnModel().getColumn(1);
		Column.setPreferredWidth(70);
		Column = jTable1.getColumnModel().getColumn(2);
		Column.setPreferredWidth(85);
		Column = jTable1.getColumnModel().getColumn(3);
		Column.setPreferredWidth(150);
		Column = jTable1.getColumnModel().getColumn(4);
		Column.setPreferredWidth(50);
		Column = jTable1.getColumnModel().getColumn(5);
		Column.setPreferredWidth(50);
		Column = jTable1.getColumnModel().getColumn(6);
		Column.setPreferredWidth(50);
		Column = jTable1.getColumnModel().getColumn(7);
		Column.setPreferredWidth(50);
		// 设置教学任务表格各列宽度
		Column = jTable2.getColumnModel().getColumn(0);
		Column.setPreferredWidth(30);
		Column = jTable2.getColumnModel().getColumn(1);
		Column.setPreferredWidth(70);
		Column = jTable2.getColumnModel().getColumn(2);
		Column.setPreferredWidth(85);
		Column = jTable2.getColumnModel().getColumn(3);
		Column.setPreferredWidth(150);
		Column = jTable2.getColumnModel().getColumn(4);
		Column.setPreferredWidth(60);
		Column = jTable2.getColumnModel().getColumn(5);
		Column.setPreferredWidth(60);
		Column = jTable2.getColumnModel().getColumn(6);
		Column.setPreferredWidth(60);
		Column = jTable2.getColumnModel().getColumn(7);
		Column.setPreferredWidth(60);
		Column = jTable2.getColumnModel().getColumn(8);
		Column.setPreferredWidth(150);
		Column = jTable2.getColumnModel().getColumn(9);
		Column.setPreferredWidth(60);
		Column = jTable2.getColumnModel().getColumn(10);
		Column.setPreferredWidth(60);
		Column = jTable2.getColumnModel().getColumn(11);
		Column.setPreferredWidth(80);
	}

//GEN-BEGIN:initComponents
// <editor-fold defaultstate="collapsed" desc="Generated Code">
private void initComponents() {

jLabel1 = new javax.swing.JLabel();
jComboBox1 = new javax.swing.JComboBox();
jLabel2 = new javax.swing.JLabel();
jComboBox2 = new javax.swing.JComboBox();
jLabel3 = new javax.swing.JLabel();
jComboBox3 = new javax.swing.JComboBox();
jLabel4 = new javax.swing.JLabel();
jComboBox4 = new javax.swing.JComboBox();
jLabel5 = new javax.swing.JLabel();
jComboBox5 = new javax.swing.JComboBox();
jPanel1 = new javax.swing.JPanel();
jButton5 = new javax.swing.JButton();
jButton7 = new javax.swing.JButton();
jButton4 = new javax.swing.JButton();
jButton3 = new javax.swing.JButton();
jButton2 = new javax.swing.JButton();
jButton1 = new javax.swing.JButton();
jScrollPane2 = new javax.swing.JScrollPane();
jTable2 = new javax.swing.JTable();
jScrollPane1 = new javax.swing.JScrollPane();
jTable1 = new javax.swing.JTable();

setClosable(true);
setIconifiable(true);
setTitle("\u6559\u5b66\u4efb\u52a1\u7ba1\u7406");

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

jLabel3.setText("\u4e13\u4e1a\uff1a");

jComboBox3.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox3ItemStateChanged(evt);
}
});

jLabel4.setText("\u5b66\u671f\uff1a");

jComboBox4.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox4ItemStateChanged(evt);
}
});

jLabel5.setText("\u5e74\u7ea7\uff1a");

jComboBox5.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox5ItemStateChanged(evt);
}
});

jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "\u64cd\u4f5c"));

jButton5.setText("\u5206\u79bb\u5b9e\u9a8c");
jButton5.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton5ActionPerformed(evt);
}
});

jButton7.setText("\u5206\u79bb\u5b9e\u8df5");
jButton7.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton7ActionPerformed(evt);
}
});

jButton4.setText("\u5408\u73ed");
jButton4.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton4ActionPerformed(evt);
}
});

jButton3.setText("\u5206\u73ed");
jButton3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton3ActionPerformed(evt);
}
});

jButton2.setText("<");
jButton2.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton2ActionPerformed(evt);
}
});

jButton1.setText(">");
jButton1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton1ActionPerformed(evt);
}
});

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
.addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
.addContainerGap())
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.addComponent(jButton1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton2)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton3)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton4)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton5)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton7)
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

jTable2.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"编号", "课程编号", "课程类别", "课程名称", "理论学时", "实验学时", "实践学时", "教师名称", "授课班级", "班级人数", "多媒体教室", "实践是否排课"
	}
) {
	boolean[] canEdit = new boolean [] {
		false, false, false, false, false, false, false, false, false, false, false, false
	};

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
public void mouseClicked(java.awt.event.MouseEvent evt) {
jTable2MouseClicked(evt);
}
public void mousePressed(java.awt.event.MouseEvent evt) {
jTable2MousePressed(evt);
}
});
jScrollPane2.setViewportView(jTable2);

jTable1.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"计划编号", "课程编号", "类别", "课程名称", "理论学时", "实验学时", "实践学时", "课程性质"
	}
) {
	boolean[] canEdit = new boolean [] {
		false, false, false, false, false, false, false, false
	};

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
public void mouseClicked(java.awt.event.MouseEvent evt) {
jTable1MouseClicked(evt);
}
});
jScrollPane1.setViewportView(jTable1);

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addComponent(jLabel1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel2)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel3)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel4)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel5)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(layout.createSequentialGroup()
.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)))
.addContainerGap())
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel1)
.addComponent(jLabel2)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel3)
.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel4)
.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel5)
.addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)))
.addGroup(layout.createSequentialGroup()
.addGap(67, 67, 67)
.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
.addContainerGap())
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		// 获取教学任务表格中选中的行
		int row[] = jTable2.getSelectedRows();
		// 如果未选中行，则提示信息后退出当前代码
		if (row.length == 0) {
			JOptionPane.showMessageDialog(this, "请选择要分离实践的教学任务", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int tt_id;
		Connection con = null;
		try {
			// 取得数据库的连接
			con = Databaseconnection.getconnection();
			// 设置数据库连接自动提交为假
			con.setAutoCommit(false);
			// 遍历选中的行
			for (int i = 0; i < row.length; i++) {
				// 获得课程的实践学时
				int practicehour = (Integer) jTable2.getValueAt(row[i], 6);
				// 获得课程的实验学时
				int cou_experimentalhours = (Integer) jTable2.getValueAt(
						row[i], 5);
				// 获得课程的理论学时
				int cou_theoryhour = (Integer) jTable2.getValueAt(row[i], 4);
				// 如果实践学时为0，则分离下一个教学任务
				if ((cou_theoryhour == 0 && cou_experimentalhours == 0)
						|| practicehour == 0)
					continue;
				// 取得教学任务编号
				tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// 如果此课程已经排课，则不能进行本操作
				if (isSchedulingCourse(tt_id)) {
					String msg = "课程任务编号为" + tt_id + "的课程已经进行了排课，不能完成本操作！";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 根据教学任务编号设置查询条件
				String condition = "tt_id=" + tt_id;
				// 查询Teachingtask表中对应编号的教学任务信息
				Teachingtask tt = Teachingtaskaccess.getTeachingtask(condition)
						.get(0);
				int r1 = Teachingtaskaccess.updatepracticehour(con, tt_id, 0);
				// 将任务理论学时设为0
				tt.setCou_theoryhour(0);
				// 将任务实验学时设为0
				tt.setCou_experimentalhours(0);
				// 在Teachingtask表中插入修改后的教学任务，并返回新生成的教学任务编号
				int id = Teachingtaskaccess.insert(con, tt);
				// 如果修改或插入不成功，则调用函数进行回滚
				if (r1 < 1 || id < 0) {
					String msg = "教学任务实践分离失败，请联系系统管理员！";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 根据教学任务编号取得本教学任务对应的班级
				ArrayList<Courseclass> al = Courseclassaccess
						.getCourseclass(tt_id);
				for (int j = 0; j < al.size(); j++) {
					// 根据新插入的教学任务添加班级
					int r = Courseclassaccess.insert(con, id, al.get(j)
							.getCla_id());
					// 如果插入行数小于1，数据库回滚后退出
					if (r < 1) {
						String msg = "教学任务对应班级数据插入失败，请联系系统管理员！";
						Tools.connectionroolback(con, msg);
						return;
					}
				}

			}
			con.commit();
			fillteachingtasktable();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				if (con != null)
					con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		// 获取教学任务表格中选中的行
		int row[] = jTable2.getSelectedRows();
		// 如果未选中行，则提示信息后退出当前代码
		if (row.length == 0) {
			JOptionPane.showMessageDialog(this, "请选择要分离实验的教学任务", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int tt_id;
		Connection con = null;
		try {
			// 取得数据库的连接
			con = Databaseconnection.getconnection();
			// 设置数据库连接自动提交为假
			con.setAutoCommit(false);
			// 遍历选中的行
			for (int i = 0; i < row.length; i++) {
				// 获得课程的实验学时
				int cou_experimentalhours = (Integer) jTable2.getValueAt(
						row[i], 5);
				// 获得课程的理论学时
				int cou_theoryhour = (Integer) jTable2.getValueAt(row[i], 4);
				// 如果理论学时为0或者实验学时为0，则分离下一个实验任务
				if (cou_theoryhour == 0 || cou_experimentalhours == 0)
					continue;
				// 取得教学任务编号
				tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// 如果此课程已经排课，则不能进行本操作
				if (isSchedulingCourse(tt_id)) {
					String msg = "课程任务编号为" + tt_id + "的课程已经进行了排课，不能完成本操作！";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 根据教学任务编号设置查询条件
				String condition = "tt_id=" + tt_id;
				// 查询Teachingtask表中对应编号的教学任务信息
				Teachingtask tt = Teachingtaskaccess.getTeachingtask(condition)
						.get(0);
				int r1 = Teachingtaskaccess.updateexperimentalhours(con, tt_id,
						0);
				int r2 = Teachingtaskaccess.updatepracticehour(con, tt_id, 0);
				// 将任务理论学时设为0
				tt.setCou_theoryhour(0);
				// 在Teachingtask表中插入修改后的教学任务，并返回新生成的教学任务编号
				int id = Teachingtaskaccess.insert(con, tt);
				// 如果修改或插入不成功，则调用函数进行回滚
				if (r1 < 1 || r2 < 1 || id < 0) {
					String msg = "教学任务实验分离失败，请联系系统管理员！";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 根据教学任务编号取得本教学任务对应的班级
				ArrayList<Courseclass> al = Courseclassaccess
						.getCourseclass(tt_id);
				for (int j = 0; j < al.size(); j++) {
					// 根据新插入的教学任务添加班级
					int r = Courseclassaccess.insert(con, id, al.get(j)
							.getCla_id());
					// 如果插入行数小于1，数据库回滚后退出
					if (r < 1) {
						String msg = "教学任务对应班级数据插入失败，请联系系统管理员！";
						Tools.connectionroolback(con, msg);
						return;
					}
				}

			}
			con.commit();
			fillteachingtasktable();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				if (con != null)
					con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		// 获取教学任务表格中选中的行
		int row[] = jTable2.getSelectedRows();
		// 如果未选中行，则提示信息后退出当前代码
		if (row.length == 0) {
			JOptionPane.showMessageDialog(this, "请选择要合班的教学任务", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// 如果选中行数小于2，则提示信息后退出当前代码
		if (row.length < 2) {
			JOptionPane.showMessageDialog(this, "请选择两个以上的合班教学任务数", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Connection con = null;
		try {
			con = Databaseconnection.getconnection();
			con.setAutoCommit(false);
			String condition;
			int r;
			// 取得课程编号
			String cou_id = (String) jTable2.getValueAt(row[0], 1);
			// 如果此课程已经排课，则不能进行本操作
			if (isSchedulingCourse((Integer) jTable2.getValueAt(row[0], 0))) {
				String msg = "课程任务编号为"
						+ (Integer) jTable2.getValueAt(row[0], 0)
						+ "的课程已经进行了排课，不能完成本操作！";
				Tools.connectionroolback(con, msg);
				return;
			}
			int th = (Integer) jTable2.getValueAt(row[0], 4);
			int eh = (Integer) jTable2.getValueAt(row[0], 5);
			int ph = (Integer) jTable2.getValueAt(row[0], 6);
			for (int i = 1; i < row.length; i++) {
				if (!((String) jTable2.getValueAt(row[i], 1)).equals(cou_id)) {
					String msg = "请选择课程编号相同的教学任务进行合班";
					Tools.connectionroolback(con, msg);
					return;
				}
				int th1 = (Integer) jTable2.getValueAt(row[i], 4);
				int eh1 = (Integer) jTable2.getValueAt(row[i], 5);
				int ph1 = (Integer) jTable2.getValueAt(row[i], 6);
				if (th != th1 || eh != eh1 || ph != ph1) {
					String msg = "实验分离或实践分离的教学任务不能进行合班";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 取得教学任务编号
				int tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// 如果此课程已经排课，则不能进行本操作
				if (isSchedulingCourse(tt_id)) {
					String msg = "课程任务编号为" + tt_id + "的课程已经进行了排课，不能完成本操作！";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 根据教学任务编号取得本教学任务对应的班级
				ArrayList<Courseclass> al = Courseclassaccess
						.getCourseclass(tt_id);
				// 根据教学任务编号和对应班级号设置删除条件
				condition = "tt_id=" + tt_id;
				// 教学任务对应班级表中删除满足条件的记录
				r = Courseclassaccess.Delete(con, condition);
				// 如果删除行数小于1，数据库回滚后退出
				if (r < 1) {
					String msg = "教学任务对应班级数据删除失败，请联系系统管理员！";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 遍历数组列表
				for (int j = 0; j < al.size(); j++) {
					// 教学任务对应班级表中插入合并后的班级信息
					r = Courseclassaccess.insert(con, (Integer) jTable2
							.getValueAt(row[0], 0), al.get(j).getCla_id());
					// 如果插入行数小于1，数据库回滚后退出
					if (r < 1) {
						String msg = "教学任务对应班级数据插入失败，请联系系统管理员！";
						Tools.connectionroolback(con, msg);
						return;
					}

					// 生成教学任务删除条件
					// condition = "tt_id=" + tt_id;
					// 删除已经合并的教学任务
				}
				r = Teachingtaskaccess.delete(con, tt_id);
				if (r < 1) {
					String msg = "教学任务对应班级数据插入失败，请联系系统管理员！";
					Tools.connectionroolback(con, msg);
					return;
				}
			}
			// 事务提交
			con.commit();
			// 重信息刷新教学任务表格
			fillteachingtasktable();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// 获取教学任务表格中选中的行
		int row[] = jTable2.getSelectedRows();
		// 如果未选中行，则提示信息后退出当前代码
		if (row.length == 0) {
			JOptionPane.showMessageDialog(this, "请选择要分班的教学任务", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int tt_id;

		Connection con = null;
		try {
			// 取得数据库的连接
			con = Databaseconnection.getconnection();
			// 设置数据库连接自动提交为假
			con.setAutoCommit(false);
			// 遍历选中的行
			for (int i = 0; i < row.length; i++) {
				// 取得教学任务编号
				tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// 如果此课程已经排课，则不能进行本操作
				if (isSchedulingCourse(tt_id)) {
					String msg = "课程任务编号为" + tt_id + "的课程已经进行了排课，不能完成本操作！";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 根据教学任务编号取得本教学任务对应的班级
				ArrayList<Courseclass> al = Courseclassaccess
						.getCourseclass(tt_id);
				// 如果只对应一个班级，则提示不能分班，并退出当前代码
				if (al.size() == 1) {
					JOptionPane.showMessageDialog(this, "编号为" + tt_id
							+ "的教学任务班级数为1，不能分班，请重新选择", "提示信息",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// 根据教学任务编号设置查询条件
				String condition = "tt_id=" + tt_id;
				// 查询Teachingtask表中对应编号的教学任务信息
				Teachingtask tt = Teachingtaskaccess.getTeachingtask(condition)
						.get(0);
				// 生成删除教学任务与班级对应表数据的条件
				condition = "tt_id='" + tt_id + "' and cla_id!='"
						+ al.get(0).getCla_id() + "'";
				// 删除教学任务与班级对应表中指定数据
				Courseclassaccess.Delete(con, condition);
				for (int j = 1; j < al.size(); j++) {
					// 在Teachingtask表中再次插入本教学任务，并返回新生成的教学任务编号
					int id = Teachingtaskaccess.insert(con, tt);
					// 如果插入不成功，则调用函数进行回滚
					if (id < 0) {
						String msg = "教学任务数据插入失败，请联系系统管理员！";
						Tools.connectionroolback(con, msg);
						return;
					}
					// 根据新插入的教学任务添加班级
					int r = Courseclassaccess.insert(con, id, al.get(j)
							.getCla_id());
					// 如果插入行数小于1，数据库回滚后退出
					if (r < 1) {
						String msg = "教学任务对应班级数据插入失败，请联系系统管理员！";
						Tools.connectionroolback(con, msg);
						return;
					}
				}
			}

			con.commit();
			fillteachingtasktable();
		} catch (Exception e) {
			try {
				if (con != null)
					con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void jTable2MousePressed(java.awt.event.MouseEvent evt) {

	}

	private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {
		// 获取鼠标点击次数
		int clicked = evt.getClickCount();
		// 如果不等于2则退出函数执行(鼠标双击)
		if (clicked != 2)
			return;
		// 获得教学任务表格中选中的行数信息
		int rows[] = jTable2.getSelectedRows();
		// 如果没有选中行则退出函数执行
		if (rows.length > 1)
			return;
		// 获取选中的第一行的教学任务编号信息
		int tt_id = (Integer) jTable2.getValueAt(rows[0], 0);
		// 生成教学任务数据表查询条件
		String condition = "tt_id=" + tt_id;
		// 如果此课程已经排课，则不能进行本操作
		if (isSchedulingCourse(tt_id)) {
			JOptionPane.showMessageDialog(this, "课程任务编号为" + tt_id
					+ "的课程已经进行了排课，不能完成本操作！");
			return;
		}
		// 从教学任务数据表中查询满足条件信息返回到数组列表中
		ArrayList<View_teachingtask> al = Teachingtaskaccess
				.getView_teachingtask(condition);

		// 初始化教学任务修改窗体
		CZY_jd_teachingtaskmodify ttm = new CZY_jd_teachingtaskmodify(null,
				true, al.get(0), (Department) jComboBox1.getSelectedItem(),
				(Office) jComboBox2.getSelectedItem());
		// 窗体显示
		ttm.setVisible(true);
		// 刷新教学任务表格
		fillteachingtasktable();

	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// 获得教学任务表格中选中的行数信息
		int row[] = jTable2.getSelectedRows();
		// 调取函数计算教学任务表格中没有选中的行数信息
		int unselected[] = Tools.getunselectedrows(row, jTable2.getRowCount());
		int tt_id;
		Connection con = null;
		int r = 0;
		try {
			// 获取数据库的连接
			con = Databaseconnection.getconnection();
			// 数据连接设置自动提交为假
			con.setAutoCommit(false);
			// 遍历选中的行
			for (int i = 0; i < row.length; i++) {
				// 获取教学任务编号
				tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// 如果此课程已经排课，则不能进行本操作
				if (isSchedulingCourse(tt_id)) {
					String msg = "课程任务编号为" + tt_id + "的课程已经进行了排课，不能完成本操作！";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 获取课程编号
				String cou_id = (String) jTable2.getValueAt(row[i], 1);
				int j;
				// 遍历未选中行
				for (j = 0; j < unselected.length; j++) {
					// 如果在未选中行中发现课程编号重复信息则退出当前循环
					if (cou_id.equals((String) jTable2.getValueAt(
							unselected[j], 1))) {
						break;
					}
				}
				// 如果发现未选中行中存在选中的课程信息，则提示当前课程任务不能单独删除，数据库回滚后退出
				if (j < unselected.length) {
					String msg = "课程任务删除应删除这门课程所有班级的任务，请查看课程编号为" + cou_id
							+ "的课程任务！";
					Tools.connectionroolback(con, msg);
					return;
				}
				// 教学任务表中删除指定的教学任务
				r = Teachingtaskaccess.delete(con, tt_id);
				// 如果删除行数小于1，数据库回滚后退出
				if (r < 1) {
					String msg = "教学任务数据删除失败，请联系系统管理员！";
					Tools.connectionroolback(con, msg);
					return;
				}
				String grade = (String) jComboBox5.getSelectedItem();
				r = Courseexperimentalenvironmentaccess.deletebycouidgrade(con,
						cou_id, grade);
				if (r == -1) {
					String msg = "因数据库连接异常，教学任务数据删除失败，请联系系统管理员！";
					Tools.connectionroolback(con, msg);
					return;
				}
			}
			// 事务提交
			con.commit();
			// 刷新教学任务表格
			fillteachingtasktable();
			// 刷新课程表格
			fillcoursetable();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				// }
				if (!con.isClosed())
					con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// 获取课程表格中选中的行
		int[] rows = jTable1.getSelectedRows();
		// 遍历选中的行
		for (int i = 0; i < rows.length; i++) {
			// 获取选中行中的课程编号
			String cou_id = (String) jTable1.getValueAt(rows[i], 1);
			int cou_theoryhour = (Integer) jTable1.getValueAt(rows[i], 4);
			int cou_experimentalhours = (Integer) jTable1
					.getValueAt(rows[i], 5);
			int cou_practicehour = (Integer) jTable1.getValueAt(rows[i], 6);
			// 获取下拉列表中的学期编号
			int sy_id = ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id();
			// 是否多媒体字段初始化为是
			String multimedia = "是";
			// 获取下拉列表中的专业编号
			String m_id = ((Major) jComboBox3.getSelectedItem()).getM_id();
			// 获取下拉列表中的年级
			String tt_grade = (String) jComboBox5.getSelectedItem();
			// 根据获取的信息生成教学任务对象
			Teachingtask tt = new Teachingtask(cou_id, cou_theoryhour,
					cou_experimentalhours, cou_practicehour, "-1", sy_id,
					multimedia, m_id, tt_grade, "", "", 0);
			Connection con = null;
			try {
				// 取得数据库的连接
				con = Databaseconnection.getconnection();
				con.setAutoCommit(false);
				// 将教学任务对象插入到数据库教学任务表中
				int tt_id = Teachingtaskaccess.insert(con, tt);
				// 如果插入成功
				if (tt_id >= 0) {
					// 根据专业编号、年级生成数据库查询条件
					String condition = "m_id='" + m_id + "' and cla_grade='"
							+ tt_grade + "'";
					// 在班级信息数据库中查询满足条件记录到View_classinformation类型数组列表中
					ArrayList<View_classinformation> al = Classinformationaccess
							.getView_classinformation(condition);
					// 遍历获取到的数组列表
					for (int j = 0; j < al.size(); j++) {
						// 插入信息到数据库教学任务对应班级表中
						int r = Courseclassaccess.insert(con, tt_id, al.get(j)
								.getCla_id());
						// 如果插入行数小于1，数据库回滚后退出
						if (r < 1) {
							String msg = "教学任务对应班级数据插入失败，请联系系统管理员！";
							Tools.connectionroolback(con, msg);
							return;
						}
					}
				} else {// 如果插入失败，则调用方法进行回滚
					String msg = "教学任务数据插入失败，请联系系统管理员！";
					Tools.connectionroolback(con, msg);
					return;
				}

				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		fillteachingtasktable();
		fillcoursetable();
	}

	private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {
		fillteachingtasktable();
		fillcoursetable();
	}

	private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {
		fillteachingtasktable();
		fillcoursetable();
	}

	private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {
		Schoolyear sy = (Schoolyear) jComboBox4.getSelectedItem();
		if (sy == null)
			return;
		String sy_name = sy.getSy_name();
		Fillcombobox.fillgrade(sy_name, jComboBox5);
	}

	private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
		Office o = (Office) jComboBox2.getSelectedItem();
		if (o == null)
			return;
		String o_id = o.getO_id();
		Fillcombobox.fillmajor(o_id, jComboBox3);
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

	private boolean isSchedulingCourse(int tt_id) {
		// 设置条件用于查找数据库中该节课与教师、教室、学生时间是否冲突
		String condition = "tt_id=" + tt_id;
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

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton7;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable jTable2;
	// End of variables declaration//GEN-END:variables

}