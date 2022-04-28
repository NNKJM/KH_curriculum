--upload table
create table upload(
	upload_no number(5) primary key,
	upload_writer varchar2(30) not null,
	upload_title varchar2(200) not null,
	upload_cont varchar2(1500) not null,
	upload_pwd varchar2(30) not null,
	upload_file varchar2(500),
	upload_hit number(5) default 0,
	upload_date date,
	upload_update date
);
