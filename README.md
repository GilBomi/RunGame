# javaProject -GoGoGame
__작업 날짜__

2018년 9월~12월

__사용한 기술__

GUI,네트워크,디비

__내용__

2인용 게임, 캐릭터를 정하고 자기 닉네임을 정하고 상대방이 접속했다고 나오면 키보드 화살표 ->를 눌러서 앞으로 움직입니다.
채팅도 사용 가능하고 중간 두번 문제가 나오는데 첫번째 문제는 영어, 두번째 문제는 수학입니다. 맞춰야지 다시 앞으로 갈 수 있습니다.
먼저 맨 끝에 도달한 사람이 이깁니다.

__소스코드 받아서 쓸 때 수정해야될 점__

1. mysql에서 사용자 'puser'@'localhost' 비밀번호 '1234' 만들기

2. 데이터베이스 'javaproject' 만들기

3. 'puser' 사용자에게 'javaproject' 데이터베이스에 권한 주기(select포함)

4. 'javaproject'데이터베이스 안에 밑의 코드 넣기

```
create table question1(
id int primary key,
name varchar(50),
answer int);

create table question2(
id int primary key,
name varchar(50),
answer int);
```

5. 위에서 만든 테이블 'question1'과 'question2'에 밑의 데이터 넣기
```
insert into question values(0,'The capital of Taiwan is Taipei.',0);
insert into question values(1,'The capital of Nepal is Jakarta.',1);
insert into question values(2,'The person who made the Eiffel Tower is Eiffel.',0);
insert into question values(3,'The hedgehog hibernates.',0);
insert into question values(4,'The PyeongChang Olympic Games were held in 2016',1);
insert into question values(5,'The President of France is not Macron.',1);

insert into question2 values(0,'55+37=92',0);
insert into question2 values(1,'14*6=94',1);
insert into question2 values(2,'13+14+5=32',0);
insert into question2 values(3,'128/8=16',0);
insert into question2 values(4,'86-28=68',1);
insert into question2 values(5,'71-53=28',1);
```

_0은 '예'를 의미하고 1은 '아니요'를 의미_

__저작권__

[music] :Arrival by MBB https://soundcloud.com/mbbofficial

Creative Commons — Attribution-ShareAlike 3.0 Unported  — CC BY-SA 3.0 

http://creativecommons.org/licenses/b...

Music promoted by Audio Library https://youtu.be/qZHipfdE4s8
