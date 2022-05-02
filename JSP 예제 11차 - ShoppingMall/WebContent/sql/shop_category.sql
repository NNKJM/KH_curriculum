-- shop_category 테이블 생성
create table shop_category(
	category_num number(3) primary key,
	category_code varchar2(8) not null,
	category_name varchar2(100) not null
);
