package file;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dto.AccountDto;
import singleton.SingletonClass;

public class FileProc {
	private File file = null;
	
	// 저장할 파일명를 매개변수로 받아옴
	public FileProc(String filename) {
		file = new File("d:/" + filename + ".txt");	// 파일을 저장할 경로와 파일명, 확장자를 입력
		
		try {
			// 파일 생성
			if(file.createNewFile()){
				System.out.println("파일 생성 성공!");
			}else {
				System.out.println("파일 생성 실패~");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	// 파일 생성 기능
	public void write() {
		// SingletonClass(ArrayList<AccountDto>()) 선언
		SingletonClass singleton = SingletonClass.getInstance();
		try {
			// 파일 작성을 위한 PrintWriter 클래스 선언
			// 파일을 저장할 경로와 파일명, 확장자가 저장된 매개변수를 받음
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			for (int i = 0; i < singleton.accountList.size(); i++) { // 가계부 첫번째 항목부터 탐색
				AccountDto dto = singleton.accountList.get(i);
				pw.println(dto.print());	// 각 항목당 한줄로 입력
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("파일에 저장되었습니다");
	}
	// 파일 읽기 기능
	public void read() throws IOException {
		SingletonClass singleton = SingletonClass.getInstance();
		
		try {
			// 파일 읽기를 위한 FileReader 클래스 선언
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String str = "";
			// 파일의 모든 라인을 읽을 때까지 반복
			while((str = br.readLine()) != null) {
				// "-" 기호로 구분하여 읽음
				String split[] = str.split("-");
				// 저장된 날짜의 형태가 yyyy-MM-dd 이므로 각 년/월/일을 먼저 받아줌
				String dateStr = split[0] + "-" + split[1] + "-" + split[2];
				// 가계부 항목중 날짜를 입력하기 위한 LocalDate 클래스 변수 선언
				LocalDate date = LocalDate.parse(dateStr, 
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				AccountDto dto = new AccountDto(date,
						split[3],
						split[4],
						Integer.parseInt(split[5]),
						split[6]);
				singleton.accountList.add(dto);
				System.out.println("날짜: " + dateStr + " 용도: " + split[3] + 
						" 수입/지출: " + split[4] + " 금액: " + split[5] + " 내용: " + split[6]);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
