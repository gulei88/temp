/*
 * CZY_jd_updatecourseexperimentalenvironment.java
 *
 * Created on __DATE__, __TIME__
 */

package czy.view;

import global.dao.Databaseconnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import czy.dao.Courseexperimentalenvironmentaccess;
import czy.model.Courseexperimentalenvironment;

/**
 * 
 * @author __USER__
 */
public class CZY_jd_updatecourseexperimentalenvironment extends
		javax.swing.JDialog {
	private Courseexperimentalenvironment ce;

	/** Creates new form CZY_jd_updatecourseexperimentalenvironment */
	public CZY_jd_updatecourseexperimentalenvironment(java.awt.Frame parent,
			boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public CZY_jd_updatecourseexperimentalenvironment(java.awt.Frame parent,
			boolean modal, Courseexperimentalenvironment ce) {
		super(parent, modal);
		initComponents();
		this.ce = ce;
		//取得实验环境内容
		String experimentalenvironment = ce.getExperimentalenvironment();
		//如果实验环境不为空，设置文本框的内容为实验环境
		if (experimentalenvironment != null)
			jTextField1.setText(experimentalenvironment);
		// 设置界面居中显示
				this.setLocationRelativeTo(null);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u5b9e\u9a8c\u73af\u5883\u8bbe\u7f6e");

		jLabel1.setText("\u5b9e\u9a8c\u73af\u5883\uff1a");

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
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel1)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jTextField1,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		276,
																		Short.MAX_VALUE)
																.addContainerGap())
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		jButton1)
																.addGap(72, 72,
																		72)
																.addComponent(
																		jButton2)
																.addGap(90, 90,
																		90)))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(26, 26, 26)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														jTextField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(33, 33, 33)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton2)
												.addComponent(jButton1))
								.addContainerGap()));

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		String experimentalenvironment = jTextField1.getText();
		ce.setExperimentalenvironment(experimentalenvironment);
		Connection con = null;
		try {
			con = Databaseconnection.getconnection();
			Courseexperimentalenvironmentaccess.updateexperimentalenvironment(
					con, ce);
			this.dispose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "实验环境设置失败，请联系系统管理员！");
			e.printStackTrace();
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

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				CZY_jd_updatecourseexperimentalenvironment dialog = new CZY_jd_updatecourseexperimentalenvironment(
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
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration//GEN-END:variables

}