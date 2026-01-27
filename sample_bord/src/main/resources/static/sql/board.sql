-- 테이블 이름 board

create table board(
id int auto_increment primary key, -- 게시글 번호
title varchar(100) not null,
content varchar(1000) not null,
writer varchar(10) not null,
createdAT datetime default now() -- 작성일
)

