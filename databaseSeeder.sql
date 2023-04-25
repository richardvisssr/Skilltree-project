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

INSERT INTO Nodes (Skill, Description, PositionX, PositionY, SkillTreeID)
VALUES 
    ('testSkill', 'Dit is een test skill zodat ik de query kan testen', 100, 100, 1),
    ('node voor skilltree 2', 'Dit is een test skill zodat ik de query kan testen', 100, 100, 2)

INSERT INTO AssesmentCriteria (Description, NodeID)
VALUES 
    ('dit is een test description', 1),
    ('dit is een tweede test description', 1),
    ('dit is een description voor node 2', 2),
    ('dit is een tweede test description voor node 2', 2)

INSERT INTO LearningOutcome (Description, NodeID)
VALUES 
    ('dit is een test description voor learning outcome', 1),
    ('learning outcome voor node 2', 2)