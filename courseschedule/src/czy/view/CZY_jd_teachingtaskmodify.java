/*
 * CZY_jd_teachingtaskmodify.java
 *
 * Created on __DATE__, __TIME__
 */

package czy.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import czy.dao.Courseexperimentalenvironmentaccess;
import czy.model.Tools;

import global.dao.Databaseconnection;
import global.dao.Teacheraccess;
import global.dao.Teachingtaskaccess;
import global.model.Department;
import global.model.Fillcombobox;
import global.model.Office;
import global.model.View_teacher;
import global.model.View_teachingtask;

/**
 * 
 * @author __USER__
 */
public class CZY_jd_teachingtaskmodify extends javax.swing.JDialog {
	private Department d;
	private Office o;
	private View_teachingtask tt;
	private View_teacher vt = null;

	/** Creates new form CZY_jd_teachingtaskmodify */
	public CZY_jd_teachingtaskmodify(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public CZY_jd_teachingtaskmodify(java.awt.Frame parent, boolean modal,
			View_teachingtask tt, Department d, Office o) {
		super(parent, modal);

		initComponents();
		// 对象的部门属性设置初值为传递进来的部门对象
		this.d = d;
		// 对象的科室属性设置初值为传递进来的科室对象
		this.o = o;
		// 对象的教学任务属性设置初值为传递进来的教学任务对象
		this.tt = tt;
		// 设置界面居中显示
		this.setLocationRelativeTo(null);
		// 文本框设置初值
		jTextField1.setText(tt.getCou_id());
		jTextField2.setText(tt.getCou_name());
		jTextField3.setText(tt.getCla_name());
		jTextField4.setText(tt.getCla_number() + "");
		// 如果实践课时大于零，实践课排课复选按钮状态设置为启用，
		if (tt.getCou_practicehour() > 0) {
			jCheckBox2.setEnabled(true);
		} else {
			// 否则实践课排课复选按钮状态设置为停用，
			jCheckBox2.setEnabled(false);
		}
		// 判定传递来的教学任务中实践课是否排课的值为“是”，则实践课排课复选按钮设置被选中
		if (tt.getPracticescheduling().equals("是"))
			jCheckBox2.setSelected(true);
		else {
			// 否则实践课排课复选按钮设置被未选中
			jCheckBox2.setSelected(false);
		}
		// 判定传递来的教学任务中多媒体教室的值为“是”，则对应复选按钮设为选中
		if (tt.getMultimedia().equals("是"))
			jCheckBox1.setSelected(true);
		else
			// 否则对应复选按钮设为未选中
			jCheckBox1.setSelected(false);
		// 获取传递来的教学任务中的教师编号
		String t_id = tt.getT_id();
		// 用获取得教师编号生成查询条件
		String condition = "t_id='" + t_id + "'";
		// 如果教师编号不等于-1
		if (!t_id.equals("-1")) {
			// 获取该教师信息
			ArrayList<View_teacher> al = Teacheraccess
					.getview_teacher(condition);
			vt = al.get(0);
			// 取得该教师的部门给对象的属性赋值
			this.d = new Department(vt.getD_id(), vt.getD_name());
			// 取得该教师的科室给对象的属性赋值
			this.o = new Office(vt.getO_id(), vt.getD_id(), vt.getO_name());
		}
		// 为部门下拉列表填充内容
		Fillcombobox.filldepartment(jComboBox1);
		// 设置部门选中的为对象的部门属性
		jComboBox1.setSelectedItem(this.d);

	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		buttonGroup2 = new javax.swing.ButtonGroup();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jTextField4 = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox();
		jCheckBox1 = new javax.swing.JCheckBox();
		jComboBox2 = new javax.swing.JComboBox();
		jCheckBox2 = new javax.swing.JCheckBox();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u6559\u5b66\u4efb\u52a1\u7f16\u8f91");
		setAlwaysOnTop(true);
		setResizable(false);

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder(javax.swing.BorderFactory
						.createTitledBorder(
								javax.swing.BorderFactory.createEtchedBorder(),
								"\u8bfe\u7a0b\u4fe1\u606f")));

		jLabel1.setText("\u8bfe\u7a0b\u7f16\u53f7\uff1a");

		jTextField1.setEditable(false);

		jLabel2.setText("\u8bfe\u7a0b\u540d\u79f0\uff1a");

		jTextField2.setEditable(false);

		jLabel3.setText("\u6388\u8bfe\u73ed\u7ea7\uff1a");

		jTextField3.setEditable(false);

		jLabel4.setText("\u73ed\u7ea7\u4eba\u6570\uff1a");

		jTextField4.setEditable(false);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jTextField1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				134,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabel2))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel4)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jTextField4)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jLabel3)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jTextField2)
														.addComponent(
																jTextField3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																224,
																Short.MAX_VALUE))
										.addContainerGap(17, Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabel1)
																		.addComponent(
																				jTextField1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel2)
																						.addComponent(
																								jTextField2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel3)
																						.addComponent(
																								jLabel4)
																						.addComponent(
																								jTextField4,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jTextField3,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createEtchedBorder(),
				"\u6559\u5e08\u3001\u6559\u5ba4\u4fe1\u606f"));

		jLabel5.setText("\u90e8\u95e8\uff1a");

		jComboBox1.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox1ItemStateChanged(evt);
			}
		});

		jLabel6.setText("\u7cfb\u90e8\uff1a");

		jLabel7.setText("\u6559\u5e08\uff1a");

		jCheckBox1.setText("\u591a\u5a92\u4f53\u6559\u5ba4");

		jComboBox2.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox2ItemStateChanged(evt);
			}
		});

		jCheckBox2.setText("\u5b9e\u8df5\u8bfe\u6392\u8bfe");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel5)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jComboBox1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				141,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel7)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jComboBox3,
																				0,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabel6)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jComboBox2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				266,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap(
																				24,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jCheckBox1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				80,
																				Short.MAX_VALUE)
																		.addComponent(
																				jCheckBox2)
																		.addGap(40,
																				40,
																				40)))));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(jLabel6)
														.addComponent(
																jComboBox1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jComboBox2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7)
														.addComponent(
																jComboBox3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jCheckBox1)
														.addComponent(
																jCheckBox2))
										.addContainerGap(38, Short.MAX_VALUE)));

		jButton1.setText("\u786e\u5b9a");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("\u53d6\u6d88");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(152,
																		152,
																		152)
																.addComponent(
																		jButton1)
																.addGap(113,
																		113,
																		113)
																.addComponent(
																		jButton2))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																				.addComponent(
																						jPanel2,
																						javax.swing.GroupLayout.Alignment.LEADING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						jPanel1,
																						javax.swing.GroupLayout.Alignment.LEADING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))))
								.addContainerGap(12, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jPanel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton2)
												.addComponent(jButton1))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// GEN-END:initComponents
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		View_teacher vt = (View_teacher) jComboBox3.getSelectedItem();
		if (vt == null) {
			JOptionPane.showMessageDialog(this, "请选择授课教师！", "提示信息",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String t_id = vt.getT_id();
		tt.setT_id(t_id);
		if (jCheckBox1.isSelected())
			tt.setMultimedia("是");
		else {
			tt.setMultimedia("否");
		}
		tt.setPracticescheduling("");
		if (jCheckBox2.isEnabled()) {
			if (jCheckBox2.isSelected())
				tt.setPracticescheduling("是");
			else {
				tt.setPracticescheduling("否");
			}
		}
		Connection con = null;
		try {
			con = Databaseconnection.getconnection();
			con.setAutoCommit(false);
			int r=Teachingtaskaccess.update(con, tt);
			// 如果修改行数小于1，数据库回滚后退出
			if (r < 1) {
				String msg = "教学任务数据修改失败，请联系系统管理员！";
				Tools.connectionroolback(con, msg);
				return;
			}
			//
			if(tt.getPracticescheduling().equals("否")&&tt.getCou_experimentalhours()==0){
				String cou_id=tt.getCou_id();
				String grade=tt.getTt_grade();
				r=Courseexperimentalenvironmentaccess.deletebycouidgrade(con, cou_id, grade);
				if(r==-1){
					String msg = "因数据库连接异常，教学任务数据修改失败，请联系系统管理员！";
					Tools.connectionroolback(con, msg);
					return;
				}
			}
			// 事务提交
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		this.dispose();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {
		Office o1 = (Office) jComboBox2.getSelectedItem();
		if (o1 == null)
			return;
		String o_id = o1.getO_id();
		Fillcombobox.fillteacher(o_id, vt, jComboBox3);
	}

	private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
		Department dp = (Department) jComboBox1.getSelectedItem();
		if (dp == null)
			return;
		Fillcombobox.filloffice(dp.getD_id(), jComboBox2);
		if (dp.equals(d)) {
			jComboBox2.setSelectedItem(o);
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				CZY_jd_teachingtaskmodify dialog = new CZY_jd_teachingtaskmodify(
						new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JCheckBox jCheckBox2;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	// End of variables declaration//GEN-END:variables

}