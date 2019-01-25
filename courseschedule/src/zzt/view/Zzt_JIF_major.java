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
 *专业管理界面
 *		  dt：
 *			Major对象
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
		//初始化界面
		initComponents();
	}

	public Zzt_JIF_major(View_teacher mj) {
		//初始化界面
		initComponents();
		// 将传递的参数（登录用户的视图信息）传递给类的对应属性字段
		this.mj = mj;
		// 调用类的静态函数初始化部门下拉列表
		Fillcombobox.filldepartmentadd(d_id, jComboBox2);
		// 调用类的静态函数初始化科室下拉列表
		//		Fillcombobox.filloffice(o_id, jComboBox1);
		//		jComboBox1.addItem(new Department("", ""));
		jComboBox2.setSelectedItem(null);
	}

	/**
	 * 构造界面
	 * 
	 * @author zzt
	 * @param vt
	 *            ：登录用户的视图信息
	 */
	public void majortable() {
		//声明DefaultTableModel 得到jtble1表格上的模型
		DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
		//设置表格的行数从0开始计数
		dtm.setRowCount(0);
		//得到科室下拉列表内容
		Office o = (Office) jComboBox1.getSelectedItem();
		// 如果为空,则退出
		if (o == null)
			return;
		//得到科室下拉列表内容
		Department d = (Department) jComboBox2.getSelectedItem();
		// 如果为空,则推出
		if (d == null) {
			return;
		}

		//初始化ArrayList列表，通过o_id主键查询（专业表）
		ArrayList<View_major> aList = Viewmajoraccess.getMajor(o.getO_id());
		if (aList.size() == 0)
			return;
		// 遍历专业表的数组列表
		for (int i = 0; i < aList.size(); i++) {
			// 取出对应字段信息
			String d_name = aList.get(i).getD_name();
			String o_name = aList.get(i).getO_name();
			String m_id = aList.get(i).getM_id();
			String m_name = aList.get(i).getM_name();
			// 生成向量类型变量
			Vector v = new Vector();
			// 将取得数据添加到向量中
			v.add(d_name);
			v.add(o_name);
			v.add(m_id);
			v.add(m_name);
			// 将向量添加到表格中
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
		"部门名称", "科室名称", "专业编号", "专业名称"
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
		//实例化部门列表，把部门信息存放在calist变量中
		ArrayList<View_major> caList = Viewmajoraccess.getMajor(o_id);
		List<View_major> list = new ArrayList<View_major>();
		//设置excel表头信息
		String[] header = { "部门编号","部门名称", "科室编号","科室名称", "专业编号", "专业名称" };
		JFileChooser fDialog = new JFileChooser();
		if (Filename == null) {
			return;
		}
		//怎么拿到导出之后的文件名
		if (filenametow.exists()) {
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
		System.out.println(filetable);
		String o_id = null;
		//实例化部门列表，把部门信息存放在calist变量中
		List<Major> caList = Majoraccess.getMajor(o_id);
		List<Major> list = new ArrayList<Major>();
		//设置excel表头信息
		String[] header = { "专业编号", "科室编号", "专业名称" };
		JFileChooser fDialog = new JFileChooser();
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
		// 浏览文件
		fDialog.setDialogTitle("请选择文件");
		// 弹出选择框
		// 定义table变量用来存储要浏览文件的路径
		// 初始化department_List数组,把判定的错误信息放在department_List数组列表里
		List<String> department_List = MajorAddTable.getDepartmentThrow(table);
		// 判断department_List的错误信息个数,如果为空则导入成功,刷新页面
		if (department_List.size() == 0 || department_List == null) {
			JOptionPane.showMessageDialog(this, "导入成功", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			majortable();

		} else {
			// 首先定义一个字符串变量为str
			String str = "";
			// 遍历数组列表错误信息
			for (String item : department_List) {
				// 把遍历过的错误信息,存放到str变量当中
				str += item + "\n";
			}
			// 提示信息：把错误信息输出到前台页面当中！
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
	 * 删除专业管理信息
	 * 
	 * @author zzt
	 */
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//记录jtble1面板当前的行数
		int i = jTable1.getSelectedRow();
		jTable1.setRowSelectionAllowed(true);
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
			int[] row = jTable1.getSelectedRows();
			//定义一个count用来存放行数
			int count = row.length;
			//遍历行数
			for (int a = 0; a < count; a++) {
				//定义一个变量来存放部门编号的第row[a]行第0列
				String d_id = (String) jTable1.getValueAt(row[a], 1);
				//定义一个空字符串变量st
				String st = ",";
				//用来存放满足删除多条语句要求的sql语句
				zzt = "(" + (zztString += "'" + d_id + "'" + (st)) + ")";
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
					re = Majoraccess.Delete(con, zzt);
					//如果结果集  > 0 则删除成功
					if (re > 0) {
						JOptionPane.showMessageDialog(this, "删除记录成功！", "确认信息",
								JOptionPane.INFORMATION_MESSAGE);
						//开启数据库的手动提交
						con.commit();
						//实时刷新jtble1表格数据
						majortable();
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

	/**
	 * 修改专业管理信息
	 * 
	 * @author zzt
	 */
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
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
		// 如果专业编号文本框中没有输入内容
		if (jTextField1.getText().equals("")) {
			// 提示信息:请输入专业编号
			JOptionPane.showMessageDialog(this, "请输入专业编号！", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField1.requestFocus();
			return;
		}

		// 如果专业名称文本框中没有输入内容
		if (jTextField2.getText().equals("")) {
			// 提示信息:请输入专业名称
			JOptionPane.showMessageDialog(this, "请输入专业名称！", "提示信息",
					JOptionPane.INFORMATION_MESSAGE);
			jTextField2.requestFocus();
			return;
		}
		// 定义一个变量来存放jtable1的第i行第0列的字符串
		Office jcbox1 = (Office) jComboBox1.getSelectedItem();
		Department jcbox2 = (Department) jComboBox2.getSelectedItem();
		String zzt_oid = jcbox1.getO_id();
		String zzt_did = jcbox2.getD_id();
		String m_id = (String) jTable1.getValueAt(i, 2);
		// 定义一个变量来存放jtable1的第i行第1列的字符串
		String m_name = (String) jTable1.getValueAt(i, 3);
		// 定义一个d_id_zzt变量用来得到专业编号文本框的内容
		String m_id_zzt = jTextField1.getText();
		// 定义一个d_name_zzt用来得到专业名称文本框的内容
		String m_name_zzt = jTextField2.getText();
		// 声明数据库为空
		Connection con = null;
		try {
			// 创建数据库连接
			con = Databaseconnection.getconnection();
			//设置数据库的自动提交方式为false
			con.setAutoCommit(false);
			// 如果当前的文本框的内容和选中的对应的列的值不匹配的时,说明用户要修改信息！
			if (!(m_id_zzt.equals(m_id))) {
				// 访问数据库查询专业编号字段值,如果当前数据库对应的字段里面的值等于用户当前已经输入的值的情况下
				if (Majoraccess.findid(con, m_id_zzt)) {
					// 提示信息：专业编号已经存在
					JOptionPane.showMessageDialog(this, "专业编号已经存在请重新输入",
							"错误信息", JOptionPane.ERROR_MESSAGE);
					// jTextField1文本框获得焦点
					jTextField1.requestFocus();
					// jtexfField1文本框全选
					jTextField1.selectAll();
					return;
				}
			}
			if (!(m_name_zzt.equals(m_name))) {
				if (Majoraccess.findname(con, m_name_zzt)) {
					// 提示信息：专业名称已经存在
					JOptionPane.showMessageDialog(this, "专业名称已经存在请重新输入",
							"错误信息", JOptionPane.ERROR_MESSAGE);
					// jTextField1文本框获得焦点
					jTextField2.requestFocus();
					// jtexfField1文本框全选
					jTextField2.selectAll();
					return;
				}
			}
			// 判定当前的输入的专业编号和专业名称与数据库里面的是否重复,如果重复则提示信息
			if ((m_id_zzt.equals(m_id)) && (m_name_zzt.equals(m_name))) {
				// 提示信息:请输入您要修改的信息！
				JOptionPane.showMessageDialog(this, "请输入您要修改的信息！", "提示信息",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			// 定义一个变量r用来存储update方法返回的结果集
			int r = Majoraccess.update(con, m_id, new Major(m_id_zzt, zzt_oid,
					jTextField2.getText()));
			// 如果结果集大于0的话
			if (r > 0) {
				// 提示信息修改成功
				JOptionPane.showMessageDialog(this, "修改成功", "提示信息",
						JOptionPane.INFORMATION_MESSAGE);
				//清空专业编号的文本框
				jTextField1.setText("");
				//清空专业名称的文本框
				jTextField2.setText("");
				//开启数据库手动提交方式
				con.commit();
				// 调用majortable方法进行实时刷新表格
				majortable();
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

	/**
	 * 添加部门管理信息
	 * 
	 * @author zzt
	 */
	@SuppressWarnings("unused")
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:		// 取得部门下拉列表选择内容
		Department jcbox2 = (Department) jComboBox2.getSelectedItem();
		if (jcbox2 == null) {
			JOptionPane.showMessageDialog(this, "请选择部门名称", "错误信息",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String zzt_did = jcbox2.getD_id();
		// 取得科室下拉列表选择内容
		Office jcbox1 = (Office) jComboBox1.getSelectedItem();
		if (jcbox1 == null) {
			JOptionPane.showMessageDialog(this, "请选择科室名称", "错误信息",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String zzt_oid = jcbox1.getO_id();

		//获取当前专业编号的文本框的内容
		String d_id_zzt = jTextField1.getText();
		//获取当前专业名称的文本框的内容
		String d_name_zzt = jTextField2.getText();
		// 如果专业编号文本框内容为空
		if (zzt_did == null) {
			JOptionPane.showMessageDialog(this, "请选择学院名称");
			return;
		}
		//		System.out.println(zzt_did);
		//		System.out.println(zzt_oid);
		if (zzt_oid == null) {
			JOptionPane.showMessageDialog(this, "请选择部门名称");
			return;
		}
		if (d_id_zzt.equals("")) {
			//提示信息：专业编号不能为空！
			JOptionPane.showMessageDialog(this, "专业编号不能为空！");
			jTextField1.requestFocus();
			return;
		}
		// 如果专业名称文本框内容为空
		if (d_name_zzt.equals("")) {
			//提示信息：部门名称不能为空！
			JOptionPane.showMessageDialog(this, "专业名称不能为空！");
			jTextField2.requestFocus();
			return;
		}
		if (jComboBox2.equals("")) {
			JOptionPane.showMessageDialog(this, "请选中要添加部门的名称！");
			return;
		}
		if (jComboBox1.equals("")) {
			JOptionPane.showMessageDialog(this, "请选中要添加科室的名称！");
			return;
		}
		// 实例化Major类
		Major mj = new Major(d_id_zzt, zzt_oid, d_name_zzt);
		Connection con = null;
		try {
			// 创建数据库连接
			con = Databaseconnection.getconnection();
			//设置数据库的自动提交方式为false
			con.setAutoCommit(false);
			// 访问数据库查询专业编号字段值,如果当前数据库对应的字段里面的值等于用户当前已经输入的值的情况下
			if (Majoraccess.findid(con, d_id_zzt)) {
				// 提示信息：专业编号已经存在提示信息！
				JOptionPane.showMessageDialog(this, "专业编号已经存在请重新输入", "错误信息",
						JOptionPane.ERROR_MESSAGE);
				// jTextField1文本框获得焦点
				jTextField1.requestFocus();
				// jtexfField1文本框全选
				jTextField1.selectAll();
				return;
			}
			// 访问数据库查询专业名称字段值,如果当前数据库对应的专业名称字段里面的值等于用户当前已经输入的值的情况下
			if (Majoraccess.findname(con, d_name_zzt)) {
				// 提示信息：类型名称已经存在
				JOptionPane.showMessageDialog(this, "部门名称已经存在请重新输入", "错误信息",
						JOptionPane.ERROR_MESSAGE);
				// jTextField1文本框获得焦点
				jTextField2.requestFocus();
				// jtexfField1文本框全选
				jTextField2.selectAll();
				return;
			}

			// 定义r变量存放结果集
			int r = Majoraccess.insert(con, mj);
			//如果r>=1
			if (r >= 1) {
				//则提示信息：添加成功！
				JOptionPane.showMessageDialog(this, "添加成功！");
				//开始数据库的手动提交方式
				con.commit();
				//刷新表格
				majortable();
				//清空专业编号文本框内容
				jTextField1.setText("");
				//清空专业名称文本框内容
				jTextField2.setText("");
			}
			//如果r<1则提示信息部门编号插入失败，请联系系统管理员！
			if (r < 1) {
				Tools.connectionroolback(con, "部门编号插入失败，请联系系统管理员！");
				return;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				//如果失败则回滚
				con.rollback();
			} catch (SQLException e1) {

				//				if(jcbox2 == null){
				//
				//				}
				if (jcbox1 == null) {
					JOptionPane.showMessageDialog(this, "请选择部门名称", "错误信息",
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

	//显示科室列表对应的用户视图
	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		majortable();
	}

	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		//得到表格中的当前行
		int row = jTable1.getSelectedRow();
		//把表格中的第row行的第二列数据赋值给专业编号
		jTextField1.setText((String) jTable1.getValueAt(row, 2));
		//把表格中的第row行的第三列数据赋值给专业名称
		jTextField2.setText((String) jTable1.getValueAt(row, 3));
		//把表格中的第row行的第一列数据赋值给科室名称
		jComboBox1.setSelectedItem(new Office(null, null, (String) jTable1
				.getValueAt(row, 1)));
		//把表格中的第row行的第二列数据赋值给部门名称
		jComboBox2.setSelectedItem(new Department(null, (String) jTable1
				.getValueAt(row, 0)));
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		//退出专业管理界面
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