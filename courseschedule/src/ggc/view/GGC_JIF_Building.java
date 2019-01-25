package ggc.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ggc.dao.GGC_BuildingAddTable;
import ggc.dao.GGC_OfficeAddTable;
import global.dao.Buildingaccess;
import global.dao.Databaseconnection;
import global.dao.ExcelFileFilter;
import global.dao.Fileselection;
import global.dao.Viewofficeaccess;
import global.model.Buliding;
import global.model.View_office;
import global.model.View_teacher;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import zxx.dao.PrintObjectExcel;

import czy.model.Tools;

/**
 *
 * @author  __USER__
 */
public class GGC_JIF_Building extends javax.swing.JInternalFrame {
	private View_teacher b;
	private int b_id;
	private String b_name;
	private String b_alias;
	private String b_address;

	/** Creates new form GGC_JIf_Building */
	public GGC_JIF_Building() {
		initComponents();
	}

	public GGC_JIF_Building(View_teacher b) {
		initComponents();
		this.b = b;
		fillbuilding();
	}

	public void fillbuilding() {
		int b_id = 0;
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		dtm.setRowCount(0);
		ArrayList<Buliding> alist = Buildingaccess.getbuliding(b_id);
		for (int i = 0; i < alist.size(); i++) {
			int b_id_ggc = alist.get(i).getB_id();
			String b_name = alist.get(i).getB_name();
			String b_alias = alist.get(i).getB_alias();
			String b_address = alist.get(i).getB_address();
			Vector v = new Vector();
			v.add(b_id_ggc);
			v.add(b_name);
			v.add(b_alias);
			v.add(b_address);
			dtm.addRow(v);
		}
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jButton7 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jTextField4 = new javax.swing.JTextField();
		jButton8 = new javax.swing.JButton();

		jButton7.setText("jButton7");

		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setTitle("\u6559\u5b66\u697c\u7ba1\u7406");
		setVisible(true);

		jLabel1.setText("\u6559\u5b66\u697c\u7f16\u53f7\uff1a");

		jTextField1.setEditable(false);

		jLabel2.setText("\u6559\u5b66\u697c\u540d\u79f0\uff1a");

		jLabel3.setText("\u6559\u5b66\u697c\u522b\u540d\uff1a");

		jLabel4.setText("\u6559\u5b66\u697c\u5730\u5740\uff1a");

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] { "��ѧ¥���", "��ѧ¥����", "��ѧ¥����", "��ѧ¥��ַ" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane2.setViewportView(jTable1);

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

		jButton6.setText("\u5bfc\u51fa");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		jButton8.setText("\u5bfc\u51fa\u8f93\u5165\u683c\u5f0f");
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(108, 108, 108)
								.addComponent(jTextField4,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										823, Short.MAX_VALUE)
								.addGap(36, 36, 36))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		jButton1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		94,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		97,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton3,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		90,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton4,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		89,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		243,
																		Short.MAX_VALUE)
																.addComponent(
																		jButton5,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		96,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton6,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		94,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton8))
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		jScrollPane2,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		943,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(31, 31,
																		31)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						jLabel4,
																						javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.LEADING,
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel1)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										jTextField1,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										151,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(33,
																										33,
																										33)
																								.addComponent(
																										jLabel2)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										jTextField2,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										197,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(28,
																										28,
																										28)
																								.addComponent(
																										jLabel3)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										jTextField3,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										203,
																										javax.swing.GroupLayout.PREFERRED_SIZE)))))
								.addGap(31, 31, 31)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(25, 25, 25)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(jLabel2)
												.addComponent(
														jTextField2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel3)
												.addComponent(
														jTextField3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(33, 33, 33)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel4)
												.addComponent(
														jTextField4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(jScrollPane2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										329,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(26, 26, 26)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jButton1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														42,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														42,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														42,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														42,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton6,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														42,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton5,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														42,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jButton8,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														39,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(32, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//���õ����������ļ���·�������tabletwo������
		File Filename = Fileselection.exportselect();
		File filenametow = new File(Filename + ".xls");
		String filetable = filenametow.toString();
		//		 System.out.println(Filename+".xls");
		String o_id = null;
		//ʵ���������б��Ѳ�����Ϣ�����calist������
		ArrayList<Buliding> caList = Buildingaccess.getbuliding(b_id);
		List<Buliding> list = new ArrayList<Buliding>();
		//����excel��ͷ��Ϣ
		String[] header = { "��ѧ¥���", "��ѧ¥����", "��ѧ¥����", "��ѧ¥��ַ" };
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

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
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
		List<String> buliding_List = GGC_BuildingAddTable
				.getBuildingThrow(table);
		//		System.out.println(department_List);
		// �ж�office_List�Ĵ�����Ϣ����,���Ϊ������ɹ�,ˢ��ҳ��
		if (buliding_List.size() == 0 || buliding_List == null) {
			JOptionPane.showMessageDialog(this, "����ɹ�", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			fillbuilding();
		} else {
			// ���ȶ���һ���ַ�������Ϊstr
			String str = "";
			// ���������б������Ϣ
			for (String item : buliding_List) {
				// �ѱ������Ĵ�����Ϣ,��ŵ�str��������
				str += item + "\n";
			}
			// ��ʾ��Ϣ���Ѵ�����Ϣ�����ǰ̨ҳ�浱�У�
			JOptionPane.showMessageDialog(this, str, "��ʾ��Ϣ",
					JOptionPane.ERROR_MESSAGE);
		}
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
		// ���jTextField2�ı�����û����������
		if (jTextField2.getText().equals("")) {
			// ��ʾ��Ϣ:�������ѧ¥����
			JOptionPane.showMessageDialog(this, "�������ѧ¥���ƣ�", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField2.requestFocus();
			return;
		}
		if (jTextField4.getText().equals("")) {
			// ��ʾ��Ϣ:�������ѧ¥��ַ
			JOptionPane.showMessageDialog(this, "�������ѧ¥��ַ��", "��ʾ��Ϣ",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField4.requestFocus();
			return;
		}
		// ����һ�����������jtable1�ĵ�i�е�0�е��ַ���
		int b_id = (Integer) jTable1.getValueAt(i, 0);
		// ����һ�����������jtable1�ĵ�i�е�1�е��ַ���
		String b_name = (String) jTable1.getValueAt(i, 1);
		// ����һ�����������jtable1�ĵ�i�е�2�е��ַ���
		String b_alias = (String) jTable1.getValueAt(i, 2);
		// ����һ�����������jtable1�ĵ�i�е�3�е��ַ���
		String b_address = (String) jTable1.getValueAt(i, 3);
		// ����һ��b_id_ggc���������õ�jTextField1�ı��������
		int b_id_ggc = Integer.parseInt(jTextField1.getText());
		// ����һ��b_name_ggc�����õ�jTextField2�ı��������
		String b_name_ggc = jTextField2.getText();
		// ����һ��b_name_ggc�����õ�jTextField2�ı��������
		String b_alias_ggc = jTextField3.getText();
		// ����һ��b_name_ggc�����õ�jTextField2�ı��������
		String b_address_ggc = jTextField4.getText();
		// �������ݿ�Ϊ��
		Connection con = null;
		try {
			// �������ݿ�����
			con = Databaseconnection.getconnection();
			//�������ݿ���Զ��ύ��ʽΪfalse
			con.setAutoCommit(false);
			// �����ǰ���ı�������ݺ�ѡ�еĶ�Ӧ���е�ֵ��ƥ���ʱ,˵���û�Ҫ�޸���Ϣ��
			if (!(b_id_ggc == b_id)) {
				// �������ݿ��ѯ��ѧ¥����ֶ�ֵ,�����ǰ���ݿ��Ӧ���ֶ������ֵ�����û���ǰ�Ѿ������ֵ�������
				if (Buildingaccess.findid(con, b_id_ggc)) {
					// ��ʾ��Ϣ����ѧ¥����Ѿ�����
					JOptionPane.showMessageDialog(this, "��ѧ¥����Ѿ���������������",
							"������Ϣ", JOptionPane.ERROR_MESSAGE);
					// jTextField1�ı����ý���
					jTextField1.requestFocus();
					// jtexfField1�ı���ȫѡ
					jTextField1.selectAll();
					return;
				}
			}
			if (!(b_name_ggc.equals(b_name))) {
				if (Buildingaccess.findname(con, b_name_ggc)) {
					// ��ʾ��Ϣ����ѧ¥�����Ѿ�����
					JOptionPane.showMessageDialog(this, "��ѧ¥�����Ѿ���������������",
							"������Ϣ", JOptionPane.ERROR_MESSAGE);
					// jTextField1�ı����ý���
					jTextField2.requestFocus();
					// jtexfField1�ı���ȫѡ
					jTextField2.selectAll();
					return;
				}
			}
			// �ж���ǰ�������b_id��b_alias��b_name�����ݿ������d_id��b_alias��d_name�Ƿ��ظ�,����ظ�����ʾ��Ϣ
			if ((b_id_ggc == b_id) && (b_name_ggc.equals(b_name))
					&& (b_alias_ggc.equals(b_alias))
					&& (b_address_ggc.equals(b_address))) {
				// ��ʾ��Ϣ:��������Ҫ�޸ĵ���Ϣ��
				JOptionPane.showMessageDialog(this, "��������Ҫ�޸ĵ���Ϣ��", "��ʾ��Ϣ",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int r = Buildingaccess.update(con, b_id, new Buliding(b_id_ggc,
					jTextField2.getText(), b_alias_ggc, b_address_ggc));
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
				fillbuilding();
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

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		this.dispose();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//��¼jtble1��嵱ǰ������
		int i = jTable1.getSelectedRow();
		jTable1.setRowSelectionAllowed(true);
		String ggcString = "";
		String ggc = "";
		int b_id = 0;
		int number = 0;
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
				b_id = (Integer) jTable1.getValueAt(row[a], 0);
				String st = ",";
				ggc = "(" + (ggcString += b_id + (st)) + ")";
				//����һ�����ַ�������st
				//�����������ɾ���������Ҫ���sql���
				//						number = Integer.parseInt(zzt);
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
					//��re����¼ɾ�����ű�ŵķ����Ľ����
					re = Buildingaccess.Delete(con, ggc);
					//��������  > 0 ��ɾ���ɹ�
					if (re > 0) {
						JOptionPane.showMessageDialog(this, "ɾ����¼�ɹ���", "ȷ����Ϣ",
								JOptionPane.INFORMATION_MESSAGE);
						//�������ݿ���ֶ��ύ
						con.commit();
						//ʵʱˢ��jtble1�������
						fillbuilding();
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

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		int row = jTable1.getSelectedRow();
		jTextField1.setText(jTable1.getValueAt(row, 0).toString());
		jTextField2.setText((String) jTable1.getValueAt(row, 1));
		jTextField3.setText((String) jTable1.getValueAt(row, 2));
		jTextField4.setText((String) jTable1.getValueAt(row, 3));
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		String b_name = jTextField2.getText();
		String b_alias = jTextField3.getText();
		String b_address = jTextField4.getText();
		if (b_name.equals("")) {
			// ��ʾ��Ϣ����ѧ¥���Ʋ���Ϊ�գ�
			JOptionPane.showMessageDialog(this, "��ѧ¥���Ʋ���Ϊ�գ�");
			jTextField2.requestFocus();
			return;
		}
		if (b_address.equals("")) {
			// ��ʾ��Ϣ����ѧ¥���Ʋ���Ϊ�գ�
			JOptionPane.showMessageDialog(this, "��ѧ¥��ַ����Ϊ�գ�");
			jTextField4.requestFocus();
			return;
		}
		Connection con = null;
		int zhujian = 0;
		int b_int = 0;
		try {
			con = Databaseconnection.getconnection();
			//�Ѳ�ѯ��ѧ¥��ŵ����ֵ����max�Ľ����
			ResultSet max = Buildingaccess.findmax(con);
			//��������������������ҵ����ֵ��ֵ��zhujian����
			while (max.next()) {
				zhujian = max.getInt("b_id");
			}
			//�����ѧ¥���û������,�����һ�����Ϊ��01��������
			if (zhujian == 0) {
				b_int = 1;
			} else {
				//����һ�����α���,������ŵ�ǰ��ѧ¥�����������ֵ��һ
				b_int = (zhujian) + 1;
				//�ڰ��Ѿ��ӹ�һ�����α���ת��Ϊ�ַ�������b_int������
			}
			//ʵ�������ŵĹ��췽��
			Buliding bu = new Buliding(b_int, b_name, b_alias, b_address);
			// �������ݿ�����
			// �������ݿ���Զ��ύ��ʽΪfalse
			con.setAutoCommit(false);
			if (Buildingaccess.findname(con, b_name)) {
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
			int r = Buildingaccess.insert(con, bu);
			// ���r>=1
			if (r >= 1) {
				// ����ʾ��Ϣ����ӳɹ���
				JOptionPane.showMessageDialog(this, "��ӳɹ���");
				// ��ʼ���ݿ���ֶ��ύ��ʽ
				con.commit();
				fillbuilding();
				jTextField1.setText("");
				jTextField2.setText("");
				jTextField3.setText("");
				jTextField4.setText("");
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

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//���õ����������ļ���·�������tabletwo������
		File Filename = Fileselection.exportselect();
		File filenametow = new File(Filename + ".xls");
		String filetable = filenametow.toString();
		//		 System.out.println(Filename+".xls");
		String o_id = null;
		//ʵ���������б��Ѳ�����Ϣ�����calist������
		ArrayList<Buliding> caList = Buildingaccess.getbuliding(b_id);
		List<Buliding> list = new ArrayList<Buliding>();
		//����excel��ͷ��Ϣ
		String[] header = { "��ѧ¥���", "��ѧ¥����", "��ѧ¥����", "��ѧ¥��ַ" };
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

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	// End of variables declaration//GEN-END:variables

}