# ShowTicket
KOPIS Open API를 활용한 국내 공연 조회 및 리뷰 작성/조회/수정/삭제 하는 서비스

# 프로젝트 목적
KOPIS Open API 공연 정보 추출 및 저장
Java Mail Sender를 이용한 메일 인증 활용
JWT Token을 이용한 로그인 구현

# 프로젝트 기능
- 회원가입
    - 회원가입시 이메일, 이름, 비밀번호, 연락처, 생년월일 정보를 기재한다.
    - 회원등록 후 이메일로 인증 메일을 발송한다, 인증 완료 후 회원가입이 완료된다. 
    
- 로그인
    - user는 이메일, 비밀번호를 입력하여 로그인을 한다.
    - 로그인시 토큰이 생성된다.
    - 로그인시 공연조회/공연상세조회/리뷰 CRUD/ 크레딧 충전/ 정보조회가 가능하다.
    
- 정보조회
    - user의 이메일/이름/연락처/생년월일/현재크레딧/유저타입(user,provider) 정보 확인이 가능하다.
    
- 크레딧 관리
    - user는 크레딧 충전이 가능하다.
    - 크레딧 변동 내역은 userCreditBalanceHistory db에 저장된다.
        
- user open API 공연 조회
    - 공연 목록 조회를 통해 open API에서 제공하는 공연 목록을 조회 할 수 있다.
    - 공연 id를 조회시 해당 공연의 상세정보를 확인 할 수 있다.
    
- user provider 공연 조회
    - 공연 목록 조회를 통해 user는 사이트에서 직접 제공하는 공연 목록을 조회 할 수 있다.
    - 공연 id를 조회시 해당 공연의 상세정보를 확인 할 수 있다.

- 게시판
    - Read
        - 게시판 목록 조회를 통해 user는 해당 게시판에 작성된 모든 리뷰/공연을 조회 할 수 있다.
        - 리뷰 id 를 조회시 해당 리뷰/공연의 상세내용을 확인 할 수 있다.
    - Create
        - 리뷰/공연 생성을 통해 리뷰/공연을 작성할 수 있다.
        - 작성 기록은 post db에 저장된다.
        - user 타입과 board 타입이 일치하지 않을 경우 해당 게시판 작성이 불가하다.
    - Update
        - 로그인 사용자와 게시글을 작성한 사용자의 id와 email이 일치할 경우 title과 content를 수정 할 수 있다.
    - Dlelete
        - 로그인 사용자와 게시글을 작성한 사용자의 id와 email이 일치할 경우 선택한 게시글id의 게시글을 삭제할 수 있다.

# 사용 기술스택
Java, Spring Boot , JPA, Docker, Swagger, MySQL