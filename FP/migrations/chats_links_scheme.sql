CREATE TABLE links (id INTEGER, link VARCHAR(128) NOT NULL);

CREATE TABLE tgChats (id INTEGER, tg_chat_id BIGINT NOT NULL);

CREATE TABLE links_tgChats (
    linkid INTEGER,
    chatid INTEGER
);