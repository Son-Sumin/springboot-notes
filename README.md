## [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8)   
출처 : 김영한, "스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술", 인프런, 2022년 11월 28일, https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8   
<br>
### SpringBoot, JPA, Thymeleaf, HIBERNATE, Eclipse(gradle-groovy), Tomcat, MariaDB(MySQL) 을 활용한 수업
* * *

### 프로젝트 환경설정
<details>
    <summary> 살펴보기 </summary> 
<br>
  
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
</details>
<br>

### View 환경설정   
<details>
    <summary> 살펴보기 </summary> 
<br>

- 스프링 부트가 제공하는 Welcome Page 기능   
  - static/index.html 을 올려두면 Welcome page 기능을 제공
  - https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-welcome-page   
  <br>
  
- thymeleaf 템플릿 엔진
  - thymeleaf 공식 사이트: https://www.thymeleaf.org/
  - 스프링 공식 튜토리얼: https://spring.io/guides/gs/serving-web-content/
  - 스프링부트 메뉴얼: https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-template-engines
  <br>
  
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
<br>
</details>
<br>

### build.gradle, application.properties 세팅 필수   
<details>
    <summary> 살펴보기 </summary> 
<br>
  
- [build.gradle](https://github.com/Son-Sumin/springboot-hello/blob/main/build.gradle)   
- [application.properties](https://github.com/Son-Sumin/springboot-hello/blob/main/src/main/resources/application.properties)   
</details>
<br>

* * *

### 웹을 출력하는 3가지 방법   
<details>
    <summary> 살펴보기 </summary> 
<br>
  
정적 컨텐츠를 제외하면 view를 찾아 템플릿 엔진을 활용하여 html로 웹 브라우저에 넘겨주는지,   
API를 활용하여 데이터만 넘겨주는지 크게 2가지로 나뉜다.   
<br>
  
**1. 정적 컨텐츠**   
     처리없이 파일 자체를 웹 브라우저에 보냄   
  <br>
     
**2. MVC와 템플릿 엔진**   
     서버에서 프로그램을 거쳐 html을 좀 바꿔서 동적으로 웹 브라우저에 보냄   
   - Controller : 비지니스 로직, 서버, 내부적으로 처리에 집중   
   - Model : 화면에서 필요한 것을 담아 넘겨줌   
   - View : 화면을 그리는데 집중   
   - 템플릿 엔진 : html을 효율적이고, 보기 좋게 바꿔줌    
  <br>
   
**3. API**   
    - JSON이라는 data format으로 data를 클라이언트에게 전달   
    - Vue.js, JS, React 사용할 때도 API로 data만 내려주면 화면은 클라이언트가 구성   
    - 서버끼지 통신할 경우 (html 필요없음)   
    - @ResponseBody 문자, 객체(JSON으로 변환됨) 반환하며, 이를 사용하면 viewResolver를 사용하지 않음   
  <br>
  
**@ResponseBody 동작 원리**
  - HTTP의 통신 프로토콜로서 HEAD와 BODY 존재하는데, HTTP의 BODY에 문자 내용을 직접 반환
  - MVC의 viewResolver 대신에 HttpMessageConverter가 동작
  - 기본 문자처리: StringHttpMessageConverter
  - 기본 객체처리: MappingJackson2HttpMessageConverter
  - byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
  <br>
</details>
<br>


### 스프링 빈을 등록하는 2가지 방법   
<details>
    <summary> 살펴보기 </summary> 
<br>
  
**1. 컴포넌트 스캔과 자동 의존관계 설정**  
  - 컴포넌트 스캔 원리
    * @Component 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
    * @Component 를 포함하는 @Controller, @Service, @Repository도 스프링 빈으로 자동 등록된다.   
<br> 
  
  - 컴포넌트 스캔과 자동 의존관계 설정   
    * 생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 해당 스프링 빈을 찾아서 넣어준다.   
      이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 한다.
    * 생성자가 1개만 있으면 @Autowired 는 생략할 수 있다.
  
<br><br>
  
**2. 자바 코드로 직접 스프링 빈 등록하기**   
  ``` 
  [예시]
  @Configuration
  public class SpringConfig {
 
    @Bean
     public MemberService memberService() {
      return new MemberService(memberRepository());
   }

    @Bean
    public MemberRepository memberRepository() {
      return new MemoryMemberRepository();
   }
}
```  
 - DI에는 필드 주입, setter 주입, 생성자 주입 이렇게 3가지 방법이 있다.   
  의존관계가 실행 중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.   
 -  실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.   
    그리고 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.   
 - @Autowired 를 통한 DI는 스프링이 관리하는 객체에서만 동작한다.   
   스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.
  
</details>
<br>

### 스프링 DB 접근 기술
<details>
    <summary> 살펴보기 </summary> 
<br>
  
**1. 순수 Jdbc** (이런게 있구나 하고 참고만)   
  <br>
  
**2. 스프링 JdbcTemplate**   
  - 순수 Jdbc와 동일한 환경설정을 하면 된다.   
  - 스프링 JdbcTemplate과 MyBatis 같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거해준다.   
    하지만 SQL은 직접 작성해야 한다.   
  <br>
  
**3. JPA**   
  - JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다.   
  - JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다.   
  - JPA를 사용하면 개발 생산성을 크게 높일 수 있다.   
  <br>
  
**4. 스프링 데이터 JPA**   
  - 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료할 수 있다.   
  - 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공한다.
  - 실무에서 관계형 데이터베이스를 사용한다면 스프링 데이터 JPA는 이제 선택이 아니라 필수이다.
  - 단, 스프링 데이터 JPA는 JPA를 편리하게 사용하도록 도와주는 기술이다.   
    따라서 JPA를 먼저 학습한 후에 스프링 데이터 JPA를 학습해야 한다.
  
</details>
