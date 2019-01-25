package zzt.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {

    public static void main(String[] args) throws Exception {
        final JFrame jf = new JFrame("���Դ���");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btn = new JButton("��ʾ�Զ���Ի���");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCustomDialog(jf, jf);
            }
        });

        JPanel panel = new JPanel();
        panel.add(btn);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

    /**
     * ��ʾһ���Զ���ĶԻ���
     *
     * @param owner �Ի����ӵ����
     * @param parentComponent �Ի���ĸ������
     */
    private static void showCustomDialog(Frame owner, Component parentComponent) {
        // ����һ��ģ̬�Ի���
        final JDialog dialog = new JDialog(owner, "��ʾ", true);
        // ���öԻ���Ŀ��
        dialog.setSize(250, 150);
        // ���öԻ����С���ɸı�
        dialog.setResizable(false);
        // ���öԻ��������ʾ��λ��
        dialog.setLocationRelativeTo(parentComponent);

        // ����һ����ǩ��ʾ��Ϣ����
        JLabel messageLabel = new JLabel("�Ի�����Ϣ����");

        // ����һ����ť���ڹرնԻ���
        JButton okBtn = new JButton("ȷ��");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // �رնԻ���
                dialog.dispose();
            }
        });

        // �����Ի�����������, ������ڿ��Ը����Լ�����Ҫ����κ�������������ǲ���
        JPanel panel = new JPanel();

        // �����������
        panel.add(messageLabel);
        panel.add(okBtn);

        // ���öԻ�����������
        dialog.setContentPane(panel);
        // ��ʾ�Ի���
        dialog.setVisible(true);
    }

}

