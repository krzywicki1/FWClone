------------------------------------------------------------------------------------------------------------------------
-- H2 DDL Script
------------------------------------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS movies;
CREATE TABLE movies
(
    id         INT8 AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    production_date  INT NOT NULL,
    director      VARCHAR(255) NOT NULL,
    budget      INT,
    boxoffice      INT,
    score     INT
);
