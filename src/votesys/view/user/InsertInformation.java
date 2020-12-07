package votesys.view.user;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import votesys.domain.Voter;
import votesys.file.DistrictIO;
import votesys.file.VoterIO;
import votesys.view.common.AlertDialog;

public class InsertInformation extends JFrame {

	private Vector<String> v;
	private DistrictIO dist;
	private VoterIO vIO;
	private Voter voter;
	private JFrame nowFrame;

	public InsertInformation() {
		nowFrame = this;
		setSize(450,400);
		setTitle("Insert Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER,100,35));
		setResizable(false);
		setLocationRelativeTo(null);
		dist = new DistrictIO();

		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("Vote System");
		l1.setFont(new Font("돋움", Font.BOLD,35));
		p1.add(l1);

		v = dist.returnDist();
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(0,2,0,10));

		JPanel distPanel1 = new JPanel();
		distPanel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel l2 = new JLabel("지역구 선택:         ",JLabel.RIGHT);
		distPanel1.add(l2);

		JComboBox combo = new JComboBox(v);
		combo.setSize(50, 10);
		JPanel distPanel2 = new JPanel();
		distPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		distPanel2.add(combo);

		JPanel namePanel1 = new JPanel();
		namePanel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel l3 = new JLabel("이름:         ",JLabel.RIGHT);
		JTextField nameTf = new JTextField(8);
		namePanel1.add(l3);

		JPanel namePanel2 = new JPanel();
		namePanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		namePanel2.add(nameTf);

		JPanel secPanel1 = new JPanel();
		secPanel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel l4 = new JLabel("주민번호:         ",JLabel.RIGHT);
		JPasswordField secNum1Tf = new JPasswordField(6);
		JPasswordField secNum2Tf = new JPasswordField(7);
		JLabel l5 = new JLabel("-");
		secPanel1.add(l4);

		JPanel secPanel2 = new JPanel();
		secPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		secPanel2.add(secNum1Tf);
		secPanel2.add(l5);
		secPanel2.add(secNum2Tf);

		p2.add(distPanel1);
		p2.add(distPanel2);
		p2.add(namePanel1);
		p2.add(namePanel2);
		p2.add(secPanel1);
		p2.add(secPanel2);

		JPanel p3 = new JPanel();
		GridLayout g2 = new GridLayout(0,2,150,0);

		p3.setLayout(g2);

		JButton undo = new JButton("Undo");
		JButton vote = new JButton("Vote");

		undo.setSize(6, 4);
		vote.setSize(6, 4);

		p3.add(undo);
		p3.add(vote);

		add(p1);
		add(p2);
		add(p3);

		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserMain();
			}			
		});

		vote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTf.getText();
				String leftSec = secNum1Tf.getText();
				String rightSec = secNum2Tf.getText();
				String secNum = leftSec + "-" + rightSec;

				if(secNum.length() != 14 || leftSec.length() != 6 || rightSec.length() != 7) {
					new AlertDialog(nowFrame,"Alert",true,"주민번호를 정확히 입력하세요.").show();
					return;
				}

				String tmpNum = leftSec + rightSec;
				char tmp;
				for(int i = 0; i < 13; i++) {
					tmp = tmpNum.charAt(i);
					if(tmp < 48 || tmp > 58) {
						new AlertDialog(nowFrame, "Alert", true, "주민번호는 숫자입니다.").show();;
						return;
					}
				}

				vIO = VoterIO.getInstance();
				vIO.saveVoterInfo(name, secNum);

				if(vIO.searchOverlap()) {
					new VotingFrame(nowFrame,"Vote",(String)combo.getSelectedItem()).show();
					dispose();
				}
				else {
					new AlertDialog(nowFrame,"Alert",true,"이미 투표를 진행하셨습니다.").show();
				}
			}
		});

	}

}
