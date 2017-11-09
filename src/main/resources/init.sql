CREATE DATABASE IF NOT EXISTS BasicMySqlCRUD;
USE BasicMySqlCRUD;

DROP TABLE IF EXISTS customers_projects;
DROP TABLE IF EXISTS companies_projects;
DROP TABLE IF EXISTS developer_projects;
DROP TABLE IF EXISTS developer_skill;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS developers;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS companies;

CREATE TABLE IF NOT EXISTS skills(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL
);

CREATE TABLE IF NOT EXISTS projects(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL
);

CREATE TABLE IF NOT EXISTS companies(
  id int PRIMARY KEY,
  name VARCHAR(100) not NULL
);

CREATE TABLE IF NOT EXISTS developers(
  id int PRIMARY KEY,
  firs_name varchar(50) not null,
  last_name varchar(100) not null,
  specialty varchar(100) not null
);

CREATE TABLE IF NOT EXISTS customers(
  id int PRIMARY KEY,
  firs_name varchar(50) not null,
  last_name varchar(100) not null
);

CREATE TABLE IF NOT EXISTS developer_skill(
  developer_id int not null,
  skill_id int not null,

  FOREIGN KEY (developer_id) REFERENCES developers(id),
  FOREIGN KEY (skill_id) REFERENCES skills(id),

  UNIQUE (developer_id, skill_id)
);

CREATE TABLE IF NOT EXISTS developer_projects(
  developer_id int not null,
  projects_id int not null,

  FOREIGN KEY (developer_id) REFERENCES developers(id),
  FOREIGN KEY (projects_id) REFERENCES projects(id),

  UNIQUE (developer_id, projects_id)
);

CREATE TABLE IF NOT EXISTS customers_projects(
  customers_id int not null,
  projects_id int not null,

  FOREIGN KEY (customers_id) REFERENCES customers(id),
  FOREIGN KEY (projects_id) REFERENCES projects(id),

  UNIQUE (customers_id, projects_id)
);

CREATE TABLE IF NOT EXISTS companies_projects(
  companies_id int not null,
  projects_id int not null,

  FOREIGN KEY (companies_id) REFERENCES companies(id),
  FOREIGN KEY (projects_id) REFERENCES projects(id),

  UNIQUE (companies_id, projects_id)
);

INSERT into companies VALUES
  (1, 'google'),
  (2, 'facebook'),
  (3, 'freeCodeCamp'),
  (4, 'Microsoft'),
  (5, 'apache'),
  (6, 'airbnb'),
  (7, 'alibaba'),
  (8, 'twbs'),
  (9, 'github'),
  (10, 'mozilla');

INSERT into customers VALUES
  (1, 'DANIIL', 'KUZMIN'),
  (2, 'NATALYA', 'MOISEEVA'),
  (3, 'OLEG', 'VOROBEV'),
  (4, 'ALLA', 'SEMENOVA'),
  (5, 'NIKOLAY', 'GRIGOREV'),
  (6, 'VALENTIN', 'KUDRYASHOV'),
  (7, 'YURIY', 'EGOROV'),
  (8, 'OLGA', 'LAZAREVA'),
  (9, 'TAISIYA', 'SOKOLOVA'),
  (10, 'OLEG', 'TARASOV'),
  (11, 'NATALYA', 'KUZNECOVA');

INSERT into projects VALUES
  (1, 'freeCodeCamp'),
  (2, 'bootstrap'),
  (3, 'react'),
  (4, 'tensorflow'),
  (5, 'vue'),
  (6, 'awesome'),
  (7, 'angular.js'),
  (8, 'Font-Awesome'),
  (9, 'electron'),
  (10, 'swift'),
  (11, 'nodejs'),
  (12, 'atom'),
  (13, 'Semantic-UI'),
  (14, 'vscode'),
  (15, 'redux'),
  (16, 'golang'),
  (17, 'jekyll'),
  (18, 'ionic'),
  (19, 'moment');

INSERT into skills VALUES
  (1, 'Algorithms'),
  (2, 'C++'),
  (3, 'Java'),
  (4, 'Analyze Data'),
  (5, 'Applications'),
  (6, 'Application Development'),
  (7, 'Application Development Methodologies'),
  (8, 'Application Development Techniques'),
  (9, 'Architecture'),
  (10, 'Application Programming Interfaces'),
  (11, 'Application Development Tools'),
  (12, 'Aspect Oriented Programming'),
  (13, 'Computer Platforms'),
  (14, 'Computer Science'),
  (15, 'Database Management Systems (DBMS)'),
  (16, 'Data Analytics'),
  (17, 'Documentation'),
  (18, 'Embedded Hardware'),
  (19, 'Emerging Technologies'),
  (20, 'Hardware'),
  (21, 'HTML Authoring Tools'),
  (22, 'Industry Systems'),
  (23, 'iOS'),
  (24, 'Information Systems'),
  (25, 'Linux'),
  (26, 'MacOS'),
  (27, 'Math'),
  (28, 'Mobile'),
  (29, 'Operating Systems'),
  (30, 'Parallel Processing'),
  (31, 'Planning'),
  (32, 'Post Object Programming'),
  (33, 'Programming Methodologies'),
  (34, 'Quality Control'),
  (35, 'Relational Databases'),
  (36, 'Relational Programming'),
  (37, 'Revision Control'),
  (38, 'Self-Motivation'),
  (39, 'Structured Query Language (SQL)'),
  (40, 'Symbolic Programming'),
  (41, 'System Architecture'),
  (42, 'Teamwork'),
  (43, 'UNIX'),
  (44, 'Web'),
  (45, 'Windowing Systems'),
  (46, 'Windows');

INSERT INTO customers_projects VALUES
  (1,1),
  (2,2),
  (3,3),
  (4,4),
  (5,5),
  (6,6),
  (7,7),
  (8,8),
  (9,9),
  (10,10),
  (11,11),
  (1,11),
  (2,12),
  (3,13),
  (4,14),
  (5,15),
  (6,16),
  (7,17),
  (8,18),
  (9,19),
  (10,1),
  (11,2),
  (1,3),
  (2,4);

INSERT into companies_projects VALUES
  (1,1),
  (2,2),
  (3,3),
  (4,4),
  (5,5),
  (6,6),
  (7,7),
  (8,8),
  (9,9),
  (10,10),
  (1,11),
  (2,12),
  (3,13),
  (4,14),
  (5,15),
  (6,16),
  (7,17),
  (8,18),
  (9,19),
  (10,17),
  (1,16),
  (2,15),
  (3,14),
  (4,13),
  (5,12);

INSERT INTO developers VALUES
  (1, 'ELVIRA', 'POPOVA', 'Java developer'),
  (2, 'VALERIY', 'EGOROV', 'Java developer'),
  (3, 'VALENTIN', 'STEPANOV', 'Java developer'),
  (4, 'SERGEY', 'IVANOV', 'Java developer'),
  (5, 'ALEKSANDR', 'ABRAMOV', 'Java developer'),
  (6, 'ALISA', 'VASILEVA', 'C++ developer'),
  (7, 'ANNA', 'KUZNECOVA', 'C++ developer'),
  (8, 'SERGEY', 'KONOVALOV', 'C++ developer'),
  (9, 'ROMAN', 'ANTONOV', 'C++ developer'),
  (10, 'LYUDMILA', 'LEBEDEVA', 'Menenger'),
  (11, 'DMITRIY', 'BELOV', 'Menenger'),
  (12, 'ILYA', 'SOROKIN', 'Menenger'),
  (13, 'DMITRIY', 'SOKOLOV', 'QA'),
  (14, 'GALINA', 'ZOTOVA', 'QA'),
  (15, 'NADEZHDA', 'VLASOVA', 'QA'),
  (16, 'NADEZHDA', 'KONOVALOVA', 'QA'),
  (17, 'VLADIMIR', 'GRIGOREV', 'Mobile developer'),
  (18, 'GENNADIY', 'KUZNECOV', 'Mobile developer'),
  (19, 'VITALIY', 'PETROV', 'Mobile developer'),
  (20, 'LARISA', 'TROFIMOVA', 'Mobile developer'),
  (21, 'ALEKSEY', 'DAVYDOV', 'Python developer'),
  (22, 'NADEZHDA', 'SOLOVEVA', 'Python developer'),
  (23, 'NIKOLAY', 'YAKOVLEV', 'Python developer'),
  (24, 'DMITRIY', 'MARTYNOV', 'Python developer'),
  (25, 'ALEVTINA', 'NOVIKOVA', 'Java developer'),
  (26, 'SERGEY', 'SHEVCHENKO', 'Java developer'),
  (27, 'IRINA', 'ROMANOVA', 'Java developer'),
  (28, 'TATYANA', 'MALYSHEVA', 'Java developer');

INSERT INTO developer_skill VALUES
  (1,1),(1,2),
  (2,3),(2,4),
  (3,5),(3,6),
  (4,7),(4,8),
  (5,9),(5,10),
  (6,11),(6,12),
  (7,13),(7,14),
  (8,15),(8,16),
  (9,17),(9,18),
  (10,19),(10,20),
  (11,21),(11,22),
  (12,23),(12,24),
  (13,25),(13,26),
  (14,27),(14,28),
  (15,29),(15,30),
  (16,31),(16,32),
  (17,33),(17,34),
  (18,35),(18,36),
  (19,37),(19,38),
  (20,39),(20,40),
  (21,41),(21,42),
  (22,43),(22,44),
  (23,45),(23,46),
  (24,45),(24,46),
  (25,44),(25,1),
  (26,2),(26,3),
  (27,4),(27,5),
  (28,6),(28,7),
  (1,8),(1,9),
  (2,10),(2,11),
  (3,12),(3,13),
  (4,14),(4,15),
  (5,16),(5,17),
  (6,18),(6,19),
  (7,20),(7,21),
  (8,23),(8,24),
  (9,25),(9,26),
  (10,27),(10,28),
  (11,29),(11,30),
  (12,31),(12,32),
  (13,33),(13,34),
  (14,35),(14,36),
  (15,37),(15,38),
  (16,39),(16,40),
  (17,41),(17,42),
  (18,43),(18,44),
  (19,45),(19,46),
  (20,46),(20,45),
  (21,44),(21,1),
  (22,2),(22,3),
  (23,4),(23,5),
  (24,6),(24,7),
  (25,8),(25,9),
  (26,10),(26,11),
  (27,12),(27,13),
  (28,14),(28,15),
  (1,16),(1,17),
  (2,18),(2,19),
  (3,20),(3,21),
  (4,22),(4,23),
  (5,24),(5,25),
  (6,26),(6,27),
  (7,28),(7,29),
  (8,30),(8,31),
  (9,32),(9,33),
  (10,34),(10,35),
  (11,36),(11,37),
  (12,38),(12,39),
  (13,40),(13,41),
  (14,42),(14,43),
  (15,44),(15,45),
  (16,46),(16,45),
  (17,44),(17,43),
  (18,1),(18,2),
  (19,3),(19,4),
  (20,5),(20,6),
  (21,7),(21,8),
  (22,9),(22,10),
  (23,11),(23,12),
  (24,13),(24,14),
  (25,15),(25,16),
  (26,17),(26,18),
  (27,19),(27,20),
  (28,21),(28,22);

INSERT INTO developer_projects VALUES
  (1,1),(1,2),
  (2,3),(2,4),
  (3,5),(3,6),
  (4,7),(4,8),
  (5,9),(5,10),
  (6,11),(6,12),
  (7,13),(7,14),
  (8,15),(8,16),
  (9,17),(9,18),
  (10,19),(10,1),
  (11,2),(11,3),
  (12,4),(12,5),
  (13,6),(13,7),
  (14,8),(14,9),
  (15,10),(15,11),
  (16,12),(16,13),
  (17,14),(17,15),
  (18,16),(18,17),
  (19,18),(19,19),
  (20,1),(20,2),
  (21,3),(21,4),
  (22,5),(22,6),
  (23,7),(23,8),
  (24,9),(24,10),
  (25,11),(25,12),
  (26,13),(26,14),
  (27,15),(27,16),
  (28,17),(28,18),
  (1,19),(1,7),
  (2,8),(2,9),
  (3,10),(3,11),
  (4,12),(4,13),
  (5,14),(5,15),
  (6,16),(6,17),
  (7,18),(7,19),
  (8,2),(8,3),
  (9,4),(9,5),
  (10,6),(10,7),
  (11,8),(11,9),
  (12,10),(12,11),
  (13,12),(13,13),
  (14,14),(14,15),
  (15,16),(15,17),
  (16,18),(16,19),
  (17,1),(17,2),
  (18,3),(18,4),
  (19,5),(19,6),
  (20,7),(20,8),
  (21,9),(21,10),
  (22,11),(22,12),
  (23,13),(23,14),
  (24,15),(24,16),
  (25,17),(25,18),
  (26,19),(26,7),
  (27,8),(27,9),
  (28,10),(28,11);

ALTER TABLE developers ADD salary decimal not null;

UPDATE developers SET salary = 4410 WHERE specialty = 'Java developer';
UPDATE developers SET salary = 4380 WHERE specialty = 'C++ developer';
UPDATE developers SET salary = 2860 WHERE specialty = 'Menenger';
UPDATE developers SET salary = 3470 WHERE specialty = 'QA';
UPDATE developers SET salary = 3620 WHERE specialty = 'Mobile developer';
UPDATE developers SET salary = 3730 WHERE specialty = 'Python developer';

ALTER TABLE projects ADD cost decimal not null;

UPDATE projects SET cost = 10000 WHERE name = 'freeCodeCamp';
UPDATE projects SET cost = 25000 WHERE name = 'bootstrap';
UPDATE projects SET cost = 36000 WHERE name = 'react';
UPDATE projects SET cost = 22000 WHERE name = 'tensorflow';
UPDATE projects SET cost = 17000 WHERE name = 'vue';
UPDATE projects SET cost = 24800 WHERE name = 'awesome';
UPDATE projects SET cost = 37000 WHERE name = 'angular.js';
UPDATE projects SET cost = 23000 WHERE name = 'Font-Awesome';
UPDATE projects SET cost = 23500 WHERE name = 'electron';
UPDATE projects SET cost = 40100 WHERE name = 'swift';
UPDATE projects SET cost = 21456 WHERE name = 'nodejs';
UPDATE projects SET cost = 16546 WHERE name = 'atom';
UPDATE projects SET cost = 32456 WHERE name = 'Semantic-UI';
UPDATE projects SET cost = 62340 WHERE name = 'vscode';
UPDATE projects SET cost = 12456 WHERE name = 'redux';
UPDATE projects SET cost = 33140 WHERE name = 'golang';
UPDATE projects SET cost = 46795 WHERE name = 'jekyll';
UPDATE projects SET cost = 13487 WHERE name = 'ionic';
UPDATE projects SET cost = 24456 WHERE name = 'moment';
