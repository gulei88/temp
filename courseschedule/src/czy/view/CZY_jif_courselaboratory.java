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
		// ��ʼ������
		initComponents();
		// �����ݵĲ�������¼�û�����ͼ��Ϣ�����ݸ���Ķ�Ӧ�����ֶ�
		this.vt = vt;
//		System.out.println(vt);
		// ���ú�����ʼ��ѧԺ�����б�
		fillcomboboxdepartment();
		// ������ľ�̬������ʼ��ѧ�������б�
		Fillcombobox.fillschoolyear(jComboBox4);
		// ����jtable����е�ѡ
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ���ر����
		Tools.hideColumn(jTable3, 0);
		// ����ʵ���ұ����
		Tools.hideColumn(jTable3, 1);
		// ����ʵ����ұ����
		Tools.hideColumn(jTable3, 3);
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

			// �����������ͱ���
			Vector v = new Vector();
			// ��ȡ��������ӵ�������
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
			// ��������ӵ������
			dtm.addRow(v);
		}
	}

	private void Filltablecourselaboratory() {
		// ȡ�ñ���model��Ϣ
		DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
		// ��ձ������ʾ������
		dtm.setRowCount(0);
		int row = jTable1.getSelectedRow();
		if (row == -1)
			return;
		int ce_id = (Integer) jTable1.getValueAt(row, 0);

		ArrayList<View_courselaboratory> cllist = Courselaboratoryaccess
				.getcourselaboratorybyceid(ce_id);
		for (int i = 0; i < cllist.size(); i++) {
			// ȡ����Ӧ�ֶ���Ϣ

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

			// �����������ͱ���
			Vector v = new Vector();
			// ��ȡ��������ӵ�������
			v.add(cl_id);
			v.add(cr_id);
			v.add(cr_name);
			v.add(sl_id);
			v.add(sl_name);
			// ���ʵ�������λ������0,��������������Ϊʵ�������λ��
			if (sumsl_seating > 0)
				v.add(sumsl_seating);
			else
				// ����������������Ϊʵ������λ��
				v.add(cr_seating);
			// ��������ӵ������
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
		"���", "�γ̱��", "�γ����", "�γ�����", "����ѧʱ", "ʵ��ѧʱ", "ʵ��ѧʱ", "�꼶", "���ΰ༶", "�༶������", "���ϰ���", "��ʦ���", "�ڿν�ʦ", "ʵ�黷��"
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
		"���", "ʵ���ұ��", "ʵ��������", "ʵ����ұ��", "ʵ���������", "����������"
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
		// ��ȡ���������
		int clicked = evt.getClickCount();
		// ���������2���˳�����ִ��(���˫��)
		if (clicked == 1) {
			Filltablecourselaboratory();
			return;
		}
		if (clicked == 2) {
			// ���ʵ�黷�������ѡ�е�������Ϣ
			int row = jTable1.getSelectedRow();
			// ���û��ѡ�������˳�����ִ��
			if (row == -1)
				return;
			int ce_id = (Integer) jTable1.getValueAt(row, 0);
			// ��ʼ���γ�ʵ�����޸Ĵ���
			CZY__jd_updatecourselaboratory ucl = new CZY__jd_updatecourselaboratory(
					null, true, ce_id);
			// ������ʾ
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