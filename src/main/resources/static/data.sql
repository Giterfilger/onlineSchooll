INSERT INTO clazz(name)
VALUES ('7-A'),
       ('11-A');

INSERT INTO admin(name, surname, email, phone, role, password, test)
VALUES ('Andrii', 'Klymchuk', 'andrii@gmail.com', '0507898745', 'ADMIN', 'admin', 'test');

INSERT INTO main_teacher(name, surname, email, phone, role, password, school_name, image_url)
VALUES ('Vitaliy', 'Homiak', 'vitaliy@gmail.com', '0507898747', 'MAIN_TEACHER', 'pass', 'school1', 'http://res.cloudinary.com/giterfilger/image/upload/v1615318631/school/student/2-id.jpg');

INSERT INTO student(name, surname, email, phone, password, role, image_url, clazz_id)
VALUES ('Андрій', 'Климчук', 'andrii@gmail.com', '0507898745', 'pass', 'STUDENT', 'http://res.cloudinary.com/giterfilger/image/upload/v1615318135/school/student/1-id.jpg', 1),
       ('Богдан', 'Кравчук', 'bohdan@gmail.com', '0507898746', 'pass', 'STUDENT', 'http://res.cloudinary.com/giterfilger/image/upload/v1615318631/school/student/2-id.jpg', 1),
       ('Остап', 'Вишня', 'ostap@gmail.com', '0669893346', 'pass', 'STUDENT', 'http://res.cloudinary.com/giterfilger/image/upload/v1615318631/school/student/2-id.jpg', 2);

INSERT INTO teacher(name, surname, email, password, phone, role, image_url)
VALUES ('Олег', 'Буг', 'oleh@gmail.com', 'pass', '0507898747', 'TEACHER', 'http://res.cloudinary.com/giterfilger/image/upload/v1615318631/school/student/2-id.jpg');

INSERT INTO subject(name, count_of_hour, teacher_id, clazz_id)
VALUES ('Математика', 175, 1, 1),
       ('Література', 145, 1, 1),
       ('Хімія', 145, 1, 1),
       ('Біологія', 145, 1, 1),
       ('Георгафія', 145, 1, 1),
       ('Математика', 175, 1, 2),
       ('Право', 145, 1, 2);

INSERT INTO type(name, coefficient, subject_id)
VALUES ('робота в класі', 2, 1),
       ('домашня робота', 2, 1),
       ('самостійна робота', 5, 1),
       ('контрольна робота', 8, 1),
       ('робота в класі', 2, 2),
       ('домашня робота', 2, 2),
       ('самостійна робота', 5, 2),
       ('контрольна робота', 8, 2),
       ('робота в класі', 2, 3),
       ('домашня робота', 2, 3),
       ('самостійна робота', 5, 3),
       ('контрольна робота', 8, 3),
       ('робота в класі', 2, 4),
       ('домашня робота', 2, 4),
       ('самостійна робота', 5, 4),
       ('контрольна робота', 8, 4),
       ('робота в класі', 2, 5),
       ('домашня робота', 2, 5),
       ('самостійна робота', 5, 5),
       ('контрольна робота', 8, 5),
       ('робота в класі', 2, 6),
       ('домашня робота', 2, 6),
       ('самостійна робота', 5, 6),
       ('контрольна робота', 8, 6),
       ('робота в класі', 2, 7),
       ('домашня робота', 2, 7),
       ('самостійна робота', 5, 7),
       ('контрольна робота', 8, 7);

INSERT INTO topic(description, homework, resources, subject_id)
VALUES ('Тема-1', 'завдання 1.1, 1.2', 'сторінка 5-10', 1),
       ('Тема-2', 'завдання 2.1', 'сторінка 10-13', 1);

INSERT INTO journal(student_id, subject_id, date, mark, type, visiting)
VALUES (1, 1, '2021-03-23', 11, 'самостійна робота', true),
       (1, 1, '2021-03-23', 10, 'контрольна робота', true),
       (1, 1, '2021-03-21', null, 'робота в класі', false),
       (1, 1, '2021-03-22', null, 'робота в класі', false),
       (1, 3, '2021-03-23', null, 'робота в класі', false),
       (1, 3, '2021-03-24', null, 'робота в класі', false),
       (1, 3, '2021-03-25', null, 'робота в класі', false),
       (1, 3, '2021-03-26', null, 'робота в класі', false),
       (1, 2, '2021-03-23', null, 'робота в класі', false),
       (1, 2, '2021-03-23', null, 'робота в класі', false),
       (2, 1, '2021-03-23', 2, 'самостійна робота', true),
       (1, 1, '2021-03-23', 11, 'контрольна робота', true),
       (1, 2, '2021-03-23', 8, 'домашня робота', true),
       (1, 3, '2021-03-23', 4, 'домашня робота', true),
       (1, 3, '2021-03-23', 4, 'домашня робота', true),
       (1, 3, '2021-03-23', 12, 'домашня робота', true),
       (1, 4, '2021-03-23', 10, 'домашня робота', true),
       (1, 4, '2021-03-23', 10, 'самостійна робота', true),
       (1, 4, '2021-03-23', 10, 'робота в класі', true),
       (1, 5, '2021-03-23', 12, 'самостійна робота', true),
       (1, 2, '2021-03-23', 4, 'домашня робота', true),
       (1, 1, '2021-03-23', 4, 'домашня робота', true),
       (1, 1, '2021-03-23', 9, 'домашня робота', true),
       (1, 1, '2021-03-23', 9, 'домашня робота', true),
       (1, 1, '2021-03-23', 9, 'домашня робота', true),
       (1, 1, '2021-03-23', 9, 'домашня робота', true),
       (1, 1, '2021-03-23', 9, 'домашня робота', true),
       (1, 1, '2021-03-23', 9, 'домашня робота', true),
       (2, 2, '2021-03-23', 4, 'домашня робота', true),
       (2, 3, '2021-03-23', 12, 'домашня робота', true),
       (3, 6, '2021-03-23', 9, 'домашня робота', true),
       (3, 6, '2021-03-23', 9, 'робота в класі', true),
       (3, 7, '2021-03-23', 9, 'домашня робота', true),
       (3, 7, '2021-03-23', 4, 'домашня робота', true),
       (3, 7, '2021-03-23', 12, 'домашня робота', true);