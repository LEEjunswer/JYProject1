drop database jyprojectdb;
create database JYProjectdb;
use JYProjectdb;



CREATE TABLE member
(
    member_id INT AUTO_INCREMENT PRIMARY KEY, -- 멤버번호
    login_id VARCHAR(50) NOT NULL, -- 아이디
    pw VARCHAR(100) NOT NULL, -- 비밀번호
    name VARCHAR(50) NOT NULL, -- 이름
    nickname VARCHAR(50) NOT NULL, -- 닉네임
    phone VARCHAR(20), -- 휴대폰번호
    email VARCHAR(100) NOT NULL, -- 이메일주소
    zip_code VARCHAR(10), -- 우편번호
    address VARCHAR(100), -- 주소
    address_detail VARCHAR(100), -- 상세주소
    profile VARCHAR(255), -- 프로필이미지
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 가입일자
    last_login_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 마지막 로그인
    active TINYINT(1) DEFAULT 1, -- 계정 활성화 여부
    grade VARCHAR(100), -- 유저등급
    point INT DEFAULT 0 -- 유저포인트
);
ALTER TABLE member COMMENT '회원에 관한 테이블';


CREATE TABLE category
(
    `category_id`   INT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리ID',
    `category_name` VARCHAR(100)    NOT NULL    COMMENT '카테고리이름',
    `reg_date`       DATETIME        NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자'
);

CREATE TABLE board
(
    board_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY , -- 게시글Pk
    category_id INT NOT NULL, -- 카테고리
    member_id INT NOT NULL, -- 작성자
    title VARCHAR(50) NOT NULL, -- 제목
    content VARCHAR(1000) NOT NULL, -- 내용
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 등록일자
    update_date DATETIME, -- 수정일자
    delete_date DATETIME, -- 삭제일자
    view_cnt INT DEFAULT 0, -- 조회수
    likes INT DEFAULT 0, -- 좋아요 수
    dislikes INT DEFAULT 0, -- 싫어요 수

    FOREIGN KEY (member_id) REFERENCES Member(member_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);
CREATE TABLE reply
(
    reply_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '댓글번호',
    board_id INT NOT NULL, -- 보드번호
    member_id INT NOT NULL, -- 작성자
    content VARCHAR(1000) NOT NULL, -- 내용
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    update_date DATETIME, -- 수정일자
    delete_date DATETIME, -- 삭제일자
   /* likes INT DEFAULT 0, -- 좋아요 수
    dislikes INT DEFAULT 0, -- 싫어요 수*/

    FOREIGN KEY (member_id) REFERENCES Member(member_id),
    FOREIGN KEY (Board_id) REFERENCES Board(board_id)
);

ALTER TABLE reply COMMENT '게시글에 대한 댓글 테이블';

ALTER TABLE reply
    ADD CONSTRAINT FK_reply_board_ic_board_board_id FOREIGN KEY (board_id)
        REFERENCES board (board_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

CREATE TABLE file
(
    file_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '파일no',
    board_id INT NOT NULL COMMENT '게시글no',
    file_name VARCHAR(255), -- 파일이름
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    FOREIGN KEY (board_id) REFERENCES board(board_id) -- 외래 키
);
ALTER TABLE file COMMENT '여러 개 파일 등록을 위한 테이블';

desc file;

CREATE TABLE filter (
                        filter_id INT AUTO_INCREMENT PRIMARY KEY,
                        word VARCHAR(100) NOT NULL COMMENT '필터링할 단어',
                        create_at DATETIME NOT NULL   DEFAULT CURRENT_TIMESTAMP COMMENT '필터링 단어 추가일시'
);
ALTER TABLE filter COMMENT '욕설을 제한하기 위한 필터 테이블';



CREATE TABLE `Like` (
Like_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
Member_id INT,
Board_id INT,
Reg_date VARCHAR(25),
is_likes TINYINT(1),  /*불리언값 싫어요는 false 좋아요는 true*/
FOREIGN KEY (Member_id) REFERENCES Member(member_id),
FOREIGN KEY (Board_id) REFERENCES Board(board_id)
);
ALTER TABLE `Like` COMMENT '보드 좋아요 싫어요 했을경우 중보체크';

CREATE TABLE deleted_member (
deleted_id INT ,
login_id VARCHAR(50) NOT NULL,
name VARCHAR(50) NOT NULL,
phone VARCHAR(15) NOT NULL,
email VARCHAR(100) NOT NULL,
zipcode VARCHAR(10),
address VARCHAR(100),
address_detail VARCHAR(100),
reg_date DATETIME,
deleted_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (deleted_id) REFERENCES member(member_id)
);
ALTER TABLE deleted_member COMMENT '회원탈퇴시 1년동안 보관 테이블';

CREATE TABLE admin_board (
        admin_board_id int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
        member_id int NOT NULL,
        category int NOT NULL, /* 0은 공지사항  1은 이벤트*/
        title VARCHAR(50) NOT NULL, -- 제목
        content VARCHAR(1000) NOT NULL, -- 내용
        reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 등록일자
        delete_date DATETIME, -- 삭제일자
        end_date DATETIME, -- 종료일자 // 이벤트시 종료일자 이벤트만 해당
        view_cnt INT DEFAULT 0, -- 조회수
        FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE event (
    event_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    admin_board_id int not null,
    content VARCHAR(100) not null,
    point int not null ,
    start_date DATETIME NOT NULL, -- 이벤트 시작일자
    end_date DATETIME NOT NULL , -- 종료일자 // 이벤트시 종료일자 이벤트만 해당
    lottery_completed  BOOLEAN default false,/*이벤트 완료시 페이지 안보여주게 하기*/
    event_point BOOLEAN not null, /*이벤트가 포인트 이벤트인가 아닌가 포인트 이벤트시 당첨시 밑에 포인트를 지급한다*/
    point_reward int, /*포인트여부를 체크하고 당첨시 이벤트 퐁니트 지급*/
    FOREIGN KEY (admin_board_id) REFERENCES admin_board(admin_board_id)
);


CREATE TABLE event_applicant(
    event_applicant_id int not null AUTO_INCREMENT PRIMARY KEY ,
    event_id int not null,
    member_id int not null,
    FOREIGN KEY (event_id) REFERENCES event(event_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);
CREATE TABLE question (
    question_id int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    board_id int not null, /*카테고리가 6질문*/
    member_id int NOT NULL,
    question_point int not null, /*내공자 포인트*/
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    adoption_date timestamp null, /*채택됐다는걸 불리언 or 타임스탬프으로 고민하다가 타임스탬프로 진행시켰다. */
    delete_date timestamp null,
    FOREIGN KEY (board_id) REFERENCES  board(board_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id)
);

CREATE TABLE adopter (
    adopter_id int AUTO_INCREMENT PRIMARY KEY,
    question_id int NOT NULL,
    member_id int NOT NULL, /*채택자 멤버 아이디*/
    reply_id int not null,
    adoption_point int not null,  /*채택된 포인트*/
    reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delete_date timestamp null,

    FOREIGN KEY (question_id) REFERENCES question(question_id),
    FOREIGN KEY (member_id) REFERENCES member(member_id),
    Foreign Key (reply_id) REFERENCES  reply(reply_id)
);

/*1.자유 2.정보 3.추천 4.후기 5.기타 6.질문
insert into category(category_name) values("자유");
insert into category(category_name) values("정보");
insert into category(category_name) values("추천");
insert into category(category_name) values("후기");
insert into category(category_name) values("기타");
insert into category(category_name) values("질문");
어드민영역 insert into member (login_id,pw,nickname,name,email) values("admin","admin","admin","admin","adminmoa.com");*/
desc filter;
desc member;
desc file;
desc board;