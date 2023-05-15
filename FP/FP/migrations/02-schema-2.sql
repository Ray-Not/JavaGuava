--liquibase formatted sql

--changeset romanova:create-link-table-1
CREATE TABLE "link" (
                        id bigserial PRIMARY KEY,
                        url text UNIQUE NOT NULL,
                        checked_at timestamp NOT NULL,
                        gh_pushed_at timestamp,
                        gh_description text,
                        gh_forks_count int,
                        so_answer_count int,
                        so_last_edit_date timestamp
);

--rollback DROP TABLE "link";