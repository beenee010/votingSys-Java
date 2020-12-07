package votesys.view.user;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import votesys.domain.Candidate;
import votesys.file.CandidateIO;
import votesys.file.DistrictIO;

public class CandidateList extends JFrame {

	private Vector<String> v;
	private DistrictIO d;
	private JList<String> list;
	private CandidateIO cIO;
	private Vector<Candidate> cand;
	private Candidate c;

	public CandidateList(JFrame preFrame) {
		setSize(300,420);
		d = new DistrictIO();
		v = d.returnDist();
		setLayout(new FlowLayout(FlowLayout.CENTER,100,30));
		setTitle("Voter System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel p1 = new JPanel();
		JComboBox combo = new JComboBox(v);
		combo.setSize(30, 10);
		p1.add(combo);

		list = new JList<>();
		list.setSize(new Dimension(200,200));
		list.setFont(new Font("굴림",Font.BOLD,15));
		JScrollPane p2 = new JScrollPane();
		p2.setPreferredSize(new Dimension(200,200));
		p2.setViewportView(list);

		JPanel p3 = new JPanel();
		JButton ok = new JButton("OK");
		p3.add(ok);

		add(p1);
		add(p2);
		add(p3);

		// 콤보박스를 선택한 경우
		combo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String dist = combo.getSelectedItem().toString();

				cIO = CandidateIO.getInstance();
				cIO.setDistrict(dist);
				if(cIO.fileReader()) {
					cand = cIO.returnCand();
					String[] candArr = new String[cand.size()];
					for(int i = 0; i <cand.size(); i++) {
						c = cand.get(i);
						candArr[i] = (i+1) + "번          " + c.getName();
					}
					list.setListData(candArr);
				}

			}

		});

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserMain().show();
				dispose();
			}
		});
	}

}
