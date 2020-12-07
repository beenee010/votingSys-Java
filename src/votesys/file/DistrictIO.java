package votesys.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class DistrictIO {

	Vector<String> distList = new Vector<String>();
	//���� �޾ƿ���
	File[] dists;
	
	public DistrictIO() {
		dists = new File(".\\lib\\VoteSystem\\District").listFiles();
	}

	public Vector<String> returnDist(){

		for(File dist : dists) {	//���ϵ� ó��
			if(dist.isFile()) {
				String fName = dist.getName();
				int txt = fName.lastIndexOf(".txt");	//Ȯ���� ����
				fName = fName.substring(0,txt);
				distList.add(fName);
			}
		}
		return distList;
	}

	public boolean fileCreate(String dist) {

		String fileName = ".\\lib\\VoteSystem\\District\\" + dist + ".txt";
		File tmp = new File(fileName);

		if(tmp.isFile()) {
			return false;
		}
		else {
			try {
				FileWriter fw = new FileWriter(tmp);
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write("Empty\tEmpty\tEmpty");

				bw.close();
				fw.close();
			} catch(IOException e) {
				System.out.println("���� ���� ����");
			}
			return true;
		}
	}

	public boolean fileDelete(String dist) {
		String fileName = ".\\lib\\VoteSystem\\District\\" + dist + ".txt";
		File tmp = new File(fileName);

		if(tmp.exists()) {
			tmp.delete();
			return true;
		}
		else {
			return false;
		}
	}
}
