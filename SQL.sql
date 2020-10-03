show databases;

use nenblog;

show tables;

use nenBlog;

create database nenblog;

drop database nenblog;
drop table user_auth;
drop table user_info;
drop table user_board;
drop table user_category;

rename table USER_INFO to user_info;

create table USER_INFO
(
    USER_ID  INT AUTO_INCREMENT,
    EMAIL    VARCHAR(250) not null,
    PASSWORD VARCHAR(100) not null,
    PHONE    VARCHAR(100) not null,
    primary key (USER_ID),
    foreign key (EMAIL) references USER_AUTH (EMAIL)
);

ALTER TABLE USER_INFO
    ADD CONSTRAINT EMAIL FOREIGN KEY (EMAIL) REFERENCES USER_AUTH (EMAIL);

# ALTER TABLE USER_INFO
#     ADD CONSTRAINT EMAIL_CATEGORY FOREIGN KEY (EMAIL_CATEGORY) REFERENCES user_category (EMAIL);

create table USER_AUTH
(
    EMAIL varchar(250) primary key not null,
    AUTH  boolean                  not null #권한
);

#1
insert into USER_AUTH(EMAIL, AUTH)
VALUES ('kevin0181@naver.com', true);

#2
insert into USER_INFO(EMAIL, PASSWORD, PHONE)
VALUES ('kevin0181@naver.com', 'kevin1141', '01086509052');

#3
insert into user_category(EMAIL, CATEGORY)
VALUES ('kevin0181@naver.com', '기본');

# 테이블 조회
select *
from USER_INFO;
select *
from USER_AUTH;
select *
from user_category;
select *
from user_board;



select CATEGORY
from user_category
where EMAIL = 'kevin0181@naver.com';


#####################################################


drop table USER_INFO;
drop table USER_AUTH;

# 테이블 내용 삭제
delete
from u1,
     u2 USING
     USER_INFO u1
         INNER JOIN user_auth u2
where u1.EMAIL = u2.EMAIL;

delete
from user_auth
where EMAIL = 'kevin0181@naver.com';

# 테이블 2개 조인해서 작제하는 부분
delete u1,u2
from USER_INFO u1
         join user_auth u2 on u1.EMAIL = u2.EMAIL
where u1.EMAIL = 'kevin123321@naver.com';


# 테이블내용 삭제
truncate USER_INFO;
truncate USER_AUTH;

create user 'nenblog'@'localhost' identified by '123321';

grant all privileges on nenblog.* to 'nenblog'@'localhost';

delete
from USER_INFO
where email = '1@1';


#글
create table user_board
(
    BOARD_ID       INT AUTO_INCREMENT primary key not null,
    BOARD_EMAIL    VARCHAR(250)                   not null,
    BOARD_DATE     DATETIME                       not null,
    BOARD_TITLE    VARCHAR(100)                   not null,
    BOARD_CATEGORY VARCHAR(100),
    BOARD_PUBLIC   BOOLEAN                        not null,
    BOARD_TEXT     VARCHAR(8000),
    BOARD_SAVE     BOOLEAN                        not null,
    foreign key (BOARD_EMAIL) references USER_INFO (EMAIL)
);

ALTER TABLE user_board
    ADD CONSTRAINT BOARD_EMAIL FOREIGN KEY (BOARD_EMAIL) REFERENCES USER_INFO (EMAIL);

SELECT *
FROM user_board
order by BOARD_ID;

INSERT INTO user_board (BOARD_EMAIL, BOARD_DATE, BOARD_TITLE, BOARD_CATEGORY, BOARD_PUBLIC, BOARD_TEXT, BOARD_SAVE)
VALUES ('kevin0181@naver.com', now(), '제목입니다', '스프링', true, '텍스트를 쓰는 곳 입니다!', false);

#카테고리
create table user_category
(
    category_id INT AUTO_INCREMENT PRIMARY KEY not null,
    EMAIL       VARCHAR(250)                   NOT NULL,
    CATEGORY    VARCHAR(200),
    foreign key (EMAIL) references USER_INFO (EMAIL)
);

select *
from user_category;

select * from user_category where EMAIL='kevin0181@naver.com';

ALTER TABLE user_category
    ADD CONSTRAINT EMAIL FOREIGN KEY (EMAIL) REFERENCES USER_INFO (EMAIL);

insert into user_category(email, category)
VALUES ('kevin0181@naver.com', '지똥');