/*
 * CZY_jif_curriculumquery.java
 *
 * Created on __DATE__, __TIME__
 */

package czy.view;

import java.util.Vector;

import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.View_classinformation;
import global.model.View_classroom;
import global.model.View_teacher;

import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import czy.view.CZY_jif_curriculum.ActionTry;

/**
 *
 * @author  __USER__
 */
public class CZY_jif_curriculumquery extends javax.swing.JInternalFrame {
	private View_teacher vt;

	/** Creates new form CZY_jif_curriculumquery */
	public CZY_jif_curriculumquery() {
		initComponents();
	}

	public CZY_jif_curriculumquery(View_teacher vt) {
		// 初始化界面
		initComponents();
		// 将传递的参数（登录用户的视图信息）传递给类的对应属性字段
		this.vt = vt;
		// 调用函数初始化学院下拉列表
		fillcomboboxdepartment();
		// 调用类的静态函数初始化学期下拉列表
		Fillcombobox.fillschoolyear(jComboBox4);
	}

	/**
	 * 初始化学院下拉列表
	 * 
	 * @author czy
	 */
	private void fillcomboboxdepartment() {
		// 如果权限为学校管理，则学院下拉列表调用类的静态函数初始化为全部学院,否则学院下拉列表初始化为登录者所在学院
		if (vt.getT_power().equals("学校管理"))
			Fillcombobox.filldepartment(jComboBox1);
		else {
			Department dm = new Department(vt.getD_id(), vt.getD_name());
			jComboBox1.addItem(dm);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

buttonGroup1 = new javax.swing.ButtonGroup();
jLabel2 = new javax.swing.JLabel();
jLabel3 = new javax.swing.JLabel();
jPanel1 = new javax.swing.JPanel();
jLabel1 = new javax.swing.JLabel();
jComboBox1 = new javax.swing.JComboBox();
jLabel4 = new javax.swing.JLabel();
jComboBox2 = new javax.swing.JComboBox();
jLabel5 = new javax.swing.JLabel();
jRadioButton1 = new javax.swing.JRadioButton();
jRadioButton2 = new javax.swing.JRadioButton();
jRadioButton3 = new javax.swing.JRadioButton();
jComboBox3 = new javax.swing.JComboBox();
jButton1 = new javax.swing.JButton();
jLabel6 = new javax.swing.JLabel();
jComboBox5 = new javax.swing.JComboBox();
jComboBox4 = new javax.swing.JComboBox();
jButton2 = new javax.swing.JButton();
jScrollPane1 = new javax.swing.JScrollPane();
jTable1 = new javax.swing.JTable();

setClosable(true);
setIconifiable(true);
setMaximizable(true);
setResizable(true);
setTitle("\u8bfe\u8868\u67e5\u8be2");

jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "\u67e5\u8be2\u6761\u4ef6"));

jLabel1.setText("\u5b66\u9662\uff1a");

jComboBox1.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox1ItemStateChanged(evt);
}
});

jLabel4.setText("\u7cfb\u90e8\uff1a");

jComboBox2.setEnabled(false);
jComboBox2.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox2ItemStateChanged(evt);
}
});

jLabel5.setText("\u5b66\u671f\uff1a");

buttonGroup1.add(jRadioButton1);
jRadioButton1.setText("\u73ed\u7ea7");
jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jRadioButton1ItemStateChanged(evt);
}
});

buttonGroup1.add(jRadioButton2);
jRadioButton2.setSelected(true);
jRadioButton2.setText("\u6559\u5ba4");
jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jRadioButton2ItemStateChanged(evt);
}
});

buttonGroup1.add(jRadioButton3);
jRadioButton3.setText("\u6559\u5e08");
jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jRadioButton3ItemStateChanged(evt);
}
});


jButton1.setText("\u67e5\u8be2");
jButton1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton1ActionPerformed(evt);
}
});

jLabel6.setText("\u4e13\u4e1a\uff1a");

jComboBox5.setEnabled(false);
jComboBox5.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox5ItemStateChanged(evt);
}
});


jButton2.setText("\u5bfc\u51fa");

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.addComponent(jLabel1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel4)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel6)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel5)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jRadioButton2)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jRadioButton1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jRadioButton3)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton2)
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel1)
.addComponent(jLabel4)
.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel6)
.addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel5)
.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jRadioButton2)
.addComponent(jRadioButton1)
.addComponent(jRadioButton3)
.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jButton1)
.addComponent(jButton2))
.addContainerGap(8, Short.MAX_VALUE))
);

jTable1.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		
	}
));
jScrollPane1.setViewportView(jTable1);

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1421, Short.MAX_VALUE)
.addContainerGap())
.addGroup(layout.createSequentialGroup()
.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(82, 82, 82))))
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(0, 0, Short.MAX_VALUE)
.addComponent(jLabel2)
.addGap(0, 0, Short.MAX_VALUE)))
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(0, 0, Short.MAX_VALUE)
.addComponent(jLabel3)
.addGap(0, 0, Short.MAX_VALUE)))
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(18, 18, 18)
.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
.addContainerGap())
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(0, 0, Short.MAX_VALUE)
.addComponent(jLabel2)
.addGap(0, 0, Short.MAX_VALUE)))
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(0, 0, Short.MAX_VALUE)
.addComponent(jLabel3)
.addGap(0, 0, Short.MAX_VALUE)))
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {
		// TODO add your handling code here:
		if (jRadioButton2.isSelected()) {
			jComboBox2.setEnabled(false);
			jComboBox5.setEnabled(false);
		}
		FillComboxByClassRoom();
	}

	private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {
		FillComboxByClassinformation();
	}

	private void jRadioButton3ItemStateChanged(java.awt.event.ItemEvent evt) {
		if (jRadioButton3.isSelected()) {
			jComboBox2.setEnabled(true);
			jComboBox5.setEnabled(false);
		}
		FillComboxByTeacher();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		Object o = jComboBox3.getSelectedItem();
		Object data[][];
		Object columnNames[];
		if (o == null || (o.toString()).equals("全部")) {
			data = new Object[1][71];
			columnNames = new String[] { "", "周一第一节", "周一第二节", "周一第三节",
					"周一第四节", "周一第五节", "周一第六节", "周一第七节", "周一第八节", "周一第九节",
					"周一第十节", "周二第一节", "周二第二节", "周二第三节", "周二第四节", "周二第五节",
					"周二第六节", "周二第七节", "周二第八节", "周二第九节", "周二第十节", "周三第一节",
					"周三第二节", "周三第三节", "周三第四节", "周三第五节", "周三第六节", "周三第七节",
					"周三第八节", "周三第九节", "周三第十节", "周四第一节", "周四第二节", "周四第三节",
					"周四第四节", "周四第五节", "周四第六节", "周四第七节", "周四第八节", "周四第九节",
					"周四第十节", "周五第一节", "周五第二节", "周五第三节", "周五第四节", "周五第五节",
					"周五第六节", "周五第七节", "周五第八节", "周五第九节", "周五第十节", "周六第一节",
					"周六第二节", "周六第三节", "周六第四节", "周六第五节", "周六第六节", "周六第七节",
					"周六第八节", "周六第九节", "周六第十节", "周日第一节", "周日第二节", "周日第三节",
					"周日第四节", "周日第五节", "周日第六节", "周日第七节", "周日第八节", "周日第九节",
					"周日第十节" };
			if (jRadioButton2.isSelected())
				columnNames[0] = "教室";
			if (jRadioButton1.isSelected())
				columnNames[0] = "班级";
			if (jRadioButton3.isSelected())
				columnNames[0] = "教师";
		} else {
			data = new Object[1][8];
			columnNames = new String[] { "课节", "周一", "周二", "周三", "周四", "周五",
					"周六", "周日" };
		}
		Curriculumtable ct = new Curriculumtable(data, columnNames);
		jTable1.setModel(ct);
		jScrollPane1.setViewportView(jTable1);
		if (o == null || (o.toString()).equals("全部")) {
			jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			for (int i = 0; i < 71; i++) {
				jTable1.getColumnModel().getColumn(i).setPreferredWidth(80);
			}
		}else {
			jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}
	}
	/**
	 * 使用教室信息填充下拉列表
	 * @author czy
	 */
	public void FillComboxByClassRoom() {
		if (jRadioButton2.isSelected()) {
			jComboBox3.removeAllItems();
			Department dm = (Department) jComboBox1.getSelectedItem();
			if (dm == null)
				return;
			String d_id = dm.getD_id();
			Fillcombobox.fillClassRoom(d_id, jComboBox3);
			jComboBox3.addItem(new View_classroom("", "",0,"全部", "", 0, 0, "",0,"", "",""));
		}
	}

	/**
	 * 使用教师信息填充下拉列表
	 * @author czy
	 */
	public void FillComboxByTeacher() {
		if (jRadioButton3.isSelected()) {
			jComboBox3.removeAllItems();
			Office o = (Office) jComboBox2.getSelectedItem();
			if (o == null)
				return;
			String o_id = o.getO_id();
			Fillcombobox.fillteacher(o_id, vt, jComboBox3);
			jComboBox3.addItem(new View_teacher("", "", "", "", "", "全部", "",
					"", ""));
		}
	}

	/**
	 * 使用班级信息填充下拉列表
	 * @param CZY
	 */
	public void FillComboxByClassinformation() {

		if (jRadioButton1.isSelected()) {
			jComboBox3.removeAllItems();
			Major m = (Major) jComboBox5.getSelectedItem();
			if (m == null)
				return;
			String m_id = m.getM_id();
			String condition = " m_id='" + m_id + "'";
			Fillcombobox.FillClassinformation(condition, jComboBox3);
			jComboBox3.addItem(new View_classinformation("", "", "", "", "",
					"", "", "全部", "", 0, "", 0));
		}
	}

	private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {
		if (jRadioButton1.isSelected()) {
			jComboBox2.setEnabled(true);
			jComboBox5.setEnabled(true);
		}
		FillComboxByClassinformation();
	}

	private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
		Office o = (Office) jComboBox2.getSelectedItem();
		if (o == null)
			return;
		String o_id = o.getO_id();
		Fillcombobox.fillmajor(o_id, jComboBox5);
		FillComboxByTeacher();
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
		FillComboxByClassRoom();
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
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
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JRadioButton jRadioButton3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	// End of variables declaration//GEN-END:variables

}

class Curriculumtable extends javax.swing.table.DefaultTableModel {
	boolean[] canEdit;

	public Curriculumtable(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		canEdit = new boolean[columnNames.length];
		// TODO Auto-generated constructor stub
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}