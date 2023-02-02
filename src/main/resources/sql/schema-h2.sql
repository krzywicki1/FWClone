------------------------------------------------------------------------------------------------------------------------
-- H2 DDL Script
------------------------------------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    id         INT8 AUTO_INCREMENT PRIMARY KEY,
    salutation INT4,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    birthdate  DATE,
    email      VARCHAR(255) NOT NULL,
    phone      VARCHAR(100),
    orcid      VARCHAR(19),
    status     VARCHAR(255) NOT NULL DEFAULT 'unknown'
);

DROP TABLE IF EXISTS submission;
CREATE TABLE submission
(
    id        INT8 AUTO_INCREMENT PRIMARY KEY,
    author_id INT8                  NOT NULL,
    title     VARCHAR2(250)         NOT NULL,
    summary   VARCHAR(1000)         NOT NULL,
    keywords  VARCHAR(500)          NOT NULL,
    lang      VARCHAR(2)            NOT NULL DEFAULT 'pl',
    pages     INT4                  NOT NULL,
    status    INT4                  NOT NULL DEFAULT 0,
    archived  BIT                   NOT NULL DEFAULT FALSE
);
------------------------------------------------------------------------------------------------------------------------
