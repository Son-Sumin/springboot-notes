## [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8)   
출처 : 김영한, "스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술", 인프런, 2022년 11월 28일, https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8   
<br>
### SpringBoot, JPA, Thymeleaf, HIBERNATE, Eclipse(gradle-groovy), Tomcat, MariaDB(MySQL) 을 활용한 수업
* * *

### 프로젝트 환경설정
- 준비물 : Java 11, IDE(IntelliJ 또는 Eclipse)   
- 스프링 부트 스타터 사이트로 이동해서 스프링 프로젝트 생성 (https://start.spring.io)   
```
- 프로젝트 선택   
  - Project: Gradle - Groovy Project   
  - Spring Boot: 2.3.x   
  - Language: Java   
  - Packaging: Jar   
  - Java: 11   
- Project Metadata   
  - groupId: hello   
  - artifactId: hello-spring   
- Dependencies: Spring Web, Thymeleaf   

- 주의
  - 스프링 부트 3.0을 선택하게 되면
    1. Java 17 이상을 사용
    2.  javax 패키지 이름을 jakarta로 변경
  - 스로젝트 버전 선택 시 SNAPSHOT, M1 같은 미정식 버전을 제외하고 뒤에 영어가 붙지 않은 정식 버전 선택
```
- 동작 확인 (ctrl + F11)   
  - 기본 메인 클래스 실행   
  - 스프링 부트 메인 실행 후 에러페이지로 간단하게 동작 확인(http://localhost:8080)   
  
- 라이브러리
 1. 스프링 부트 라이브러리
```
- spring-boot-starter-web
  - spring-boot-starter-tomcat: 톰캣 (웹서버)
  - spring-webmvc: 스프링 웹 MVC
- spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
- spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
  - spring-boot
     - spring-core
  - spring-boot-starter-logging
     - logback, slf4j
  - spring-boot-devtools: html 파일을 컴파일만 해주면 서버 재시작 없이 View 파일 변경이 가능
```

  2. 테스트 라이브러리
 ```
- spring-boot-starter-test
  - junit: 테스트 프레임워크
  - mockito: 목 라이브러리
  - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  - spring-test: 스프링 통합 테스트 지원
```
<br><br>

### View 환경설정   
- 스프링 부트가 제공하는 Welcome Page 기능   
  - static/index.html 을 올려두면 Welcome page 기능을 제공
  - https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-welcome-page   
- thymeleaf 템플릿 엔진
  - thymeleaf 공식 사이트: https://www.thymeleaf.org/
  - 스프링 공식 튜토리얼: https://spring.io/guides/gs/serving-web-content/
  - 스프링부트 메뉴얼: https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-template-engines
- thymeleaf 템플릿엔진 동작 확인 (http://localhost:8080/hello)
  - 확인하기 위해 HelloController.java, resources/templates/hello.html 작성 후 서버 접근
<br>

- 빌드하고 실행 (Window 사용)
  * 서버 배포시 jar 파일 복사하여 서버에 넣어주고 # java -jar ~ 실행시키면 된다
  ```
  이클립스 서버 끈 후
  명령 프롬프트(cmd)로 이동
  해당 dir로 이동
    # ./gradlew build
    # cd build
    # cd libs
    # java -jar hello-spring-0.0.1-SNAPSHOT.jar
   실행 확인 (Spring 뜨는지 확인)
  
  위가 잘 안 될 시
    # ./gradlew clean build   (build 폴더 완전 삭제 후 재생성)
    
  ```
  <br><br>

### build.gradle, application.properties 세팅 필수
- [build.gradle](https://github.com/Son-Sumin/springboot-hello/blob/main/build.gradle)   
- [application.properties](https://github.com/Son-Sumin/springboot-hello/blob/main/src/main/resources/application.properties)   
* * *

### 웹을 출력하는 3가지 방법   
정적 컨텐츠를 제외하면 view를 찾아 템플릿 엔진을 활용하여 html로 웹 브라우저에 넘겨주는지,   
API를 활용하여 데이터만 넘겨주는지 크게 2가지로 나뉜다.   
**1. 정적 컨텐츠**   
     처리없이 파일 자체를 웹 브라우저에 보냄   
     
**2. MVC와 템플릿 엔진**   
     서버에서 프로그램을 거쳐 html을 좀 바꿔서 동적으로 웹 브라우저에 보냄   
   - Controller : 비지니스 로직, 서버, 내부적으로 처리에 집중   
   - Model : 화면에서 필요한 것을 담아 넘겨줌   
   - View : 화면을 그리는데 집중   
   - 템플릿 엔진 : html을 효율적이고, 보기 좋게 바꿔줌    
   
**3. API**   
     JSON이라는 data format으로 data를 클라이언트에게 전달   
     Vue.js, JS, React 사용할 때도 API로 data만 내려주면 화면은 클라이언트가 구성   
     서버끼지 통신할 경우 (html 필요없음)   
     @ResponseBody 문자, 객체(JSON으로 변환됨) 반환하며, 이를 사용하면 viewResolver를 사용하지 않음   
