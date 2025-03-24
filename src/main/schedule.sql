CREATE DATABASE schedule;
USE schedule;

CREATE TABLE schedule
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '할일 식별자',
    todo        VARCHAR(255) NOT NULL COMMENT '할일',
    author      VARCHAR(100) NOT NULL COMMENT '작성자',
    create_date DATETIME     NOT NULL COMMENT '작성일',
    edit_date   DATETIME     NOT NULL COMMENT '수정일',
    password    VARCHAR(50)  NOT NULL COMMENT '비밀번호'
);