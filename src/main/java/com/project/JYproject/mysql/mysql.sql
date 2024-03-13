 drop database JYproject;
create database JYproject;

use JYproject;

--유저테이블
CREATE TABLE Users (
    userNo INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    FullName VARCHAR(100),
    Bio TEXT,
    ProfilePicture VARCHAR(255),
    JoinDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    LastLogin TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 보드 테이블
CREATE TABLE Boards (
    BoardNo INT AUTO_INCREMENT PRIMARY KEY,
    BoardName VARCHAR(100) UNIQUE NOT NULL,
    Description TEXT,
    CategoryID INT,
    CreatorID INT,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryNo),
    FOREIGN KEY (CreatorID) REFERENCES Users(userNo),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--카테고리 테이블
CREATE TABLE Categories (
    CategoryNo INT AUTO_INCREMENT PRIMARY KEY,
    CategoryName VARCHAR(100) UNIQUE NOT NULL,
    Description TEXT,
    ParentCategoryID INT,
    FOREIGN KEY (ParentCategoryID) REFERENCES Categories(CategoryNo)
);

-- 게시글 테이블
CREATE TABLE Posts (
    PostID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Content TEXT,
    UserID INT,
    BoardID INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(userNo),
    FOREIGN KEY (BoardID) REFERENCES Boards(BoardNo)
);


-- 댓글 테이블
CREATE TABLE Comments (
    CommentID INT AUTO_INCREMENT PRIMARY KEY,
    Content TEXT,
    UserID INT,
    PostID INT,
    ParentCommentID INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(userNo),
    FOREIGN KEY (PostID) REFERENCES Posts(PostID),
    FOREIGN KEY (ParentCommentID) REFERENCES Comments(CommentID)
);

-- 좋아요 싫어요 테이블
CREATE TABLE Recommendations (
    RecommendationID INT AUTO_INCREMENT PRIMARY KEY,
    PostID INT,
    UserID INT,
    Type ENUM('like', 'dislike') NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (PostID) REFERENCES Posts(PostID),
    FOREIGN KEY (UserID) REFERENCES Users(userNo),
    UNIQUE(PostID, UserID)
);

-- 쪽지 테이블
CREATE TABLE Messages (
    MessageID INT AUTO_INCREMENT PRIMARY KEY,
    SenderID INT,
    ReceiverID INT,
    Content TEXT,
    SentAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (SenderID) REFERENCES Users(userNo),
    FOREIGN KEY (ReceiverID) REFERENCES Users(userNo)
);