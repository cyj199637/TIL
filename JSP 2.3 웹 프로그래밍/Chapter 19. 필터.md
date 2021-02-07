# Chapter 19. 필터

## 01. 필터란 무엇인가?

**필터**: HTTP 요청과 응답을 변경할 수 있는 재사용 가능한 클래스

* HTTP 요청과 최종 자원(JSP, 서블릿 등) 사이에 위치하여 요청 정보를 알맞게 변경 가능

  ex) JSP/서블릿 등을 실행하기 전에 요청이 올바른지 또는 자원 접근 권한을 가졌는지 등을 확인

* 최종 자원과 HTTP 응답 사이에 위치하여 최종 자원의 요청 결과를 알맞게 변경 가능

  ex) JSP나 서블릿이 생성한 응답 데이터의 변경/취소 기능 구현

![img](../images/21_tistory_2008_12_08_14_39_493cb30ada51d)

* 자원이 받는 요청 정보는 필터가 변경한 요청 정보가 되며, 클라이언트가 보는 응답 정보도 필터가 변경한 응답 정보이다.
* 여러 개의 필터가 모여 하나의 **필터 체인**을 형성할 수도 있다.
* 필터는 클라이언트의 요청을 필터 체인의 다음 단계가 아닌 다른 자원의 결과를 클라이언트에 전송할 수도 있다.
* 요청의 필터 적용 순서와 응답의 필터 적용 순서는 서로 반대이다.





## 02. 필터의 구현

* javax.servlet.Filter 인터페이스
* javax.servlet.ServletRequestWrapper 클래스: 필터가 요청을 변경한 결과를 저장하는 래퍼
* javax.servlet.ServletResponseWrapper 클래스: 필터가 응답을 변경하기 위해 사용하는 래퍼

### 2.1 Filter 인터페이스

서블릿 컨테이너는 사용자가 특정 자원을 요청했을 때 그 자원 사이에 필터가 존재하는 경우, 요청이 있을 때마다 필터 객체의 doFilter() 메소드를 호출한다.

~~~ java
public class LoginCheckFilter implements Filter {
    @Override
    // 필터 초기화
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    // 필터 기능 수행
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // 1. request 파라미터를 이용하여 요청의 필터 작업 수행 → ServletRequestWrapper
        ...
        // 2. 요청의 필터링 결과를 다음 필터에게 전달
        chain.doFilter(request, response);
        // 3. response 파라미터를 이용하여 응답의 필터링 작업 수행 → ServletResponseWrapper
        //  → 체인을 통해 전달된 응답 데이터를 변경하여 그 결과를 클라이언트에 전송
        ...
    }

    @Override
    // 웹 컨테이너에서 필터 삭제
    public void destroy() {
        // 주로 필터가 사용한 자원 반납
    }
}
~~~



### 2.2 필터 설정하기: web.xml 이용

~~~ xml
<!-- 웹 어플리케이션에서 사용할 필터 지정 -->
<filter>
    <filter-name>필터 이름</filter-name>
    <filter-class>필터 클래스의 완전한 이름</filter-class>
    <!-- 필터를 초기화할 때 전달할 파라미터 설정 -->
    <init-param>
        <param-name>파라미터 이름</param-name>
        <param-value>파라미터 값</param-value>
    </init-param>
</filter>

<!-- 특정 자원에 대해 어떤 필터를 사용할지 지정 -->
<filter-mapping>
    <filter-name>필터 이름</filter-name>
    <url-pattern>*.jsp</url-pattern> <!-- 특정 URI에 대해 필터링 -->
</filter-mapping>

<filter-mapping>
    <filter-name>필터 이름</filter-name>
    <servlet-name>서블릿 이름</servlet-name> <!-- 특정 서블릿으로 오는 모든 요청에 대해 필터링 -->
    <dispatcher>INCLUDE</dispatcher> <!-- 필터가 적용되는 시점 지정 -->
</filter-mapping>
~~~

* `<dispatcher>`
  * REQUEST: 클라이언트의 요청인 경우 필터 적용
  * FORWARD: forward()를 통해 제어 흐름을 이동하는 경우에만 필터 적용
  * INCLUDE: include()를 통해 포함되는 경우에만 필터 적용
* 동시에 여러 개의 필터 매핑에 적용되는 경우, web.xml 파일에 표시한 순서대로 적용됨



### 2.3 필터 설정하기: @WebFilter 애노테이션 이용

Filter 클래스가 @WebFilter 애노테이션을 가지면 자동으로 필터로 등록된다.

~~~ java
@WebFilter(filterName = "xsltFilter", urlPatterns = { "/xml/*" })
public class XSLTFilter implements Filter {
	...
}
~~~

* filterName: 필터 이름
* urlPatterns: 필터를 적용할 URL 패턴 목록
* servletNames: 필터를 적용할 서블릿 목록
* initParams: 초기화 파라미터 목록
* dispatcherTypes: 필터를 적용할 범위



### 2.4 요청 및 응답 래퍼 클래스

ServletRequestWrapper와 ServletResponseWrapper로 요청과 응답을 변경한다. 그러나 대부분 필터는 HTTP 프로토콜에 대한 요청과 응답을 필터링하므로 **HTTPServletRequestWrapper**와 **HTTPServletResponseWrapper**를 상속받아 구현하는 것이 좋다.

* 요청 정보를 변경하여 최종 자원(서블릿, JSP, HTML 등)에 전달
* 최종 자원으로부터의 응답을 변경하여 새로운 응답 정보를 클라이언트에 전달

~~~ java
public class NullParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> parameterMap = null;

    // 1. HTTPServletRequestWrapper나 HTTPServletResponseWrapper를 상속받은 클래스 생성
    public NullParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        parameterMap = new HashMap<String, String[]>(request.getParameterMap());
    }

    // 2. 변경하고 싶은 요청/응답 정보를 추출하는 메소드를 재정의하여 변경한 정보를 제공하도록 구현
    public void checkNull(String[] parameterNames) {
        for (int i = 0; i < parameterNames.length; i++) {
            if (!parameterMap.containsKey(parameterNames[i])) {
                String[] values = new String[] { "" };
                parameterMap.put(parameterNames[i], values);
            }
        }
    }
    ...
}
~~~

~~~ java
public class NullParameterFilter implements Filter {
    ...
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // 3. 구현한 래퍼 클래스의 객체를 FilterChain.doFilter()에 넘겨줌
        NullParameterRequestWrapper requestWrapper =
            new NullParameterRequestWrapper((HttpServletRequest) request);
        requestWrapper.checkNull(parameterNames);

        chain.doFilter(requestWrapper, response);
    }
    ...
}
~~~





## 03. 필터의 응용

* 사용자 인증
* 캐싱 필터
* 자원 접근에 대한 로깅
* 응답 데이터 변환 - HTML 변환, 응답 헤더 변환, 데이터 암호화 등
* 공통 기능 실행

필터를 적용함으로써 최종 자원들에 공통적으로 적용되는 기능들을 각각의 자원이 아닌 필터 한 곳에만 코드를 추가하면 되기 때문에 관심사를 분리할 수 있고, 유지보수성이 향상된다는 장점을 가질 수 있다.