package votesys.view.admin;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import votesys.domain.Voter;
import votesys.file.VoterIO;

public class ShowVoterList extends JFrame {

	private VoterIO vIO;
	private Voter v;
	private Vector<Voter> vVec;
	
	public ShowVoterList() {
		setSize(400,500);
		setTitle("Show Voter");
		setLayout(new FlowLayout(FlowLayout.CENTER,100,30));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel l1 = new JLabel("투표한 유권자 리스트",JLabel.CENTER);
		l1.setFont(new Font("돋움",Font.BOLD,20));
		p1.add(l1);
		
		JScrollPane p2 = new JScrollPane();
		p2.setPreferredSize(new Dimension(250,250));
		
		vIO = VoterIO.getInstance();
		vIO.loadVoterFile();
		vVec = vIO.returnVoterList();
		String[] voterArr = new String[vVec.size()];
		for(int i = 0; i < vVec.size(); i++) {
			v = vVec.get(i);
			voterArr[i] = v.toString();
		}
		JList<String> list = new JList<String>(voterArr);
		list.setSize(new Dimension(250,250));
		list.setFont(new Font("굴림",Font.BOLD,15));
		p2.setViewportView(list);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton ok = new JButton("OK");
		p3.add(ok);
		
		add(p1);
		add(p2);
		add(p3);
		
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AdminSystem().show();
				dispose();
			}
			
		});
	}
	
}
