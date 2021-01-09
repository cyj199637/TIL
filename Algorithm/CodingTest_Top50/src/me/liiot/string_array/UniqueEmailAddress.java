package me.liiot.string_array;

import java.util.HashSet;
import java.util.Set;

/*
11) Unique Email Address
모든 이메일은 @ 기호로 구분 된 로컬 이름과 도메인 이름으로 구성됩니다.
예를 들어 alice@leetcode.com에서 alice는 로컬 이름이고 leetcode.com은 도메인 이름입니다.
이 이메일에는 소문자 외에 '.'또는 '+'가 포함될 수 있습니다.
이메일 주소의 로컬 이름의 문자 사이에 마침표를 추가하면 그곳으로 전송된 메일은 점은 없지만 문자는 같은 로컬 이름의 주소로도 전달됩니다.
예를 들어 "alice.z@leetcode.com"에 메일을 보내면 "alicez@leetcode.com"에도 메일이 전달됩니다.
로컬 이름에 더하기 '+'를 추가하면 첫 번째 더하기 기호 뒤의 모든 항목이 무시됩니다. 이를 통해 특정 이메일을 필터링 할 수 있습니다.
예를 들어 m.y+name@email.com에 보낸 메일은 my@email.com으로 전달됩니다.
이 두 규칙을 동시에 사용할 수 있으며, 도메인 이름에는 적용되지 않습니다.
주어진 이메일 목록의 각 주소로 이메일을 보냈을 때, 실제로 메일을 받는 주소는 몇 개입니까?

- 이메일 개수를 셀 때, 중복된 이메일은 세지 않으므로 Set 사용
 */
public class UniqueEmailAddress {

    public static void main(String[] args) {

        String[] emails = {"test.email+alex@leetcode.com",
                           "test.e.mail+bob.cathy@leetcode.com",
                           "testemail+david@lee.tcode.com"};

        System.out.println(solve(emails));
    }

    private static int solve(String[] emails) {

        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String localName = makeLocalName(email);
            String domainName = email.substring(email.indexOf("@"));

            set.add(localName+domainName);
        }

        return set.size();
    }

    private static String makeLocalName(String email) {

        StringBuilder localName = new StringBuilder();

        char[] chars = email.toCharArray();
        for (char c : chars) {
            if (c == '.') {
                continue;
            } else if (c == '+' || c == '@') {
                break;
            } else {
                localName.append(c);
            }
        }

        return localName.toString();
    }
}
