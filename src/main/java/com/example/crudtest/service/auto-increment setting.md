# Spring Boot JPA 2.0에서 PK의 Auto-increment를 위한 설정

Spring Boot 1.5.x버전에서는 @GeneratedValue의 기본값이 GenerationType.AUTO였음.

### Spring Boot 2.0 Release Notes를 찾아보면
- Spring Boot 1.5.x에서는 Hibernate 5.0.x버전 사용
- Spring Boot 2.0.x에서는 Hibernate 5.2.x버전 사용

### Spring Boot 2.0의 migration guide를 찾아보면
- JPA의 Id generator에서 spring.jpa.hibernate.use-new-id-generator-mappings의 
default값이 true로 변경되어 패치되었다고 함.
(1.5 JpaProperties.java에 보면 원래 default값이 false였음.)

### Hibernate 5.0이전
```
@GeneratedValue(strategy = GenerationTYpe.AUTO)
```
로 설정하면 Hibernate used the IDENTITY라고 뜸.

### Hibernate 5.0
```
@GeneratedValue(strategy = GenerationTYpe.AUTO)
```
로 설정하면 Hibernate picks the TABLE generator instead of IDENTITY라고 뜸.

즉, Hibernate 5.0부터 MySQL에서의 GenarationType.AUTO는 IDENTITY가 아닌 TABLE을 기본 시퀀스 전략으로 가져감.

### 정리
- Spring Boot는 Hibernate의 id 생성 전략을 그대로 따라갈지 말지를 결정하는 useNewIdGeneratorMappings 설정이 있음.
- 1.5에선 기본값이 false, 2.0부턴 기본값이 ture
- Hibernate 5.0부터 MySQL의 AUTO는 IDENTITY가 아닌 TABLE을 기본 시퀀스 전략으로 선택됨.
- 즉, 1.5에선 Hibernate 5.0을 쓰더라도 AUTO를 따라가지 않기 때문에 IDENTITY가 선택됨.
- 2.0에선 true이므로 Hibernate 5.0을 그대로 따라가기 때문에 TABLE이 선택됨.

### 해결책
1. application.properties/yml의 
```
spring.jpa.hibernate.use-new-id-generator-mappings = false
```
로 설정.

2. Entity Class에서
```
@GeneratedValue(strategy = GenerationType.IDENTITY)
```
로 설정.

=> 둘중에 어느것을 하더라도 의도한 대로 잘 실행되는 것을 확인할 수 있음.
