DROP TABLE IF EXISTS BILL;

CREATE TABLE BILL (
    id IDENTITY,
    name VARCHAR(200) not null,
    uuid VARCHAR(200) not null,
    currency VARCHAR(3) not null
);

DROP TABLE IF EXISTS LINE_ITEM;

CREATE TABLE LINE_ITEM (
    id IDENTITY,
    uuid VARCHAR(200) not null,
    description VARCHAR(200) not null,
    amount NUMBER(10, 2) not null,
    user_id NUMBER(10) not null,
    bill NUMBER(10) not null
);

DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
    id IDENTITY,
    uuid VARCHAR(200),
    name VARCHAR(200),
    bill NUMBER(10)
);