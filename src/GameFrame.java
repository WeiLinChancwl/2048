import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener {
	/**
	 * 登录界面
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnStart, btnGrade, btnExit, btnInfo;
	private JPanel panel1, panel2, panel3;
	private JLabel imageLabel;
    
	public GameFrame() {
		this.setLayout(new FlowLayout());	
		this.setBounds(100, 100, 400, 550);	
		
		//更换Java咖啡图标
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/title.png"));	
		this.setIconImage(imageIcon.getImage());	
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		//增加登录界面图片
		ImageIcon image = new ImageIcon(getClass().getResource("/images/GameFrame.jpg"));
		imageLabel = new JLabel(image);
		panel1.add(imageLabel);

		btnStart = new JButton("开始游戏");
		btnStart.setBackground(Color.lightGray);
		btnGrade = new JButton("得分记录");
		btnGrade.setBackground(Color.lightGray);
		btnExit = new JButton("退出游戏");
		btnExit.setBackground(Color.lightGray);
		btnInfo = new JButton("游戏规则");
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
		//设置不可扩展窗口
		this.setResizable(false);
		setTitle("2048");
		//设置关闭窗口
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		// 居中窗体
		setLocationRelativeTo(null);
		//设置监听器
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
			JOptionPane.showMessageDialog(this, "两个相同的块碰撞，即可得到这两个块的数字的分数，出现数字为2048块的即通关");
		}
	}
}


