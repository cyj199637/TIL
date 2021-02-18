package me.liiot.Level2;

/*
큰 수 만들기
 */
public class CreateBigNumber {

    public static void main(String[] args) {

        System.out.println(solution2("1231234", 3));
    }

    public static String solution(String s, int k) {

        StringBuilder sb = new StringBuilder();
        int targetLength = s.length() - k;

        int remain = targetLength;
        int end = s.length() - remain;
        char[] nums = {'9', '8', '7', '6', '5', '4', '3', '2', '1', '0'};
        while (sb.length() < targetLength) {
            for (char n : nums) {
                int index = s.indexOf(n);
                if (index >= 0 && index <= end) {
                    sb.append(s.charAt(index));
                    s = s.substring(index+1);
                    remain--;
                    end = s.length() - remain;

                    break;
                }
            }
        }

        return sb.toString();
    }

    public static String solution2(String s, int k) {

        StringBuilder sb = new StringBuilder(s);
        for (int i=0; i+1<s.length() && k>0; i++) {
            if (sb.charAt(i) < sb.charAt(i+1)) {
                sb.deleteCharAt(i);
                k--;    // 제거해야 할 숫자 개수가 줄어들었기 때문
                i--;    // 앞 숫자가 하나 삭제되었으니 인덱스도 하나 앞으로 땡겨야 함
            }
        }

        return sb.toString();
    }
}
