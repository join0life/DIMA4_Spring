-- Database 생성
DROP DATABASE dima;
CREATE DATABASE dima;

-- 테이블 생성
DROP TABLE dima.friend;
CREATE TABLE dima.friend
(
	fseq	int AUTO_INCREMENT PRIMARY KEY
	, fname varchar(50) NOT NULL
	, age   int DEFAULT 0
	, phone varchar(50)
	, birthday datetime DEFAULT current_timestamp
	, active char(1) DEFAULT '1'
);

INSERT INTO dima.friend
(fname, age, phone, birthday)
VALUES
('홍길동', 25, '010-1111', '2020-03-01');

SELECT * FROM dima.friend f;
