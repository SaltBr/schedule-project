CREATE SCHEMA schedule;

USE schedule;

CREATE TABLE author
(
    author_id   BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '작성자 식별자',
    name       VARCHAR(100) NOT NULL COMMENT '작성자 이름',
    email      VARCHAR(100) NOT NULL COMMENT '작성자 이메일',
    create_date DATETIME     NOT NULL COMMENT '등록일',
    edit_date   DATETIME     NOT NULL COMMENT '수정일'
);

CREATE TABLE schedule
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '할일 식별자',
    todo       VARCHAR(255) NOT NULL COMMENT '할일',
    create_date DATETIME     NOT NULL COMMENT '등록일',
    edit_date   DATETIME     NOT NULL COMMENT '수정일',
    password   VARCHAR(50)  NOT NULL COMMENT '비밀번호',
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES author(author_id)
);
