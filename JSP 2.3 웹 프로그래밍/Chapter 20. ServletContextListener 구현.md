# Chapter 20. ServletContextListener 구현

## 01. ServletContextListener를 이용한 이벤트 처리

웹 컨테이너는 웹 어플리케이션(컨텍스트)이 시작되거나 종료되는 시점에 초기화나 사용된 자원 반환 등 특정 클래스의 메소드를 실행할 수 있는 기능을 제공하고 있다. `javax.servlet.ServletContextListener` 인터페이스를 활용하면 이 기능을 사용할 수 있다.

* `javax.servlet.ServletContextListener`

  : 웹 어플리케이션이 시작되거나 종료될 때 호출할 메서드를 정의한 인터페이스

* ServletContextListener 인터페이스의 두 메소드는 모두 **ServletContextEvent** 타입의 객체를 전달받음

  * ServletContextEvent 클래스는 ServletContext를 리턴하는 **getServletContext()** 메소드 제공
    * **ServletContext**: JSP의 application 기본 객체와 동일한 객체
      * **getInitParamter(String name)**: 지정한 이름의 web.xml에 설정된 컨텍스트 초기화 파라미터를 리턴

~~~ java
public class DBCPInitListener implements ServletContextListener {

	@Override
    // 웹 어플리케이션을 초기화
	public void contextInitialized(ServletContextEvent sce) {
		String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}

	private void loadJDBCDriver(Properties prop) {
		...
	}

	private void initConnectionPool(Properties prop) {
		...
	}

	@Override
    // 웹 어플리케이션을 종료
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
~~~



~~~xml
<!-- ServletContextListener 클래스 등록 -->
<listener>
    <listener-class>jdbc.DBCPInitListener</listener-class> <!-- 리스너 클래스의 완전한 이름 -->
</listener>

<context-param>
    <param-name>poolConfig</param-name>
    <param-value>
        jdbcdriver=com.mysql.jdbc.Driver
        jdbcUrl=jdbc:mysql://localhost:3306/guestbook?characterEncoding=utf8
        dbUser=jspexam
        dbPass=jsppw
        poolName=guestbook
    </param-value>
</context-param>
~~~

* 여러 개의 리스너가 등록된 경우, contextInitialized()는 등록된 순서대로 실행되고 contextDestroyed는 등록된 반대 순서대로 실행됨



### 1.3 애노테이션을 이용한 리스너 등록

@WebListener를 리스너 클래스에 적용하면 자동으로 리스너로 등록된다.

~~~ java
@WebListener
public class DBCPInitListener implements ServletContextListener {
    ...
}
~~~



이전에는 서블릿 클래스에서 웹 어플리케이션이 실행할 때 초기화 작업을 하거나 웹 어플리케이션이 종료되기 전에 사용된 자원을 반환했다. 만약 하나의 서블릿 클래스만이 아닌 거의 모든 서블릿 클래스에서 해야 하는 초기화 작업이나 자원 반환 작업이 있다면 이는 코드의 중복을 발생시키며, 유지보수하기가 어려워진다. 또한, 위와 같은 작업 로직이 더 길고 복잡하다면 서블릿 클래스는 본연의 역할에 집중하지 못할 수 있다. 따라서, 공통적으로 처리해야 하는 작업이라면 ServleContextListener를 사용해서 분리시키는 것이 좋다.