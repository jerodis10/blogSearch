
## 빌드 결과물 링크
https://raw.githubusercontent.com/jerodis10/blogSearch/master/excute/blogSearch-0.0.1-SNAPSHOT.jar


## 추가 기능 구현
- 블로그 검색 api
  - Pagination 형태가 아닌 검색어에 해당되는 블로그 전체 검색 
    - 요청 파라미터 page = 0 으로 넘길 시 전체 검색
    - 최대 page * size 까지만 검색 가능


## 외부 라이브러리 및 오픈소스
- JaCoCo 
  - Java 코드의 커버리지를 체크
  
- P6spy 
  - SQL 로그를 쿼리와 함께 바인딩된 파라미터의 타입과 값을 출력

- Resilience4j
  - Retry, Circuit Breaker 적용

- Lombok
  - 어노테이션 기반의 코드 자동 생성을 통한 생산성 향상

- QueryDSL
  - SQL, JPQL을 코드로 작성

- AssertJ
  - 메소드 체이닝을 통한 좀 더 깔끔하고 읽기 쉬운 테스트 코드를 작성