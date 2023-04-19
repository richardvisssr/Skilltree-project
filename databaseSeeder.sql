use skilltree

INSERT INTO Roles (Role) 
VALUES 
	('Docent'),
	('Student')

INSERT INTO Users (Firstname, Lastname, Email, Password, RoleID)
VALUES
	('Henk', 'Peters', 'henk.peters@han.nl', 'passpass', 1),
	('Jan', 'Jansen', 'jan.jansen@student.han.nl', 'passpass', 2)

INSERT INTO SkillTrees (Title, Description, UserID)
VALUES
	('Skilltree 1', 'Dit is de eerste skilltree', 1),
	('Skilltree 2', 'Dit is de tweede skilltree', 1),
	('Skilltree 3', 'Dit is de derde skilltree', 1)