
## 빌드 결과물 링크
[다운로드](https://raw.githubusercontent.com/jerodis10/blogSearch/master/excute/blogSearch-0.0.1-SNAPSHOT.jar)


## 추가 기능 구현
### * 블로그 검색 api
  - Pagination 형태가 아닌 검색어에 해당되는 블로그 전체 검색 
    - 요청 파라미터 page = 0 으로 넘길 시 전체 검색  
      - ex) blog/search?query=서울난곡로205&page=0
    - 최대 page * size 까지만 검색 가능


## 외부 라이브러리 및 오픈소스
- P6spy 
  - SQL 로그를 쿼리와 함께 바인딩된 파라미터의 타입과 값을 출력

- Resilience4j
  - Retry, Circuit Breaker 적용

- Lombok
  - 어노테이션 기반의 코드 자동 생성을 통한 생산성 향상

- QueryDSL
  - 문자가 아닌 코드로 쿼리를 작성함으로써, 컴파일 시점에 문법 오류를 쉽게 확인

- AssertJ
  - 메소드 체이닝을 통한 좀 더 깔끔하고 읽기 쉬운 테스트 코드를 작성


## REST API 명세서
### * 블로그 검색 (GET - /blog/search)
  - Request
    - Parameter
      - query : String | 검색을 원하는 질의어 | 필수 O
      - sort : String | 결과 문서 정렬 방식 | 필수 X
      - page : Integer | 결과 페이지 번호 | 필수 X
      - size : Integer | 한 페이지에 보여질 문서 수 | 필수 X
    
    - Response
      - total_count : Integer | 검색된 문서 수
      - title : String | 블로그 글 제목
      - contents : String | 블로그 글 요약
      - url : String | 블로그 글 URL
      - blogname : String | 블로그의 이름
      - datetime : Datetime | 블로그 글 작성시간

### * 인기 검색어 목록 (GET - /blog/keyword)
  - Response
    - keyword : String | 인기 검색어
    - searchCount : Integer | 검색된 횟수
     