-- DEPT 테이블 생성
create table dept( 
  deptno     number(2,0), 
  dname      varchar2(14), 
  loc        varchar2(13), 
  constraint pk_dept primary key (deptno) 
);

-- DEPT 테이블에 데이터 추가
insert into DEPT (DEPTNO, DNAME, LOC)
	values(10, 'ACCOUNTING', 'NEW YORK');
insert into dept 
	values(20, 'RESEARCH', 'DALLAS');
insert into dept 
	values(30, 'SALES', 'CHICAGO');
insert into dept 
	values(40, 'OPERATIONS', 'BOSTON');
insert into dept 
	values(50, 'IT', 'SEOUL');