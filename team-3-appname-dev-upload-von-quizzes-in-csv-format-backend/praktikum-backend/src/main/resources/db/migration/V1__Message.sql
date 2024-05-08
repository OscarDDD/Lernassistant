CREATE TABLE message (
    id              uuid                            NOT NULL PRIMARY KEY,
    content         character varying(255)          NOT NULL,
    created_at      timestamp(6) with time zone     NOT NULL,
    author          character varying(255)          NOT NULL
);