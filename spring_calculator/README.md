## MockMvc
웹 어플리케이션을 서버에 배포하지 않고 테스트용 MVC 환경을 만들어 요청 및 전송, 응답 기능을 제공해주는 유틸리티
___
**사용법**  
<pre><code>@Autowired
private MockMvc mock;

@Test
public void test() {
  mock.preform(
    // 메소드.URI
    MockMvcRequestBuilders.get("http://localhost:8080/api/test") 
  )
  .andExpect(
    // 기대값 - HTTP 상태 코드
    MockMvcResultMatcher.status().isOk()
  )
  .andExpect(
    MockMvcResultMatcher.content().string()
  )
  .andDo(MockMvcResultHandlers.print()); // 출력
}
</code></pre>
___
**설명**  
- preform : 실행 / 요청
  - MockMvcRequestBuilders.get : 빌더.메소드(URI)
  - queryParam : get 방식 파라미터 맵핑
  - contentType : json 타입으로 변경 가능
  - content : body에 데이터 넣어서 요청
- andExpect : 검증
  - MockMvcResultMatcher : 값 매칭
  - status : HTTP 상태 코드
  - content : body 내용
  - jsonPath : json으로 응답된 내용 파싱
- andDo : 출력 및 로그 보이기