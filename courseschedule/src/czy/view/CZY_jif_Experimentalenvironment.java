/*
 * CZY_jif_Experimentalenvironment.java
 *
 * Created on __DATE__, __TIME__
 */

package czy.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import zxx.dao.PrintObjectExcel;

import czy.dao.Courseexperimentalenvironmentaccess;
import czy.model.ExportExperimentalenvironment;
import czy.model.Courseexperimentalenvironment;
import czy.model.View_courseexperimentalenvironment;
import global.dao.Teachingtaskaccess;
import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.Schoolyear;
import global.model.View_teacher;
import global.model.View_teachingtask;

/**
 * 
 * @author __USER__
 */
public class CZY_jif_Experimentalenvironment extends javax.swing.JInternalFrame {
	private View_teacher vt;

	/** Creates new form CZY_jif_Experimentalenvironment */
	public CZY_jif_Experimentalenvironment() {
		initComponents();
	}

	public CZY_jif_Experimentalenvironment(View_teacher vt) {
		// ��ʼ������
		initComponents();
		// �����ݵĲ�������¼�û�����ͼ��Ϣ�����ݸ���Ķ�Ӧ�����ֶ�
		this.vt = vt;
		// ���ú�����ʼ��ѧԺ�����б�
		fillcomboboxdepartment();
		// ������ľ�̬������ʼ��ѧ�������б�
		Fillcombobox.fillschoolyear(jComboBox4);
		// ����jtable����е�ѡ
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * ��ʼ��ѧԺ�����б�
	 * 
	 * @author czy
	 */
	private void fillcomboboxdepartment() {
		// �����¼��Ȩ��Ϊϵ���ſλ��Ź�����ѧԺ�����б������֮��ʼ��Ϊ��¼�û�����ѧԺ
		if (vt.getT_power().equals("ϵ���ſ�") || vt.getT_power().equals("���Ź���")) {
			Department dm = new Department(vt.getD_id(), vt.getD_name());
			jComboBox1.addItem(dm);
		}
		// ���Ȩ��ΪѧУ������ѧԺ�����б������ľ�̬������ʼ��Ϊȫ��ѧԺ
		else if (vt.getT_power().equals("ѧУ����")) {
			Fillcombobox.filldepartment(jComboBox1);
		}
	}

	private void filltableExperimentalenvironment() {
		// ȡ�ñ���model��Ϣ
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		// ��ձ������ʾ������
		dtm.setRowCount(0);
		// ȡ��רҵ�����б�ѡ������
		Major m = (Major) jComboBox3.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (m == null)
			return;
		// ȡ��ѧ�������б�ѡ������
		Schoolyear sy = (Schoolyear) jComboBox4.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (sy == null)
			return;
		int sy_id = sy.getSy_id();
		String m_id = m.getM_id();
		ArrayList<View_courseexperimentalenvironment> ceList = Courseexperimentalenvironmentaccess
				.getview_courseexperimentalenvironment(sy_id, m_id);
		for (int i = 0; i < ceList.size(); i++) {
			// ȡ����Ӧ�ֶ���Ϣ
			int ce_id = ceList.get(i).getCe_id();
			String cou_id = ceList.get(i).getCou_id();
			String cou_name = ceList.get(i).getCou_name();
			int cou_theoryhour = ceList.get(i).getCou_theoryhour();
			int cou_experimentalhours = ceList.get(i)
					.getCou_experimentalhours();
			int cou_practicehour = ceList.get(i).getCou_practicehour();
			String grade = ceList.get(i).getGrade();
			String cla_name = ceList.get(i).getCla_name();
			String cla_number = ceList.get(i).getCla_number();
			String t_id = ceList.get(i).getT_id();
			String t_name = ceList.get(i).getT_name();
			String Experimentalenvironment = ceList.get(i)
					.getExperimentalenvironment();

			// �����������ͱ���
			Vector v = new Vector();
			// ��ȡ��������ӵ�������
			v.add(ce_id);
			v.add(cou_id);
			v.add(cou_name);
			v.add(cou_theoryhour);
			v.add(cou_experimentalhours);
			v.add(cou_practicehour);
			v.add(grade);
			v.add(cla_name);
			v.add(cla_number);
			v.add(t_id);
			v.add(t_name);
			v.add(Experimentalenvironment);
			// ��������ӵ������
			dtm.addRow(v);
		}
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
jScrollPane1 = new javax.swing.JScrollPane();
jTable1 = new javax.swing.JTable();
jButton1 = new javax.swing.JButton();
jButton2 = new javax.swing.JButton();

setClosable(true);
setIconifiable(true);
setTitle("\u5b9e\u9a8c\u73af\u5883\u7ba1\u7406");

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

jTable1.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"���", "�γ̱��", "�γ�����", "����ѧʱ", "ʵ��ѧʱ", "ʵ��ѧʱ", "�꼶", "���ΰ༶", "�༶����", "��ʦ���", "�ڿν�ʦ", "ʵ�黷��"
	}
) {
	boolean[] canEdit = new boolean [] {
		true, false, false, false, false, false, false, false, false, false, false, false
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

jButton1.setText("\u5bfc\u51fa");
jButton1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton1ActionPerformed(evt);
}
});

jButton2.setText("\u5bfc\u5165");

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1225, Short.MAX_VALUE)
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
.addGap(48, 48, 48)
.addComponent(jButton1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jButton2)))
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
.addComponent(jButton1)
.addComponent(jButton2))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
.addContainerGap())
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		List<ExportExperimentalenvironment> export = new ArrayList<ExportExperimentalenvironment>();
		for (int i = 0; i < jTable1.getRowCount(); i++) {
			// ȡ����Ӧ�ֶ���Ϣ
			int ce_id = (Integer) jTable1.getValueAt(i, 0);
			String cou_id = (String) jTable1.getValueAt(i, 1);
			String cou_name = (String) jTable1.getValueAt(i, 2);
			int cou_theoryhour = (Integer) jTable1.getValueAt(i, 3);
			int cou_experimentalhours = (Integer) jTable1.getValueAt(i, 4);
			int cou_practicehour = (Integer) jTable1.getValueAt(i, 5);
			String grade = (String) jTable1.getValueAt(i, 6);
			String cla_name = (String) jTable1.getValueAt(i, 7);
			String cla_number = (String) jTable1.getValueAt(i, 8);
			String t_id = (String) jTable1.getValueAt(i, 9);
			String t_name = (String) jTable1.getValueAt(i, 10);
			String Experimentalenvironment = (String) jTable1.getValueAt(i, 11);
			//����ʵ�黷�����������
			ExportExperimentalenvironment ee = new ExportExperimentalenvironment(
					ce_id, cou_id, cou_name, cou_theoryhour,
					cou_experimentalhours, cou_practicehour, grade, cla_name,
					cla_number, t_id, t_name, Experimentalenvironment);
			//��ӵ��б���
			export.add(ee);
		}
		String[] header={"���", "�γ̱��", "�γ�����", "����ѧʱ", "ʵ��ѧʱ", "ʵ��ѧʱ", "�꼶", "���ΰ༶", "�༶����", "��ʦ���", "�ڿν�ʦ", "ʵ�黷��"};
		List<String> aList =PrintObjectExcel.printExcel(export, "C:/trouble.xls",header);
		int r=aList.size();
		if(r>0)
			JOptionPane.showMessageDialog(this,aList.get(0));
	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// ��ȡ���������
		int clicked = evt.getClickCount();
		// ���������2���˳�����ִ��(���˫��)
		if (clicked != 2)
			return;
		// ���ʵ�黷�������ѡ�е�������Ϣ
		int row = jTable1.getSelectedRow();
		// ���û��ѡ�������˳�����ִ��
		if (row == -1)
			return;
		int ce_id = (Integer) jTable1.getValueAt(row, 0);
		String cou_id = (String) jTable1.getValueAt(row, 1);
		String grade = (String) jTable1.getValueAt(row, 6);
		String experimentalenvironment = (String) jTable1.getValueAt(row, 11);

		Courseexperimentalenvironment ce = new Courseexperimentalenvironment(
				ce_id, cou_id, grade, experimentalenvironment);

		// ��ʼ���γ�ʵ�黷���޸Ĵ���
		CZY_jd_updatecourseexperimentalenvironment uce = new CZY_jd_updatecourseexperimentalenvironment(
				null, true, ce);
		// ������ʾ
		uce.setVisible(true);
		// ˢ�½�ѧ������
		filltableExperimentalenvironment();
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
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	// End of variables declaration//GEN-END:variables

}