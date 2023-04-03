DROP TABLE IF EXISTS task, worker;

CREATE TABLE worker
(
    id       SERIAL PRIMARY KEY NOT NULL,
    name     varchar(255),
    position varchar(255),
    avatar   varchar(255)
);

INSERT INTO worker(name, position, avatar)
VALUES ('name', 'position', 'avatar');
INSERT INTO worker(name, position, avatar)
VALUES ('name', 'position', 'avatar');
INSERT INTO worker(name, position, avatar)
VALUES ('name', 'position', 'avatar');
INSERT INTO worker(name, position, avatar)
VALUES ('name', 'position', 'avatar');

CREATE TABLE task
(
    id          SERIAL PRIMARY KEY NOT NULL,
    title       varchar(255),
    description varchar(255),
    time        varchar(255),
    status      varchar(255),
    performer   INTEGER,
    FOREIGN KEY (performer) REFERENCES worker (id)
);
INSERT INTO task(title, description, time, status, performer)
VALUES ('title', 'description', 'time', 'status', 1);
INSERT INTO task(title, description, time, status, performer)
VALUES ('title', 'description', 'time', 'status', 2);
INSERT INTO task(title, description, time, status, performer)
VALUES ('title', 'description', 'time', 'status', 3);
INSERT INTO task(title, description, time, status, performer)
VALUES ('title', 'description', 'time', 'status', 1);