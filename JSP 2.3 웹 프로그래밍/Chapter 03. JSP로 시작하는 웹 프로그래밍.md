# Chapter 03. JSP로 시작하는 웹 프로그래밍

## 01. JSP에서 HTML 문서를 생성하는 기본 코드 구조

~~~ jsp
<%-- JSP 페이지에 대한 설정 정보: 문서 타입, 사용할 커스텀 태그, 사용할 자바 클래스 지정 등 --%>
<%@ page contentType="text/html; charset=utf-8" %>

<%-- 응답 생성 --%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%
        	String bookTitle = "Book Title";
        	String author = "Author";
        %>
        <b><%= bookTitle %></b>(<%= author %>)
    </body>
</html>
~~~

* `<%@ page ... %>` : 페이지 디렉티브
  * JSP 페이지에 대한 정보를 설정할 때 사용
* `<% ... %>` : 스크립트 코드
  * HTML 문서를 생성하는 데 필요한 데이터를 생성하고 출력하는 데 사용





## 02. JSP 페이지의 구성 요소

### 2.1 디렉티브

: JSP 페이지에 대한 설정 정보를 지정할 때 사용

`<%@ 디렉티브이름 속성="값" ... %>`

* page: JSP 페이지에 대한 정보 지정 ex) 문서 타입, 출력 버퍼 크기, 에러 페이지 등
* taglib: JSP 페이지에서 사용할 태그 라이브러리 지정
* include: JSP 페이지의 특정 영역에 다른 문서 포함



### 2.2 스트립트 요소

: JSP에서 문서의 내용을 동적으로 생성하기 위해 사용되는 것

ex) 사용자가 폼에 입력한 정보를 DB에 저장, DB 데이터 출력, 자바 기능 사용 등

* 스크립트릿: 자바 코드 실행할 때 사용하는 코드 블록 → `<% 자바코드 %>`
* 표현식: 어떤 값을 출력 결과에 포함시키고자 할 때 사용 → `<%= 값 %>`
* 선언부: 스크립트릿이나 표현식에서 사용할 수 있는 자바 메서드 생성 → `<%! 자바메서드정의 %>`



### 2.3 기본 객체

: 웹 어플리케이션 프로그래밍을 하는 데 필요한 기능 제공

ex) request, response, session, application, page 등



### 2.4 표현 언어

: 스크립트 요소로 인해 복잡해진 코드를 간결하게 작성 가능하게 해줌

`${ expression }`

* expression: 정해진 문법을 따라 작성된 식으로, 값을 생성하는 코드



### 2.5 표준 액션 태그와 태그 라이브러리

액션 태그: JSP 페이지에서 특별한 기능 제공 → `<jsp:액션태그이름>`

커스텀 태그: JSP를 확장시켜주는 기능

* 보통 JSP 코드에서 중복되는 것을 모듈화하거나 스크립트 코드로 인한 복잡함을 없애기 위해 사용
* JSTL: 커스텀 태그 중 자주 사용하는 것들을 별도로 표준화한 태그 라이브러리





## Etc. 용어 정리

#### MIME

: 멀티미디어 데이터를 전송하려면 이 데이터들의 바이너리 데이터들을 ASCII 코드로 변환해야 한다. 이때 전송 ASCII 데이터가 원래 어떤 형식의 파일이었는지 기록하는 것이 MIME 타입이다.

#### BOM

: 문서 맨 앞에 눈에 보이지 않는 특정 바이트를 넣은 다음 이것을 해석해서 정확히 어떤 인코딩 방식이 사용됐는지 알아내는 방법

#### 쿼리 문자열

: URL 경로 뒤에 물음표와 함께 파라미터를 붙여 전송하는 것 → `?이름=값&...이름n=값n`

#### 리다이렉트

: 웹 서버가 웹 브라우저에게 다른 페이지로 이동하라고 응답하는 기능

→ 웹 서버 측에서 웹 브라우저에게 어떤 페이지로 이동하라고 지정하는 것

