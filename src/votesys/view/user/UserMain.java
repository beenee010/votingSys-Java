package votesys.view.user;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import votesys.view.admin.AdminMain;

public class UserMain extends JFrame {

	private JFrame nowFrame;

	public UserMain() {
		nowFrame = this;
		setTitle("User System");
		setSize(400,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER,100,50));
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel p1 = new JPanel();
		Font f = new Font("돋움", Font.BOLD,35);
		JLabel label = new JLabel("Vote System");
		label.setFont(f);
		p1.add(label);

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(0,2,30,0));
		String[] items = {"후보자 목록", "투표"};
		JComboBox combo = new JComboBox(items);
		JButton ok = new JButton("OK");

		p2.add(combo);
		p2.add(ok);

		JPanel p3 = new JPanel();
		JButton exit = new JButton("Exit");
		p3.add(exit);

		add(p1);
		add(p2);
		add(p3);

		setVisible(true);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if((combo.getSelectedItem().toString()).equals("투표")) {
					new InsertInformation().show();
					dispose();
				}
				else {
					new CandidateList(nowFrame).show();
					dispose();
				}
			}
		});

		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				nowFrame.dispose();
			}	
		});
	}
}
