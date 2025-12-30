# SPRING PLUS
AWS EC2 instance
![](https://velog.velcdn.com/images/fluxing/post/95ec9857-e1e8-488c-b38b-dab3a6c11a3a/image.png)

AWS RDS DataBase
![](https://velog.velcdn.com/images/fluxing/post/48cfe083-b135-4050-8a35-b3022311c6b9/image.png)

서버 접속 및 Live 상태를 확인할 수 있는 health check API
![](https://velog.velcdn.com/images/fluxing/post/4f638aef-900b-4ea2-b62a-4422eaf7f410/image.png)
Postman을 통한 접속 확인
![](https://velog.velcdn.com/images/fluxing/post/82659913-6f45-4fad-ac82-d889638a5758/image.png)

### 대용량 데이터 처리
DB에 유저 500만명 데이터 주입
![](https://velog.velcdn.com/images/fluxing/post/ea517da1-2c9b-4a11-8bd8-ce9e202861e0/image.png)
SearchUserResponse DTO 생성 후 repository에 대입
![](https://velog.velcdn.com/images/fluxing/post/fa9f1b77-2731-45c0-a861-3e025806853f/image.png)
유저 검색 결과 808ms 소요
![](https://velog.velcdn.com/images/fluxing/post/3dea44ad-8fe1-4c93-ae5a-2302ab3d8fd2/image.png)
### 속도 개선을 위해 처음에 캐시를 사용
1. 의존성 주입
   ![](https://velog.velcdn.com/images/fluxing/post/5ead5722-0e9a-4902-bf53-bbb9ab05000f/image.png)
2. 캐시 활성화를 위해 @EnableCaching 사용
   ![](https://velog.velcdn.com/images/fluxing/post/6dce155a-497e-4aab-aebf-82fc79ca3b97/image.png)
3. 환경변수에 캐싱시간 설정
   ![](https://velog.velcdn.com/images/fluxing/post/d18e90c8-e004-4836-b28a-5b83f14718d9/image.png)

4. 서비스단에 @Cacheable 사용 
![](https://velog.velcdn.com/images/fluxing/post/6b355fbe-2f73-42a7-b806-b44fa2eeb0c9/image.png)
캐시에 등록 전 동일하게 808ms 소요
   ![](https://velog.velcdn.com/images/fluxing/post/19ec2259-d3a4-4632-ae8a-f35d9e6d8b38/image.png)
캐시에 등록 후 12ms 소요
   ![](https://velog.velcdn.com/images/fluxing/post/78d4c4c4-ed37-4202-8da9-09d949fd7a3b/image.png)

### 첫 검색시에도 속도 개선을 위해 인덱스 + 캐시 사용
인덱스 사용
![](https://velog.velcdn.com/images/fluxing/post/a7f4b506-cf21-434b-956c-239dd3999380/image.png)
캐시 등록 전 인덱스 사용 후 71ms 소요
![](https://velog.velcdn.com/images/fluxing/post/0d4503c7-a397-422f-b0e2-df25b6390e58/image.png)
캐시 등록 후 18ms 소요
![](https://velog.velcdn.com/images/fluxing/post/2ca76935-6b89-424a-9a6c-12c5920cb611/image.png)