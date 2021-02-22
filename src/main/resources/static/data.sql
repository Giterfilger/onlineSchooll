INSERT INTO clazz(name)
VALUES ('7-A');

INSERT INTO admin(name, surname, email, phone, password, test)
VALUES ('Andrii', 'Klymchuk', 'andrii@gmail.com', '0507898745', 'admin', 'test');

INSERT INTO student(name, surname, email, phone, password, role, clazz_id)
VALUES ('Andrii', 'Klymchuk', 'andrii@gmail.com', '0507898745', 'pass', 'STUDENT', 1),
       ('Bohdan', 'Kravchuk', 'bohdan@gmail.com', '0507898746', 'pass', 'STUDENT', 1);

INSERT INTO teacher(name, surname, email, password, phone)
VALUES ('Oleh', 'Bug', 'oleh@gmail.com', 'pass', '0507898747');

INSERT INTO subject(name, count_of_hour, teacher_id, clazz_id)
VALUES ('Math', 175, 1, 1);

INSERT INTO type(name, coefficient, subject_id)
VALUES ('class work', 2, 1),
       ('home work', 2, 1),
       ('independent work', 5, 1),
       ('control work', 7, 1);

INSERT INTO topic(description, homework, resources, subject_id)
VALUES ('Topic-1', 'task 1.1, 1.2', 'page 5-10', 1),
       ('Topic-2', 'task 2.1', 'page 10-13', 1);

INSERT INTO journal(student_id, subject_id, date, mark, type, visiting)
VALUES (1, 1, '2020-05-17', 11, 'independent work', true),
       (1, 1, '2020-05-17', 10, 'control work', true),
       (2, 1, '2020-05-17', 2, 'independent work', true),
       (2, 1, '2020-05-17', 11, 'control work', true),
       (2, 1, '2020-05-17', null, null, false);

