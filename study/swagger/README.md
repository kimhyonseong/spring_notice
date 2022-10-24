## Swagger
개발한 REST API를 편리하게 문서화 해주고, 이를 통해서 관리 및 제 3자의 사용자가 편리하게 API를 호출해보고 테스트 할 수 있는 프로젝트

어노테이션 기반으로 상세한 설정 가능
_ _ _
@Api - 클래스를 스웨거의 리소스로 표시 - 컨트롤러 클래스에 사용  
@ApiOperation - 특정 경로의 오퍼레이션 HTTP 메소드 설명 - 컨트롤러 메소드에 사용  
@ApiParam - 오퍼레이션 파라미터에 메타 데이터 설명 - 컨트롤러 파라미터에 사용  
@ApiResponse - 오퍼레이션의 응답 지정  
@ApiModelProperty - 모델의 속성 데이터를 설명 - 클래스 내부 변수에 사용  
@ApiImplicitParam - 메소드 단위의 오퍼레이션 파라미터를 설명 - ApiParam을 배열로 나타냄  
@ApiImplicitParams - ApiImplicitParam을 감싸주기 위한 태그