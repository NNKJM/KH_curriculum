-- 상품 정보 테이블을 만들어 보자
create table products(
    pnum number(11) primary key, -- 제품 번호
    category_fk varchar2(8) not null, -- 카테고리 코드
    products_name varchar2(50) not null, -- 제품명
    ep_code_fk varchar2(5) not null, -- 제품 코드
    input_price number(10) default 0 not null, -- 제품 입고 가격
    output_price number(10) default 0 not null, -- 제품 출고 가격
    trans_cost number(5) default 0 not null, -- 제품 배송비
    mileage number(6) default 0 not null, -- 제품 마일리지
    company varchar2(30) -- 제조사
);

-- 제품 데이터를 저장해보자.
insert into products values(1, '00010001', 'S 벽걸이 TV', '00001', 5000000, 8000000, 0, 100000, '삼성');
insert into products values(2, '00010001', 'D TV', '00002', 300000, 400000, 0, 50000, '대우');
insert into products values(3, '00010004', 'S 에어컨 TV', '00001', 1000000, 1100000, 5000, 100000, '삼성');
insert into products values(4, '00010000', 'C 밥솥', '00003', 200000, 2200000, 5500, 0, '쿠쿠');
insert into products values(5, '00010004', 'L 에어컨', '00003', 1200000, 1300000, 0, 0, 'LG');
insert into products values(6, '00020001', '남성난방', '00002', 100000, 150000, 2500, 0, '');
insert into products values(7, '00020001', '여성난방', '00002', 120000, 200000, 0, 0, '');
insert into products values(8, '00020002', '사각팬티', '00002', 10000, 20000, 0, 0, '보디가드');
insert into products values(9, '00020003', '멜빵바지', '00002', 15000, 18000, 0, 0, '');
insert into products values(10, '00030001', '무따기 시리즈', '00001', 25000, 30000, 2000, 0, '길벗');