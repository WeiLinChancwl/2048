import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ʵ����Ϸ�ĵ�¼
 * @author William
 */
public class Login {
	public static void main(String[] args) {
		new loginFrame();
	}
}

class loginFrame extends JFrame implements ActionListener {
	/**
	 * ��¼����
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
		
		//����Java����ͼ��
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/title.png"));	
		this.setIconImage(imageIcon.getImage());	
		
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		//���ӵ�¼����ͼƬ
		ImageIcon image = new ImageIcon(getClass().getResource("/images/login.jpg"));
		imageLabel = new JLabel(image);
		panel1.add(imageLabel);
		
		//����ѡ���¼��ɫ
		role = new JLabel("ѡ����Ľ�ɫ:");		
		faceCombo = new JComboBox<String>();
		faceCombo.addItem("�û�");
		faceCombo.addItem("�ο�");
		
		userName = new JLabel("���������˺�:");
		userName.setBackground(Color.lightGray);
		userPwd = new JLabel("������������:");
		userPwd.setBackground(Color.lightGray);
		nameField = new JTextField(10);
		pwdField = new JPasswordField(10);

		btnLogin = new JButton("��¼");
		btnLogin.setBackground(Color.lightGray);
		btncz = new JButton("ע��");
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
		//���ò�����չ����
		this.setResizable(false);
		setTitle("2048��½");
		//���ùرմ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		// ���д���
		setLocationRelativeTo(null);
		//���ü�����
		btnLogin.addActionListener(this);
		btncz.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	public static String getUserName() {
		//����û���
		return nameField.getText();
	}

	@SuppressWarnings("deprecation")
	public static String getUserPwd() {
		//����û�����
		return pwdField.getText();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnLogin) {
			String userName, userPwd;
			userName = getUserName();
			userPwd = getUserPwd();
			if (faceCombo.getSelectedItem() == "�û�") {
				//User.createExcel();	//����EXCEL�ļ�
				try {
					if(User.existAccount(userName)) {
						if(User.judgeUserPwd(userName, userPwd)) {
							this.dispose();
							new GameFrame();
						} else {
							JOptionPane.showMessageDialog(this, "�������");
						}
					} else {
						JOptionPane.showMessageDialog(this,"�û���ûע�ᣬ����ע��");
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
				//�ο͵�¼
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

