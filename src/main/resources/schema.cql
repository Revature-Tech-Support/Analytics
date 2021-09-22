CREATE KEYSPACE IF NOT EXISTS TechSupport WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE TechSupport;

CREATE TABLE IF NOT EXISTS User (
userID UUID,
username TEXT,
password TEXT,
isTechAgent BOOLEAN,
PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS Issue (
issueID UUID,
issueTitle TEXT,
openedBy UUID,
closedBy UUID,
openTime TIMESTAMP,
reviewTime TIMESTAMP,
closedTime TIMESTAMP,
inQueue BOOLEAN,
PRIMARY KEY (issueID, closedBy)
);

CREATE TABLE IF NOT EXISTS Chat (
issueID UUID,
chatID UUID,
issueTitle TEXT,
user UUID,
agent UUID,
active BOOLEAN,
PRIMARY KEY (issueID, agent)
);

CREATE TABLE IF NOT EXISTS Message (
messageID UUID,
message TEXT,
postedBy UUID,
postTime TIMESTAMP,
PRIMARY KEY (messageID)
);

INSERT INTO Issue (issueid,issuetitle,openedby,closedby,opentime,reviewtime,closedtime,inQueue)
VALUES (
    d03ab884-6515-4e63-90b6-1a9c6076a012,
    'Setup Issue',
    d00af75e-7176-4b92-bef0-31bed390741f,
    d00af75e-7176-4b92-bef0-31bed390741f,
    '2021-09-20 15:30:48+0000',
    '2021-09-20 15:35:48+0000',
    '2021-09-20 15:50:48+0000',
    false
);

INSERT INTO Issue (issueid,issuetitle,openedby,closedby,opentime,reviewtime,closedtime,inQueue)
VALUES (
    5c9ea820-0aa6-43c9-ab90-fb6a9e293d65,
    'Test',
    d00af75e-7176-4b92-bef0-31bed390741f,
    d00af75e-7176-4b92-bef0-31bed390741f,
    '2021-09-20 15:30:48+0000',
    '2021-09-20 15:30:48+0000',
    '2021-09-20 15:50:48+0000',
    true
);

INSERT INTO Issue (issueid,issuetitle,openedby,closedby,opentime,reviewtime,closedtime,inQueue)
VALUES (
    2485fbfd-86d7-4011-b55f-9b6e500b888c,
    'Setup Issue',
    d00af75e-7176-4b92-bef0-31bed390741f,
    d00af75e-7176-4b92-bef0-31bed390741f,
    '2021-09-20 15:30:48+0000',
    '2021-09-20 15:40:48+0000',
    '2021-09-20 15:50:48+0000',
    false
);
