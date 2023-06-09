package dto;

import java.time.LocalDate;

public class AccountDto {
    private LocalDate date;
    private String purpose;
    private String type;
    private int amount;
    private String description;

    // AccountDto 클래스 생성자
    // 각각 날짜, 용도, 수입/지출, 금액, 내용을 클래스 선언 시 입력해줘야 함
    public AccountDto(LocalDate date, String purpose, String type, int amount, String description) {
        this.date = date;
        this.purpose = purpose;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }
    // 날짜 리턴
    public LocalDate getDate() {
        return date;
    }
    // 용도 리턴
    public String getPurpose() {
        return purpose;
    }
    // 수입/지출 리턴
    public String getType() {
        return type;
    }
    // 금액 리턴
    public int getAmount() {
        return amount;
    }
    // 내용 리턴
    public String getDescription() {
        return description;
    }
    
    // 날짜 수정
    public void setDate(LocalDate date) {
        this.date = date;
    }
    // 용도 수정
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    // 수입/지출 수정
    public void setType(String type) {
        this.type = type;
    }
    // 금액 수정
    public void setAmount(int amount) {
        this.amount = amount;
    }
    // 내용 수정
    public void setDescription(String description) {
        this.description = description;
    }
    
    // 가계부 항목 출력 기능
    @Override
    public String toString() {
        return "날짜: " + date + ", 용도: " + purpose + ", 수입/지출: " + type + ", 금액: " + amount + ", 내용: " + description;
    }
    // 파일 생성 시 해당 파일에 입력하기 위한 기능
    // 파일을 읽을 때 용이하도록 각 항목을 '-'로 구분
    public String print() {
        return date + "-" + purpose + "-" + type + "-" + amount + "-" + description;
    }
}
