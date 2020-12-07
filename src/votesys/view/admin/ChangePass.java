package votesys.view.admin;

import java.awt.FlowLayout;
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

public class ChangePass extends JFrame {

	private JFrame nowFrame;
	private AdminPassIO aPass;
	
	public ChangePass() {
		nowFrame = this;
		setSize(400,280);
		setTitle("Change Pass");
		setLayout(new FlowLayout(FlowLayout.CENTER,100,30));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0,2,20,20));
		JLabel l1 = new JLabel("현재 비밀번호:",JLabel.RIGHT);
		JLabel l2 = new JLabel("변경할 비밀번호:",JLabel.RIGHT);
		JLabel l3 = new JLabel("변경할 비밀번호 확인:",JLabel.RIGHT);
		JPasswordField nowTf = new JPasswordField(10);
		JPasswordField newTf = new JPasswordField(10);
		JPasswordField newConfirmTf = new JPasswordField(10);
		p1.add(l1);
		p1.add(nowTf);
		p1.add(l2);
		p1.add(newTf);
		p1.add(l3);
		p1.add(newConfirmTf);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(0,2,100,0));
		JButton undo = new JButton("Undo");
		JButton save = new JButton("Save");
		p2.add(undo);
		p2.add(save);
		
		add(p1);
		add(p2);
		
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminSystem().show();
				dispose();
			}
			
		});
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				aPass = new AdminPassIO();
				aPass.fileReader();
				String realPass = aPass.getText();
				String nowPass = nowTf.getText();
				String newPass = newTf.getText();
				String newConfirmPass = newConfirmTf.getText();
				
				if(nowPass.equals("") || newPass.equals("") || newConfirmPass.equals("")) {
					new AlertDialog(nowFrame,"Alert",true,"빈곳없이 입력해 주세요.").show();
				}
				else if(!nowPass.equals(realPass)) {
					new AlertDialog(nowFrame,"Alert",true,"이전 비밀번호를 잘못 입력하셨습니다.").show();
				}
				else if(!newPass.equals(newConfirmPass)) {
					new AlertDialog(nowFrame,"Alert",true,"입력하신 비밀번호가 틀립니다.").show();
				}
				else if(nowPass.equals(newPass)) {
					new AlertDialog(nowFrame,"Alert",true,"이전 비밀번호와 같습니다.").show();
				}
				else {
					aPass.fileWriter(newPass);
					new AlertDialog(nowFrame,"Alert",true,"비밀번호가 변경되었습니다.").show();
					new AdminSystem().show();
					dispose();
				}
			}
			
		});
	}
	
}
