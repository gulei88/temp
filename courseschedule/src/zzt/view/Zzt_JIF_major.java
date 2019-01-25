/*
 * Zzt_JIF_major.java
 *
 * Created on __DATE__, __TIME__
 */

package zzt.view;

import global.dao.Databaseconnection;
import global.dao.Departmentaccess;
import global.dao.ExcelFileFilter;
import global.dao.Fileselection;
import global.dao.Majoraccess;
import global.dao.Viewmajoraccess;
import global.model.Department;
import global.model.Fillcombobox;
import global.model.Major;
import global.model.Office;
import global.model.Teacher;
import global.model.View_major;
import global.model.View_teacher;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import zxx.dao.DepartmentAddTable;
import zxx.dao.MajorAddTable;
import zxx.dao.PrintObjectExcel;
import zxx.dao.TestFileChooserUI;

import czy.model.Tools;

/**
 *רҵ�������
 *		  dt��
 *			Major����
 * @author  zzt
 */
public class Zzt_JIF_major extends javax.swing.JInternalFrame {

	private View_teacher mj;
	private String o_id;
	private String o_name;
	private String d_id;
	private Connection con;
	private String[] args;
	private String String;
	private Object modal;

	/** Creates new form Zzt_JIF_major */
	public Zzt_JIF_major() {
		//��ʼ������
		initComponents();
	}

	public Zzt_JIF_major(View_teacher mj) {
		//��ʼ������
		initComponents();
		// �����ݵĲ�������¼�û�����ͼ��Ϣ�����ݸ���Ķ�Ӧ�����ֶ�
		this.mj = mj;
		// ������ľ�̬������ʼ�����������б�
		Fillcombobox.filldepartmentadd(d_id, jComboBox2);
		// ������ľ�̬������ʼ�����������б�
		//		Fillcombobox.filloffice(o_id, jComboBox1);
		//		jComboBox1.addItem(new Department("", ""));
		jComboBox2.setSelectedItem(null);
	}

	/**
	 * �������
	 * 
	 * @author zzt
	 * @param vt
	 *            ����¼�û�����ͼ��Ϣ
	 */
	public void majortable() {
		//����DefaultTableModel �õ�jtble1����ϵ�ģ��
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		//���ñ���������0��ʼ����
		dtm.setRowCount(0);
		//�õ����������б�����
		Office o = (Office) jComboBox1.getSelectedItem();
		// ���Ϊ��,���˳�
		if (o == null)
			return;
		//�õ����������б�����
		Department d = (Department) jComboBox2.getSelectedItem();
		// ���Ϊ��,���Ƴ�
		if (d == null) {
			return;
		}

		//��ʼ��ArrayList�б�ͨ��o_id������ѯ��רҵ��
		ArrayList<View_major> aList = Viewmajoraccess.getMajor(o.getO_id());
		if (aList.size() == 0)
			return;
		// ����רҵ��������б�
		for (int i = 0; i < aList.size(); i++) {
			// ȡ����Ӧ�ֶ���Ϣ
			String d_name = aList.get(i).getD_name();
			String o_name = aList.get(i).getO_name();
			String m_id = aList.get(i).getM_id();
			String m_name = aList.get(i).getM_name();
			// �����������ͱ���
			Vector v = new Vector();
			// ��ȡ��������ӵ�������
			v.add(d_name);
			v.add(o_name);
			v.add(m_id);
			v.add(m_name);
			// ��������ӵ������
			dtm.addRow(v);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

jLabel1 = new javax.swing.JLabel();
jTextField1 = new javax.swing.JTextField();
jLabel2 = new javax.swing.JLabel();
jTextField2 = new javax.swing.JTextField();
jLabel3 = new javax.swing.JLabel();
jComboBox1 = new javax.swing.JComboBox();
jScrollPane1 = new javax.swing.JScrollPane();
jTable1 = new javax.swing.JTable();
jButton1 = new javax.swing.JButton();
jButton2 = new javax.swing.JButton();
jButton3 = new javax.swing.JButton();
jButton4 = new javax.swing.JButton();
jButton5 = new javax.swing.JButton();
jLabel4 = new javax.swing.JLabel();
jComboBox2 = new javax.swing.JComboBox();
jButton6 = new javax.swing.JButton();
jButton7 = new javax.swing.JButton();

setClosable(true);
setForeground(java.awt.Color.white);
setIconifiable(true);
setMaximizable(true);
setTitle("\u4e13\u4e1a\u7ba1\u7406");

jLabel1.setText("\u4e13\u4e1a\u7f16\u53f7\uff1a");

jLabel2.setText("\u4e13\u4e1a\u540d\u79f0\uff1a");

jLabel3.setText("\u8bf7\u9009\u62e9\u79d1\u5ba4\u540d\u79f0\uff1a");

jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
jComboBox1.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox1ItemStateChanged(evt);
}
});

jTable1.setModel(new javax.swing.table.DefaultTableModel(
	new Object [][] {
		
	},
	new String [] {
		"��������", "��������", "רҵ���", "רҵ����"
	}
) {
	boolean[] canEdit = new boolean [] {
		true, false, false, false
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

jButton5.setText("\u5bfc\u5165\u6570\u636e");
jButton5.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton5ActionPerformed(evt);
}
});

jLabel4.setText("\u8bf7\u9009\u62e9\u90e8\u95e8\u540d\u79f0\uff1a");

jComboBox2.addItemListener(new java.awt.event.ItemListener() {
public void itemStateChanged(java.awt.event.ItemEvent evt) {
jComboBox2ItemStateChanged(evt);
}
});

jComboBox2.addInputMethodListener(new java.awt.event.InputMethodListener() {
public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
}
public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
jComboBox2InputMethodTextChanged(evt);
}
});

jButton6.setText("\u5bfc\u51fa\u8f93\u5165\u683c\u5f0f");
jButton6.addActionListener(new java.awt.event.ActionListener() {
public void actionPerformed(java.awt.event.ActionEvent evt) {
jButton6ActionPerformed(evt);
}
});

jButton7.setText("\u5bfc\u51fa\u754c\u9762\u683c\u5f0f");
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
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addComponent(jLabel3)
.addComponent(jLabel4))
.addGap(7, 7, 7)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addComponent(jComboBox2, 0, 477, Short.MAX_VALUE)
.addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, 477, Short.MAX_VALUE)))
.addGroup(layout.createSequentialGroup()
.addComponent(jLabel1)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jLabel2)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
.addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
.addGroup(layout.createSequentialGroup()
.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(18, 18, 18)
.addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE))
.addGroup(layout.createSequentialGroup()
.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(29, 29, 29)))))
.addContainerGap())
);
layout.setVerticalGroup(
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
.addGroup(layout.createSequentialGroup()
.addContainerGap()
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel4)
.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGap(18, 18, 18)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel3)
.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGap(18, 18, 18)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jLabel1)
.addComponent(jLabel2)
.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
.addGap(18, 18, 18)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
.addGap(18, 18, 18)
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

pack();
}// </editor-fold>

	//GEN-END:initComponents
	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		File Filename = Fileselection.exportselect();
		File filenametow = new File(Filename + ".xls");
		String filetable = filenametow.toString();
		String o_id = null;
		//ʵ���������б��Ѳ�����Ϣ�����calist������
		ArrayList<View_major> caList = Viewmajoraccess.getMajor(o_id);
		List<View_major> list = new ArrayList<View_major>();
		//����excel��ͷ��Ϣ
		String[] header = { "���ű��","��������", "���ұ��","��������", "רҵ���", "רҵ����" };
		JFileChooser fDialog = new JFileChooser();
		if (Filename == null) {
			return;
		}
		//��ô�õ�����֮����ļ���
		if (filenametow.exists()) {
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
		System.out.println(filetable);
		String o_id = null;
		//ʵ���������б��Ѳ�����Ϣ�����calist������
		List<Major> caList = Majoraccess.getMajor(o_id);
		List<Major> list = new ArrayList<Major>();
		//����excel��ͷ��Ϣ
		String[] header = { "רҵ���", "���ұ��", "רҵ����" };
		JFileChooser fDialog = new JFileChooser();
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

	private void jComboBox2InputMethodTextChanged(
			java.awt.event.InputMethodEvent evt) {
		// TODO add your handling code here:
	}

	private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
		// TODO add your handling code here:
		Department dm = (Department) jComboBox2.getSelectedItem();
		if (dm == null)
			return;
		String d_id = dm.getD_id();
		Fillcombobox.filloffice(d_id, jComboBox1);
		jTextField1.setText("");
		jTextField2.setText("");
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser fDialog = new JFileChooser();
		String table = TestFileChooserUI.main(String);
		// ����ļ�
		fDialog.setDialogTitle("��ѡ���ļ�");
		// ����ѡ���
		// ����table���������洢Ҫ����ļ���·��
		// ��ʼ��department_List����,���ж��Ĵ�����Ϣ����department_List�����б���
		List<String> department_List = MajorAddTable.getDepartmentThrow(table);
		// �ж�department_List�Ĵ�����Ϣ����,���Ϊ������ɹ�,ˢ��ҳ��
		if (department_List.size() == 0 || department_List == null) {
			JOptionPane.showMessageDialog(this, "����ɹ�", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			majortable();

		} else {
			// ���ȶ���һ���ַ�������Ϊstr
			String str = "";
			// ���������б������Ϣ
			for (String item : department_List) {
				// �ѱ������Ĵ�����Ϣ,��ŵ�str��������
				str += item + "\n";
			}
			// ��ʾ��Ϣ���Ѵ�����Ϣ�����ǰ̨ҳ�浱�У�
			throw_ok th = new throw_ok(null, true);
			th.infomation(str);
			th.setVisible(true);

		}
	}

	private Container setContentPane(java.lang.String str) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ɾ��רҵ������Ϣ
	 * 
	 * @author zzt
	 */
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//��¼jtble1��嵱ǰ������
		int i = jTable1.getSelectedRow();
		jTable1.setRowSelectionAllowed(true);
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
			int[] row = jTable1.getSelectedRows();
			//����һ��count�����������
			int count = row.length;
			//��������
			for (int a = 0; a < count; a++) {
				//����һ����������Ų��ű�ŵĵ�row[a]�е�0��
				String d_id = (String) jTable1.getValueAt(row[a], 1);
				//����һ�����ַ�������st
				String st = ",";
				//�����������ɾ���������Ҫ���sql���
				zzt = "(" + (zztString += "'" + d_id + "'" + (st)) + ")";
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
					re = Majoraccess.Delete(con, zzt);
					//��������  > 0 ��ɾ���ɹ�
					if (re > 0) {
						JOptionPane.showMessageDialog(this, "ɾ����¼�ɹ���", "ȷ����Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
						//�������ݿ���ֶ��ύ
						con.commit();
						//ʵʱˢ��jtble1�������
						majortable();
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

	/**
	 * �޸�רҵ������Ϣ
	 * 
	 * @author zzt
	 */
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
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
		// ���רҵ����ı�����û����������
		if (jTextField1.getText().equals("")) {
			// ��ʾ��Ϣ:������רҵ���
			JOptionPane.showMessageDialog(this, "������רҵ��ţ�", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField1.requestFocus();
			return;
		}

		// ���רҵ�����ı�����û����������
		if (jTextField2.getText().equals("")) {
			// ��ʾ��Ϣ:������רҵ����
			JOptionPane.showMessageDialog(this, "������רҵ���ƣ�", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField2.requestFocus();
			return;
		}
		// ����һ�����������jtable1�ĵ�i�е�0�е��ַ���
		Office jcbox1 = (Office) jComboBox1.getSelectedItem();
		Department jcbox2 = (Department) jComboBox2.getSelectedItem();
		String zzt_oid = jcbox1.getO_id();
		String zzt_did = jcbox2.getD_id();
		String m_id = (String) jTable1.getValueAt(i, 2);
		// ����һ�����������jtable1�ĵ�i�е�1�е��ַ���
		String m_name = (String) jTable1.getValueAt(i, 3);
		// ����һ��d_id_zzt���������õ�רҵ����ı��������
		String m_id_zzt = jTextField1.getText();
		// ����һ��d_name_zzt�����õ�רҵ�����ı��������
		String m_name_zzt = jTextField2.getText();
		// �������ݿ�Ϊ��
		Connection con = null;
		try {
			// �������ݿ�����
			con = Databaseconnection.getconnection();
			//�������ݿ���Զ��ύ��ʽΪfalse
			con.setAutoCommit(false);
			// �����ǰ���ı�������ݺ�ѡ�еĶ�Ӧ���е�ֵ��ƥ���ʱ,˵���û�Ҫ�޸���Ϣ��
			if (!(m_id_zzt.equals(m_id))) {
				// �������ݿ��ѯרҵ����ֶ�ֵ,�����ǰ���ݿ��Ӧ���ֶ������ֵ�����û���ǰ�Ѿ������ֵ�������
				if (Majoraccess.findid(con, m_id_zzt)) {
					// ��ʾ��Ϣ��רҵ����Ѿ�����
					JOptionPane.showMessageDialog(this, "רҵ����Ѿ���������������",
							"������Ϣ", JOptionPane.ERROR_MESSAGE);
					// jTextField1�ı����ý���
					jTextField1.requestFocus();
					// jtexfField1�ı���ȫѡ
					jTextField1.selectAll();
					return;
				}
			}
			if (!(m_name_zzt.equals(m_name))) {
				if (Majoraccess.findname(con, m_name_zzt)) {
					// ��ʾ��Ϣ��רҵ�����Ѿ�����
					JOptionPane.showMessageDialog(this, "רҵ�����Ѿ���������������",
							"������Ϣ", JOptionPane.ERROR_MESSAGE);
					// jTextField1�ı����ý���
					jTextField2.requestFocus();
					// jtexfField1�ı���ȫѡ
					jTextField2.selectAll();
					return;
				}
			}
			// �ж���ǰ�������רҵ��ź�רҵ���������ݿ�������Ƿ��ظ�,����ظ�����ʾ��Ϣ
			if ((m_id_zzt.equals(m_id)) && (m_name_zzt.equals(m_name))) {
				// ��ʾ��Ϣ:��������Ҫ�޸ĵ���Ϣ��
				JOptionPane.showMessageDialog(this, "��������Ҫ�޸ĵ���Ϣ��", "��ʾ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			// ����һ������r�����洢update�������صĽ����
			int r = Majoraccess.update(con, m_id, new Major(m_id_zzt, zzt_oid,
					jTextField2.getText()));
			// ������������0�Ļ�
			if (r > 0) {
				// ��ʾ��Ϣ�޸ĳɹ�
				JOptionPane.showMessageDialog(this, "�޸ĳɹ�", "��ʾ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
				//���רҵ��ŵ��ı���
				jTextField1.setText("");
				//���רҵ���Ƶ��ı���
				jTextField2.setText("");
				//�������ݿ��ֶ��ύ��ʽ
				con.commit();
				// ����majortable��������ʵʱˢ�±��
				majortable();
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

	/**
	 * ��Ӳ��Ź�����Ϣ
	 * 
	 * @author zzt
	 */
	@SuppressWarnings("unused")
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:		// ȡ�ò��������б�ѡ������
		Department jcbox2 = (Department) jComboBox2.getSelectedItem();
		if (jcbox2 == null) {
			JOptionPane.showMessageDialog(this, "��ѡ��������", "������Ϣ",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String zzt_did = jcbox2.getD_id();
		// ȡ�ÿ��������б�ѡ������
		Office jcbox1 = (Office) jComboBox1.getSelectedItem();
		if (jcbox1 == null) {
			JOptionPane.showMessageDialog(this, "��ѡ���������", "������Ϣ",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String zzt_oid = jcbox1.getO_id();

		//��ȡ��ǰרҵ��ŵ��ı��������
		String d_id_zzt = jTextField1.getText();
		//��ȡ��ǰרҵ���Ƶ��ı��������
		String d_name_zzt = jTextField2.getText();
		// ���רҵ����ı�������Ϊ��
		if (zzt_did == null) {
			JOptionPane.showMessageDialog(this, "��ѡ��ѧԺ����");
			return;
		}
		//		System.out.println(zzt_did);
		//		System.out.println(zzt_oid);
		if (zzt_oid == null) {
			JOptionPane.showMessageDialog(this, "��ѡ��������");
			return;
		}
		if (d_id_zzt.equals("")) {
			//��ʾ��Ϣ��רҵ��Ų���Ϊ�գ�
			JOptionPane.showMessageDialog(this, "רҵ��Ų���Ϊ�գ�");
			jTextField1.requestFocus();
			return;
		}
		// ���רҵ�����ı�������Ϊ��
		if (d_name_zzt.equals("")) {
			//��ʾ��Ϣ���������Ʋ���Ϊ�գ�
			JOptionPane.showMessageDialog(this, "רҵ���Ʋ���Ϊ�գ�");
			jTextField2.requestFocus();
			return;
		}
		if (jComboBox2.equals("")) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ��Ӳ��ŵ����ƣ�");
			return;
		}
		if (jComboBox1.equals("")) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ��ӿ��ҵ����ƣ�");
			return;
		}
		// ʵ����Major��
		Major mj = new Major(d_id_zzt, zzt_oid, d_name_zzt);
		Connection con = null;
		try {
			// �������ݿ�����
			con = Databaseconnection.getconnection();
			//�������ݿ���Զ��ύ��ʽΪfalse
			con.setAutoCommit(false);
			// �������ݿ��ѯרҵ����ֶ�ֵ,�����ǰ���ݿ��Ӧ���ֶ������ֵ�����û���ǰ�Ѿ������ֵ�������
			if (Majoraccess.findid(con, d_id_zzt)) {
				// ��ʾ��Ϣ��רҵ����Ѿ�������ʾ��Ϣ��
				JOptionPane.showMessageDialog(this, "רҵ����Ѿ���������������", "������Ϣ",
						JOptionPane.ERROR_MESSAGE);
				// jTextField1�ı����ý���
				jTextField1.requestFocus();
				// jtexfField1�ı���ȫѡ
				jTextField1.selectAll();
				return;
			}
			// �������ݿ��ѯרҵ�����ֶ�ֵ,�����ǰ���ݿ��Ӧ��רҵ�����ֶ������ֵ�����û���ǰ�Ѿ������ֵ�������
			if (Majoraccess.findname(con, d_name_zzt)) {
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
			int r = Majoraccess.insert(con, mj);
			//���r>=1
			if (r >= 1) {
				//����ʾ��Ϣ����ӳɹ���
				JOptionPane.showMessageDialog(this, "��ӳɹ���");
				//��ʼ���ݿ���ֶ��ύ��ʽ
				con.commit();
				//ˢ�±��
				majortable();
				//���רҵ����ı�������
				jTextField1.setText("");
				//���רҵ�����ı�������
				jTextField2.setText("");
			}
			//���r<1����ʾ��Ϣ���ű�Ų���ʧ�ܣ�����ϵϵͳ����Ա��
			if (r < 1) {
				Tools.connectionroolback(con, "���ű�Ų���ʧ�ܣ�����ϵϵͳ����Ա��");
				return;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				//���ʧ����ع�
				con.rollback();
			} catch (SQLException e1) {

				//				if(jcbox2 == null){
				//
				//				}
				if (jcbox1 == null) {
					JOptionPane.showMessageDialog(this, "��ѡ��������", "������Ϣ",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}

	}

	//��ʾ�����б��Ӧ���û���ͼ
	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		majortable();
	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		//�õ�����еĵ�ǰ��
		int row = jTable1.getSelectedRow();
		//�ѱ���еĵ�row�еĵڶ������ݸ�ֵ��רҵ���
		jTextField1.setText((String) jTable1.getValueAt(row, 2));
		//�ѱ���еĵ�row�еĵ��������ݸ�ֵ��רҵ����
		jTextField2.setText((String) jTable1.getValueAt(row, 3));
		//�ѱ���еĵ�row�еĵ�һ�����ݸ�ֵ����������
		jComboBox1.setSelectedItem(new Office(null, null, (String) jTable1
				.getValueAt(row, 1)));
		//�ѱ���еĵ�row�еĵڶ������ݸ�ֵ����������
		jComboBox2.setSelectedItem(new Department(null, (String) jTable1
				.getValueAt(row, 0)));
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//�˳�רҵ�������
		this.dispose();
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
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	// End of variables declaration//GEN-END:variables

}