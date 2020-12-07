package votesys.view.admin;

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

import votesys.file.DistrictIO;
import votesys.view.common.AlertDialog;

public class EditDistrict extends JFrame{

	private JFrame nowFrame;
	private JList<String> list;
	private DistrictIO distIO;
	private Vector<String> distVec;
	private boolean boo;	// switch btn을 제어하기 위한 변수

	public EditDistrict(){
		nowFrame = this;
		setSize(400,500);
		setTitle("Edit District");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER,100,20));
		boo = false;

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER,100,0));
		JLabel l1 = new JLabel("Edit District");
		l1.setFont(new Font("돋움", Font.BOLD,35));
		p1.add(l1);

		JScrollPane p2 = new JScrollPane();
		p2.setPreferredSize(new Dimension(150,180));
		distIO = new DistrictIO();
		distVec = distIO.returnDist();
		list = new JList<>();
		list.setSize(new Dimension(150,180));
		list.setFont(new Font("굴림",Font.BOLD,15));
		list.setListData(distVec);
		p2.setViewportView(list);

		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER,100,0));
		JButton switchBtn = new JButton("Switch");		
		p3.add(switchBtn);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(0,2,10,0));
		JLabel l2 = new JLabel("입력:");
		JTextField dist = new JTextField(8);
		p4.add(l2);
		p4.add(dist);
		
		JPanel p5 = new JPanel();
		p5.setLayout(new GridLayout(0,2,60,0));
		JButton undo = new JButton("Undo");
		JButton ok = new JButton("OK");
		p5.add(undo);
		p5.add(ok);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);

		// 버튼을 클릭할 경우의 액션
		switchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(!boo) {
					switchBtn.setText("Add");
					boo = true;
				}
				else {
					switchBtn.setText("Del");
					boo = false;
				}
			}

		});
		
		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new AdminSystem().show();
				dispose();
			}
			
		});
		
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if((switchBtn.getText()).equals("Switch")) {
					new AlertDialog(nowFrame,"Warning",true,"Switch를 눌러주세요").show();
					return;
				}
				String name = dist.getText();
				
				if(boo) {
					if(distIO.fileCreate(name)) {
						new AlertDialog(nowFrame,"Success",true,"파일을 생성하였습니다.").show();
					}
					else {
						new AlertDialog(nowFrame,"Fail",true,"파일을 생성할 수 없습니다.").show();
					}
				}
				else {
					if(distIO.fileDelete(name)) {
						new AlertDialog(nowFrame,"Success",true,"파일을 삭제하였습니다.").show();
					}
					else {
						new AlertDialog(nowFrame,"Fail",true,"파일을 삭제할 수 없습니다.").show();
					}
				}
			}
			
		});
	}

}
