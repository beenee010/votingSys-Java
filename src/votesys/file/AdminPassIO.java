package votesys.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//������ ��й�ȣ ����
public class AdminPassIO {
	private String pass = "";

	public void fileReader() {
		String fileName = ".\\lib\\VoteSystem\\Admin\\AdminPass.txt";
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
			this.pass = fileReader.readLine();
			fileReader.close();
		} catch(IOException e) {
			System.out.println("������ �ε��� �� �����ϴ�.");
		}
	}

	public String getText() {
		return pass;
	}


	//������ ��й�ȣ ����
	public void fileWriter(String pass) {

		String fileName = ".\\lib\\VoteSystem\\Admin\\AdminPass.txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
			bw.write(pass);
			bw.close();
		} catch(IOException e) {
			System.out.println("���� ���忡 �����Ͽ����ϴ�.");
		}
	}

}
