package votesys.view.admin;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdminSystem extends JFrame {
	
	public AdminSystem() {
		setSize(400,400);
		setLayout(new FlowLayout(FlowLayout.CENTER,100,60));
		setTitle("Admin System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel l1 = new JLabel("Admin System");
		l1.setFont(new Font("����", Font.BOLD,35));
		p1.add(l1);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		String[] menu = {"������ ����","�ĺ��� �߰�","�ĺ��� ����","��ǥ�� ���","������ ��й�ȣ ����"};
		JComboBox combo = new JComboBox(menu);
		combo.setSize(50, 5);
		p2.add(combo);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER,70,0));
		JButton undo = new JButton("Undo");
		JButton ok = new JButton("OK");
		p3.add(undo);
		p3.add(ok);
		
		add(p1);
		add(p2);
		add(p3);
		
		setVisible(true);
		
		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new AdminMain().show();
				dispose();
			}
		});
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String select = (String) combo.getSelectedItem();
				
				// ������ ���õ� �޴��� switch���� ���Ͽ� ���� Frame ����
				switch(select) {
				case "������ ����":
					new EditDistrict().show();
					break;
				case "�ĺ��� �߰�":
					new AddCandidate().show();
					break;
				case "�ĺ��� ����":
					new DeleteCandidate().show();
					break;
				case "��ǥ�� ���":
					new ShowVoterList().show();
					break;
				case "������ ��й�ȣ ����":
					new ChangePass().show();
					break;
				}
				dispose();
			}
			
		});
	}
	
}
