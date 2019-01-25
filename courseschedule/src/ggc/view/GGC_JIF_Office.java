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
		//声明DefaultTableModel 得到jtble1表格上的模型
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		//设置表格的行数从0开始计数
		dtm.setRowCount(0);
		Department d = (Department) jComboBox1.getSelectedItem();
		// 如果为空，则退出
		if (d == null) {
			return;
		}

		//初始化ArrayList列表，通过d_id主键查询department（部门表）
		ArrayList<View_office> aList = Viewofficeaccess.getoffice(d.getD_id());
		if (aList.size() == 0)
			return;
		// 遍历部门的数组列表
		for (int i = 0; i < aList.size(); i++) {
			// 取出对应字段信息
			String d_name = aList.get(i).getD_name();
			String o_id = aList.get(i).getO_id();
			String o_name = aList.get(i).getO_name();
			// 生成向量类型变量
			Vector v = new Vector();
			// 将取得数据添加到向量中
			v.add(d_name);
			v.add(o_id);
			v.add(o_name);
			// 将向量添加到表格中
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
		"部门名称", "科室编号", "科室名称"
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
		//调用导出方法把文件的路径存放在tabletwo变量里
		File Filename = Fileselection.exportselect();
		File filenametow = new File(Filename + ".xls");
		String filetable = filenametow.toString();
		//		 System.out.println(Filename+".xls");
		String d_id = null;
		//实例化部门列表，把部门信息存放在calist变量中
		ArrayList<Office> caList = Officeaccess.getoffice(o_id);
		List<Office> list = new ArrayList<Office>();
		//设置excel表头信息
		String[] header = { "科室编号", "部门编号", "科室名称" };
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

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//调用导出方法把文件的路径存放在tabletwo变量里
		File Filename = Fileselection.exportselect();
		File filenametow = new File(Filename + ".xls");
		String filetable = filenametow.toString();
		//		 System.out.println(Filename+".xls");
		String o_id = null;
		//实例化部门列表，把部门信息存放在calist变量中
		ArrayList<View_office> caList = Viewofficeaccess.getoffice(o_id);
		List<View_office> list = new ArrayList<View_office>();
		//设置excel表头信息
		String[] header = { "部门编号", "部门名称", "科室编号", "科室名称" };
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
		// //得到当前jTextField2文本框
		String o_name_ggc = jTextField2.getText();
		// 如果当前的文本框内容为空
		//		if (o_id_ggc.equals("")) {
		//			//提示信息：科室编号不能为空！
		//			JOptionPane.showMessageDialog(this, "科室编号不能为空！");
		//			jTextField1.requestFocus();
		//			return;
		//		}
		// 如果部门名称文本框内容为空
		if (o_name_ggc.equals("")) {
			//提示信息：科室名称不能为空！
			JOptionPane.showMessageDialog(this, "科室名称不能为空！");
			jTextField2.requestFocus();
			return;
		}
		if (jcbox1.equals(null)) {
			JOptionPane.showMessageDialog(this, "请选中部门的名称！");
			jTextField1.requestFocus();
			return;
		}
		// 实例化Department类
		//		Office of = new Office(o_id_ggc, ggc_did, o_name_ggc);
		Connection con = null;
		String zhujian = null;
		String d_str = "";
		try {
			// 创建数据库连接
			con = Databaseconnection.getconnection();
			con.setAutoCommit(false);
			ResultSet max = Officeaccess.findmax(con);
			//遍历结果集的所有数据找到最大值赋值给zhujian变量
			while (max.next()) {
				zhujian = max.getString("o_id");
			}
			//如果部门编号没用数据,则添加一条编号为“01”的数据
			if (zhujian == null) {
				d_str = "0901";
			} else {
				//定义一个整形变量,从来存放当前部门编号里面的最大值加一
				int d_id_int = Integer.parseInt(zhujian) + 1;
				//在把已经加过一的整形变量转换为字符串放在d_str变量里
				d_str = "0" + Integer.toString(d_id_int);
				//实例化部门的构造方法
			}
			//实例化部门的构造方法
			Office dt = new Office(d_str, ggc_did, o_name_ggc);
			//设置数据库的自动提交方式为false

			// 访问数据库查询科室编号字段值,如果当前数据库对应的字段里面的值等于用户当前已经输入的值的情况下
			//			if (Officeaccess.findid(con, o_id_ggc)) {
			//				// 提示信息：类型编号已经存在
			//				JOptionPane.showMessageDialog(this, "科室编号已经存在请重新输入", "错误信息",
			//						JOptionPane.ERROR_MESSAGE);
			//				// jTextField1文本框获得焦点
			//				jTextField1.requestFocus();
			//				// jtexfField1文本框全选
			//				jTextField1.selectAll();
			//				return;
			//			}
			// 访问数据库查询o_name字段值,如果当前数据库对应的o_name字段里面的值等于用户当前已经输入的值的情况下
			if (Officeaccess.findname(con, o_name_ggc)) {
				// 提示信息：类型名称已经存在
				JOptionPane.showMessageDialog(this, "科室名称已经存在请重新输入", "错误信息",
						JOptionPane.ERROR_MESSAGE);
				// jTextField1文本框获得焦点
				jTextField2.requestFocus();
				// jtexfField1文本框全选
				jTextField2.selectAll();
				return;
			}

			// 定义r变量存放结果集
			int r = Officeaccess.insert(con, dt);
			//如果r>=1
			if (r >= 1) {
				//则提示信息：添加成功！
				JOptionPane.showMessageDialog(this, "添加成功！");
				//开始数据库的手动提交方式
				con.commit();
				officetable();
				jTextField1.setText("");
				jTextField2.setText("");
			}
			//如果r<1
			if (r < 1) {
				//则提示信息科室编号插入失败，请联系系统管理员！
				Tools.connectionroolback(con, "科室编号插入失败，请联系系统管理员！");
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
		List<String> office_List = GGC_OfficeAddTable.getOfficeThrow(table);
		//		System.out.println(department_List);
		// 判断office_List的错误信息个数,如果为空则导入成功,刷新页面
		if (office_List.size() == 0 || office_List == null) {
			JOptionPane.showMessageDialog(this, "导入成功", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			officetable();
		} else {
			// 首先定义一个字符串变量为str
			String str = "";
			// 遍历数组列表错误信息
			for (String item : office_List) {
				// 把遍历过的错误信息,存放到str变量当中
				str += item + "\n";
			}
			// 提示信息：把错误信息输出到前台页面当中！
			//			JOptionPane.showMessageDialog(this, str, "提示信息",
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
		// 记录jtble1表格当前的行
		int i = jTable1.getSelectedRow();
		// 如果i==-1，说明没有选中行
		if (i == -1) {
			// 提示信息：请选中要修改的记录
			JOptionPane.showMessageDialog(this, "请选中要修改的记录", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// 如果jTextField1文本框中没有输入内容
		if (jTextField1.getText().equals("")) {
			// 提示信息:请输入科室编号
			JOptionPane.showMessageDialog(this, "请输入科室编号！", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField1.requestFocus();
			return;
		}

		// 如果jTextField2文本框中没有输入内容
		if (jTextField2.getText().equals("")) {
			// 提示信息:请输入科室名称
			JOptionPane.showMessageDialog(this, "请输入科室名称！", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField2.requestFocus();
			return;
		}
		// 定义一个变量来存放jtable1的第i行第0列的字符串
		Department jcbox1 = (Department) jComboBox1.getSelectedItem();
		String ggc_did = jcbox1.getD_id();
		String o_id = (String) jTable1.getValueAt(i, 1);
		// 定义一个变量来存放jtable1的第i行第1列的字符串
		String o_name = (String) jTable1.getValueAt(i, 2);
		// 定义一个o_id_ggc变量用来得到jTextField1文本框的内容
		String o_id_ggc = jTextField1.getText();
		// 定义一个o_name_ggc用来得到jTextField2文本框的内容
		String o_name_ggc = jTextField2.getText();
		// 声明数据库为空
		Connection con = null;
		try {
			// 创建数据库连接
			con = Databaseconnection.getconnection();
			//设置数据库的自动提交方式为false
			con.setAutoCommit(false);
			// 如果当前的文本框的内容和选中的对应的列的值不匹配的时,说明用户要修改信息！
			if (!(o_id_ggc.equals(o_id))) {
				// 访问数据库查询科室编号字段值,如果当前数据库对应的字段里面的值等于用户当前已经输入的值的情况下
				if (Officeaccess.findid(con, o_id_ggc)) {
					// 提示信息：类型编号已经存在
					JOptionPane.showMessageDialog(this, "科室编号已经存在请重新输入",
							"错误信息", JOptionPane.ERROR_MESSAGE);
					// jTextField1文本框获得焦点
					jTextField1.requestFocus();
					// jtexfField1文本框全选
					jTextField1.selectAll();
					return;
				}
			}
			if (!(o_name_ggc.equals(o_name))) {
				if (Officeaccess.findname(con, o_name_ggc)) {
					// 提示信息：科室名称已经存在
					JOptionPane.showMessageDialog(this, "科室名称已经存在请重新输入",
							"错误信息", JOptionPane.ERROR_MESSAGE);
					// jTextField1文本框获得焦点
					jTextField2.requestFocus();
					// jtexfField1文本框全选
					jTextField2.selectAll();
					return;
				}
			}
			// 判定当前的输入的o_id和o_name与数据库里面的o_id和o_name是否重复,如果重复则提示信息
			if ((o_id_ggc.equals(o_id)) && (o_name_ggc.equals(o_name))) {
				// 提示信息:请输入您要修改的信息！
				JOptionPane.showMessageDialog(this, "请输入您要修改的信息！", "提示信息",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			// 定义一个变量r用来存储update方法返回的结果集
			int r = Officeaccess.update(con, o_id, new Office(o_id_ggc,
					ggc_did, jTextField2.getText()));
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
				officetable();
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

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//记录jtble1面板当前的行数
		int i = jTable1.getSelectedRow();
		jTable1.setRowSelectionAllowed(true);
		String ggcString = "";
		String ggc = "";
		//如果i==-1，说明没有选中行
		if (i == -1) {
			//提示信息：请选中要删除的记录
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
				//定义一个变量来存放科室编号的第row[a]行第0列
				String o_id = (String) jTable1.getValueAt(row[a], 1);
				//定义一个空字符串变量st
				String st = ",";
				//用来存放满足删除多条语句要求的sql语句
				ggc = "(" + (ggcString += "'" + o_id + "'" + (st)) + ")";
				//如果sql语句的最后一个字符是“)”则删除最后两位字符在加上一个“)”
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
					//用re来记录删除科室编号的方法的结果集
					re = Officeaccess.Delete(con, ggc);
					//如果结果集  > 0 则删除成功
					if (re > 0) {
						JOptionPane.showMessageDialog(this, "删除记录成功！", "确认信息",
								JOptionPane.INFORMATION_MESSAGE);
						//开启数据库的手动提交
						con.commit();
						//实时刷新jtble1表格数据
						officetable();
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