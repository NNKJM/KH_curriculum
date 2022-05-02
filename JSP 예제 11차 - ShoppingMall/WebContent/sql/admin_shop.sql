-- admin_shop 테이블 생성
create table admin_shop(
	admin_id varchar2(30) primary key,
	admin_pwd varchar2(30) not null,
	admin_name varchar2(50) not null,
	admin_email varchar2(200),
	admin_date date
);

-- admin_shop 항목 삽입
insert into admin_shop
	values('admin', 'admin1234', '관리자', 'admin@naver.com', sysdate);