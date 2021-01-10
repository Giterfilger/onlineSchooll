INSERT INTO clazz(name)
VALUES ('7-A');

INSERT INTO student(name, surname, email, phone, clazz_id)
VALUES ('Andrii', 'Klymchuk', 'andrii@gmail.com', '0507898745', 1),
       ('Bohdan', 'Kravchuk', 'bohdan@gmail.com', '0507898746', 1);

INSERT INTO teacher(name, surname, email, phone)
VALUES ('Oleh', 'Bug', 'oleh@gmail.com','0507898747');

INSERT INTO subject(name, count_of_hour, teacher_id, clazz_id)
VALUES ('Math', 175, 1, 1);

INSERT INTO topic(description, homework, resources, subject_id)
VALUES ('Topic-1', 'task 1.1, 1.2', 'page 5-10', 1),
       ('Topic-2', 'task 2.1', 'page 10-13', 1);

INSERT INTO journal(student_id, subject_id, date, mark, type, visiting)
VALUES (1, 1, '2020-05-17', 11, 'independent work', true),
       (1, 1, '2020-05-17', 10, 'control work', true),
       (2, 1, '2020-05-17', 2, 'independent work', true),
       (2, 1, '2020-05-17', 11, 'control work', true),
       (2, 1, '2020-05-17', null, null, false);

