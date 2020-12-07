package votesys.view.admin;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import votesys.view.common.AlertChoiceDialog;
import votesys.view.common.AlertDialog;

public class AddCandidate extends EditCandidate{

	private JFrame nowFrame;
	
	public AddCandidate() {
		setTitle("Add Candidate");
		nowFrame = this;
		
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(0,2,10,20));
		JLabel l1 = new JLabel("기호:");
		JTextField symbolTf = new JTextField(8);
		JLabel l2 = new JLabel("이름:");
		JTextField nameTf = new JTextField(8);
		p3.add(l1);
		p3.add(symbolTf);
		p3.add(l2);
		p3.add(nameTf);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(0,2,100,0));
		JButton undo = new JButton("Undo");
		JButton ok = new JButton("OK");
		p4.add(undo);
		p4.add(ok);
		
		add(p3);
		add(p4);
		
		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new AdminSystem().show();
				dispose();
			}

		});
		
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String symbolStr = symbolTf.getText();
				String name = nameTf.getText();

				if(name.equals("") || symbolStr.equals("") || getDist().equals("")) {
					new AlertDialog(nowFrame, "Alert", true, "정보를 모두 입력하세요.").show();
					return;
				}	// 정보를 모두 입력하지 않은 경우

				char tmp;
				for(int i = 0; i < symbolStr.length(); i++) {
					tmp = symbolStr.charAt(i);
					if(tmp < 48 || tmp > 58) {
						new AlertDialog(nowFrame, "Alert", true, "기호를 잘못 입력하셨습니다.").show();
						return;
					}
				}	// 입력한 기호가 숫자가 아닌경우 탐색
				
				int symbol = Integer.parseInt(symbolStr);
				if(symbol > getCandSize()+1 || symbol < 1) {
					new AlertDialog(nowFrame, "Alert", true, "기호를 잘못 입력하셨습니다.").show();
				}	//기호가 존재하는 기호인지 확인
				else {
					new AlertChoiceDialog(nowFrame, "Add", true,
							"정말 기호" + symbol + "번 " + name +"을 추가하시겠습니까?",symbol, name).show();
				}
			}
		});
	}
}
