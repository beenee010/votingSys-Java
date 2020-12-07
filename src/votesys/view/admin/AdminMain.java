package votesys.view.admin;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import votesys.file.AdminPassIO;
import votesys.view.common.AlertDialog;

public class AdminMain extends JFrame {
	
	private AdminPassIO aPass;
	private JFrame nowFrame;
	
	public AdminMain() {
		nowFrame = this;
		setSize(400,350);
		setTitle("Admin System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER,100,30));
		setLocationRelativeTo(null);
		setResizable(false);
		
		Font f = new Font("돋움", Font.BOLD,35);
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0,1,10,10));
		JLabel l1 = new JLabel("Vote System");
		l1.setFont(f);
		JLabel l2 = new JLabel(": Admin",JLabel.CENTER);
		l2.setFont(new Font("돋움", Font.BOLD, 20));
		p1.add(l1);
		p1.add(l2);
		
		JPanel p2 = new JPanel();
		JLabel l3 = new JLabel("Password: ");
		JPasswordField passTf = new JPasswordField(10);
		p2.add(l3);
		p2.add(passTf);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER,70,0));
		JButton exit = new JButton("Exit");
		JButton login = new JButton("Login");
		p3.add(exit);
		p3.add(login);
		
		add(p1);
		add(p2);
		add(p3);
		
		setVisible(true);
		
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		login.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String pass = passTf.getText();
				
				aPass = new AdminPassIO();
				aPass.fileReader();
				if(pass.equals(aPass.getText())) {
					// Admin System
					new AdminSystem().show();
					dispose();
				}
				else {
					new AlertDialog(nowFrame,"Alert",true,"비밀번호가 일치하지 않습니다.").show();
				}	//비밀번호가 일치하지 않을경우
			}
		});
	}
	
}
