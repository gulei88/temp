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
		// ��ʼ������
		initComponents();
		// �����ݵĲ�������¼�û�����ͼ��Ϣ�����ݸ���Ķ�Ӧ�����ֶ�
		this.vt = vt;
		// ���ú�����ʼ��ѧԺ�����б�
		fillcomboboxdepartment();
		// ������ľ�̬������ʼ��ѧ�������б�
		Fillcombobox.fillschoolyear(jComboBox4);
	}

	/**
	 * ��ʼ��ѧԺ�����б�
	 * 
	 * @author czy
	 */
	private void fillcomboboxdepartment() {
		// ���Ȩ��ΪѧУ������ѧԺ�����б������ľ�̬������ʼ��Ϊȫ��ѧԺ,����ѧԺ�����б��ʼ��Ϊ��¼������ѧԺ
		if (vt.getT_power().equals("ѧУ����"))
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
		if (o == null || (o.toString()).equals("ȫ��")) {
			data = new Object[1][71];
			columnNames = new String[] { "", "��һ��һ��", "��һ�ڶ���", "��һ������",
					"��һ���Ľ�", "��һ�����", "��һ������", "��һ���߽�", "��һ�ڰ˽�", "��һ�ھŽ�",
					"��һ��ʮ��", "�ܶ���һ��", "�ܶ��ڶ���", "�ܶ�������", "�ܶ����Ľ�", "�ܶ������",
					"�ܶ�������", "�ܶ����߽�", "�ܶ��ڰ˽�", "�ܶ��ھŽ�", "�ܶ���ʮ��", "������һ��",
					"�����ڶ���", "����������", "�������Ľ�", "���������", "����������", "�������߽�",
					"�����ڰ˽�", "�����ھŽ�", "������ʮ��", "���ĵ�һ��", "���ĵڶ���", "���ĵ�����",
					"���ĵ��Ľ�", "���ĵ����", "���ĵ�����", "���ĵ��߽�", "���ĵڰ˽�", "���ĵھŽ�",
					"���ĵ�ʮ��", "�����һ��", "����ڶ���", "���������", "������Ľ�", "��������",
					"���������", "������߽�", "����ڰ˽�", "����ھŽ�", "�����ʮ��", "������һ��",
					"�����ڶ���", "����������", "�������Ľ�", "���������", "����������", "�������߽�",
					"�����ڰ˽�", "�����ھŽ�", "������ʮ��", "���յ�һ��", "���յڶ���", "���յ�����",
					"���յ��Ľ�", "���յ����", "���յ�����", "���յ��߽�", "���յڰ˽�", "���յھŽ�",
					"���յ�ʮ��" };
			if (jRadioButton2.isSelected())
				columnNames[0] = "����";
			if (jRadioButton1.isSelected())
				columnNames[0] = "�༶";
			if (jRadioButton3.isSelected())
				columnNames[0] = "��ʦ";
		} else {
			data = new Object[1][8];
			columnNames = new String[] { "�ν�", "��һ", "�ܶ�", "����", "����", "����",
					"����", "����" };
		}
		Curriculumtable ct = new Curriculumtable(data, columnNames);
		jTable1.setModel(ct);
		jScrollPane1.setViewportView(jTable1);
		if (o == null || (o.toString()).equals("ȫ��")) {
			jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			for (int i = 0; i < 71; i++) {
				jTable1.getColumnModel().getColumn(i).setPreferredWidth(80);
			}
		}else {
			jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}
	}
	/**
	 * ʹ�ý�����Ϣ��������б�
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
			jComboBox3.addItem(new View_classroom("", "",0,"ȫ��", "", 0, 0, "",0,"", "",""));
		}
	}

	/**
	 * ʹ�ý�ʦ��Ϣ��������б�
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
			jComboBox3.addItem(new View_teacher("", "", "", "", "", "ȫ��", "",
					"", ""));
		}
	}

	/**
	 * ʹ�ð༶��Ϣ��������б�
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
					"", "", "ȫ��", "", 0, "", 0));
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
		if (vt.getT_power().equals("ϵ���ſ�")) {
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