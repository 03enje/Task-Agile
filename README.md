# TaskAgile

TaskAgile은 오픈 소스 작업 관리 툴입니다. Vue.js 3, Spring Boot 2, MySQL 8.0+로 작업하였습니다.

## 로컬 개발환경

`application.properties` 에서의 세팅을 오버라이드하기 위해 다음 세팅을 따라 `src/main/resources/application-dev.properties` 경로에 파일을 생성합니다.

```properties
spring.profiles.active=dev
spring.datasource.url=jdbc:mysql://localhost:3306/task_agile?useSSL=false
spring.datasource.username=<your username>
spring.datasource.password=<your password>
```

## 명령어

- `mvn install`         | 프론트엔드와 백엔드를 빌드합니다.
- `mvn test`            | 프론트엔드와 백엔드를 테스트합니다.
- `mvn spring-boot:run` | 백엔드 서버를 시작합니다.
- `npm run serve`       | 프론트 엔드 서버를 시작합니다. (`front-end` 파일에서 명령어 실행)
