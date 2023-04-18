CREATE TABLE links (id INTEGER PRIMARY KEY, link VARCHAR(128) NOT NULL);

CREATE TABLE tgChats (id INTEGER PRIMARY KEY, tg_chat_id BIGINT NOT NULL);

CREATE TABLE links_tgChats (
    LinkID INTEGER,
    FOREIGN KEY (LinkID) REFERENCES links (id),
    ChatID INTEGER,
    FOREIGN KEY (ChatID) REFERENCES tgChats (id)
);