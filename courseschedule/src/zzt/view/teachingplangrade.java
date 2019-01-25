/*
 * teachingplangrade.java
 *
 * Created on __DATE__, __TIME__
 */

package zzt.view;

import java.io.Console;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import zxx.dao.PrintObjectExcel;

import czy.model.Tools;
import czy.view.CZY_jd_teachingtaskmodify;

import global.dao.Classinformationaccess;
import global.dao.Classinformationtwoaccess;
import global.dao.Classroomaccess;
import global.dao.Courseclassaccess;
import global.dao.Databaseconnection;
import global.dao.Departmentaccess;
import global.dao.Fileselection;
import global.dao.Teachingplanaccess;
import global.dao.Teachingplangradeaccess;
import global.dao.Teachingtaskaccess;
import global.model.Classinformation;
import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.Schoolyear;
import global.model.Teacher;
import global.model.Teachingplan;
import global.model.Teachingplangrade;
import global.model.Teachingtask;
import global.model.View_classinformation;
import global.model.View_classroom;
import global.model.View_teacher;
import global.model.View_teachingplan;
import global.model.View_teachingtask;

/**
 *
 * @author  __USER__
 */
public class teachingplangrade extends javax.swing.JInternalFrame {
	private View_teacher vt;
	private String m_id;
	private String d_id;
	private String o_id;
	private String tp_id;
	ArrayList<String> tgArrayList = null;
	/** Creates new form teachingplangrade */
	public teachingplangrade() {
		initComponents();
	}

	/**
	 * �������
	 * 
	 * @author zzt
	 * @param vt
	 *            ����¼�û�����ͼ��Ϣ
	 */
	public teachingplangrade(View_teacher vt) {
		// ��ʼ������
		initComponents();
		// �����ݵĲ�������¼�û�����ͼ��Ϣ�����ݸ���Ķ�Ӧ�����ֶ�
		this.vt = vt;
		// ���ú�����ʼ��ѧԺ�����б�
		Fillcombobox.filldepartmentadd(d_id, jComboBox1);
		// ������ľ�̬������ʼ�����������б�
		//		Fillcombobox.filloffice(o_id, jComboBox2);
		// ������ľ�̬������ʼ��ѧ�������б�
		//		Fillcombobox.fillmajor(m_id, jComboBox3);
		//		Fillcombobox.fillteachingplan(tp_id, jComboBox4);
		//		jComboBox1.addItem(new Department("-1",""));
		jComboBox1.setSelectedItem(null);
		jComboBox2.setSelectedItem(null);
		jComboBox3.setSelectedItem(null);
		jComboBox4.setSelectedItem(null);
	}

	/**
	 * ��ʼ��ѧԺ�����б�
	 * 
	 * @author zzt
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

	private void teachingplan() {
		//���ȸ�������Ϊ��ֵ
		String d_id = null;
		//����DefaultTableModel �õ�jtble1����ϵ�ģ��
		DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
		//���ñ���������0��ʼ����
		dtm.setRowCount(0);
		// ȡ�ò��������б�ѡ������
		Department dm = (Department) jComboBox1.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (dm == null)
			return;
		// ȡ�ÿ��������б�ѡ������
		Office of = (Office) jComboBox2.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (of == null)
			return;
		Major mj = (Major) jComboBox3.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (mj == null)
			return;
		Teachingplan tp = (Teachingplan) jComboBox4.getSelectedItem();
		if (tp == null)
			return;
		//��ʼ��ArrayList�б�ͨ�����ű��������ѯdepartment�����ű�
		ArrayList<Teachingplangrade> aList = Teachingplangradeaccess
				.getTeachingplangrade(tp.getTp_id());
		System.out.println(aList);
		// �������ŵ������б�
		for (int i = 0; i < aList.size(); i++) {
			// ȡ����Ӧ�ֶ���Ϣ
			String tp_id = ((Teachingplan) jComboBox4.getSelectedItem())
					.getTp_id();
			String tp_name = ((Teachingplan) jComboBox4.getSelectedItem())
					.getTp_name();
			String m_name = ((Major) jComboBox3.getSelectedItem()).getM_name();
			
			String tg_grade = aList.get(i).getTg_grade();
			// �����������ͱ���
			Vector v = new Vector();
			// ��ȡ��������ӵ�������
			v.add(tp_id);
			v.add(tp_name);
			v.add(m_name);
			v.add(tg_grade);
			// ��������ӵ������
			dtm.addRow(v);
		}
	}

	private void teachinggrade() {
		//���ȸ�������Ϊ��ֵ
		String d_id = null;
		//����DefaultTableModel �õ�jtble1����ϵ�ģ��
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		//���ñ���������0��ʼ����
		dtm.setRowCount(0);
		// ȡ�ò��������б�ѡ������
		Department dm = (Department) jComboBox1.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (dm == null)
			return;
		// ȡ�ÿ��������б�ѡ������
		Office of = (Office) jComboBox2.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (of == null)
			return;
		Major mj = (Major) jComboBox3.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (mj == null)
			return;
		Teachingplan tp = (Teachingplan) jComboBox4.getSelectedItem();
		if (tp == null)
			return;
		//��ʼ��ArrayList�б�ͨ�����ű��������ѯdepartment�����ű�
		ArrayList<View_classinformation> aList = Classinformationtwoaccess
				.getView_classinformationgrade(mj.getM_id());
		// �������ŵ������б�
		ArrayList<Teachingplangrade> gradelist = Teachingplangradeaccess
				.getTeachingplangradetwo(mj.getM_id());

		String cla_grade = "";
		String tg_grade = "";
		aList.removeAll(gradelist);
		//		System.out.println(aList);
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < aList.size(); i++) {
			cla_grade = aList.get(i).getCla_grade();
			list1.add(cla_grade);
		}
		for (int i = 0; i < gradelist.size(); i++) {
			tg_grade = gradelist.get(i).getTg_grade();
			list2.add(tg_grade);
		}
		String cc = "";
		list1.removeAll(list2);
		//		System.out.println(list1);
		for (String item : list1) {
			//			System.out.println(item);
			Vector v = new Vector();
			//			// ��ȡ��������ӵ�������
			v.add(item);
			// // ��������ӵ������
			dtm.addRow(v);
			//			System.out.println(dtm);
			//			System.out.println(v);
		}
		//		for (int i = 0; i < list1.size(); i++) {
		//			cc = aList.get(i).getCla_grade();
		//			// �����������ͱ���
		//			System.out.println(aList);
		//			Vector v = new Vector();
		//			//			// ��ȡ��������ӵ�������
		//			v.add(cc);
		//			// // ��������ӵ������
		//			dtm.addRow(v);
		//			System.out.println(dtm);
		////			System.out.println(v);
		//		}
		//		
		//		System.out.println(list1);
		//
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

jLabel1 = new javax.swing.JLabel();
jLabel2 = new javax.swing.JLabel();
jLabel3 = new javax.swing.JLabel();
jLabel4 = new javax.swing.JLabel();
jLabel5 = new javax.swing.JLabel();
jComboBox1 = new javax.swing.JComboBox();
jComboBox2 = new javax.swing.JComboBox();
jComboBox3 = new javax.swing.JComboBox();
jComboBox4 = new javax.swing.JComboBox();
jScrollPane1 = new javax.swing.JScrollPane();
jTable1 = new javax.swing.JTable();
jPanel1 = new javax.swing.JPanel();
jButton1 = new javax.swing.JButton();
jButton2 = new javax.swing.JButton();
jScrollPane2 = new javax.swing.JScrollPane();
jTable2 = new javax.swing.JTable();
jButton3 = new javax.swing.JButton();

setClosable(true);
setIconifiable(true);
setMaximizable(true);
setTitle("\u6559\u5b66\u8ba1\u5212");

jLabel1.setText("\u5b66\u9662\uff1a");

jLabel3.setText("\u7cfb\u90e8\uff1a");

jLabel4.setText("\u4e13\u4e1a\uff1a");

jLabel5.setText("\u6559\u5b66\u8ba1\u5212\uff1a");

jComboBox1.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox1ItemStateChanged(evt);
}
});
jComboBox1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jComboBox1ActionPerformed(evt);
}
});

jComboBox2.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox2ItemStateChanged(evt);
}
});

jComboBox3.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox3ItemStateChanged(evt);
}
});
jComboBox3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jComboBox3ActionPerformed(evt);
}
});

jComboBox4.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox4ItemStateChanged(evt);
}
});

jTable1.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"�꼶"
	}
) {
	boolean[] canEdit = new boolean [] {
		false
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

jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "\u64cd\u4f5c"));

jButton1.setText(">");
jButton1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton1ActionPerformed(evt);
}
});

jButton2.setText("<");
jButton2.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton2ActionPerformed(evt);
}
});

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
.addContainerGap())
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGap(24, 24, 24)
.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(36, 36, 36)
.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
.addContainerGap(44, Short.MAX_VALUE))
);

jTable2.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"�������", "��������", "רҵ����", "�꼶"
	}
) {
	boolean[] canEdit = new boolean [] {
		false, false, false, false
	};

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
public void mouseClicked(java.awt.event.MouseEvent evt) {
jTable2MouseClicked(evt);
}
});
jScrollPane2.setViewportView(jTable2);

jButton3.setText("\u5bfc\u51fa\u89c6\u56fe\u6570\u636e");
jButton3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton3ActionPerformed(evt);
}
});

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(230, 230, 230)
.addComponent(jLabel2))
.addGroup(layout.createSequentialGroup()
.addGap(33, 33, 33)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addComponent(jLabel1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(18, 18, 18)
.addComponent(jLabel3)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(28, 28, 28)
.addComponent(jLabel4)
.addGap(3, 3, 3)
.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(18, 18, 18)
.addComponent(jLabel5)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(layout.createSequentialGroup()
.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))))))
.addContainerGap(32, Short.MAX_VALUE))
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addGap(57, 57, 57)
.addComponent(jLabel2))
.addGroup(layout.createSequentialGroup()
.addGap(21, 21, 21)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel1)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel3)
.addComponent(jLabel4)
.addComponent(jLabel5)
.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
.addGap(35, 35, 35)
.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
.addContainerGap())
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// TODO add your handling code here:
				// TODO add your handling code here:
				//���õ����������ļ���·�������tabletwo������
				File Filename = Fileselection.exportselect();
				File filenametow = new File(Filename + ".xls");
				String filetable = filenametow.toString();
				String cr_id = null;
				Major mj = (Major) jComboBox3.getSelectedItem();
				Teachingplan tp = (Teachingplan) jComboBox4.getSelectedItem();
				// ���Ϊ�գ����˳�
				if (mj == null)
					return;
				ArrayList<View_classinformation> abList = Classinformationtwoaccess
						.getView_classinformationgrade(mj.getM_id());
				// �������ŵ������б�
				ArrayList<Teachingplangrade> gradelist = Teachingplangradeaccess
						.getTeachingplangradetwo(mj.getM_id());
				
				
				String cla_grade = "";
				String tg_grade = "";
				abList.removeAll(gradelist);
				//		System.out.println(aList);
				List<String> list1 = new ArrayList<String>();
				List<String> list2 = new ArrayList<String>();
				for (int i = 0; i < abList.size(); i++) {
					cla_grade = abList.get(i).getCla_grade();
					list1.add(cla_grade);
				}
				for (int i = 0; i < gradelist.size(); i++) {
					tg_grade = gradelist.get(i).getTg_grade();
					list2.add(tg_grade);
				}
				String cc = "";
				list1.removeAll(list2);
				//ʵ���������б��Ѳ�����Ϣ�����calist������
				list1.addAll(list2);
				teachingplan();
				List<Teachingplangrade> caList = gradelist;
				System.out.println(abList);
				//����excel��ͷ��Ϣ
				String[] header = { "���ұ��", "���ű��", "��ʦ����", "��������", "��������", "��ѧ¥���" };
				if (Filename == null) {
					return;
				}
				//��ô�õ�����֮����ļ���
				if (filenametow.exists()) {
					System.out.println(filenametow.exists());
					int overwriteSelect = JOptionPane.showConfirmDialog(this,
							"<html><font size=3>�ļ�" + filenametow.getName()
									+ "�Ѵ��ڣ��Ƿ񸲸�?</font><html>", "�Ƿ񸲸�?",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					System.out.println(overwriteSelect);
					if (overwriteSelect == JOptionPane.YES_OPTION) {

						List<String> aList = PrintObjectExcel.printExcel(caList,
								filetable, header);
						if (aList.size() == 0) {
							JOptionPane.showMessageDialog(this, "�����ɹ�", "��ʾ��Ϣ",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							String str = "";
							for (String item : aList) {
								str += item + "\n";
							}
							JOptionPane.showMessageDialog(this, str, "������Ϣ",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						return;
					}
				}
				List<String> aList = PrintObjectExcel.printExcel(caList, filetable,
						header);
				if (aList.size() == 0) {
					JOptionPane.showMessageDialog(this, "�����ɹ�", "��ʾ��Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String str = "";
					for (String item : aList) {
						str += item + "\n";
					}
					JOptionPane.showMessageDialog(this, str, "������Ϣ",
							JOptionPane.ERROR_MESSAGE);
				}
		
		
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		//��¼jtble1��嵱ǰ������
		int i = jTable2.getSelectedRow();
		jTable2.setRowSelectionAllowed(true);
		String zztString = "";
		String zzt = "";
		//���i==-1��˵��û��ѡ����
		if (i == -1) {
			//��ʾ��Ϣ����ѡ��Ҫ�޸ĵļ�¼
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ���ļ�¼", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else {
			//����һ���������row��ŵ�ǰѡ�е�������
			int[] row = jTable2.getSelectedRows();
			//����һ��count�����������
			int count = row.length;
			//��������
			for (int a = 0; a < count; a++) {
				//����һ����������Ų��ű�ŵĵ�row[a]�е�0��
				String grade = (String) jTable2.getValueAt(row[a], 3);
				//����һ�����ַ�������st
				String st = ",";
				//�����������ɾ���������Ҫ���sql���
				zzt = "(" + (zztString += "'" + grade + "'" + (st)) + ")";
				//���sql�������һ���ַ��ǡ�)����ɾ�������λ�ַ��ڼ���һ����)��
				if (zzt.endsWith(")")) {
					zzt = zzt.substring(0, zzt.length() - 2) + ")";
				}
			}
			//��ʾ��Ϣ,ȷ��Ҫɾ����ǰ��¼�,���ȷ������ɾ����ǰѡ�еļ�¼
			int r = JOptionPane.showConfirmDialog(this, "ȷ��Ҫɾ����ǰ��¼�", "ȷ����Ϣ",
					JOptionPane.OK_CANCEL_OPTION);
			if (r == 0) {
				Connection con = null;
				try {
					//�������ݿ�����
					con = Databaseconnection.getconnection();
					//�������ݿ����ӵ��Զ��ύΪ(false)
					con.setAutoCommit(false);
					//����һ�����ͱ���re����Ž����
					int re;
					//��re����¼ɾ�����ű�ŵķ����Ľ����
					re = Teachingplangradeaccess.Delete(con, zzt);
					//��������  > 0 ��ɾ���ɹ�
					if (re > 0) {
						JOptionPane.showMessageDialog(this, "ɾ����¼�ɹ���", "ȷ����Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
						//�������ݿ���ֶ��ύ
						con.commit();
						//ʵʱˢ��jtble1�������
					} else {
						JOptionPane.showMessageDialog(this,
								"ɾ��ʧ�ܣ�����ϵ����Ա������ز�����", "������Ϣ",
								JOptionPane.ERROR_MESSAGE);
					}
					teachinggrade();
					teachingplan();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//��������쳣������д���,����һ��str����,������ŷ���sql�������״ֵ̬
					String str = "23000";
					//�����ǰ���ʵ�״ֵ̬����str,����ʾ��Ӧ��Ϣ
					if (e.getSQLState().equals(str)) {
						JOptionPane.showMessageDialog(this,
								"���ڴ˱�������Լ��Ӱ��,��ʱ����ɾ����", "��ʾ��Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}

	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		int clicked = evt.getClickCount();
		if (clicked != 1)
			return;
		int rowss[] = jTable1.getSelectedRows();
		String cle_grade = "";
		for (int i = 0; i < rowss.length; i++) {
			cle_grade = (String) jTable1.getValueAt(rowss[i], 0);
		}

		System.out.println(cle_grade);

	}

	private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:

		// TODO add your handling code here:

		int clicked = evt.getClickCount();
		// ���������2���˳�����ִ��(���˫��)
		if (clicked != 1)
			return;
		// ��ý�ѧ��������ѡ�е�������Ϣ
		int rows[] = jTable2.getSelectedRows();
		//		int rowss[] = jTable1.getSelectedRows();
		// ���û��ѡ�������˳�����ִ��
		if (rows.length > 1)
			return;
		String tp_id = "";
		String cle_grade = "";
		tp_id = (String) jTable2.getValueAt(rows[0], 0);

		System.out.println(tp_id);

		// ��ȡѡ�еĵ�һ�еĽ�ѧ��������Ϣ

		// ���ɽ�ѧ�������ݱ��ѯ����
		//		String condition = "tp_id=" + tp_id;
		// ����˿γ��Ѿ��ſΣ����ܽ��б�����
		//		if (isSchedulingCourse(tp_id)) {
		//			JOptionPane.showMessageDialog(this, "�γ�������Ϊ" + tt_id
		//					+ "�Ŀγ��Ѿ��������ſΣ�������ɱ�������");
		//			return;
		//		}
		// ˢ�½�ѧ������
		//		teachingplan();

	}

	private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {
		// TODO add your handling code here:
		teachingplan();
	}

	private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {
		// TODO add your handling code here:
		Major mj = (Major) jComboBox3.getSelectedItem();
		if (mj == null)
			return;
		String m_id = mj.getM_id();
		Fillcombobox.fillteachingplan(m_id, jComboBox4);
		teachinggrade();
	}

	private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
		// TODO add your handling code here:
		// TODO add your handling code here:
		//�õ����������б������
		Office of = (Office) jComboBox2.getSelectedItem();
		//���Ϊ���򷵻�
		if (of == null)
			return;
		String o_id = of.getO_id();
		//���þ�̬����ͨ����ѧ�ƻ���ŵõ���ǰרҵ����
		Fillcombobox.fillmajor(o_id, jComboBox3);
		jComboBox4.setSelectedItem(null);

	}

	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		// TODO add your handling code here:
		// TODO add your handling code here:
		//�õ����������б������
		Department dm = (Department) jComboBox1.getSelectedItem();
		//���Ϊ���򷵻�
		if (dm == null)
			return;
		String d_id = dm.getD_id();
		//���þ�̬����ͨ�����ű�ŵõ���ǰרҵ����
		Fillcombobox.filloffice(d_id, jComboBox2);
		//����רҵ�����б�Ϊ��
		jComboBox3.setSelectedItem(null);
		jComboBox4.setSelectedItem(null);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// ��ȡ�γ̱����ѡ�е���
		int[] rows = jTable1.getSelectedRows();
		// ����ѡ�е���
		String tg_grade = "";
		String m_id = "";
		String tp_id = "";
		for (int i = 0; i < rows.length; i++) {
			tg_grade = (String) jTable1.getValueAt(rows[i], 0);
			m_id = ((Major) jComboBox3.getSelectedItem()).getM_id();
			tp_id = ((Teachingplan) jComboBox4.getSelectedItem()).getTp_id();
		}

		Teachingplangrade tp = new Teachingplangrade(tp_id, m_id, tg_grade);
		Connection con = null;
		try {
			//				 ȡ�����ݿ������
			con = Databaseconnection.getconnection();
			con.setAutoCommit(false);
			//				 ����ѧ���������뵽���ݿ��ѧ�������
			int r = Teachingplangradeaccess.insert(con, tp);
			// �������ɹ�
			if (r >= 1) {
				JOptionPane.showMessageDialog(this, "����ɹ�");

			} else {// �������ʧ�ܣ�����÷������лع�
				String msg = "��ѧ�ƻ����ݲ���ʧ�ܣ�����ϵϵͳ����Ա��";
				Tools.connectionroolback(con, msg);
				return;
			}
			teachinggrade();
			teachingplan();
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
		teachinggrade();
		teachingplan();

	}

	private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
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
	public Object tg_grade;

}