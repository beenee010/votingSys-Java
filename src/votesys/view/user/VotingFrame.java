package votesys.view.user;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import votesys.domain.Candidate;
import votesys.file.CandidateIO;
import votesys.view.common.AlertChoiceDialog;
import votesys.view.common.AlertDialog;

public class VotingFrame extends JFrame {
	
	private CandidateIO cIO;
	private Candidate c;
	private String dist;
	private JFrame nowFrame;
	private Vector<Candidate> candVec;
	
	public VotingFrame(JFrame preFrame, String title, String dist) {
		setSize(400,500);
		this.dist = dist;
		nowFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Voting");
		setLayout(new FlowLayout(FlowLayout.CENTER,200,30));
		
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("기호를 입력하세요.");
		l1.setFont(new Font("돋움", Font.CENTER_BASELINE,20));
		p1.add(l1);
		
		JScrollPane p2 = new JScrollPane();
		p2.setPreferredSize(new Dimension(200,200));
		JList<String> list = new JList<>(setList());
		list.setFont(new Font("굴림",Font.BOLD,15));
		p2.setViewportView(list);
		
		JPanel p3 = new JPanel();
		JTextField symbolTf = new JTextField(8);
		JLabel l2 = new JLabel("기호 : ");
		p3.add(l2);
		p3.add(symbolTf);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(0,2,100,0));
		JButton undo = new JButton("Undo");
		JButton vote = new JButton("Vote");
		p4.add(undo);
		p4.add(vote);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InsertInformation().show();
				dispose();
			}
		});
		
		vote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symbolStr = symbolTf.getText();
				
				if(symbolStr.equals("")) {
					new AlertDialog(nowFrame, "Alert", true, "기호를 입력해주세요.").show();
					return;
				}
				
				char tmp;
				for(int i = 0; i < symbolStr.length(); i++) {
					tmp = symbolStr.charAt(i);
					if(tmp < 48 || tmp > 58) {
						new AlertDialog(nowFrame, "Alert", true, "기호를 잘못 입력하셨습니다.").show();;
						return;
					}
				}
				
				int symbol = Integer.parseInt(symbolStr);
				if(symbol > candVec.size() || symbol < 1) {
					new AlertDialog(nowFrame, "Alert", true, "기호를 잘못 입력하셨습니다.").show();
				}
				else {
					new AlertChoiceDialog(nowFrame, "Vote", true, "기호 " + symbol + "번에 투표하시겠습니까 ??",symbol).show();
				}
			}
		});
		
	}
	
	private String[] setList() {
		String[] candArr = null;
		cIO = CandidateIO.getInstance();
		cIO.setDistrict(dist);
		
		if(cIO.fileReader()) {
			candVec = cIO.returnCand();
			candArr = new String[candVec.size()];
			for(int i = 0; i < candVec.size(); i++) {
				c = candVec.get(i);
				candArr[i] = (i+1) + "번          " + c.getName();
			}
		}
		return candArr;
	}
	
}
