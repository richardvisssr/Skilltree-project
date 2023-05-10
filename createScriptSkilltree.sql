CREATE DATABASE skilltree;

use skilltree
BEGIN TRY
	BEGIN TRAN

    CREATE TABLE Roles
    (
        ID        INT UNIQUE                    NOT NULL IDENTITY (1,1),
        Role      VARCHAR(255)                  NOT NULL,

        CONSTRAINT PK_Roles PRIMARY KEY (ID),
    );

    CREATE TABLE Users
    (
        ID        INT UNIQUE                        NOT NULL IDENTITY (1,1),
        Firstname VARCHAR(255)                      NOT NULL,
        Lastname  VARCHAR(255)                      NOT NULL,
        Email     VARCHAR(255)                      NOT NULL,
        Password  VARCHAR(255)                      NOT NULL,
        RoleID    INT                               NOT NULL,

        CONSTRAINT PK_Users PRIMARY KEY (ID),
        CONSTRAINT FK_Users_RoleID_ID FOREIGN KEY (RoleID) REFERENCES Roles (ID) ON UPDATE CASCADE,
    );

    CREATE TABLE SkillTrees
    (
        ID       INT UNIQUE				NOT NULL IDENTITY (1,1),
        Description VARCHAR(2000)		 NOT NULL,
        Title      VARCHAR(255)                  NOT NULL,

        UserID   INT                   NOT NULL,

		CONSTRAINT FK_Skilltree_User_ID FOREIGN KEY (userID) REFERENCES Users (ID) ON DELETE CASCADE,
        CONSTRAINT PK_SkillTrees PRIMARY KEY (ID)
    );

    CREATE TABLE Nodes
    (
        ID                 INT UNIQUE                    NOT NULL IDENTITY (1,1),
		Skill				VARCHAR(255)                  NOT NULL,
        Description        VARCHAR(2000)                  NOT NULL,
        PositionX          INT      DEFAULT 0            NOT NULL,
        PositionY          INT      DEFAULT 0            NOT NULL,

        SkillTreeID        INT                           NOT NULL,

        CONSTRAINT PK_Nodes PRIMARY KEY (ID, SkillTreeID),
        CONSTRAINT FK_Nodes_SkillTreeID_ID FOREIGN KEY (SkillTreeID) REFERENCES SkillTrees (ID) ON DELETE CASCADE,
    );

		CREATE TABLE AssesmentCriteria
		(
		ID          INT UNIQUE                    NOT NULL IDENTITY (1,1),
		Description VARCHAR(255)                  NOT NULL, 
		character CHAR(1) CHECK (character IN ('O', 'V', 'G')),

		NodeID  INT NOT NULL,     

        CONSTRAINT PK_AssessmentsCriteria PRIMARY KEY (ID),
		CONSTRAINT FK_AssesmentCriteria_Nodes_ID FOREIGN KEY (NodeID) REFERENCES Nodes (ID) ON DELETE CASCADE
		);

		CREATE TABLE LearningOutcome
		(
		ID          INT UNIQUE                    NOT NULL IDENTITY (1,1),
		Description VARCHAR(255)                  NOT NULL, 

		NodeID  INT NOT NULL,

		CONSTRAINT FK_LearningOutcome_Nodes_ID FOREIGN KEY (NodeID) REFERENCES Nodes (ID) ON DELETE CASCADE
		);
		

    CREATE TABLE Edges
    (
        ID          INT UNIQUE NOT NULL IDENTITY (1,1),

        SourceID    INT        NULL,
        TargetID    INT        NULL,
        SkillTreeID INT        NOT NULL,

        CONSTRAINT PK_Edges PRIMARY KEY (ID),
        CONSTRAINT CHK_SourceID_TargetID CHECK (SourceID != TargetID),
        CONSTRAINT FK_Edges_SkillTreeID_ID FOREIGN KEY (SkillTreeID) REFERENCES SkillTrees (ID) ON DELETE CASCADE,
        CONSTRAINT FK_Edges_ParentID_ID FOREIGN KEY (SourceID) REFERENCES Nodes (ID),
        CONSTRAINT FK_Edges_ChildID_ID FOREIGN KEY (TargetID) REFERENCES Nodes (ID),
        CONSTRAINT UQ_SourceID_TargetID UNIQUE (SourceID, TargetID)
    );


    COMMIT TRAN
END TRY
BEGIN CATCH
    THROW
    ROLLBACK TRAN
END CATCH