------------------------------------------------------------------------------------------------------------------------
-- H2 DML Script
------------------------------------------------------------------------------------------------------------------------
INSERT INTO author (salutation, first_name, last_name, birthdate, email, phone, orcid)
VALUES (1, 'John', 'Smith', '19991204', 'johnny@mail.com', '+48 606 222 555', '0000-0002-1825-0097')
     , (2, 'Jane', 'Doe', '20020425', 'jane.doe@yahoo.com', null, '0000-0001-5851-3011')
     , (0, 'Joe', 'Average', '19950912', 'just.joe@a.very.long.email.addr.es', '112', null)
;
------------------------------------------------------------------------------------------------------------------------
INSERT INTO submission (author_id, title, summary, keywords, lang, pages, status, archived)
VALUES (1, 'A very important scientific paper', 'This is a very short summary.',
        'important stuff, things that matter, imponderables', 'en', 10, 0, false)
     , (2, 'Who framed Roger Rabbit?', 'Yet another very brief summary.',
        'drama, things that don''t matter that much, fresh carrots', 'en', 16, 0, false)
;
------------------------------------------------------------------------------------------------------------------------
