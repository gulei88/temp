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
	 * �������
	 * 
	 * @author czy
	 * @param vt
	 *            ����¼�û�����ͼ��Ϣ
	 */
	public CZY_jif_teachingtask(View_teacher vt) {
		// ��ʼ������
		initComponents();
		System.out.println(vt);
		// �����ݵĲ�������¼�û�����ͼ��Ϣ�����ݸ���Ķ�Ӧ�����ֶ�
		this.vt = vt;
		// ���ú�����ʼ��ѧԺ�����б�
		fillcomboboxdepartment();
		// ������ľ�̬������ʼ��ѧ�������б�
		Fillcombobox.fillschoolyear(jComboBox4);
		setColumnWidth();
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

	/**
	 * ��ʼ����ѧ������
	 * 
	 * @author czy
	 */
	private void fillteachingtasktable() {
		// ȡ�ñ���model��Ϣ
		DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
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
		// ȡ���꼶�����б�ѡ������
		String grade = (String) jComboBox5.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (grade == null)
			return;
		// ���ɲ�ѯ�������ڲ�ѯָ��רҵ���꼶��ѧ�ڶ�Ӧ�Ľ�ѧ����
		String condition = " m_id='"
				+ m.getM_id()
				+ "' and tt_grade='"
				+ grade
				+ "' and sy_name='"
				+ sy.getSy_name()
				+ "' order by cou_id,cou_theoryhour DESC,cou_experimentalhours  DESC,cou_practicehour  DESC";
		// �������������Ľ�ѧ�ƻ���ͼ��Ϣ��ָ���Ŀγ���ͼ���͵������б���
		ArrayList<View_teachingtask> aList = Teachingtaskaccess
				.getView_teachingtask(condition);
		// �����γ���ͼ���͵������б�
		for (int i = 0; i < aList.size(); i++) {
			// ȡ����Ӧ�ֶ���Ϣ
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
			// �����������ͱ���
			Vector v = new Vector();
			// ��ȡ��������ӵ�������
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
			// ��������ӵ������
			dtm.addRow(v);
		}

	}

	/**
	 * ���γ̱��
	 * 
	 * @author czy
	 */
	private void fillcoursetable() {
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
		// ȡ���꼶�����б�ѡ������
		String grade = (String) jComboBox5.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (grade == null)
			return;
		// ���ɲ�ѯ�������ڲ�ѯָ��רҵ���꼶��Ӧ�Ľ�ѧ�ƻ����
		String condition = " m_id='" + m.getM_id() + "' and tg_grade='" + grade
				+ "'";
		// ������������Ϊ�������ݸ����������ؽ�ѧ�ƻ���Ϣ���͵������б�
		ArrayList<Teachingplangrade> al = Teachingplangradeaccess
				.getTeachingplangradetwo(condition);
		// ��������б��д�������
		if (al.size() > 0) {
			// ��ȡ��ѧ�ƻ����
			String tp_id = al.get(0).getTp_id();
			// ����ѡ����ѧ�ں��꼶ת������Ӧ������ѧ��
			int semester = Tools.Semesterconversion(sy.getSy_name(), grade);
			// ���ݽ�ѧ�ƻ���ź�ѧ�����ɲ�ѯ����
			condition = " tp_id='"
					+ tp_id
					+ "' and cou_semester="
					+ semester
					+ " and cou_id not in(SELECT cou_id FROM courseschedule.teachingtask where m_id='"
					+ m.getM_id() + "' and tt_grade='" + grade + "')";
			// �������������Ŀγ���Ϣ��ָ���Ŀγ���ͼ���͵������б���
			ArrayList<View_course> al1 = Courseaccess.getView_course(condition);
			// �����γ���ͼ���͵������б�
			for (int i = 0; i < al1.size(); i++) {
				// ȡ����Ӧ�ֶ���Ϣ
				String cou_id = al1.get(i).getCou_id();
				String cou_category = al1.get(i).getCou_category();
				String cou_name = al1.get(i).getCou_name();
				int cou_theoryhour = al1.get(i).getCou_theoryhour();
				int cou_experimentalhours = al1.get(i)
						.getCou_experimentalhours();
				int cou_practicehour = al1.get(i).getCou_practicehour();
				int cou_type = al1.get(i).getCou_type();
				// �����������ͱ���
				Vector v = new Vector();
				// ��ȡ��������ӵ�������
				v.add(tp_id);
				v.add(cou_id);
				v.add(cou_category);
				v.add(cou_name);
				v.add(cou_theoryhour);
				v.add(cou_experimentalhours);
				v.add(cou_practicehour);
				if (cou_type == 1)
					v.add("����");
				else
					v.add("ѡ��");
				// ��������ӵ������
				dtm.addRow(v);
			}
		}
	}

	private void setColumnWidth() {
		TableColumn Column;
		// ���ÿγ̱����п��
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
		// ���ý�ѧ��������п��
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
		"���", "�γ̱��", "�γ����", "�γ�����", "����ѧʱ", "ʵ��ѧʱ", "ʵ��ѧʱ", "��ʦ����", "�ڿΰ༶", "�༶����", "��ý�����", "ʵ���Ƿ��ſ�"
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
		"�ƻ����", "�γ̱��", "���", "�γ�����", "����ѧʱ", "ʵ��ѧʱ", "ʵ��ѧʱ", "�γ�����"
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
		// ��ȡ��ѧ��������ѡ�е���
		int row[] = jTable2.getSelectedRows();
		// ���δѡ���У�����ʾ��Ϣ���˳���ǰ����
		if (row.length == 0) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ����ʵ���Ľ�ѧ����", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int tt_id;
		Connection con = null;
		try {
			// ȡ�����ݿ������
			con = Databaseconnection.getconnection();
			// �������ݿ������Զ��ύΪ��
			con.setAutoCommit(false);
			// ����ѡ�е���
			for (int i = 0; i < row.length; i++) {
				// ��ÿγ̵�ʵ��ѧʱ
				int practicehour = (Integer) jTable2.getValueAt(row[i], 6);
				// ��ÿγ̵�ʵ��ѧʱ
				int cou_experimentalhours = (Integer) jTable2.getValueAt(
						row[i], 5);
				// ��ÿγ̵�����ѧʱ
				int cou_theoryhour = (Integer) jTable2.getValueAt(row[i], 4);
				// ���ʵ��ѧʱΪ0���������һ����ѧ����
				if ((cou_theoryhour == 0 && cou_experimentalhours == 0)
						|| practicehour == 0)
					continue;
				// ȡ�ý�ѧ������
				tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// ����˿γ��Ѿ��ſΣ����ܽ��б�����
				if (isSchedulingCourse(tt_id)) {
					String msg = "�γ�������Ϊ" + tt_id + "�Ŀγ��Ѿ��������ſΣ�������ɱ�������";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ���ݽ�ѧ���������ò�ѯ����
				String condition = "tt_id=" + tt_id;
				// ��ѯTeachingtask���ж�Ӧ��ŵĽ�ѧ������Ϣ
				Teachingtask tt = Teachingtaskaccess.getTeachingtask(condition)
						.get(0);
				int r1 = Teachingtaskaccess.updatepracticehour(con, tt_id, 0);
				// ����������ѧʱ��Ϊ0
				tt.setCou_theoryhour(0);
				// ������ʵ��ѧʱ��Ϊ0
				tt.setCou_experimentalhours(0);
				// ��Teachingtask���в����޸ĺ�Ľ�ѧ���񣬲����������ɵĽ�ѧ������
				int id = Teachingtaskaccess.insert(con, tt);
				// ����޸Ļ���벻�ɹ�������ú������лع�
				if (r1 < 1 || id < 0) {
					String msg = "��ѧ����ʵ������ʧ�ܣ�����ϵϵͳ����Ա��";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ���ݽ�ѧ������ȡ�ñ���ѧ�����Ӧ�İ༶
				ArrayList<Courseclass> al = Courseclassaccess
						.getCourseclass(tt_id);
				for (int j = 0; j < al.size(); j++) {
					// �����²���Ľ�ѧ������Ӱ༶
					int r = Courseclassaccess.insert(con, id, al.get(j)
							.getCla_id());
					// �����������С��1�����ݿ�ع����˳�
					if (r < 1) {
						String msg = "��ѧ�����Ӧ�༶���ݲ���ʧ�ܣ�����ϵϵͳ����Ա��";
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
		// ��ȡ��ѧ��������ѡ�е���
		int row[] = jTable2.getSelectedRows();
		// ���δѡ���У�����ʾ��Ϣ���˳���ǰ����
		if (row.length == 0) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ����ʵ��Ľ�ѧ����", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int tt_id;
		Connection con = null;
		try {
			// ȡ�����ݿ������
			con = Databaseconnection.getconnection();
			// �������ݿ������Զ��ύΪ��
			con.setAutoCommit(false);
			// ����ѡ�е���
			for (int i = 0; i < row.length; i++) {
				// ��ÿγ̵�ʵ��ѧʱ
				int cou_experimentalhours = (Integer) jTable2.getValueAt(
						row[i], 5);
				// ��ÿγ̵�����ѧʱ
				int cou_theoryhour = (Integer) jTable2.getValueAt(row[i], 4);
				// �������ѧʱΪ0����ʵ��ѧʱΪ0���������һ��ʵ������
				if (cou_theoryhour == 0 || cou_experimentalhours == 0)
					continue;
				// ȡ�ý�ѧ������
				tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// ����˿γ��Ѿ��ſΣ����ܽ��б�����
				if (isSchedulingCourse(tt_id)) {
					String msg = "�γ�������Ϊ" + tt_id + "�Ŀγ��Ѿ��������ſΣ�������ɱ�������";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ���ݽ�ѧ���������ò�ѯ����
				String condition = "tt_id=" + tt_id;
				// ��ѯTeachingtask���ж�Ӧ��ŵĽ�ѧ������Ϣ
				Teachingtask tt = Teachingtaskaccess.getTeachingtask(condition)
						.get(0);
				int r1 = Teachingtaskaccess.updateexperimentalhours(con, tt_id,
						0);
				int r2 = Teachingtaskaccess.updatepracticehour(con, tt_id, 0);
				// ����������ѧʱ��Ϊ0
				tt.setCou_theoryhour(0);
				// ��Teachingtask���в����޸ĺ�Ľ�ѧ���񣬲����������ɵĽ�ѧ������
				int id = Teachingtaskaccess.insert(con, tt);
				// ����޸Ļ���벻�ɹ�������ú������лع�
				if (r1 < 1 || r2 < 1 || id < 0) {
					String msg = "��ѧ����ʵ�����ʧ�ܣ�����ϵϵͳ����Ա��";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ���ݽ�ѧ������ȡ�ñ���ѧ�����Ӧ�İ༶
				ArrayList<Courseclass> al = Courseclassaccess
						.getCourseclass(tt_id);
				for (int j = 0; j < al.size(); j++) {
					// �����²���Ľ�ѧ������Ӱ༶
					int r = Courseclassaccess.insert(con, id, al.get(j)
							.getCla_id());
					// �����������С��1�����ݿ�ع����˳�
					if (r < 1) {
						String msg = "��ѧ�����Ӧ�༶���ݲ���ʧ�ܣ�����ϵϵͳ����Ա��";
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
		// ��ȡ��ѧ��������ѡ�е���
		int row[] = jTable2.getSelectedRows();
		// ���δѡ���У�����ʾ��Ϣ���˳���ǰ����
		if (row.length == 0) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�ϰ�Ľ�ѧ����", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// ���ѡ������С��2������ʾ��Ϣ���˳���ǰ����
		if (row.length < 2) {
			JOptionPane.showMessageDialog(this, "��ѡ���������ϵĺϰ��ѧ������", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		Connection con = null;
		try {
			con = Databaseconnection.getconnection();
			con.setAutoCommit(false);
			String condition;
			int r;
			// ȡ�ÿγ̱��
			String cou_id = (String) jTable2.getValueAt(row[0], 1);
			// ����˿γ��Ѿ��ſΣ����ܽ��б�����
			if (isSchedulingCourse((Integer) jTable2.getValueAt(row[0], 0))) {
				String msg = "�γ�������Ϊ"
						+ (Integer) jTable2.getValueAt(row[0], 0)
						+ "�Ŀγ��Ѿ��������ſΣ�������ɱ�������";
				Tools.connectionroolback(con, msg);
				return;
			}
			int th = (Integer) jTable2.getValueAt(row[0], 4);
			int eh = (Integer) jTable2.getValueAt(row[0], 5);
			int ph = (Integer) jTable2.getValueAt(row[0], 6);
			for (int i = 1; i < row.length; i++) {
				if (!((String) jTable2.getValueAt(row[i], 1)).equals(cou_id)) {
					String msg = "��ѡ��γ̱����ͬ�Ľ�ѧ������кϰ�";
					Tools.connectionroolback(con, msg);
					return;
				}
				int th1 = (Integer) jTable2.getValueAt(row[i], 4);
				int eh1 = (Integer) jTable2.getValueAt(row[i], 5);
				int ph1 = (Integer) jTable2.getValueAt(row[i], 6);
				if (th != th1 || eh != eh1 || ph != ph1) {
					String msg = "ʵ������ʵ������Ľ�ѧ�����ܽ��кϰ�";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ȡ�ý�ѧ������
				int tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// ����˿γ��Ѿ��ſΣ����ܽ��б�����
				if (isSchedulingCourse(tt_id)) {
					String msg = "�γ�������Ϊ" + tt_id + "�Ŀγ��Ѿ��������ſΣ�������ɱ�������";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ���ݽ�ѧ������ȡ�ñ���ѧ�����Ӧ�İ༶
				ArrayList<Courseclass> al = Courseclassaccess
						.getCourseclass(tt_id);
				// ���ݽ�ѧ�����źͶ�Ӧ�༶������ɾ������
				condition = "tt_id=" + tt_id;
				// ��ѧ�����Ӧ�༶����ɾ�����������ļ�¼
				r = Courseclassaccess.Delete(con, condition);
				// ���ɾ������С��1�����ݿ�ع����˳�
				if (r < 1) {
					String msg = "��ѧ�����Ӧ�༶����ɾ��ʧ�ܣ�����ϵϵͳ����Ա��";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ���������б�
				for (int j = 0; j < al.size(); j++) {
					// ��ѧ�����Ӧ�༶���в���ϲ���İ༶��Ϣ
					r = Courseclassaccess.insert(con, (Integer) jTable2
							.getValueAt(row[0], 0), al.get(j).getCla_id());
					// �����������С��1�����ݿ�ع����˳�
					if (r < 1) {
						String msg = "��ѧ�����Ӧ�༶���ݲ���ʧ�ܣ�����ϵϵͳ����Ա��";
						Tools.connectionroolback(con, msg);
						return;
					}

					// ���ɽ�ѧ����ɾ������
					// condition = "tt_id=" + tt_id;
					// ɾ���Ѿ��ϲ��Ľ�ѧ����
				}
				r = Teachingtaskaccess.delete(con, tt_id);
				if (r < 1) {
					String msg = "��ѧ�����Ӧ�༶���ݲ���ʧ�ܣ�����ϵϵͳ����Ա��";
					Tools.connectionroolback(con, msg);
					return;
				}
			}
			// �����ύ
			con.commit();
			// ����Ϣˢ�½�ѧ������
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
		// ��ȡ��ѧ��������ѡ�е���
		int row[] = jTable2.getSelectedRows();
		// ���δѡ���У�����ʾ��Ϣ���˳���ǰ����
		if (row.length == 0) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�ְ�Ľ�ѧ����", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		int tt_id;

		Connection con = null;
		try {
			// ȡ�����ݿ������
			con = Databaseconnection.getconnection();
			// �������ݿ������Զ��ύΪ��
			con.setAutoCommit(false);
			// ����ѡ�е���
			for (int i = 0; i < row.length; i++) {
				// ȡ�ý�ѧ������
				tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// ����˿γ��Ѿ��ſΣ����ܽ��б�����
				if (isSchedulingCourse(tt_id)) {
					String msg = "�γ�������Ϊ" + tt_id + "�Ŀγ��Ѿ��������ſΣ�������ɱ�������";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ���ݽ�ѧ������ȡ�ñ���ѧ�����Ӧ�İ༶
				ArrayList<Courseclass> al = Courseclassaccess
						.getCourseclass(tt_id);
				// ���ֻ��Ӧһ���༶������ʾ���ְܷ࣬���˳���ǰ����
				if (al.size() == 1) {
					JOptionPane.showMessageDialog(this, "���Ϊ" + tt_id
							+ "�Ľ�ѧ����༶��Ϊ1�����ְܷ࣬������ѡ��", "��ʾ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// ���ݽ�ѧ���������ò�ѯ����
				String condition = "tt_id=" + tt_id;
				// ��ѯTeachingtask���ж�Ӧ��ŵĽ�ѧ������Ϣ
				Teachingtask tt = Teachingtaskaccess.getTeachingtask(condition)
						.get(0);
				// ����ɾ����ѧ������༶��Ӧ�����ݵ�����
				condition = "tt_id='" + tt_id + "' and cla_id!='"
						+ al.get(0).getCla_id() + "'";
				// ɾ����ѧ������༶��Ӧ����ָ������
				Courseclassaccess.Delete(con, condition);
				for (int j = 1; j < al.size(); j++) {
					// ��Teachingtask�����ٴβ��뱾��ѧ���񣬲����������ɵĽ�ѧ������
					int id = Teachingtaskaccess.insert(con, tt);
					// ������벻�ɹ�������ú������лع�
					if (id < 0) {
						String msg = "��ѧ�������ݲ���ʧ�ܣ�����ϵϵͳ����Ա��";
						Tools.connectionroolback(con, msg);
						return;
					}
					// �����²���Ľ�ѧ������Ӱ༶
					int r = Courseclassaccess.insert(con, id, al.get(j)
							.getCla_id());
					// �����������С��1�����ݿ�ع����˳�
					if (r < 1) {
						String msg = "��ѧ�����Ӧ�༶���ݲ���ʧ�ܣ�����ϵϵͳ����Ա��";
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
		// ��ȡ���������
		int clicked = evt.getClickCount();
		// ���������2���˳�����ִ��(���˫��)
		if (clicked != 2)
			return;
		// ��ý�ѧ��������ѡ�е�������Ϣ
		int rows[] = jTable2.getSelectedRows();
		// ���û��ѡ�������˳�����ִ��
		if (rows.length > 1)
			return;
		// ��ȡѡ�еĵ�һ�еĽ�ѧ��������Ϣ
		int tt_id = (Integer) jTable2.getValueAt(rows[0], 0);
		// ���ɽ�ѧ�������ݱ��ѯ����
		String condition = "tt_id=" + tt_id;
		// ����˿γ��Ѿ��ſΣ����ܽ��б�����
		if (isSchedulingCourse(tt_id)) {
			JOptionPane.showMessageDialog(this, "�γ�������Ϊ" + tt_id
					+ "�Ŀγ��Ѿ��������ſΣ�������ɱ�������");
			return;
		}
		// �ӽ�ѧ�������ݱ��в�ѯ����������Ϣ���ص������б���
		ArrayList<View_teachingtask> al = Teachingtaskaccess
				.getView_teachingtask(condition);

		// ��ʼ����ѧ�����޸Ĵ���
		CZY_jd_teachingtaskmodify ttm = new CZY_jd_teachingtaskmodify(null,
				true, al.get(0), (Department) jComboBox1.getSelectedItem(),
				(Office) jComboBox2.getSelectedItem());
		// ������ʾ
		ttm.setVisible(true);
		// ˢ�½�ѧ������
		fillteachingtasktable();

	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// ��ý�ѧ��������ѡ�е�������Ϣ
		int row[] = jTable2.getSelectedRows();
		// ��ȡ���������ѧ��������û��ѡ�е�������Ϣ
		int unselected[] = Tools.getunselectedrows(row, jTable2.getRowCount());
		int tt_id;
		Connection con = null;
		int r = 0;
		try {
			// ��ȡ���ݿ������
			con = Databaseconnection.getconnection();
			// �������������Զ��ύΪ��
			con.setAutoCommit(false);
			// ����ѡ�е���
			for (int i = 0; i < row.length; i++) {
				// ��ȡ��ѧ������
				tt_id = (Integer) jTable2.getValueAt(row[i], 0);
				// ����˿γ��Ѿ��ſΣ����ܽ��б�����
				if (isSchedulingCourse(tt_id)) {
					String msg = "�γ�������Ϊ" + tt_id + "�Ŀγ��Ѿ��������ſΣ�������ɱ�������";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ��ȡ�γ̱��
				String cou_id = (String) jTable2.getValueAt(row[i], 1);
				int j;
				// ����δѡ����
				for (j = 0; j < unselected.length; j++) {
					// �����δѡ�����з��ֿγ̱���ظ���Ϣ���˳���ǰѭ��
					if (cou_id.equals((String) jTable2.getValueAt(
							unselected[j], 1))) {
						break;
					}
				}
				// �������δѡ�����д���ѡ�еĿγ���Ϣ������ʾ��ǰ�γ������ܵ���ɾ�������ݿ�ع����˳�
				if (j < unselected.length) {
					String msg = "�γ�����ɾ��Ӧɾ�����ſγ����а༶��������鿴�γ̱��Ϊ" + cou_id
							+ "�Ŀγ�����";
					Tools.connectionroolback(con, msg);
					return;
				}
				// ��ѧ�������ɾ��ָ���Ľ�ѧ����
				r = Teachingtaskaccess.delete(con, tt_id);
				// ���ɾ������С��1�����ݿ�ع����˳�
				if (r < 1) {
					String msg = "��ѧ��������ɾ��ʧ�ܣ�����ϵϵͳ����Ա��";
					Tools.connectionroolback(con, msg);
					return;
				}
				String grade = (String) jComboBox5.getSelectedItem();
				r = Courseexperimentalenvironmentaccess.deletebycouidgrade(con,
						cou_id, grade);
				if (r == -1) {
					String msg = "�����ݿ������쳣����ѧ��������ɾ��ʧ�ܣ�����ϵϵͳ����Ա��";
					Tools.connectionroolback(con, msg);
					return;
				}
			}
			// �����ύ
			con.commit();
			// ˢ�½�ѧ������
			fillteachingtasktable();
			// ˢ�¿γ̱��
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
		// ��ȡ�γ̱����ѡ�е���
		int[] rows = jTable1.getSelectedRows();
		// ����ѡ�е���
		for (int i = 0; i < rows.length; i++) {
			// ��ȡѡ�����еĿγ̱��
			String cou_id = (String) jTable1.getValueAt(rows[i], 1);
			int cou_theoryhour = (Integer) jTable1.getValueAt(rows[i], 4);
			int cou_experimentalhours = (Integer) jTable1
					.getValueAt(rows[i], 5);
			int cou_practicehour = (Integer) jTable1.getValueAt(rows[i], 6);
			// ��ȡ�����б��е�ѧ�ڱ��
			int sy_id = ((Schoolyear) jComboBox4.getSelectedItem()).getSy_id();
			// �Ƿ��ý���ֶγ�ʼ��Ϊ��
			String multimedia = "��";
			// ��ȡ�����б��е�רҵ���
			String m_id = ((Major) jComboBox3.getSelectedItem()).getM_id();
			// ��ȡ�����б��е��꼶
			String tt_grade = (String) jComboBox5.getSelectedItem();
			// ���ݻ�ȡ����Ϣ���ɽ�ѧ�������
			Teachingtask tt = new Teachingtask(cou_id, cou_theoryhour,
					cou_experimentalhours, cou_practicehour, "-1", sy_id,
					multimedia, m_id, tt_grade, "", "", 0);
			Connection con = null;
			try {
				// ȡ�����ݿ������
				con = Databaseconnection.getconnection();
				con.setAutoCommit(false);
				// ����ѧ���������뵽���ݿ��ѧ�������
				int tt_id = Teachingtaskaccess.insert(con, tt);
				// �������ɹ�
				if (tt_id >= 0) {
					// ����רҵ��š��꼶�������ݿ��ѯ����
					String condition = "m_id='" + m_id + "' and cla_grade='"
							+ tt_grade + "'";
					// �ڰ༶��Ϣ���ݿ��в�ѯ����������¼��View_classinformation���������б���
					ArrayList<View_classinformation> al = Classinformationaccess
							.getView_classinformation(condition);
					// ������ȡ���������б�
					for (int j = 0; j < al.size(); j++) {
						// ������Ϣ�����ݿ��ѧ�����Ӧ�༶����
						int r = Courseclassaccess.insert(con, tt_id, al.get(j)
								.getCla_id());
						// �����������С��1�����ݿ�ع����˳�
						if (r < 1) {
							String msg = "��ѧ�����Ӧ�༶���ݲ���ʧ�ܣ�����ϵϵͳ����Ա��";
							Tools.connectionroolback(con, msg);
							return;
						}
					}
				} else {// �������ʧ�ܣ�����÷������лع�
					String msg = "��ѧ�������ݲ���ʧ�ܣ�����ϵϵͳ����Ա��";
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

	private boolean isSchedulingCourse(int tt_id) {
		// �����������ڲ������ݿ��иýڿ����ʦ�����ҡ�ѧ��ʱ���Ƿ��ͻ
		String condition = "tt_id=" + tt_id;
		// ����������ѯ���ݿ�
		ArrayList<View_curriculum> aList = Curriculumaccess
				.getCurriculums(condition);
		// ��������ݣ�������г�ͻ����ʱ��β����ſ�
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