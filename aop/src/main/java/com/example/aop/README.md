### @Pointcut
적용 위치 지정자  
적용할 대상  
패키지부터 메소드까지 다양하게 지정 가능

### JointPoint
bean들의 모든 메서드  
Aspect 적용 가능한 모든 지점

### @Before
값으로 pointcut 메소드 또는 pointcut 표현식 가능  
값으로 받은 메소드가 실행되기 전에 실행  

### @AfterReturning
pointcut 메소드와 객체를 값으로 받을 수 있음  
값으로 받은 메소드가 실행된 후 에러가 없을 시 실행