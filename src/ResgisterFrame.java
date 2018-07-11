import javax.swing.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ResgisterFrame extends JFrame implements ActionListener {
	private JLabel lbAccount=new JLabel("请您输入账号：");
	private JTextField tfAccount=new JTextField(10);
    private JLabel lbPassword=new JLabel("请您输入密码：");
    private JPasswordField pfPassword=new JPasswordField(10);
    private JLabel lbPassword2=new JLabel("输入确认密码：");
    private JPasswordField pfPassword2=new JPasswordField(10);
    private JLabel lbName=new JLabel("请您输入姓名：");
    private JTextField tfName=new JTextField(10);
    private JButton btRegister=new JButton("注册");
    private JButton btLogin=new JButton("登录");
    private JButton btExit=new JButton("退出");
    public ResgisterFrame() {
    	super("注册");
    	this.setLayout(new FlowLayout());
    	this.add(lbAccount);
    	this.add(tfAccount);
    	this.add(lbPassword);
        this.add(pfPassword);
        this.add(lbPassword2);
        this.add(pfPassword2);
        this.add(lbName);
        this.add(tfName);
        this.add(btRegister);
        this.add(btLogin);
        this.add(btExit);
        this.setSize(240, 220);
        this.setResizable(false);
        this.setVisible(true);
        // 居中窗体
     	this.setLocationRelativeTo(null);
        btLogin.addActionListener(this);
        btRegister.addActionListener(this);
        btExit.addActionListener(this);
      
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		 String password1=new String(pfPassword.getPassword());
         String password2=new String(pfPassword2.getPassword());
		if(e.getSource()==btRegister) {
			if(password1.trim().equals("") || tfAccount.getText().trim().equals(""))
            	JOptionPane.showMessageDialog(this, "账号或密码不可为空");
			else {
	            if(!password1.equals(password2)) {
	                JOptionPane.showMessageDialog(this,"两个密码不相同");
	                return;
	            }
	            String account=tfAccount.getText();
	            try {
					if(User.existAccount(tfAccount.getText())) {
					    JOptionPane.showMessageDialog(this,"用户已经注册");
					    return;
					}
				} catch (HeadlessException e2) {
					e2.printStackTrace();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	            String name=tfName.getText();
	            try {
					updateUser(account,password1,name);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	            JOptionPane.showMessageDialog(this,"注册成功");
	            this.dispose();
			}
        }
        else if(e.getSource()==btLogin) {
            this.dispose();
            new Login();
        }
        else {
            JOptionPane.showMessageDialog(this,"谢谢使用");
            System.exit(0);
        }
    }
	private void updateUser(String account, String password1, String name) throws Exception {
		User.ExportExcel(account, password1);
		User.readExcel();
	}
}
