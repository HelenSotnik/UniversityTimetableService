INSERT INTO students (id, age, email, faculty, first_name, group_name, last_name, password, phone, speciality)
VALUES (1, 18, 'zozulia@gmail.com', 'FACULTY_OF_FLYING_MACHINES', 'Rita', 'FLA_109', 'Zozulia', 'Zozi123',
        '380675432211', 'AIRDROME_EQUIPMENT');

INSERT INTO classrooms (id, building_name, classroom_number)
VALUES (231, 'Corpus 4', 231),
       (230, 'Corpus 3', 230),
       (127, 'Corpus 6', 127),
       (109, 'Corpus 3', 109),
       (555, 'Corpus 5', 555),
       (113, 'Corpus 3', 113),
       (701, 'Corpus 2', 701),
       (318, 'Corpus 5', 318),
       (415, 'Main Building', 415),
       (504, 'Main Building', 504),
       (306, 'Corpus 6', 306),
       (103, 'Main Building', 103),
       (314, 'Corpus 3', 314),
       (500, 'Corpus 2', 500),
       (117, 'Corpus 2', 117),
       (124, 'Main Building', 124),
       (316, 'Corpus 4', 316),
       (225, 'Corpus 5', 225),
       (101, 'Corpus 6', 101),
       (234, 'Corpus 3', 234),
       (119, 'Main Building', 119),
       (129, 'Corpus 2', 129),
       (102, 'Main Corpus', 102),
       (222, 'Corpus 6', 222);

INSERT INTO timetables (id, day_of_the_week, student_id)
VALUES (1, 'MONDAY', 1),
       (2, 'TUESDAY', 1),
       (3, 'WEDNESDAY', 1),
       (4, 'THURSDAY', 1),
       (5, 'FRIDAY', 1);

INSERT INTO student_authorities (student_id, authorities)
VALUES (1, 'USER');

INSERT INTO lessons (id, lesson_type, subject_name, classroom_id, timetable_id)
VALUES (1, 'LECTURE', 'Higher Mathematics', 504, 1),
       (2, 'PRACTICAL_LESSON', 'Strength of Materials', 306, 1),
       (3, 'LECTURE', 'Philosophy', 119, 1),
       (4, 'LECTURE', 'Informatics', 225, 1),
       (5, 'LABORATORY_WORK', 'Chemistry', 124, 1),
       (6, 'PRACTICAL_LESSON', 'Higher Mathematics', 500, 2),
       (7, 'LECTURE', 'Aerodynamics', 101, 2),
       (8, 'LECTURE', 'Geometry', 103, 2),
       (9, 'PRACTICAL_LESSON', 'Physics', 316, 2),
       (10, 'LABORATORY_WORK', 'Hydraulics', 117, 2),
       (11, 'LABORATORY_WORK', 'Strength of Materials', 234, 3),
       (12, 'LECTURE', 'Physics', 222, 3),
       (13, 'PRACTICAL_LESSON', 'Higher Mathematics', 102, 3),
       (14, 'LECTURE', 'Theoretical mechanics', 314, 3),
       (15, 'PRACTICAL_LESSON', 'Informatics', 415, 4),
       (16, 'PRACTICAL_LESSON', 'Geometry', 318, 4),
       (17, 'LECTURE', 'Strength of Materials', 701, 4),
       (18, 'LECTURE', 'Hydraulics', 113, 4),
       (20, 'LECTURE', 'Higher Mathematics', 555, 5),
       (21, 'LECTURE', 'Fuel and Lubricating Materials', 231, 5),
       (22, 'LABORATORY_WORK', 'Aerodynamics', 127, 5),
       (23, 'LECTURE', 'Theoretical mechanics', 230, 5),
       (24, 'PRACTICAL_LESSON', 'Hydraulics', 109, 5);
