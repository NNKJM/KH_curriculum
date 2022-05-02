-- shop_cart 테이블 생성
create table shop_cart(
	cart_num number(5) primary key,
	cart_pnum number(5) not null,
	cart_userId varchar2(30) not null,
	cart_pname varchar2(100) not null,
	cart_pqty number(5) not null,
	cart_price number(8) not null,
	cart_pspec varchar2(20) not null,
	cart_pimage varchar2(500)
);
