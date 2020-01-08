# SpringBoot

### Spring Boot의 외부설정
- 스프링 부트는 외부 설정을 통해 스프링 부트 어플리케이션의 환경설정 혹은 설정값을 정할 수 있음. 외부설정은 크게 properties, YAML, 환경변수, 커맨드 라인 인수 등이 있음.

### 기본 프로젝트 파일
- application.properties 파일 : 
  스프링 부트 사용시 필요한 옵션 설정 파일.
  예를들어 메일서버를 구축하거나 db의 종류를 설정, 로그 사용 여부 등의 설정이 가능함.
  properties의 값은 @Value를 통해 읽어 올 수 있음.
  
### Annotation에 대하여
- Controller와 RestController의 차이 : HTTP Response Body가 생성되는 방식의 차이.
  기존의 MVC @Controller는 View기술을 사용하지만, @RestController는 객체를 반환할 때 객체 데이터는 바로     Json/Xml 타입의 HTTP응답을 직접 리턴함.(즉, 데이터를 리턴함)
- @RestController의 실행 흐름 : Client -> HTTP Request -> Dispatcher Servlet -> Handler Mapping -> RestController(자동으로 ResponseBody 추가) -> HTTP Response -> Client
- @GetMapping()은 Get방식으로 들어오는 ResquestMapping()을 받아주는 어노테이션.

### ResponseEntity객체
- data + http status code
- 인자값으로 Map<key,value>을 가짐. 리턴시 map과 상태 메세지를 함께 전송함.

### Whitelabel Error Page
- 스트링 부트 프로젝트를 실행시켰을때 페이지를 찾지 못하면 브라우저에 뜨는 에러로 부트자체에 내장되어 있음.
