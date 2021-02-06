# Chapter 18. MVC 패턴 구현

## 01. 모델 2 구조와 MVC 패턴

### 1.1 모델 1 구조

: JSP를 이용한 단순한 모델

* 웹 브라우저의 요청을 JSP가 직접 처리

* JSP는 자바빈이나 서비스 클래스를 사용해서 요청 작업을 처리하고 그 결과를 클라이언트에 출력

  → JSP 페이지에 비즈니스 로직과 웹 브라우저에 결과를 출력하는 코드가 섞임

![img](../images/99F311385B96891B2C)



### 1.2 모델 2 구조

: 웹 브라우저의 요청을 하나의 서블릿이 받는 구조

* 웹 브라우저의 모든 요청을 하나의 서블릿에서 처리

  → 서블릿은 웹 브라우저의 요청을 구분하는 방법이 필요

![img](../images/9934AD3E5B968DB904)

1. 서블릿은 웹 브라우저의 로직에 따라 요청을 알맞게 처리
2. 처리 결과를 웹 브라우저에 보여줄 JSP 페이지 선택
3. 선택한 JSP 페이지로 요청 흐름을 포워딩
4. JSP 페이지는 결과 화면을 클라이언트에 전송



### 1.3 MVC 패턴

: Model - View - Controller의 세 부분으로 구성된 구조

* 모델: 비즈니스 영역의 로직 처리
* 뷰: 비즈니스 영역에 대한 프레젠테이션 뷰(사용자가 보게 될 결과 화면) 담당
* 컨트롤러: 사용자의 입력 처리와 흐름 제어 담당

<MVC 패턴의 핵심>

* **비즈니스 로직을 처리하는 모델과 결과 화면을 보여주는 뷰를 분리**

  → 모델의 내부 로직이 변경되더라도 뷰는 영향을 받지 않고, 내부 로직과 상관없이 뷰를 변경할 수 있음

* **어플리케이션의 흐름 제어나 사용자의 처리 요청은 컨트롤러에 집중**

  → 컨트롤러는 요청에 적합한 모델을 사용하고 사용자에게 보여줄 뷰를 선택

  * 비즈니스 로직에 포함되지는 않지만 전체 웹 어플리케이션에 일괄적으로 적용되는 기능도 처리

따라서 MVC 패턴을 사용하면 유지보수 작업이 쉬워지고 어플리케이션을 쉽게 확장할 수 있다.

![img](../images/994B9C4B5B9690BA33)

1. 사용자가 모든 요청을 컨트롤러에 보냄
2. 컨트롤러가 모델을 이용해서 사용자 요청을 비즈니스 로직에 따라 처리
3. 컨트롤러가 사용자에게 보여줄 뷰 선택
4. 선택된 뷰는 사용자에게 알맞은 결과 화면을 출력
   * 화면에 데이터가 필요한 경우, 컨트롤러를 통해 전달받음



### 1.4 MVC 패턴과 모델 2 구조의 매핑

* 컨트롤러 = 서블릿: 웹 브라우저의 요청과 웹 어플리케이션의 전체적인 흐름 제어
* 모델 = 로직 처리 클래스, 자바빈: 비즈니스 로직 처리
* 뷰 = JSP: 컨트롤러에서 request나 session에 저장한 데이터를 사용하여 웹 브라우저에 결과 출력



### 1.5 MVC의 컨트롤러: 서블릿

#### 내부 동작 방식

1. 웹 브라우저가 전송한 HTTP 요청을 받는다. 서블릿의 doGet() 메소드나 doPost() 메소드가 호출된다.
2. 웹 브라우저가 어떤 기능을 요청했는지 분석한다.
3. 알맞은 모델을 호출 후, 모델을 사용하여 요청한 기능을 수행한다.
4. 모델로부터 전달받은 결과물을 알맞게 가공한 후, request나 session에 저장한다. 저장한 결과값은 뷰에서 사용한다.
5. 웹 브라우저에 결과를 전송할 JSP를 선택한 후, 해당 JSP로 포워딩하거나 리다이렉트한다. 

서블릿은 모델의 내부 로직을 알 필요 없이, 웹 브라우저의 요청에 따라 알맞게 모델을 사용하여 요청 기능을 실행하고 그 결과를 뷰에 전달하면 된다.



### 1.6 MVC의 뷰: JSP

* 웹 브라우저가 요청한 결과를 보여주는 프레젠테이션 역할
* 웹 브라우저의 요청을 컨트롤러에 전달해주는 매개체

웹 브라우저가 지속적으로 컨트롤러에 요청을 보낼 수 있는 링크나 폼을 제공해서 웹 브라우저가 업무 흐름에 따라 컨트롤러에 알맞은 요청을 보낼 수 있도록 한다.



### 1.7 MVC의 모델

* 웹 브라우저의 요청을 처리하는데 필요

<컨트롤러와 모델 간의 통신>

1. 컨트롤러로부터 요청을 받는다.
2. 모델에 구현한 비즈니스 로직을 수행한다.
3. 수행 결과를 컨트롤러에 전달한다.
   * 보통 자바빈을 사용하여 처리한 결과값을 저장





## 02. 모델 2 구조를 이용한 MVC 패턴 구현

### 2.2 커맨드 패턴 기반의 코드

사용자가 어떤 기능을 요구했는지 판단하기 위해 사용하는 방법 중에서 가장 일반적인 방법은 명령어를 사용하는 것이다.

* 방법 1: 특정 이름의 파라미터에 명령어 정보를 전달
* 방법 2: 요청 URI 자체를 명령어로 사용

#### 2.2.1 커맨드 패턴을 이용한 명령어 처리기의 분리

방법 1을 사용하면 파라미터 값에 따라 알맞은 기능을 분기해야하기 때문에 굉장히 많은 if문이 중첩된다. 또한 비즈니스 로직이 섞여 있어 코드가 길고 복잡하다. 이런 복잡함을 줄이기 위해 **커맨드 패턴**으로 각 명령어에 해당하는 로직 처리 코드를 별도 클래스로 작성한다.

* 커맨드 패턴: 하나의 명령어를 하나의 클래스에서 처리하도록 구현하는 패턴

~~~ java
...
String command = request.getParameter("cmd");
CommandHandler handler = null;	// CommandHandler: Handler 클래스들의 인터페이스

// 각각의 로직 수행 코드를 담고 있는 클래스의 인스턴스 생성
if (command == null) {
    handler = new NullHandler();
} else if (command.equals("BoardList")) {
    handler = new BoardListHandler();
} else if (command.equals("BoardWriteForm")) {
    handler = new BoardWriteFormHandler();
}

// process() 메소드를 호출하여 로직을 수행한 후, 결과를 보여줄 뷰 페이지 정보 리턴
String viewPage = handler.process(request, response);

RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
dispatcher.forward(request, response);
~~~

기존 컨트롤러 내부 동작 방식의 3단계와 4단계, 5단계의 일부분까지를 CommandHandler로 분리한 것이다.

**<컨트롤러 서블릿 내부 동작 방식>**

1. 웹 브라우저가 전송한 HTTP 요청을 받는다.
2. 웹 브라우저가 어떤 기능을 요청했는지 분석한다.
3. 로직을 처리할 명령어 핸들러를 생성
4. 명령어 핸들러를 통해(process()) 로직 처리
5. 명령어 핸들러가 리턴한 뷰로 포워딩

**< Command Handler >**

1. 로직 처리
2. 결과 저장
3. 뷰 리턴



### 2.3 설정 파일에 커맨드와 클래스의 관계 명시하기

비즈니스 로직 처리 코드를 컨트롤러 서블릿에서 핸들러 클래스로 옮겼지만 여전히 중첩된 if 문을 사용해야 한다. 만약 새로운 명령어가 추가되면 컨트롤러 서블릿 클래스의 코드를 직접 변경해야 하는 단점이 있다. 따라서, `<명령어, 핸들러 클래스>` 의 매핑 정보를 설정 파일(프로퍼티 파일)에 저장한다.

~~~ properties
BoardList=mv.jsp.command.BoardListHandler
BoardWriteForm=mv.jsp.command.BoardWriteFormHandler
...
~~~

~~~ java
public class ControllerUsingFile extends HttpServlet {

    // <커맨드, 핸들러 객체> 매핑 정보 저장
    private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

    public void init() throws ServletException {
        String configFile = getInitParameter("configFile");
        Properties prop = new Properties();
        // 초기화 파라미터를 통해 설정 파일 경로 구하기
        String configFilePath = getServletContext().getRealPath(configFile);
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis); // 설정 파일로부터 매핑 정보를 읽어와 Properties 객체에 저장
        } catch (IOException e) {
            throw new ServletException(e);
        }
        Iterator keyIter = prop.keySet().iterator();
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next();
            String handlerClassName = prop.getProperty(command);
            try {
                // 설정 파일에서 읽은 핸들러 클래스 이름으로 실제 Class 객체를 구한다.
                Class<?> handlerClass = Class.forName(handlerClassName);
                // Class로부터 핸들러 객체 생성
                CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }
    ...
    private void process(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("cmd");
        // commandHandlerMapd에서 명령어에 해당하는 요청을 처리할 핸들러 객체를 구한다.
        CommandHandler handler = commandHandlerMap.get(command);
        if (handler == null) {
            handler = new NullHandler();
        }
        String viewPage = null;
        try {
            viewPage = handler.process(request, response);
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        if (viewPage != null) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	        dispatcher.forward(request, response);
        }
    }
}
~~~

init() 메소드에서 설정 파일을 읽어와 명령어에 해당하는 핸들러 객체를 미리 생성하고, process() 메소드에서 사용한다.



### 2.4 요청 URI 명령어로 사용하기

명령어 기반의 파라미터는 컨트롤러의 URL이 사용자에게 노출된다는 단점이 있다. 따라서 사용자는 얼마든지 명령어를 변경해서 컨트롤러에 요청을 전송할 수 있다. 이러한 불필요한 공격을 방지하려면 요청 URI 자체를 명령어로 사용하는 것이 좋다.

* 장점 1: 파라미터로 명령어를 전달할 때 발생하는 사용자의 악의적인 잘못된 명령어 입력을 일차적으로 예방 가능
* 장점 2: URL 자체가 의미가 있으므로 자연스럽게 기능 설명 가능

~~~ java
private void process(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String command = request.getRequestURI();
    // URI가 컨텍스트 경로로 시작한다면 이를 제외
    if (command.indexOf(request.getContextPath()) == 0) {
        command = command.substring(request.getContextPath().length());
    }
    ...
}
~~~





## 03. 모델 1 구조와 모델 2 구조의 선택

### 모델 1

#### 장점

* 배우기 쉽다.
* 자바 언어를 몰라도 어느 정도 구현 가능하다.
* 기능과 JSP가 직관적으로 연결된다.

#### 단점

* 로직 코드와 뷰 코드가 혼합되어 JSP 코드가 복잡해진다.
* 뷰 변경 시 논리 코드의 빈번한 복사가 발생해서 코드 중복이 발생하기 쉽다. → 유지보수성 감소



### 모델 2

#### 장점

* 로직 코드와 뷰 코드를 분리해서 유지보수가 쉬워진다.
* 컨트롤러 서블릿에서 권한 검사나 인증과 같은 공통 기능 처리가 가능하다.
* 확장이 용이하다.

#### 단점

* 자바 언어에 친숙하지 않으면 접근하기가 쉽지 않다.
* 작업량이 많다.