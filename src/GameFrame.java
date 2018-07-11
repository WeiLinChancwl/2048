import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener {
	/**
	 * ��¼����
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnStart, btnGrade, btnExit, btnInfo;
	private JPanel panel1, panel2, panel3;
	private JLabel imageLabel;
    
	public GameFrame() {
		this.setLayout(new FlowLayout());	
		this.setBounds(100, 100, 400, 550);	
		
		//����Java����ͼ��
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/title.png"));	
		this.setIconImage(imageIcon.getImage());	
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		//���ӵ�¼����ͼƬ
		ImageIcon image = new ImageIcon(getClass().getResource("/images/GameFrame.jpg"));
		imageLabel = new JLabel(image);
		panel1.add(imageLabel);

		btnStart = new JButton("��ʼ��Ϸ");
		btnStart.setBackground(Color.lightGray);
		btnGrade = new JButton("�÷ּ�¼");
		btnGrade.setBackground(Color.lightGray);
		btnExit = new JButton("�˳���Ϸ");
		btnExit.setBackground(Color.lightGray);
		btnInfo = new JButton("��Ϸ����");
		btnInfo.setBackground(Color.lightGray);
		
		panel3.setLayout(new FlowLayout());
		panel3.add(btnStart);
		panel3.add(btnGrade);
		panel3.add(btnExit);
		panel3.add(btnInfo);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.setVisible(true);
		//���ò�����չ����
		this.setResizable(false);
		setTitle("2048");
		//���ùرմ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		// ���д���
		setLocationRelativeTo(null);
		//���ü�����
		btnStart.addActionListener(this);
		btnGrade.addActionListener(this);
		btnExit.addActionListener(this);
		btnInfo.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnStart) {
			this.dispose();
			new StartFrame();
		} else if (source == btnGrade) {
			new GradeFrame();
		} else if (source == btnExit) {
			System.exit(0);
		} else if(source == btnInfo) {
			JOptionPane.showMessageDialog(this, "������ͬ�Ŀ���ײ�����ɵõ�������������ֵķ�������������Ϊ2048��ļ�ͨ��");
		}
	}
}


