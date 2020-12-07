package votesys.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//관리자 비밀번호 관리
public class AdminPassIO {
	private String pass = "";

	public void fileReader() {
		String fileName = ".\\lib\\VoteSystem\\Admin\\AdminPass.txt";
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
			this.pass = fileReader.readLine();
			fileReader.close();
		} catch(IOException e) {
			System.out.println("파일을 로드할 수 없습니다.");
		}
	}

	public String getText() {
		return pass;
	}


	//관리자 비밀번호 변경
	public void fileWriter(String pass) {

		String fileName = ".\\lib\\VoteSystem\\Admin\\AdminPass.txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
			bw.write(pass);
			bw.close();
		} catch(IOException e) {
			System.out.println("파일 저장에 실패하였습니다.");
		}
	}

}
