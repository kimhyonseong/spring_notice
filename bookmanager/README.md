### JPA : ORM 인터페이스  
### 하이버네이트 : JPA 구현체  
### Spring Data JPA : JPA 쉽게 사용할 수 있게 하는 모듈  
* Spring Data JPA는 구현체 필요

domain/User.java  
h2db 테스트 시 User 엔티티 생성시 이미 존재하여 클래스 이름을 Member로 수정  

main 또는 test 디렉토리에 resources/data.sql 생성 시 스프링 부트 / 테스트 할때마다 data.sql 속의 쿼리를 실행  

findBy... 네이밍 규칙을 통해서 커스터마이징 가능  
https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-return-types  

@MappedSuperclass : 상속되는 변수를 상위 엔티티에 컬럼으로 사용하겠다는 어노테이션  
@GeneratedValue(strategy = GenerationType.IDENTITY) : 각 엔티티마다 id 값 따로 사용함  
@PreUpdate : 트랜잭션이 종료된 시점에서 실행되어 postUpdate와 같은 값을 가질 것  
