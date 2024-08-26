-- 8월 23일
-- --------Fitness 테이블 설계 -----------
USE dima;

DROP TABLE dima.fitness;

CREATE TABLE dima.fitness
(
    seq int AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    gender char(1) DEFAULT '1',
    height decimal(3, 2),
    weight decimal(3, 2),
    join_date datetime DEFAULT current_timestamp,
    std_weight decimal(5, 2),
    bmi decimal(5, 2),
    bmi_result varchar(100)
);

COMMIT;