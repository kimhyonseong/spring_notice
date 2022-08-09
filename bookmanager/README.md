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

### 트랜잭션  
RuntimeException - 트랜잭션 내에서 발생하면 반영 내용이 롤백 됨  
Exception(checked exception) - 트랜잭션 내에서 발생해도 반영(커밋), 롤백조차도 반드시 예외처리를 해주어야함
### isolation
- Isolation.READ_UNCOMMITTED - 커밋되지 않은 정보도 불러옴, 중간에 다른 트랜잭션이 맞물리면 락이됨  
@DynamicUpdate 어노테이션을 이용하여 필요한 정보들만 업데이트 가능 - uncommited일때 모두 업데이트 되는 현상 저지 가능  
- Isolation.READ_COMMITTED - 의도한대로 동작 가능, 엔티티 캐시로 인해 의도치 않게 데이터가 조회될 수 있음  
언리피터블 현상 : 반복적으로 조회했을때 조작을 하지 않았지만 트랜잭션 내에서 값이 달라질 수 있는 현상
- Isolation.REPEATABLE_READ - 트랜잭션 도중 다른곳의 커밋이 일어나도 현재 트랜잭션이 끝난 후에 적용됨  
팬텀리드 : 데이터가 조회되지 않는데 변경되는 현상
- Isolation.SERIALIZABLE - 커밋이 일어나지 않은 트랜잭션이 존재하면 락을 통해서 기다림 / 지연 때문에 성능 문제 발생

### Propagation
- Propagation.REQUIRED -  기본값, 트랜잭션 재활용 모두 같은 트랜잭션으로 묶음
- Propagation.REQUIRED_NEW - 무조건 새로운 트랜잭션 생성(독립적)
- Propagation.NESTED - 부분적인 작은 트랜잭션 생성(종속적인 트랜잭션, 상위에 영향을 주지 않음)
- Propagation.SUPPORTS - 새로 생성하지 않고 처리, 트랜잭션 있으면 지원
- Propagation.NOT_SUPPORTS - 해당 영역은 트랜잭션 없이 별도로 처리
- Propagation.MANDATORY - 이미 만들어진 트랜잭션이 반드시 존재해야함
- Propagation.NEVER - 트랜잭션이 없어야함

### Native Query 
- 성능 이슈가 있을때 사용  
ex) update all
- JPA에서 지원하지 않는 기능을 사용할 때 사용  
ex) show tables;