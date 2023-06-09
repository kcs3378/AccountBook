package singleton;

import dto.AccountDto;

import java.util.ArrayList;
import java.util.List;

public class SingletonClass {
    private static SingletonClass instance;
    public List<AccountDto> accountList;

    // SingletonClass 클래스 생성자
    // AccountDto 클래스 리스트 선언 
    private SingletonClass() {
        accountList = new ArrayList<AccountDto>();
    }

    public static SingletonClass getInstance() {
        if (instance == null) {
            instance = new SingletonClass();
        }
        return instance;
    }
}
