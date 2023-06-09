package main;

import dao.AccountDao;
import file.FileProc;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        AccountDao accountDao = new AccountDao();
        FileProc fp = new FileProc("accountBook");

        while (true) {
        	// 가계부 메뉴
            System.out.println("==== 가계부 ====");
            System.out.println("1. 추가");
            System.out.println("2. 삭제");
            System.out.println("3. 수정");
            System.out.println("4. 검색 (용도)");
            System.out.println("5. 검색 (날짜)");
            System.out.println("6. 검색 (기간)");
            System.out.println("7. 월별 결산");
            System.out.println("8. 기간별 결산");
            System.out.println("9. 항목 저장");
            System.out.println("10. 항목 읽기");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택: ");

            int choice = sc.nextInt();	// 기능 사용을 위한 가계부 메뉴 번호 입력

            switch (choice) {
                case 1:
                    accountDao.insert();	// 가계부 항목 추가 함수 실행
                    break;
                case 2:
                    accountDao.delete();	// 가계부 항목 삭제 함수 실행
                    break;
                case 3:
                    accountDao.update();	// 가계부 항목 수정 함수 실행
                    break;
                case 4:
                    accountDao.searchByPurpose();	// 가계부 항목 검색(용도) 함수 실행
                    break;
                case 5:
                    accountDao.searchByDate();	// 가계부 항목 검색(날짜) 함수 실행
                    break;
                case 6:
                    accountDao.searchByDateRange();	// 가계부 항목 검색(기간) 함수 실행
                    break;
                case 7:
                    accountDao.calculateMonthlySummary();	// 가계부 월별 결산 함수 실행
                    break;
                case 8:
                    accountDao.calculatePeriodSummary();	// 가계부 기간별 결산 함수 실행
                    break;
                case 9:
                    fp.write();	// 가계부 기간별 결산 함수 실행
                    break;
                case 10:
                    fp.read();	// 가계부 기간별 결산 함수 실행
                    break;
                case 0:	// 0번 입력 시 종료
                    System.out.println("프로그램을 종료합니다");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("유효하지 않은 메뉴 선택입니다");
            }
            System.out.println();
        }
    }
}
