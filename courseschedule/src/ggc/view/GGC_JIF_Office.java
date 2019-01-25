/*
 * GGC_JIF_Office.java
 *
 * Created on __DATE__, __TIME__
 */

package ggc.view;

import ggc.dao.GGC_OfficeAddTable;
import global.dao.Databaseconnection;
import global.dao.Departmentaccess;
import global.dao.ExcelFileFilter;
import global.dao.Fileselection;
import global.dao.Majoraccess;
import global.dao.Officeaccess;
import global.dao.Viewmajoraccess;
import global.dao.Viewofficeaccess;
import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.Teacher;
import global.model.View_major;
import global.model.View_office;
import global.model.View_teacher;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import czy.model.Tools;

import zxx.dao.DepartmentAddTable;
import zxx.dao.PrintObjectExcel;

//import zxx.dao.OfficeTable;

/**
 *
 * @author  __USER__
 */
public class GGC_JIF_Office extends javax.swing.JInternalFrame {

	private Department dt;
	private View_teacher of;
	private String o_id;
	private String o_name;
	private String d_id;
	private Connection con;

	/** Creates new form GGC_JIF_Office */
	public GGC_JIF_Office() {
		initComponents();
	}

	public GGC_JIF_Office(View_teacher of) {
		initComponents();
		this.of = of;
		Fillcombobox.filldepartmentadd(d_id, jComboBox1);
		jComboBox1.addItem(new Department("", ""));
		jComboBox1.setSelectedIndex(jComboBox1.getItemCount() - 1);
	}

	public void officetable() {
		//����DefaultTableModel �õ�jtble1����ϵ�ģ��
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		//���ñ���������0��ʼ����
		dtm.setRowCount(0);
		Department d = (Department) jComboBox1.getSelectedItem();
		// ���Ϊ�գ����˳�
		if (d == null) {
			return;
		}

		//��ʼ��ArrayList�б�ͨ��d_id������ѯdepartment�����ű�
		ArrayList<View_office> aList = Viewofficeaccess.getoffice(d.getD_id());
		if (aList.size() == 0)
			return;
		// �������ŵ������б�
		for (int i = 0; i < aList.size(); i++) {
			// ȡ����Ӧ�ֶ���Ϣ
			String d_name = aList.get(i).getD_name();
			String o_id = aList.get(i).getO_id();
			String o_name = aList.get(i).getO_name();
			// �����������ͱ���
			Vector v = new Vector();
			// ��ȡ��������ӵ�������
			v.add(d_name);
			v.add(o_id);
			v.add(o_name);
			// ��������ӵ������
			dtm.addRow(v);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

jPanel1 = new javax.swing.JPanel();
jLabel1 = new javax.swing.JLabel();
jComboBox1 = new javax.swing.JComboBox();
jLabel2 = new javax.swing.JLabel();
jTextField1 = new javax.swing.JTextField();
jLabel3 = new javax.swing.JLabel();
jTextField2 = new javax.swing.JTextField();
jScrollPane1 = new javax.swing.JScrollPane();
jTable1 = new javax.swing.JTable();
jButton1 = new javax.swing.JButton();
jButton2 = new javax.swing.JButton();
jButton3 = new javax.swing.JButton();
jButton4 = new javax.swing.JButton();
jButton5 = new javax.swing.JButton();
jButton6 = new javax.swing.JButton();
jButton7 = new javax.swing.JButton();

setClosable(true);
setIconifiable(true);
setTitle("\u79d1\u5ba4\u7ba1\u7406");

jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "\u79d1\u5ba4\u7ba1\u7406"));

jLabel1.setText("\u8bf7\u9009\u62e9\u90e8\u95e8\u540d\u79f0\uff1a");


jComboBox1.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox1ItemStateChanged(evt);
}
});

jLabel2.setText("\u79d1\u5ba4\u7f16\u53f7\uff1a");

jTextField1.setEditable(false);

jLabel3.setText("\u79d1\u5ba4\u540d\u79f0\uff1a");

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGap(22, 22, 22)
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
.addGroup(jPanel1Layout.createSequentialGroup()
.addComponent(jLabel2)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(39, 39, 39)
.addComponent(jLabel3)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jTextField2))
.addGroup(jPanel1Layout.createSequentialGroup()
.addComponent(jLabel1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
.addContainerGap(140, Short.MAX_VALUE))
);
jPanel1Layout.setVerticalGroup(
jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(jPanel1Layout.createSequentialGroup()
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel1)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGap(18, 18, 18)
.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel2)
.addComponent(jLabel3)
.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

jTable1.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"��������", "���ұ��", "��������"
	}
) {
	boolean[] canEdit = new boolean [] {
		false, false, false
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
jButton4.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton4ActionPerformed(evt);
}
});

jButton5.setText("\u5bfc\u5165");
jButton5.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton5ActionPerformed(evt);
}
});

jButton6.setText("\u6570\u636e\u5bfc\u51fa");
jButton6.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton6ActionPerformed(evt);
}
});

jButton7.setText("\u5bfc\u5165\u683c\u5f0f\u5bfc\u51fa");
jButton7.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton7ActionPerformed(evt);
}
});

javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
.addGroup(layout.createSequentialGroup()
.addComponent(jButton1)
.addGap(18, 18, 18)
.addComponent(jButton2)
.addGap(18, 18, 18)
.addComponent(jButton3)
.addGap(18, 18, 18)
.addComponent(jButton4)
.addGap(73, 73, 73)
.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jButton7)))
.addContainerGap())
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jButton1)
.addComponent(jButton2)
.addComponent(jButton3)
.addComponent(jButton4)
.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
.addContainerGap(76, Short.MAX_VALUE))
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//���õ����������ļ���·�������tabletwo������
		File Filename = Fileselection.exportselect();
		File filenametow = new File(Filename + ".xls");
		String filetable = filenametow.toString();
		//		 System.out.println(Filename+".xls");
		String d_id = null;
		//ʵ���������б��Ѳ�����Ϣ�����calist������
		ArrayList<Office> caList = Officeaccess.getoffice(o_id);
		List<Office> list = new ArrayList<Office>();
		//����excel��ͷ��Ϣ
		String[] header = { "���ұ��", "���ű��", "��������" };
		//		 File Filename =new File() ;
		//		 System.out.println(Filename);
		//		
		JFileChooser fDialog = new JFileChooser();
		if (Filename == null) {
			//			 System.out.println("�ļ���Ϊnull");
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

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//���õ����������ļ���·�������tabletwo������
		File Filename = Fileselection.exportselect();
		File filenametow = new File(Filename + ".xls");
		String filetable = filenametow.toString();
		//		 System.out.println(Filename+".xls");
		String o_id = null;
		//ʵ���������б��Ѳ�����Ϣ�����calist������
		ArrayList<View_office> caList = Viewofficeaccess.getoffice(o_id);
		List<View_office> list = new ArrayList<View_office>();
		//����excel��ͷ��Ϣ
		String[] header = { "���ű��", "��������", "���ұ��", "��������" };
		//		 File Filename =new File() ;
		//		 System.out.println(Filename);
		//		
		JFileChooser fDialog = new JFileChooser();
		if (Filename == null) {
			//			 System.out.println("�ļ���Ϊnull");
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

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		this.dispose();
	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		int row = jTable1.getSelectedRow();
		jTextField1.setText((String) jTable1.getValueAt(row, 1));
		jTextField2.setText((String) jTable1.getValueAt(row, 2));
		jComboBox1.setSelectedItem(new Department(null, (String) jTable1
				.getValueAt(row, 0)));

	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		Department jcbox1 = (Department) jComboBox1.getSelectedItem();
		String ggc_did = jcbox1.getD_id();
		//		String o_id_ggc = jTextField1.getText();
		// //�õ���ǰjTextField2�ı���
		String o_name_ggc = jTextField2.getText();
		// �����ǰ���ı�������Ϊ��
		//		if (o_id_ggc.equals("")) {
		//			//��ʾ��Ϣ�����ұ�Ų���Ϊ�գ�
		//			JOptionPane.showMessageDialog(this, "���ұ�Ų���Ϊ�գ�");
		//			jTextField1.requestFocus();
		//			return;
		//		}
		// ������������ı�������Ϊ��
		if (o_name_ggc.equals("")) {
			//��ʾ��Ϣ���������Ʋ���Ϊ�գ�
			JOptionPane.showMessageDialog(this, "�������Ʋ���Ϊ�գ�");
			jTextField2.requestFocus();
			return;
		}
		if (jcbox1.equals(null)) {
			JOptionPane.showMessageDialog(this, "��ѡ�в��ŵ����ƣ�");
			jTextField1.requestFocus();
			return;
		}
		// ʵ����Department��
		//		Office of = new Office(o_id_ggc, ggc_did, o_name_ggc);
		Connection con = null;
		String zhujian = null;
		String d_str = "";
		try {
			// �������ݿ�����
			con = Databaseconnection.getconnection();
			con.setAutoCommit(false);
			ResultSet max = Officeaccess.findmax(con);
			//��������������������ҵ����ֵ��ֵ��zhujian����
			while (max.next()) {
				zhujian = max.getString("o_id");
			}
			//������ű��û������,�����һ�����Ϊ��01��������
			if (zhujian == null) {
				d_str = "0901";
			} else {
				//����һ�����α���,������ŵ�ǰ���ű����������ֵ��һ
				int d_id_int = Integer.parseInt(zhujian) + 1;
				//�ڰ��Ѿ��ӹ�һ�����α���ת��Ϊ�ַ�������d_str������
				d_str = "0" + Integer.toString(d_id_int);
				//ʵ�������ŵĹ��췽��
			}
			//ʵ�������ŵĹ��췽��
			Office dt = new Office(d_str, ggc_did, o_name_ggc);
			//�������ݿ���Զ��ύ��ʽΪfalse

			// �������ݿ��ѯ���ұ���ֶ�ֵ,�����ǰ���ݿ��Ӧ���ֶ������ֵ�����û���ǰ�Ѿ������ֵ�������
			//			if (Officeaccess.findid(con, o_id_ggc)) {
			//				// ��ʾ��Ϣ�����ͱ���Ѿ�����
			//				JOptionPane.showMessageDialog(this, "���ұ���Ѿ���������������", "������Ϣ",
			//						JOptionPane.ERROR_MESSAGE);
			//				// jTextField1�ı����ý���
			//				jTextField1.requestFocus();
			//				// jtexfField1�ı���ȫѡ
			//				jTextField1.selectAll();
			//				return;
			//			}
			// �������ݿ��ѯo_name�ֶ�ֵ,�����ǰ���ݿ��Ӧ��o_name�ֶ������ֵ�����û���ǰ�Ѿ������ֵ�������
			if (Officeaccess.findname(con, o_name_ggc)) {
				// ��ʾ��Ϣ�����������Ѿ�����
				JOptionPane.showMessageDialog(this, "���������Ѿ���������������", "������Ϣ",
						JOptionPane.ERROR_MESSAGE);
				// jTextField1�ı����ý���
				jTextField2.requestFocus();
				// jtexfField1�ı���ȫѡ
				jTextField2.selectAll();
				return;
			}

			// ����r������Ž����
			int r = Officeaccess.insert(con, dt);
			//���r>=1
			if (r >= 1) {
				//����ʾ��Ϣ����ӳɹ���
				JOptionPane.showMessageDialog(this, "��ӳɹ���");
				//��ʼ���ݿ���ֶ��ύ��ʽ
				con.commit();
				officetable();
				jTextField1.setText("");
				jTextField2.setText("");
			}
			//���r<1
			if (r < 1) {
				//����ʾ��Ϣ���ұ�Ų���ʧ�ܣ�����ϵϵͳ����Ա��
				Tools.connectionroolback(con, "���ұ�Ų���ʧ�ܣ�����ϵϵͳ����Ա��");
				return;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
		} finally {
			try {
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser fDialog = new JFileChooser();
		// ��ʼ��ExcelFileFilter�ļ����͹�����
		ExcelFileFilter excelFilter = new ExcelFileFilter();
		// �����ı�������
		fDialog.addChoosableFileFilter(excelFilter);
		// �����ļ�ѡ���ı���
		fDialog.setFileFilter(excelFilter);
		// ����ļ�
		fDialog.setDialogTitle("��ѡ���ļ�");
		// ����ѡ���
		int returnVal = fDialog.showOpenDialog(null);
		// ����table���������洢Ҫ����ļ���·��
		String table = fDialog.getSelectedFile().toString();
		//		System.out.println(table);
		// ��ʼ��office_List����,���ж��Ĵ�����Ϣ����office_List�����б���
		List<String> office_List = GGC_OfficeAddTable.getOfficeThrow(table);
		//		System.out.println(department_List);
		// �ж�office_List�Ĵ�����Ϣ����,���Ϊ������ɹ�,ˢ��ҳ��
		if (office_List.size() == 0 || office_List == null) {
			JOptionPane.showMessageDialog(this, "����ɹ�", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			officetable();
		} else {
			// ���ȶ���һ���ַ�������Ϊstr
			String str = "";
			// ���������б������Ϣ
			for (String item : office_List) {
				// �ѱ������Ĵ�����Ϣ,��ŵ�str��������
				str += item + "\n";
			}
			// ��ʾ��Ϣ���Ѵ�����Ϣ�����ǰ̨ҳ�浱�У�
			//			JOptionPane.showMessageDialog(this, str, "��ʾ��Ϣ",
			//					JOptionPane.ERROR_MESSAGE);
			throw_office th = new throw_office(null, true);
			th.infomation(str);
			th.setVisible(true);
		}
	}

	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		// TODO add your handling code here:
		officetable();
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// ��¼jtble1���ǰ����
		int i = jTable1.getSelectedRow();
		// ���i==-1��˵��û��ѡ����
		if (i == -1) {
			// ��ʾ��Ϣ����ѡ��Ҫ�޸ĵļ�¼
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�޸ĵļ�¼", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// ���jTextField1�ı�����û����������
		if (jTextField1.getText().equals("")) {
			// ��ʾ��Ϣ:��������ұ��
			JOptionPane.showMessageDialog(this, "��������ұ�ţ�", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField1.requestFocus();
			return;
		}

		// ���jTextField2�ı�����û����������
		if (jTextField2.getText().equals("")) {
			// ��ʾ��Ϣ:�������������
			JOptionPane.showMessageDialog(this, "������������ƣ�", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField2.requestFocus();
			return;
		}
		// ����һ�����������jtable1�ĵ�i�е�0�е��ַ���
		Department jcbox1 = (Department) jComboBox1.getSelectedItem();
		String ggc_did = jcbox1.getD_id();
		String o_id = (String) jTable1.getValueAt(i, 1);
		// ����һ�����������jtable1�ĵ�i�е�1�е��ַ���
		String o_name = (String) jTable1.getValueAt(i, 2);
		// ����һ��o_id_ggc���������õ�jTextField1�ı��������
		String o_id_ggc = jTextField1.getText();
		// ����һ��o_name_ggc�����õ�jTextField2�ı��������
		String o_name_ggc = jTextField2.getText();
		// �������ݿ�Ϊ��
		Connection con = null;
		try {
			// �������ݿ�����
			con = Databaseconnection.getconnection();
			//�������ݿ���Զ��ύ��ʽΪfalse
			con.setAutoCommit(false);
			// �����ǰ���ı�������ݺ�ѡ�еĶ�Ӧ���е�ֵ��ƥ���ʱ,˵���û�Ҫ�޸���Ϣ��
			if (!(o_id_ggc.equals(o_id))) {
				// �������ݿ��ѯ���ұ���ֶ�ֵ,�����ǰ���ݿ��Ӧ���ֶ������ֵ�����û���ǰ�Ѿ������ֵ�������
				if (Officeaccess.findid(con, o_id_ggc)) {
					// ��ʾ��Ϣ�����ͱ���Ѿ�����
					JOptionPane.showMessageDialog(this, "���ұ���Ѿ���������������",
							"������Ϣ", JOptionPane.ERROR_MESSAGE);
					// jTextField1�ı����ý���
					jTextField1.requestFocus();
					// jtexfField1�ı���ȫѡ
					jTextField1.selectAll();
					return;
				}
			}
			if (!(o_name_ggc.equals(o_name))) {
				if (Officeaccess.findname(con, o_name_ggc)) {
					// ��ʾ��Ϣ�����������Ѿ�����
					JOptionPane.showMessageDialog(this, "���������Ѿ���������������",
							"������Ϣ", JOptionPane.ERROR_MESSAGE);
					// jTextField1�ı����ý���
					jTextField2.requestFocus();
					// jtexfField1�ı���ȫѡ
					jTextField2.selectAll();
					return;
				}
			}
			// �ж���ǰ�������o_id��o_name�����ݿ������o_id��o_name�Ƿ��ظ�,����ظ�����ʾ��Ϣ
			if ((o_id_ggc.equals(o_id)) && (o_name_ggc.equals(o_name))) {
				// ��ʾ��Ϣ:��������Ҫ�޸ĵ���Ϣ��
				JOptionPane.showMessageDialog(this, "��������Ҫ�޸ĵ���Ϣ��", "��ʾ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			// ����һ������r�����洢update�������صĽ����
			int r = Officeaccess.update(con, o_id, new Office(o_id_ggc,
					ggc_did, jTextField2.getText()));
			// ������������0�Ļ�
			if (r > 0) {
				// ��ʾ��Ϣ�޸ĳɹ�
				JOptionPane.showMessageDialog(this, "�޸ĳɹ�", "��ʾ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
				jTextField1.setText("");
				jTextField2.setText("");
				//�������ݿ��ֶ��ύ��ʽ
				con.commit();
				// ����departmenttable��������ʵʱˢ�±��
				officetable();
			} else {
				// ������ʾ��Ϣ���޸�ʧ������ϵ����Ա
				JOptionPane.showMessageDialog(this, "�޸�ʧ������ϵ����Ա", "��ʾ��Ϣ",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				//������ݿ�����쳣,��ع�
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//��¼jtble1��嵱ǰ������
		int i = jTable1.getSelectedRow();
		jTable1.setRowSelectionAllowed(true);
		String ggcString = "";
		String ggc = "";
		//���i==-1��˵��û��ѡ����
		if (i == -1) {
			//��ʾ��Ϣ����ѡ��Ҫɾ���ļ�¼
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ���ļ�¼", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else {
			//����һ���������row��ŵ�ǰѡ�е�������
			int[] row = jTable1.getSelectedRows();
			//����һ��count�����������
			int count = row.length;
			//��������
			for (int a = 0; a < count; a++) {
				//����һ����������ſ��ұ�ŵĵ�row[a]�е�0��
				String o_id = (String) jTable1.getValueAt(row[a], 1);
				//����һ�����ַ�������st
				String st = ",";
				//�����������ɾ���������Ҫ���sql���
				ggc = "(" + (ggcString += "'" + o_id + "'" + (st)) + ")";
				//���sql�������һ���ַ��ǡ�)����ɾ�������λ�ַ��ڼ���һ����)��
				if (ggc.endsWith(")")) {
					ggc = ggc.substring(0, ggc.length() - 2) + ")";
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
					//��re����¼ɾ�����ұ�ŵķ����Ľ����
					re = Officeaccess.Delete(con, ggc);
					//��������  > 0 ��ɾ���ɹ�
					if (re > 0) {
						JOptionPane.showMessageDialog(this, "ɾ����¼�ɹ���", "ȷ����Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
						//�������ݿ���ֶ��ύ
						con.commit();
						//ʵʱˢ��jtble1�������
						officetable();
						jTextField1.setText("");
						jTextField2.setText("");
					} else {
						JOptionPane.showMessageDialog(this,
								"ɾ��ʧ�ܣ�����ϵ����Ա������ز�����", "������Ϣ",
								JOptionPane.ERROR_MESSAGE);
					}

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

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	// End of variables declaration//GEN-END:variables

}