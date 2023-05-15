--liquibase formatted sql

--changeset romanova:create-user-link-table-1
CREATE TABLE "user_link" (
                             link_id bigint REFERENCES "link" (id),
                             chat_id bigint REFERENCES "user" (chat_id),
                             PRIMARY KEY (link_id,chat_id)
);

--rollback DROP TABLE "user_link";