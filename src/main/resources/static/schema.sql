CREATE TABLE IF NOT EXISTS clazz
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS student
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(32) NOT NULL,
    surname     VARCHAR(32) NOT NULL,
    email       VARCHAR(32) NOT NULL,
    phone       VARCHAR(32) NOT NULL,
    clazz_id    INT NOT NULL,

    CONSTRAINT fk_student_clazz
        FOREIGN KEY (clazz_id)
            REFERENCES clazz (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS teacher
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(32) NOT NULL,
    surname     VARCHAR(32) NOT NULL,
    email       VARCHAR(32) NOT NULL,
    phone       VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS subject
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(32) NOT NULL,
    count_of_hour INT NOT NULL,
    teacher_id    INT NOT NULL,
    clazz_id      INT NOT NULL,

    CONSTRAINT fk_subject_teacher
        FOREIGN KEY (teacher_id)
            REFERENCES teacher (id) ON DELETE CASCADE,

    CONSTRAINT fk_subject_clazz
        FOREIGN KEY (clazz_id)
            REFERENCES clazz (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS topic
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(32) NOT NULL,
    homework    VARCHAR(32) NOT NULL,
    resources   VARCHAR(32) NOT NULL,
    subject_id  INT NOT NULL,

    CONSTRAINT fk_topic_subject
        FOREIGN KEY (subject_id)
            REFERENCES subject (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS journal
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    student_id  INT NOT NULL,
    subject_id  INT NOT NULL,
    date        DATE NOT NULL,
    mark        INT,
    type        VARCHAR(32),
    visiting    BOOLEAN NOT NULL,
--  change1
--  change2
    CONSTRAINT fk_journal_student
        FOREIGN KEY (student_id)
            REFERENCES student (id) ON DELETE CASCADE,

    CONSTRAINT fk_journal_subject
        FOREIGN KEY (subject_id)
            REFERENCES subject (id) ON DELETE CASCADE
);


