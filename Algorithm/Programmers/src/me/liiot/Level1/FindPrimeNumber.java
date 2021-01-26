package me.liiot.Level1;

/*
소수 찾기

- 소수를 쉽게 찾기 위해서는 에라토스테네스의 체라는 알고리즘을 사용해야 한다.
참고: https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4
 */
public class FindPrimeNumber {

    public static void main(String[] args) {

        FindPrimeNumber find = new FindPrimeNumber();

        System.out.println(find.solution(10));
    }

    private int solution(int n) {

        int answer = 0;
        // 1. 0과 1은 소수가 아니므로 반복문 제외
        if (n==0 || n==1) return answer;

        boolean[] notPrime = new boolean[n+1];  // 이미 후보에서 제외시켰던 수인지 판별하기 위한 배열
        answer = n-1;
        // 2. 2부터 n까지 각자의 배수를 소수 후보에서 제외시킨다.
        for (int i=2; (i*i)<=n; i++) {
            // 3. j의 시작점은 i의 제곱수부터 시작한다. (i의 제곱수 미만은 이미 다 처리되었기 때문)
            // ex) i가 3일 때 2의 배수는 처리가 된 상황이다. 3은 소수이고, 6은 2의 배수로 처리되었지만
            //     9는 3의 제곱수라 i가 2일때는 처리가 되지 않았으므로 9부터 시작
            for (int j=(i*i); j<=n; j+=i) {
                if (!notPrime[j]) {
                    answer--;
                    notPrime[j] = true;
                }
            }
        }

        return answer;
    }
}
