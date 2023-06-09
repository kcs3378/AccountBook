package dao;

import dto.AccountDto;
import singleton.SingletonClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AccountDao {
    private SingletonClass singleton;
    private Scanner sc;

    // AccountDao 클래스 생성자
    public AccountDao() {
    	// SingletonClass(ArrayList<AddressDto>()) 및 Scanner 선언
        singleton = SingletonClass.getInstance();
        sc = new Scanner(System.in);
    }

    // 가계부 항목 추가 기능
    public void insert() {
    	
        System.out.print("날짜 (yyyy-mm-dd): ");
        String dateStr = sc.next();
        // 날짜 계산을 용이하게 하기위해 LocalData 클래스 사용
        // ofPattern(" ") 형식을 따름
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        System.out.print("용도: ");
        String purpose = sc.next();	// 간단한 용도 입력

        System.out.print("수입/지출: ");
        String type = sc.next();	// 수입 or 지출 입력

        System.out.print("금액: ");
        int amount = sc.nextInt();	// 금액은 정수
        
        System.out.print("내용: ");
        String dummy = sc.nextLine();	// scanner.nextLine() 오류 회피용
        String description = sc.nextLine();	// 내용 설명

        // AccountDto 객체 생성 후 singleton list에 추가
        AccountDto account = new AccountDto(date, purpose, type, amount, description);
        singleton.accountList.add(account);
        System.out.println("가계부에 추가되었습니다");
        // 추가한 정보 출력
        for (int i = 0; i < singleton.accountList.size(); i++) {
        	System.out.println(singleton.accountList.get(i).toString());
		}
        
    }

    // 가계부 항목 삭제 기능
    public void delete() {
        System.out.print("삭제할 가계부 항목의 번호: ");
        int index = sc.nextInt()-1;	// 삭제할 가계부 항목의 번호 입력 후 인덱스(번호-1)로 치환

        // 가계부 항목 삭제
        if (index >= 0 && index < singleton.accountList.size()) {	// index 범위(0 ~ 항목 수)
            singleton.accountList.remove(index);	// index에 해당하는 가계부 항목 삭제
            System.out.println("가계부에서 삭제되었습니다");
        } else {									// index가 0 미만이거나 항목 수에서 벗어나면 취소
            System.out.println("유효하지 않은 번호입니다");
        }
    }

    // 가계부 항목 수정 기능
    public void update() {
        System.out.print("수정할 가계부 항목의 번호: ");
        int index = sc.nextInt()-1; // 삭제할 가계부 항목의 번호 입력 후 인덱스(번호-1)로 치환

        // 가계부 항목 수정
        if (index >= 0 && index < singleton.accountList.size()) {	// index 범위(0 ~ 항목 수)
        	// 각각 날짜, 용도, 수입/지출, 금액, 내용 입력
        	System.out.print("날짜 (yyyy-mm-dd): ");
            String dateStr = sc.next();
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.print("용도: ");
            String purpose = sc.next();

            System.out.print("수입/지출: ");
            String type = sc.next();

            System.out.print("금액: ");
            int amount = sc.nextInt();

            System.out.print("내용: ");
            String dummy = sc.nextLine();	// scanner.nextLine() 오류 회피용
            String description = sc.next();

            // AccountDto 객체 생성 후 singleton list에 추가
            AccountDto account = new AccountDto(date, purpose, type, amount, description);
            // 입력한 번호에 해당하는 index에 수정한 항목 입력
            singleton.accountList.set(index, account);

            System.out.println("가계부가 수정되었습니다");
        } else {	// index가 0 미만이거나 항목 수에서 벗어나면 취소
            System.out.println("유효하지 않은 번호입니다");
        }
    }
    
    // 가계부 항목 검색(용도) 기능
    public void searchByPurpose() {
        System.out.print("검색할 용도: ");
        String purpose = sc.next();	// 검색할 항목의 용도를 입력 

        // 가계부 항목 전체 탐색
        for (int i = 0; i < singleton.accountList.size(); i++) {// 가계부 항목 첫번째부터 탐색
            AccountDto account = singleton.accountList.get(i);
            // 입력한 용도와 현재 탐색중인 가계부 항목의 용도가 일치하면 해당 항목 출력
            if (account.getPurpose().equals(purpose)) {	 
                System.out.println(account.toString());
            }
        }
    }
    // 가계부 항목 검색(날짜) 기능
    public void searchByDate() {
        System.out.print("검색할 날짜 (yyyy-mm-dd): ");
        String dateStr = sc.next();	// 검색할 항목의 날짜를 입력
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        for (int i = 0; i < singleton.accountList.size(); i++) {// 가계부 항목 첫번째부터 탐색
            AccountDto account = singleton.accountList.get(i);
            // 입력한 날짜와 현재 탐색중인 가계부 항목의 날짜가 일치하면 해당 항목 출력
            if (account.getDate().equals(date)) {
                System.out.println(account.toString());
            }
        }
    }
    // 가계부 항목 검색(기간) 기능
    public void searchByDateRange() {
        System.out.print("검색할 시작 날짜 (yyyy-mm-dd): ");
        String startDateStr = sc.next();	// 검색할 항목 기간의 시작 날짜를 입력
        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.print("검색할 종료 날짜 (yyyy-mm-dd): ");
        String endDateStr = sc.next();		// 검색할 항목 기간의 종료 날짜를 입력
        LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        for (int i = 0; i < singleton.accountList.size(); i++) {// 가계부 항목 첫번째부터 탐색
            AccountDto account = singleton.accountList.get(i);
            // 날짜 계산을 위해 LocalDate 클래스를 선언하여 가계부 항목의 날짜값 전달
            LocalDate accountDate = account.getDate(); 
            // 입력한 날짜의 범위 내 날짜와 현재 탐색중인 가계부 항목의 날짜에 포함되면 해당 항목 출력
            if (accountDate.isAfter(startDate) && accountDate.isBefore(endDate)) {
                System.out.println(account.toString());
            }
        }
    }

    // 가계부 월별 결산 기능
    public void calculateMonthlySummary() {
        System.out.print("결산할 년도 (yyyy): ");
        String yearStr = sc.next();	// 결산할 년도 입력
        int year = Integer.parseInt(yearStr);

        System.out.print("결산할 월 (mm): ");
        String monthStr = sc.next();// 결산할 월 입력
        int month = Integer.parseInt(monthStr);

        int totalIncome = 0; 	// 총 수입
        int totalExpense = 0; 	// 총 지출

        for (int i = 0; i < singleton.accountList.size(); i++) {// 가계부 항목 첫번째부터 탐색
            AccountDto account = singleton.accountList.get(i);
            // 날짜 계산을 위해 LocalDate 클래스를 선언하여 가계부 항목의 날짜값 전달
            LocalDate accountDate = account.getDate();
            // 입력 년도, 입력 월 각각 가계부 항목의 날짜값과 일치하는 항목의 수입 및 지출만 중첩
            if (accountDate.getYear() == year && accountDate.getMonthValue() == month) {
                // getType으로 해당 항목의 수입/지출을 받아와서 판단
            	if (account.getType().equals("수입")) {
                    totalIncome += account.getAmount();  // 수입 중첩
                } else if (account.getType().equals("지출")) {
                    totalExpense += account.getAmount(); // 지출 중첩
                }
            }
        }
        // 수입, 지출 및 해당 월 총 결산 출력 
        System.out.println("총 수입: " + totalIncome);
        System.out.println("총 지출: " + totalExpense);
        System.out.println("이번 달 총 결산: " + (totalIncome - totalExpense));	// 수입 - 지출
    }
    // 가계부 기간별 결산 기능
    public void calculatePeriodSummary() {
        System.out.print("결산할 시작 날짜 (yyyy-mm-dd): ");
        String startDateStr = sc.next();	// 결산할 항목 기간의 시작 날짜 입력
        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.print("결산할 종료 날짜 (yyyy-mm-dd): ");
        String endDateStr = sc.next();		// 결산할 항목 기간의 종료 날짜 입력
        LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        int totalIncome = 0;	// 총 수입
        int totalExpense = 0;	// 총 지출

        for (int i = 0; i < singleton.accountList.size(); i++) {// 가계부 항목 첫번째부터 탐색
            AccountDto account = singleton.accountList.get(i);
            // 날짜 계산을 위해 LocalDate 클래스를 선언하여 가계부 항목의 날짜값 전달
            LocalDate accountDate = account.getDate();
         // 입력 날짜값과 일치하는 가계부 항목의 수입 및 지출만 중첩
            if (accountDate.isAfter(startDate) && accountDate.isBefore(endDate)) {
            	// getType으로 해당 항목의 수입/지출을 받아와서 판단
                if (account.getType().equals("수입")) {
                    totalIncome += account.getAmount();		// 수입 중첩
                } else if (account.getType().equals("지출")) {
                    totalExpense += account.getAmount();	// 지출 중첩
                }
            }
        }
        // 수입, 지출 및 해당 기간 총 결산 출력 
        System.out.println("총 수입: " + totalIncome);
        System.out.println("총 지출: " + totalExpense);
        System.out.println("기간 별 결산: " + (totalIncome - totalExpense));	// 수입 - 지출
    }
//    // 파일 생성
// 	public File addressWrite() throws Exception  {
// 		SingletonClass s = SingletonClass.getInstance();
// 		
// 		String filePath = "addressList.txt";
// 		
// 		File file = new File(filePath);
// 		file.createNewFile();
// 		
// 		FileWriter fw = new FileWriter(file);		//파일 객체 생성 
// 		BufferedWriter bw = new BufferedWriter(fw);	//buffer에 삽입
// 		PrintWriter pw = new PrintWriter(bw);		//print로 입력
// 		
// 		for(int i = 0; i < singleton.accountList.size(); i++) {
// 			pw.println(singleton.accountList.get(i));
// 		}
//
// 		pw.close();
// 		System.out.println("정보 기입 완료.");
// 		return file;
// 	}
}
