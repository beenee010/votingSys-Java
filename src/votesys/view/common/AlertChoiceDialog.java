package votesys.view.common;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import votesys.file.CandidateIO;
import votesys.file.VoterIO;
import votesys.view.admin.AdminSystem;
import votesys.view.user.UserMain;

// 추상클래스를 상속
public class AlertChoiceDialog extends AbstractDialog {

	private VoterIO vIO;
	private CandidateIO cIO;
	private String name;

	public AlertChoiceDialog(JFrame preFrame, String title, boolean modal, String text, int symbol, String name) {
		this(preFrame, title, modal, text, symbol);
		this.name = name;
	}

	public AlertChoiceDialog(JFrame preFrame, String title, boolean modal, String text, int symbol) {
		super(preFrame,title,modal,text);

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(0,2,100,0));
		JButton ok = new JButton("OK");
		JButton cancel = new JButton("Cancel");
		p2.add(cancel);
		p2.add(ok);
		add(p2);

		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				switch(title) {
				case "Vote":
					vIO = VoterIO.getInstance();
					vIO.saveVoterFile();
					cIO = CandidateIO.getInstance();
					cIO.addNumofVotes(symbol);
					cIO.fileSave(symbol);
					new AlertDialog(preFrame, "Finish", true, "소중한 한 표 감사합니다 !").show();
					new UserMain().show();
					break;
				case "Add":
					cIO = CandidateIO.getInstance();
					cIO.addCandidate(symbol, name);
					new AlertDialog(preFrame, "Add",true,"추가가 완료되었습니다.").show();
					new AdminSystem().show();
					break;
				case "Delete":
					cIO = CandidateIO.getInstance();
					cIO.delCandidate(symbol);
					new AlertDialog(preFrame, "Delete",true,"삭제가 완료되었습니다.").show();
					new AdminSystem().show();
					break;
				}
				preFrame.dispose();
			}

		});

		cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}

		});

	}

}
