drop database lms;
CREATE DATABASE IF NOT EXISTS lms;
USE lms;
create table assignment
(
    assignment_id   bigint auto_increment
        primary key,
    assignment_type varchar(255) null,
    created_at      datetime(6)  null,
    description     varchar(255) null,
    duration        int          null,
    end_time        datetime(6)  null,
    modified_at     datetime(6)  null,
    repeat_limit    int          null,
    start_time      datetime(6)  null,
    title           varchar(255) null
);

create table classes
(
    class_id    bigint auto_increment
        primary key,
    class_code  varchar(255) not null,
    created_at  datetime(6)  null,
    max_student int          null,
    modified_at datetime(6)  null,
    modified_by varchar(255) null,
    constraint UKo7h6axo2jyskq2aqusb5povfp
        unique (class_code)
);



create table question_material
(
    question_material_id bigint auto_increment
        primary key,
    type                 varchar(255) null,
    url                  varchar(255) null
);

create table assignment_section
(
    assignment_section_id bigint auto_increment
        primary key,
    name                  varchar(255) null,
    question_type         varchar(255) null,
    assignment_id         bigint       null,
    question_material_id  bigint       null,
    constraint FKerk9lmeb5bpt9nqnc3531bfwr
        foreign key (assignment_id) references assignment (assignment_id),
    constraint FKqar3cwq9yspv2e9lgf8sh2bbg
        foreign key (question_material_id) references question_material (question_material_id)
);

create table assignment_section_submission
(
    assignment_section_submission_id bigint auto_increment
        primary key,
    saved_at                         datetime(6) null,
    section_score                    double      null,
    assignment_section_id            bigint      null,
    constraint FK8aa80gngmxf7irc3afw4q9wr8
        foreign key (assignment_section_id) references assignment_section (assignment_section_id)
);
CREATE TABLE question
(
    question_id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    assignment_section_id BIGINT       NOT NULL,
    question_material_id  BIGINT       NULL,
    question_content      TEXT         NOT NULL,
    type                  VARCHAR(50)  NOT NULL,
    question_explain      TEXT         NULL,
    
    CONSTRAINT FK_question_assignment_section 
        FOREIGN KEY (assignment_section_id) REFERENCES assignment_section (assignment_section_id),
        
    CONSTRAINT FK_question_material 
        FOREIGN KEY (question_material_id) REFERENCES question_material (question_material_id)
);
create table user
(
    user_id     bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    email       varchar(255) not null,
    is_active   bit          null,
    modified_at datetime(6)  null,
    modified_by varchar(255) null,
    password    varchar(255) not null,
    phone       varchar(255) null,
    photo_url   varchar(255) null,
    user_name   varchar(255) null,
    constraint UKob8kqyqqgmefl0aco34akdtpe
        unique (email)
);

create table student
(
    student_id  bigint auto_increment
        primary key,
    address     varchar(255) null,
    created_at  datetime(6)  null,
    dob         date         null,
    joined_at   datetime(6)  null,
    modified_at datetime(6)  null,
    modified_by varchar(255) null,
    updated_at  datetime(6)  null,
    user_id     bigint       null,
    constraint FKk5m148xqefonqw7bgnpm0snwj
        foreign key (user_id) references user (user_id)
);

create table score_report
(
    score_report_id bigint auto_increment
        primary key,
    created_at      datetime(6)  null,
    grading_status  varchar(255) null,
    assignment_id   bigint       null,
    student_id      bigint       null,
    constraint FK8mqgn02pj5040nkbtyvq7lyc8
        foreign key (assignment_id) references assignment (assignment_id),
    constraint FKnfcn3lunhch7cpw71sbwlsh5y
        foreign key (student_id) references student (student_id)
);

CREATE TABLE question_submission
(
    question_submission_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    is_correct             BIT          NULL,
    score                  DOUBLE       NULL,
    student_answer         VARCHAR(255) NULL,
    assignment_section_id  BIGINT       NULL,
    question_id            BIGINT       NULL,
    score_report_id        BIGINT       NULL,
    question_review	       VARCHAR(255) NULL,
    
    CONSTRAINT FK_qs_assignment_section 
        FOREIGN KEY (assignment_section_id) REFERENCES assignment_section (assignment_section_id),
        
    CONSTRAINT FK_qs_score_report 
        FOREIGN KEY (score_report_id) REFERENCES score_report (score_report_id),
        
    CONSTRAINT FK_qs_question 
        FOREIGN KEY (question_id) REFERENCES question (question_id)
);

create table score_version
(
    score_id        bigint auto_increment
        primary key,
    modified_at     datetime(6) null,
    review          text        null,
    score           double      null,
    score_report_id bigint      null,
    constraint FKdjl6pc4bl14q70e9uykcdnxd4
        foreign key (score_report_id) references score_report (score_report_id)
);

create table tbl_student_class
(
    student_id bigint not null,
    class_id   bigint not null,
    primary key (student_id, class_id),
    constraint FK3yrunu5t5x0ps76jin1f7x6u3
        foreign key (class_id) references classes (class_id),
    constraint FKf9db81po3q405026hu874smp8
        foreign key (student_id) references student (student_id)
);

CREATE TABLE flashcards (
    flashcard_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    courses_version_id BIGINT
);

CREATE TABLE flashcard_materials (
    flashcard_material_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quizlet_id BIGINT,
    `key` VARCHAR(255),
    `value` TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    flashcard_id BIGINT,
    CONSTRAINT fk_flashcard FOREIGN KEY (flashcard_id) REFERENCES flashcards(flashcard_id) ON DELETE CASCADE
);
CREATE TABLE mcq_answer (
    mcqa_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_id BIGINT,
    answer_content VARCHAR(255),
    is_correct TINYINT(1),
    CONSTRAINT fk_mcq_answer_question 
        FOREIGN KEY (question_id) REFERENCES question(question_id)
);


use lms;
INSERT INTO flashcards (title, description, created_at, courses_version_id)
VALUES
('Basic English Vocabulary 1', 'Từ vựng cơ bản tiếng Anh phần 1', NOW(), 1),
('Basic English Vocabulary 2', 'Từ vựng cơ bản tiếng Anh phần 2', NOW(), 1),
('Basic English Vocabulary 3', 'Từ vựng cơ bản tiếng Anh phần 3', NOW(), 1),
('Daily Communication', 'Từ vựng giao tiếp hằng ngày', NOW(), 1),
('Business English', 'Từ vựng tiếng Anh thương mại', NOW(), 2);


INSERT INTO flashcard_materials (quizlet_id, `key`, value, created_at, flashcard_id) VALUES
-- 📘 Flashcard 1
(1, 'apple', 'quả táo', NOW(), 1),
(1, 'book', 'quyển sách', NOW(), 1),
(1, 'cat', 'con mèo', NOW(), 1),
(1, 'dog', 'con chó', NOW(), 1),
(1, 'egg', 'quả trứng', NOW(), 1),
(1, 'fish', 'con cá', NOW(), 1),
(1, 'green', 'màu xanh lá', NOW(), 1),
(1, 'house', 'ngôi nhà', NOW(), 1),
(1, 'ice', 'nước đá', NOW(), 1),
(1, 'juice', 'nước ép', NOW(), 1),

-- 📗 Flashcard 2
(2, 'key', 'chìa khóa', NOW(), 2),
(2, 'lamp', 'đèn bàn', NOW(), 2),
(2, 'milk', 'sữa', NOW(), 2),
(2, 'night', 'ban đêm', NOW(), 2),
(2, 'orange', 'quả cam', NOW(), 2),
(2, 'pen', 'bút viết', NOW(), 2),
(2, 'queen', 'nữ hoàng', NOW(), 2),
(2, 'rain', 'mưa', NOW(), 2),
(2, 'sun', 'mặt trời', NOW(), 2),
(2, 'tree', 'cái cây', NOW(), 2),

-- 📙 Flashcard 3
(3, 'umbrella', 'cái ô', NOW(), 3),
(3, 'village', 'ngôi làng', NOW(), 3),
(3, 'window', 'cửa sổ', NOW(), 3),
(3, 'xylophone', 'đàn gõ', NOW(), 3),
(3, 'yellow', 'màu vàng', NOW(), 3),
(3, 'zebra', 'ngựa vằn', NOW(), 3),
(3, 'airplane', 'máy bay', NOW(), 3),
(3, 'bridge', 'cây cầu', NOW(), 3),
(3, 'cloud', 'đám mây', NOW(), 3),
(3, 'desert', 'sa mạc', NOW(), 3),

-- 📒 Flashcard 4
(4, 'hello', 'xin chào', NOW(), 4),
(4, 'goodbye', 'tạm biệt', NOW(), 4),
(4, 'thank you', 'cảm ơn', NOW(), 4),
(4, 'sorry', 'xin lỗi', NOW(), 4),
(4, 'please', 'làm ơn', NOW(), 4),
(4, 'how are you', 'bạn khỏe không', NOW(), 4),
(4, 'nice to meet you', 'rất vui được gặp bạn', NOW(), 4),
(4, 'see you later', 'gặp lại sau nhé', NOW(), 4),
(4, 'what time is it', 'mấy giờ rồi', NOW(), 4),
(4, 'where are you from', 'bạn đến từ đâu', NOW(), 4),

-- 📓 Flashcard 5
(5, 'invoice', 'hóa đơn', NOW(), 5),
(5, 'meeting', 'cuộc họp', NOW(), 5),
(5, 'profit', 'lợi nhuận', NOW(), 5),
(5, 'revenue', 'doanh thu', NOW(), 5),
(5, 'contract', 'hợp đồng', NOW(), 5),
(5, 'deadline', 'hạn chót', NOW(), 5),
(5, 'client', 'khách hàng', NOW(), 5),
(5, 'budget', 'ngân sách', NOW(), 5),
(5, 'salary', 'tiền lương', NOW(), 5),
(5, 'promotion', 'thăng chức', NOW(), 5);

-- ========================
-- USER (20 records)
-- ========================
INSERT INTO user (email, password, user_name, is_active, created_at)
VALUES
('user1@example.com', '123456', 'User1', 1, NOW()),
('user2@example.com', '123456', 'User2', 1, NOW()),
('user3@example.com', '123456', 'User3', 1, NOW()),
('user4@example.com', '123456', 'User4', 1, NOW()),
('user5@example.com', '123456', 'User5', 1, NOW()),
('user6@example.com', '123456', 'User6', 1, NOW()),
('user7@example.com', '123456', 'User7', 1, NOW()),
('user8@example.com', '123456', 'User8', 1, NOW()),
('user9@example.com', '123456', 'User9', 1, NOW()),
('user10@example.com', '123456', 'User10', 1, NOW()),
('user11@example.com', '123456', 'User11', 1, NOW()),
('user12@example.com', '123456', 'User12', 1, NOW()),
('user13@example.com', '123456', 'User13', 1, NOW()),
('user14@example.com', '123456', 'User14', 1, NOW()),
('user15@example.com', '123456', 'User15', 1, NOW()),
('user16@example.com', '123456', 'User16', 1, NOW()),
('user17@example.com', '123456', 'User17', 1, NOW()),
('user18@example.com', '123456', 'User18', 1, NOW()),
('user19@example.com', '123456', 'User19', 1, NOW()),
('user20@example.com', '123456', 'User20', 1, NOW()),
('user21@example.com', '123456', 'User21', 1, NOW());

-- ========================
-- STUDENT (20 records)
-- ========================
INSERT INTO student (user_id, dob, address, joined_at, created_at)
VALUES
(1, '2000-01-01', 'Hanoi', NOW(), NOW()),
(2, '2001-02-01', 'HCM', NOW(), NOW()),
(3, '2002-03-01', 'Da Nang', NOW(), NOW()),
(4, '2003-04-01', 'Hai Phong', NOW(), NOW()),
(5, '2000-05-01', 'Can Tho', NOW(), NOW()),
(6, '2001-06-01', 'Hue', NOW(), NOW()),
(7, '2002-07-01', 'Nam Dinh', NOW(), NOW()),
(8, '2003-08-01', 'Thanh Hoa', NOW(), NOW()),
(9, '2000-09-01', 'Nghe An', NOW(), NOW()),
(10, '2001-10-01', 'Quang Ninh', NOW(), NOW()),
(11, '2002-11-01', 'Hanoi', NOW(), NOW()),
(12, '2003-12-01', 'HCM', NOW(), NOW()),
(13, '2000-01-15', 'Da Nang', NOW(), NOW()),
(14, '2001-02-20', 'Hai Phong', NOW(), NOW()),
(15, '2002-03-25', 'Hue', NOW(), NOW()),
(16, '2003-04-10', 'Hanoi', NOW(), NOW()),
(17, '2000-05-30', 'HCM', NOW(), NOW()),
(18, '2001-06-15', 'Nghe An', NOW(), NOW()),
(19, '2002-07-22', 'Quang Nam', NOW(), NOW()),
(20, '2003-08-05', 'Hai Duong', NOW(), NOW()),
(21, '2003-08-05', 'Hai Duong', NOW(), NOW());

-- ========================
-- CLASSES (20 records)
-- ========================
INSERT INTO classes (class_code, created_at, max_student)
VALUES
('CLS101', NOW(), 30), ('CLS102', NOW(), 40), ('CLS103', NOW(), 35),
('CLS104', NOW(), 50), ('CLS105', NOW(), 25), ('CLS106', NOW(), 60),
('CLS107', NOW(), 45), ('CLS108', NOW(), 55), ('CLS109', NOW(), 40),
('CLS110', NOW(), 50), ('CLS111', NOW(), 35), ('CLS112', NOW(), 60),
('CLS113', NOW(), 45), ('CLS114', NOW(), 30), ('CLS115', NOW(), 40),
('CLS116', NOW(), 50), ('CLS117', NOW(), 55), ('CLS118', NOW(), 25),
('CLS119', NOW(), 60), ('CLS120', NOW(), 45);

-- ========================
-- TBL_STUDENT_CLASS (20 mappings)
-- ========================
INSERT INTO tbl_student_class (student_id, class_id)
VALUES
(1,1),(2,2),(3,3),(4,4),(5,5),
(6,6),(7,7),(8,8),(9,9),(10,10),
(11,11),(12,12),(13,13),(14,14),(15,15),
(16,16),(17,17),(18,18),(19,19),(20,20), (21,20);

-- ========================
-- ASSIGNMENT (20 records)
-- ========================
INSERT INTO assignment (title, description, assignment_type, start_time, end_time, duration, repeat_limit, created_at)
VALUES
('Java Basics', 'Assignment on OOP concepts', 'theory', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 60, 1, NOW()),
('Math Quiz', 'Assignment on Calculus', 'quiz', NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 45, 1, NOW()),
('Physics HW', 'Assignment on Mechanics', 'homework', NOW(), DATE_ADD(NOW(), INTERVAL 6 DAY), 50, 1, NOW()),
('Chemistry Lab', 'Lab on Organic compounds', 'lab', NOW(), DATE_ADD(NOW(), INTERVAL 10 DAY), 90, 1, NOW()),
('Biology Test', 'Quiz on Cells', 'quiz', NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), 30, 1, NOW()),
('History Essay', 'Essay on WW2', 'essay', NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY), 120, 1, NOW()),
('English Grammar', 'Assignment on grammar rules', 'quiz', NOW(), DATE_ADD(NOW(), INTERVAL 4 DAY), 40, 1, NOW()),
('Database Design', 'Design ERD', 'project', NOW(), DATE_ADD(NOW(), INTERVAL 12 DAY), 180, 1, NOW()),
('Networking Basics', 'Assignment on OSI model', 'theory', NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), 60, 1, NOW()),
('Web Dev', 'HTML CSS project', 'project', NOW(), DATE_ADD(NOW(), INTERVAL 15 DAY), 200, 1, NOW()),
('AI Intro', 'Essay on AI ethics', 'essay', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 100, 1, NOW()),
('ML Basics', 'Assignment on regression', 'quiz', NOW(), DATE_ADD(NOW(), INTERVAL 8 DAY), 70, 1, NOW()),
('Data Structures', 'Assignment on Trees', 'theory', NOW(), DATE_ADD(NOW(), INTERVAL 9 DAY), 90, 1, NOW()),
('Algorithms', 'Quiz on sorting', 'quiz', NOW(), DATE_ADD(NOW(), INTERVAL 6 DAY), 60, 1, NOW()),
('Software Eng', 'Essay on Agile', 'essay', NOW(), DATE_ADD(NOW(), INTERVAL 11 DAY), 120, 1, NOW()),
('Cloud Basics', 'Assignment on AWS', 'theory', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 60, 1, NOW()),
('Cyber Security', 'Quiz on threats', 'quiz', NOW(), DATE_ADD(NOW(), INTERVAL 4 DAY), 30, 1, NOW()),
('OS HW', 'Assignment on scheduling', 'homework', NOW(), DATE_ADD(NOW(), INTERVAL 13 DAY), 100, 1, NOW()),
('Mobile Dev', 'Android project', 'project', NOW(), DATE_ADD(NOW(), INTERVAL 20 DAY), 250, 1, NOW()),
('Big Data', 'Essay on Hadoop', 'essay', NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY), 150, 1, NOW());

-- ========================
-- QUESTION_MATERIAL (20 records)
-- ========================
INSERT INTO question_material (type, url)
VALUES
('pdf','http://example.com/mat1.pdf'),('image','http://example.com/img1.png'),
('pdf','http://example.com/mat2.pdf'),('image','http://example.com/img2.png'),
('pdf','http://example.com/mat3.pdf'),('image','http://example.com/img3.png'),
('pdf','http://example.com/mat4.pdf'),('image','http://example.com/img4.png'),
('pdf','http://example.com/mat5.pdf'),('image','http://example.com/img5.png'),
('pdf','http://example.com/mat6.pdf'),('image','http://example.com/img6.png'),
('pdf','http://example.com/mat7.pdf'),('image','http://example.com/img7.png'),
('pdf','http://example.com/mat8.pdf'),('image','http://example.com/img8.png'),
('pdf','http://example.com/mat9.pdf'),('image','http://example.com/img9.png'),
('pdf','http://example.com/mat10.pdf'),('image','http://example.com/img10.png');

-- ========================
-- ASSIGNMENT_SECTION (20 records)
-- ========================
INSERT INTO assignment_section (name, question_type, assignment_id, question_material_id)
VALUES
('Sec1','mcq',1,1),('Sec2','theory',1,2),('Sec3','essay',1,3),('Sec4','lab',1,4),
('Sec5','mcq',2,5),('Sec6','theory',2,6),('Sec7','essay',2,7),('Sec8','mcq',2,8),
('Sec9','quiz',3,9),('Sec10','theory',3,10),('Sec11','essay',3,11),('Sec12','quiz',3,12),
('Sec13','theory',4,13),('Sec14','quiz',4,14),('Sec15','essay',4,15),('Sec16','mcq',4,16),
('Sec17','quiz',5,17),('Sec18','theory',5,18),('Sec19','project',5,19),('Sec20','essay',5,20);

-- ========================
-- QUESTION (20 records)
-- ========================
INSERT INTO question (assignment_section_id, question_material_id, question_content, type, question_explain)
VALUES
(1,1,'What is OOP?','mcq','Object-Oriented Programming'),
(1,2,'Define derivative','theory','Rate of change'),
(1,3,'Write essay about Newton','essay','Physics'),
(1,4,'Lab procedure for acid test','lab','Chemistry'),
(2,5,'What is cell theory?','mcq','Biology'),
(2,6,'Explain WW2','theory','History'),
(2,7,'Essay on grammar','essay','English'),
(2,8,'Draw ERD','mcq','Database'),
(3,9,'OSI 7 layers','quiz','Networking'),
(3,10,'CSS selectors','theory','Web Dev'),
(3,11,'AI Ethics','essay','AI'),
(3,12,'Regression types','quiz','ML'),
(4,13,'Tree traversal','theory','DSA'),
(4,14,'Sorting algo','quiz','Algo'),
(4,15,'Agile principles','essay','SE'),
(4,16,'Cloud services','mcq','AWS'),
(5,17,'Cyber attacks','quiz','Security'),
(5,18,'Process scheduling','theory','OS'),
(5,19,'Android lifecycle','project','Mobile'),
(5,20,'Big Data history','essay','Hadoop');

-- ========================
-- ASSIGNMENT_SECTION_SUBMISSION (20 records)
-- ========================
INSERT INTO assignment_section_submission (saved_at, section_score, assignment_section_id)
VALUES
(NOW(),8.5,1),(NOW(),7.0,1),(NOW(),9.0,1),(NOW(),6.5,1),
(NOW(),8.0,2),(NOW(),7.5,2),(NOW(),9.5,2),(NOW(),6.0,2),
(NOW(),8.2,3),(NOW(),7.8,3),(NOW(),9.1,3),(NOW(),6.7,3),
(NOW(),8.9,4),(NOW(),7.3,4),(NOW(),9.7,4),(NOW(),6.4,4),
(NOW(),8.6,5),(NOW(),7.1,5),(NOW(),9.2,5),(NOW(),6.8,5);

-- ========================
-- SCORE_REPORT (20 records)
-- ========================
INSERT INTO score_report (assignment_id, student_id, grading_status, created_at)
VALUES
(1,1,'pending',NOW()),(2,2,'pending',NOW()),(3,3,'pending',NOW()),(4,4,'completed',NOW()),
(5,5,'completed',NOW()),(6,6,'pending',NOW()),(7,7,'completed',NOW()),(8,8,'completed',NOW()),
(9,9,'pending',NOW()),(10,10,'completed',NOW()),(11,11,'completed',NOW()),(12,12,'pending',NOW()),
(13,13,'completed',NOW()),(14,14,'completed',NOW()),(15,15,'pending',NOW()),(16,16,'completed',NOW()),
(17,17,'completed',NOW()),(18,18,'pending',NOW()),(19,19,'completed',NOW()),(20,20,'completed',NOW());

-- ========================
-- SCORE_VERSION (20 records)
-- ========================
INSERT INTO score_version (score_report_id, score, review, modified_at)
VALUES
(1,8.5,'Good OOP',NOW()),(2,7.0,'Calc ok',NOW()),(3,9.0,'Physics strong',NOW()),(4,6.5,'Chem needs work',NOW()),
(5,8.0,'Bio good',NOW()),(6,7.5,'History fair',NOW()),(7,9.5,'English good',NOW()),(8,6.0,'DB weak',NOW()),
(9,8.2,'Network fine',NOW()),(10,7.8,'Web fair',NOW()),(11,9.1,'AI great',NOW()),(12,6.7,'ML needs more',NOW()),
(13,8.9,'DSA good',NOW()),(14,7.3,'Algo fair',NOW()),(15,9.7,'SE excellent',NOW()),(16,6.4,'Cloud weak',NOW()),
(17,8.6,'Cyber good',NOW()),(18,7.1,'OS fair',NOW()),(19,9.2,'Mobile great',NOW()),(20,6.8,'Big Data weak',NOW());

-- ========================
-- QUESTION_SUBMISSION (20 records)
-- ========================
INSERT INTO question_submission (is_correct, score, student_answer, assignment_section_id, question_id, score_report_id)
VALUES
(1,0,'OOP',1,1,1),(0,0,'Wrong',1,2,2),(1,0,'Newton essay',1,3,3),(0,0,'Missed step',1,4,4),
(1,0,'Cell theory',2,5,5),(0,0,'Half correct',2,6,6),(1,0,'Grammar essay',2,7,7),(0,0,'Wrong ERD',2,8,8),
(1,0,'OSI 7',3,9,9),(0,0,'Wrong CSS',3,10,10),(1,0,'AI ethics',3,11,11),(0,0,'Wrong regression',3,12,12),
(1,10,'Tree traversal',4,13,13),(0,10,'Wrong sort',4,14,14),(1,10,'Agile principles',4,15,15),(0,10,'Missed cloud',4,16,16),
(null,null,'Cyber attacks',5,17,17),(null,null,'Wrong OS',5,18,18),(null,null,'Android lifecycle',5,19,19),(null,null,'Wrong Hadoop',5,20,20);
