show databases ;

create database nenBlog;

use nenblog;

show tables ;

create table USERINFO(
    EMAIL VARCHAR(50),
    PASSWORD VARCHAR(30),
    PHONE VARCHAR(50),
    PRIMARY KEY (EMAIL)
);

SELECT * FROM USERINFO;