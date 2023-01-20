## [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8)   
출처 : 김영한, "스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술", 인프런, 2022년 11월 28일, https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8   
<br>
### SpringBoot, JPA, Thymeleaf, Eclipse(gradle-groovy), Tomcat, MariaDB(MySQL) 을 활용한 수업입니다.
* * *

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
```

  2. 테스트 라이브러리
 ```
- spring-boot-starter-test
  - junit: 테스트 프레임워크
  - mockito: 목 라이브러리
  - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  - spring-test: 스프링 통합 테스트 지원
```
