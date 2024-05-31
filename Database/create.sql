--cleanup


USE master;
DROP DATABASE IF EXISTS companydb;
GO



--create
CREATE DATABASE companydb;
GO
USE companydb;

--tables
CREATE TABLE PERSON(
	Id int IDENTITY(1,1) PRIMARY KEY,
	Email varchar(50) NOT NULL UNIQUE,
	Password varchar(50) NOT NULL,
	Name varchar(30) NOT NULL,
	Surname varchar(30) NOT NULL,
	Gender char(1) NOT NULL CHECK (Gender IN ('M', 'F')),
	Phone char(9) CHECK (Phone LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	Address varchar(100)
);


CREATE TABLE TRAIT(
	Id int IDENTITY(1,1) PRIMARY KEY,
	Name varchar(30) NOT NULL
);

CREATE TABLE WANTED_TRAIT(
	Person_Id int NOT NULL FOREIGN KEY REFERENCES PERSON(Id),
	Trait_Id int NOT NULL FOREIGN KEY REFERENCES TRAIT(Id),
	Value int NOT NULL,
	CONSTRAINT PK_WANTED_TRAITS PRIMARY KEY(Person_Id, Trait_Id)
);

CREATE TABLE PERSON_TRAIT(
	Person_Id int NOT NULL FOREIGN KEY REFERENCES PERSON(Id),
	Trait_Id int NOT NULL FOREIGN KEY REFERENCES TRAIT(Id),
	CONSTRAINT PK_PERSON_TRAIT PRIMARY KEY(Person_Id, Trait_Id)
);

CREATE TABLE MATCH(
	Person_Id int NOT NULL FOREIGN KEY REFERENCES PERSON(Id),
	Time_Stamp datetime NOT NULL DEFAULT GETDATE(),
	Match_Id int FOREIGN KEY REFERENCES PERSON(Id),
	CONSTRAINT PK_Match PRIMARY KEY(Person_Id, Time_Stamp)
);

GO

--views
CREATE VIEW CURRENT_MATCHES AS
	SELECT t1.Person_Id AS Person_Id, t2.Match_Id AS Match_Id, t2.Time_Stamp AS Time_Stamp
	FROM (
		SELECT Person_Id, MAX(Time_Stamp) AS Time_Stamp
		FROM MATCH
		GROUP BY Person_Id
	) t1 JOIN MATCH t2
	ON (t1.Person_Id = t2.Person_Id AND t1.Time_Stamp = t2.Time_Stamp)
	;
GO

CREATE VIEW SINGLE_MEN AS
	SELECT p.*
	FROM PERSON p
	JOIN CURRENT_MATCHES cm
	ON (p.Id = cm.Person_Id)
	WHERE p.Gender = 'M' AND cm.Match_Id IS NULL
GO

CREATE VIEW SINGLE_WOMEN AS
	SELECT p.*
	FROM PERSON p
	JOIN CURRENT_MATCHES cm
	ON (p.Id = cm.Person_Id)
	WHERE p.Gender = 'F' AND cm.Match_Id IS NULL
GO

--functions
CREATE FUNCTION Get_Match (@Id int)
RETURNS int
AS
BEGIN
	DECLARE @Match_Id int;
	DECLARE Table_Cursor CURSOR FAST_FORWARD FOR
		SELECT Match_Id
		FROM CURRENT_MATCHES
		WHERE Person_Id = @Id;
	OPEN Table_Cursor
	FETCH NEXT FROM Table_Cursor INTO @Match_Id
	CLOSE Table_Cursor
	DEALLOCATE Table_Cursor
	RETURN @Match_Id;
END;
GO
--procedures
CREATE PROCEDURE Match_People @Id1 int, @Id2 int
AS
	INSERT INTO MATCH(Person_Id, Match_Id) VALUES
	(@Id1, @Id2),
	(@Id2, @Id1)
GO

CREATE PROCEDURE Split_People @Id int
AS
	INSERT INTO MATCH(Person_Id, Match_Id) VALUES
	(dbo.Get_Match(@Id), NULL)
	INSERT INTO MATCH(Person_Id, Match_Id) VALUES
	(@Id, NULL)
GO

--triggers
CREATE TRIGGER TR_PERSON_INSERT
ON PERSON
AFTER INSERT
AS
INSERT INTO MATCH(Person_Id, Match_Id)
SELECT Id, null
FROM inserted;
GO
