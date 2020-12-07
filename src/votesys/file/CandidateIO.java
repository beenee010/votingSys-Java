package votesys.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import votesys.domain.Candidate;


public class CandidateIO {

	private String dist = "";
	private Vector<Candidate> candidate = new Vector<Candidate>();
	private Candidate c;

	private static CandidateIO uniqueInstance ;

	public static CandidateIO getInstance()
	{
		if (uniqueInstance == null) {
			uniqueInstance = new CandidateIO() ;
		}
		return uniqueInstance ;
	}

	private CandidateIO() {}

	public void setDistrict(String dist) {
		this.dist = dist;
	}

	public boolean fileReader() {
		candidate.clear();
		String symbol;
		String name;
		String count;

		String fileName = ".\\lib\\VoteSystem\\District\\" + dist + ".txt";
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			String line;
			StringTokenizer st;
			while((line = br.readLine()) != null) {
				if(line.contains("Empty")) {
					candidate.add(0, null);
				}
				else {
					st = new StringTokenizer(line,"번\t");
					symbol = st.nextToken();
					name = st.nextToken();
					count = st.nextToken();
					candidate.add(Integer.parseInt(symbol)-1, new Candidate(symbol,name,count));
				}
			}
			br.close();
			fr.close();
		}catch (IOException e) {
			System.out.println("파일 로드 실패");
			return false;
		}
		return true;
	}

	public Vector<Candidate> returnCand(){	//candidate vector 리턴
		return candidate;
	}

	//후보자 추가
	public boolean addCandidate(int num, String name) {

		//기호를 잘못 입력했을 경우
		if(num <= 0 || num > candidate.size()+1) {
			return false;
		}
		else {
			int tmp = num-1; //인덱스로 저장하기 때문에 -1
			String symbol = Integer.toString(num);
			System.out.println(num + "  " + name);
			candidate.add(tmp, new Candidate(symbol,name,"0"));
			fileSave(-1);

			return true;
		}
	}

	//후보자 삭제
	public boolean delCandidate(int num) {

		if(num < 1 || num-1 > candidate.size()) {
			return false;
		}
		else {
			candidate.remove(num-1);
			fileSave(-1);	
			return true;
		}
	}

	//득표수 저장
	public void addNumofVotes(int num) {

		c = candidate.get(num-1);
		c.addCount();
		fileSave(-1);

	}
	
	public void fileSave(int num) {

		String txt;
		//후보 추가 및 득표수 저장
		if(num == -1)
			txt = ".\\lib\\VoteSystem\\District\\" + dist +".txt";
		//투표 용지 파일 출력 및 저장
		else
			txt = ".\\lib\\VoteSystem\\Votes\\" + "투표용지" +".txt";
		try {
			File f = new File(txt);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < candidate.size(); i++) {
				c = candidate.get(i);
				if(c == null) break;
				bw.write(Integer.toString(i+1) + "번");
				bw.write("\t");
				bw.write(c.getName());
				if(num == i+1) {
					bw.write("\tO");
				}
				if(num == -1) {
					bw.write("\t");
					bw.write(c.getCount());
				}
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("파일을 저장할 수 없습니다.");
		}
	}

}

