package votesys.view.admin;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import votesys.domain.Candidate;
import votesys.file.CandidateIO;
import votesys.file.DistrictIO;

public abstract class EditCandidate extends JFrame{

	private JFrame nowFrame;
	private JList<String> list;
	private DistrictIO distIO;
	private Vector<String> distVec;
	private CandidateIO cIO;
	private Candidate c;
	private Vector<Candidate> candVec;
	private String dist;
	
	public EditCandidate() {
		nowFrame = this;
		setSize(400,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER,100,30));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		distIO = new DistrictIO();
		distVec = distIO.returnDist();
		JComboBox combo = new JComboBox(distVec);
		p1.add(combo);
		
		JScrollPane p2 = new JScrollPane();
		list = new JList<>();
		list.setSize(new Dimension(210,180));
		list.setFont(new Font("굴림",Font.BOLD,15));
		p2.setPreferredSize(new Dimension(210,180));
		p2.setViewportView(list);
		

		
		add(p1);
		add(p2);

		// 콤보박스를 선택한 경우 액션
		combo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dist = (String) combo.getSelectedItem();

				cIO = CandidateIO.getInstance();
				cIO.setDistrict(dist);
				if(cIO.fileReader()) {
					candVec = cIO.returnCand();

					String[] tmpList = new String[candVec.size()];
					for(int i = 0; i < candVec.size(); i++) {
						c = candVec.get(i);
						if(c == null) {
							break;
						}
						String str = (i+1) + "번           " + c.getName();
						tmpList[i] = str;
					}
					list.setListData(tmpList);
				}
			}

		});
	}
	
	protected String getDist() {
		return dist;
	}
	
	protected int getCandSize() {
		return candVec.size();
	}
}
