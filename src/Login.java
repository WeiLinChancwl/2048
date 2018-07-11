import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 实现游戏的登录
 * @author William
 */
public class Login {
	public static void main(String[] args) {
		new loginFrame();
	}
}

class loginFrame extends JFrame implements ActionListener {
	/**
	 * 登录界面
	 */
	private static final long serialVersionUID = 1L;
	private JLabel userName, userPwd, role;
	private static JTextField nameField;
	private static JPasswordField pwdField;
	private JButton btnLogin, btncz, btnCancel;
	private JPanel panel1, panel2, panel3;
	private JLabel imageLabel;
	private JComboBox<String> faceCombo;
    
	loginFrame() {
		this.setLayout(new FlowLayout());	
		this.setBounds(100, 100, 350, 550);	
		
		//更换Java咖啡图标
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/title.png"));	
		this.setIconImage(imageIcon.getImage());	
		
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		//增加登录界面图片
		ImageIcon image = new ImageIcon(getClass().getResource("/images/login.jpg"));
		imageLabel = new JLabel(image);
		panel1.add(imageLabel);
		
		//设置选择登录角色
		role = new JLabel("选择你的角色:");		
		faceCombo = new JComboBox<String>();
		faceCombo.addItem("用户");
		faceCombo.addItem("游客");
		
		userName = new JLabel("请你输入账号:");
		userName.setBackground(Color.lightGray);
		userPwd = new JLabel("请你输入密码:");
		userPwd.setBackground(Color.lightGray);
		nameField = new JTextField(10);
		pwdField = new JPasswordField(10);

		btnLogin = new JButton("登录");
		btnLogin.setBackground(Color.lightGray);
		btncz = new JButton("注册");
		btncz.setBackground(Color.lightGray);
		btnCancel = new JButton("exit");
		btnCancel.setBackground(Color.lightGray);
		

		panel2.setLayout(new GridLayout(4, 2));
		panel2.add(userName);
		panel2.add(nameField);
		panel2.add(userPwd);
		panel2.add(pwdField);
		panel2.add(role);
		panel2.add(faceCombo);
		
		panel3.setLayout(new FlowLayout());
		panel3.add(btnLogin);
		panel3.add(btncz);
		panel3.add(btnCancel);
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.setVisible(true);
		//设置不可扩展窗口
		this.setResizable(false);
		setTitle("2048登陆");
		//设置关闭窗口
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		// 居中窗体
		setLocationRelativeTo(null);
		//设置监听器
		btnLogin.addActionListener(this);
		btncz.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	public static String getUserName() {
		//获得用户名
		return nameField.getText();
	}

	@SuppressWarnings("deprecation")
	public static String getUserPwd() {
		//获得用户密码
		return pwdField.getText();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnLogin) {
			String userName, userPwd;
			userName = getUserName();
			userPwd = getUserPwd();
			if (faceCombo.getSelectedItem() == "用户") {
				//User.createExcel();	//创建EXCEL文件
				try {
					if(User.existAccount(userName)) {
						if(User.judgeUserPwd(userName, userPwd)) {
							this.dispose();
							new GameFrame();
						} else {
							JOptionPane.showMessageDialog(this, "密码错误");
						}
					} else {
						JOptionPane.showMessageDialog(this,"用户还没注册，请先注册");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				//游客登录
				this.dispose();
				new GameFrame();
			}
		} else if (source == btncz) {
			new ResgisterFrame();
		} else if (source == btnCancel) {
			System.exit(0);
		}
	}
}

