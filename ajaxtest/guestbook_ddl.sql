USE dima;

DROP TABLE dima.guestbook;
CREATE TABLE dima.guestbook
(
	seq int AUTO_INCREMENT PRIMARY KEY
	, name varchar(50) NOT NULL
	, pwd varchar(50) NOT NULL
	, content varchar(1000)
	, create_date datetime DEFAULT current_timestamp
);

SELECT * FROM dima.guestbook;