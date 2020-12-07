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

public class DeleteCandidate extends EditCandidate {

	private JFrame nowFrame;

	public DeleteCandidate() {
		setTitle("Delete Candidate");
		nowFrame = this;
		
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(0,2,10,20));
		JLabel l1 = new JLabel("��ȣ:");
		JTextField symbolTf = new JTextField(8);
		p3.add(l1);
		p3.add(symbolTf);

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
				dispose();
				new AdminSystem().show();
			}

		});
		
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String symbolStr = symbolTf.getText();

				if(symbolStr.equals("") || getDist().equals("")) {
					new AlertDialog(nowFrame, "Alert", true, "������ ��� �Է��ϼ���.").show();
					return;
				}
				
				char tmp;
				for(int i = 0; i < symbolStr.length(); i++) {
					tmp = symbolStr.charAt(i);
					if(tmp < 48 || tmp > 58) {
						new AlertDialog(nowFrame, "Alert", true, "��ȣ�� �߸� �Է��ϼ̽��ϴ�.").show();
						return;
					}
				}

				int symbol = Integer.parseInt(symbolStr);
				if(symbol > getCandSize() || symbol < 1) {
					new AlertDialog(nowFrame, "Alert", true, "��ȣ�� �߸� �Է��ϼ̽��ϴ�.").show();
				}
				else {
					new AlertChoiceDialog(nowFrame, "Delete", true, "���� ��ȣ " + symbol + "���� �����Ͻðڽ��ϱ�?",symbol).show();
				}
			}
		});
	}
}
