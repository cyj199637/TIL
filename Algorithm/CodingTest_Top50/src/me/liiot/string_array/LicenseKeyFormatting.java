package me.liiot.string_array;

/*
08) License Key Formatting
숫자, 영문자, 대시(-)로만 구성된 문자열 표시되는 라이센스 키가 제공됩니다.
문자열은 N개의 대시로 N + 1개의 그룹으로 나눠집니다.
첫 번째 그룹은 1개 이상 K개 미만의 문자를 포함하고, 나머지 그룹은 정확히 K개의 문자를 포함하도록 라이센스 키를 다시 포맷하려고 합니다.
또한 두 그룹 사이에는 대시가 들어가야 하며 모든 소문자는 대문자로 변환되어야 합니다.
 */
public class LicenseKeyFormatting {

    public static void main(String[] args) {

        String s1 = "8F3Z-2e-9-w";
        String s2 = "8-5g-3-J54D-u";
        int k = 4;

        System.out.println(solve(s1, k));
        System.out.println(solve(s2, k));
    }

    private static StringBuilder solve(String s, int k) {

        String tempS = s.replace("-", "");
        tempS = tempS.toUpperCase();

        StringBuilder newKey = new StringBuilder();
        newKey.append(tempS);

        int length = tempS.length();
        for (int i=k; i<length; i=i+k) {    // 끝에서 K개 만큼 잘라 그 사이에 "-"를 넣어야 하므로
            newKey.insert(length-i, "-");   // length가 9일때 length-i는 5, 1, ...
        }

        return newKey;
    }
}
