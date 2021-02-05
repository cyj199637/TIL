package me.liiot.Level2;

import java.util.Arrays;

/*
전화번호 목록
 */
public class PhoneNumberList {

    public static void main(String[] args) {

        String[] phoneNumbers1 = {"119", "97674223", "1195524421"};
        System.out.println(solution(phoneNumbers1));

        // 아래와 같은 케이스는 정렬을 하지 않으면 123이 12의 접두사가 아니므로  true를 리턴한다.
        // 따라서, 정렬을 하거나 뒤에 있는 원소가 앞에 있는 원소의 접두사인지 한 번 더 검사해야 한다.
        String[] phoneNumbers2 = {"123", "12"};
        System.out.println(solution(phoneNumbers2));
    }

    public static boolean solution(String[] phoneNumbers) {

        Arrays.sort(phoneNumbers);
        int length = phoneNumbers.length;
        for (int i=0; i<length; i++) {
            for (int j=i+1; j<length; j++) {
                if (phoneNumbers[j].startsWith(phoneNumbers[i]))
                    return false;
            }
        }

        return true;
    }
}
