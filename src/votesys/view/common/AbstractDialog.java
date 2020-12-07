package votesys.view.common;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 추상클래스를 활용
public abstract class AbstractDialog extends JDialog {

	protected JFrame preFrame;
	
	public AbstractDialog(JFrame preFrame, String title, boolean modal, String text) {
		super(preFrame,title);
		this.preFrame = preFrame;
		setSize(300,200);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.CENTER,100,20));
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER,100,20));
		
		JLabel l = new JLabel(text);
		p1.add(l);
		
		add(p1);
		setModal(modal);
	}
	
}
