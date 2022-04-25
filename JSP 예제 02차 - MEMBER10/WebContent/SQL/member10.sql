-- member10 이라는 테이블을 만들어 보자
create table member10(
    num number(7) primary key, -- 숫자
    memid varchar2(20) not null, -- id
    memname varchar2(20) not null, -- 이름
    pwd varchar2(20) not null, -- 비밀번호
    age number(3), -- 나이
    mileage number(5) not null, -- 마일리지
    job varchar2(30), -- 직업
    addr varchar2(200), -- 주소
    regdate date not null -- 가입일
);

-- member 10 테이블에 고객의 정보를 저장해 보자
insert into member10 values(1, 'id1', '홍길동', '1111', 25, 0, '학생', '부산시 동래구', '2013-08-08');
insert into member10 values(2, 'id2', '김길동', '2222', 30, 1500, '직장인', '서울시 강남구', '2013-09-05');
insert into member10 values(3, 'id3', '공성현', '3333', 33, 10000, '직장인', '부산시 중구', '2013-09-09');
insert into member10 values(4, 'id4', '김영희', '4444', 18, 3000, '학생', '경상남도 마산시', '2012-01-01');
insert into member10 values(5, 'id5', '박말자', '5555', 45, 5000, '주부', '경기도 남양주시', '2013-05-05');
insert into member10 values(6, 'id6', '김철수', '6666', 55, 0, '교수', '제주도 서귀포시', '2013-01-01');
insert into member10 values(7, 'id7', '홍길동', '7777', 14, 6000, '학생', '경상북도 경주시', '2012-12-25');
insert into member10 values(8, 'id8', '김상현', '8888', 31, 0, '무작', '서울시 마포구', '2013-04-18');
insert into member10 values(9, 'id9', '이지연', '9999', 25, 0, '학생', '강원도 강릉시', '2013-02-06');
insert into member10 values(10, 'id10', '홍길동', '0101', 52, 10000, '주부', '서울시 강북구', '2013-09-15');