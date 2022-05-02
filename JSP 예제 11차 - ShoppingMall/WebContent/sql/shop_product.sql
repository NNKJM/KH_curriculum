-- shop_products 테이블 생성
create table shop_products(
	pnum number(5) primary key,
	pname varchar2(50) not null,
	pcategory_fk varchar2(10) not null,
	pcompany varchar2(100) not null,
	pimage varchar2(100),
	pqty number(5) default 0,
	price number(10) default 0,
	pspec varchar2(30),
	pcontents varchar2(1000),
	point number(6) default 0,
	pinputdate date
);