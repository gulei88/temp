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

				}, new String[] { "教学楼编号", "教学楼名称", "教学楼别名", "教学楼地址" }) {
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
		//调用导出方法把文件的路径存放在tabletwo变量里
		File Filename = Fileselection.exportselect();
		File filenametow = new File(Filename + ".xls");
		String filetable = filenametow.toString();
		//		 System.out.println(Filename+".xls");
		String o_id = null;
		//实例化部门列表，把部门信息存放在calist变量中
		ArrayList<Buliding> caList = Buildingaccess.getbuliding(b_id);
		List<Buliding> list = new ArrayList<Buliding>();
		//设置excel表头信息
		String[] header = { "教学楼编号", "教学楼名称", "教学楼别名", "教学楼地址" };
		//		 File Filename =new File() ;
		//		 System.out.println(Filename);
		//		
		JFileChooser fDialog = new JFileChooser();
		if (Filename == null) {
			//			 System.out.println("文件名为null");
			return;
		}
		//怎么拿到导出之后的文件名
		if (filenametow.exists()) {
			System.out.println(filenametow.exists());
			int overwriteSelect = JOptionPane.showConfirmDialog(this,
					"<html><font size=3>文件" + filenametow.getName()
							+ "已存在，是否覆盖?</font><html>", "是否覆盖?",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			System.out.println(overwriteSelect);
			if (overwriteSelect == JOptionPane.YES_OPTION) {

				List<String> aList = PrintObjectExcel.printExcel(caList,
						filetable, header);
				if (aList.size() == 0) {
					JOptionPane.showMessageDialog(this, "导出成功", "提示信息",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String str = "";
					for (String item : aList) {
						str += item + "\n";
					}
					JOptionPane.showMessageDialog(this, str, "错误信息",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				return;
			}
		}
		List<String> aList = PrintObjectExcel.printExcel(caList, filetable,
				header);
		if (aList.size() == 0) {
			JOptionPane.showMessageDialog(this, "导出成功", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			String str = "";
			for (String item : aList) {
				str += item + "\n";
			}
			JOptionPane.showMessageDialog(this, str, "错误信息",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		JFileChooser fDialog = new JFileChooser();
		// 初始化ExcelFileFilter文件类型过滤类
		ExcelFileFilter excelFilter = new ExcelFileFilter();
		// 设置文本框类型
		fDialog.addChoosableFileFilter(excelFilter);
		// 设置文件选择框的标题
		fDialog.setFileFilter(excelFilter);
		// 浏览文件
		fDialog.setDialogTitle("请选择文件");
		// 弹出选择框
		int returnVal = fDialog.showOpenDialog(null);
		// 定义table变量用来存储要浏览文件的路径
		String table = fDialog.getSelectedFile().toString();
		//		System.out.println(table);
		// 初始化office_List数组,把判定的错误信息放在office_List数组列表里
		List<String> buliding_List = GGC_BuildingAddTable
				.getBuildingThrow(table);
		//		System.out.println(department_List);
		// 判断office_List的错误信息个数,如果为空则导入成功,刷新页面
		if (buliding_List.size() == 0 || buliding_List == null) {
			JOptionPane.showMessageDialog(this, "导入成功", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			fillbuilding();
		} else {
			// 首先定义一个字符串变量为str
			String str = "";
			// 遍历数组列表错误信息
			for (String item : buliding_List) {
				// 把遍历过的错误信息,存放到str变量当中
				str += item + "\n";
			}
			// 提示信息：把错误信息输出到前台页面当中！
			JOptionPane.showMessageDialog(this, str, "提示信息",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// 记录jtble1表格当前的行
		int i = jTable1.getSelectedRow();
		// 如果i==-1，说明没有选中行
		if (i == -1) {
			// 提示信息：请选中要修改的记录
			JOptionPane.showMessageDialog(this, "请选中要修改的记录", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// 如果jTextField2文本框中没有输入内容
		if (jTextField2.getText().equals("")) {
			// 提示信息:请输入教学楼名称
			JOptionPane.showMessageDialog(this, "请输入教学楼名称！", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField2.requestFocus();
			return;
		}
		if (jTextField4.getText().equals("")) {
			// 提示信息:请输入教学楼地址
			JOptionPane.showMessageDialog(this, "请输入教学楼地址！", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField4.requestFocus();
			return;
		}
		// 定义一个变量来存放jtable1的第i行第0列的字符串
		int b_id = (Integer) jTable1.getValueAt(i, 0);
		// 定义一个变量来存放jtable1的第i行第1列的字符串
		String b_name = (String) jTable1.getValueAt(i, 1);
		// 定义一个变量来存放jtable1的第i行第2列的字符串
		String b_alias = (String) jTable1.getValueAt(i, 2);
		// 定义一个变量来存放jtable1的第i行第3列的字符串
		String b_address = (String) jTable1.getValueAt(i, 3);
		// 定义一个b_id_ggc变量用来得到jTextField1文本框的内容
		int b_id_ggc = Integer.parseInt(jTextField1.getText());
		// 定义一个b_name_ggc用来得到jTextField2文本框的内容
		String b_name_ggc = jTextField2.getText();
		// 定义一个b_name_ggc用来得到jTextField2文本框的内容
		String b_alias_ggc = jTextField3.getText();
		// 定义一个b_name_ggc用来得到jTextField2文本框的内容
		String b_address_ggc = jTextField4.getText();
		// 声明数据库为空
		Connection con = null;
		try {
			// 创建数据库连接
			con = Databaseconnection.getconnection();
			//设置数据库的自动提交方式为false
			con.setAutoCommit(false);
			// 如果当前的文本框的内容和选中的对应的列的值不匹配的时,说明用户要修改信息！
			if (!(b_id_ggc == b_id)) {
				// 访问数据库查询教学楼编号字段值,如果当前数据库对应的字段里面的值等于用户当前已经输入的值的情况下
				if (Buildingaccess.findid(con, b_id_ggc)) {
					// 提示信息：教学楼编号已经存在
					JOptionPane.showMessageDialog(this, "教学楼编号已经存在请重新输入",
							"错误信息", JOptionPane.ERROR_MESSAGE);
					// jTextField1文本框获得焦点
					jTextField1.requestFocus();
					// jtexfField1文本框全选
					jTextField1.selectAll();
					return;
				}
			}
			if (!(b_name_ggc.equals(b_name))) {
				if (Buildingaccess.findname(con, b_name_ggc)) {
					// 提示信息：教学楼名称已经存在
					JOptionPane.showMessageDialog(this, "教学楼名称已经存在请重新输入",
							"错误信息", JOptionPane.ERROR_MESSAGE);
					// jTextField1文本框获得焦点
					jTextField2.requestFocus();
					// jtexfField1文本框全选
					jTextField2.selectAll();
					return;
				}
			}
			// 判定当前的输入的b_id、b_alias和b_name与数据库里面的d_id、b_alias和d_name是否重复,如果重复则提示信息
			if ((b_id_ggc == b_id) && (b_name_ggc.equals(b_name))
					&& (b_alias_ggc.equals(b_alias))
					&& (b_address_ggc.equals(b_address))) {
				// 提示信息:请输入您要修改的信息！
				JOptionPane.showMessageDialog(this, "请输入您要修改的信息！", "提示信息",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int r = Buildingaccess.update(con, b_id, new Buliding(b_id_ggc,
					jTextField2.getText(), b_alias_ggc, b_address_ggc));
			// 如果结果集大于0的话
			if (r > 0) {
				// 提示信息修改成功
				JOptionPane.showMessageDialog(this, "修改成功", "提示信息",
						JOptionPane.INFORMATION_MESSAGE);
				jTextField1.setText("");
				jTextField2.setText("");
				//开启数据库手动提交方式
				con.commit();
				// 调用departmenttable方法进行实时刷新表格
				fillbuilding();
			} else {
				// 否则提示信息：修改失败请联系管理员
				JOptionPane.showMessageDialog(this, "修改失败请联系管理员", "提示信息",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				//如果数据库访问异常,则回滚
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
		//记录jtble1面板当前的行数
		int i = jTable1.getSelectedRow();
		jTable1.setRowSelectionAllowed(true);
		String ggcString = "";
		String ggc = "";
		int b_id = 0;
		int number = 0;
		//如果i==-1，说明没有选中行
		if (i == -1) {
			//提示信息：请选中要修改的记录
			JOptionPane.showMessageDialog(this, "请选中要删除的记录", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else {
			//定义一个数组变量row存放当前选中的所有行
			int[] row = jTable1.getSelectedRows();
			//定义一个count用来存放行数
			int count = row.length;
			//遍历行数
			for (int a = 0; a < count; a++) {
				//定义一个变量来存放部门编号的第row[a]行第0列
				b_id = (Integer) jTable1.getValueAt(row[a], 0);
				String st = ",";
				ggc = "(" + (ggcString += b_id + (st)) + ")";
				//定义一个空字符串变量st
				//用来存放满足删除多条语句要求的sql语句
				//						number = Integer.parseInt(zzt);
				if (ggc.endsWith(")")) {
					ggc = ggc.substring(0, ggc.length() - 2) + ")";
				}
			}

			//提示信息,确认要删除当前记录嘛？,如果确定，则删除当前选中的记录
			int r = JOptionPane.showConfirmDialog(this, "确认要删除当前记录嘛？", "确认信息",
					JOptionPane.OK_CANCEL_OPTION);
			if (r == 0) {
				Connection con = null;
				try {
					//创建数据库连接
					con = Databaseconnection.getconnection();
					//设置数据库连接的自动提交为(false)
					con.setAutoCommit(false);
					//定义一个整型变量re来存放结果集
					int re;
					//用re来记录删除部门编号的方法的结果集
					re = Buildingaccess.Delete(con, ggc);
					//如果结果集  > 0 则删除成功
					if (re > 0) {
						JOptionPane.showMessageDialog(this, "删除记录成功！", "确认信息",
								JOptionPane.INFORMATION_MESSAGE);
						//开启数据库的手动提交
						con.commit();
						//实时刷新jtble1表格数据
						fillbuilding();
						jTextField1.setText("");
						jTextField2.setText("");
					} else {
						JOptionPane.showMessageDialog(this,
								"删除失败，请联系管理员进行相关操作！", "错误信息",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//捕获错误异常情况进行处理,定义一个str变量,用来存放访问sql语句错误的状态值
					String str = "23000";
					//如果当前访问的状态值等于str,则提示相应信息
					if (e.getSQLState().equals(str)) {
						JOptionPane.showMessageDialog(this,
								"由于此编号有外键约束影响,暂时不能删除！", "提示信息",
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
			// 提示信息：教学楼名称不能为空！
			JOptionPane.showMessageDialog(this, "教学楼名称不能为空！");
			jTextField2.requestFocus();
			return;
		}
		if (b_address.equals("")) {
			// 提示信息：教学楼名称不能为空！
			JOptionPane.showMessageDialog(this, "教学楼地址不能为空！");
			jTextField4.requestFocus();
			return;
		}
		Connection con = null;
		int zhujian = 0;
		int b_int = 0;
		try {
			con = Databaseconnection.getconnection();
			//把查询教学楼编号的最大值存入max的结果集
			ResultSet max = Buildingaccess.findmax(con);
			//遍历结果集的所有数据找到最大值赋值给zhujian变量
			while (max.next()) {
				zhujian = max.getInt("b_id");
			}
			//如果教学楼编号没用数据,则添加一条编号为“01”的数据
			if (zhujian == 0) {
				b_int = 1;
			} else {
				//定义一个整形变量,从来存放当前教学楼编号里面的最大值加一
				b_int = (zhujian) + 1;
				//在把已经加过一的整形变量转换为字符串放在b_int变量里
			}
			//实例化部门的构造方法
			Buliding bu = new Buliding(b_int, b_name, b_alias, b_address);
			// 创建数据库连接
			// 设置数据库的自动提交方式为false
			con.setAutoCommit(false);
			if (Buildingaccess.findname(con, b_name)) {
				// 提示信息：教学楼名称已经存在
				JOptionPane.showMessageDialog(this, "教学楼名称已经存在请重新输入", "错误信息",
						JOptionPane.ERROR_MESSAGE);
				// jTextField2文本框获得焦点
				jTextField2.requestFocus();
				// jtexfField2文本框全选
				jTextField2.selectAll();
				return;
			}
			// 定义r变量存放结果集
			int r = Buildingaccess.insert(con, bu);
			// 如果r>=1
			if (r >= 1) {
				// 则提示信息：添加成功！
				JOptionPane.showMessageDialog(this, "添加成功！");
				// 开始数据库的手动提交方式
				con.commit();
				fillbuilding();
				jTextField1.setText("");
				jTextField2.setText("");
				jTextField3.setText("");
				jTextField4.setText("");
			}
			// 如果r<1
			if (r < 1) {
				// 则提示信息部门编号插入失败，请联系系统管理员！
				Tools.connectionroolback(con, "教学楼编号插入失败，请联系系统管理员！");
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
		//调用导出方法把文件的路径存放在tabletwo变量里
		File Filename = Fileselection.exportselect();
		File filenametow = new File(Filename + ".xls");
		String filetable = filenametow.toString();
		//		 System.out.println(Filename+".xls");
		String o_id = null;
		//实例化部门列表，把部门信息存放在calist变量中
		ArrayList<Buliding> caList = Buildingaccess.getbuliding(b_id);
		List<Buliding> list = new ArrayList<Buliding>();
		//设置excel表头信息
		String[] header = { "教学楼编号", "教学楼名称", "教学楼别名", "教学楼地址" };
		//		 File Filename =new File() ;
		//		 System.out.println(Filename);
		//		
		JFileChooser fDialog = new JFileChooser();
		if (Filename == null) {
			//			 System.out.println("文件名为null");
			return;
		}
		//怎么拿到导出之后的文件名
		if (filenametow.exists()) {
			System.out.println(filenametow.exists());
			int overwriteSelect = JOptionPane.showConfirmDialog(this,
					"<html><font size=3>文件" + filenametow.getName()
							+ "已存在，是否覆盖?</font><html>", "是否覆盖?",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			System.out.println(overwriteSelect);
			if (overwriteSelect == JOptionPane.YES_OPTION) {

				List<String> aList = PrintObjectExcel.printExcel(caList,
						filetable, header);
				if (aList.size() == 0) {
					JOptionPane.showMessageDialog(this, "导出成功", "提示信息",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String str = "";
					for (String item : aList) {
						str += item + "\n";
					}
					JOptionPane.showMessageDialog(this, str, "错误信息",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				return;
			}
		}
		List<String> aList = PrintObjectExcel.printExcel(caList, filetable,
				header);
		if (aList.size() == 0) {
			JOptionPane.showMessageDialog(this, "导出成功", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			String str = "";
			for (String item : aList) {
				str += item + "\n";
			}
			JOptionPane.showMessageDialog(this, str, "错误信息",
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