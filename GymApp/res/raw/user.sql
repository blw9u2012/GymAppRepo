/*************************************************************************
SQL Files To Import into Database
**************************************************************************/
CREATE TABLE [User]
(
    [id] INTEGER NOT NULL,
    [name] NVARCHAR(30) NOT NULL,
    [email] NVARCHAR(30) NOT NULL,
    [phone] NVARCHAR(10) NOT NULL,
    CONSTRAINT [PK_User] PRIMARY KEY ([userId]) 
);


INSERT INTO [User] ([id], [name], [email], [phone]) VALUE (1, 'Brandon', 'brandonlwalton@gmail.com', '2158344621');

INSERT INTO [User] ([id], [name], [email], [phone]) VALUE (2, 'Alan', 'alan@gmail.com', '2154894621');

INSERT INTO [User] ([id], [name], [email], [phone]) VALUE (3, 'Alex', 'alex@gmail.com', '2158778523');
