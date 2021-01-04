## Item 09. try-finally보다는 try-with-resources를 사용하라

반납해야 하는 자원이 여러 개일 때 `try-finally` 를 사용하면 코드가 지저분해진다.

~~~ java
static String firstLineOfFile(String path) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(path));
    try {
        return br.readLine();
    } finally {
        br.close();
    }
}
~~~

~~~ java
static void copy(String src, String dst) throws IOException {
    InputStream in = new FileInputStream(src);
    try {
        OutputStream out = new FileOutputStream(dst);
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        } finally {
            out.close();
        }
    } finally {
        in.close();
    }
}
~~~

코드의 가독성 외에도 한 가지 문제가 더 있다. 만약 첫 번째 코드를 실행하다가 기기에 물리적인 문제가 생긴다면 `firstLineOfFile()` 안의 `readLine()` 이 예외를 던지고, 같은 이유로 `close()` 도 실패할 것이다. 이런 상황이라면 두 번째 예외가 첫 번째 예외를 덮어 버리고, 이로 인해 첫 번째 예외에 관한 정보는 남지 않게 되어 디버깅을 어렵게 만든다. (이를 해결하려고 코드를 수정하기에는 코드가 너무 지저분해진다.)



### try-with-resources

`try-with-resources` 구문을 사용하면 위의 문제가 해결된다. 이 구조를 사용하려면 해당 자원이 `AutoCloseable` 인터페이스를 구현해야 한다.

~~~ java
static String firstLindOfFile(String path) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
~~~

~~~ java
static void copy(String src, String dst) throws IOException {
    try (InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dst);) {
        byte[] buf = new byte[BUFFER_SIZE];
        int n;
        while ((n = in.read(buf)) >= 0) {
            out.write(buf, 0, n);
        }
    }
}
~~~

첫 번째 코드의 `firstLineOfFile()` 의 `readLine()` 과 `close()` 호출 양쪽에서 예외가 발생하면, 이제 `close()` 에서 발생한 예외는 숨겨지고 `readLine()` 에서 발생한 예외가 기록된다. 숨겨진 예외도 스택 추적 내역에 '숨겨졌다' 라는 꼬리표를 달고 출력된다. `Throwable`의 `getSuppressed` 메소드를 사용해서 뒤에 쌓여있는 예외를 프로그램 코드에서 가져올 수도 있다.

`try-with-resources` 문에서도 `catch` 절을 사용할 수 있다.