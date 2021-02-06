# Chapter 17. 서블릿 기초

### JSP vs Servlet?

#### JSP

: 서블릿 기반의 스크립트 기술

* 스크립트 기술: 미리 약속된 규정에 따라 간단한 키워드를 조합하여 입력하면, 실행 시점에 각각의 키워드들에 매핑된 

  ​						코드로 변환 후 실행되는 형태

* 서블릿의 단점 보완
* HTML 내부에 자바 코드 삽입

#### Servlet

: 자바 언어로 웹 어플리케이션을 더 개발하기 쉽게 만든 API

* 서버에서 웹 페이지 등을 동적으로 생성하거나 데이터 처리를 수행하기 위해 자바로 작성된 프로그램
* 자바 코드 안에 HTML 코드 삽입





## 01. 서블릿 기초

#### 서블릿 개발 과정

1. 서블릿 규약에 따라 자바 코드 작성
2. 자바 코드를 컴파일해서 클래스 파일 생성
3. 클래스 파일을 `/WEB-INF/classes` 폴더에 패키지에 알맞게 위치
4. web.xml 파일에 서블릿 클래스 설정
   * 서블릿으로 사용할 클래스 등록
   * 서블릿과 URL 간의 매핑
5. 톰캣 등의 컨테이너 실행
6. 웹 브라우저에서 확인





## 02. 예제 프로젝트 생성

### 2.1 서블릿 구현

~~~ java
// HttpServlet 클래스를 상속받아 서블릿 클래스 작성
public class NowServlet extends HttpServlet {

	@Override
    // 처리하고자 하는 HTTP 메소드에 따라 알맞은 doXXX() 메소드를 재정의
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // 응답 컨텐츠 타입 지정
		response.setContentType("text/html; charset=utf-8");

        // 응답을 내보낼 출력 스트림 생성
		PrintWriter out = response.getWriter();
        // 전송할 응답 데이터 전달
		out.println("<html>");
		out.println("<head><title>현재시간</title></head>");
		out.println("<body>");
		out.println("현재 시간은");
		out.println(new Date());
		out.println("입니다.");
		out.println("</body></html>");
	}

}
~~~



### 2.2 web.xml로 매핑하기

~~~ xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
		http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
	version="3.1">

    <!-- 서블릿 클래스 등록 -->
	<servlet>
        <!-- 해당 서블릿을 참조할 때 사용할 이름 -->
		<servlet-name>now</servlet-name>
        <!-- 서블릿으로 사용할 클래스의 완전한 이름 -->
		<servlet-class>example.NowServlet</servlet-class>
	</servlet>

    <!-- 서블릿과 URL 간의 매핑 -->
	<servlet-mapping>
		<servlet-name>now</servlet-name> <!-- 매핑할 서블릿 이름 -->
		<url-pattern>/now</url-pattern> <!-- 해당 서블릿이 처리할 URL 패턴 매핑 -->
	</servlet-mapping>

</web-app>
~~~



### 2.3 애노테이션으로 매핑하기

`@WebServlet` 을 사용하면 web.xml에 따로 등록하지 않아도 서블릿으로 등록된다. 톰캣이 웹 어플리케이션을 구동하면서 `@WebServlet` 이 적용된 서블릿을 찾아 등록하기 때문이다.

~~~ java
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		...
	}
}
~~~

`@WebServlet` 을 사용할 경우 서블릿이 처리해야 할 URL 패턴이 변경될 때마다, 자바 소스 코드의 urlPatterns 속성값을 변경하고 다시 컴파일해야 한다. 반면 web.xml 파일을 사용하면 URL 경로가 바뀌어도 web.xml 파일만 변경하면 된다.



### 2.5 서블릿 로딩과 초기화

서블릿 컨테이너는 서블릿을 최초 요청할 때 서블릿 객체를 생성하고, 이후 요청이 오면 앞서 생성한 서블릿 객체를 그대로 사용한다.

![image-20210207001256612](../images/image-20210207001256612.png)

* 서블릿 로딩 과정: 웹 컨테이너가 서블릿 객체를 생성하고 init() 메소드를 호출하여 초기화하는 과정

![image-20210207001632085](../images/image-20210207001632085.png)

보통 초기화 작업은 상대적으로 시간이 오래 걸리기 때문에 위의 그림처럼 웹 컨테이너를 처음 구동하는 시점에 초기화를 진행하는 것이 좋다.

* web.xml → `<load-on-startup>1</load-on-startup>`
* `@WebServlet(loadOnStartup = 1)`



### 2.6 초기화 파라미터

~~~ java
public class DBCPInit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}

	private void loadJDBCDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	private void initConnectionPool() {
		try {
			String jdbcUrl = 
					"jdbc:mysql://localhost:3306/chap14?" + 
					"useUnicode=true&characterEncoding=utf8";
			String username = "jspexam";
			String pw = "jsppw";
            ...
        }
    }
}
~~~

위의 코드는 DB에 대한 설정 정보가 코드에 있기 때문에 DB가 변경된다면 코드를 다시 작성하여 컴파일해야 된다. 하지만 초기화 파라미터를 사용하면 코드를 직접 수정하지 않고도 사용할 값을 변경할 수 있다.

* web.xml

~~~xml
<servlet>
    <servlet-name>DBCPInit2</servlet-name>
    <servlet-class>jdbc.DBCPInit2</servlet-class>
    <!-- 초기화 파라미터 전달 -->
    <init-param>
        <param-name>jdbcdriver</param-name> <!-- 초기화 파라미터 이름 -->
        <param-value>com.mysql.jdbc.Driver</param-value> <!-- 초기화 파라미터 값 -->
    </init-param>
    <init-param>
        <param-name>jdbcUrl</param-name>
        <param-value> jdbc:mysql://localhost:3306/chap14?characterEncoding=utf8 </param-value>
    </init-param>
    ...
    <load-on-startup>1</load-on-startup>
</servlet>
~~~

~~~ java
public class DBCPInit2 extends HttpServlet {
	...
	private void loadJDBCDriver() {
        // web.xml에 설정한 jdbcdriber라는 초기화 파라미터에 접근
		String driverClass = getInitParameter("jdbcdriver");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}
    ...
}
~~~



* `@WebServlet` : 초기화 파라미터를 사용하는 이유 중 하나는 클래스의 수정 없이 초기화 과정에서 필요한 값을 수정할 						수 있기 때문이다. 하지만 이 방법을 사용하면 초기화 설정을 변경할 때마다 자바 코드를 수정해야 하						기 때문에 변경의 유연함을 떨어뜨린다.

~~~ java
@WebServlet(urlPatterns = "/hello",
           initParams = {
               @WebInitParam(name="greeting", value="Hello"),
               @WebInitParam(name="title", value="Hello World!")
           })
~~~

