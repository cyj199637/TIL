package me.liiot.Level1;

/*
신규 아이디 추천
 */
public class RecommendNewId {

    public static void main(String[] args) {

        RecommendNewId recom = new RecommendNewId();

        System.out.println(recom.solution("abcdefghijklmn.p"));
    }

    private String solution(String newId) {

        String result = "";
        if (newId == null)
            return result;

        // 1단계
        result = newId.toLowerCase();

        // 2단계
        result = result.replaceAll("[^a-z0-9-_.]", "");

        // 3단계
        result = result.replaceAll("[.]+", ".");

        while (result.startsWith(".") || result.endsWith(".") || result.length()>=16) {
            // 4단계
            if (result.length() > 0) {
                if (result.startsWith("."))
                    result = result.substring(1);

                if (result.endsWith("."))
                    result = result.substring(0, result.length() - 1);
            }

            // 5단계
            if (result.length() == 0)
                result = "a";

            // 6단계
            if (result.length() >= 16)
                result = result.substring(0, 15);
        }

        // 7단계
        String last = result.substring(result.length()-1);
        while (result.length() < 3) {
            result += last;
        }

        return result;
    }
}
