--liquibase formatted sql

--changeset romanova:create-user-table-1
CREATE TABLE "user" (
                        chat_id bigint PRIMARY KEY,
                        username text UNIQUE NOT NULL,
                        first_name text,
                        last_name text
);

--rollback DROP TABLE "user";