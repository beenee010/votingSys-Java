package votesys.view.common;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 추상클래스를 상속
public class AlertDialog extends AbstractDialog{

	public AlertDialog(JFrame preFrame, String title, boolean modal, String text) {
		super(preFrame,title,modal,text);

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER,100,0));
		JButton ok = new JButton("OK");
		p2.add(ok);
		add(p2);

		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(title.equals("Finish")) {
					preFrame.dispose();
				}
				else {
					dispose();
				}
			}

		});
	}

}
