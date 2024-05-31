USE companydb;
INSERT INTO PERSON(Email, Password, Name, Surname, Gender) VALUES
('paraXM@gmail.com','qwerty' ,'Adam', 'Kowalski', 'M'),
('paraYM@gmail.com','123456' ,'Zyzuú', 'T≥uúcioch', 'M'),
('paraZM@gmail.com','abcdefgh' ,'Jan', 'Zwyczajny', 'M'),
('bezPary@gmail.com','has≥o' ,'Jan', 'Samotny', 'M'),
('paraXF@gmail.com','......' ,'Janina', 'Dolna', 'F'),
('paraYF@gmail.com','xyz' ,'Anna', 'Jagiellonka', 'F'),
('paraZF@gmail.com','brak pomys≥u' ,'Eløbieta', 'Goüdzikowa', 'F');



INSERT INTO TRAIT(Name) VALUES ('Niepalπcy/a'),
('Abstynent'),
('Introwertyk');



INSERT INTO PERSON_TRAIT(Person_Id, Trait_Id) VALUES
(1, 1),
(2, 2),
(3, 3),
(5, 1),
(5, 2),
(6, 2),
(7, 3);

INSERT INTO WANTED_TRAIT(Person_Id, Trait_Id, Value) VALUES
(1, 1, 4),
(2, 2, 3),
(3, 3, 1),
(4, 1, 1),
(4, 2, 4),
(5, 1, 7),
(5, 2, 2),
(6, 2, 20),
(7, 3, 4);

