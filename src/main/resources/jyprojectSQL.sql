drop database jyprojectdb;
create database JYProjectdb;
desc member;
use JYProjectdb;
CREATE TABLE board
(
    `board_id`    INT   NOT NULL    AUTO_INCREMENT COMMENT '게시글no',
    `writer`      INT      NOT NULL    COMMENT '작성자',
    `title`       VARCHAR(50)      NOT NULL    COMMENT '제목',
    `content`     VARCHAR(1000)    NOT NULL    COMMENT '내용',
    `category_id` INT NOT NULL COMMENT '카테고리',
    `reg_date`     DATETIME         NOT NULL    DEFAULT CURRENT_TIMESTAMP  COMMENT '등록일자',
    `update_date`  DATETIME         NULL        COMMENT '수정일자',
    `delete_date`  DATETIME         NULL        COMMENT '삭제일자',
    `view_cnt` INT DEFAULT 0 COMMENT '조회수',
    `likes` 	INT     DEFAULT 0 COMMENT '좋아요 수',
    `dislikes` 		INT    DEFAULT 0 COMMENT '싫어요 수',
    PRIMARY KEY (board_id)
);
ALTER TABLE board
    ADD CONSTRAINT fk_category_board FOREIGN KEY (category_id) REFERENCES category(category_id),
    ADD constraint fk_member_board Foreign key (writer) references member(id);
ALTER TABLE board COMMENT 'community(자유게시판) 게시글을 위한 테이블';


CREATE TABLE category
(
    `category_id`   INT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리ID',
    `category_name` VARCHAR(100)    NOT NULL    COMMENT '카테고리이름',
    `reg_date`       DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자'
);
alter table board Comment '카테고리';

CREATE TABLE reply
(
    `board_id`  INT      NOT NULL    COMMENT '보드번호',
    `reply_id`  INT      NOT NULL    AUTO_INCREMENT COMMENT '댓글번호',
    `writer`    int      NOT NULL    COMMENT '작성자',
    `content`   VARCHAR(1000)    NOT NULL    COMMENT '내용',
    `reg_date`   DATETIME         NOT NULL    DEFAULT CURRENT_TIMESTAMP  COMMENT '등록일자',
    `update_date`   DATETIME     NULL    COMMENT '수정일자',
    `delete_date`   DATETIME     NULL    COMMENT '삭제일자',
    `likes` 	INT     DEFAULT 0 COMMENT '좋아요 수',
    `dislikes` 		INT    DEFAULT 0 COMMENT '싫어요 수',
    PRIMARY KEY (reply_id)
);

ALTER TABLE reply COMMENT '게시글에 대한 댓글 테이블';

ALTER TABLE reply
    ADD CONSTRAINT FK_reply_board_ic_board_board_id FOREIGN KEY (board_id)
        REFERENCES board (board_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE file
(
    `file_id`   INT AUTO_INCREMENT PRIMARY KEY COMMENT '파일no',
    `board_id`  INT             NOT NULL    COMMENT '게시글no',
    `filename`  VARCHAR(100)    NULL        COMMENT '파일이름',
    `reg_date`   DATETIME        NOT NULL   DEFAULT CURRENT_TIMESTAMP  COMMENT '등록일자',
    FOREIGN KEY (board_id) REFERENCES board(board_id)
);
ALTER TABLE file COMMENT '여러 개 파일 등록을 위한 테이블';

desc file;

ALTER TABLE file
    ADD CONSTRAINT FK_file_board_id_board_board_id FOREIGN KEY (board_id)
        REFERENCES board (board_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE filter (
                        filter_id INT AUTO_INCREMENT PRIMARY KEY,
                        word VARCHAR(100) NOT NULL COMMENT '필터링할 단어',
                        created_at DATETIME NOT NULL   DEFAULT CURRENT_TIMESTAMP COMMENT '필터링 단어 추가일시'
);
ALTER TABLE filter COMMENT '욕설을 제한하기 위한 필터 테이블';

CREATE TABLE member
(
    `id`        int             not null   auto_increment comment '멤버번호',
    `loginid`              VARCHAR(50)     NOT NULL    COMMENT '아이디',
    `pw`              VARCHAR(100)    NOT NULL    COMMENT '비밀번호',
    `name`           VARCHAR(50)      Not null   comment '이름',
    `nickname`           VARCHAR(50)      Not null   comment '닉네임',
    `phone`          VARCHAR(15)    not null     comment '휴대폰번호',
    `email`          VARCHAR(100)   not null      comment '이메일주소',
    `zipcode`         VARCHAR(10)     NULL        COMMENT '우편번호',
    `address`         VARCHAR(100)    NULL        COMMENT '주소',
    `address_detail`  VARCHAR(100)    NULL        COMMENT '상세주소',
    `member_profile`         VARCHAR(100)    NULL        COMMENT '멤퍼프로필사진',
    `reg_date`         DATETIME        NOT NULL   DEFAULT CURRENT_TIMESTAMP  COMMENT '가입일자',
    `last_login_date`  DATETIME      NOT NULL  DEFAULT CURRENT_TIMESTAMP comment '마지막 로그인',
    `active` 		BOOLEAN 	DEFAULT true 	COMMENT '계정 활성화 여부',
    `grade`  VARCHAR(100)    NULL        COMMENT '유저등급',
    `point` 	INT    DEFAULT 0 COMMENT '유저포인트',
    PRIMARY KEY (id)
);

ALTER TABLE member COMMENT '회원에 관한 테이블';

ALTER TABLE member
    ADD CONSTRAINT FK_member_id_board_writer FOREIGN KEY (id)
        REFERENCES board (writer) ON DELETE RESTRICT ON UPDATE RESTRICT;



CREATE TABLE deleted_member
(
    `id`        int             not null comment '탈퇴한 멤버번호',
    `loginid`              VARCHAR(50)     NOT NULL    COMMENT '아이디',
    `pw`              VARCHAR(100)    NOT NULL    COMMENT '비밀번호',
    `name`           VARCHAR(50)      Not null   comment '이름',
    `phone`          VARCHAR(15)    not null     comment '휴대폰번호',
    `email`          VARCHAR(100)   not null      comment '이메일주소',
    `zipcode`         VARCHAR(10)     NULL        COMMENT '우편번호',
    `address`         VARCHAR(100)    NULL        COMMENT '주소',
    `address_detail`  VARCHAR(100)    NULL        COMMENT '상세주소',
    `reg_date`         DATETIME      NULL    COMMENT '가입일자',
    `deleted_date`  DATETIME        Null comment '회원탈퇴날짜',
    PRIMARY KEY (id)
);
ALTER TABLE deleted_member COMMENT '회원탈퇴시 1년동안 보관 테이블';
