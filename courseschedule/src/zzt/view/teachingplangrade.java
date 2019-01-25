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
	 * 构造界面
	 * 
	 * @author zzt
	 * @param vt
	 *            ：登录用户的视图信息
	 */
	public teachingplangrade(View_teacher vt) {
		// 初始化界面
		initComponents();
		// 将传递的参数（登录用户的视图信息）传递给类的对应属性字段
		this.vt = vt;
		// 调用函数初始化学院下拉列表
		Fillcombobox.filldepartmentadd(d_id, jComboBox1);
		// 调用类的静态函数初始化科室下拉列表
		//		Fillcombobox.filloffice(o_id, jComboBox2);
		// 调用类的静态函数初始化学期下拉列表
		//		Fillcombobox.fillmajor(m_id, jComboBox3);
		//		Fillcombobox.fillteachingplan(tp_id, jComboBox4);
		//		jComboBox1.addItem(new Department("-1",""));
		jComboBox1.setSelectedItem(null);
		jComboBox2.setSelectedItem(null);
		jComboBox3.setSelectedItem(null);
		jComboBox4.setSelectedItem(null);
	}

	/**
	 * 初始化学院下拉列表
	 * 
	 * @author zzt
	 */
	private void fillcomboboxdepartment() {
		// 如果登录的权限为系部排课或部门管理，则学院下拉列表的内容之初始化为登录用户所在学院
		if (vt.getT_power().equals("系部排课") || vt.getT_power().equals("部门管理")) {
			Department dm = new Department(vt.getD_id(), vt.getD_name());
			jComboBox1.addItem(dm);
		}
		// 如果权限为学校管理，则学院下拉列表调用类的静态函数初始化为全部学院
		else if (vt.getT_power().equals("学校管理")) {
			Fillcombobox.filldepartment(jComboBox1);
		}
	}

	private void teachingplan() {
		//首先给变量设为空值
		String d_id = null;
		//声明DefaultTableModel 得到jtble1表格上的模型
		DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
		//设置表格的行数从0开始计数
		dtm.setRowCount(0);
		// 取得部门下拉列表选择内容
		Department dm = (Department) jComboBox1.getSelectedItem();
		// 如果为空，则退出
		if (dm == null)
			return;
		// 取得科室下拉列表选择内容
		Office of = (Office) jComboBox2.getSelectedItem();
		// 如果为空，则退出
		if (of == null)
			return;
		Major mj = (Major) jComboBox3.getSelectedItem();
		// 如果为空，则退出
		if (mj == null)
			return;
		Teachingplan tp = (Teachingplan) jComboBox4.getSelectedItem();
		if (tp == null)
			return;
		//初始化ArrayList列表，通过部门编号主键查询department（部门表）
		ArrayList<Teachingplangrade> aList = Teachingplangradeaccess
				.getTeachingplangrade(tp.getTp_id());
		System.out.println(aList);
		// 遍历部门的数组列表
		for (int i = 0; i < aList.size(); i++) {
			// 取出对应字段信息
			String tp_id = ((Teachingplan) jComboBox4.getSelectedItem())
					.getTp_id();
			String tp_name = ((Teachingplan) jComboBox4.getSelectedItem())
					.getTp_name();
			String m_name = ((Major) jComboBox3.getSelectedItem()).getM_name();
			
			String tg_grade = aList.get(i).getTg_grade();
			// 生成向量类型变量
			Vector v = new Vector();
			// 将取得数据添加到向量中
			v.add(tp_id);
			v.add(tp_name);
			v.add(m_name);
			v.add(tg_grade);
			// 将向量添加到表格中
			dtm.addRow(v);
		}
	}

	private void teachinggrade() {
		//首先给变量设为空值
		String d_id = null;
		//声明DefaultTableModel 得到jtble1表格上的模型
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		//设置表格的行数从0开始计数
		dtm.setRowCount(0);
		// 取得部门下拉列表选择内容
		Department dm = (Department) jComboBox1.getSelectedItem();
		// 如果为空，则退出
		if (dm == null)
			return;
		// 取得科室下拉列表选择内容
		Office of = (Office) jComboBox2.getSelectedItem();
		// 如果为空，则退出
		if (of == null)
			return;
		Major mj = (Major) jComboBox3.getSelectedItem();
		// 如果为空，则退出
		if (mj == null)
			return;
		Teachingplan tp = (Teachingplan) jComboBox4.getSelectedItem();
		if (tp == null)
			return;
		//初始化ArrayList列表，通过部门编号主键查询department（部门表）
		ArrayList<View_classinformation> aList = Classinformationtwoaccess
				.getView_classinformationgrade(mj.getM_id());
		// 遍历部门的数组列表
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
			//			// 将取得数据添加到向量中
			v.add(item);
			// // 将向量添加到表格中
			dtm.addRow(v);
			//			System.out.println(dtm);
			//			System.out.println(v);
		}
		//		for (int i = 0; i < list1.size(); i++) {
		//			cc = aList.get(i).getCla_grade();
		//			// 生成向量类型变量
		//			System.out.println(aList);
		//			Vector v = new Vector();
		//			//			// 将取得数据添加到向量中
		//			v.add(cc);
		//			// // 将向量添加到表格中
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
		"年级"
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
		"方案编号", "方案名称", "专业名称", "年级"
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
				//调用导出方法把文件的路径存放在tabletwo变量里
				File Filename = Fileselection.exportselect();
				File filenametow = new File(Filename + ".xls");
				String filetable = filenametow.toString();
				String cr_id = null;
				Major mj = (Major) jComboBox3.getSelectedItem();
				Teachingplan tp = (Teachingplan) jComboBox4.getSelectedItem();
				// 如果为空，则退出
				if (mj == null)
					return;
				ArrayList<View_classinformation> abList = Classinformationtwoaccess
						.getView_classinformationgrade(mj.getM_id());
				// 遍历部门的数组列表
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
				//实例化部门列表，把部门信息存放在calist变量中
				list1.addAll(list2);
				teachingplan();
				List<Teachingplangrade> caList = gradelist;
				System.out.println(abList);
				//设置excel表头信息
				String[] header = { "教室编号", "部门编号", "教师名称", "教室类型", "教室人数", "教学楼编号" };
				if (Filename == null) {
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

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		//记录jtble1面板当前的行数
		int i = jTable2.getSelectedRow();
		jTable2.setRowSelectionAllowed(true);
		String zztString = "";
		String zzt = "";
		//如果i==-1，说明没有选中行
		if (i == -1) {
			//提示信息：请选中要修改的记录
			JOptionPane.showMessageDialog(this, "请选中要删除的记录", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else {
			//定义一个数组变量row存放当前选中的所有行
			int[] row = jTable2.getSelectedRows();
			//定义一个count用来存放行数
			int count = row.length;
			//遍历行数
			for (int a = 0; a < count; a++) {
				//定义一个变量来存放部门编号的第row[a]行第0列
				String grade = (String) jTable2.getValueAt(row[a], 3);
				//定义一个空字符串变量st
				String st = ",";
				//用来存放满足删除多条语句要求的sql语句
				zzt = "(" + (zztString += "'" + grade + "'" + (st)) + ")";
				//如果sql语句的最后一个字符是“)”则删除最后两位字符在加上一个“)”
				if (zzt.endsWith(")")) {
					zzt = zzt.substring(0, zzt.length() - 2) + ")";
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
					re = Teachingplangradeaccess.Delete(con, zzt);
					//如果结果集  > 0 则删除成功
					if (re > 0) {
						JOptionPane.showMessageDialog(this, "删除记录成功！", "确认信息",
								JOptionPane.INFORMATION_MESSAGE);
						//开启数据库的手动提交
						con.commit();
						//实时刷新jtble1表格数据
					} else {
						JOptionPane.showMessageDialog(this,
								"删除失败，请联系管理员进行相关操作！", "错误信息",
								JOptionPane.ERROR_MESSAGE);
					}
					teachinggrade();
					teachingplan();
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
		// 如果不等于2则退出函数执行(鼠标双击)
		if (clicked != 1)
			return;
		// 获得教学任务表格中选中的行数信息
		int rows[] = jTable2.getSelectedRows();
		//		int rowss[] = jTable1.getSelectedRows();
		// 如果没有选中行则退出函数执行
		if (rows.length > 1)
			return;
		String tp_id = "";
		String cle_grade = "";
		tp_id = (String) jTable2.getValueAt(rows[0], 0);

		System.out.println(tp_id);

		// 获取选中的第一行的教学任务编号信息

		// 生成教学任务数据表查询条件
		//		String condition = "tp_id=" + tp_id;
		// 如果此课程已经排课，则不能进行本操作
		//		if (isSchedulingCourse(tp_id)) {
		//			JOptionPane.showMessageDialog(this, "课程任务编号为" + tt_id
		//					+ "的课程已经进行了排课，不能完成本操作！");
		//			return;
		//		}
		// 刷新教学任务表格
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
		//得到科室下拉列表的内容
		Office of = (Office) jComboBox2.getSelectedItem();
		//如果为空则返回
		if (of == null)
			return;
		String o_id = of.getO_id();
		//调用静态方法通过教学计划编号得到当前专业名称
		Fillcombobox.fillmajor(o_id, jComboBox3);
		jComboBox4.setSelectedItem(null);

	}

	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		// TODO add your handling code here:
		// TODO add your handling code here:
		//得到部门下拉列表的内容
		Department dm = (Department) jComboBox1.getSelectedItem();
		//如果为空则返回
		if (dm == null)
			return;
		String d_id = dm.getD_id();
		//调用静态方法通过部门编号得到当前专业名称
		Fillcombobox.filloffice(d_id, jComboBox2);
		//设置专业下拉列表为空
		jComboBox3.setSelectedItem(null);
		jComboBox4.setSelectedItem(null);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// 获取课程表格中选中的行
		int[] rows = jTable1.getSelectedRows();
		// 遍历选中的行
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
			//				 取得数据库的连接
			con = Databaseconnection.getconnection();
			con.setAutoCommit(false);
			//				 将教学任务对象插入到数据库教学任务表中
			int r = Teachingplangradeaccess.insert(con, tp);
			// 如果插入成功
			if (r >= 1) {
				JOptionPane.showMessageDialog(this, "插入成功");

			} else {// 如果插入失败，则调用方法进行回滚
				String msg = "教学计划数据插入失败，请联系系统管理员！";
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