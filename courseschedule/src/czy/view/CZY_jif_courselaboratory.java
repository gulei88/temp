/*
 * CZY_jif_courselaboratory.java
 *
 * Created on __DATE__, __TIME__
 */

package czy.view;

import java.util.ArrayList;
import java.util.Vector;

import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.Schoolyear;
import global.model.View_teacher;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import czy.dao.Courseexperimentalenvironmentaccess;
import czy.dao.Courselaboratoryaccess;
import czy.model.Courseexperimentalenvironment;
import czy.model.Courselaboratory;
import czy.model.Tools;
import czy.model.View_courseexperimentalenvironment;
import czy.model.View_courselaboratory;

/**
 * 
 * @author __USER__
 */
public class CZY_jif_courselaboratory extends javax.swing.JInternalFrame {
	private View_teacher vt;

	/** Creates new form CZY_jif_courselaboratory */
	public CZY_jif_courselaboratory() {
		initComponents();
	}
	
	public CZY_jif_courselaboratory(View_teacher vt) {
		// 初始化界面
		initComponents();
		// 将传递的参数（登录用户的视图信息）传递给类的对应属性字段
		this.vt = vt;
//		System.out.println(vt);
		// 调用函数初始化学院下拉列表
		fillcomboboxdepartment();
		// 调用类的静态函数初始化学期下拉列表
		Fillcombobox.fillschoolyear(jComboBox4);
		// 设置jtable表格行单选
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 隐藏编号列
		Tools.hideColumn(jTable3, 0);
		// 隐藏实验室编号列
		Tools.hideColumn(jTable3, 1);
		// 隐藏实验分室编号列
		Tools.hideColumn(jTable3, 3);
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

	private void filltableExperimentalenvironment() {
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
		int sy_id = sy.getSy_id();
		String m_id = m.getM_id();
		ArrayList<View_courseexperimentalenvironment> ceList = Courseexperimentalenvironmentaccess
				.getview_courseexperimentalenvironment(sy_id, m_id);
		for (int i = 0; i < ceList.size(); i++) {
			// 取出对应字段信息
			int ce_id = ceList.get(i).getCe_id();
			String cou_id = ceList.get(i).getCou_id();
			String cou_category = ceList.get(i).getCou_category();
			String cou_name = ceList.get(i).getCou_name();

			int cou_theoryhour = ceList.get(i).getCou_theoryhour();
			int cou_experimentalhours = ceList.get(i)
					.getCou_experimentalhours();
			int cou_practicehour = ceList.get(i).getCou_practicehour();
			String grade = ceList.get(i).getGrade();
			String cla_name = ceList.get(i).getCla_name();
			String cla_number = ceList.get(i).getCla_number();
			String Numberofcombinedclasses = ceList.get(i)
					.getNumberofcombinedclasses();
			String t_id = ceList.get(i).getT_id();
			String t_name = ceList.get(i).getT_name();
			String Experimentalenvironment = ceList.get(i)
					.getExperimentalenvironment();

			// 生成向量类型变量
			Vector v = new Vector();
			// 将取得数据添加到向量中
			v.add(ce_id);
			v.add(cou_id);
			v.add(cou_category);
			v.add(cou_name);
			v.add(cou_theoryhour);
			v.add(cou_experimentalhours);
			v.add(cou_practicehour);
			v.add(grade);
			v.add(cla_name);
			v.add(cla_number);
			v.add(Numberofcombinedclasses);
			v.add(t_id);
			v.add(t_name);
			v.add(Experimentalenvironment);
			// 将向量添加到表格中
			dtm.addRow(v);
		}
	}

	private void Filltablecourselaboratory() {
		// 取得表格的model信息
		DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
		// 清空表格中显示的数据
		dtm.setRowCount(0);
		int row = jTable1.getSelectedRow();
		if (row == -1)
			return;
		int ce_id = (Integer) jTable1.getValueAt(row, 0);

		ArrayList<View_courselaboratory> cllist = Courselaboratoryaccess
				.getcourselaboratorybyceid(ce_id);
		for (int i = 0; i < cllist.size(); i++) {
			// 取出对应字段信息

			String cl_id = cllist.get(i).getCl_id();
			// int ce_id=cllist.get(i).getCe_id() ;
			String sl_id = cllist.get(i).getSl_id();
			String sl_name = cllist.get(i).getSl_name();
			int sumsl_seating = cllist.get(i).getSumsl_seating();
			String sl_seating = cllist.get(i).getSl_seating();
			int cr_id = cllist.get(i).getCr_id();
			String d_id = cllist.get(i).getD_id();
			String cr_name = cllist.get(i).getCr_name();
			int ct_id = cllist.get(i).getCt_id();
			int cr_seating = cllist.get(i).getCr_seating();
			int b_id = cllist.get(i).getB_id();

			// 生成向量类型变量
			Vector v = new Vector();
			// 将取得数据添加到向量中
			v.add(cl_id);
			v.add(cr_id);
			v.add(cr_name);
			v.add(sl_id);
			v.add(sl_name);
			// 如果实验分室座位数大于0,则可容纳人数添加为实验分室座位数
			if (sumsl_seating > 0)
				v.add(sumsl_seating);
			else
				// 否则可容纳人数添加为实验室座位数
				v.add(cr_seating);
			// 将向量添加到表格中
			dtm.addRow(v);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

jScrollPane2 = new javax.swing.JScrollPane();
jTable2 = new javax.swing.JTable();
jScrollPane1 = new javax.swing.JScrollPane();
jTable1 = new javax.swing.JTable();
jLabel1 = new javax.swing.JLabel();
jComboBox1 = new javax.swing.JComboBox();
jLabel2 = new javax.swing.JLabel();
jComboBox2 = new javax.swing.JComboBox();
jLabel3 = new javax.swing.JLabel();
jComboBox3 = new javax.swing.JComboBox();
jLabel4 = new javax.swing.JLabel();
jComboBox4 = new javax.swing.JComboBox();
jScrollPane3 = new javax.swing.JScrollPane();
jTable3 = new javax.swing.JTable();

jTable2.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		{null, null, null, null},
		{null, null, null, null},
		{null, null, null, null},
		{null, null, null, null}
	},
	new String [] {
		"Title 1", "Title 2", "Title 3", "Title 4"
	}
));
jScrollPane2.setViewportView(jTable2);

setClosable(true);
setIconifiable(true);

jTable1.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"编号", "课程编号", "课程类别", "课程名称", "理论学时", "实验学时", "实践学时", "年级", "开课班级", "班级总人数", "最大合班数", "教师编号", "授课教师", "实验环境"
	}
) {
	boolean[] canEdit = new boolean [] {
		false, false, false, false, false, false, false, false, false, false, false, false, false, false
	};

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
public void mouseClicked(java.awt.event.MouseEvent evt) {
jTable1MouseClicked(evt);
}
});
jScrollPane1.setViewportView(jTable1);

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

jTable3.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"编号", "实验室编号", "实验室名称", "实验分室编号", "实验分室名称", "可容纳人数"
	}
) {
	boolean[] canEdit = new boolean [] {
		false, false, false, false, false, false
	};

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
jScrollPane3.setViewportView(jTable3);

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
.addContainerGap())
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
.addContainerGap())
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
.addGap(186, 186, 186)))))
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addContainerGap(375, Short.MAX_VALUE)
.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
.addContainerGap())
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
.addGap(183, 183, 183)))
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// 获取鼠标点击次数
		int clicked = evt.getClickCount();
		// 如果不等于2则退出函数执行(鼠标双击)
		if (clicked == 1) {
			Filltablecourselaboratory();
			return;
		}
		if (clicked == 2) {
			// 获得实验环境表格中选中的行数信息
			int row = jTable1.getSelectedRow();
			// 如果没有选中行则退出函数执行
			if (row == -1)
				return;
			int ce_id = (Integer) jTable1.getValueAt(row, 0);
			// 初始化课程实验室修改窗体
			CZY__jd_updatecourselaboratory ucl = new CZY__jd_updatecourselaboratory(
					null, true, ce_id);
			// 窗体显示
			ucl.setVisible(true);
			Filltablecourselaboratory();
		}
	}

	private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {
		filltableExperimentalenvironment();
	}

	private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {
		filltableExperimentalenvironment();
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

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable jTable2;
	private javax.swing.JTable jTable3;
	// End of variables declaration//GEN-END:variables

}