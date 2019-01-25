/*
 * GGC_JIF_Classroom.java
 *
 * Created on __DATE__, __TIME__
 */

package ggc.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import czy.model.Tools;

import zzt.model.Classroomtype;

import global.dao.Classinformationaccess;
import ggc.dao.Classroomaccess;
import global.dao.Courseaccess;
import global.dao.Databaseconnection;
import global.model.Buliding;
import global.model.Classroom;
import global.model.Course;
import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.Teacher;
import global.model.Teachingplan;
import global.model.View_classinformation;
import global.model.View_classroom;
import global.model.View_teacher;

/**
 *
 * @author  __USER__
 */
public class GGC_JIF_Classroom extends javax.swing.JInternalFrame {
	private View_teacher cl;
	private int b_id;
	private int ct_id;

	/** Creates new form GGC_JIF_Classroom */
	public GGC_JIF_Classroom() {
		initComponents();
	}

	public GGC_JIF_Classroom(View_teacher cl) {
		initComponents();
		this.cl = cl;
		Fillcombobox.filldepartment(jComboBox1);
		Fillcombobox.fillclassroomtype(ct_id, jComboBox2);
		Fillcombobox.fillbuilding(b_id, jComboBox3);
	}

	public void fillclassroom() {
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		dtm.setRowCount(0);
		Department dm = (Department) jComboBox1.getSelectedItem();
		if (dm == null)
			return;
		Classroomtype ct = (Classroomtype) jComboBox2.getSelectedItem();
		if (ct == null)
			return;
		Buliding bu = (Buliding) jComboBox3.getSelectedItem();
		if (bu == null)
			return;
		ArrayList<View_classroom> alist = Classroomaccess.getView_classroom(dm
				.getD_id());
		if (alist.size() == 0)
			return;
		for (int i = 0; i < alist.size(); i++) {
			String d_name = alist.get(i).getD_name();
			String b_name = alist.get(i).getB_name();
			String b_alias = alist.get(i).getB_alias();
			String b_addres = alist.get(i).getB_address();
			String ct_name = alist.get(i).getCt_name();
			int cr_id = alist.get(i).getCr_id();
			String cr_name = alist.get(i).getCr_name();
			int seating = alist.get(i).getSeating();
			Vector v = new Vector();
			v.add(d_name);
			v.add(b_name);
			v.add(ct_name);
			v.add(cr_id);
			v.add(cr_name);
			v.add(seating);
			dtm.addRow(v);
		}
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

jPanel1 = new javax.swing.JPanel();
jLabel1 = new javax.swing.JLabel();
jComboBox1 = new javax.swing.JComboBox();
jLabel2 = new javax.swing.JLabel();
jComboBox2 = new javax.swing.JComboBox();
jLabel3 = new javax.swing.JLabel();
jComboBox3 = new javax.swing.JComboBox();
jLabel4 = new javax.swing.JLabel();
jTextField1 = new javax.swing.JTextField();
jLabel5 = new javax.swing.JLabel();
jLabel6 = new javax.swing.JLabel();
jTextField2 = new javax.swing.JTextField();
jTextField3 = new javax.swing.JTextField();
jScrollPane1 = new javax.swing.JScrollPane();
jTable1 = new javax.swing.JTable();
jButton1 = new javax.swing.JButton();
jButton2 = new javax.swing.JButton();
jButton3 = new javax.swing.JButton();
jButton4 = new javax.swing.JButton();
jButton5 = new javax.swing.JButton();

setClosable(true);
setIconifiable(true);
setTitle("\u6559\u5ba4\u7ba1\u7406");

jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "\u6559\u5ba4\u7ba1\u7406"));

jLabel1.setText("\u8bf7\u9009\u62e9\u90e8\u95e8\u540d\u79f0\uff1a");


jLabel2.setText("\u8bf7\u9009\u62e9\u6559\u5ba4\u7c7b\u578b\uff1a");


jLabel3.setText("\u8bf7\u9009\u62e9\u6559\u5b66\u697c\u540d\u79f0\uff1a");


jLabel4.setText("\u6559\u5ba4\u7f16\u53f7\uff1a");

jLabel5.setText("\u6559\u5ba4\u540d\u79f0\uff1a");

jLabel6.setText("\u5ea7\u4f4d\uff1a");

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGap(41, 41, 41)
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addGroup(jPanel1Layout.createSequentialGroup()
.addComponent(jLabel4)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(35, 35, 35)
.addComponent(jLabel5)
.addGap(3, 3, 3)
.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(34, 34, 34)
.addComponent(jLabel6)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jTextField3))
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(jLabel1)
.addComponent(jLabel2))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addGroup(jPanel1Layout.createSequentialGroup()
.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addComponent(jLabel3)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))))
.addGap(44, 44, 44))
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addContainerGap()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel1)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGap(18, 18, 18)
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel2)
.addComponent(jLabel3)
.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGap(18, 18, 18)
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel4)
.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel5)
.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jLabel6))
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

jTable1.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"��������", "��������", "��ѧ¥��", "���ұ��", "��������", "��λ"
	}
) {
	boolean[] canEdit = new boolean [] {
		false, false, false, false, false, false
	};

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit [columnIndex];
	}
});
jScrollPane1.setViewportView(jTable1);

jButton1.setText("\u6dfb\u52a0");
jButton1.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton1ActionPerformed(evt);
}
});

jButton2.setText("\u5220\u9664");
jButton2.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton2ActionPerformed(evt);
}
});

jButton3.setText("\u4fee\u6539");
jButton3.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton3ActionPerformed(evt);
}
});

jButton4.setText("\u9000\u51fa");

jButton5.setText("\u5bfc\u5165");

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
.addGroup(layout.createSequentialGroup()
.addComponent(jButton1)
.addGap(18, 18, 18)
.addComponent(jButton2)
.addGap(18, 18, 18)
.addComponent(jButton3)
.addGap(18, 18, 18)
.addComponent(jButton4)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
.addComponent(jButton5)))
.addContainerGap())
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(18, 18, 18)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jButton1)
.addComponent(jButton5)
.addComponent(jButton2)
.addComponent(jButton3)
.addComponent(jButton4))
.addContainerGap(26, Short.MAX_VALUE))
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		Department jcob1 = (Department) jComboBox1.getSelectedItem();
		String d_id=jcob1.getD_id();
		Classroomtype jcob2 = (Classroomtype) jComboBox2.getSelectedItem();
		int ct_id = jcob2.getCt_id();
		
		Buliding jcob3 = (Buliding) jComboBox3.getSelectedItem();
		int b_id = jcob3.getB_id();
		//		int cr_id = Integer.parseInt((String)jTextField1.getText());
		String cr_name = jTextField2.getText();
		int cr_seating = (java.lang.Integer) Integer(jTextField3.getText().toString());
		if (cr_name.equals("")) {
			// ��ʾ��Ϣ���������Ʋ���Ϊ�գ�
			JOptionPane.showMessageDialog(this, "�������Ʋ���Ϊ�գ�");
			jTextField2.requestFocus();
			return;
		}
	
		if (cr_seating==0) {
			// ��ʾ��Ϣ��������λ����Ϊ�գ�
			JOptionPane.showMessageDialog(this, "������λ����Ϊ�գ�");
			jTextField3.requestFocus();
			return;
		}
		Connection con = null;
		int zhujian = 0;
		int cr_int = 0;
		try {
			con = Databaseconnection.getconnection();
			//�Ѳ�ѯ���ұ�ŵ����ֵ����max�Ľ����
			ResultSet max = Classroomaccess.findmax(con);
			//��������������������ҵ����ֵ��ֵ��zhujian����
			while (max.next()) {
				zhujian = max.getInt("cr_id");
			}
			//������ұ��û������,������һ�����Ϊ��01��������
			if (zhujian == 0) {
				cr_int = 1;
			} else {
				//����һ�����α���,������ŵ�ǰ��ѧ¥�����������ֵ��һ
				cr_int = (zhujian) + 1;
				//�ڰ��Ѿ��ӹ�һ�����α���ת��Ϊ�ַ�������b_int������
			}
			//ʵ�������ŵĹ��췽��
			Classroom cr = new Classroom(cr_int, d_id, cr_name, ct_id,
					cr_seating, b_id);
			// �������ݿ�����
			// �������ݿ���Զ��ύ��ʽΪfalse
			con.setAutoCommit(false);
			if (Classroomaccess.findname(con, cr_name)) {
				// ��ʾ��Ϣ����ѧ¥�����Ѿ�����
				JOptionPane.showMessageDialog(this, "��ѧ¥�����Ѿ���������������", "������Ϣ",
						JOptionPane.ERROR_MESSAGE);
				// jTextField2�ı����ý���
				jTextField2.requestFocus();
				// jtexfField2�ı���ȫѡ
				jTextField2.selectAll();
				return;
			}
			// ����r������Ž����
			int r = Classroomaccess.insert(con, cr);
			// ���r>=1
			if (r >= 1) {
				// ����ʾ��Ϣ�����ӳɹ���
				JOptionPane.showMessageDialog(this, "���ӳɹ���");
				// ��ʼ���ݿ���ֶ��ύ��ʽ
				con.commit();
				fillclassroom();
				jTextField1.setText("");
				jTextField2.setText("");
				jTextField3.setText("");
			}
			// ���r<1
			if (r < 1) {
				// ����ʾ��Ϣ���ű�Ų���ʧ�ܣ�����ϵϵͳ����Ա��
				Tools.connectionroolback(con, "��ѧ¥��Ų���ʧ�ܣ�����ϵϵͳ����Ա��");
				return;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
		} finally {
			try {
				con.rollback();
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Integer Integer(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	// End of variables declaration//GEN-END:variables

}