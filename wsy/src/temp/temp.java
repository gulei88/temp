package temp;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class temp extends JFrame {
	private JTextField textField;
	public temp() {
		getContentPane().setLayout(new GridLayout(5, 5));
		
		JButton btnNewButton = new JButton("New button");
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("New button");
		getContentPane().add(btnNewButton_5);
		
		textField = new JTextField();
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("New button");
		getContentPane().add(btnNewButton_4);
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar()==e.VK_ENTER) {
					System.out.println("\r+1");
					System.out.println(e.getKeyChar());
					System.out.println(e.VK_ENTER+"    q");
				}
				if (e.getKeyChar()=='\n') {
					System.out.println(" \\ n+2");
					
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public static void main(String[] args) {
//		System.out.println(JOptionPane.YES_NO_CANCEL_OPTION);
//		System.out.println(JOptionPane.YES_NO_OPTION);
//		System.out.println(JOptionPane.YES_OPTION);
		temp t=new temp();
		t.setVisible(true);
		t.setSize(500,500);
		
	}
}
