package votesys.view.admin;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminSystem extends JFrame {
	
	public AdminSystem() {
		setSize(400,400);
		setLayout(new FlowLayout(FlowLayout.CENTER,100,60));
		setTitle("Admin System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel l1 = new JLabel("Admin System");
		l1.setFont(new Font("돋움", Font.BOLD,35));
		p1.add(l1);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		String[] menu = {"지역구 수정","후보자 추가","후보자 삭제","투표자 목록","관리자 비밀번호 변경"};
		JComboBox combo = new JComboBox(menu);
		combo.setSize(50, 5);
		p2.add(combo);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER,70,0));
		JButton undo = new JButton("Undo");
		JButton ok = new JButton("OK");
		p3.add(undo);
		p3.add(ok);
		
		add(p1);
		add(p2);
		add(p3);
		
		setVisible(true);
		
		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new AdminMain().show();
				dispose();
			}
		});
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String select = (String) combo.getSelectedItem();
				
				// 현재의 선택된 메뉴를 switch문을 통하여 다음 Frame 생성
				switch(select) {
				case "지역구 수정":
					new EditDistrict().show();
					break;
				case "후보자 추가":
					new AddCandidate().show();
					break;
				case "후보자 삭제":
					new DeleteCandidate().show();
					break;
				case "투표자 목록":
					new ShowVoterList().show();
					break;
				case "관리자 비밀번호 변경":
					new ChangePass().show();
					break;
				}
				dispose();
			}
			
		});
	}
	
}
