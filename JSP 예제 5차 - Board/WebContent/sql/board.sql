-- board 테이블 생성
create table board (
	board_no number(5) primary key, 					-- 게시판 글 번호
	board_writer varchar2(30) not null,					-- 게시판 글 작성자
	board_title varchar2(200) not null,					-- 게시판 글 제목
	board_cont varchar2(1000),							-- 게시판 글 내용
	board_pwd varchar2(30),								-- 게시판 글 비밀번호
	board_hit number(5) default 0,						-- 게시판 글 조회수
	board_date date,									-- 게시판 글 작성일
	board_update date									-- 게시판 글 수정일
);

-- board 테이블에 게시글을 추가해 보자
insert into board values(1, '홍길동','제목1', '길동이 글', '1111', default, sysdate, '');
insert into board values(2, '이순신','제목2', '장군님 글', '2222', default, sysdate, '');
insert into board values(3, '유관순','제목3', '유관순 열사 글', '3333', default, sysdate, '');
insert into board values(4, '김유신','제목4', '화랑도 글', '4444', default, sysdate, '');
insert into board values(5, '김연아','제목5', '연아님 글', '5555', default, sysdate, '');

commit;