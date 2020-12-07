package votesys.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import votesys.domain.Voter;

public class VoterIO {
	private String name = "";
	private String secNum = "";
	private Voter voter;
	private HashMap<String,Voter> vMap = new HashMap<String,Voter>();
	private Vector<Voter> vVec = new Vector<Voter>();

	private static VoterIO uniqueInstance ;

	public static VoterIO getInstance()
	{
		if (uniqueInstance == null) {
			uniqueInstance = new VoterIO() ;
		}
		return uniqueInstance ;
	}

	private VoterIO() {}


	public void saveVoterInfo(String name, String secNum) {
		this.name = name;
		this.secNum = secNum;
	}

	public Vector<Voter> returnVoterList() {
		return vVec;
	}


	//투표자목록.txt
	
	public void loadVoterFile() {
		String fName = ".\\lib\\VoteSystem\\Admin\\투표자목록.txt";
		String tmpName;
		String tmpSecNum;

		try {
			File f = new File(fName);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);

			String line;
			StringTokenizer st;
			while((line = br.readLine()) != null) {
				st = new StringTokenizer(line,"\t");
				tmpName = st.nextToken();
				tmpSecNum = st.nextToken();

				voter = new Voter(tmpName,tmpSecNum);
				vVec.add(voter);
				vMap.put(tmpSecNum,voter);
			}
			br.close();
			fr.close();
		}catch (IOException e) {
			System.out.println("로드할 수 없습니다.");
		}

	}

	//중복투표 검색
	public boolean searchOverlap() {
		if(vMap.containsKey(secNum) == true) {
			return false;
		}
		else {
			return true;
		}
	}

	//투표자 목록 저장
	public void saveVoterFile() {
		String fName = ".\\lib\\VoteSystem\\Admin\\투표자목록.txt";
		File f = new File(fName);

		try {
			FileWriter fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("\n" + name);
			bw.write("\t");
			bw.write(secNum);
			bw.close();
			fw.close();
		}catch (IOException e) {
			System.out.println("저장할 수 없습니다.");
		}
	}
}
