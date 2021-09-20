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
PRIMARY KEY (issueID)
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